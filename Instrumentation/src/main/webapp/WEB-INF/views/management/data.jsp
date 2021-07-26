<!-- 기본세팅 ~~ -->
<%@page import="org.springframework.web.bind.annotation.SessionAttributes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- ~~ 여기까지 -->
<html lang="ko" oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Data Manager</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="./css/main.css">
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	
	<script type='text/javascript'>
		$(document).ready(function(){
			$('.dateFilter').datepicker({
				dateFormat: "yymmdd",
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
				showMonthAfterYear: true
			});
		});
	</script>
	
	<style>
		.tb-container{
			height : 700px;
			overflow : auto
		}
		.fixedHeader {
			position: sticky;
			top:0;
		}
	</style>
</head>
<body style="margin-top:70px;">
<!-- navbar-fixed-top -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="color:orange">로거명</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class=""><a href="info?tb=로거명">INFO</a></li>
					<li class="active"><a href="data?tb=로거명">DATA</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="info페이지?tb=로거명&start=시작시간&end=끝시간"><span class="glyphicon glyphicon-time"></span> 현재시간표시</a>
					</li>
					<li><a href="로그아웃"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container-fluid">
		<form class="form-inline" method="GET" action="data.php" style="margin-top:10px;">
			<div class="form-group">
				<label class="" for="start" style="color:#999999;">START : </label>
				<input type="number" class="form-control dateFilter" id="start" name="start" placeholder="Start" value="시작시간(현날짜-7)">
				<label class="-" for="start-hour" style="color:#999999;">HOUR : </label>
				<input type="number" class="form-control" id="start-hour" name="start_h" min="00" max="23" placeholder="hour" value="시작시간(hour)">
				<label class="" for="start-min" style="color:#999999;">MIN : </label>
				<input type="number" class="form-control" id="start-min" name="start_m" min="00" max="59" placeholder="min" value="시작시간(minute)">
				&nbsp&nbsp&nbsp
				<label class="" for="end" style="color:#999999;">END : </label>
				<input type="number" class="form-control dateFilter" id="end"  name="end" placeholder="End" value="끝시간(현날짜)">
				<label class="" for="end-hour" style="color:#999999;">HOUR : </label>
				<input type="number" class="form-control" id="end-hour" name="end_h" min="00" max="23" placeholder="hour" value="끝시간(hour)">
				<label class="" for="end-min" style="color:#999999;">MIN : </label>
				<input type="number" class="form-control" id="end-min" name="end_m" min="00" max="59" placeholder="min" value="끝시간(minute)">
				<input type='hidden' name='tb' value='테이블명'>
			</div>
			<button type="submit" class="btn btn-default">조회</button>
		</form>
		<br>
		<div class="panel panel-primary ">
			<div class="panel-heading"></div>
			<div class="panel-body">
				<div class="table-responsive tb-container">
					<table class="table table-hover text-center table-bordered">
						<!-- 기존 -->
						<!-- <thead > 
							<tr>
							query문 "SELECT  COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='g2inet_daintech21' AND TABLE_NAME = '테이블이름'
								<th class="text-center fixedHeader" bgcolor="#eeeeee">컬럼명</th>
							</tr>
						</thead> -->
						<!-- 테스트용 -->
						<thead >
							<tr>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">u_idx</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">dtime</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">place</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">SG_1</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">CR_1</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">EL_1_C</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">EL_1_Y</th>
							</tr>
						</thead>
						<% String data = (String)request.getAttribute("data"); %>
						<%= data %>
						<tr>
							<td>데이터</td>
						</tr>
					</table>
				</div>
			</div><!-- panel body -->
			</div><!-- panel dain -->
		
		</div><!-- container -->
</body>
</html>