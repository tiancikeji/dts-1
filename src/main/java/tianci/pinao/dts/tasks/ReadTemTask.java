package tianci.pinao.dts.tasks;

import java.io.File;
import java.io.FileWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tianci.pinao.dts.sal.TemReader;
import tianci.pinao.dts.util.PinaoConstants;

@Service
public class ReadTemTask implements Runnable {

	private static final Log logger = LogFactory.getLog(ReadTemTask.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String path = PinaoConstants.TEM_PATH;
	
	@Autowired
	private TemReader termReader;
	
    @Override
    public void run() {
    	long start = System.currentTimeMillis();
    	try {
	    	File dir = new File(path);
	    	if(!(dir.exists() && dir.isDirectory()))
	    		dir.mkdirs();
	    	// 1. call jni
	    	// TODO get channel from db?
	    	int channel = 0;
	    	// TODO get length from db?
	    	int length = 1000;
	    	double[] stock = new double[length];
	    	double[] unstock = new double[length];
	    	double[] referTem = new double[1];
	    	double[] term = new double[length];
	    	long startSAL = System.currentTimeMillis();
	    	termReader.read(channel, length, stock, unstock, referTem, term);
	    	if(logger.isInfoEnabled())
	    		logger.info("termReader used >> " + (System.currentTimeMillis() - startSAL) + " ms.");
	    	
	    	//TODO get switchCom from db?
	    	int switchCom = 0;
	    	// TODO get port from db?
	    	int port = 0;
	    	StringBuilder sb = new StringBuilder();
	    	for(int i = 0; i < length; i ++){
	    		sb.append(switchCom);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(port);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(channel);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(i);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(stock[i]);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(unstock[i]);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(referTem[0]);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(term[i]);
	    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
	    		sb.append(PinaoConstants.TEM_DATA_LINE_SEP);
	    	}
	    	
	    	// 2. save into file-read
	    	long prefix = System.currentTimeMillis();
	    	File file = new File(path + "/" + prefix + PinaoConstants.TEM_READ_SUFFIX);
			FileWriter fw = new FileWriter(file);
			fw.write(sb.toString());
			fw.flush();
			fw.close();
			
	    	// 3. rename file-read to file
			file.renameTo(new File(dir, "" + prefix));
    	} catch (Throwable e) {
    		e.printStackTrace();
    		if(logger.isErrorEnabled())
    			logger.error("Exception when reading term >> ", e);
    	}
    	if(logger.isInfoEnabled())
    		logger.info("ReadTemTask used >> " + (System.currentTimeMillis() - start) + " ms.");
    }
}
