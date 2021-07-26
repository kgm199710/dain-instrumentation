package com.dain.instrumentation.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dain.instrumentation.util.DBConnection;

public class dummyService {
	static Connection conn;
	static Statement stmt;
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	public static String readDummy(String tb) {
		conn = DBConnection.getConn();
		String httmlDataRow = "";
		//테이블이 dummy_data라 뒤에 data를 붙임
		String SqlReadTable = "select * from " + tb +"_data";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SqlReadTable);
			while(rs.next()) {
				httmlDataRow += "\r\n"
						+ "                    <tr>\r\n"
						+ "                      <td>"+rs.getInt("u_idx")+"</td>\r\n"
						+ "                      <td>"+rs.getString("dtime")+"</td>\r\n"
						+ "                      <td>"+rs.getString("place")+"</td>\r\n"
						+ "                      <td>"+rs.getString("SG_1")+"</td>\r\n"
						+ "                      <td>"+rs.getString("CR_1")+"</td>\r\n"
						+ "                      <td>"+rs.getString("EL_1_X")+"</td>\r\n"
						+ "                      <td>"+rs.getString("EL_1_Y")+"</td>\r\n"
						+ "                    </tr>";
			} 
			rs.close();
			stmt.close();
			System.out.println("read성공");
		} catch (SQLException e) {
			System.out.println("read 오류");
			e.printStackTrace();
		}
		
		DBConnection.terminateDB();
		return httmlDataRow;
	}
	public static String readDummySenser(String loggerName) {
		conn = DBConnection.getConn();
		String httmlDataRow = "";
		//테이블이 dummy_data라 뒤에 data를 붙임
		String SqlReadTable = "select * from sensers"; // + " where logger_name = " + loggerName;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SqlReadTable);
			while(rs.next()) {
				httmlDataRow = httmlDataRow + "<tr>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("idx")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("logger_name")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("sen_name")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("sen_info")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("sen_type")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("sen_order")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("sen_group")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("sen_group_order")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("main_view_enable")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("sensor_init")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("calculation1")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("calculation2")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("calculation3")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("calculation4")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("calculation5")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("calculation6")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("chk_sms")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("chk_cal_1")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("chk_cal_2")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("chk_cal_3")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("chk_cal_4")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("chk_cal_5")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("chk_cal_6")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("safe_value")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("data_count")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("manage_cal_index")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("safe_1")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("safe_2")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getInt("safe_3")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("sen_enddatetime")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("sen_startdatetime")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("etc1")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("etc2")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("etc3")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("sen_data_type")+"</td>\r\n"
						+ "                                                      <td nowrap>"+rs.getString("label_list")+"</td>\r\n"
						+ "                                                  </tr>\r\n"
						+ "                                              <tr>";
			} 
			rs.close();
			stmt.close();
			System.out.println("read성공");
		} catch (SQLException e) {
			System.out.println("read 오류");
			e.printStackTrace();
		}
		
		DBConnection.terminateDB();
		return httmlDataRow;
	}
}
