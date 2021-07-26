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
  <title>login</title>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet" href="./css/login.css" type="text/css">
</head>
	<script>
		//jquery
		//enter클릭 시 로그인 버튼 클릭 기능
		$(document).ready( function() {
		  	$("#content").keydown(function(key) {
			  	if (key.keyCode == 13) {
		  			document.login.submit();
	  			}
			});
	 	});
	</script>

<body>
  <div class="wrapper">
    <div id="content">
      <h1>다인계측</h1>
      <p>자동화 계측 모니터링</p>
        <form method="post" action="login_proc">
            <input type="text" placeholder="id" name="user_id" id="login" value="${!empty login ? login : ''}">
            <input type="password" placeholder="password" name="user_pw" id="pw" value="${!empty pw ? pw : ''}">
        <input type="submit" class="fadeIn fourth" value="Log In">
      </form>
        <!--로그인된경우-->
        <!--<p><strong>유저명</strong>님은 이미 로그인하고 있습니다.
        <a href="/"><br>[돌아가기]</a>-->
    </div><!-- content -->
  </div><!-- wrapper --> 
</body>
</html>