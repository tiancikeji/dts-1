package tianci.pinao.dts.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import tianci.pinao.dts.models.Temperature;
import tianci.pinao.dts.util.PinaoConstants;

public class SaveTemTask implements Runnable {

	private static final Log logger = LogFactory.getLog(SaveTemTask.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String path = PinaoConstants.TEM_PATH;

	public static void main(String[] args) {
		new SaveTemTask().run();
	}
	
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
		    						if(cols != null && cols.length > 1){
		    							Temperature tem = new Temperature();
		    							tem.setChannel(NumberUtils.toInt(cols[0]));
		    							tem.setTem(cols[1]);
		    							if(cols.length > 3)
		    								tem.setStock(cols[2]);
		    							if(cols.length > 4)
		    								tem.setUnstock(cols[3]);
		    							if(cols.length > 5)
		    								tem.setReferTem(NumberUtils.toDouble(cols[4]));
		    							temps.add(tem);
		    						}
		    					}
		    				}
		    				sc.close();
		    				
					    	// 4. save into db
		    				if(temps.size() > 0){
		    					for(final Temperature tm : temps){
		    						String sqlh = "insert into dts.temperature(channel,tem,";
		    						String sqlt = " values(?,?,";
		    						List<Object> params = new ArrayList<Object>();
		    						
		    						params.add(tm.getChannel());
		    						params.add(tm.getTem());
		    						
		    						if(StringUtils.isNotBlank(tm.getStock())){
		    							sqlh += "stock,";
		    							sqlt += "?,";
		    							params.add(tm.getStock());
		    						}
									if(StringUtils.isNotBlank(tm.getUnstock())){
		    							sqlh += "unstock,";
										sqlt += "?,";
		    							params.add(tm.getUnstock());
		    						}
									if(tm.getReferTem() > 0){
		    							sqlh += "refer_tem,";
										sqlt += "?,";
		    							params.add(tm.getReferTem());
		    						}
	    							sqlh += "date)";
									sqlt += "?)";
									params.add(new Timestamp(date));
									
									jdbcTemplate.update(sqlh + sqlt, params.toArray());
		    					}
		    				}
		    				
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

