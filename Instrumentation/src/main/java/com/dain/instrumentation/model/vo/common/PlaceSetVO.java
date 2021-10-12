package com.dain.instrumentation.model.vo.common;

public class PlaceSetVO {
	int idx; 
	String logger_name;
	String sen_name;
	String sen_info;
	String sen_type;
	int sen_order;
	String sen_group;
	int sen_group_order;
	int main_view_enable;
	String sensor_init;
	String calculation1;
	String calculation2;
	String calculation3;
	String calculation4;
	String calculation5;
	String calculation6;
	String chk_sms;
	String chk_cal_1;
	String chk_cal_2;
	String chk_cal_3;
	String chk_cal_4;
	String chk_cal_5;
	String chk_cal_6;
	float safe_value;
	int data_count;
	int manage_cal_index;
	float safe_1;
	float safe_2;
	float safe_3;
	String sen_enddatetime;
	String sen_startdatetime;
	String etc1;
	String etc2;
	String etc3;
	String sen_data_type;
	String label_list;
	
	//basic constructor
	public PlaceSetVO() {}
	
	//업평균 조회 constructor
	public PlaceSetVO(int idx, String logger_name, String sen_name, String sen_info, String sen_type, int sen_order,
			String sen_group, int sen_group_order, int main_view_enable, String sensor_init, String calculation1,
			String calculation2, String calculation3, String calculation4, String calculation5, String calculation6,
			String chk_sms, String chk_cal_1, String chk_cal_2, String chk_cal_3, String chk_cal_4, String chk_cal_5,
			String chk_cal_6, float safe_value, int data_count, int manage_cal_index, float safe_1, float safe_2,
			float safe_3) {
		this( idx, logger_name, sen_name, sen_info, sen_type, sen_order, 
				sen_group, sen_group_order, main_view_enable, sensor_init, calculation1, 
				calculation2, calculation3, calculation4, calculation5, calculation6, 
				chk_sms, chk_cal_1, chk_cal_2, chk_cal_3, chk_cal_4, chk_cal_5, 
				chk_cal_6, safe_value, data_count, manage_cal_index, safe_1, safe_2, safe_3
				, null, null, null, null, null, null, null);
	}
	//full constructor
	public PlaceSetVO(int idx, String logger_name, String sen_name, String sen_info, String sen_type, int sen_order,
			String sen_group, int sen_group_order, int main_view_enable, String sensor_init, String calculation1,
			String calculation2, String calculation3, String calculation4, String calculation5, String calculation6,
			String chk_sms, String chk_cal_1, String chk_cal_2, String chk_cal_3, String chk_cal_4, String chk_cal_5,
			String chk_cal_6, float safe_value, int data_count, int manage_cal_index, float safe_1, float safe_2,
			float safe_3, String sen_enddatetime, String sen_startdatetime, String etc1, String etc2, String etc3,
			String sen_data_type, String label_list) {
		super();
		this.idx = idx;
		this.logger_name = logger_name;
		this.sen_name = sen_name;
		this.sen_info = sen_info;
		this.sen_type = sen_type;
		this.sen_order = sen_order;
		this.sen_group = sen_group;
		this.sen_group_order = sen_group_order;
		this.main_view_enable = main_view_enable;
		this.sensor_init = sensor_init;
		this.calculation1 = calculation1;
		this.calculation2 = calculation2;
		this.calculation3 = calculation3;
		this.calculation4 = calculation4;
		this.calculation5 = calculation5;
		this.calculation6 = calculation6;
		this.chk_sms = chk_sms;
		this.chk_cal_1 = chk_cal_1;
		this.chk_cal_2 = chk_cal_2;
		this.chk_cal_3 = chk_cal_3;
		this.chk_cal_4 = chk_cal_4;
		this.chk_cal_5 = chk_cal_5;
		this.chk_cal_6 = chk_cal_6;
		this.safe_value = safe_value;
		this.data_count = data_count;
		this.manage_cal_index = manage_cal_index;
		this.safe_1 = safe_1;
		this.safe_2 = safe_2;
		this.safe_3 = safe_3;
		this.sen_enddatetime = sen_enddatetime;
		this.sen_startdatetime = sen_startdatetime;
		this.etc1 = etc1;
		this.etc2 = etc2;
		this.etc3 = etc3;
		this.sen_data_type = sen_data_type;
		this.label_list = label_list;
	}
	
	//getter setter
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getLogger_name() {
		return logger_name;
	}
	public void setLogger_name(String logger_name) {
		this.logger_name = logger_name;
	}
	public String getSen_name() {
		return sen_name;
	}
	public void setSen_name(String sen_name) {
		this.sen_name = sen_name;
	}
	public String getSen_info() {
		return sen_info;
	}
	public void setSen_info(String sen_info) {
		this.sen_info = sen_info;
	}
	public String getSen_type() {
		return sen_type;
	}
	public void setSen_type(String sen_type) {
		this.sen_type = sen_type;
	}
	public int getSen_order() {
		return sen_order;
	}
	public void setSen_order(int sen_order) {
		this.sen_order = sen_order;
	}
	public String getSen_group() {
		return sen_group;
	}
	public void setSen_group(String sen_group) {
		this.sen_group = sen_group;
	}
	public int getSen_group_order() {
		return sen_group_order;
	}
	public void setSen_group_order(int sen_group_order) {
		this.sen_group_order = sen_group_order;
	}
	public int getMain_view_enable() {
		return main_view_enable;
	}
	public void setMain_view_enable(int main_view_enable) {
		this.main_view_enable = main_view_enable;
	}
	public String getSensor_init() {
		return sensor_init;
	}
	public void setSensor_init(String sensor_init) {
		this.sensor_init = sensor_init;
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
	public String getChk_sms() {
		return chk_sms;
	}
	public void setChk_sms(String chk_sms) {
		this.chk_sms = chk_sms;
	}
	public String getChk_cal_1() {
		return chk_cal_1;
	}
	public void setChk_cal_1(String chk_cal_1) {
		this.chk_cal_1 = chk_cal_1;
	}
	public String getChk_cal_2() {
		return chk_cal_2;
	}
	public void setChk_cal_2(String chk_cal_2) {
		this.chk_cal_2 = chk_cal_2;
	}
	public String getChk_cal_3() {
		return chk_cal_3;
	}
	public void setChk_cal_3(String chk_cal_3) {
		this.chk_cal_3 = chk_cal_3;
	}
	public String getChk_cal_4() {
		return chk_cal_4;
	}
	public void setChk_cal_4(String chk_cal_4) {
		this.chk_cal_4 = chk_cal_4;
	}
	public String getChk_cal_5() {
		return chk_cal_5;
	}
	public void setChk_cal_5(String chk_cal_5) {
		this.chk_cal_5 = chk_cal_5;
	}
	public String getChk_cal_6() {
		return chk_cal_6;
	}
	public void setChk_cal_6(String chk_cal_6) {
		this.chk_cal_6 = chk_cal_6;
	}
	public float getSafe_value() {
		return safe_value;
	}
	public void setSafe_value(float safe_value) {
		this.safe_value = safe_value;
	}
	public int getData_count() {
		return data_count;
	}
	public void setData_count(int data_count) {
		this.data_count = data_count;
	}
	public int getManage_cal_index() {
		return manage_cal_index;
	}
	public void setManage_cal_index(int manage_cal_index) {
		this.manage_cal_index = manage_cal_index;
	}
	public float getSafe_1() {
		return safe_1;
	}
	public void setSafe_1(float safe_1) {
		this.safe_1 = safe_1;
	}
	public float getSafe_2() {
		return safe_2;
	}
	public void setSafe_2(float safe_2) {
		this.safe_2 = safe_2;
	}
	public float getSafe_3() {
		return safe_3;
	}
	public void setSafe_3(float safe_3) {
		this.safe_3 = safe_3;
	}
	public String getSen_enddatetime() {
		return sen_enddatetime;
	}
	public void setSen_enddatetime(String sen_enddatetime) {
		this.sen_enddatetime = sen_enddatetime;
	}
	public String getSen_startdatetime() {
		return sen_startdatetime;
	}
	public void setSen_startdatetime(String sen_startdatetime) {
		this.sen_startdatetime = sen_startdatetime;
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
	public String getSen_data_type() {
		return sen_data_type;
	}
	public void setSen_data_type(String sen_data_type) {
		this.sen_data_type = sen_data_type;
	}
	public String getLabel_list() {
		return label_list;
	}
	public void setLabel_list(String label_list) {
		this.label_list = label_list;
	}
	
	//toString
	@Override
	public String toString() {
		return "placeSet [idx=" + idx + ", logger_name=" + logger_name + ", sen_name=" + sen_name + ", sen_info="
				+ sen_info + ", sen_type=" + sen_type + ", sen_order=" + sen_order + ", sen_group=" + sen_group
				+ ", sen_group_order=" + sen_group_order + ", main_view_enable=" + main_view_enable + ", sensor_init="
				+ sensor_init + ", calculation1=" + calculation1 + ", calculation2=" + calculation2 + ", calculation3="
				+ calculation3 + ", calculation4=" + calculation4 + ", calculation5=" + calculation5 + ", calculation6="
				+ calculation6 + ", chk_sms=" + chk_sms + ", chk_cal_1=" + chk_cal_1 + ", chk_cal_2=" + chk_cal_2
				+ ", chk_cal_3=" + chk_cal_3 + ", chk_cal_4=" + chk_cal_4 + ", chk_cal_5=" + chk_cal_5 + ", chk_cal_6="
				+ chk_cal_6 + ", safe_value=" + safe_value + ", data_count=" + data_count + ", manage_cal_index="
				+ manage_cal_index + ", safe_1=" + safe_1 + ", safe_2=" + safe_2 + ", safe_3=" + safe_3
				+ ", sen_enddatetime=" + sen_enddatetime + ", sen_startdatetime=" + sen_startdatetime + ", etc1=" + etc1
				+ ", etc2=" + etc2 + ", etc3=" + etc3 + ", sen_data_type=" + sen_data_type + ", label_list="
				+ label_list + "]";
	}
}
