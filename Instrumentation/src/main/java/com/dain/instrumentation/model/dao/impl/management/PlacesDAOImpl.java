package com.dain.instrumentation.model.dao.impl.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dain.instrumentation.model.dao.inf.management.IPlacesDAO;
import com.dain.instrumentation.model.vo.PlacesVO;
import com.dain.instrumentation.model.vo.SpotVO;
import com.dain.instrumentation.util.DBConnection;

public class PlacesDAOImpl implements IPlacesDAO {

	private String SQL_SELECT_ONE = "SELECT ? FROM places where ?=?";
	private String SQL_SELECT_EPLACE_DONE_BY_PLACE = "SELECT e_place,done FROM places where place=?";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	@Override
	public ArrayList<PlacesVO> selectNeed(String pl) {
		conn = DBConnection.getConn();
		ArrayList<PlacesVO> plList = new ArrayList<PlacesVO>();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_EPLACE_DONE_BY_PLACE);
			pstmt.setString(1, pl);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String ePlace = rs.getString(1);
				boolean done = (rs.getInt(2)==1);
				PlacesVO plVO = new PlacesVO(pl, ePlace, done);
				System.out.println(plVO);
				plList.add(plVO);
			}
		} catch (SQLException e) {
			System.out.println("오류발생");
			e.printStackTrace();
		}
		DBConnection.releaseRS(rs);
		DBConnection.releasePstmt(pstmt);
		DBConnection.terminateDB();
		return plList;
	}
}
