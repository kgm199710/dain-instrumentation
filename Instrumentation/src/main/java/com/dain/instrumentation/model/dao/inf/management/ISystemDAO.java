package com.dain.instrumentation.model.dao.inf.management;

import java.sql.Timestamp;

public interface ISystemDAO {

	Timestamp findStartDate(String tbName);

	Timestamp findLastDate(String tbName);

}
