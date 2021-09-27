<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>${pageName} login</title>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- 	<link rel="stylesheet" href="./././resoursces/css/login.css" type="text/css"> -->
	<style>
		body {
			font-family: "Poppins", sans-serif;
			height: 100vh;
		}
		.wrapper {
			 display: flex;
			 align-items: center;
			 flex-direction: column;
			 justify-content: center;
			 background-color: #eee;
			 width: 100%;
			 min-height: 100%;
			 padding: 20px;
		}
		#content {
			border-radius: 10px 10px 10px 10px;
			background: #fff;
			padding: 30px;
			width: 90%;
			max-width: 450px;
			position: relative;
			box-shadow: 0 30px 50px 0 rgba(0,0,0,0.3);
			text-align: center;
		}
		input[type=button], input[type=submit], input[type=reset]  {
			background-color: #003964;
			border: none;
			color: white;
			padding: 15px 80px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			text-transform: uppercase;
			font-size: 13px;
			box-shadow: 0 10px 30px 0 rgba(95,186,233,0.4);
			border-radius: 5px 5px 5px 5px;
			margin: 5px 20px 0px 20px;
		}
		input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover  {
			background-color: #001729;
		}
		input[type=text], input[type=password]{
			background-color: #eee;
			border: none;
			color: #0d0d0d;
			padding: 15px 32px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin: 5px;
			width: 85%;
			border: 2px solid #eee;
			border-radius: 5px 5px 5px 5px;
		}
	</style>
</head>
<body>
	<div class="wrapper">
		<div id="content">
			<h2>${pageKorName}</h2>
			<h2>자동화계측</h2>
			<form method="post" action="login_proc">
				<input type="password" id="login" name="user_id" placeholder="id">
				<input type="password" id="login" name="user_pw" placeholder="password">
				<input type="submit" class="fadeIn fourth" value="Log In">
				<input type="hidden" name="pageName" value="${pageName}">
				<input type="hidden" name="pageKorName" value="${pageKorName}">
			</form>
		</div><!-- content -->
	</div><!-- wrapper --> 
</body>
</html>