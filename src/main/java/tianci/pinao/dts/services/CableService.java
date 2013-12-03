package tianci.pinao.dts.services;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.Util.page;
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
	//查询总行数
	public int findcount(){
		String sql="SELECT COUNT(*) FROM CABLE limit 100";
		int rowCount = this.jdbcTemplate.queryForInt(sql);
		return rowCount  ;
	}
	
	
	
	//分页查询
	public List<Cable>  find(int now,int dan){
		page p=new page();
		p.setNow(now);
		p.setDan(dan);
		int startrow=p.startrow();
		int endrow=p.endrow();
		Object[] params={startrow,endrow};	
		String sql="SELECT *  FROM cable  LIMIT ?,?";
		List<Cable> List =jdbcTemplate.query(sql,params,new CableMapper());
		//List<Cable> List = jdbcTemplate.query(sql,new CableMapper());
		return List;
	}
	
	
	

	public List<Cable> findByArea(Area area) {
		if(area == null)
			return null;
		String sql = "SELECT * FROM CABLE where id >= "+area.getScope_start()+" and  id <= "+area.getScope_end()+" limit 100";
		List<Cable> cableList = jdbcTemplate.query(sql,new CableMapper());
		return cableList;
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
