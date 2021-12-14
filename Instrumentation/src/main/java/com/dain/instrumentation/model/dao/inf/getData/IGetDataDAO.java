package com.dain.instrumentation.model.dao.inf.getData;

import java.util.List;
import java.util.Map;

import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.IPIVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;

public interface IGetDataDAO {
	List<String> ReadTableNames(String tbName);
	String[] readSystemInit(String loggerName);
	String[] readSystemLast(String loggerName);
	List<PlaceSetVO> ReadPlaceSet(String lg);
	WaterSenResultVO ReadSensor(String logger, String name, int manageCal);
	List<IPIVO> ReadIpiData(String[] ipiName, int[] depth, String logger, String order, int manageCalIndex);

	List<String> ReadColumns(String logger);
	String[] ReadSystemFirst(String logger, String[] columns, Map<String, Integer> manageCalIndex);
	List<String[]> ReadSysteamDatas(String logger, String[] columns, Map<String, Integer> manageCalIndex, String start, String end);
	
	String readWlFirst(PlaceSetVO senInfo);
	List<String[]> ReadWlDatas(PlaceSetVO senInfo, String start, String end, String[] outputColumns);
	
	String[] ReadIpiFirst(String logger, String[] columns, Map<String, Integer> manageCalIndex);
	List<String[]> ReadIpiDatas(String logger, String[] columns, Map<String, Integer> manageCalIndex, String start, String end);
}
