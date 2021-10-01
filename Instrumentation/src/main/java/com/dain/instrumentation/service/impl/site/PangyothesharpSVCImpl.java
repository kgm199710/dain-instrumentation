package com.dain.instrumentation.service.impl.site;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dain.instrumentation.model.dao.impl.getData.getDataDAO;
import com.dain.instrumentation.model.dao.inf.getData.IGetDataDAO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.service.inf.ISiteSVC;

@Service
public class PangyothesharpSVCImpl implements ISiteSVC {
	IGetDataDAO gdDao;
	
	@Override
	public String makeSystemHtml(List<SystemVO> syList, String[] systemName) {
		String result = "";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String now = year + "" + month + "" + day + "" + hour + "0000";
		System.out.println(now);
		String basic = "<td>";
		String errorColor = "<td bgcolor='#fcf8e3'>";
		for (int i = 0; i < syList.size(); i++) {
			SystemVO sy = syList.get(i);
			boolean timeCheck = false;
			String color = "<td>";
			if(timeCheck) {
				color = "<td bgcolor='#fcf8e3'>";
			}
			result = 
			"											<tr>\r\n"
			+ "												" + color + "<a href='./data/data_total?id=순서값&start=시작날짜&end=끝날짜' role='button'><strong>" + systemName[1] + "</strong></a></td>\r\n"
			+ "												" + color + sy.getInit() + "</td>\r\n"
			+ "												" + color + sy.getLast() + "</td>\r\n"
			+ "												" + color + "</td>\r\n"
			+ "											</tr>\r\n";
		}
		for (SystemVO sy : syList) {
			boolean timeCheck = false;
			String color = "<td>";
			if(timeCheck) {
				color = "<td bgcolor='#fcf8e3'>";
			}
			result = 
			"											<tr>\r\n"
			+ "												" + color + "<a href='./data/data_total?id=순서값&start=시작날짜&end=끝날짜' role='button'><strong>" + systemName[1] + "</strong></a></td>\r\n"
			+ "												" + color + sy.getInit() + "</td>\r\n"
			+ "												" + color + sy.getLast() + "</td>\r\n"
			+ "												" + color + "</td>\r\n"
			+ "											</tr>\r\n";
		}
		
		return result;
	}
	
	@Override
	public List<String> getTableNames(String pageName) {
		gdDao = new getDataDAO();
		List<String> lgList = gdDao.ReadTableNames(pageName);
		for (String lg : lgList) {
			System.out.println(lg);
		}
		return lgList;
	}
	
	@Override
	public List<SystemVO> getSystemInitAndLast(List<String> lgList) {
		gdDao = new getDataDAO();
		ArrayList<SystemVO> syList = new ArrayList<SystemVO>();
		
		for (String logger : lgList) {
			String init = gdDao.readSystemInit(logger);
			String last = gdDao.readSystemLast(logger);
			SystemVO sy = new SystemVO(logger, init, last);
			System.out.println(sy);
			syList.add(sy);
		}
		
		return syList;
	}
}
