package tianci.pinao.dts.tasks.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tianci.pinao.dts.models.Temperature;

public interface TemDao {

	public void saveTem(Temperature tm, long date);

	public Timestamp getTemMaxTime();
	
	public void copyTem(Timestamp ts);
	
	public void removeTem(Timestamp ts);

	public Map<Integer, List<String>> getTemsByChannels(Set<Integer> ids);

}
