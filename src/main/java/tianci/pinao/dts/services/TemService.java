package tianci.pinao.dts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.models.Area;
import tianci.pinao.dts.models.AreaChannel;

@Component
public class TemService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Boolean add(final Area area) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String dateString = formatter.format(currentTime);
		final String sql = "INSERT INTO dts.AREA(name,created_at,parent_id,background) "
				+ "values(?,?,?,?)";
	    KeyHolder holder = new GeneratedKeyHolder();
	    int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setObject(1, area.getName());
				ps.setObject(2, dateString);
				ps.setObject(3, area.getpId());
				ps.setObject(4, area.getBackground());
				return ps;
			}
		}, holder);
		if (result >= 0) {
	    	area.setId(holder.getKey().intValue());
	    	jdbcTemplate.batchUpdate("insert into dts.area_channel(area_id, channel_id, scope_start, scope_end) values(?,?,?,?)", new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int index) throws SQLException {
					if(area.getAreaChannels().size() > index){
						AreaChannel ac = area.getAreaChannels().get(index);
						if(ac != null){
							ps.setInt(1, area.getId());
							ps.setInt(2, ac.getChannelId());
							ps.setInt(3, ac.getScopeStart());
							ps.setInt(4, ac.getScopeEnd());
						}
					}
				}
				
				@Override
				public int getBatchSize() {
					return area.getAreaChannels().size();
				}
			});
			return true;
		}
		return false;
	}

	public Boolean delete(int id) {
		jdbcTemplate.update("delete from dts.area_channel where area_id = ?", new Object[] { id });
		int result = jdbcTemplate.update("DELETE FROM dts.AREA where id = ?",
				new Object[] { id });
		if (result >= 0) {
			return true;
		}
		return false;
	}

	public Area findById(int id) {
		String sql = "SELECT * FROM dts.AREA WHERE id = ?";
		Object[] params = new Object[] { id };
		List<Area> areaList = jdbcTemplate.query(sql, params, new AreaMapper());
		if (areaList.isEmpty()) {
			return null;
		}
		return (Area) areaList.get(0);
	}

	public List<Area> list() {
		String sql = "SELECT * FROM dts.AREA ";
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
