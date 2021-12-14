package com.dain.instrumentation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dain.instrumentation.model.vo.UserVO;
import com.dain.instrumentation.model.vo.common.PlaceSetVO;
import com.dain.instrumentation.model.vo.pangyothesharp.IPIVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenResultVO;
import com.dain.instrumentation.service.impl.site.PangyothesharpSVCImpl;
import com.dain.instrumentation.service.inf.ISiteSVC;
import com.dain.instrumentation.util.TimeSetting;

@Controller
public class PangyothesharpController {
	
	private static final Logger logger = LoggerFactory.getLogger(PangyothesharpController.class);
	private static ModelAndView mav;
	
	//사전 임시 지정변수들
	String pageName = "pangyothesharp";
	String pageKorName = "판교TheSharp";
	String[] systemName = {"시스템1","시스템2","시스템3","시스템4","시스템5"};
	//자동 html생성 없이 생성하기 위한 지정변수들
	String[] ipiGroupName = {"IPI-2","IPI-3","IPI-7","IPI-11","IPI-13"};
	String[] ipiGroupKorName = {"지중경사계 I-2","지중경사계 I-3","지중경사계 I-7","지중경사계 I-11","지중경사계 I-13"};
	String[][] ipiName = {
			{"IPI_2_18_Y","IPI_2_17_Y","IPI_2_16_Y","IPI_2_15_Y","IPI_2_14_Y","IPI_2_13_Y","IPI_2_12_Y","IPI_2_11_Y","IPI_2_10_Y","IPI_2_09_Y","IPI_2_08_Y","IPI_2_07_Y","IPI_2_06_Y","IPI_2_05_Y","IPI_2_04_Y","IPI_2_03_Y","IPI_2_02_Y","IPI_2_01_Y"},
			{"IPI_3_19_Y","IPI_3_18_Y","IPI_3_17_Y","IPI_3_16_Y","IPI_3_15_Y","IPI_3_14_Y","IPI_3_13_Y","IPI_3_12_Y","IPI_3_11_Y","IPI_3_10_Y","IPI_3_09_Y","IPI_3_08_Y","IPI_3_07_Y","IPI_3_06_Y","IPI_3_05_Y","IPI_3_04_Y","IPI_3_03_Y","IPI_3_02_Y","IPI_3_01_Y"},
			{"IPI_7_20_Y","IPI_7_19_Y","IPI_7_18_Y","IPI_7_17_Y","IPI_7_16_Y","IPI_7_15_Y","IPI_7_14_Y","IPI_7_13_Y","IPI_7_12_Y","IPI_7_11_Y","IPI_7_10_Y","IPI_7_09_Y","IPI_7_08_Y","IPI_7_07_Y","IPI_7_06_Y","IPI_7_05_Y","IPI_7_04_Y","IPI_7_03_Y","IPI_7_02_Y","IPI_7_01_Y"},
			{"IPI_11_16_Y","IPI_11_15_Y","IPI_11_14_Y","IPI_11_13_Y","IPI_11_12_Y","IPI_11_11_Y","IPI_11_10_Y","IPI_11_09_Y","IPI_11_08_Y","IPI_11_07_Y","IPI_11_06_Y","IPI_11_05_Y","IPI_11_04_Y","IPI_11_03_Y","IPI_11_02_Y","IPI_11_01_Y"},
			{"IPI_13_17_Y","IPI_13_16_Y","IPI_13_15_Y","IPI_13_14_Y","IPI_13_13_Y","IPI_13_12_Y","IPI_13_11_Y","IPI_13_10_Y","IPI_13_09_Y","IPI_13_08_Y","IPI_13_07_Y","IPI_13_06_Y","IPI_13_05_Y","IPI_13_04_Y","IPI_13_03_Y","IPI_13_02_Y","IPI_13_01_Y"}
	};
	float[][] existing = {
			{0.45f,0.48f,0.17f,-0.28f,-0.16f,0.13f,0.26f,0.15f,-0.68f,0.58f,0.35f,0.11f,0.35f,0.05f,-0.44f,-0.28f,0.00f,-0.13f},
			{0.14f,0.68f,0.57f,0.57f,0.27f,0.39f,0.31f,0.05f,0.38f,0.11f,-0.23f,-0.19f,-0.43f,-0.18f,-0.28f,0.01f,-0.33f,-0.08f,-0.07f},
			{-0.21f,-0.22f,-0.29f,0.10f,0.34f,0.22f,0.08f,-0.36f,-0.14f,-0.38f,0.22f,-0.03f,0.07f,-0.20f,0.00f,0.06f,-0.06f,-0.20f,-0.12f,0.13f},
			{0.66f,0.44f,0.63f,0.76f,0.19f,0.33f,0.27f,0.66f,0.66f,-0.10f,-0.14f,-0.17f,-0.32f,0.01f,0.36f,0.22f},
			{0.58f,0.48f,0.58f,0.47f,0.13f,-0.27f,0.13f,-0.17f,0.04f,0.06f,0.14f,0.38f,0.16f,0.22f,-0.02f,-0.55f,-0.27f,0.15f}
	};
	int[][] depth = {
			{0,-2,-4,-6,-8,-10,-12,-14,-16,-18,-20,-22,-24,-26,-28,-30,-32,-34,-36},
			{0,-2,-4,-6,-8,-10,-12,-14,-16,-18,-20,-22,-24,-26,-28,-30,-32,-34,-36,-38},
			{0,-2,-4,-6,-8,-10,-12,-14,-16,-18,-20,-22,-24,-26,-28,-30,-32,-34,-36,-38,-40}, 
			{0,-2,-4,-6,-8,-10,-12,-14,-16,-18,-20,-22,-24,-26,-28,-30,-32},
			{0,-2,-4,-6,-8,-10,-12,-14,-16,-18,-20,-22,-24,-26,-28,-30,-32,-34}             
	};
	float[][] ipiLevel = {
			{18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0},
			{19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0},
			{20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0},
			{16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0},
			{17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0}
	};

	final int checked = 1;
	final int unChecked = 0;

	// 기본 화면 in
	@RequestMapping(value = "pangyothesharp", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpSession ses) {
		System.out.println("pangyothesharp페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		mav = new ModelAndView(); 
		String systemHtml = "";
		String waterSenHtml = "";
		String waterSenJS = "";
		String waterTableHtml = "";
		UserVO user = (UserVO)ses.getAttribute("user");
		if(user==null) {
			mav.addObject("pageName",pageName);
			mav.addObject("pageKorName",pageKorName);
			mav.setViewName("./login/login");
			return mav;
		} else {
			//data 생성부
			ISiteSVC psvc = new PangyothesharpSVCImpl();
			//로거목록
			List<String> lgList = new ArrayList<String>();
			lgList = psvc.getTableNames(pageName);
			//시스템 목록
			List<SystemVO> syList = new ArrayList<SystemVO>();
			syList = psvc.getSystemInitAndLast(lgList);
			//설치된 센서 리스트
			List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
			psList = psvc.getPlaceSet(pageName);
			//출력할 수위계 리스트
			List<WaterSenResultVO> wsrList = new ArrayList<WaterSenResultVO>();
			wsrList = psvc.getWaterSensers(psList);
			
			//여기서부터 for문으로 html생성x
			List<IPIVO> ipi2List = new ArrayList<IPIVO>();
			List<IPIVO> ipi3List = new ArrayList<IPIVO>();
			List<IPIVO> ipi7List = new ArrayList<IPIVO>();
			List<IPIVO> ipi11List = new ArrayList<IPIVO>();
			List<IPIVO> ipi13List = new ArrayList<IPIVO>();
			ipi2List = psvc.getIPIGraphData(psList, depth[0], ipiName[0], existing[0],lgList.get(0));
			ipi3List = psvc.getIPIGraphData(psList, depth[1], ipiName[1], existing[1],lgList.get(1));
			ipi7List = psvc.getIPIGraphData(psList, depth[2], ipiName[2], existing[2],lgList.get(2));
			ipi11List = psvc.getIPIGraphData(psList, depth[3], ipiName[3], existing[3],lgList.get(3));
			ipi13List = psvc.getIPIGraphData(psList, depth[4], ipiName[4], existing[4],lgList.get(4));

			IPIVO ipi2Max = new IPIVO();
			IPIVO ipi3Max = new IPIVO();
			IPIVO ipi7Max = new IPIVO();
			IPIVO ipi11Max = new IPIVO();
			IPIVO ipi13Max = new IPIVO();
			ipi2Max = psvc.getIPIMax(ipi2List);
			ipi3Max = psvc.getIPIMax(ipi3List);
			ipi7Max = psvc.getIPIMax(ipi7List);
			ipi11Max = psvc.getIPIMax(ipi11List);
			ipi13Max = psvc.getIPIMax(ipi13List);
			
			//html 생성부
			systemHtml = psvc.makeSystemHtml(syList, systemName);
			waterSenHtml = psvc.makeWaterSenHtml(wsrList);
			waterSenJS = psvc.makeWaterSenJS(wsrList);
			waterTableHtml = psvc.makeWaterTableHtml(wsrList);
			
			//modal 삽입부
			mav.addObject("syshtml", systemHtml);
			mav.addObject("wshtml", waterSenHtml);
			mav.addObject("wsjs", waterSenJS);
			mav.addObject("wtable", waterTableHtml);
			
			mav.addObject("ipi2List", ipi2List);
			mav.addObject("ipi3List", ipi3List);
			mav.addObject("ipi7List", ipi7List);
			mav.addObject("ipi11List", ipi11List);
			mav.addObject("ipi13List", ipi13List);
			mav.addObject("ipi2Max",ipi2Max);
			mav.addObject("ipi3Max",ipi3Max);
			mav.addObject("ipi7Max",ipi7Max);
			mav.addObject("ipi11Max",ipi11Max);
			mav.addObject("ipi13Max",ipi13Max);
			
			//session 및 view 처리
			mav.setViewName("pangyothesharp/main");
			ses.setAttribute("user", user);
			
			return mav;
		}
	}
	
	@RequestMapping(value = "pangyothesharp/data", method = RequestMethod.GET)
	public ModelAndView pangyothesharpData(Locale locale, Model model, HttpSession ses) {
		System.out.println("pangyothesharp/data페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		mav = new ModelAndView();
		mav.setViewName("pangyothesharp/data/data");
		return mav;
	}
	
	@RequestMapping(value = "pangyothesharp/data_total", method = RequestMethod.GET)
	public ModelAndView pangyothesharpDataTotal(Locale locale, Model model, HttpSession ses, int id, String start, String end) {
		System.out.println("pangyothesharp/data_total페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		mav = new ModelAndView();
		
		//data 생성부
		ISiteSVC psvc = new PangyothesharpSVCImpl();
		//로거목록
		List<String> lgList = new ArrayList<String>();
		lgList = psvc.getTableNames(pageName);
		String[] loggers = new String[lgList.size()];
		for (int i = 0; i < lgList.size(); i++) {
			loggers[i] = lgList.get(i);
		}
		//설치된 센서 리스트
		List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
		psList = psvc.getPlaceSet(pageName);
//		for (PlaceSetVO ps : psList) {
//			System.out.println(ps);
//		}
		// ipi리스트들
		String[] ipi2List = ipiName[0];
		String[] ipi3List = ipiName[1];
		String[] ipi7List = ipiName[2];
		String[] ipi11List = ipiName[3];
		String[] ipi13List = ipiName[4];
//		List<String> wlList = new ArrayList<String>();

		//임시로 그냥 지정
		String[] wlList = {"WL_01","WL_02","WL_03","WL_05"};
		
		if(!TimeSetting.checkTimeForm(start) && !TimeSetting.checkTimeForm(end)) {
			end = TimeSetting.SetEnd();
			start = TimeSetting.SetStart();
		}
		
		String[][] systemDatas = psvc.getSystem(loggers[id],psList,start,end);
//		Map<String, List> systemMap = psvc.getSystem(loggers[id],start,end);
		
		mav.addObject("pageName",pageName);
		mav.addObject("pageKorName",pageKorName);
		mav.addObject("systemName",systemName);
		mav.addObject("ipiName",ipiName);
		
		mav.addObject("ipi2List", ipi2List);
		mav.addObject("ipi3List", ipi3List);
		mav.addObject("ipi7List", ipi7List);
		mav.addObject("ipi11List", ipi11List);
		mav.addObject("ipi13List", ipi13List);
		mav.addObject("wlList", wlList);
		
		mav.addObject("systemDatas", systemDatas);
		
		mav.addObject("start",start);
		mav.addObject("end",end);
		mav.addObject("id",id);
		
		mav.setViewName("pangyothesharp/data/data_total");
		return mav;
	}
	
	@RequestMapping(value = "pangyothesharp/data_wl", method = RequestMethod.GET)
	public ModelAndView pangyothesharpDataWL(Locale locale, Model model, HttpSession ses, int id, String select_id, int chkAvg, String start, String end) {
		System.out.println("pangyothesharp/data_wl페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		mav = new ModelAndView();
		
		//data 생성부
		ISiteSVC psvc = new PangyothesharpSVCImpl();
		
		//로거목록
		List<String> lgList = new ArrayList<String>();
		lgList = psvc.getTableNames(pageName);
		String[] loggers = new String[lgList.size()];
		for (int i = 0; i < lgList.size(); i++) {
			loggers[i] = lgList.get(i);
		}
		//설치된 센서 리스트
		List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
		psList = psvc.getPlaceSet(pageName);
		// ipi리스트들
		String[] ipi2List = ipiName[0];
		String[] ipi3List = ipiName[1];
		String[] ipi7List = ipiName[2];
		String[] ipi11List = ipiName[3];
		String[] ipi13List = ipiName[4];

		//임시로 그냥 지정
		String[] wlList = {"WL_01","WL_02","WL_03","WL_05"};
		//출력 내용 배열
		String[] outputColumns = {"측정시간","측정값(mV)","각도(degree)","변화량(mm)","비고"};
		
		if(!TimeSetting.checkTimeForm(start) || !TimeSetting.checkTimeForm(end)) {
			end = TimeSetting.SetEnd();
			start = TimeSetting.SetStart();
		}
		//쓰이는 수위계 정보
		PlaceSetVO senInfo = new PlaceSetVO();
		for (PlaceSetVO ps : psList) {
			if(ps.getSen_name().equals(select_id)) {
				senInfo = ps;
			}
		} 
		//수위계 데이터 배열
		String[][] wlDatas = psvc.getWL(select_id, psList, start, end, outputColumns);
		
		//페이지 기본정보
		mav.addObject("pageName",pageName);
		mav.addObject("pageKorName",pageKorName);
		mav.addObject("systemName",systemName);
		mav.addObject("ipiName",ipiName);
		
		//네비바용 정보
		mav.addObject("ipi2List", ipi2List);
		mav.addObject("ipi3List", ipi3List);
		mav.addObject("ipi7List", ipi7List);
		mav.addObject("ipi11List", ipi11List);
		mav.addObject("ipi13List", ipi13List);
		mav.addObject("wlList", wlList);
		
		//메인페이지 정보
		mav.addObject("wlDatas", wlDatas);
		mav.addObject("senInfo",senInfo);

		mav.addObject("id",id);
		mav.addObject("chkAvg",chkAvg);
		mav.addObject("selectId",select_id);
		mav.addObject("start",start);
		mav.addObject("end",end);
		
		mav.setViewName("pangyothesharp/data/data_wl");
		return mav;
	}
	
	//누적그레프 출력
//	@RequestMapping(value = "pangyothesharp/data_all_ipi", method = RequestMethod.GET)
//	public ModelAndView pangyothesharpDataAllIpi(Locale locale, Model model, HttpSession ses, int id, String start, String end) {
//		System.out.println("pangyothesharp/data_wl페이지 in");
//		logger.info("Welcome home! The client locale is {}.", locale);
//		mav = new ModelAndView();
//		
//		//data 생성부
//		ISiteSVC psvc = new PangyothesharpSVCImpl();
//		
//		//로거목록
//		List<String> lgList = new ArrayList<String>();
//		lgList = psvc.getTableNames(pageName);
//		String[] loggers = new String[lgList.size()];
//		for (int i = 0; i < lgList.size(); i++) {
//			loggers[i] = lgList.get(i);
//		}
//		//설치된 센서 리스트
//		List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
//		psList = psvc.getPlaceSet(pageName);
//		// ipi리스트들
//		String[] ipi2List = ipiName[0];
//		String[] ipi3List = ipiName[1];
//		String[] ipi7List = ipiName[2];
//		String[] ipi11List = ipiName[3];
//		String[] ipi13List = ipiName[4];
//
//		//임시로 그냥 지정
//		String[] wlList = {"WL_01","WL_02","WL_03","WL_05"};
//		String selectIpi = pageName+"_"+id;
//		
//		if(!TimeSetting.checkTimeForm(start) && !TimeSetting.checkTimeForm(end)) {
//			end = TimeSetting.SetEnd();
//			start = TimeSetting.SetStart();
//		}
//		
//		//출력할 데이터의 숫자
//		int viewGraphNum = 5;
//		//end=선택한 날짜
//		String[][] allIpiDatas = psvc.getIPIGraphDatas(ipiName, depth, psList, selectIpi, end, viewGraphNum);
//		
//		
//		//페이지 기본정보
//		mav.addObject("pageName",pageName);
//		mav.addObject("pageKorName",pageKorName);
//		mav.addObject("systemName",systemName);
//		mav.addObject("ipiName",ipiName);
//		
//		//네비바용 정보
//		mav.addObject("ipi2List", ipi2List);
//		mav.addObject("ipi3List", ipi3List);
//		mav.addObject("ipi7List", ipi7List);
//		mav.addObject("ipi11List", ipi11List);
//		mav.addObject("ipi13List", ipi13List);
//		mav.addObject("wlList", wlList);
//		
//		//메인페이지 정보
////		mav.addObject("wlDatas", wlDatas);
////		mav.addObject("senInfo",senInfo);
//
//		mav.addObject("id",id);
//		mav.addObject("start",start);
//		mav.addObject("end",end);
//		
//		mav.setViewName("pangyothesharp/data/data_all_ipi");
//		return mav;
//	}
	
	@RequestMapping(value = "pangyothesharp/data_total_ipi", method = RequestMethod.GET)
	public ModelAndView pangyothesharpDataTotalIpi(Locale locale, Model model, HttpSession ses, int id, String start, String end) {
		System.out.println("pangyothesharp/data_total페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		mav = new ModelAndView();
		
		//data 생성부
		ISiteSVC psvc = new PangyothesharpSVCImpl();
		//로거목록
		List<String> lgList = new ArrayList<String>();
		lgList = psvc.getTableNames(pageName);
		String[] loggers = new String[lgList.size()];
		for (int i = 0; i < lgList.size(); i++) {
			loggers[i] = lgList.get(i);
		}
		//설치된 센서 리스트
		List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
		psList = psvc.getPlaceSet(pageName);
		List<PlaceSetVO> ipiPsList = new ArrayList<PlaceSetVO>();
		for (PlaceSetVO ps : psList) {
			if(ps.getSen_type().equals("IPI"))
				ipiPsList.add(ps);
		}
		// ipi리스트들
		String[] ipi2List = ipiName[0];
		String[] ipi3List = ipiName[1];
		String[] ipi7List = ipiName[2];
		String[] ipi11List = ipiName[3];
		String[] ipi13List = ipiName[4];

		//임시로 그냥 지정
		String[] wlList = {"WL_01","WL_02","WL_03","WL_05"};
		
		if(!TimeSetting.checkTimeForm(start) && !TimeSetting.checkTimeForm(end)) {
			end = TimeSetting.SetEnd();
			start = TimeSetting.SetStart();
		}
		
		String[][] ipiDatas = psvc.getIPITotal(loggers[id],ipiPsList,start,end,ipiName[id],depth[id]);
		
		for (String[] strs : ipiDatas) {
			for (String str : strs) {
				System.out.print(str + "/");
			}
			System.out.println();
		}
		
		mav.addObject("pageName",pageName);
		mav.addObject("pageKorName",pageKorName);
		mav.addObject("systemName",systemName);
		mav.addObject("ipiName",ipiName);
		
		mav.addObject("ipi2List", ipi2List);
		mav.addObject("ipi3List", ipi3List);
		mav.addObject("ipi7List", ipi7List);
		mav.addObject("ipi11List", ipi11List);
		mav.addObject("ipi13List", ipi13List);
		mav.addObject("wlList", wlList);
		
		mav.addObject("ipiDatas", ipiDatas);

		mav.addObject("id",id);
		mav.addObject("start",start);
		mav.addObject("end",end);
		mav.addObject("ipiName",ipiGroupName[id]);
		mav.addObject("ipiGroupKorName",ipiGroupKorName[id]);
		
		mav.setViewName("pangyothesharp/data/data_total_ipi");
		return mav;
	}
	
	@RequestMapping(value = "pangyothesharp/data_ipi", method = RequestMethod.GET)
	public ModelAndView pangyothesharpDataIPI(Locale locale, Model model, HttpSession ses, int id, String select_id, int chkAvg, String start, String end) {
		System.out.println("pangyothesharp/data_ipi페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		mav = new ModelAndView();
		
		//data 생성부
		ISiteSVC psvc = new PangyothesharpSVCImpl();
		
		//로거목록
		List<String> lgList = new ArrayList<String>();
		lgList = psvc.getTableNames(pageName);
		String[] loggers = new String[lgList.size()];
		for (int i = 0; i < lgList.size(); i++) {
			loggers[i] = lgList.get(i);
		}
		//설치된 센서 리스트
		List<PlaceSetVO> psList = new ArrayList<PlaceSetVO>();
		psList = psvc.getPlaceSet(pageName);
		// ipi리스트들
		String[] ipi2List = ipiName[0];
		String[] ipi3List = ipiName[1];
		String[] ipi7List = ipiName[2];
		String[] ipi11List = ipiName[3];
		String[] ipi13List = ipiName[4];

		//임시로 그냥 지정
		String[] wlList = {"WL_01","WL_02","WL_03","WL_05"};
		//출력 내용 배열
		String[] outputColumns = {"측정시간","측정값(mV)","각도(degree)","변화량(mm)","비고"};
		
		if(!TimeSetting.checkTimeForm(start) || !TimeSetting.checkTimeForm(end)) {
			end = TimeSetting.SetEnd();
			start = TimeSetting.SetStart();
		}
		//쓰이는 수위계 정보
		PlaceSetVO senInfo = new PlaceSetVO();
		for (PlaceSetVO ps : psList) {
			if(ps.getSen_name().equals(select_id)) {
				senInfo = ps;
			}
		} 
		//수위계 데이터 배열
//		String[][] wlDatas = psvc.getWL(select_id, psList, start, end, outputColumns);
		
		//페이지 기본정보
		mav.addObject("pageName",pageName);
		mav.addObject("pageKorName",pageKorName);
		mav.addObject("systemName",systemName);
		mav.addObject("ipiName",ipiName);
		
		//네비바용 정보
		mav.addObject("ipi2List", ipi2List);
		mav.addObject("ipi3List", ipi3List);
		mav.addObject("ipi7List", ipi7List);
		mav.addObject("ipi11List", ipi11List);
		mav.addObject("ipi13List", ipi13List);
		mav.addObject("wlList", wlList);
		
		//메인페이지 정보
//		mav.addObject("wlDatas", wlDatas);
//		mav.addObject("senInfo",senInfo);

		mav.addObject("id",id);
		mav.addObject("chkAvg",chkAvg);
		mav.addObject("selectId",select_id);
		mav.addObject("start",start);
		mav.addObject("end",end);
		
		mav.setViewName("pangyothesharp/data/data_ipi");
		return mav;
	}
}
