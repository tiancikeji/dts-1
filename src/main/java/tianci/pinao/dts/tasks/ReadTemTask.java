package tianci.pinao.dts.tasks;

import java.io.File;
import java.io.FileWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tianci.pinao.dts.models.Channels;
import tianci.pinao.dts.models.InnerChannel;
import tianci.pinao.dts.sal.TemMonitor;
import tianci.pinao.dts.services.ChannelsService;
import tianci.pinao.dts.util.PinaoConstants;

@Service
public class ReadTemTask implements Runnable {

	private static final Log logger = LogFactory.getLog(ReadTemTask.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String path = PinaoConstants.TEM_PATH;
	
	private boolean saveStock;
	
	private boolean saveReferTem;
	
	@Autowired
	private ChannelsService channelsService;
	
	private Holder holder = new Holder();
	
	private TemMonitor tm;
	
    @Override
    public void run() {
    	long start = System.currentTimeMillis();
    	try {
	    	File dir = new File(path);
	    	if(!(dir.exists() && dir.isDirectory()))
	    		dir.mkdirs();
	    	Channels channels = getChannels();
	    	if(channels != null && channels.getSwitchcom() != null && channels.getSwitchcom() > 0 
	    			&& channels.getPort() != null && channels.getPort() > 0
	    			&& channels.getChannels() != null && channels.getChannels().size() > 0){
	    		// init ...
	    		if(tm == null){
	    			System.loadLibrary("TemMonitor");
	    			tm = new TemMonitor();
	    			int state = tm.InitDts(channels.getSwitchcom(), channels.getPort());
	    			if(logger.isInfoEnabled())
	    				logger.info("Init Dts returned >> " + state);
	    			final TemMonitor tmp = tm;
	    			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	    				@Override
	    				public void run() {
	    					if(tmp != null){
	    						tmp.CloseDevice();
	    						if(logger.isInfoEnabled())
	    							logger.info("CloseDevice Dts");
	    					}
	    				}
	    			}));
	    		}
	    		
	    		for(InnerChannel ic : channels.getChannels()){
			    	// 1. call jni
			    	// get channel from db?
			    	int channel = ic.getChannel();
			    	// get length from db?
			    	int length = ic.getLength();
			    	double[] stock = new double[length];
			    	double[] unstock = new double[length];
			    	double[] referTem = new double[1];
			    	double[] tem = new double[length];
			    	long startSAL = System.currentTimeMillis();
			    	tm.PickData(channel, length, stock, unstock, referTem, tem, 0);
			    	if(logger.isInfoEnabled())
			    		logger.info("termReader used >> " + (System.currentTimeMillis() - startSAL) + " ms.");
			    	
			    	StringBuilder sb = new StringBuilder();
			    	sb.append(channel);
		    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
		    		for(double tm : tem)
		    			sb.append(tm + PinaoConstants.TEM_DATA_ELEMENT_SEP);
		    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
		    		if(isSaveStock()){
		    			for(double tm : stock)
		        			sb.append(tm + PinaoConstants.TEM_DATA_ELEMENT_SEP);
		        		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
		        		
		    			for(double tm : unstock)
		        			sb.append(tm + PinaoConstants.TEM_DATA_ELEMENT_SEP);
		    		}
		    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
		    		
		    		if(isSaveReferTem()){
		    			sb.append(referTem[0]);
		    		}
		    		sb.append(PinaoConstants.TEM_DATA_COL_SEP);
			    	
			    	// 2. save into file-read
			    	long prefix = System.currentTimeMillis();
			    	File file = new File(path + "/" + prefix + PinaoConstants.TEM_READ_SUFFIX);
					FileWriter fw = new FileWriter(file);
					fw.write(sb.toString());
					fw.flush();
					fw.close();
					
			    	// 3. rename file-read to file
					file.renameTo(new File(dir, "" + prefix));
	    		}
	    	}
    	} catch (Throwable e) {
    		e.printStackTrace();
    		if(logger.isErrorEnabled())
    			logger.error("Exception when reading term >> ", e);
    	}
    	if(logger.isInfoEnabled())
    		logger.info("ReadTemTask used >> " + (System.currentTimeMillis() - start) + " ms.");
    }

	private Channels getChannels() {
		if(holder.channels == null || (System.currentTimeMillis() - holder.time) > 600000){
			holder.channels = channelsService.findChannels();
			holder.time = System.currentTimeMillis();
		}
		return holder.channels;
	}

	public boolean isSaveStock() {
		return saveStock;
	}

	public void setSaveStock(boolean saveStock) {
		this.saveStock = saveStock;
	}

	public boolean isSaveReferTem() {
		return saveReferTem;
	}

	public void setSaveReferTem(boolean saveReferTem) {
		this.saveReferTem = saveReferTem;
	}
}

class Holder{
	Channels channels;
	
	long time;
}
