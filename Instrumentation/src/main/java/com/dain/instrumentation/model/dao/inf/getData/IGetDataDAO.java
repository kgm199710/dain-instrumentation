package com.dain.instrumentation.model.dao.inf.getData;

import java.util.List;

import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;

public interface IGetDataDAO {
	String getTableName(String tbName);
	List<String> ReadTableNames(String tbName);
	String[] readSystemInit(String loggerName);
	String[] readSystemLast(String loggerName);
//	String readSystemInit(String loggerName);
//	String readSystemLast(String loggerName);
	List<PlaceSetVO> ReadPlaceSet(String lg);
	WaterSenResultVO ReadSensor(String logger, String name, int manageCal);
}
