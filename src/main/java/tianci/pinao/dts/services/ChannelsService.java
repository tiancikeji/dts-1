package tianci.pinao.dts.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tianci.pinao.dts.models.Channels;
import tianci.pinao.dts.models.InnerChannel;

@Component
public class ChannelsService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean saveChannels(final Channels channels){
		if(channels != null && channels.getSwitchcom() != null && channels.getSwitchcom() >= 0 && channels.getPort() != null && channels.getPort() >= 0){
			jdbcTemplate.execute("delete from dts.config");
			jdbcTemplate.update("insert into dts.config(switchcom,port,date) values(?,?,now())", new Object[]{channels.getSwitchcom(), channels.getPort()});
			jdbcTemplate.execute("delete from dts.channels");
			jdbcTemplate.batchUpdate("insert into dts.channels(channel, length, date) values(?,?,now())", new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pst, int index) throws SQLException {
					InnerChannel ic = channels.getChannels().get(index);
					pst.setInt(1, ic.getChannel());
					pst.setInt(2, ic.getLength());
				}
				
				@Override
				public int getBatchSize() {
					return channels.getChannels().size();
				}
			});
		}
		return true;
	}

	public Channels findChannels() {
		final Channels channels = new Channels();
		jdbcTemplate.query("select * from dts.config limit 1", new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int index) throws SQLException {
				channels.setSwitchcom(rs.getInt("switchcom"));
				channels.setPort(rs.getInt("port"));
				return index;
			}
			
		});
		
		final List<InnerChannel> innerchannels = new ArrayList<InnerChannel>();
		channels.setChannels(innerchannels);
		
		jdbcTemplate.query("select * from dts.channels", new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int index) throws SQLException {
				InnerChannel ic = new InnerChannel();
				ic.setChannel(rs.getInt("channel"));
				ic.setLength(rs.getInt("length"));
				innerchannels.add(ic);
				return index;
			}
			
		});
		
		return channels;
	}
}
