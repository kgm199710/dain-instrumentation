package com.dain.instrumentation.model.dao.impl.getData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dain.instrumentation.model.dao.inf.getData.IGetDataDAO;
import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenVO;
import com.dain.instrumentation.util.DBConnection2;


public class getDataDAO implements IGetDataDAO {

	final String SQL_READ_LOGGERNAME = "SELECT logger_name FROM logger WHERE logger_name=?";
	final String SQL_READ_LOGGERNAMES = "SELECT logger_name FROM logger WHERE logger_name LIKE ?";
	final String SQL_READ_SYSTEM = "SELECT dyear, dmonth, dday, dtime FROM ";
	final String SQL_READ_SYSTEM_INIT = " ORDER BY t_date asc LIMIT 1";
	final String SQL_READ_SYSTEM_LAST = " ORDER BY t_date desc LIMIT 1";
	//SELECT * FROM place_set WHERE logger_name LIKE '?(+%)' and sen_type = "Water Level Sensor" ORDER BY sen_name asc;
	final String SQL_READ_PLACESET = "SELECT * FROM place_set WHERE logger_name LIKE ?";
	final String SQL_READ_SENSORS_1 = "SELECT t_date, ";
	final String SQL_READ_SENSORS_2 = "_1 FROM ";
	final String SQL_READ_SENSORS_3 = " ORDER BY t_date ASC LIMIT 1";
	final String SQL_READ_SENSORS_4 = " ORDER BY t_date DESC LIMIT 24";
	
	
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
	public String[] readSystemInit(String loggerName) {
		conn = DBConnection2.getConn();
		String[] result = new String[3];
		try {
			pstmt = conn.prepareStatement(SQL_READ_SYSTEM + loggerName + SQL_READ_SYSTEM_INIT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
			}
		} catch (SQLException e) {
			System.out.println("readSystemInit(String loggerName)함수 sql 오류");
			e.printStackTrace();
		}
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public String[] readSystemLast(String loggerName) {
		conn = DBConnection2.getConn();
		String[] result = new String[4];
		try {
			pstmt = conn.prepareStatement(SQL_READ_SYSTEM + loggerName + SQL_READ_SYSTEM_LAST);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = rs.getString(4);
			}
		} catch (SQLException e) {
			System.out.println("readSystemLast(String loggerName)함수 sql 오류");
			e.printStackTrace();
		}
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public List<PlaceSetVO> ReadPlaceSet(String pageName) {
		List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
		conn = DBConnection2.getConn();
		try {
			pstmt = conn.prepareStatement(SQL_READ_PLACESET);
			pstmt.setString(1, pageName+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = rs.getInt(1); 
				String logger_name = rs.getString(2);
				String sen_name = rs.getString(3);
				String sen_info = rs.getString(4);
				String sen_type = rs.getString(5);
				int sen_order = rs.getInt(6);
				String sen_group = rs.getString(7);
				int sen_group_order = rs.getInt(8);
				int main_view_enable = rs.getInt(9);
				String sensor_init = rs.getString(10);
				String calculation1 = rs.getString(11);
				String calculation2 = rs.getString(12);
				String calculation3 = rs.getString(13);
				String calculation4 = rs.getString(14);
				String calculation5 = rs.getString(15);
				String calculation6 = rs.getString(16);
				String chk_sms = rs.getString(17);
				String chk_cal_1 = rs.getString(18);
				String chk_cal_2 = rs.getString(19);
				String chk_cal_3 = rs.getString(20);
				String chk_cal_4 = rs.getString(21);
				String chk_cal_5 = rs.getString(22);
				String chk_cal_6 = rs.getString(23);
				float safe_value = rs.getFloat(24);
				int data_count = rs.getInt(25);
				int manage_cal_index = rs.getInt(26);
				float safe_1 = rs.getFloat(27);
				float safe_2 = rs.getFloat(28);
				float safe_3 = rs.getFloat(29);
				PlaceSetVO ps = new PlaceSetVO(
						idx, logger_name, sen_name, sen_info, sen_type, sen_order, 
						sen_group, sen_group_order, main_view_enable, sensor_init, calculation1, 
						calculation2, calculation3, calculation4, calculation5, calculation6, 
						chk_sms, chk_cal_1, chk_cal_2, chk_cal_3, chk_cal_4, chk_cal_5, 
						chk_cal_6, safe_value, data_count, manage_cal_index, safe_1, safe_2, safe_3);
				psList.add(ps);
			}
		} catch (SQLException e) {
			System.out.println("ReadPlaceSet(String lg)오류발생");
			e.printStackTrace();
		}
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return psList;
	}

	@Override
	public WaterSenResultVO ReadSensor(String logger, String name, int manageCal) {
		conn = DBConnection2.getConn();
		WaterSenResultVO wsr = new WaterSenResultVO();
		List<WaterSenVO> wsList = new ArrayList<WaterSenVO>();
		try {
			String sql = SQL_READ_SENSORS_1 + name + SQL_READ_SENSORS_2 + logger + SQL_READ_SENSORS_3;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WaterSenVO ws = new WaterSenVO(name, rs.getString(1), rs.getString(2).split("\\|")[manageCal-1]);
				wsList.add(ws);
			}
			sql = SQL_READ_SENSORS_1 + name + SQL_READ_SENSORS_2 + logger + SQL_READ_SENSORS_4;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WaterSenVO ws = new WaterSenVO(name, rs.getString(1), rs.getString(2).split("\\|")[manageCal-1]);
				wsList.add(ws);
			}
			wsr.setName(name);
			wsr.setInit(wsList.get(0));
			wsr.setNow(wsList.get(1));
			wsr.setB1(wsList.get(2));
			wsr.setB2(wsList.get(3));
			wsr.setB3(wsList.get(4));
			wsr.setB4(wsList.get(5));
			wsr.setB5(wsList.get(6));
			wsr.setB6(wsList.get(7));
			wsr.setB7(wsList.get(8));
			wsr.setB8(wsList.get(9));
			wsr.setB9(wsList.get(10));
			wsr.setB10(wsList.get(11));
			wsr.setB11(wsList.get(12));
			wsr.setB12(wsList.get(13));
			wsr.setB13(wsList.get(14));
			wsr.setB14(wsList.get(15));
			wsr.setB15(wsList.get(16));
			wsr.setB16(wsList.get(17));
			wsr.setB17(wsList.get(18));
			wsr.setB18(wsList.get(19));
			wsr.setB19(wsList.get(20));
			wsr.setB20(wsList.get(21));
			wsr.setB21(wsList.get(22));
			wsr.setB22(wsList.get(23));
			wsr.setB23(wsList.get(24));
		} catch (SQLException e) {
			System.out.println("ReadSensor(String logger, String name, int manageCal)오류발생");
			e.printStackTrace();
		}
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return wsr;
	}
}
