package tianci.pinao.dts.sal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TemReader {
	
	private TemMonitor tm;
	
	private Log logger = LogFactory.getLog(getClass());

	{
		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("TemMonitor");
		tm = new TemMonitor();
		// TODO switchcom & port load from db?
		int state = tm.InitDts(888, 0);
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
	
	public int read(int channel, int length, double[] stock, double[] unstock, double[] referTem, double[] term){
		int ret = 0;
		tm.PickData(channel, length, stock, unstock, referTem, term, ret);
		return ret;
	}
}
