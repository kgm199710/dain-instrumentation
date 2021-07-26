package com.dain.instrumentation.model.vo;

import java.sql.Timestamp;

public class SensorsVO {
	int idx;
	String logger;
	String senName;
	String senInfo;
	String senType;
	int senOrder;
	String senGroup;
	int senGroupOrder;
	boolean mainVieewEnable;
	Timestamp sensorInit;
	String calculation1;
	String calculation2;
	String calculation3;
	String calculation4;
	String calculation5;
	String calculation6;
	boolean chkCal1;
	boolean chkCal2;
	boolean chkCal3;
	boolean chkCal4;
	boolean chkCal5;
	boolean chkCal6;
	boolean chkSms;
	float safe1;
	float safe2;
	float safe3;
	String etc1;
	String etc2;
	String etc3;
	
	//basic constructor
	public SensorsVO() {}
	//최초 생성시 입력값
	public SensorsVO(String logger, String senName, String senInfo, String senType, int senOrder,
			String senGroup, int senGroupOrder, Timestamp sensorInit, 
			String calculation1,String calculation2, String calculation3, String calculation4, String calculation5, String calculation6,
			boolean chkCal1, boolean chkCal2, boolean chkCal3, boolean chkCal4, boolean chkCal5, boolean chkCal6, boolean chkSms,
			float safe1, float safe2, float safe3) {
		this(0, logger, senName, senInfo, senType, senOrder, senGroup, senGroupOrder, true, sensorInit, calculation1, calculation2, calculation3, calculation4, calculation5, calculation6, chkCal1, chkCal2, chkCal3, chkCal4, chkCal5, chkCal6, chkSms, safe1, safe2, safe3, null, null, null);
	}
	//full constructor
	public SensorsVO(int idx, String logger, String senName, String senInfo, String senType, int senOrder,
			String senGroup, int senGroupOrder, boolean mainVieewEnable, Timestamp sensorInit, String calculation1,
			String calculation2, String calculation3, String calculation4, String calculation5, String calculation6,
			boolean chkCal1, boolean chkCal2, boolean chkCal3, boolean chkCal4, boolean chkCal5, boolean chkCal6, boolean chkSms, 
			float safe1, float safe2, float safe3, String etc1, String etc2, String etc3) {
		super();
		this.idx = idx;
		this.logger = logger;
		this.senName = senName;
		this.senInfo = senInfo;
		this.senType = senType;
		this.senOrder = senOrder;
		this.senGroup = senGroup;
		this.senGroupOrder = senGroupOrder;
		this.mainVieewEnable = mainVieewEnable;
		this.sensorInit = sensorInit;
		this.calculation1 = calculation1;
		this.calculation2 = calculation2;
		this.calculation3 = calculation3;
		this.calculation4 = calculation4;
		this.calculation5 = calculation5;
		this.calculation6 = calculation6;
		this.chkCal1 = chkCal1;
		this.chkCal2 = chkCal2;
		this.chkCal3 = chkCal3;
		this.chkCal4 = chkCal4;
		this.chkCal5 = chkCal5;
		this.chkCal6 = chkCal6;
		this.chkSms = chkSms;
		this.safe1 = safe1;
		this.safe2 = safe2;
		this.safe3 = safe3;
		this.etc1 = etc1;
		this.etc2 = etc2;
		this.etc3 = etc3;
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
	public String getSenName() {
		return senName;
	}
	public void setSenName(String senName) {
		this.senName = senName;
	}
	public String getSenInfo() {
		return senInfo;
	}
	public void setSenInfo(String senInfo) {
		this.senInfo = senInfo;
	}
	public String getSenType() {
		return senType;
	}
	public void setSenType(String senType) {
		this.senType = senType;
	}
	public int getSenOrder() {
		return senOrder;
	}
	public void setSenOrder(int senOrder) {
		this.senOrder = senOrder;
	}
	public String getSenGroup() {
		return senGroup;
	}
	public void setSenGroup(String senGroup) {
		this.senGroup = senGroup;
	}
	public int getSenGroupOrder() {
		return senGroupOrder;
	}
	public void setSenGroupOrder(int senGroupOrder) {
		this.senGroupOrder = senGroupOrder;
	}
	public boolean isMainVieewEnable() {
		return mainVieewEnable;
	}
	public void setMainVieewEnable(boolean mainVieewEnable) {
		this.mainVieewEnable = mainVieewEnable;
	}
	public Timestamp getSensorInit() {
		return sensorInit;
	}
	public void setSensorInit(Timestamp sensorInit) {
		this.sensorInit = sensorInit;
	}
	public String getCalculation1() {
		return calculation1;
	}
	public void setCalculation1(String calculation1) {
		this.calculation1 = calculation1;
	}
	public String getCalculation2() {
		return calculation2;
	}
	public void setCalculation2(String calculation2) {
		this.calculation2 = calculation2;
	}
	public String getCalculation3() {
		return calculation3;
	}
	public void setCalculation3(String calculation3) {
		this.calculation3 = calculation3;
	}
	public String getCalculation4() {
		return calculation4;
	}
	public void setCalculation4(String calculation4) {
		this.calculation4 = calculation4;
	}
	public String getCalculation5() {
		return calculation5;
	}
	public void setCalculation5(String calculation5) {
		this.calculation5 = calculation5;
	}
	public String getCalculation6() {
		return calculation6;
	}
	public void setCalculation6(String calculation6) {
		this.calculation6 = calculation6;
	}
	public boolean isChkCal1() {
		return chkCal1;
	}
	public void setChkCal1(boolean chkCal1) {
		this.chkCal1 = chkCal1;
	}
	public boolean isChkCal2() {
		return chkCal2;
	}
	public void setChkCal2(boolean chkCal2) {
		this.chkCal2 = chkCal2;
	}
	public boolean isChkCal3() {
		return chkCal3;
	}
	public void setChkCal3(boolean chkCal3) {
		this.chkCal3 = chkCal3;
	}
	public boolean isChkCal4() {
		return chkCal4;
	}
	public void setChkCal4(boolean chkCal4) {
		this.chkCal4 = chkCal4;
	}
	public boolean isChkCal5() {
		return chkCal5;
	}
	public void setChkCal5(boolean chkCal5) {
		this.chkCal5 = chkCal5;
	}
	public boolean isChkCal6() {
		return chkCal6;
	}
	public void setChkCal6(boolean chkCal6) {
		this.chkCal6 = chkCal6;
	}
	public boolean isChkSms() {
		return chkSms;
	}
	public void setChkSms(boolean chkSms) {
		this.chkSms = chkSms;
	}
	public float getSafe1() {
		return safe1;
	}
	public void setSafe1(float safe1) {
		this.safe1 = safe1;
	}
	public float getSafe2() {
		return safe2;
	}
	public void setSafe2(float safe2) {
		this.safe2 = safe2;
	}
	public float getSafe3() {
		return safe3;
	}
	public void setSafe3(float safe3) {
		this.safe3 = safe3;
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
	public String getEtc3() {
		return etc3;
	}
	public void setEtc3(String etc3) {
		this.etc3 = etc3;
	}
	
	@Override
	public String toString() {
		return "SensorsVO [idx=" + idx + ", logger=" + logger + ", senName=" + senName + ", senInfo=" + senInfo
				+ ", senType=" + senType + ", senOrder=" + senOrder + ", senGroup=" + senGroup + ", senGroupOrder="
				+ senGroupOrder + ", mainVieewEnable=" + mainVieewEnable + ", sensorInit=" + sensorInit
				+ ", calculation1=" + calculation1 + ", calculation2=" + calculation2 + ", calculation3=" + calculation3
				+ ", calculation4=" + calculation4 + ", calculation5=" + calculation5 + ", calculation6=" + calculation6
				+ ", chkCal1=" + chkCal1 + ", chkCal2=" + chkCal2 + ", chkCal3=" + chkCal3 + ", chkCal4=" + chkCal4
				+ ", chkCal5=" + chkCal5 + ", chkCal6=" + chkCal6 + ", chkSms=" + chkSms + ", safe1=" + safe1
				+ ", safe2=" + safe2 + ", safe3=" + safe3 + ", etc1=" + etc1 + ", etc2=" + etc2 + ", etc3=" + etc3
				+ "]";
	}
}
