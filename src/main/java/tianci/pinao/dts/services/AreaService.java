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
import tianci.pinao.dts.models.Channel;
import tianci.pinao.dts.models.Sensor;

@Component
public class AreaService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Boolean add(Area area) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String sql = "INSERT INTO AREA(name,created_at,parent_id,background,scope_start,scope_end) "
				+ "values(?,?,?,?,?,?)";
		// int result = jdbcTemplate.update(sql,new
		// Object[]{area.getName(),dateString,area.getpId(),area.getBackground(),area.getScope_start(),area.getScope_end()});
		int result = jdbcTemplate.update(sql, area.getName(), dateString,
				area.getpId(), area.getBackground(), area.getScope_start(),
				area.getScope_end());
		// \ ,new
		// Object[]{,dateString,area.getpId(),area.getBackground(),area.getScope_start(),area.getScope_end()});
		if (result >= 0) {
			return true;
		}
		return false;
	}

	public Boolean delete(int id) {
		int result = jdbcTemplate.update("DELETE FROM AREA where id = ?",
				new Object[] { id });
		if (result >= 0) {
			return true;
		}
		return false;
	}

	public Area findById(int id) {
		String sql = "SELECT * FROM AREA WHERE id = ?";
		Object[] params = new Object[] { id };
		List<Area> areaList = jdbcTemplate.query(sql, params, new AreaMapper());
		if (areaList.isEmpty()) {
			return null;
		}
		return (Area) areaList.get(0);
	}

	public List<Area> list() {
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

	// 设备全查询 增加 修改 删除
	public List<Sensor> findall() {
		String sql = "SELECT * FROM Sensor ";
		List<Sensor> SensorList = jdbcTemplate.query(sql, new SensorMapper());
		return SensorList;
	}

	protected class SensorMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Sensor sensor = new Sensor();
			sensor.setId(rs.getInt("id"));
			sensor.setName(rs.getString("name"));
			return sensor;
		}
	}

	// 设备 增加
	public boolean addSensor(Sensor sensor) {
		String sql = "INSERT INTO SENSOR(name)" + "values(?)";
		int result = jdbcTemplate
				.update(sql, new Object[] { sensor.getName() });
		if (result >= 0) {
			return true;
		}
		return false;
	}

	// 设备 删除
	public Boolean deletesensor(int id) {
		int result = jdbcTemplate.update("DELETE FROM SENSOR where id = ?",
				new Object[] { id });
		if (result >= 0) {
			return true;
		}
		return false;
	}

	// //设备 修改
	//
	// public Boolean updatesensor(int id){
	//
	// String sql="UPDATE SENSOR SET NAME = '"+name+"' WHERE id =  "+id;
	// int result=jdbcTemplate.update(sql);
	// if(result>0){
	// return true;
	// }
	// return false;
	// }

	// 管道全查询
	public List<Channel> findallchannel() {
		String sql = "SELECT * FROM channel";
		List<Channel> ChannelList = jdbcTemplate.query(sql, new ChannelMapper());
		return ChannelList;
	}

	// 管道增加 
	public Boolean addChannel(Channel channel) {
		String sql = "INSERT INTO CHANNEL(channelname,sensor_id)" + "values(?,?)";
		int result = jdbcTemplate.update(sql, new Object[] { channel.getChannelname(),
				channel.getSensor_id() });
		if (result > 0) {
			return true;
		}
		return false;
	}

	// 管道删除
	public Boolean deleteChannel(int id) {
		String sql = "delete from channel where id=?";
		int result = jdbcTemplate.update(sql, new Object[] { id });
		if (result > 0) {
			return true;
		}
		return false;
	}

	protected class ChannelMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Channel channel = new Channel();

			channel.setId(rs.getInt("id"));
			channel.setChannelname(rs.getString("channelname"));
			channel.setSensor_id(rs.getInt("sensor_id"));
			return channel;
		}
	}

}
