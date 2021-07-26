package com.dain.instrumentation.model.dao.impl.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.dain.instrumentation.model.dao.inf.management.IDataInfoDAO;
import com.dain.instrumentation.model.vo.SensorsVO;
import com.dain.instrumentation.util.DBConnection;

public class DataInfoDAOImpl implements IDataInfoDAO {
	private final String SQL_SELECT_SENSOR_BY_LOGGER = "SELECT * FROM sensors where logger=?";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public ArrayList<SensorsVO> selectSensorByLogger(String tb) {
		conn = DBConnection.getConn();
		ArrayList<SensorsVO> ssList = new ArrayList<SensorsVO>();
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_SENSOR_BY_LOGGER);
			pstmt.setString(1, tb);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = rs.getInt(1);
				String logger = rs.getString(2);
				String senName = rs.getString(3);
				String senInfo = rs.getString(4);
				String senType = rs.getString(5);
				int senOrder = rs.getInt(6);
				String senGroup = rs.getString(7);
				int senGroupOrder = rs.getInt(8);
				boolean mainVieewEnable = rs.getInt(9)==1;
				Timestamp sensorInit = rs.getTimestamp(10);
				String calculation1 = rs.getString(11);
				String calculation2 = rs.getString(12);
				String calculation3 = rs.getString(13);
				String calculation4 = rs.getString(14);
				String calculation5 = rs.getString(15);
				String calculation6 = rs.getString(16);
				boolean chkCal1 = rs.getInt(17)==1;
				boolean chkCal2 = rs.getInt(18)==1;
				boolean chkCal3 = rs.getInt(19)==1;
				boolean chkCal4 = rs.getInt(20)==1;
				boolean chkCal5 = rs.getInt(21)==1;
				boolean chkCal6 = rs.getInt(22)==1;
				boolean chkSms = rs.getInt(23)==1;
				float safe1 = rs.getFloat(24);
				float safe2 = rs.getFloat(25);
				float safe3 = rs.getFloat(26);
				String etc1 = rs.getString(27);
				String etc2 = rs.getString(28);
				String etc3 = rs.getString(29);
				SensorsVO ss = new SensorsVO(idx, logger, senName, senInfo, senType, senOrder, senGroup, senGroupOrder, mainVieewEnable, sensorInit, calculation1, calculation2, calculation3, calculation4, calculation5, calculation6, chkCal1, chkCal2, chkCal3, chkCal4, chkCal5, chkCal6, chkSms, safe1, safe2, safe3, etc1, etc2, etc3);
//				System.out.println(ss);
				ssList.add(ss);
			}
		} catch (SQLException e) {
			System.out.println("sql오류");
			e.printStackTrace();
		}
		
		DBConnection.releaseRS(rs);
		DBConnection.releasePstmt(pstmt);
		DBConnection.terminateDB();
		return ssList;
	}
}
