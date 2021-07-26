<!-- 기본세팅 ~~ -->
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
					<li class="active"><a href="info?tb=로거명">INFO</a></li>
					<li class=""><a href="data?tb=로거명">DATA</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="info?tb=로거명"><span class="glyphicon glyphicon-time"></span> 현재시간표시</a>
					</li>
					<li><a href="로그아웃"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container-fluid">
		<div class="panel panel-primary ">
		<div class="panel-heading"></div>
			<div class="panel-body">
				<div class="table-responsive tb-container">
					<table class="table table-hover text-center table-bordered">
						<thead >
							<tr>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">idx</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">logger_name</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_name</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_info</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_type</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_order</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_group</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_group_order</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">main_view_enable</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sensor_init</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">calculation1</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">calculation2</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">calculation3</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">calculation4</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">calculation5</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">calculation6</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">chk_sms</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">chk_cal_1</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">chk_cal_2</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">chk_cal_3</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">chk_cal_4</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">chk_cal_5</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">chk_cal_6</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">safe_value</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">data_count</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">manage_cal_index</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">safe_1</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">safe_2</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">safe_3</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_enddatetime</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_startdatetime</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">etc1</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">etc2</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">etc3</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">sen_data_type</th>
								<th class="text-center fixedHeader" bgcolor="#eeeeee">label_list</th>
							</tr>
						</thead>
							<% String info = (String)request.getAttribute("info"); %>
							<%= info %>
						<tr>
							<!-- query문 "SELECT * FROM place_set WHERE logger_name = '".$tb_name."' ORDER BY sen_order"-->
							<td nowrap>데이터 항목 내용</td>
						</tr>
					</table>
				</div>
			</div><!-- panel body -->
		</div><!-- panel dain -->
	</div><!-- container -->
</body>
</html>