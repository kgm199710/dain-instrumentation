package com.dain.instrumentation.model.dao.impl.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dain.instrumentation.model.dao.inf.management.ISpotDAO;
import com.dain.instrumentation.model.vo.SpotVO;
import com.dain.instrumentation.util.DBConnection;

public class SpotDAOImpl implements ISpotDAO {
	private String SQL_SELECT_ALL = "SELECT *FROM spot";
	private String SQL_SELECT_NEED = "SELECT idx,spot,places,company FROM spot where view=1";
	private String SQL_SELECT_ONE = "SELECT ? FROM spot where ?=?";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public ArrayList<SpotVO> selectAll() {
		conn = DBConnection.getConn();
		ArrayList<SpotVO> spList = new ArrayList<SpotVO>();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = rs.getInt(1);
				String spot = rs.getString(2);
				String place = rs.getString(3);
				String company = rs.getString(4);
				boolean viewEnable = (rs.getInt(5)==1)?true:false;
				String etc = rs.getString(6);
				SpotVO sp = new SpotVO(idx, spot, place, company, viewEnable, etc);
				spList.add(sp);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류");
			e.printStackTrace();
		}
		
		DBConnection.releaseRS(rs);
		DBConnection.releasePstmt(pstmt);
		DBConnection.terminateDB();
		return spList;
	}

	@Override
	public ArrayList<SpotVO> selectNeed() {
		// TODO Auto-generated method stub
		return null;
	}
}
