package tianci.pinao.dts.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.models.AreaChannel;
import tianci.pinao.dts.models.Channel;

@Component
public class ChannelService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	// 管道全查询
	public List<Channel> findall() {
		String sql = "SELECT * FROM dts.channel";
		List<Channel> ChannelList = jdbcTemplate.query(sql, new ChannelMapper());
		return ChannelList;
	}

	// 管道增加 
	public Boolean add(Channel channel) {
		String sql = "INSERT INTO dts.CHANNEL(name,sensor_id)" + "values(?,?)";
		int result = jdbcTemplate.update(sql, new Object[] { channel.getChannelname(),
				channel.getSensor_id() });
		if (result > 0) {
			return true;
		}
		return false;
	}

	// 管道删除
	public Boolean delete(int id) {
		String sql = "delete from dts.channel where id=?";
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
			channel.setChannelname(rs.getString("name"));
			channel.setSensor_id(rs.getInt("sensor_id"));
			return channel;
		}
	}

	public Map<Integer, List<AreaChannel>> findByAreaIds(List<Integer> ids) {
		final Map<Integer, List<AreaChannel>> result = new HashMap<Integer, List<AreaChannel>>();
		if(ids != null && ids.size() > 0)
			jdbcTemplate.query("select * from dts.area_channel where area_id in (" + StringUtils.join(ids, ",") + ")", new RowMapper<Integer>(){

				@Override
				public Integer mapRow(ResultSet rs, int index)
						throws SQLException {
					AreaChannel ac = new AreaChannel();
					ac.setAreaId(rs.getInt("area_id"));
					ac.setChannelId(rs.getInt("channel_id"));
					ac.setScopeStart(rs.getInt("scope_start"));
					ac.setScopeEnd(rs.getInt("scope_end"));
					List<AreaChannel> acs = result.get(ac.getAreaId());
					if(acs == null){
						acs = new ArrayList<AreaChannel>();
						result.put(ac.getAreaId(), acs);
					}
					acs.add(ac);
					return index;
				}
				
			});
		return result;
	}
	
	// 管道全查询
	/*public List<Channel> findall() {
		String sql = "SELECT * FROM dts.channel";
		List<Channel> ChannelList = jdbcTemplate.query(sql, new ChannelMapper());
		return ChannelList;
	}*/
}
