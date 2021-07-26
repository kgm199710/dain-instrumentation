package com.dain.instrumentation.model.dao.inf.management;

import java.util.ArrayList;

import com.dain.instrumentation.model.vo.LoggerVO;

public interface ILoggerDAO {

	ArrayList<LoggerVO> selectNeed(String ePlace);
	
}
