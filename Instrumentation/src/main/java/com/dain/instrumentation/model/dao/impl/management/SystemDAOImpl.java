package com.dain.instrumentation.model.dao.impl.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.dain.instrumentation.model.dao.inf.management.ISystemDAO;
import com.dain.instrumentation.util.DBConnection;

public class SystemDAOImpl implements ISystemDAO {
	private static final String SQL_SELECT_STARTDATE = "SELECT date FROM ? WHERE date ORDER BY date asc LIMIT 1,1";
	private static final String SQL_SELECT_LASTDATE = "SELECT date FROM ? WHERE date ORDER BY date desc LIMIT 1,1";
	
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public Timestamp findStartDate(String tbName) {
		Timestamp startDate = new Timestamp(System.currentTimeMillis());
		conn = DBConnection.getConn();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_STARTDATE);
			pstmt.setString(1, tbName);
			rs = pstmt.executeQuery();
			rs.next();
			startDate = rs.getTimestamp(1);
			
		} catch (SQLException e) {
			System.out.println("sql오류");
			e.printStackTrace();
		}
		
		DBConnection.releaseRS(rs);
		DBConnection.releasePstmt(pstmt);
		DBConnection.terminateDB();
		return startDate;
	}

	@Override
	public Timestamp findLastDate(String tbName) {
		Timestamp lastDate = new Timestamp(System.currentTimeMillis());
		conn = DBConnection.getConn();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_LASTDATE);
			pstmt.setString(1, tbName);
			rs = pstmt.executeQuery();
			rs.next();
			lastDate = rs.getTimestamp(1);
			
		} catch (SQLException e) {
			System.out.println("sql오류");
			e.printStackTrace();
		}
		
		DBConnection.releaseRS(rs);
		DBConnection.releasePstmt(pstmt);
		DBConnection.terminateDB();
		return lastDate;
	}

}
