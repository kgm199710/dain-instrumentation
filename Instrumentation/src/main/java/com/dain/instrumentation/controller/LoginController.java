package com.dain.instrumentation.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	private static ModelAndView mav;

	
//	 //로그인 페이지 in
//	@RequestMapping(value = "login", method = RequestMethod.GET)
//	public ModelAndView login(Locale locale, ModelAndView mav) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		this.mav = mav;
//		System.out.println("확인\n" + mav + "\n여기까지");
//		this.mav.setViewName("login/login");
//		System.out.println("login page in");
//		
//		return mav;
//	}

	// 로그인 하기
	// 로그인 검증 Service 만들어야됨
	@RequestMapping(value = "login_proc", method = RequestMethod.POST)
	public ModelAndView loginProc(Locale locale, HttpSession ses,  String user_id, String user_pw, String pageName, String pageKorName) {
		System.out.println("login proc");
		
		//임의로 지정 - 후에 제대로된 DB까지 생성하면 DB에서 받아오는 것으로
		String correctId = "pangyothesharp";
		String correctPw = "pangyothesharp";
		
		mav = new ModelAndView();
//		System.out.println("login=" + user_id + ", pw=" + user_pw + ", pageName=" + pageName + ", pageKorName=" + pageKorName);
		logger.info("Welcome home! The client locale is {}.", locale);
		
		if(user_id.equals(correctId) && user_pw.equals(correctPw)) {
			UserVO user = new UserVO();
			ses.setAttribute("user", user);
			mav.setViewName("redirect:/" + pageName);
			System.out.println(mav.getViewName());
		} else {
			mav.addObject("pageName",pageName);
			mav.addObject("pageKorName",pageKorName);
			mav.setViewName("./login/login");
		}
		return mav;
	}

	// 로그아웃 시
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String memberLogout(HttpSession ses, HttpServletRequest request, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("logout_proc()...");
		ses.invalidate();
		String url = "redirect:" + request.getHeader("Referer");
		System.out.println(url);
		return url; // 이전페이지로 돌아가기
	}
}
