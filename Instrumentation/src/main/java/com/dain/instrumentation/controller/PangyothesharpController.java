package com.dain.instrumentation.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dain.instrumentation.model.vo.UserVO;

@Controller
public class PangyothesharpController {
	
	private static final Logger logger = LoggerFactory.getLogger(PangyothesharpController.class);
	private static ModelAndView mav;

	// 기본 화면 in
	@RequestMapping(value = "pangyothesharp", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpSession ses) {
		System.out.println("pangyothesharp페이지 in");
		logger.info("Welcome home! The client locale is {}.", locale);
		
		mav = new ModelAndView();
		String pageName = "pangyothesharp";
		String pageKorName = "판교TheSharp";
		UserVO user = (UserVO)ses.getAttribute("user");
		if(user==null) {
			mav.addObject("pageName",pageName);
			mav.addObject("pageKorName",pageKorName);
			mav.setViewName("./login/login");
			return mav;
		} else {
			mav.setViewName("pangyothesharp/main");
			
			
			
			
			return mav;
		}
		
		
	}
}
