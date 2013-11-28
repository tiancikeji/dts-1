package tianci.pinao.dts.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.models.Area;

@Component
public class AreaService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public Boolean add(Area area){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		int result = jdbcTemplate.update("INSERT INTO AREA(name,created_at,parent_id,background,scope_start,scope_end) values(?,?,?,?,?,?)",
				new Object[]{area.getName(),dateString,area.getpId(),area.getBackground(),area.getScope_start(),area.getScope_end()});
		if(result >= 0 ){
			return true;
		}
		return false;
	}
	
	public Boolean delete(int id){
		int result = jdbcTemplate.update("DELETE FROM AREA where id = ?", new Object[]{id});
		if(result >= 0 ){
			return true;
		}
		return false;
	}
	
	public Area findById(int id){
		 String sql = "SELECT * FROM AREA WHERE id = ?";
		  Object[] params = new Object[] {id};
		  List<Area> areaList = jdbcTemplate.query(sql,params,new AreaMapper());
		  if(areaList.isEmpty()){
		   return null;
		  }
		  return (Area)areaList.get(0);
	}
	
	public List<Area> list(){
		String sql = "SELECT * FROM AREA ";
		List<Area> areaList = jdbcTemplate.query(sql, new AreaMapper());
		return areaList;
	}
	
	 protected class AreaMapper implements RowMapper {
		  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			  Area area = new Area();
				area.setId(rs.getInt("id"));
				area.setName(rs.getString("name"));
				area.setBackground(rs.getString("background"));
				area.setpId(rs.getInt("parent_id"));
				area.setScope_end(rs.getInt("scope_end"));
				area.setScope_start(rs.getInt("scope_start"));
				area.setCreated_at(rs.getString("created_at"));
				return area;
		  }
	}
}
