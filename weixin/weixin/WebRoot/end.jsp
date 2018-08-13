<%@ page language="java" 
					 contentType="text/html; charset=utf-8"
    				 pageEncoding="utf-8"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图片信息</title>
<style>
				img {
					width: auto;
					height: auto;
				}
</style>
</head>
<body>
	    <%
	    		String photo = (String)request.getAttribute("photo");
	     %>
		<img alt="环境检测报告" src="img/<%=photo%>">
</body>
</html>