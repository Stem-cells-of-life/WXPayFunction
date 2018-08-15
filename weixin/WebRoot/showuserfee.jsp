<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>        
    <title>My JSP 'index.jsp' starting page</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  

  </head>  
  <body>  
    This is my JSP page. <br>  
       <input type="button" value="点击获取结果" id="submit" >  
      
    <br>  
    result : 
    <div class="comment">
		返回数据：
		<p id="resultJsonText"></p>
	</div>  
    
    
    <table border="1" >
    	<c:forEach items="">
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
		</c:forEach>	
		<tbody id="table">
			
		</tbody>
    </table>
    

    
  </body>  
  <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
  	 //onclick="jsonAjaxPost()"
  //	function tempsort(){
  //var arr=[{name:"123",age:111},{name:456,age:4444}]
  //       var str='';
  //	    for(var i=0;i<arr.length;i++){
  //	    	str+="<tr><td>"+arr[i].name+"</td><td>"+arr[i].age+"</td></tr>";
  //	    }
  //    $("#table").html(str);
  //}
  //	 tempsort();
	      
		function jsonAjaxPost(){
            var str='';
             $.ajax({
               type:"post",//请求方式
               url:"http://localhost:8080/weixin/ShowUserFee",
               timeout:3000,//超时时间：3秒
               dataType:"json",//设置返回数据的格式
               //请求成功后的回调函数 data为json格式
               
               success:function(data){
     
                    
                for(var i=0,l=data.length;i<l;i++){
                    str+="<tr><td>"+data[i].BS_AGREEMENT_0034+"</td><td>"+data[i].DCF+"</td><td>"+data[i].BS_AGREEMENT_0025+"</td><td>"+data[i].BS_FEE_0002+"</td><td>"+data[i].BS_AGREEMENT_0001+"</td><td>"+data[i].XY_TYPE+"</td><td>"+data[i].AGREEMENT_ID+"</td><td>"+data[i].ISISVALID+"</td></tr>";                     
                         } 
                     
                   $("#table").html(str);
              },
              //请求出错的处理
              error:function(){
                        alert("请求出错");
              }
           });
          }
         // jsonAjaxPost(); 
	</script>
</html>