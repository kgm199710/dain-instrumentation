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
	<link href="../css/styles.css" rel="stylesheet" />
	<script type="text/javascript" src="../../libs/font-awesome/5.15.1/js/all.min.js"></script>
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	
	<script type = "text/javascript" src = "https://www.gstatic.com/charts/loader.js"></script>
	<script>
		window.onload = function() {
		document.getElementById('collapseIPI').className += ' show'
		document.getElementById('show_id').className += ' show'
		document.getElementById('select_id').className += ' active'
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
		<a class="navbar-brand" href="../main.php?start=시작날짜&end=시작날짜">현장명</a>
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
						<form method='GET' action= 'data.php' class="d-md-inline-block form-inline ml-3 mr-3 mr-md-3 my-0 my-md-0">
							<div class="form-group my-2 btn-block">
								<label for="start" >시작일 : </label>
								<input type="text" class="dateFilter form-control-sm btn-block" id="start" name="start" placeholder="Start" value="시작날짜">
							</div>
							
							<div class="form-group my-2 btn-block">
								<label for="start" >종료일 : </label>
								<input type="text" class="dateFilter form-control-sm btn-block" id="end" name="end" placeholder="End" value="현날짜">
							</div>
							
							<div class="form-check my-2">
								<input class="form-check-input" id="avg_chk" type="checkbox" name="chkAvg" value="chk" >
								<label class="form-check-label" for="avg_chk">
									일평균데이터 보기
								</label>
							</div>
							<input type='hidden' name='select_id' value='select_id'>
							<input type='hidden' name='id' value='id'>
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
						
						<jsp:include page="sidebar.jsp" flush="true"></jsp:include>
					</div>
				</div>
				<div class="sb-sidenav-footer"></div>
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
	<script src="../../libs/js/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
	<script src="../../libs/js/bootstrap-4.5.3.bundle.min.js" crossorigin="anonymous"></script>
	<script src="scripts.js"></script>
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
		// F12 버튼 방지 
		$(document).ready(function(){ 
			$(document).bind('keydown',function(e){ 
				if ( e.keyCode == 123 /* F12 */) { 
					e.preventDefault(); 
					e.returnValue = false; 
				} 
			}); 
		}); 
		// 우측 클릭 방지 
		document.onmousedown=disableclick; 
		status="Right click is not available."; 
		function disableclick(event){ 
			if (event.button==2) { 
				alert(status); 
				return false; 
			} 
		} 
	</script>
</body>
</html>
