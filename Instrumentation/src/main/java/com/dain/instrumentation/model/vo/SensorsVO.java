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
	int mainVieewEnable;
	Timestamp sensorInit;
	String chkSms;
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
	float safeValue;
	int dataCount;
	int manageCalIndex;
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
			String senGroup, int senGroupOrder, Timestamp sensorInit, String chkSms, 
			String calculation1,String calculation2, String calculation3, String calculation4, String calculation5, String calculation6,
			boolean chkCal1, boolean chkCal2, boolean chkCal3, boolean chkCal4, boolean chkCal5, boolean chkCal6,
			float safeValue, int dataCount, int manageCalIndex, float safe1, float safe2, float safe3) {
		this(0, logger, senName, senInfo, senType, senOrder, senGroup, senGroupOrder, senGroupOrder, sensorInit, chkSms, calculation1, calculation2, calculation3, calculation4, calculation5, calculation6, chkCal1, chkCal2, chkCal3, chkCal4, chkCal5, chkCal6, safeValue, dataCount, manageCalIndex, safe1, safe2, safe3, null, null, null);
	}
	//full constructor
	public SensorsVO(int idx, String logger, String senName, String senInfo, String senType, int senOrder,
			String senGroup, int senGroupOrder, int mainVieewEnable, Timestamp sensorInit, String chkSms, String calculation1,
			String calculation2, String calculation3, String calculation4, String calculation5, String calculation6,
			boolean chkCal1, boolean chkCal2, boolean chkCal3, boolean chkCal4, boolean chkCal5, boolean chkCal6, 
			float safeValue, int dataCount, int manageCalIndex, float safe1, float safe2, float safe3,
			String etc1, String etc2, String etc3) {
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
		this.chkSms = chkSms;
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
		this.safeValue = safeValue;
		this.dataCount = dataCount;
		this.manageCalIndex = manageCalIndex;
		this.safe1 = safe1;
		this.safe2 = safe2;
		this.safe3 = safe3;
		this.etc1 = etc1;
		this.etc2 = etc2;
		this.etc3 = etc3;
	}
	
}
