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
	String start = (String)request.getAttribute("start");
	String end = (String)request.getAttribute("end");

	String[][] ipiDatas = (String[][])request.getAttribute("ipiDatas");
	String ipiName = (String)request.getAttribute("ipiName");
	String ipiGroupKorName = (String)request.getAttribute("ipiGroupKorName");
	
	%>
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
            document.getElementById('<%=ipiName%>').className += ' show'
	        document.getElementById('<%=ipiName%>_data').className += ' active'
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
						<form method='GET' action= 'data_total_ipi' class="d-md-inline-block form-inline ml-3 mr-3 mr-md-3 my-0 my-md-0">
							<input type="hidden" name="id" value="<%=id%>">
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
				<div class="py-3 px-3 my-3 mx-3">
                        <div style="padding-left:20px;">
                            <h1><%=ipiGroupKorName %><br></h1>
                        </div>

                        <div class="info">
                            <h3><p>현장명 : <%=pageKorName %></p>
                            검색기간 : <%=start%> ~  <%=end %></h3>
                        </div>
                        <table>
						<%	for(int i=0; i<ipiDatas.length; i++){ %>
							<tr>
						<% 		for(int j=0; j<ipiDatas[i].length; j++){%>
								<td><%= ipiDatas[i][j] %></td>
  						<%		} %>
  							</tr>
						<% 	} %>
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
// 				minDate: new Date( (연도),(월) - 1,(일) ), //검색가능 최소 일자(최초데이터값일자)
				minDate: new Date(2021,5 - 1, 31),
				maxDate: "0d",
				
				showMonthAfterYear: true
			});
		});
	</script>
	<script type="text/javascript">
		function excelDown() { 
	        var html = ''; 
	        html += '<html xmlns:x="urn:schemas-microsoft-com:office:excel">'; 
	        html += ' <head>'; 
	        html += ' <meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">'; 
	        html += ' <xml>'; 
	        html += ' <x:ExcelWorkbook>'; 
	        html += ' <x:ExcelWorksheets>'; 
	        html += ' <x:ExcelWorksheet>';
	        html += ' <x:Name><%= ipiName  + "_" + start + "_" + end%></x:Name>'; // 시트이름 
	        html += ' <x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions>'; 
	        html += ' </x:ExcelWorksheet>'; 
	        html += ' </x:ExcelWorksheets>'; 
	        html += ' </x:ExcelWorkbook>'; 
	        html += ' </xml>'; 
	        html += ' </head>'; 
	        html += ' <body>'; 
	        // ----------------- 시트 내용 부분 ----------------- 
	        html += ' <table>'
	        html += ' <tr><td colspan="4">현장명 : <%=pageKorName%></td></tr>';
	        html += ' <tr><td colspan="4">검색기간 : <%=start%> ~  <%=end %></td></tr>';
			html += ' <tr>';
				<% 		for(int j=0; j<ipiDatas[0].length; j++){%>
			html += 	'<td><%= ipiDatas[0][j] %></td>'; <%		} %>
			html += ' </tr>';
				<%	for(int i=ipiDatas.length-1; i>0; i--){ %>
			html += ' <tr>';
				<% 		for(int j=0; j<ipiDatas[i].length; j++){%>
			html += 	'<td><%= ipiDatas[i][j] %></td>'; <%		} %>
			html += ' </tr>'; <% 	} %>
			html += ' </table>';
	        // ----------------- //시트 내용 부분 ----------------- 
	        html += ' </body>'; 
	        html += ' </html>'; 
	        // 데이터 타입 
	        var data_type = 'data:application/vnd.ms-excel';
	        var ua = window.navigator.userAgent;
	        var blob = new Blob([html], {type: "application/csv;charset=utf-8;"}); 
	        if ((ua.indexOf("MSIE ") > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) && window.navigator.msSaveBlob) { 
	            // ie이고 msSaveBlob 기능을 지원하는 경우 
	            navigator.msSaveBlob(blob, fileName); 
	        } else { 
	            // ie가 아닌 경우 (바로 다운이 되지 않기 때문에 클릭 버튼을 만들어 클릭을 임의로 수행하도록 처리) 
	            var anchor = window.document.createElement('a'); 
	            anchor.href = window.URL.createObjectURL(blob); 
	            anchor.download = '<%= ipiName + "_" + start + "_" + end%>.xls'; 
	            document.body.appendChild(anchor); 
	            anchor.click(); 
	
	            // 클릭(다운) 후 요소 제거 
	            document.body.removeChild(anchor); 
	        } 
	    }
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