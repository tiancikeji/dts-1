package tianci.pinao.dts.services;

import java.sql.ResultSet; 
import java.sql.SQLException;
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
		
		String wheresql = "  WHERE length >= "+area.getScope_start()+" and  length <= "+area.getScope_end();
		sql.append(wheresql);
		if(currPage != 0){
			sql.append("  LIMIT "+(currPage-1)*maxLinks+","+maxLinks*currPage);
		}
		System.out.println(sql.toString());
		List<Cable> cableList = jdbcTemplate.query(sql.toString(),new CableMapper());
		result.put("result", cableList);
		
		StringBuffer countSql = new StringBuffer("SELECT COUNT(*) FROM CABLE");
		result.put("totalPages", jdbcTemplate.queryForInt(countSql.append(wheresql).toString()));
		return result;
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
