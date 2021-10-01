package com.dain.instrumentation.model.dao.inf.getData;

import java.util.List;

import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;

public interface IGetDataDAO {
	String getTableName(String tbName);
	List<String> ReadTableNames(String tbName);
	String readSystemInit(String loggerName);
	String readSystemLast(String loggerName);
}
