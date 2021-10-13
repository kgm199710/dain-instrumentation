package com.dain.instrumentation.service.impl.site;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dain.instrumentation.model.dao.impl.getData.getDataDAO;
import com.dain.instrumentation.model.dao.inf.getData.IGetDataDAO;
import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenVO;
import com.dain.instrumentation.service.inf.ISiteSVC;

@Service
public class PangyothesharpSVCImpl implements ISiteSVC {
	IGetDataDAO gdDao;
	
	
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
			String[] init = gdDao.readSystemInit(logger);
			String[] last = gdDao.readSystemLast(logger);
			SystemVO sy = new SystemVO();
			sy.setName(logger);
			sy.setInitDyear(init[0]);
			sy.setInitDmonth(init[1]);
			sy.setInitDday(init[2]);
			sy.setLastDyear(last[0]);
			sy.setLastDmonth(last[1]);
			sy.setLastDday(last[2]);
			sy.setLastDtime(last[3]);
			syList.add(sy);
		}
		
		return syList;
	}

	@Override
	public String makeSystemHtml(List<SystemVO> syList, String[] systemName) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date nowDate = new Date();
		Calendar cal = Calendar.getInstance();
//		String now = year + "" + month + "" + day + "" + hour + "0000";
//		System.out.println(now);
		String basic = "<td>";
		String errorColor = "<td bgcolor='#fcf8e3'>";
		for (int i = 0; i < syList.size(); i++) {
			SystemVO sy = syList.get(i);
			boolean timeCheck = false;
			String init = sy.getInitDyear()+"-"+sy.getInitDmonth()+"-"+sy.getInitDday();
			String last = sy.getLastDyear()+"-"+sy.getLastDmonth()+"-"+sy.getLastDday()+" "+sy.getLastDtime().substring(0,5) ;
			try {
				Date lastDate = sdf.parse(last);
				long calDate = nowDate.getTime() - lastDate.getTime();
				long calDateHour = calDate / (60*60*1000);
				timeCheck = calDateHour>2;
				//값확인
//				System.out.println("calDate = " + calDate + " / calDateHour = " + calDateHour);
			} catch (ParseException e) {
				System.out.println("날짜 오류");
				e.printStackTrace();
			}
			String color = "<td>";
			if(timeCheck) {
				color = "<td bgcolor='#fcf8e3'>";
			}
			result += 
			"											<tr>\r\n"
			+ "												" + color + "<a href='./data/data_total?id=순서값&start=시작날짜&end=끝날짜' role='button'><strong>" + systemName[i] + "</strong></a></td>\r\n"
			+ "												" + color + init + "</td>\r\n"
			+ "												" + color + last+ "</td>\r\n"
			+ "												" + color + "</td>\r\n"
			+ "											</tr>\r\n";
		}
		
		return result;
	}
	
	@Override
	public List<PlaceSetVO> getPlaceSet(String pageName) {
		List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
		gdDao = new getDataDAO();
		psList = gdDao.ReadPlaceSet(pageName);
		return psList;
	}
	
	@Override
	public List<WaterSenResultVO> getWaterSensers(List<PlaceSetVO> psList) {
		List<WaterSenResultVO> wsrList = new ArrayList<WaterSenResultVO>();
		gdDao = new getDataDAO();
		for (PlaceSetVO ps : psList) {
			String type = ps.getSen_type();
//			WaterSenVO ws = new WaterSenVO();	//특정시간의 수위계
			if(type.equals("Water Level Sensor")) {
				String logger = ps.getLogger_name();
				String name = ps.getSen_name();
				int manageCal = ps.getManage_cal_index();
				WaterSenResultVO wsr = new WaterSenResultVO();	//필요한 모든 수위계
				wsr = gdDao.ReadSensor(logger,name,manageCal);
				wsrList.add(wsr);
			}
		}
		return wsrList;
	}
	
	@Override
	public String makeWaterSenHtml(List<WaterSenResultVO> wsrList) {
		String waterSenHtml =
				"										<thead>\r\n"
				+ "											<tr bgcolor='#eeeeee'>\r\n"
				+ "												<th class='text-center'>구분</th>\r\n";
		for (WaterSenResultVO wsr : wsrList) {
			waterSenHtml += 
			"												<th class='text-center'><a href='#WL'>" + wsr.getName() + "</a></th>\r\n";
		}
		waterSenHtml +=
				"											</tr>\r\n"
				+ "										</thead>\r\n"
				+ "										<tbody>\r\n"
				+ "											<tr>\r\n"
				+ "												<td class='text-center' bgcolor='#eeeeee'>지하수위</td>\r\n";
		for (WaterSenResultVO wsr : wsrList) {
			float nowLevel = Float.parseFloat(wsr.getNow().getLevel());
			waterSenHtml += "												<td class='px-0'>" + String.format("%.2f", nowLevel)  + " m</td>\r\n" ;
		}
		waterSenHtml +=
				"											</tr>\r\n"
				+ "											<tr>\r\n"
				+ "												<td class='text-center' bgcolor='#eeeeee'>변화량</td>\r\n";
		for (WaterSenResultVO wsr : wsrList) {
			float nowLevel = Float.parseFloat(wsr.getNow().getLevel());
			float initLevel = Float.parseFloat(wsr.getInit().getLevel());
			waterSenHtml += "												<td class='px-0'>" + String.format("%.2f", (nowLevel - initLevel)) + " m</td>\r\n";
		}
		waterSenHtml +=
				"											</tr>\r\n"
				+ "											<tr>\r\n"
				+ "												<td class='text-center' bgcolor='#eeeeee'>일변화량</td>\r\n";
		for (WaterSenResultVO wsr : wsrList) {
			float nowLevel = Float.parseFloat(wsr.getNow().getLevel());
			float b23Level = Float.parseFloat(wsr.getB23().getLevel());
			waterSenHtml += "												<td class='px-0'>" + String.format("%.2f", (nowLevel - b23Level)) + " m</td>\r\n";
		}
		waterSenHtml += 
				"											</tr>\r\n"
				+ "										</tbody>";
		return waterSenHtml;
	}
	
	@Override
	public String makeWaterSenJS(List<WaterSenResultVO> wsList) {
		String waterSenJS = "";
		for (int i = 0; i < wsList.size(); i++) {
			String varName = "data_w" + (i + 1);
			WaterSenVO wsB23 = wsList.get(i).getB23();
			float wsB23Limit = Float.parseFloat(wsB23.getLevel());
			WaterSenVO wsNow = wsList.get(i).getNow();
			float wsNowLimit = Float.parseFloat(wsNow.getLevel());
			waterSenJS += 
					"\r\n		var " + varName + "=new google.visualization.DataTable();\r\n"
					+ "		" + varName + ".addColumn('datetime','시간');\r\n"
					+ "		" + varName + ".addColumn('number','" + wsList.get(i).getName() + "');\r\n"
					+ "		" + varName + ".addColumn({type:'string',role:'annotation'});\r\n"
					+ "		" + varName + ".addRows(\r\n"
					+ "		    [\r\n"
					+ "		        [new Date(" + maketime(wsB23.getTime())+ ")," + wsB23.getLevel() + ",'" + String.format("%.2f", wsB23Limit) + "m'], \r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB22().getTime()) + ")," + wsList.get(i).getB22().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB21().getTime()) + ")," + wsList.get(i).getB21().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB20().getTime()) + ")," + wsList.get(i).getB20().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB19().getTime()) + ")," + wsList.get(i).getB19().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB18().getTime()) + ")," + wsList.get(i).getB18().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB17().getTime()) + ")," + wsList.get(i).getB17().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB16().getTime()) + ")," + wsList.get(i).getB16().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB15().getTime()) + ")," + wsList.get(i).getB15().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB14().getTime()) + ")," + wsList.get(i).getB14().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB13().getTime()) + ")," + wsList.get(i).getB13().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB12().getTime()) + ")," + wsList.get(i).getB12().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB11().getTime()) + ")," + wsList.get(i).getB11().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB10().getTime()) + ")," + wsList.get(i).getB10().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB9().getTime()) + ")," + wsList.get(i).getB9().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB8().getTime()) + ")," + wsList.get(i).getB8().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB7().getTime()) + ")," + wsList.get(i).getB7().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB6().getTime()) + ")," + wsList.get(i).getB6().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB5().getTime()) + ")," + wsList.get(i).getB5().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB4().getTime()) + ")," + wsList.get(i).getB4().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB3().getTime()) + ")," + wsList.get(i).getB3().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB2().getTime()) + ")," + wsList.get(i).getB2().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsList.get(i).getB1().getTime()) + ")," + wsList.get(i).getB1().getLevel() + ",null],\r\n"
					+ "		        [new Date(" + maketime(wsNow.getTime()) + ")," + wsNow.getLevel() + ",'" + String.format("%.2f", wsNowLimit) + "m']\r\n"
					+ "		    ]\r\n"
					+ "		);\r\n";
		}
		for (int i = 1; i < wsList.size()+1; i++) {
			waterSenJS += "\r\n"
					+ "		\r\n"
					+ "		var chart_w" + i + "=new google.visualization.LineChart(document.getElementById('chart_" + i + "_w'));\r\n"
					+ "		chart_w" + i +".draw(data_w" + i + ",options_w);";
			
		}
		
		return waterSenJS;
	}
	String maketime(String time) {
		String result = 
				time.substring(0,4) + "," 
				+ time.substring(4,6) + "," 
				+ time.substring(6,8) + "," 
				+ time.substring(8,10) + "," 
				+ time.substring(10,12) + "," 
				+ time.substring(12,14);
		return result;
	}
	
	@Override
	public String makeWaterTableHtml(List<WaterSenResultVO> wsrList) {
		String waterTableHtml = "";
		for (int i = 0; i < wsrList.size(); i++) {
			float nowLevel = Float.parseFloat(wsrList.get(i).getNow().getLevel());
			float initLevel = Float.parseFloat(wsrList.get(i).getInit().getLevel());
			float b23Level = Float.parseFloat(wsrList.get(i).getB23().getLevel());
			waterTableHtml += "\r\n"
					+ "							<div class='col-md-6'>\r\n"
					+ "								<div id = 'chart_" + (i+1) + "_w' style='margin-bottom:10px;padding:1px;height:331px; border: 1px solid #ccc;'></div>\r\n"
					+ "								<div class='table-responsive'>\r\n"
					+ "									<table class='table text-center table-bordered'>\r\n"
					+ "										<thead>\r\n"
					+ "											<tr bgcolor='#eeeeee'>\r\n"
					+ "												<th class='text-center'>식별코드</th>\r\n"
					+ "												<th class='text-center'>수위</th>\r\n"
					+ "												<th class='text-center'>변화량</th>\r\n"
					+ "												<th class='text-center'>24시간변화량</th>\r\n"
					+ "												<th class='text-center'>비고</th>\r\n"
					+ "											</tr>\r\n"
					+ "										</thead>\r\n"
					+ "										<tr>\r\n"
					+ "											<td><a href='data/" + wsrList.get(i).getName() + "/start=?&end=?' role='button' target='_blank'><strong>" + wsrList.get(i).getName() + "</strong></a></td>\r\n"
					+ "											<td>" + String.format("%.2f", nowLevel) +"m</td>\r\n"
					+ "											<td>" + String.format("%.2f", (nowLevel - initLevel)) + "m</td>\r\n"
					+ "											<td>" + String.format("%.2f", (nowLevel - b23Level)) + "m</td>\r\n"
					+ "											<td></td>\r\n"
					+ "										</tr>\r\n"
					+ "									</table>\r\n"
					+ "								</div>\r\n"
					+ "							</div>";
		}
//		System.out.println(waterTableHtml);
		return waterTableHtml;
	}
	
	@Override
	public List<PlaceSetVO> branchIPIPlaceSet(List<PlaceSetVO> psList) {
		List<PlaceSetVO> psIpiList = new ArrayList<PlaceSetVO>();
		for (PlaceSetVO ps : psList) {
			if(ps.getSen_type().equals("IPI")){
				psIpiList.add(ps);
			}
		}
		return psIpiList;
	}
	
	@Override
	public String makeIPISenHtml(List<PlaceSetVO> psIpiList) {
		String ipiSenHtml = "";
		for (int i = 0; i < psIpiList.size(); i++) {
			System.out.println(psIpiList.get(i));
		}
		
		return ipiSenHtml;
	}
	
}
