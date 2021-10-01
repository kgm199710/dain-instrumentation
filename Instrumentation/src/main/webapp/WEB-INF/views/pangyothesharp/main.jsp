<!-- 기본세팅 ~~ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- ~~ 여기까지 -->
<html lang="ko" oncontextmenu='return false' >
<head>
	<title>판교 더샵</title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 새로고침 -->
	<meta http-equiv="refresh" content="60"/>
	
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
							<a class="dropdown-item" href="#IPI-2">IPI2</a>
						</div>
					</li>
				</ul>
				<ul class="navbar-nav navbar-right">
					<li class="nav-item"><a  class="nav-link" href="logout"><i class="fas fa-sign-out-alt"></i> Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center" style="padding-bottom:15px;">
		<h2>판교더샵<!--  <small class="text-secondary">자동화계측</small> --></h2>
	</div><!-- header -->

	<div class="container">
		<div class="content">
			<div id="Location"></div>
				<div class="card mb-3">
					<div class="card-header bg-secondary text-white border border-secondary pb-1">
						<h5><i class="fas fa-map-marker-alt text-warning"></i>&nbsp&nbsp<small>계측기 설치위치</small></h5>
					</div><!-- card-header -->
					<div class="card-body border border-secondary">
						<img src="resources/img/main.png" alt="계측기 평면도" width="100%" />
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
<!-- 										mv	|?	         |현재수위(m)          |/|/|/  -->
										<tbody>
											<tr>
												<td class="text-center" bgcolor="#eeeeee">지하수위</td>
												<td class="px-0">db3번항목</td>
											</tr>
											<tr>
												<td class="text-center" bgcolor="#eeeeee">변화량</td>
												<td class="px-0">db3번항목 첫째값에서 현재 값 빼기</td>
											</tr>
											<tr>
												<td class="text-center" bgcolor="#eeeeee">일변화량</td>
												<td class="px-0">(당일0시데이터)-(전날23시데이터)</td>
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
<!-- 										mv      |현재각도	   | 변위(mm)	   |/|/|/ -->
										<tbody>
											<tr>
												<td class="text-center px-0" bgcolor="#eeeeee">최대변위</td>
												<td class="px-0">해당 ipi그룹중 db3번항목중 가장 큰값</td>
											</tr>
											<tr>
												<td class="text-center px-0" bgcolor="#eeeeee">최대변위지점</td>
												<td class="px-0">윗항목의 ipi번호</td>
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
<!-- 							수위계반복시작 -->
							<div class="col-md-6">
								<!--차트만드는 코드?-->
<%-- 								<div id = "차트명 아래 js와 연동해야됨" style="margin-bottom:10px;padding:1px;height:<?=$chart_height*1.5?>px; border: 1px solid #ccc;"></div> --%>
<!-- 								차트 height는 변수로 임의설정해야됨 -->
								<div id = "chart_1_w" style="margin-bottom:10px;padding:1px;height:331px; border: 1px solid #ccc;"></div>
<!-- 								여기까지삽입 -->
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
											<td>현재수위m</td>
											<td>변화랑m</td>
											<td>24시간변화량m</td>
											<td></td>
										</tr>
									</table>
								</div>
							</div>
<!-- 							수위계반복끝 -->
						</div>
					</div><!-- card-body -->
				</div><!-- card -->
			
<!-- 			ipi반복시작 -->
			<div id="IPI-2"></div>
			<div class="card mb-3">
				<div class="card-header bg-secondary text-white border border-secondary pb-1">
					<h5><i class="fas fa-chart-line text-warning"></i>&nbsp&nbsp<small>IPI</small></h5>
				</div>
				<div class="card-body border border-secondary">
					<div class="row">
						<div class="col-md-6">
<%-- 							<div id = "IPI차트명" style="margin-bottom:10px;padding:1px;height:<?=$chart_height*4?>px; border: 1px solid #ccc;"></div> --%>
							<div id = "chart_1_ipi" style="margin-bottom:10px;padding:1px;height:840px; border: 1px solid #ccc;"></div>
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
										<td><a href="IPI데이터웹페이지" role="button" target="_blank"><strong>IPI-2</strong></a></td>
										<td>깊이 m</td>
<!-- 										해당센서(최근값 - 최초값) + 해당센서 이전까지의 값(0m에서 시작) + existing(기변위)값 -->
										<td>누적변위 mm</td>
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
<!-- 			ipi반복끝 -->
		
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
	
	//옵션설정
	var options_w = {
		width:'100%',height:'100%',
		legend: { position: "top", maxLines:2, alignment:"end",textStyle: {fontSize: 9}},
		title: '지하수위계 24시간 변화 그래프',
		chartArea: {top:80, bottom:50, left:50, right:50},
		//가로줄(→)
		hAxis: {
			title:'Time',
			titleTextStyle: {color: '#000',fontName: 'Arial',italic:false, fontSize: 9},
			format:'yyyy-MM-dd HH:mm',
			textStyle: {color: '#000', fontName: 'Arial',fontSize: 9},
		},
		//세로줄(↑)
		vAxis: {
			title:'GL.m',
			textStyle: {color: '#000', fontName: 'Arial',fontSize: 9},
			titleTextStyle: {color: '#000',fontName: 'Arial',italic:false, fontSize: 9},
			//차트 노출범위 최솟값 최댓값
			viewWindow: {min: -50, max: 0},
		},
		//선 색상/보이는여부/폰트사이즈/선굵기
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
	//데이터입력 및 그리기 함수
	function drawChart_w(){
		//데이터입력
		var data_w1=new google.visualization.DataTable();
		data_w1.addColumn('datetime','시간');
		data_w1.addColumn('number','WL_01');
		data_w1.addColumn({type:'string',role:'annotation'});
		data_w1.addRows(
		    [
		        [new Date(2021,8,21,21,00,00),-19.7985848820432,'-19.80m'], //첫번째와 마지막값만 소수점2번째까지 출력
		        [new Date(2021,8,21,20,00,00),-19.8227963346402,null],
		        [new Date(2021,8,21,19,00,00),-19.8112670714988,null],
		        [new Date(2021,8,21,18,00,00),-19.8312030890141,null],
		        [new Date(2021,8,21,17,00,00),-19.7992093837967,null],
		        [new Date(2021,8,21,16,00,00),-19.8014992235595,null],
		        [new Date(2021,8,21,15,00,00),-19.8128843709117,null],
		        [new Date(2021,8,21,14,00,00),-19.8239972995507,null],
		        [new Date(2021,8,21,13,00,00),-19.7982005732719,null],
		        [new Date(2021,8,21,12,00,00),-19.8020116352547,null],
		        [new Date(2021,8,21,11,00,00),-19.8021317317458,null],
		        [new Date(2021,8,21,10,00,00),-19.800114110696,null],
		        [new Date(2021,8,21,09,00,00),-19.7923718902393,null],
		        [new Date(2021,8,21,08,00,00),-19.7991853644985,null],
		        [new Date(2021,8,21,07,00,00),-19.8081125370004,null],
		        [new Date(2021,8,21,06,00,00),-19.8140853358222,null],
		        [new Date(2021,8,21,05,00,00),-19.8172078445897,null],
		        [new Date(2021,8,21,04,00,00),-19.8321078159134,null],
		        [new Date(2021,8,21,03,00,00),-19.8142614773424,null],
		        [new Date(2021,8,21,02,00,00),-19.8131565896247,null],
		        [new Date(2021,8,21,01,00,00),-19.7826040423,null],
		        [new Date(2021,8,21,00,00,00),-19.7814991545823,null],
		        [new Date(2021,8,20,23,00,00),-19.7693614025529,null],
		        [new Date(2021,8,20,22,00,00),-19.7957586112871,'-19.80m']
		    ]
		);
		
		//차트그리기
		var chart_w1=new google.visualization.LineChart(document.getElementById('chart_1_w'));
		chart_w1.draw(data_w1,options_w);
		window.addEventListener('resize',drawChart_w,false);
	};


	//지중경사계 차트
	google.charts.setOnLoadCallback(drawChart_ipi);
	
	var option_i={
		width:'100%',
	    height:'100%',
	    legend:'none',
	    title:'지중경사계 그래프',
	    chartArea:{top:80,bottom:80,left:50,right:50},
	    orientation:'vertical',
	    hAxis:{
	        title:'displacement(mm)',
	        titleTextStyle:{color:'#000',fontName:'Arial',italic:false,fontSize:9},
	        textStyle:{color:'#000',fontName:'Arial',fontSize:9},
	        viewWindow:{min:-100,max:100}
	    },
	    vAxis:{
	        title:'depth(m)',
	        titleTextStyle:{color:'#000',fontName:'Arial',italic:false,fontSize:9},
	        textStyle:{color:'#000',fontName:'Arial',fontSize:9},
	        gridlines:{minSpacing:20}
	    },
	    series:{
	        0:{color:'blue',pointsVisible:true,pointSize:8,lineWidth:2}
	    },
	    dataOpacity:0.3,
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
    function drawChart_ipi(){
        var data_i1=new google.visualization.DataTable();
        data_i1.addColumn('number','depth(m)');
        data_i1.addColumn('number','change');
        data_i1.addColumn({type:'string',role:'annotation'});
        data_i1.addRows(
            [
//             	${test}
                [0,2.83,null],
                [-2,2.90,'max : 2.90mm'], //최대 변위만 표시할것
                [-4,1.95,null],
                [-6,0.24,null],
                [-8,1.01,null],
                [-10,1.32,null],
                [-12,1.66,null],
                [-14,1.34,null],
                [-16,0.04,null],
                [-18,0.92,null],
                [-20,1.07,null],
                [-22,0.52,null],
                [-24,0.42,null],
                [-26,0.28,null],
                [-28,0.20,null],
                [-30,-0.08,null],
                [-32,0.43,null],
                [-34,0.08,null],
                [-36,0,null]
            ]
        );
        var chart_i1=new google.visualization.LineChart(document.getElementById('chart_1_ipi'));
        chart_i1.draw(data_i1,option_i);
        window.addEventListener('resize',drawChart_ipi,false);}
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
