package tianci.pinao.dts.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.models.Sensor;

@Component
public class SensorService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;	
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
	public boolean add(Sensor sensor) {
		String sql = "INSERT INTO SENSOR(name)" + "values(?)";
		int result = jdbcTemplate
				.update(sql, new Object[] { sensor.getName() });
		if (result >= 0) {
			return true;
		}
		return false;
	}

	// 设备 删除
	public Boolean delete(int id) {
		int result = jdbcTemplate.update("DELETE FROM SENSOR where id = ?",
				new Object[] { id });
		if (result >= 0) {
			return true;
		}
		return false;
	}

}
