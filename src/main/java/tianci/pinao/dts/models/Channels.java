package tianci.pinao.dts.models;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Channels {

	private Integer switchcom;
	
	private Integer port;
	
	private List<Integer> channelids;
	
	private List<Integer> channellengths;
	
	private List<InnerChannel> channels;

	public Integer getSwitchcom() {
		return switchcom;
	}

	public void setSwitchcom(Integer switchcom) {
		this.switchcom = switchcom;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public List<Integer> getChannelids() {
		return channelids;
	}

	public void setChannelids(List<Integer> channelids) {
		this.channelids = channelids;
	}

	public List<Integer> getChannellengths() {
		return channellengths;
	}

	public void setChannellengths(List<Integer> channellengths) {
		this.channellengths = channellengths;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public List<InnerChannel> getChannels() {
		return channels;
	}

	public void setChannels(List<InnerChannel> channels) {
		this.channels = channels;
	}
}
