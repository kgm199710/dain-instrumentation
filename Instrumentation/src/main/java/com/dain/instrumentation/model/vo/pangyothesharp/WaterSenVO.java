package com.dain.instrumentation.model.vo.pangyothesharp;

public class WaterSenVO {
	String name;
	String time;
	String level;
	
	//baisc constructor
	public WaterSenVO() {}
	//full constructor
	public WaterSenVO(String name, String time, String level) {
		super();
		this.name = name;
		this.time = time;
		this.level = level;
	}
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	//toString
	@Override
	public String toString() {
		return "WaterSenVO [name=" + name + ", time=" + time + ", level=" + level + "]";
	}
}
