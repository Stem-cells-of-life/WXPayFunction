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
        #css_table {
          display:table;
                   }
       .css_tr {
          
          display: table-row;
         border:1px solid #000;
               }
        .css_th {
           
           display: table-cell;
           border:1px solid #000;
           padding: 10px;
                }           
        .css_td {
           
           display: table-cell;
           border:1px solid #000;
           padding: 10px;
                }   
    </style>  
  <div id="css_table">
     <div class="css_tr">
           <div class="css_th">协议号</div>
           <div class="css_th">协议类型</div>
           <div class="css_th">费用到期时间</div>
           <div class="css_th">上次冻存费</div>
           <div class="css_th">是否作废</div>
           <div class="css_th">缴费</div>
     </div>
   <c:forEach items="${feeList}" var="list" varStatus="">
        <div class="css_tr">
           <div class="css_td">${list.bs_agreement_0001}</div>
           <div class="css_td">${list.xy_type}</div>
           <div class="css_td">${list.bs_fee_0002}</div>
           <div class="css_td">${list.dcf}</div>
           <div class="css_td">${list.isisvalid}</div>
           <div class="css_td">单价600/年</div>
        </div>
     </c:forEach>
   </div>
 

    

    
  </body>  
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js" ></script>  
<script type="text/javascript">

</script>
</html>