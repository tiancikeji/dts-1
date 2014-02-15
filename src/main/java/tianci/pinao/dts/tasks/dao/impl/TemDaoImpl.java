package tianci.pinao.dts.tasks.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import tianci.pinao.dts.models.Temperature;
import tianci.pinao.dts.tasks.dao.TemDao;

public class TemDaoImpl extends JdbcDaoSupport implements TemDao {

	@Override
	public void saveTem(Temperature tm, long date) {
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
		
		getJdbcTemplate().update(sqlh + sqlt, params.toArray());
	}

	@Override
	public Timestamp getTemMaxTime() {
		return getJdbcTemplate().queryForObject("select max(`date`) from dts.temperature where `date` < adddate(now(), interval -1 hour)", Timestamp.class);
	}

	@Override
	public void copyTem(Timestamp ts) {
		getJdbcTemplate().update("insert into dts.temperature_log select * from dts.temperature where `date` <= ?", new Object[]{ts});
	}

	@Override
	public void removeTem(Timestamp ts) {
		getJdbcTemplate().update("delete from dts.temperature where `date` <= ?", new Object[]{ts});
	}

	@Override
	public Map<Integer, List<String>> getTemsByChannels(Set<Integer> ids) {
		final Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
		
		getJdbcTemplate().query("select channel, tem from dts.temperature where channel in (" + StringUtils.join(ids, ",") + ") order by `date` desc limit 1", new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int index) throws SQLException {
				int channel = rs.getInt("channel");
				List<String> temps = result.get(channel);
				if(temps == null){
					temps = new ArrayList<String>();
					result.put(channel, temps);
				}
				temps.add(rs.getString("tem"));
				return index;
			}
			
		});
		
		return result;
	}

}
