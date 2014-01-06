package tianci.pinao.dts.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import tianci.pinao.dts.models.Temperature;
import tianci.pinao.dts.util.PinaoConstants;

public class SaveTemTask implements Runnable {

	private static final Log logger = LogFactory.getLog(SaveTemTask.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String path = PinaoConstants.TEM_PATH;

    @Override
    public void run() {
    	long start = System.currentTimeMillis();
    	File dir = new File(path);
    	if(dir.exists() && dir.isDirectory()){
	    	// 1. read files
    		File[] files = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return NumberUtils.isNumber(name);
				}
			});
    		
    		List<File> paths = new ArrayList<File>();
    		if(files != null && files.length > 0){
    			// 2. rename files
	    		for(File file : files){
	    			try{
		    			File path = new File(file.getAbsolutePath() + PinaoConstants.TEM_WRITE_SUFFIX);
		    			file.renameTo(path);
		    			paths.add(path);
	    			} catch(Throwable t){
	    				t.printStackTrace();
	    				if(logger.isErrorEnabled())
	    					logger.error("Exception during rename file >> " + file);
	    			}
	    		}
	    		
	    		if(paths.size() > 0)
	    			for(File path : paths){
	    				try{
		    				final List<Temperature> temps = new ArrayList<Temperature>();
		    				final long date = NumberUtils.toLong(StringUtils.removeEnd(path.getName(), PinaoConstants.TEM_WRITE_SUFFIX));
					    	// 3. read file-data
		    				Scanner sc = new Scanner(new FileInputStream(path));
		    				while(sc.hasNextLine()){
		    					String line = sc.nextLine();
		    					if(StringUtils.isNotBlank(line)){
		    						String[] cols = StringUtils.split(line, PinaoConstants.TEM_DATA_COL_SEP);
		    						if(cols != null && cols.length > 7){
		    							Temperature tem = new Temperature();
		    							tem.setSwitchCom(NumberUtils.toInt(cols[0]));
		    							tem.setPort(NumberUtils.toInt(cols[1]));
		    							tem.setChannel(NumberUtils.toInt(cols[2]));
		    							tem.setLength(NumberUtils.toInt(cols[3]));
		    							tem.setStock(NumberUtils.toDouble(cols[4]));
		    							tem.setUnstock(NumberUtils.toDouble(cols[5]));
		    							tem.setReferTem(NumberUtils.toDouble(cols[6]));
		    							tem.setTem(NumberUtils.toDouble(cols[7]));
		    							temps.add(tem);
		    						}
		    					}
		    				}
		    				sc.close();
		    				
					    	// 4. save into db
		    				if(temps.size() > 0)
		    					jdbcTemplate.batchUpdate("insert into dts.temperature(switch_com,port,channel,length,stock,unstock,refer_tem,tem,date) values(?,?,?,?,?,?,?,?,?)", new BatchPreparedStatementSetter() {
									@Override
									public void setValues(PreparedStatement ps, int index) throws SQLException {
										if(index < temps.size()){
											Temperature tem = temps.get(index);
											if(tem != null){
												ps.setInt(1, tem.getSwitchCom());
												ps.setInt(2, tem.getPort());
												ps.setInt(3, tem.getChannel());
												ps.setInt(4, tem.getLength());
												ps.setDouble(5, tem.getStock());
												ps.setDouble(6, tem.getUnstock());
												ps.setDouble(7, tem.getReferTem());
												ps.setDouble(8, tem.getTem());
												ps.setTimestamp(9, new Timestamp(date));
											}
										}
									}
									@Override
									public int getBatchSize() {
										return temps.size();
									}
								});
		    				
					    	// 5. delete files
		    				path.delete();
	    				} catch(Throwable t){
		    				t.printStackTrace();
		    				if(logger.isErrorEnabled())
		    					logger.error("Exception during saving db >> " + path);
		    			}
	    			}
    		}
    	}
    	if(logger.isInfoEnabled())
    		logger.info("SaveTemTask used >> " + (System.currentTimeMillis() - start) + " ms.");
    }
}

