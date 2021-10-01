package com.dain.instrumentation.model.vo.pangyothesharp;

public class SystemVO {
	String name;
	String init;
	String last;
	
	
	//basic constructor
	public SystemVO() {}
	//full constructor
	public SystemVO(String name, String init, String last) {
		super();
		this.name = name;
		this.init = init;
		this.last = last;
	}
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	
	//toString
	@Override
	public String toString() {
		return "SystemVO [name=" + name + ", init=" + init + ", last=" + last + "]";
	}
	
	
}
