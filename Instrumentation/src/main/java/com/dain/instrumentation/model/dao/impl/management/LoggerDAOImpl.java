package com.dain.instrumentation.model.dao.impl.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dain.instrumentation.model.dao.inf.management.ILoggerDAO;
import com.dain.instrumentation.model.vo.LoggerVO;
import com.dain.instrumentation.util.DBConnection;

public class LoggerDAOImpl implements ILoggerDAO {
	private String SQL_SELECT_NAME_KOR = "SELECT name, name_kor FROM logger where name like ?";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public ArrayList<LoggerVO> selectNeed(String ePlace) {
		ArrayList<LoggerVO> lgList = new ArrayList<LoggerVO>();
		
		conn = DBConnection.getConn();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_NAME_KOR);
			pstmt.setString(1, ePlace+"_%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString(1);
				String nameKor = rs.getString(2);
				LoggerVO lg = new LoggerVO(name, nameKor, ePlace);
				lgList.add(lg);
			}
		} catch (SQLException e) {
			System.out.println("sql오류");
			e.printStackTrace();
		}
		DBConnection.releaseRS(rs);
		DBConnection.releasePstmt(pstmt);
		DBConnection.terminateDB();
		return lgList;
	}

}
