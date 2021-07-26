package com.dain.instrumentation.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dain.instrumentation.model.dao.impl.management.LoggerDAOImpl;
import com.dain.instrumentation.model.dao.impl.management.NotificationDAOImpl;
import com.dain.instrumentation.model.dao.impl.management.PlacesDAOImpl;
import com.dain.instrumentation.model.dao.impl.management.SpotDAOImpl;
import com.dain.instrumentation.model.dao.impl.management.SystemDAOImpl;
import com.dain.instrumentation.model.dao.inf.management.ILoggerDAO;
import com.dain.instrumentation.model.dao.inf.management.INotificationDAO;
import com.dain.instrumentation.model.dao.inf.management.IPlacesDAO;
import com.dain.instrumentation.model.dao.inf.management.ISpotDAO;
import com.dain.instrumentation.model.dao.inf.management.ISystemDAO;
import com.dain.instrumentation.model.vo.LoggerVO;
import com.dain.instrumentation.model.vo.NotificationVO;
import com.dain.instrumentation.model.vo.PlacesVO;
import com.dain.instrumentation.model.vo.SpotVO;
import com.dain.instrumentation.service.inf.IManagementSVC;

public class ManagementSVCImpl implements IManagementSVC {
	
//	@Autowired
	ISpotDAO spDao;
//	@Autowired
	IPlacesDAO plDao;
//	@Autowired
	ILoggerDAO lgDao;
//	@Autowired
	ISystemDAO ssDao;
//	@Autowired
	INotificationDAO nfDao;


	//출력여부 확인 된 장소/회사 리스트 출력
	@Override
	public ArrayList<SpotVO> getSpotOnPage() {
		spDao = new SpotDAOImpl();
		ArrayList<SpotVO> spList = spDao.selectAll();
		
		for (int i = spList.size(); i > 0; i--) {
			System.out.println(i + "번째 " + spList.get(i-1).getSpot() + "읽힘");
			if(spList.get(i-1).isView()==false) {
				spList.remove(i-1);
				System.out.println("delete");
			}
		}
		for (SpotVO sp : spList) {
			System.out.println("spList = " + sp);
		}
		return spList;
	}
	
	//회사 리스트 출력
	@Override
	public ArrayList<String> getCompany(ArrayList<SpotVO> spList) {
		ArrayList<String> cpList = new ArrayList<String>();
		cpList.add(spList.get(0).getCompany());
		for (SpotVO sp : spList) {
			boolean check = true;
			for (int i = 0; i < cpList.size(); i++) {
				if(cpList.get(i).equals(sp.getCompany()))
					check = false;
			}
			if(check) cpList.add(sp.getCompany());
		}
		for (String cp : cpList) {
			System.out.println("cpList = " + cp); 
		}
		
		return cpList;
	}
	//nav바 html코드 출력
	@Override
	public String getTopNavHtml(ArrayList<SpotVO> spList, ArrayList<String> cpList) {
		String html = "";
		
		for (int i = 0; i < cpList.size(); i++) {
			ArrayList<String> spK = getSpKList(spList, cpList.get(i));
			html += "					<li class=\"dropdown\">"
					+ "						<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">\r\n"
					+ "							" + cpList.get(i) + "\r\n" 
					+ "							<span class=\"badge\">" + spK.size() + "</span> \r\n"
					+ "							<span class=\"caret\"></span>\r\n"
					+ "						</a>\r\n"
					+ "						<ul class=\"dropdown-menu\">\r\n";
			for (int j=0; j<spK.size(); j++) {
				ArrayList<String> spE = getSpEList(spList, cpList.get(i));
				System.out.println(spE.get(j)); 
				html += "							<li>\r\n"
						+ "								<a href=\"#"
						+ spE.get(j)
						+ "\">\r\n									"
						+ spK.get(j)
						+ "\r\n								</a>\r\n"
						+ "							</li>\r\n";
			}
			html += "						</ul>\r\n"
					+ "					</li>\r\n";
		}
		System.out.println("상단 네비 구성 항목 완료");
		return html;
	}
	//장소 영문명 리스트 출력
	public ArrayList<String> getSpEList(ArrayList<SpotVO> spList, String cp){
		ArrayList<String> spEList = new ArrayList<String>();
		for (SpotVO sp : spList) {
			boolean check = true;
			if(sp.getCompany().equals(cp)) {
				for (int j = 0; j < spEList.size(); j++) {
					if(spEList.get(j).equals(sp.getEtc())) check=false;
				}
				if(check)spEList.add(sp.getEtc());
			}
		}
		System.out.println("장소 영문 리스트 생성완료");
		return spEList;
	}
	//장소 한글명 리슽 출력
	public ArrayList<String> getSpKList(ArrayList<SpotVO> spList, String cp){
		ArrayList<String> spKList = new ArrayList<String>();
		for (SpotVO sp : spList) {
			boolean check = true;
			if(sp.getCompany().equals(cp)) {
				for (int j = 0; j < spKList.size(); j++) {
					if(spKList.get(j).equals(sp.getSpot())) check=false;
				}
				if(check)spKList.add(sp.getSpot());
			}
		}
		System.out.println("장소 한글 리스트 생성완료");
		return spKList;
	}
	
	//메인 화면 내용 출력
	@Override
		public String getMainContHtml(ArrayList<SpotVO> spList, ArrayList<String> cpList) {
		String mainContHtml = "";
		for (String cp : cpList) {
			mainContHtml += 
					"				<div class=\"panel panel-primary \">\r\n"
					+ "					<div class=\"panel-heading\">\r\n"
					+ "						<span class=\"glyphicon glyphicon-tags\"></span>"+cp+"\r\n"
					+ "					</div>\r\n"
					+ "					<div class=\"panel-body\">\r\n";
			//장소명 한글
			ArrayList<String> spK = getSpKList(spList, cp);
			//장소명 영어
			ArrayList<String> spE = getSpEList(spList, cp);
			for (int i=0; i<spK.size(); i++) {
				System.out.println("test - 한글장소명을 토대로 for문");
				//장소(spot만큼생성)
				mainContHtml += 
						"						<h5 class id=\"" + spE.get(i) + "\">\r\n"
						+ "							<span class=\"glyphicon glyphicon-stats\"></span>" + spK.get(i) + "\r\n"
						+ "						</h5 class>\r\n"
						+ "						<div class=\"table-responsive\">\r\n"
						+ "							<table class=\"table table-hover text-center table-bordered\">\r\n"
						+ "								<thead>\r\n"
						+ "									<tr bgcolor=\"#eeeeee\">\r\n"
						+ "										<th class=\"col-xs-2 text-center\">현장명</th>\r\n"
						+ "										<th class=\"col-xs-2 text-center\">구분</th>\r\n"
						+ "										<th class=\"col-xs-2 text-center\">최초</th>\r\n"
						+ "										<th class=\"col-xs-2 text-center\">최근</th>\r\n"
						+ "										<th class=\"col-xs-2 text-center\">사이트</th>\r\n"
						+ "										<th class=\"col-xs-2 text-center\">정보</th>\r\n"
						+ "									</tr>\r\n"
						+ "								</thead>\r\n";
				//places테이블 상 데이터 묶음
				ArrayList<String> plKList = new ArrayList<String>();
				plKList = getPlList(spList,spK.get(i));
				
				for (String plK : plKList) {
					System.out.println("test - 현장 이름 리스트을 토대로 for문");
					//현장리스트
					ArrayList<PlacesVO> plList = new ArrayList<PlacesVO>();
					plDao = new PlacesDAOImpl();
					plList = plDao.selectNeed(plK);
					
					for (PlacesVO pl : plList) {
						System.out.println("test - 현장 테이블 리스트를 토대로 for문");
						if(pl.isDone()) {
							//로거리스트
							ArrayList<LoggerVO> lgList = new ArrayList<LoggerVO>();
							lgDao = new LoggerDAOImpl();
							lgList = lgDao.selectNeed(pl.getePlace());
							
							//현재 로거가 첫번째인지 여부 확인
							boolean check = true;
							for (LoggerVO lg : lgList) {
								System.out.println("test - 로거 테이블 리스트를 토대로 for문");
								
								ssDao = new SystemDAOImpl();
								
								//로거 시작
								Timestamp startDate = ssDao.findStartDate(lg.getName());
								//로거 최근
								Timestamp lateDate = ssDao.findLastDate(lg.getName());
								long lateTime = lateDate.getTime();
								//지금시간
								long nowDate = new Timestamp(System.currentTimeMillis()).getTime();
								//1시간 long형
								final long oneHour = 3600000;
								//현재시간 기준 데이터 인입여부 체크
								boolean ckTime = (nowDate - lateTime)<oneHour;
								
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
								System.out.println(sdf.format(nowDate));
								
								if(check) {
									mainContHtml +=
											"								<tr>\r\n"
											+ "									<td rowspan=\""+lgList.size()+"\">"+plK+"</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>"+lg.getNameKor()+"</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>"+sdf.format(startDate)+"</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>"+sdf.format(startDate)+"</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>\r\n"
											+ "										<a class=\"btn btn-primary btn-xs\" href=\""+lg.getPlace()+"\" target= \"_blank\" role=\"button\">\r\n"
											+ "											Site &raquo;\r\n"
											+ "										</a>\r\n"
											+ "									</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>\r\n"
											+ "										<a class=\"btn btn-info btn-xs\" href=\"info?tb="+lg.getName()+"\" target= \"_blank\" role=\"button\">info &raquo;</a>\r\n"
											+ "									</td>\r\n"
											+ "								</tr>\r\n";
									check = false;
								} else {
									mainContHtml +=
											"								<tr>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>"+lg.getNameKor()+"</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>"+sdf.format(startDate)+"</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>"+sdf.format(startDate)+"</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>\r\n"
											+ "									</td>\r\n"
											+ "									<td class=\'"+((ckTime)?"":"bg-warning")+"\'>\r\n"
											+ "										<a class=\"btn btn-info btn-xs\" href=\"info?tb="+lg.getName()+"\" target= \"_blank\" role=\"button\">info &raquo;</a>\r\n"
											+ "									</td>\r\n"
											+ "								</tr>\r\n";
								}
							}
						}
					}
				}
				//장소 닫기
				mainContHtml +=
						"							</table>\r\n"
						+ "						</div>\r\n";
			}
			//마무리
			mainContHtml += 
					"					</div><!-- panel body -->\r\n"
					+ "				</div><!-- panel dain -->";
			
		}
//		System.out.println(mainContHtml);
		return mainContHtml;
	}
	//spot 객체 리스트에서 spot한글명으로 검색하여 place추출
	private ArrayList<String> getPlList(ArrayList<SpotVO> spList, String spK) {
		ArrayList<String> plList = new ArrayList<String>();
		for (SpotVO sp : spList) {
			boolean check = true;
			if(sp.getSpot().equals(spK)) {
				for (int i = 0; i < plList.size(); i++) {
					if(plList.get(i).equals(sp.getPlaces())) check=false;
				}
				if(check)plList.add(sp.getPlaces());
			}
		}
		return plList;
	}
	
	@Override
		public String getModalContHtml() {
			String modalContHtml = "";
			nfDao = new NotificationDAOImpl();
			ArrayList<NotificationVO> nfList = new ArrayList<NotificationVO>();
			nfList = nfDao.selectNotification();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (NotificationVO nf : nfList) {
//				System.out.println(nf);
				modalContHtml += "					<h4 class=\"text-primary\">" + sdf.format(nf.getDate()) + " " + nf.getContent() + "</h4>\r\n";
			}
//			System.out.println(modalContHtml);
			return modalContHtml;
		}
	
	
}
