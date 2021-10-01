package com.dain.instrumentation.model.vo.pangyothesharp;

public class IPIVO {
	String name;
	float level;
	String time;
	String depth;

	//basic constructor
	public IPIVO() {}
	public IPIVO(String name, float level, String time) {
		this(name, level, time, null);
	}
	//full constructor
	public IPIVO(String name, float level, String time, String depth) {
		super();
		this.name = name;
		this.level = level;
		this.time = time;
		this.depth = depth;
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
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "IPIVO [name=" + name + ", level=" + level + ", time=" + time + ", depth=" + depth + "]";
	}
	
	
}
