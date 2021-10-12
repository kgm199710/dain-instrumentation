package com.dain.instrumentation.service.inf;

import java.util.List;

import com.dain.instrumentation.model.vo.common.PlaceSetVO;
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

}
