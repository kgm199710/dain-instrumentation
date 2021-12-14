package com.dain.instrumentation.service.impl.site;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dain.instrumentation.model.dao.impl.getData.getDataDAO;
import com.dain.instrumentation.model.dao.inf.getData.IGetDataDAO;
import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.IPIVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenVO;
import com.dain.instrumentation.service.inf.ISiteSVC;
import com.dain.instrumentation.util.TimeSetting;

@Service
public class PangyothesharpSVCImpl implements ISiteSVC {
	

	final SimpleDateFormat beforeSDF = new SimpleDateFormat("yyyyMMddHHmmss");
	final SimpleDateFormat newSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	IGetDataDAO gdDao;
	
	
	@Override
	public List<String> getTableNames(String pageName) {
		gdDao = new getDataDAO();
		List<String> lgList = gdDao.ReadTableNames(pageName);
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
			String end = TimeSetting.SetEnd();
			String start = TimeSetting.SetStart();
			String color = "<td>";
			if(timeCheck) {
				color = "<td bgcolor='#fcf8e3'>";
			}
			result += 
			"											<tr>\r\n"
			+ "												" + color + "<a href='pangyothesharp/data_total?id="+i+"&start="+start+"&end="+end+"' role='button'><strong>" + systemName[i] + "</strong></a></td>\r\n"
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
		return waterTableHtml;
	}
	
	@Override
	public List<IPIVO> getIPIGraphData(List<PlaceSetVO> psList, int[] depth, String[] ipiName, float[] existing, String logger) {
		List<IPIVO> ipiList = new ArrayList<IPIVO>();
		List<IPIVO> ipiInitList = new ArrayList<IPIVO>();
		List<IPIVO> ipiNowList = new ArrayList<IPIVO>();
		int manageCalIndex = 3; // 임의 지정
		ipiInitList = gdDao.ReadIpiData(ipiName, depth, logger, "t_date ASC", manageCalIndex);
		ipiNowList = gdDao.ReadIpiData(ipiName, depth, logger, "t_date DESC", manageCalIndex);
		for (int i = 0; i < ipiInitList.size(); i++) {
			IPIVO ipi = ipiInitList.get(i);
			ipi.setLevel(ipiNowList.get(i).getLevel() - ipiInitList.get(i).getLevel());
			ipiList.add(ipi);
		}
		for (int i = ipiList.size()-1; i >= 0; i--) {
			if(i!=ipiList.size()-1) {
				IPIVO ipi = ipiList.get(i);
				IPIVO ipiBefore = ipiList.get(i+1);
				ipi.setLevel(ipi.getLevel()+ipiBefore.getLevel());
				ipiList.set(i, ipi);
			} else {
				ipiList.get(i).setLevel(ipiList.get(i).getLevel());
			}
		}
		
		//기변위(existing 적용)
		for (int i = 0; i < ipiList.size(); i++) {
			IPIVO ipi = ipiList.get(i);
			ipi.setLevel(ipi.getLevel()+existing[i]);
			ipiList.set(i, ipi);
		}
		
		return ipiList;
	}
	
	@Override
	public IPIVO getIPIMax(List<IPIVO> ipiList) {
		IPIVO ipiMax = ipiList.get(0);
		for (int i = 0; i < ipiList.size(); i++) {
			if (Math.abs(ipiMax.getLevel())<Math.abs(ipiList.get(i).getLevel())) 
				ipiMax = ipiList.get(i);
		}
		return ipiMax;
	}
	
	@Override
	public String[][] getSystem(String logger, List<PlaceSetVO> psList, String start, String end) {
		String[][] result;
		start = TimeSetting.MakeSearchTime(start, true);
		end = TimeSetting.MakeSearchTime(end, false);
		//현재 로거 psList
		List<PlaceSetVO> usePsList = new ArrayList<PlaceSetVO>();
//		System.out.println(logger);
		for (PlaceSetVO ps : psList) {
			if(ps.getLogger_name().equals(logger)) {
				usePsList.add(ps);
			}
		}
		
		//센서별 manageCalIndex
		Map<String, Integer> manageCalIndex = new HashMap<String, Integer>();
		for (PlaceSetVO ps : usePsList) {
			manageCalIndex.put(ps.getSen_name(), ps.getManage_cal_index());
		}
		
		//컬럼명들
		List<String> columnList = new ArrayList<String>();
		columnList = gdDao.ReadColumns(logger);
		String[] columns = new String[usePsList.size()];
		for (int i = 0; i < usePsList.size(); i++) {
			boolean check = true;
			int a=0;
			while(check) {
				if(usePsList.get(i).getSen_name().equals(columnList.get(a))) {
					columns[usePsList.get(i).getSen_order()-1] = columnList.get(a);
					check=false;
				} else {
					a++;
				}
			}			
		}
		String checkCountWord = "count";
		//count체크(cr1000인지 아닌지)
		boolean checkCount = columns[0].equals(checkCountWord);
		if(checkCount) {
			columns[0]= "측정시간";
		}
		
		//기준점(최초값)
		String[] first = new String[columns.length];
		first = gdDao.ReadSystemFirst(logger, columns, manageCalIndex);
		double[] firstFloat = new double[first.length-1];
		for (int i = 1; i < first.length; i++) {
			firstFloat[i-1] = Double.parseDouble(first[i]);
		}
			//컬럼명들 배열에 순서대로 담기 성공
			//초기값을 배열순으로 출력 성공
			
		
			//이후 컬럼리스트,manageCalIndex,시작일,마지막일을 DAO로 넘겨 거기서 데이터를 받음
		List<String[]> data = new ArrayList<String[]>();
		data = gdDao.ReadSysteamDatas(logger, columns, manageCalIndex, start, end);
			//나온 데이터리스트를 순서대로 변화량을 구한뒤 리스트<배열>화
		result = new String[data.size()+1][columns.length];
		result[0] = columns;
//		result.add(columns);
		for (int i = 0; i < data.size(); i++) {
			String[] oneData = new String[columns.length];
			for (int j = 0; j < data.get(i).length; j++) {
				if(j==0) {
					String settingTime = "";
					try {
						Date dateData = beforeSDF.parse(data.get(i)[j]);
						settingTime = newSDF.format(dateData);
					} catch (ParseException e) {
						System.out.println("시간형변형 오류");
						e.printStackTrace();
					}
					oneData[j] = settingTime;
				} else {
					double b = firstFloat[j-1];
					double a = Double.parseDouble(data.get(i)[j]);
					oneData[j] = String.format("%.3f", (a-b));
//					if(i==0)
//					System.out.println(j + "/" + a + "/" + b + "/" + String.format("%.3f", (a-b))); 
				}
			}
			result[i+1] = oneData;
		}
		
			//정리된 리스트를 controller단으로 보낸뒤 그대로 프론트엔드로 전송
		
		return result; 
	}
	
	@Override
	public String[][] getWL(String select_id, List<PlaceSetVO> psList, String start, String end, String[] outputColumns) {
		String[][] result;
		start = TimeSetting.MakeSearchTime(start, true);
		end = TimeSetting.MakeSearchTime(end, false);

		//현재 로거 psList
		PlaceSetVO senInfo = new PlaceSetVO();
		for (PlaceSetVO ps : psList) {
			if(ps.getSen_name().equals(select_id)) {
				senInfo = ps;
			}
		}
		
		//  outputColumns {"측정시간","측정값(mV)","각도(degree)","변화량(mm)","비고"}
		//초기치
		String first = "";
		first = gdDao.readWlFirst(senInfo);
		
		List<String[]> dataList = new ArrayList<String[]>();
		dataList = gdDao.ReadWlDatas(senInfo, start, end, outputColumns);

		String[] data = new String[outputColumns.length];
		result = new String[dataList.size()+1][outputColumns.length];
		result[0] = outputColumns;
		for (int i = 1; i < result.length; i++) {
			data = dataList.get(i-1);
			try {
				Date beForeDate = beforeSDF.parse(data[0]);
				data[0] = newSDF.format(beForeDate);
			} catch (ParseException e) {
				System.out.println("시간형변형 오류");
				e.printStackTrace();
			}
			data[1] = String.format("%.2f", Double.parseDouble(data[1]));
			double b = Double.parseDouble(first);
			double a = Double.parseDouble(data[3]);
			data[2] = String.format("%.3f", (a));
			data[3] = String.format("%.3f", (a-b));
			result[i] = data;
		}
		
		return result;
	}
	
//	@Override
//	public String[][] getIPIGraphDatas(String[][] ipiName, int[][] depth, List<PlaceSetVO> psList, String selectIpi, String end, int viewGraphNum) {
//		String[][] result;
//		end = TimeSetting.MakeSearchTime(end, false);
//		end = end.substring(0,end.length()-6)+"120000";
//		System.out.println(end.substring(end.length()-6));
//		return null;
//	}
	
	@Override
	public String[][] getIPITotal(String logger, List<PlaceSetVO> psList, String start, String end, String[] columns, int[] depth) {
		String[][] result;
		start = TimeSetting.MakeSearchTime(start, true);
		end = TimeSetting.MakeSearchTime(end, false);
		//현재 로거 psList
		List<PlaceSetVO> usePsList = new ArrayList<PlaceSetVO>();
//		System.out.println(logger);
		for (PlaceSetVO ps : psList) {
			if(ps.getLogger_name().equals(logger)) {
				usePsList.add(ps);
			}
		}
		
		//센서별 manageCalIndex
		Map<String, Integer> manageCalIndex = new HashMap<String, Integer>();
		for (PlaceSetVO ps : usePsList) {
			manageCalIndex.put(ps.getSen_name(), ps.getManage_cal_index());
		}
		
		//기준점(최초값)
		String[] first = new String[columns.length+1];
		first = gdDao.ReadIpiFirst(logger, columns, manageCalIndex);
		double[] firstFloat = new double[first.length-1];
		for (int i = 1; i < first.length; i++) {
			firstFloat[i-1] = Double.parseDouble(first[i]);
		}
		
		// 조건들을 이용하여 데이터 검색
		List<String[]> data = new ArrayList<String[]>();
		data = gdDao.ReadIpiDatas(logger, columns, manageCalIndex, start, end);
		
		//가져온 데이타 정리
		String[] useIpiName = new String[columns.length+1];
		for (int i = 0; i < columns.length+1; i++) {
			if(i==columns.length) {
				useIpiName[i] = "<br>("+depth[i]+"m)";
			} else
				useIpiName[i] = columns[i]+"<br>("+depth[i]+"m)";
		}
		result = new String[data.size()+1][useIpiName.length+1];
		result[0][0] = "측정시간";
		for (int i = 1; i < result[0].length; i++) {
			result[0][i] = useIpiName[i-1];
		}
		for (int i = 0; i < data.size(); i++) {
			String[] oneData = new String[useIpiName.length+1];
			for (int j = 0; j < data.get(i).length+1; j++) {
				if(j==0) {
					String settingTime = "";
					try {
						Date dateData = beforeSDF.parse(data.get(i)[j]);
						settingTime = newSDF.format(dateData);
					} catch (ParseException e) {
						System.out.println("시간형변형 오류");
						e.printStackTrace();
					}
					oneData[j] = settingTime;
				} else if(j==data.get(i).length) {
					oneData[j] = "0";
				} else {
					double b = firstFloat[j-1];
					double a = Double.parseDouble(data.get(i)[j]);
					oneData[j] = String.format("%.3f", (a-b));
				}
			}
			result[i+1] = oneData;
		}
		return result;
	}
}
