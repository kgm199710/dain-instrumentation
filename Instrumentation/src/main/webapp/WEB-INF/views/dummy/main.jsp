<!-- 기본세팅 ~~ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- ~~ 여기까지 -->
<html lang="ko" oncontextmenu='return false' >
<head>
	<title>현장명</title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<meta http-equiv="refresh" content="600"/>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- 구글차트 -->
	<script type = "text/javascript" src = "https://www.gstatic.com/charts/loader.js"></script>
	<script type= "text/javascript">google.charts.load('current', {'packages':['corechart']});</script>
	
	<!-- 아이콘 -->
	<script type="text/javascript" src="../libs/font-awesome/5.15.1/js/all.min.js"></script>
	
	
	<style>
	body{
		font-size: 14px;
	}
	:target:before{
		content: "";
		display: block;
		height: 80px;          /* fixed header 높이 만큼 부여 or 브라우저 상단에서 띄워놓기 원하는 높이 */
		margin-top: -80px;     /* 위에서 설정한 높이와 동일한 만큼을 음수로 제공 */
		visibility: hidden;
	}
	</style>

</head>
<body style="margin-top:10px;"  data-spy="scroll" data-target=".navbar" data-offset="180">

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#" style="color:orange">Monitoring</a>
			<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#myNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">  
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="#Location">계측기 설치위치</a></li>
					<li class="nav-item"><a class="nav-link" href="#part1">계측 현황</a></li>
					
					<li class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
							계측기
						</a>
						<div class="dropdown-menu">
							<a  class="dropdown-item" href="#WL">WL</a>
							<a class="dropdown-item" href="#ipi명">ipi명</a>
						</div>
					</li>
				</ul>
				<ul class="navbar-nav navbar-right">
					<li class="nav-item"><a  class="nav-link" href="/"><i class="fas fa-history"></i>현재시간</a></li>
					<li class="nav-item"><a  class="nav-link" href="로그아웃"><i class="fas fa-sign-out-alt"></i> Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center" style="padding-bottom:15px;">
		<h2>(현장명) <small class="text-secondary">자동화계측</small></h2>
	</div><!-- header -->

	<div class="container">
		<div class="content">
			<div id="Location"></div>
				<div class="card mb-3">
					<div class="card-header bg-secondary text-white border border-secondary pb-1">
						<h5><i class="fas fa-map-marker-alt text-warning"></i>&nbsp&nbsp<small>계측기 설치위치</small></h5>
					</div><!-- card-header -->
					<div class="card-body border border-secondary">
						<img src="img/main.png" alt="계측기 평면도" width="100%" />
					</div><!-- card-body -->  
				</div><!-- location card -->
			<div id="part1"></div>
				<div class="card mb-3">
					<div class="card-header bg-secondary text-white border border-secondary pb-1">
						<h5><i class="fas fa-tasks text-warning"></i>&nbsp&nbsp<small>계측 현황</small></h5>
					</div>
					<div class="card-body border border-secondary">
					
						<div class="row">
							<div class="col-md-6">
								<div class="table-responsive">
									<table class="table text-center table-bordered">
										<thead>
											<tr bgcolor="#eeeeee">
												<th class="text-center">구 분</th>
												<th class="text-center">최초측정일</th>
												<th class="text-center">최근측정시간</th>
												<th class="text-center">비고</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td 정상이면 공란 데이터없으면bgcolor="#fcf8e3"><a href="./data/data_total?id=순서값&start=시작날짜&end=끝날짜" role="button"><strong>로거명</strong></a></td>
												<td 정상이면 공란 데이터없으면bgcolor="#fcf8e3">최초시작시간</td>
												<td 정상이면 공란 데이터없으면bgcolor="#fcf8e3">마지막기록된db시간</td>
												<td 정상이면 공란 데이터없으면bgcolor="#fcf8e3"></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						
							<div class="col-md-6 ">
								<div class="table-responsive">
									<table class="table text-center table-bordered">
										<thead>
											<tr bgcolor="#eeeeee">
												<th class="text-center">구분</th>
												<th class="text-center"><a href="#WL">수위계명</a></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="text-center" bgcolor="#eeeeee">지하수위</td>
												<td class="px-0">($val[$s[$i]][$sens_no_w[$i]]*100)/100 m</td>
											</tr>
											<tr>
												<td class="text-center" bgcolor="#eeeeee">변화량</td>
												<td class="px-0">($chg[$s[$i]][$sens_no_w[$i]]*100)/100 m</td>
											</tr>
											<tr>
												<td class="text-center" bgcolor="#eeeeee">일변화량</td>
												<td class="px-0">(($chart[$s[$i]][0][$sens_no_w[$i]]-$chart[$s[$i]][23][$sens_no_w[$i]])*100)/100 m</td>
											</tr>
										</tbody>
									</table>
								</div><!-- table-responsive -->
								
								<div class="table-responsive">
									<table class="table text-center table-bordered">
										<thead>
											<tr bgcolor="#eeeeee">
												<th class="text-center">구분</th>
												<th class="text-center"><a href="#IPI명">IPI명</a></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="text-center px-0" bgcolor="#eeeeee">최대변위</td>
												<td class="px-0">max($cum_chg[$i]) mm</td>
											</tr>
											<tr>
												<td class="text-center px-0" bgcolor="#eeeeee">최대변위지점</td>
												<td class="px-0">$depth[$i][array_search((max($cum_chg[$i])), $cum_chg[$i])] m</td>
											</tr>
										</tbody>
									</table>
								</div><!-- table-responsive -->
							</div>
						</div>
					
					</div><!-- card-body -->
				</div><!-- card -->
			
			
			<div id="WL"></div>
				<div class="card mb-3">
					<div class="card-header bg-secondary text-white border border-secondary pb-1">
						<h5><i class="fas fa-chart-line text-warning"></i>&nbsp&nbsp<small>지하수위</small></h5>
					</div>
					<div class="card-body border border-secondary">
						<div class="row">
							<div class="col-md-6">
								<!--차트만드는 코드?-->
								<!--<div id = "<?=$chart_no_w[$i]?>" style="margin-bottom:10px;padding:1px;height:<?=$chart_height*1.5?>px; border: 1px solid #ccc;"></div>-->
								<div class="table-responsive">
									<table class="table text-center table-bordered">
										<thead>
											<tr bgcolor="#eeeeee">
												<th class="text-center">식별코드</th>
												<th class="text-center">수위</th>
												<th class="text-center">변화량</th>
												<th class="text-center">24시간변화량</th>
												<th class="text-center">비고</th>
											</tr>
										</thead>
										<tr>
											<td><a href="수위계상세페이지" role="button" target="_blank"><strong>수위계명</strong></a></td>
											<td>현재수위m/td>
											<td>변화랑m</td>
											<td>24시간변화량m</td>
											<td></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div><!-- card-body -->
				</div><!-- card -->
			
			<div id="IPI명"></div>
			<div class="card mb-3">
				<div class="card-header bg-secondary text-white border border-secondary pb-1">
					<h5><i class="fas fa-chart-line text-warning"></i>&nbsp&nbsp<small>IPI명</small></h5>
				</div>
				<div class="card-body border border-secondary">
					<div class="row">
						<div class="col-md-6">
							<div id = "IPI차트명" style="margin-bottom:10px;padding:1px;height:<?=$chart_height*4?>px; border: 1px solid #ccc;"></div>
						</div>
							 
						<div class="col-md-6">
							<div class="table-responsive">
								<table class="table text-center table-bordered">
									<thead>
										<tr bgcolor="#eeeeee">
											<th class="text-center" colspan="4">
												<a href="IPI상세페이지" role="button" target="_blank">지중경사계</a>
											</th>
										</tr>
										<tr bgcolor="#eeeeee">
											<th class="text-center">식별코드</th>
											<th class="text-center">심도</th>
											<th class="text-center">변위</th>
											<th class="text-center">비고</th>
										</tr>
									</thead>
									<tr>
										<td><a href="IPI데이터웹페이지" role="button" target="_blank"><strong>IPI명</strong></a></td>
										<td>깊이 m</td>
										<td>변위 mm</td>
										<td></td>
									</tr>	
									            <!--참조-->
									<!-- <tr>
									     <td>-</td>
									     <td><?=$depth[$i][$a];?> m</td>
									     <td><?=$cum_chg[$i][$a]?> mm</td>
									     <td></td>
									 </tr>-->
								</table>
							</div>
						</div>
					</div>
				
				</div><!-- card-body -->
			</div><!-- card -->
		
		</div><!-- content -->
	
	</div><!-- container -->

	<div class="jumbotron text-center text-white bg-dark" style="margin-bottom:-70px; height:100px; padding:20px;">
		<div>
			<h5>(주)새길이엔시 | <small class="text-secondary">대표번호 (02)2191-0000</br>
			서울특별시 송파구 송파대로 201,테라타워2 A동 723호</small></h5>
		</div>
	</div><!-- footer -->
</body>

<script type= "text/javascript">
// 지하수위계 차트 
	google.charts.setOnLoadCallback(drawChart_w);
	var options_w = {
		width:'100%',height:'100%',
		legend: { position: "top", maxLines:2, alignment:"end",textStyle: {fontSize: 9}},
		title: '지하수위계 24시간 변화 그래프',
		chartArea: {top:80, bottom:50, left:50, right:50},
		hAxis: {
			title:'Time',
			titleTextStyle: {color: '#000',fontName: 'Arial',italic:false, fontSize: 9},
			format:'yyyy-MM-dd HH:mm',
			textStyle: {color: '#000', fontName: 'Arial',fontSize: 9},
		},
		vAxis: {
			title:'GL.m',
			textStyle: {color: '#000', fontName: 'Arial',fontSize: 9},
			titleTextStyle: {color: '#000',fontName: 'Arial',italic:false, fontSize: 9},
			viewWindow: {min: <?=min($chart_range)?>, max: <?=max($chart_range)?>},
		},
		series: {
			0: {color : 'blue', pointsVisible : true, pointSize:8,lineWidth  :2},
			1: {color : '#F2CB61', lineWidth  :2, visibleInLegend: false},
			2: {color : '#F2CB61', lineWidth  :2, visibleInLegend: false},
			3: {color : '#F29661', lineWidth  :2, visibleInLegend: false},
			4: {color : '#F29661', lineWidth  :2, visibleInLegend: false},
			5: {color : 'red', lineWidth  :2, visibleInLegend: false},
			6: {color : 'red', lineWidth  :2, visibleInLegend: false},
		},
		dataOpacity: 0.3,
		annotations: {
			boxStyle: {
				// Color of the box outline.
				stroke: '#888',
				// Thickness of the box outline.
				strokeWidth: 1,
				// x-radius of the corner curvature.
				rx: 3,
				// y-radius of the corner curvature.
				ry: 3,
				gradient: {
					color1: 'white',
					// Finish color for gradient.
					color2: 'white',
				},
			},
		}
	};
	function drawChart_w() {

		var chart_w1 = new google.visualization.LineChart(document.getElementById('chart_1_w'));
		var chart_w2 = new google.visualization.LineChart(document.getElementById('chart_2_w'));
		var chart_w3 = new google.visualization.LineChart(document.getElementById('chart_3_w'));
		var chart_w5 = new google.visualization.LineChart(document.getElementById('chart_5_w'));
		
		chart_w1.draw(data_w1, options_w);
		chart_w2.draw(data_w2, options_w);
		chart_w3.draw(data_w3, options_w);
		chart_w5.draw(data_w5, options_w);
		
		window.addEventListener('resize',drawChart_w,false);
	};


	//지중경사계 차트
	google.charts.setOnLoadCallback(drawChart_ipi);
	
	var 옵션명 = {
		width:'100%',height:'100%',
		legend: 'none',
		title: '지중경사계 그래프',
		chartArea: {top:80, bottom:80, left:50, right:50},
		orientation: 'vertical',
		hAxis: {
			title:'displacement(mm)',
			titleTextStyle: {color: '#000',fontName: 'Arial',italic:false, fontSize: 9},
			textStyle: {color: '#000', fontName: 'Arial',fontSize: 9},
			viewWindow: {min: 최소값, max: 최대값},
		},
		vAxis: {
			title:'depth(m)',
			titleTextStyle: {color: '#000',fontName: 'Arial',italic:false, fontSize: 9},
			textStyle: {color: '#000', fontName: 'Arial',fontSize: 9},
			gridlines: {minSpacing: 최소공란값,},
		},
		series: {
			0: {color : 'blue',pointsVisible : true, pointSize:8,lineWidth  :2},
		},
		dataOpacity: 0.3,
		annotations: {
			boxStyle: {
				// Color of the box outline.
				stroke: '#888',
				// Thickness of the box outline.
				strokeWidth: 1,
				// x-radius of the corner curvature.
				rx: 3,
				// y-radius of the corner curvature.
				ry: 3,
				gradient: {
					color1: 'white',
					// Finish color for gradient.
					color2: 'white',
				},
			},
		}
	};
	function drawChart_ipi() {
		var data_i1 = new google.visualization.DataTable();
		var data_i2 = new google.visualization.DataTable();
		var data_i3 = new google.visualization.DataTable();
		var data_i4 = new google.visualization.DataTable();
		var data_i5 = new google.visualization.DataTable();
		    
		var chart_i1 = new google.visualization.LineChart(document.getElementById('chart_1_ipi'));
		var chart_i2 = new google.visualization.LineChart(document.getElementById('chart_2_ipi'));
		var chart_i3 = new google.visualization.LineChart(document.getElementById('chart_3_ipi'));
		var chart_i4 = new google.visualization.LineChart(document.getElementById('chart_4_ipi'));
		var chart_i5 = new google.visualization.LineChart(document.getElementById('chart_5_ipi'));
		
		chart_i1.draw(data_i1, option_i1);
		chart_i2.draw(data_i2, option_i2);
		chart_i3.draw(data_i3, option_i3);
		chart_i4.draw(data_i4, option_i4);
		chart_i5.draw(data_i5, option_i5);
		window.addEventListener('resize',drawChart_ipi,false);
	}
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


</html>
