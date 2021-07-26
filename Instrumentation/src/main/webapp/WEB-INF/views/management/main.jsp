<!-- 기본세팅 ~~ -->
<%@page import="org.springframework.web.bind.annotation.SessionAttributes"%>
<%@page import="java.util.ArrayList"%>
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
</head>
<body style="margin-top:70px;">
	<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fruid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" style="color:orange">Monitoring</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
						<%=request.getAttribute("topNav") %>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">
<!-- 				<div class="panel panel-primary "> -->
<!-- 					<div class="panel-heading"> -->
<!-- 						<span class="glyphicon glyphicon-tags"></span>업체명 -->
<!-- 					</div> -->
<!-- 					<div class="panel-body"> -->
<!-- 						<h5 class id="현장명"> -->
<!-- 							<span class="glyphicon glyphicon-stats"></span>현장명 -->
<!-- 						</h5 class> -->
<!-- 						<div class="table-responsive"> -->
<!-- 							<table class="table table-hover text-center table-bordered"> -->
<!-- 								<thead> -->
<!-- 									<tr bgcolor="#eeeeee"> -->
<!-- 										<th class="col-xs-2 text-center">현장명</th> -->
<!-- 										<th class="col-xs-2 text-center">구분</th> -->
<!-- 										<th class="col-xs-2 text-center">최초</th> -->
<!-- 										<th class="col-xs-2 text-center">최근</th> -->
<!-- 										<th class="col-xs-2 text-center">사이트</th> -->
<!-- 										<th class="col-xs-2 text-center">정보</th> -->
<!-- 									</tr> -->
<!-- 								</thead> -->
<!-- 								<tr> -->
<!-- 									<td rowspan="현장에 로거갯수">현장명</td> -->
<!-- 									<td class="정상이면 공란, 데이터없으면 bg-warning">로거구분</td> -->
<!-- 									<td class="정상이면 공란, 데이터없으면 bg-warning">설치일자</td> -->
<!-- 									<td class="정상이면 공란, 데이터없으면 bg-warning">최근업데이트일자</td> -->
<!-- 									<td class="정상이면 공란, 데이터없으면 bg-warning"> -->
<!-- 										<a class="btn btn-primary btn-xs" href="첫번째로거일때사이트이동버튼활성화및링크" target= "_blank" role="button"> -->
<!-- 											Site &raquo; -->
<!-- 										</a> -->
<!-- 									</td> -->
<!-- 									<td class="정상이면 공란, 데이터없으면 bg-warning"> -->
<!-- 										<a class="btn btn-info btn-xs" href="info?해당로거" target= "_blank" role="button">info &raquo;</a> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 							</table> -->
<!-- 						</div> -->
<!-- 					</div>panel body -->
<!-- 				</div>panel dain -->
				<%=request.getAttribute("mainCont") %>
			</div><!-- col-lg-10 -->
			<div class="col-lg-1"></div>
		</div><!-- row -->
	</div><!-- container -->
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-dialog-scrollable">
		
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header bg-warning">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title">Management Notification</h4>
				</div>
				<div class="modal-body">
					<%=request.getAttribute("modalCont") %>
					
					<!-- 수신 지연내역 이후 구현 예정 -->
<!-- 					<h2 class="text-danger">데이터 수신 지연 '데이터수신지연되는로거숫자' 시스템 발생</h2> -->
<!-- 					<div id="demo" class="collapse"> -->
<!-- 					  데이터수신 지연되는 로거들 -->
<!-- 					</div> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo">자세히 보기</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		
		</div>
	</div>
	
	<!-- modal영역 -->
	<script>
		$(document).ready(function(){
			$("#myModal").modal('show');
		});
	</script>

</body>
</html>