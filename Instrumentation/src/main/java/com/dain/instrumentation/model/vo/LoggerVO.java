package com.dain.instrumentation.model.vo;

public class LoggerVO {
	int idx;
	String name;
	String nameKor;
	String place;
	String filePath;
	String etc1;
	String etc2;
	
	//basic constructor
	public LoggerVO() {}

	//management main화면출력시필요값
	public LoggerVO(String name, String nameKor, String place) {
		this(name, nameKor, place, null);
	}
	//최초 생성시 필수값
	public LoggerVO(String name, String nameKor, String place, String filePath) {
		this(0, name, nameKor, place, filePath, null, null);
	}
	//full constructor
	public LoggerVO(int idx, String name, String nameKor, String place, String filePath, String etc1, String etc2) {
		super();
		this.idx = idx;
		this.name = name;
		this.nameKor = nameKor;
		this.place = place;
		this.filePath = filePath;
		this.etc1 = etc1;
		this.etc2 = etc2;
	}

	// getter/setter
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameKor() {
		return nameKor;
	}

	public void setNameKor(String nameKor) {
		this.nameKor = nameKor;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getEtc1() {
		return etc1;
	}

	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}

	public String getEtc2() {
		return etc2;
	}

	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}

	// toString
	@Override
	public String toString() {
		return "LoggerVO [idx=" + idx + ", name=" + name + ", nameKor=" + nameKor + ", place=" + place + ", filePath="
				+ filePath + ", etc1=" + etc1 + ", etc2=" + etc2 + "]";
	}
	
	
	
}
