package com.dain.instrumentation.model.dao.inf.management;

import java.util.ArrayList;

import com.dain.instrumentation.model.vo.SensorsVO;

public interface IDataInfoDAO {
	
	//로거명으로 데이터 검색
	ArrayList<SensorsVO> selectSensorByLogger(String tb);
	
}
