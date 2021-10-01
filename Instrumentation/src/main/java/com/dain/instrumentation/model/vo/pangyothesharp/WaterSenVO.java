package com.dain.instrumentation.model.vo.pangyothesharp;

public class WaterSenVO {
	String name;
	float level;
	String time;

	//basic constructor
	public WaterSenVO() {}
	//full constructor
	public WaterSenVO(String name, float level, String time) {
		super();
		this.name = name;
		this.level = level;
		this.time = time;
	}
	
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getLevel() {
		return level;
	}
	public void setLevel(float level) {
		this.level = level;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	//toString
	@Override
	public String toString() {
		return "WaterSenVO [name=" + name + ", level=" + level + ", time=" + time + "]";
	}
	
	
}
