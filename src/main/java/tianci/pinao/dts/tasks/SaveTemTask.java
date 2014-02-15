package tianci.pinao.dts.tasks;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tianci.pinao.dts.tasks.service.TemTaskService;

public class SaveTemTask implements Runnable {

	private static final Log logger = LogFactory.getLog(SaveTemTask.class);

	private TemTaskService temTaskService;
	
    @Override
    public void run() {
    	try{
    		temTaskService.saveTem();
    	} catch(Throwable t){
    		if(logger.isErrorEnabled())
    			logger.error("Exception when saving term >> ", t);
    	}
    }

	public TemTaskService getTemTaskService() {
		return temTaskService;
	}

	public void setTemTaskService(TemTaskService temTaskService) {
		this.temTaskService = temTaskService;
	}
}

