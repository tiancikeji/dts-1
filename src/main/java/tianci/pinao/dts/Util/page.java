package tianci.pinao.dts.Util;


public class page {
	//全部记录数
	private int alltiao;
	//当前页显示记录数
	private int dan; 
	//当前页
	private int now;
	//全部页
	private int totalye;
	
	public int getAlltiao() {
		
		return alltiao;
	}
	public void setAlltiao(int alltiao) {
		if(alltiao>0)
		this.alltiao = alltiao;
		//当我设置总共记录数时候，就来计算总页数
		this.settotalpagecountbyrs();
	}
	public int getDan() {
		return dan;
	}
	public void setDan(int dan) {
		if(dan>0)
		this.dan = dan;
	}
	public int getNow() {
		return now;
	}
	public void setNow(int now) {
		this.now = now;
	}
	public int getTotalye() {
		if(totalye==0)
			return 0;
		return totalye;
	}
	public void setTotalye(int totalye) {
		this.totalye = totalye;
	}
	//得到总页数
	public int settotalpagecountbyrs(){
		if(alltiao%dan==0){
			totalye=alltiao/dan;
		}else if(alltiao%dan>0){
			totalye=alltiao/dan+1;
		}else{
			totalye=0;
		}
		return totalye;
	}
	
	
	//全部记录数
	//private int alltiao;
	//当前页显示记录数
	//private int dan; 
	//当前页
	//private int now;
	//全部页
	//private int totalye;
	
	//获得开始记录
	public int startrow(){
		return (now-1)*dan+1;		
	}
	
	//得到结束记录
	public int endrow(){
		//return now*dan;
		return dan;
	}
}
