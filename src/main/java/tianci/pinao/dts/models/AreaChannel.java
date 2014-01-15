package tianci.pinao.dts.models;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AreaChannel {

	private int areaId;
	
	private int channelId;
	
	private int scopeStart;
	
	private int scopeEnd;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getScopeStart() {
		return scopeStart;
	}

	public void setScopeStart(int scopeStart) {
		this.scopeStart = scopeStart;
	}

	public int getScopeEnd() {
		return scopeEnd;
	}

	public void setScopeEnd(int scopeEnd) {
		this.scopeEnd = scopeEnd;
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
}
