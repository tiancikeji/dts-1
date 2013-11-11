package tianci.pinao.dts.models;

public class Cable {
	private Integer id;
	private String name;
	private String length;
	private String signal1;
	private String signal2;
	private String created_at;
	private double temperature;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getSignal1() {
		return signal1;
	}
	public void setSignal1(String signal1) {
		this.signal1 = signal1;
	}
	public String getSignal2() {
		return signal2;
	}
	public void setSignal2(String signal2) {
		this.signal2 = signal2;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
}
