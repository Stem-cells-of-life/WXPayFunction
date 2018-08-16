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
  <style>
        table,table tr th, table tr td { border:1px solid #0094ff; }
        table { width: 200px; min-height: 25px; line-height: 25px; text-align: center; border-collapse: collapse;}   
    </style>  
    <table>
		<tr>
			<th>agreement_id</th>
			<th>bs_agreement_0001</th>
			<th>xy_type<th>
			<th>bs_fee_0002<th>
			<th>dcf</th>
			<th>bs_agreement_0025</th>
			<th>bs_agreement_0034<th>
			<th>isisvalid<th>
		</tr>

		<c:forEach items="${feeList}" var="list" varStatus="">
              <tr>
				<td>${list.agreement_id}</td><td>${list.bs_agreement_0001}</td>
				<td>${list.xy_type}</td> <td>${list.bs_fee_0002}</td>
				<td>${list.dcf}</td><td>${list.bs_agreement_0025}</td>
				<td>${list.bs_agreement_0034}</td> <td>${list.isisvalid}</td>
			</tr>
		</c:forEach>
</table>
 

    

    
  </body>  
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js" ></script>  
<script type="text/javascript">

</script>
</html>