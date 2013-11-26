package tianci.pinao.dts.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.models.Cable;


@Component
public class CableService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Cable> findAll(){
		String sql = "SELECT * FROM CABLE limit 100";
		List<Cable> cableList = jdbcTemplate.query(sql, new RowMapper<Cable>() {
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
		});
		return cableList;
	}
}
