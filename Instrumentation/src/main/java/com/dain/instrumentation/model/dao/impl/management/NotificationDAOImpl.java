package com.dain.instrumentation.model.dao.impl.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.dain.instrumentation.model.dao.inf.management.INotificationDAO;
import com.dain.instrumentation.model.vo.NotificationVO;
import com.dain.instrumentation.util.DBConnection;

public class NotificationDAOImpl implements INotificationDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private final String SELECT_NOTIFICATION = "SELECT * FROM notification";
	
	@Override
	public ArrayList<NotificationVO> selectNotification() {
		conn = DBConnection.getConn();
		ArrayList<NotificationVO> nfList = new ArrayList<NotificationVO>();
		try {
			pstmt = conn.prepareStatement(SELECT_NOTIFICATION);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = rs.getInt(1);
				Timestamp date = rs.getTimestamp(2);
				String content = rs.getString(3);
				NotificationVO nf = new NotificationVO(idx, date, content);
				nfList.add(nf);
			}
		} catch (SQLException e) {
			System.out.println("sql 오류");
			e.printStackTrace();
		}
		DBConnection.releaseRS(rs);
		DBConnection.releasePstmt(pstmt);
		DBConnection.terminateDB();
		return nfList;
	}
}
