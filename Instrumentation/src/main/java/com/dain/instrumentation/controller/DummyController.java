package com.dain.instrumentation.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DummyController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	private static ModelAndView mav;

	// 기본 화면 in
	@RequestMapping(value = "dummy", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		
		
		mav = new ModelAndView();
		mav.setViewName("dummy/main");
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return mav;
	}
}
