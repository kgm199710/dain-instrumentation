package com.dain.instrumentation.model.vo;

import java.sql.Timestamp;

public class UserVO {
	int idx;
	String name;
	String login;
	String password;
	int position;
	Timestamp loginAt;
	String loginIp;
	String etc;
	
	//basic constructor
	public UserVO() {}
	//full constructor
	public UserVO(int idx, String name, String login, String password, int position, Timestamp loginAt, String loginIp,
			String etc) {
		super();
		this.idx = idx;
		this.name = name;
		this.login = login;
		this.password = password;
		this.position = position;
		this.loginAt = loginAt;
		this.loginIp = loginIp;
		this.etc = etc;
	}
	
	//getter,setter
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Timestamp getLoginAt() {
		return loginAt;
	}
	public void setLoginAt(Timestamp loginAt) {
		this.loginAt = loginAt;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	//toString
	@Override
	public String toString() {
		return "UserVO [idx=" + idx + ", name=" + name + ", login=" + login + ", password=" + password + ", position="
				+ position + ", loginAt=" + loginAt + ", loginIp=" + loginIp + ", etc=" + etc + "]";
	}
	
	
}
