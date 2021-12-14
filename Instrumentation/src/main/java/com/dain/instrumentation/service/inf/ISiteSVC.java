package com.dain.instrumentation.service.inf;

import java.util.List;
import java.util.Map;

import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.IPIVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;

public interface ISiteSVC {

	String makeSystemHtml(List<SystemVO> syList, String[] systemName);

	List<String> getTableNames(String pageName);

	List<SystemVO> getSystemInitAndLast(List<String> lgList);

	List<PlaceSetVO> getPlaceSet(String pageName);

	List<WaterSenResultVO> getWaterSensers(List<PlaceSetVO> psList);

	String makeWaterSenHtml(List<WaterSenResultVO> wsList);

	String makeWaterSenJS(List<WaterSenResultVO> wsList);

	String makeWaterTableHtml(List<WaterSenResultVO> wsrList);

	List<IPIVO> getIPIGraphData(List<PlaceSetVO> psList, int[] depth, String[] ipiName, float[] existing, String logger);

	IPIVO getIPIMax(List<IPIVO> ipiList);

	String[][] getSystem(String logger, List<PlaceSetVO> psList, String start, String end);

	String[][] getWL(String select_id, List<PlaceSetVO> psList, String start, String end, String[] outputColumns);

//	String[][] getIPIGraphDatas(String[][] ipiName, int[][] depth, List<PlaceSetVO> psList, String selectIpi, String end, int viewGraphNum);
	
	String[][] getIPITotal(String logger, List<PlaceSetVO> psList, String start, String end, String[] columns, int[] depth);

}
