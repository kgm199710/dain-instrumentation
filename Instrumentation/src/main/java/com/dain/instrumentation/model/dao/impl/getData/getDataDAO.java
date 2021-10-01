package com.dain.instrumentation.model.dao.impl.getData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dain.instrumentation.model.dao.inf.getData.IGetDataDAO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.util.DBConnection2;


public class getDataDAO implements IGetDataDAO {

	final String SQL_READ_LOGGERNAME = "SELECT logger_name FROM logger WHERE logger_name=?";
	final String SQL_READ_LOGGERNAMES = "SELECT logger_name FROM logger WHERE logger_name LIKE ?";
	final String SQL_READ_SYSTEM = "SELECT t_date FROM ";
	final String SQL_READ_SYSTEM_INIT = " ORDER BY t_date asc LIMIT 1";
	final String SQL_READ_SYSTEM_LAST = " ORDER BY t_date desc LIMIT 1";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public String getTableName(String tbName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ReadTableNames(String tbName) {
		conn = DBConnection2.getConn();
		ArrayList<String> tbList = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(SQL_READ_LOGGERNAMES);
			pstmt.setString(1, tbName+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				tbList.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("getTableNames(String tbName)함수 sql오류");
		}
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return tbList;
	}
	
	@Override
	public String readSystemInit(String loggerName) {
		conn = DBConnection2.getConn();
		String result = null;
		try {
			pstmt = conn.prepareStatement(SQL_READ_SYSTEM + loggerName + SQL_READ_SYSTEM_INIT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("readSystemInit(String loggerName)함수 sql 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String readSystemLast(String loggerName) {
		conn = DBConnection2.getConn();
		String result = null;
		try {
			pstmt = conn.prepareStatement(SQL_READ_SYSTEM + loggerName + SQL_READ_SYSTEM_LAST);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("readSystemLast(String loggerName)함수 sql 오류");
			e.printStackTrace();
		}
		return result;
	}

}
