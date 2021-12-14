package com.dain.instrumentation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeSetting {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//현재시간
	public static String SetEnd() {
		String end = "";
		Date now = new Date();
		end = sdf.format(now);
		return end;
	}
	
	//1주일전 시간
	public static String SetStart() {
		String start = "";
		Calendar cal = Calendar.getInstance();
		cal.add(cal.DATE, -6);
		Date before = cal.getTime();
		start = sdf.format(before);
		
		return start;
	}
	
	//형태 변환 체크
	public static boolean checkTimeForm(String time) {
		boolean result = true;
		try {
			System.out.println(sdf.parse(time));
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	//검색용 시작날자 변환
	public static String MakeSearchTime(String time, boolean checkSE) {
		String result = "";
		String [] times = time.split("-");
		// 가져가서 사용
		if(times.length != 3 || times[0].length() != 4 || times[1].length() !=2 || times[2].length() !=2 ) {
			time = SetStart();
			times = time.split("-");
		} 
		//checkSE=시작날짜or끝날짜
		if(checkSE) {
			result = times[0] + times[1] + times[2] + "000000";
		} else {
			result = times[0] + times[1] + times[2] + "235959";
		}
		
		return result;
	}
	
	//테스트용
	public static void main(String[] args) {
		System.out.println("end = " + SetEnd());
		System.out.println("start = " + SetStart());
		String test = "2021-11-12";
		System.out.println("2021-11-12 = " + checkTimeForm(test));
		test = "11";
		System.out.println("2021-13-32 = " + checkTimeForm(test));
		System.out.println(MakeSearchTime(SetStart(),true));
		System.out.println(MakeSearchTime("2015-51-51",false));
	}
	
}
