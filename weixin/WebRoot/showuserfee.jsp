<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
    <title></title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
   
  </head>  
  <body>    
    <table border="1" >
			<tr>
				<th>证件号码</th>
				<th>上回缴纳的冻存费</th>
				<th>客户姓名</th>
				<th>费用到期时间</th>
				<th>协议号</th>
				<th>类型</th>
				<th>协议主键</th>
				<th>是否作废</th>
			</tr>
		<tbody id="table">
		
		</tbody>
    </table>
    

    
  </body>  
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js" ></script>  
<script type="text/javascript">

</script>
</html>