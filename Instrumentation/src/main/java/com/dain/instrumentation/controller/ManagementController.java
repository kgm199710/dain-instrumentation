package com.dain.instrumentation.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dain.instrumentation.model.vo.SpotVO;
import com.dain.instrumentation.service.impl.ManagementSVCImpl;
import com.dain.instrumentation.service.impl.dummyService;
import com.dain.instrumentation.service.inf.IManagementSVC;
import com.dain.instrumentation.util.DBConnection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * Handles requests for the application home page.
 */
@Controller
public class ManagementController {
//	@Autowired
	IManagementSVC mSvc;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	private static ModelAndView mav;
	
	
	// 기본 화면 in
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String topNavHtml ="";
		String mainContHtml = "";
		String modalContHtml = "";
		
		//전체 spot
		ArrayList<SpotVO> spList = new ArrayList<SpotVO>();
		mSvc = new ManagementSVCImpl();
		spList = mSvc.getSpotOnPage();
		
		//회사리스트
		ArrayList<String> cpList = new ArrayList<String>();
		cpList = mSvc.getCompany(spList);
		
		//상단 네비바 
		topNavHtml = mSvc.getTopNavHtml(spList, cpList);
		//메인 내용
		mainContHtml = mSvc.getMainContHtml(spList, cpList);
		//모달 내용
		modalContHtml = mSvc.getModalContHtml();

		model.addAttribute("spList", spList);
		model.addAttribute("cpList", cpList);
		model.addAttribute("topNav", topNavHtml);
		model.addAttribute("mainCont", mainContHtml);
		model.addAttribute("modalCont", modalContHtml);
		mav = new ModelAndView();
		mav.setViewName("management/main");
		System.out.println("management page in");
		
		return mav;
	}
	
	// 로그인 페이지 in
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("login page in");
		
		return "management/login";
	}

	// 로그인 하기
	// 로그인 검증 Service 만들어야됨
	@RequestMapping(value = "login_proc", method = RequestMethod.POST)
	public String loginProc(HttpSession ses, Model model, String login, String pw, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("login proc");
		
		
		return "redirect:/";
	}


//	// 로그인 하기
//	@RequestMapping(value = "login_ok", method = RequestMethod.POST)
//	public String loginOk(HttpSession ses, Model model, String login, String pw, Locale locale) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		System.out.println("login page in");
//		
//		
//		return "management/main";
//	}
	// 로그아웃 시
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String memberLogout(HttpSession ses, Model model, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("logout_proc()...");
		ses.invalidate();
//			return "management/main"; // 그냥 메인페이지 출력
		return "redirect:/"; // 이전페이지로 돌아가기
	}
	

	// info 출력
	@RequestMapping(value="info", method=RequestMethod.GET)
	public String info(Model model, Locale locale, HttpServletRequest request) {
		// model값 저장-tb(테이블) 종류 확인
		String tb = request.getParameter("tb");
		System.out.println("tb = " + tb);
		String info = dummyService.readDummySenser(tb);
		request.setAttribute("info", info);
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("info in");
		
		
		
		mSvc = new ManagementSVCImpl();
		String infoHtml = "";
		
		infoHtml = mSvc.getInfoHtml(tb);
		

		model.addAttribute("info", infoHtml);
		model.addAttribute("tb", tb);
		return "management/info";
	}
	// data 출력
	@RequestMapping(value="data", method=RequestMethod.GET)
	public String data(Model model, Locale locale, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("data in");
		// model값 저장-tb(테이블) 종류 확인
		String tb = request.getParameter("tb");
		System.out.println("tb = " + tb);
		String data = dummyService.readDummy(tb);
		request.setAttribute("data", data);
		return "management/data";
	}
	
}
