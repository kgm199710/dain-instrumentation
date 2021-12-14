package com.dain.instrumentation.model.dao.impl.getData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dain.instrumentation.model.dao.inf.getData.IGetDataDAO;
import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.IPIVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenVO;
import com.dain.instrumentation.model.vo.pangyothesharp.pangyothesharp1VO;
import com.dain.instrumentation.util.DBConnection2;


public class getDataDAO implements IGetDataDAO {

	final String SQL_READ_LOGGERNAMES = "SELECT logger_name FROM logger WHERE logger_name LIKE ?";
	final String SQL_READ_SYSTEM = "SELECT dyear, dmonth, dday, dtime FROM ";
	final String SQL_READ_SYSTEM_INIT = " ORDER BY t_date asc LIMIT 1";
	final String SQL_READ_SYSTEM_LAST = " ORDER BY t_date desc LIMIT 1";
	//SELECT * FROM place_set WHERE logger_name LIKE '?(+%)' and sen_type = "Water Level Sensor" ORDER BY sen_name asc;
	final String SQL_READ_PLACESET = "SELECT * FROM place_set WHERE logger_name LIKE ?";
	final String SQL_READ_SENSORS_1 = "SELECT t_date, ";
	final String SQL_READ_SENSORS_2 = "_1 FROM ";
	final String SQL_READ_T_DATE_ASC = " ORDER BY t_date ASC LIMIT 1";
	final String SQL_READ_T_DATE_DESC = " ORDER BY t_date DESC LIMIT 24";

	final String SQL_READ_SELECT = "SELECT ";
	final String SQL_READ_FROM = " FROM ";
	final String SQL_READ_ORDERBY = " ORDER BY ";
	final String SQL_READ_LIMIT_1 = " LIMIT 1"; 
	
	final String SQL_READ_ONE_SYSTEM_DATA = " where t_date BETWEEN ? AND ? ORDER BY t_date desc";
	
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

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
			String sql = SQL_READ_SENSORS_1 + name + SQL_READ_SENSORS_2 + logger + SQL_READ_T_DATE_ASC;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WaterSenVO ws = new WaterSenVO(name, rs.getString(1), rs.getString(2).split("\\|")[manageCal-1]);
				wsList.add(ws);
			}
			sql = SQL_READ_SENSORS_1 + name + SQL_READ_SENSORS_2 + logger + SQL_READ_T_DATE_DESC;
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
	
	@Override
	public List<IPIVO> ReadIpiData(String[] ipiName, int[] depth, String logger, String order, int manageCalIndex) {
		List<IPIVO> ipiList = new ArrayList<IPIVO>();
		conn = DBConnection2.getConn();
		String ipiNames = "";
		for (int i = 0; i < ipiName.length; i++) {
			ipiNames += ipiName[i] + ((i==ipiName.length-1)?"_1 ":"_1, ");
		}
		String sql = SQL_READ_SELECT + ipiNames + SQL_READ_FROM + logger + SQL_READ_ORDERBY + order + SQL_READ_LIMIT_1;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int num = 0;
			while (rs.next()) {
				for (int i = 0; i < ipiName.length; i++) {
					String strLevel = rs.getString(i+1).split("\\|")[manageCalIndex-1];
					float level = Float.parseFloat(strLevel);
					IPIVO ipi = new IPIVO(ipiName[num], level, depth[num]);
					ipiList.add(ipi);
					num++;
				}
			}
		} catch (SQLException e) {
			System.out.println("ReadIpiData(String[] ipiName, int[] depth, String logger, String order, int manageCalIndex) 오류");
			e.printStackTrace();
		}

		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return ipiList;
	}
	
	
	@Override
	public List<String> ReadColumns(String logger) {
		List<String> result = new ArrayList<String>();
		conn = DBConnection2.getConn();
		String sql = SQL_READ_SELECT + "* " + SQL_READ_FROM + logger;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCnt = rsmd.getColumnCount();
			if(rs.next()) {
				for (int i = 1; i <=colCnt; i++) {
					result.add(rsmd.getColumnName(i));
//					System.out.println(rsmd.getColumnName(i));
				}
			}
		} catch (SQLException e) {
			System.out.println("ReadColumns(String logger) 오류");
			e.printStackTrace();
		}

		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public String[] ReadSystemFirst(String logger, String[] columns, Map<String, Integer> manageCalIndex) {
		String[] result = new String[columns.length];
		conn = DBConnection2.getConn();
		String columnStr = "t_date";
		for (int i = 1; i < columns.length; i++) {
			columnStr = columnStr + "," + columns[i] + "_1";
		}
		String sql = SQL_READ_SELECT + columnStr + " " + SQL_READ_FROM + logger + SQL_READ_T_DATE_ASC;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				for (int i = 0; i < columns.length; i++) {
					if(i==0) {
						result[i] = rs.getString(i+1);
					} else {
						String[] data = rs.getString(i+1).split("[|]");
						int num = manageCalIndex.get(columns[i])-1;
						result[i] = data[num];
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("ReadSystemFirst(String logger, String[] columns) 오류");
			e.printStackTrace();
		}
		
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public List<String[]> ReadSysteamDatas(String logger, String[] columns, Map<String, Integer> manageCalIndex, String start, String end) {
		List<String[]> result = new ArrayList<String[]>();
		conn = DBConnection2.getConn();
		String columnStr = "t_date";
		for (int i = 1; i < columns.length; i++) {
			columnStr = columnStr + "," + columns[i] + "_1";
		}
		String sql = SQL_READ_SELECT + columnStr + " " + SQL_READ_FROM + logger + SQL_READ_ONE_SYSTEM_DATA;
//		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String[] oneData = new String[columns.length];
				for (int i = 0; i < columns.length; i++) {
					if(i==0) {
						oneData[i] = rs.getString(i+1);
					} else {
						String[] data = rs.getString(i+1).split("[|]");
						int num = manageCalIndex.get(columns[i])-1;
						oneData[i] = data[num];
					}
				}
				result.add(oneData);
			}
		} catch (SQLException e) {
			System.out.println("ReadSysteamDatas(String logger, String[] columns, Map<String, Integer> manageCalIndex, String[] first, String start, String end) 오류");
			e.printStackTrace();
		}
		

		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public String readWlFirst(PlaceSetVO senInfo) {
		String result = "";
		conn = DBConnection2.getConn();
		String sql = SQL_READ_SELECT + senInfo.getSen_name() + SQL_READ_SENSORS_2 + senInfo.getLogger_name() + SQL_READ_T_DATE_ASC;
//		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1) + " / " + senInfo.getManage_cal_index());
				String[] data = rs.getString(1).split("[|]");
				result = data[senInfo.getManage_cal_index()-1];
			}
		} catch (SQLException e) {
			System.out.println("readWlFirst(PlaceSetVO senInfo) 실패");
			e.printStackTrace();
		}
		
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public List<String[]> ReadWlDatas(PlaceSetVO senInfo, String start, String end, String[] outputColumns) {
		List<String[]> result = new ArrayList<String[]>();
		String[] datas;
		conn = DBConnection2.getConn();
		String sql = SQL_READ_SENSORS_1 + senInfo.getSen_name() + SQL_READ_SENSORS_2 + senInfo.getLogger_name() + SQL_READ_ONE_SYSTEM_DATA;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				datas = new String[outputColumns.length];
				datas[0] = rs.getString(1);
				String[] data = rs.getString(2).split("[|]");
				datas[1] = data[0];
				datas[2] = data[senInfo.getManage_cal_index()-1];
				datas[3] = data[senInfo.getManage_cal_index()-1];
//				for (int i = 1; i < datas.length; i++) {
//					if(i==outputColumns.length-3) {
//						//{"측정시간","측정값(mV)","각도(degree)","변화량(mm)","비고"}
//						datas[i] = data[senInfo.getManage_cal_index()-1];
//						datas[i+1] = data[senInfo.getManage_cal_index()-1];
//					} else {
//					}	 
//				}
				result.add(datas);
			}
		} catch (SQLException e) {
			System.out.println("ReadWlDatas(PlaceSetVO senInfo, String start, String end, String first, String[] outputColumns) 실패");
			e.printStackTrace();
		}
		
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public String[] ReadIpiFirst(String logger, String[] columns, Map<String, Integer> manageCalIndex) {
		String[] result = new String[columns.length+1];
		conn = DBConnection2.getConn();
		String columnStr = "t_date";
		for (int i = 0; i < columns.length; i++) {
			columnStr = columnStr + "," + columns[i] + "_1";
		}
		String sql = SQL_READ_SELECT + columnStr + " " + SQL_READ_FROM + logger + SQL_READ_T_DATE_ASC;
//		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				for (int i = 0; i < columns.length+1; i++) {
					if(i==0) {
						result[i] = rs.getString(i+1);
					} else {
						String[] data = rs.getString(i+1).split("[|]");
						int num = manageCalIndex.get(columns[i-1])-1;
						result[i] = data[num];
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("ReadSystemFirst(String logger, String[] columns) 오류");
			e.printStackTrace();
		}
		
		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
	
	@Override
	public List<String[]> ReadIpiDatas(String logger, String[] columns, Map<String, Integer> manageCalIndex, String start, String end) {
		List<String[]> result = new ArrayList<String[]>();
		conn = DBConnection2.getConn();
		String columnStr = "t_date";
		for (int i = 0; i < columns.length; i++) {
			columnStr = columnStr + "," + columns[i] + "_1";
		}
		String sql = SQL_READ_SELECT + columnStr + " " + SQL_READ_FROM + logger + SQL_READ_ONE_SYSTEM_DATA;
//		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String[] oneData = new String[columns.length+1];
				for (int i = 0; i < columns.length+1; i++) {
					if(i==0) {
						oneData[i] = rs.getString(i+1);
					} else {
						String[] data = rs.getString(i+1).split("[|]");
						int num = manageCalIndex.get(columns[i-1])-1;
						oneData[i] = data[num];
					}
				}
				result.add(oneData);
			}
		} catch (SQLException e) {
			System.out.println("ReadSysteamDatas(String logger, String[] columns, Map<String, Integer> manageCalIndex, String[] first, String start, String end) 오류");
			e.printStackTrace();
		}
		

		DBConnection2.releasePstmt(pstmt);
		DBConnection2.releaseRS(rs);
		DBConnection2.terminateDB();
		return result;
	}
}
