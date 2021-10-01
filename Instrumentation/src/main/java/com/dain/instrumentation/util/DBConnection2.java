package com.dain.instrumentation.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class DBConnection2 {
	public static final String DB_DRIVER 
		= "org.mariadb.jdbc.Driver";
	public static final String URL 
		= "jdbc:mariadb://database.cluster-cgi08asuocmh.ap-northeast-2.rds.amazonaws.com";	
	public static final String PORT = "3306";
	public static final String DBNAME = "g2inet_daintech21";
	public static final String LOGIN = "dain";
	public static final String PW = "g2inet2580";

	public static Connection conn;
//	public static Statement stmt;
//	public static PreparedStatement pstmt;
//	public static ResultSet rs;

	public static Connection getConn() {
		if( conn != null ) return conn;
		else // null
			return connetDB();
	}
	
	public static Connection connetDB() {
		try {
			Class.forName(DB_DRIVER);
			String dbURL = URL+ ":" + PORT + "/" + DBNAME;
			conn = DriverManager.getConnection(dbURL, LOGIN, PW);
			System.out.println("MySQL " + DBNAME + " 접속 성공!");
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("접속 실패 1 - 클래스 오류");
			e.printStackTrace();
		} catch (SQLTimeoutException e) {
			System.out.println("접속 실패 2 - 시간만료");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("접속 실패 3 - sql문 오류?");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("일반 자바 실패들.. ");
			e.printStackTrace();
		}
		return null;
	}

	public static boolean terminateDB() {		
		if( conn != null ) {
			try {
				conn.close();
				System.out.println("DB 접속해제 성공!");
				conn = null;
				return true;
			} catch (SQLException e) {
				System.out.println("DB 접속해제 실패..");
				e.printStackTrace();				
			}
		}
		return false;
	}
	
	public static boolean releaseRS(ResultSet rs) {		
		if( conn != null && rs != null ) {
			try {				
				rs.close();
				System.out.println("RS 해제 성공!");
				return true;
			} catch (SQLException e) {
				System.out.println("RS 해제 실패..");
				e.printStackTrace();				
			}
		}
		return false;
	}
	
	public static boolean releaseStmt(Statement stmt) {		
		if( conn != null && stmt != null ) {
			try {				
				stmt.close();
				System.out.println("stmt 해제 성공!");
				return true;
			} catch (SQLException e) {
				System.out.println("stmt 해제 실패..");
				e.printStackTrace();				
			}
		}
		return false;
	}	
	
	public static boolean releasePstmt(
			PreparedStatement pstmt) {		
		if( conn != null && pstmt != null ) {
			try {				
				pstmt.close();
				System.out.println("pstmt 해제 성공!");
				return true;
			} catch (SQLException e) {
				System.out.println("pstmt 해제 실패..");
				e.printStackTrace();				
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		DBConnection2.getConn();
		DBConnection2.terminateDB();
	}
}