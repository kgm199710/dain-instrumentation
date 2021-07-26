package com.dain.instrumentation.model.vo;

import java.sql.Timestamp;

public class NotificationVO {
	int idx;
	Timestamp date;
	String content;
	
	//basic constructor
	public NotificationVO() {}
	//full constructor
	public NotificationVO(int idx, Timestamp date, String content) {
		super();
		this.idx = idx;
		this.date = date;
		this.content = content;
	}
	
	//getter/setter
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//toString
	@Override
	public String toString() {
		return "Notification [idx=" + idx + ", date=" + date + ", content=" + content + "]";
	}
}
