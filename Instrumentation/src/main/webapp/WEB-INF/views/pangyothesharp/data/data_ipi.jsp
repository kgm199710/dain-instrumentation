<!-- 기본세팅 ~~ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- ~~ 여기까지 -->
<%@ page import = "com.dain.instrumentation.model.vo.pangyothesharp.IPIVO" %>
<%@ page import = "com.dain.instrumentation.model.vo.common.PlaceSetVO" %>
	<%
	String pageName = (String)request.getAttribute("pageName");
	String pageKorName = (String)request.getAttribute("pageKorName");
	String[] systemName = (String[])request.getAttribute("systemName");
	String[] ipi2List = (String[])request.getAttribute("ipi2List");
	String[] ipi3List = (String[])request.getAttribute("ipi3List");
	String[] ipi7List = (String[])request.getAttribute("ipi7List");
	String[] ipi11List = (String[])request.getAttribute("ipi11List");
	String[] ipi13List = (String[])request.getAttribute("ipi13List");
	String[] wlList = (String[])request.getAttribute("wlList");
	int id = (int)request.getAttribute("id");
	int chkAvg = (int)request.getAttribute("chkAvg");
	String selectId = (String)request.getAttribute("selectId");
	String start = (String)request.getAttribute("start");
	String end = (String)request.getAttribute("end");
// 	String[][] wlDatas = (String[][])request.getAttribute("wlDatas");
// 	PlaceSetVO senInfo = (PlaceSetVO)request.getAttribute("senInfo");
	
// 	String manageData = senInfo.getCalculation6();
	%>
<!--개별ipi전용 페이지-->
<html lang="ko" oncontextmenu='return false' >
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title><%=pageKorName %></title>
	<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/all.min.js"></script>
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	
	<script type = "text/javascript" src = "https://www.gstatic.com/charts/loader.js"></script>
	<script>
		window.onload = function() {
			//최초 접속시or클릭했던 ipi센서항목에 대한 표시값들 show=보여주기, active=흰색처리
	        document.getElementById('collapseIPI').className += ' show'
	        document.getElementById('(현재선택된ipi그룹명-IPI-2)').className += ' show'
	        document.getElementById('(현재선택된ipi명-IPI_2_18_Y)').className += ' active'
	    }
	</script>
	<style>
		table {
			text-align: center;
			font-size: 12px;
			padding-top:10px;
		}
		tr, td {
			padding: 10px;
			border-bottom: 1px solid #dadada;
			white-space:nowrap;
		}
	</style>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<a class="navbar-brand" href="<%=pageName%>"><%=pageKorName %></a>
		<button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
		<form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0"></form>
		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0"></ul>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<form method='GET' action= 'data_wl' class="d-md-inline-block form-inline ml-3 mr-3 mr-md-3 my-0 my-md-0">
							<input type="hidden" name="id" value="<%=id%>">
							<input type="hidden" name="select_id" value="<%=selectId%>">
							<input type="hidden" name="chkAvg" value="<%=chkAvg%>">
							<div class="form-group my-2 btn-block">
								<label for="start" >시작일 : </label>
								<input type="text" class="dateFilter form-control-sm btn-block" id="start" name="start" placeholder="Start" value="<%=start%>">
							</div>
							<div class="form-group my-2 btn-block">
								<label for="start" >종료일 : </label>
								<input type="text" class="dateFilter form-control-sm btn-block" id="end" name="end" placeholder="End" value="<%=end%>">
							</div>
							<div class="form-group my-2 btn-block">
								<button type="submit" class="btn btn-sm btn-primary btn-block">
									<i class="fa fa-search text-white-50"></i>
									조회하기
								</button>
							</div>
							<div class="form-group my-2 btn-block">
								<button class="btn btn-sm btn-primary btn-block" onclick="excelDown();">
									<i class="fas fa-download text-white-50"></i>
									내려받기
								</button>
							</div>
						</form>
					
						<button class="btn btn-sm btn-primary d-md-inline-block form-inline ml-3 mr-3 mr-md-3 my-2 my-md-2" type="submit" onClick="fnPrint()" >
							<i class="fa fa-print text-white-50"></i>
							프린트
						</button>
						
						
						<a class="nav-link collapsed" id="SYS" href="#" data-toggle="collapse" data-target="#collapseS" aria-expanded="false" aria-controls="collapseS" style="width:190px;">
						    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
						    시스템
						    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						</a>
						<div class="collapse" id="collapseS" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
						    <nav class="sb-sidenav-menu-nested nav">
						    	<% for(int i=0; i<systemName.length; i++){ %>
						    	<!-- 시스템갯수만큼반복 -->
						        <a class="nav-link my-0 py-1" href="data_total?id=<%= i %>&start=<%=start %>&end=<%=end%>">
						            <i class="fas fa-tag fa-sm text-white-50"></i>
						            &nbsp <%= systemName[i] %>
					            </a>
					            <% } %>
						    </nav>
						</div>
						
						<a class="nav-link collapsed" id="W" href="#" data-toggle="collapse" data-target="#collapseW" aria-expanded="false" aria-controls="collapseW" style="width:190px;">
						    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
						    지하수위계
						    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						</a>
						<div class="collapse" id="collapseW" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
						    <nav class="sb-sidenav-menu-nested nav">
						    <% for(int i=0; i<wlList.length; i++){ %>
						    	<a class="nav-link my-0 py-1" id="0" href="data_wl?id=<%=i%>&select_id=<%=wlList[i]%>&chkAvg=0&start=<%=start%>&end=<%=end%>">
						            <i class="fas fa-tag fa-sm text-white-50"></i>
						            <%= wlList[i] %>
					            </a>
						    <%} %>
						    </nav>
						</div>
						<a class="nav-link collapsed" id="IPI" href="#" data-toggle="collapse" data-target="#collapseIPI" aria-expanded="false" aria-controls="collapseIPI" style="width:190px;">
						    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
						    지중경사계
						    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						</a>
						<div class="collapse" id="collapseIPI" aria-labelledby="headingTwo" data-parent="#sidenavAccordion" style="width:190px;">
						    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
						        <a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#IPI-2" aria-expanded="false" aria-controls="IPI-2">
						            <i class="fas fa-tags fa-sm text-white-50"></i>
						            IPI-2
						            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						        </a>
						        <div class="collapse" id="IPI-2" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
						            <nav class="sb-sidenav-menu-nested nav">
						                <a class="nav-link my-0 py-1" id="IPI-2_chart" href="data_all_ipi?id=0&&end=<%=end%>">누적그래프</a>
						                <a class="nav-link my-0 py-1" id="IPI-2_data" href="data_total_ipi?id=0&start=<%=start%>&end=<%=end%>">누적데이터</a>
						                <% for(int i=0; i<ipi2List.length; i++){ %>
						                <a class="nav-link my-0 py-1" id="<%=ipi2List[i]%>" href="data_ipi?id=0&select_id=<%=ipi2List[i]%>&chkAvg=0&start=<%=start%>&end=<%=end%>"><%=ipi2List[i] %></a>
						                <% } %>
						            </nav>
						        </div><a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#IPI-3" aria-expanded="false" aria-controls="IPI-3">
						            <i class="fas fa-tags fa-sm text-white-50"></i>
						            IPI-3
						            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						        </a>
						        <div class="collapse" id="IPI-3" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
						            <nav class="sb-sidenav-menu-nested nav">
						                <a class="nav-link my-0 py-1" id="IPI-3_chart" href="data_all_ipi?id=1&end=<%=end%>">누적그래프</a>
						                <a class="nav-link my-0 py-1" id="IPI-3_data" href="data_total_ipi?id=1&start=<%=start%>&end=<%=end%>">누적데이터</a>
						                <% for(int i=0; i<ipi3List.length; i++){ %>
						                <a class="nav-link my-0 py-1" id="<%=ipi3List[i]%>" href="data_ipi?id=1&select_id=<%=ipi3List[i]%>&chkAvg=0&start=<%=start%>&end=<%=end%>"><%=ipi3List[i] %></a>
						                <% } %>
						            </nav>
						        </div>
						        <a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#IPI-7" aria-expanded="false" aria-controls="IPI-7">
						            <i class="fas fa-tags fa-sm text-white-50"></i>
						            IPI-7
						            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						        </a>
						        <div class="collapse" id="IPI-7" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
						            <nav class="sb-sidenav-menu-nested nav">
						                <a class="nav-link my-0 py-1" id="IPI-7_chart" href="data_all_ipi?id=2&end=<%=end%>">누적그래프</a>
						                <a class="nav-link my-0 py-1" id="IPI-7_data" href="data_total_ipi?id=2&start=<%=start%>&end=<%=end%>">누적데이터</a>
						                <% for(int i=0; i<ipi7List.length; i++){ %>
						                <a class="nav-link my-0 py-1" id="<%=ipi7List[i]%>" href="data_ipi?id=2&select_id=<%=ipi7List[i]%>&chkAvg=0&start=<%=start%>&end=<%=end%>"><%=ipi7List[i] %></a>
						                <% } %>
						            </nav>
						        </div>
						        <a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#IPI-11" aria-expanded="false" aria-controls="IPI-11">
						            <i class="fas fa-tags fa-sm text-white-50"></i>
						            IPI-11
						            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						        </a>
						        <div class="collapse" id="IPI-11" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
						            <nav class="sb-sidenav-menu-nested nav">
						                <a class="nav-link my-0 py-1" id="IPI-11_chart" href="data_all_ipi?id=3&end=<%=end%>">누적그래프</a>
						                <a class="nav-link my-0 py-1" id="IPI-11_data" href="data_total_ipi?id=3&start=<%=start%>&end=<%=end%>">누적데이터</a>
						                <% for(int i=0; i<ipi11List.length; i++){ %>
						                <a class="nav-link my-0 py-1" id="<%=ipi11List[i]%>" href="data_ipi?id=3&select_id=<%=ipi11List[i]%>&chkAvg=0&start=<%=start%>&end=<%=end%>"><%=ipi11List[i] %></a>
						                <% } %>
						            </nav>
						        </div>
						        <a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#IPI-13" aria-expanded="false" aria-controls="IPI-13">
						            <i class="fas fa-tags fa-sm text-white-50"></i>
						            IPI-13
						            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						        </a>
						        <div class="collapse" id="IPI-13" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
						            <nav class="sb-sidenav-menu-nested nav">
						                <a class="nav-link my-0 py-1" id="IPI-13_chart" href="data_all_ipi?id=4&end=<%=end%>">누적그래프</a>
						                <a class="nav-link my-0 py-1" id="IPI-13_data" href="data_total_ipi?id=4&start=<%=start%>&end=<%=end%>">누적데이터</a>
						                <% for(int i=0; i<ipi13List.length; i++){ %>
						                <a class="nav-link my-0 py-1" id="<%=ipi13List[i]%>" href="data_ipi?id=4&select_id=<%=ipi13List[i]%>&chkAvg=0&start=<%=start%>&end=<%=end%>"><%=ipi13List[i] %></a>
						                <% } %>
						            </nav>
						        </div>
						    </nav>
						</div>
					</div>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="py-3 px-3 my-3 mx-3" style="width:1000px;">
					<div class="text-center my-3">
						<h2>센서명(ipi낱개)<br></h2>
					</div>
					<div class="my-3">
						현장명 : 현장명<br>
						검색기간 : 시작날짜 ~  끝날짜<br>
					</div>
					<div id = "chart" style="border: 1px solid gray; height:410px;padding:3px;"></div>
					<table class="my-3" width="100%">
						<!--일평균데이터 보기일 경우-->
						<colgroup>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
						</colgroup>
						
						<tr>
							<td>측정시간</td>
							<td>평균측정값(mV)</td>
							<td>평균각도(degree)</td>
							<td>최대변화(mm)</td>
							<td>최소변화(mm)</td>
							<td>최대변화량(mm)</td>
							<td>평균변화량(mm)</td>
							<td>비고</td>
						</tr>
						<tr>
							<td>측정시간</td>
							<td>평균측정값(mV)</td>
							<td>평균각도(degree)</td>
							<td>최대변화(mm)</td>
							<td>최소변화(mm)</td>
							<td>최대변화량(mm)</td>
							<td>평균변화량(mm)</td>
							<td>비고</td>
						</tr>
						
						<!--시간별보기일 경우-->
						<colgroup>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
						</colgroup>
						<tr>
							<td>측정시간</td>
							<td>측정값(mV)</td>
							<td>각도(degree)</td>
							<td>변화량(mm)</td>
							<td>비고</td>
						</tr>
						<tr>
							<td>측정시간</td>
							<td>측정값(mV)</td>
							<td>각도(degree)</td>
							<td>변화량(mm)</td>
							<td>비고</td>
						</tr>
					</table>
				
				</div><!-- container-fluid -->
			</main>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap-4.5.3.bundle.min.js" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	
	<script type="text/javascript">
		var fnPrint = function() {
			window.print();
		};
	</script>
	
	<script type= "text/javascript">
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = new google.visualization.DataTable();
			data.addColumn('datetime', '시간');
			data.addColumn('number', '변화량');
			data.addColumn('number', '1차관리기준');
			data.addColumn('number', '1차관리기준');
			data.addColumn('number', '2차관리기준');
			data.addColumn('number', '2차관리기준');
			data.addColumn('number', '3차관리기준');
			data.addColumn('number', '3차관리기준');
			var options = {
				width:'100%',height:400,
				legend: { position: "top", maxLines:2, alignment:"end", textStyle: {fontSize: 9}},
				chartArea: { top:50, bottom:50, left:70, right:70,},
				tooltip: {trigger: 'selection'},
				explorer: { axis: 'vertical',maxZoomOut: 100 ,maxZoomIn: 1000 },
				hAxis: {
					format:format,
					textStyle: {color: '#000', fontName: 'Arial', fontSize: 10},
					maxTextLines:1,
				},
				vAxis: {
					title:'Change(mm)',
					titleTextStyle: {color: '#000',fontName: 'Arial',italic:false, fontSize: 10},
					viewWindow: {min: 최소치, max: 최대치},
					textStyle: {color: '#000', fontName: 'Arial', fontSize: 10},
				},
				series: {
					0: {color : 'blue', lineWidth  :1},
					1: {color : '#F2CB61', lineWidth  :1},
					2: {color : '#F2CB61', lineWidth  :1, visibleInLegend: false},
					3: {color : '#F29661', lineWidth  :1},
					4: {color : '#F29661', lineWidth  :1, visibleInLegend: false},
					5: {color : 'red', lineWidth  :1},
					6: {color : 'red', lineWidth  :1, visibleInLegend: false},
				},
			};
			var chart = new google.visualization.LineChart(document.getElementById('chart'));
			chart.draw(data, options);
		}
	</script>
	
	<script type='text/javascript'>
		$(document).ready(function(){
			$('.dateFilter').datepicker({
				dateFormat: "yy-mm-dd",
				showButtonPanel: true,
				currentText: '돌아가기',
				closeText: '닫기',
				nextText:'다음 달',
				revText:'이전 달',
				dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
				dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
				monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				changeMonth: true,
				changeYear: true,
				minDate: new Date(<?=$date_year?>,<?=$date_month?> - 1,<?=$date_day?>),
				maxDate: "0d",
				
				showMonthAfterYear: true
			});
		});
	</script>
	<script type="text/javascript"> 
// 		// F12 버튼 방지 
// 		$(document).ready(function(){ 
// 			$(document).bind('keydown',function(e){ 
// 				if ( e.keyCode == 123 /* F12 */) { 
// 					e.preventDefault(); 
// 					e.returnValue = false; 
// 				} 
// 			}); 
// 		}); 
// 		// 우측 클릭 방지 
// 		document.onmousedown=disableclick; 
// 		status="Right click is not available."; 
// 		function disableclick(event){ 
// 			if (event.button==2) { 
// 				alert(status); 
// 				return false; 
// 			} 
// 		} 
	</script>
</body>
</html>