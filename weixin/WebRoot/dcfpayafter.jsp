<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="screen-orientation" content="portrait">
	<meta name="x5-orientation" content="portrait">
	<meta name="full-screen" content="yes">
	<meta name="x5-fullscreen" content="true">
	<meta name="browsermode" content="application">
	<meta name="x5-page-mode" content="app">
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="format-detection" content="telephone=no, email=no" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<style type="text/css">
		html,body{
			height:100%;
			width: 100%;
		}
		html,body,div,span,p,h1,h2,h3{
			margin: 0;
			padding: 0;
	   }
	   div{
	   		padding-top: 10vh;
	   		text-align: center;
	   }
	   div>h1{
	   		margin:50px auto;
	   }
	   div>img{
	   		width: 50%;
	   		height:50%;
	   		margin:0 auto;
	   }
	   div>button{
		   	display: block;
		   	margin:10vh auto;
		   	font-size: 30px;
	   }
	</style>
<title></title>
</head>
<body>
	<div id="success">
		<h1>谢谢您的支持!</h1>
		<img src="img/thank.jpg">
		<button onclick="backshouye()">返回首页</button>
	</div>
	<div id="cancel">
		<h1>请大人再考虑考虑吧</h1>
		<img src="img/thank.jpg">
		<button onclick="backshouye()">返回首页</button>
	</div>
	<div id="fail">
		<h1>支付失败</h1>
		<img src="img/shuini.jpg">
		<button onclick="backshouye()">返回首页</button>
	</div>
		<div>
				<input type="hidden" id="state" name="state" value="${state }">
		</div>		
</body>
<script type="text/javascript">
	window.onload=function (){
			var success = document.getElementById("success");
			var cancel = document.getElementById("cancel");
			var fail = document.getElementById("fail");
			success.style.display="none";
			cancel.style.display="none";
			fail.style.display="none";
			var state = document.getElementById("state");
			if(state.value=="01"){
						success.style.display="block";
			}
			if(state.value=="02"){
						cancel.style.display="block";
			}
			if(state.value=="03"){
						cancel.style.display="block";
			}
	}
	function backshouye(){
					window.location.href="http://203624vk44.iok.la/weixin/shouye.html?a="+Math.floor(Math.random()*10000);
			}
</script>
</html>
