package com.dain.instrumentation.model.vo;

import java.sql.Timestamp;
import java.util.Date;

public class PlacesVO {
	int idx;
	String place;
	String ePlace;
	String mUser;
	Timestamp date;
	boolean done;
	String phone;
	String phone2;
	String phone3;
	String user_id;
	String user_pw;
	boolean viewEnable;
	boolean mobileEnable;
	String webSrc;
	String memo;
	String placeMemo;
	
	//basic constructor
	public PlacesVO() {}
	//management main화면출력시필요값
	public PlacesVO(String place, String ePlace, boolean done) {
		this(0, place, ePlace, null, null, done, null, null, null, null, null, true, true, null, null, null);
	}
	//정보 생성시 최소값
	public PlacesVO(String place, String ePlace, String mUser, String phone) {
		this(place, ePlace, mUser, phone, null, null, null, null, null, null, null);
	}
	//정보 생성시 입력값
	public PlacesVO(String place, String ePlace, String mUser, String phone, String phone2, String phone3, 
			String user_id, String user_pw, String webSrc, String memo, String placeMemo) {
		this(0, place, ePlace, mUser, null, true, phone, phone2, phone3, user_id, user_pw, true, false, webSrc, memo, placeMemo);
	}
	//full constructor
	public PlacesVO(int idx, String place, String ePlace, String mUser, Timestamp date, boolean done,
			String phone, String phone2, String phone3, String user_id, String user_pw, boolean viewEnable,
			boolean mobileEnable, String webSrc, String memo, String placeMemo) {
		super();
		this.idx = idx;
		this.place = place;
		this.ePlace = ePlace;
		this.mUser = mUser;
		this.date = date;
		this.done = done;
		this.phone = phone;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.viewEnable = viewEnable;
		this.mobileEnable = mobileEnable;
		this.webSrc = webSrc;
		this.memo = memo;
		this.placeMemo = placeMemo;
	}
	
	
	
	//getter/setter
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getePlace() {
		return ePlace;
	}
	public void setePlace(String ePlace) {
		this.ePlace = ePlace;
	}
	public String getmUser() {
		return mUser;
	}
	public void setmUser(String mUser) {
		this.mUser = mUser;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public boolean isViewEnable() {
		return viewEnable;
	}
	public void setViewEnable(boolean viewEnable) {
		this.viewEnable = viewEnable;
	}
	public boolean isMobileEnable() {
		return mobileEnable;
	}
	public void setMobileEnable(boolean mobileEnable) {
		this.mobileEnable = mobileEnable;
	}
	public String getWebSrc() {
		return webSrc;
	}
	public void setWebSrc(String webSrc) {
		this.webSrc = webSrc;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPlaceMemo() {
		return placeMemo;
	}
	public void setPlaceMemo(String placeMemo) {
		this.placeMemo = placeMemo;
	}
	
	//toString
	@Override
	public String toString() {
		return "PlacesVO [idx=" + idx + ", place=" + place + ", ePlace=" + ePlace + ", mUser=" + mUser + ", date="
				+ date + ", done=" + done + ", phone=" + phone + ", phone2=" + phone2 + ", phone3=" + phone3
				+ ", user_id=" + user_id + ", user_pw=" + user_pw + ", viewEnable=" + viewEnable + ", mobileEnable="
				+ mobileEnable + ", webSrc=" + webSrc + ", memo=" + memo + ", placeMemo=" + placeMemo + "]";
	}
	
	
	
}
