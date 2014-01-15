package tianci.pinao.dts.tasks;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogTemTask implements Runnable {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Override
    public void run() {
    	Timestamp ts = jdbcTemplate.queryForObject("select max(`date`) from dts.temperature where `date` < adddate(now(), interval -1 hour)", Timestamp.class); 
    	jdbcTemplate.update("insert into dts.temperature_log select * from dts.temperature where `date` <= ?", new Object[]{ts});
    	jdbcTemplate.update("delete from dts.temperature where `date` <= ?", new Object[]{ts});
    }
}

