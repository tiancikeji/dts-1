package tianci.pinao.dts.sal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import tianci.pinao.dts.models.Channels;

public class TemReader {
	
	private TemMonitor tm;
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void init(){
		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("TemMonitor");

		final Channels channels = new Channels();
		jdbcTemplate.query("select * from dts.config limit 1", new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int index) throws SQLException {
				channels.setSwitchcom(rs.getInt("switchcom"));
				channels.setPort(rs.getInt("port"));
				return index;
			}
			
		});
		// switchcom & port load from db?
		if(channels.getSwitchcom() != null && channels.getSwitchcom() > 0 && channels.getPort() != null && channels.getPort() > 0){
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
	}
	
	public int read(int channel, int length, double[] stock, double[] unstock, double[] referTem, double[] term){
		int ret = 0;
		tm.PickData(channel, length, stock, unstock, referTem, term, ret);
		return ret;
	}
}
