package com.dain.instrumentation.service.inf;

import java.util.List;

import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;

public interface ISiteSVC {

	String makeSystemHtml(List<SystemVO> syList, String[] systemName);

	List<String> getTableNames(String pageName);

	List<SystemVO> getSystemInitAndLast(List<String> lgList);
	
}
