package com.dain.instrumentation.model.vo.pangyothesharp;

public class SystemVO {
	String name;
	String initDyear;
	String initDmonth;
	String initDday;
	String lastDyear;
	String lastDmonth;
	String lastDday;
	String lastDtime;
	
	//basic constructor
	public SystemVO() {}
	//full constructor
	public SystemVO(String name, String initDyear, String initDmonth, String initDday, String lastDyear,
			String lastDmonth, String lastDday, String lastDtime) {
		super();
		this.name = name;
		this.initDyear = initDyear;
		this.initDmonth = initDmonth;
		this.initDday = initDday;
		this.lastDyear = lastDyear;
		this.lastDmonth = lastDmonth;
		this.lastDday = lastDday;
		this.lastDtime = lastDtime;
	}
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitDyear() {
		return initDyear;
	}
	public void setInitDyear(String initDyear) {
		this.initDyear = initDyear;
	}
	public String getInitDmonth() {
		return initDmonth;
	}
	public void setInitDmonth(String initDmonth) {
		this.initDmonth = initDmonth;
	}
	public String getInitDday() {
		return initDday;
	}
	public void setInitDday(String initDday) {
		this.initDday = initDday;
	}
	public String getLastDyear() {
		return lastDyear;
	}
	public void setLastDyear(String lastDyear) {
		this.lastDyear = lastDyear;
	}
	public String getLastDmonth() {
		return lastDmonth;
	}
	public void setLastDmonth(String lastDmonth) {
		this.lastDmonth = lastDmonth;
	}
	public String getLastDday() {
		return lastDday;
	}
	public void setLastDday(String lastDday) {
		this.lastDday = lastDday;
	}
	public String getLastDtime() {
		return lastDtime;
	}
	public void setLastDtime(String lastDtime) {
		this.lastDtime = lastDtime;
	}
	
	//toString
	@Override
	public String toString() {
		return "SystemVO [name=" + name + ", initDyear=" + initDyear + ", initDmonth=" + initDmonth + ", initDday="
				+ initDday + ", lastDyear=" + lastDyear + ", lastDmonth=" + lastDmonth + ", lastDday=" + lastDday
				+ ", lastDtime=" + lastDtime + "]";
	}
	
}
