package com.dain.instrumentation.model.vo;

import java.sql.Timestamp;

public class SystemVO {
	int idx;
	String logger;
	Timestamp date;
	
	//basic constructor
	public SystemVO() {}
	//full constructor
	public SystemVO(int idx, String logger, Timestamp date) {
		super();
		this.idx = idx;
		this.logger = logger;
		this.date = date;
	}
	
	//getter/setter
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getLogger() {
		return logger;
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	//toString
	@Override
	public String toString() {
		return "SystemVO [idx=" + idx + ", logger=" + logger + ", date=" + date + "]";
	}
	
}
