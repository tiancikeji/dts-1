package tianci.pinao.dts.models;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Temperature {

	private int switchCom;
	
	private int port;
	
	private int channel;
	
	private int length;
	
	private double stock;
	
	private double unstock;
	
	private double referTem;
	
	private double tem;
	
	private int ret;
	
	private Date date;

	public int getSwitchCom() {
		return switchCom;
	}

	public void setSwitchCom(int switchCom) {
		this.switchCom = switchCom;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public double getUnstock() {
		return unstock;
	}

	public void setUnstock(double unstock) {
		this.unstock = unstock;
	}

	public double getReferTem() {
		return referTem;
	}

	public void setReferTem(double referTem) {
		this.referTem = referTem;
	}

	public double getTem() {
		return tem;
	}

	public void setTem(double tem) {
		this.tem = tem;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
