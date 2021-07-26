package com.dain.instrumentation.service.inf;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.dain.instrumentation.model.vo.LoggerVO;
import com.dain.instrumentation.model.vo.SensorsVO;
import com.dain.instrumentation.model.vo.PlacesVO;
import com.dain.instrumentation.model.vo.SpotVO;

@Service
public interface IManagementSVC {
	
	//상단 nav구성항목 얻기
	ArrayList<SpotVO> getSpotOnPage();
	//회사명 리스트 얻기
	ArrayList<String> getCompany(ArrayList<SpotVO> spList);
	//장소리스트, 회사명리스트로 html생성하기
	String getTopNavHtml(ArrayList<SpotVO> spList, ArrayList<String> cpList);
	
	//
	String getMainContHtml(ArrayList<SpotVO> spList, ArrayList<String> cpList);
	String getModalContHtml();
	String getInfoHtml(String tb);
}
