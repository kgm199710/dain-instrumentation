package com.dain.instrumentation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dain.instrumentation.model.dao.impl.getData.getDataDAO;
import com.dain.instrumentation.model.dao.inf.getData.IGetDataDAO;
import com.dain.instrumentation.model.vo.UserVO;
import com.dain.instrumentation.model.vo.pangyothesharp.IPIVO;
import com.dain.instrumentation.model.vo.pangyothesharp.SystemVO;
import com.dain.instrumentation.model.vo.pangyothesharp.WaterSenVO;
import com.dain.instrumentation.service.impl.site.PangyothesharpSVCImpl;
import com.dain.instrumentation.service.inf.ISiteSVC;

@Controller
public class PangyothesharpController {
	
	private static final Logger logger = LoggerFactory.getLogger(PangyothesharpController.class);
	private static ModelAndView mav;

	// 기본 화면 in
	@RequestMapping(value = "pangyothesharp", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpSession ses) {
		System.out.println("pangyothesharp페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		String pageName = "pangyothesharp";
		String pageKorName = "판교TheSharp";
		String[] systemName = {"시스템1","시스템2","시스템3","시스템4","시스템5"};
		
		String systemHtml = "";
		UserVO user = (UserVO)ses.getAttribute("user");
		mav = new ModelAndView(); 
		if(user==null) {
			mav.addObject("pageName",pageName);
			mav.addObject("pageKorName",pageKorName);
			mav.setViewName("./login/login");
			return mav;
		} else {
			mav.setViewName("pangyothesharp/main");
			IGetDataDAO gddao = new getDataDAO();
			ISiteSVC psvc = new PangyothesharpSVCImpl();
			
			List<String> lgList = new ArrayList<String>();
			lgList = psvc.getTableNames(pageName); //로거목록

			List<SystemVO> syList = new ArrayList<SystemVO>();
			syList = psvc.getSystemInitAndLast(lgList); //시스템 목록
			
			List<WaterSenVO> wsList = new ArrayList<WaterSenVO>();
			
			systemHtml = psvc.makeSystemHtml(syList, systemName);
			
//			String test = "\r\n"
//					+ "                [-2,1.11,null],";
//			mav.addObject("test", test);
			
			
			return mav;
		}
		
		
	}
}
