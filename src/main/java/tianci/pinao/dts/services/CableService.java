package tianci.pinao.dts.services;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.models.Cable;


@Component
public class CableService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Cable> findAll(){
		String sql = "SELECT * FROM CABLE limit 100";
		List<Cable> cableList = jdbcTemplate.query(sql, new CableMapper());
		return cableList;
	}

	public Map findByArea(Area area,int currPage,int maxLinks) {
		Map result = new HashMap();
		if(area == null)
			return null;
		StringBuffer sql = new StringBuffer("SELECT * FROM CABLE ");
		String timeQuery = getTime();
		
		String wheresql = "  WHERE length >= "+area.getScope_start()+" and  length <= "+area.getScope_end();
		sql.append(wheresql);
		
		sql.append(timeQuery);
		if(currPage != 0){
			sql.append("  LIMIT "+(currPage-1)*maxLinks+","+maxLinks*currPage);
		}

		List<Cable> cableList = jdbcTemplate.query(sql.toString(),new CableMapper());
		result.put("result", cableList);
		System.out.println(sql);
		StringBuffer countSql = new StringBuffer("SELECT COUNT(*) FROM CABLE");
		result.put("totalPages", jdbcTemplate.queryForInt(countSql.append(wheresql).append(timeQuery).toString()));
		return result;
	}

	private String getTime() {
		SimpleDateFormat   formatter   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		long currentTime = System.currentTimeMillis();
		Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间     
		String   currentStr   =   formatter.format(curDate); 
		long last4sec = currentTime - 4000;
		Date  lastDate = new Date(last4sec);
		String lastStr = formatter.format(lastDate); 
		String timeQuery = " AND created_at <=' "+ currentStr +"' AND created_at >= '"+lastStr+"'";
		return timeQuery;
	}
	
	 protected class CableMapper implements RowMapper{
				public Cable mapRow(ResultSet rs, int rowNum) throws SQLException {
					Cable cable = new Cable();
					cable.setId(rs.getInt("id"));
					cable.setCreated_at(rs.getString("created_at"));
					cable.setLength(rs.getString("length"));
					cable.setSignal1(rs.getString("signal_1"));
					cable.setSignal2(rs.getString("signal_2"));
					cable.setTemperature(rs.getDouble("temperature"));
					return cable;
				}
	 }
	 
	 

}
