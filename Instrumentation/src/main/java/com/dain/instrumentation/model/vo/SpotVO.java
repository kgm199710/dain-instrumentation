package com.dain.instrumentation.model.vo;

public class SpotVO {
	int idx;
	String spot;
	String places;
	String company;
	boolean view;
	String etc;
	
	//basic constructor
	public SpotVO() {}
	//최초 생성시 필수값
	public SpotVO(String spot, String places, String company) {
		this(spot, places, company, true);
	}
	//management main화면출력시필요값
	public SpotVO(String spot, String places, String company, boolean view) {
		this(spot, places, company, view, null);
	}
	// 혹시 몰라 만든거(etc포함)
	public SpotVO(String spot, String places, String company, boolean view, String etc) {
		this(0, spot, places, company, view, etc);
	}
	//full constructor
	public SpotVO(int idx, String spot, String places, String company, boolean view, String etc) {
		super();
		this.idx = idx;
		this.spot = spot;
		this.places = places;
		this.company = company;
		this.view = view;
		this.etc = etc;
	}
	
	// getter/setter
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getSpot() {
		return spot;
	}
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public String getPlaces() {
		return places;
	}
	public void setPlaces(String places) {
		this.places = places;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isView() {
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	@Override
	public String toString() {
		return "SpotVO [idx=" + idx + ", spot=" + spot + ", places=" + places + ", company=" + company + ", view="
				+ view + ", etc=" + etc + "]";
	}
	
	
	
}
