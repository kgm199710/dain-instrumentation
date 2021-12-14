<!-- 기본세팅 ~~ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- ~~ 여기까지 -->
<!--개별ipi전용 페이지-->
<html lang="ko" oncontextmenu='return false' >
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>사이트명</title>
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
		<a class="navbar-brand" href="pangyothesharp">현장명</a>
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
						<form method='GET' action= 'data' class="d-md-inline-block form-inline ml-3 mr-3 mr-md-3 my-0 my-md-0">
							<div class="form-group my-2 btn-block">
								<label for="start" >시작일 : </label>
								<input type="text" class="dateFilter form-control-sm btn-block" id="start" name="start" placeholder="Start" value="시작날짜(jsp나 js에서 날자 연산후 출력)">
							</div>
							<div class="form-group my-2 btn-block">
								<label for="start" >종료일 : </label>
								<input type="text" class="dateFilter form-control-sm btn-block" id="end" name="end" placeholder="End" value="현날짜(jsp나 js에서 날자 연산후 출력)">
							</div>
							<div class="form-check my-2">
								<input class="form-check-input" id="avg_chk" type="checkbox" name="chkAvg" value="chk" >
								<label class="form-check-label" for="avg_chk">
									일평균데이터 보기
								</label>
							</div>
							<!-- 용도불명? -->
							<input type='hidden' name='select_id' value='select_id'> <!-- 현재 선택된 센서명(ex-WL_02나 IPI_11_16_Y등) -->
							<!-- 용도불명? -->
							<input type='hidden' name='id' value='id'><!-- ipi그룹 순서? -->
							<div class="form-group my-2 btn-block">
								<button type="submit" class="btn btn-sm btn-primary btn-block">
									<i class="fa fa-search text-white-50"></i>
									조회하기
								</button>
							</div>
						</form>
					
						<form method='GET' action= 'excel.php' class="d-md-inline-block form-inline ml-3 mr-3 mr-md-3 my-0 my-md-0">
							<input type='hidden' name='title' value='site'>
							<input type='hidden' name='select_id' value='select_id'>
							<input type='hidden' name='id' value='id'>
							<input type='hidden' name='start' value='start'>
							<input type='hidden' name='end' value='end'>
							<input type='hidden' name='chkAvg' value='chkAvg'>
							<div class="form-group my-2 btn-block">
								<button type="submit"class="btn btn-sm btn-primary btn-block">
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
						    	<!-- 시스템갯수만큼반복 -->
						        <a class="nav-link my-0 py-1" href="data_total?id=(순서번호)&start=(시작날짜)&end=(끝날짜)">
						            <i class="fas fa-tag fa-sm text-white-50"></i>
						            &nbsp n번째 시스템
					            </a>
					            <!-- 여기까지 -->
						    </nav>
						</div>
						
						<a class="nav-link collapsed" id="W" href="#" data-toggle="collapse" data-target="#collapseW" aria-expanded="false" aria-controls="collapseW" style="width:190px;">
						    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
						    지하수위계
						    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						</a>
						<div class="collapse" id="collapseW" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
						
						    <nav class="sb-sidenav-menu-nested nav">
						    	<!-- 시스템갯수만큼반복 -->
						        <a class="nav-link my-0 py-1" id="(센서번호-WL_01)" href="data_w?id=(순서번호)&chkAvg=(일평균체크여부)&select_id=(센서명)&start=(시작날자)&end=(끝날자)">
						            <i class="fas fa-tag fa-sm text-white-50"></i>
						            dummy-wl
					            </a>
					            <!-- 여기까지 -->
						    </nav>
						</div>
						
						
						<a class="nav-link collapsed" id="IPI" href="#" data-toggle="collapse" data-target="#collapseIPI" aria-expanded="false" aria-controls="collapseIPI" style="width:190px;">
						    <div class="sb-nav-link-icon"><i class="fas fa-tags fa-sm text-white-50"></i></div>
						    지중경사계
						    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						</a>
						<div class="collapse" id="collapseIPI" aria-labelledby="headingTwo" data-parent="#sidenavAccordion" style="width:190px;">
						    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
						        <a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#(ipi그룹이름-구멍이름)" aria-expanded="false" aria-controls="(ipi그룹이름-구멍이름)">
						            <i class="fas fa-tags fa-sm text-white-50"></i>
						            dummy-ipi
						            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						        </a>
						        <div class="collapse" id="(ipi그룹이름-구멍이름)" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
						            <nav class="sb-sidenav-menu-nested nav">
						                <a class="nav-link my-0 py-1" id="(ipi그룹이름-구멍이름)_chart" href="data_all_ipi?id=(번호)&start=(시작날자)&end=(끝날자)">누적그래프</a>
						                <a class="nav-link my-0 py-1" id="(ipi그룹이름-구멍이름)_data" href="data_total_ipi?id=(번호)&start=(시작날자)&end=(끝날자)">누적데이터</a>
						                <a class="nav-link my-0 py-1" id="(ipi센서명)" href="data_ipi?id=(번호)&select_id=<?=$ipi_name[$i][$c]?>&chkAvg=<?=$chkAvg?>&start=<?=$start?>&end=(끝날자)">(ipi센서명)</a>
						            </nav>
						        </div>
								<a class="nav-link collapsed  my-0 py-1" href="#" data-toggle="collapse" data-target="#ipi그룹이름-구멍이름)" aria-expanded="false" aria-controls="(ipi그룹이름-구멍이름)">
									<i class="fas fa-tags fa-sm text-white-50"></i>
									&nbsp ipi그룹이름-구멍이름
									<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
								</a>
								<div class="collapse" id="(ipi그룹이름-구멍이름)" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
									<nav class="sb-sidenav-menu-nested nav">
										<a class="nav-link my-0 py-1" id="(ipi그룹이름-구멍이름)_chart" href="data_all_ipi?id=(순서번호)&start=(시작날자)&end=(끝날자)">누적그래프</a>
										<a class="nav-link my-0 py-1" id="(ipi그룹이름-구멍이름)_data" href="data_total_ipi?id=(순서번호)&start=(시작날자)&end=(끝날자)">누적데이터</a>
										<a class="nav-link my-0 py-1" id="(ipi센서명)" href="data_ipi?id=(순서번호)&select_id=(센서명)&chkAvg=(일평균체크여부)&start=(시작날자)&end=(끝날자)">센서명</a>
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