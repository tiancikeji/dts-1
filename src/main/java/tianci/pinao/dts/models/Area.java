package tianci.pinao.dts.models;

import java.util.List;

public class Area {
	private int id;
	private String name;
	private String created_at;
	private String background;
	private int pId;
	private int scope_start;
	private int scope_end;
	private boolean isParent;
	private boolean open;
	
	private List<AreaChannel> areaChannels;
	
	private List<Integer> channelids;
	
	private List<Integer> scopestarts;
	
	private List<Integer> scopeends;
	
	public boolean isOpen() {
		open = true;
		return open;
	}


	public boolean isParent() {
		isParent = true;
		if(this.pId ==0){
			isParent = false;
		}
		
		return isParent;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	
	
	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getScope_start() {
		return scope_start;
	}
	public void setScope_start(int scope_start) {
		this.scope_start = scope_start;
	}
	public int getScope_end() {
		return scope_end;
	}
	public void setScope_end(int scope_end) {
		this.scope_end = scope_end;
	}


	public List<AreaChannel> getAreaChannels() {
		return areaChannels;
	}


	public void setAreaChannels(List<AreaChannel> areaChannels) {
		this.areaChannels = areaChannels;
	}


	public List<Integer> getChannelids() {
		return channelids;
	}


	public void setChannelids(List<Integer> channelids) {
		this.channelids = channelids;
	}


	public List<Integer> getScopestarts() {
		return scopestarts;
	}


	public void setScopestarts(List<Integer> scopestarts) {
		this.scopestarts = scopestarts;
	}


	public List<Integer> getScopeends() {
		return scopeends;
	}


	public void setScopeends(List<Integer> scopeends) {
		this.scopeends = scopeends;
	}
	
}
