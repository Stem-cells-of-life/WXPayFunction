<span style="font-size:18px;"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>My JSP 'index.jsp' starting page</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <!-- 
    <link rel="stylesheet" type="text/css" href="styles.css"> 
    -->  
    
   
  </head>  
  <body>  
    This is my JSP page. <br>  
       <input type="button" value="点击获取结果" id="submit" onclick="jsonAjaxPost()">  
    <br>  
    result : 
    <div class="comment">
		返回数据：
		<p id="resultJsonText"></p>
	</div>  
    
    
    <table border="1" >
		<tr>
			<th>姓名</th>
			<th>身份证</th>
		</tr>
		<tbody id="table">
		
		</tbody>
    </table>
    

    
  </body>  
  <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js" ></script>  
  	<script type="text/javascript">
  	function tempsort(){
  	var arr=[{name:"123",age:111},{name:456,age:4444}]
  	        var str='';
  	    for(var i=0;i<arr.length;i++){
  	    	str+="<tr><td>"+arr[i].name+"</td><td>"+arr[i].age+"</td></tr>";
  	    }
  	    $("#table").html(str);
  	}
  	 tempsort();
	      
	      
	      
	       function jsonAjaxPost(){
             
             $.ajax({
               type:"post",//请求方式
               url:"http://localhost:8080/weixin/ShowUserFee",
               timeout:3000,//超时时间：3秒
               dataType:"json",//设置返回数据的格式
               //请求成功后的回调函数 data为json格式
               
               success:function(data){
     
              
                for(var i=0,l=data.length;i<l;i++){
                       
                       console.log( data[i].BS_AGREEMENT_0034);
                       console.log(data[i].DCF);
                       console.log(data[i].BS_AGREEMENT_0025);
                       console.log(data[i].BS_FEE_0002);
                       
                       
                } 
                     
                  $("#resultJsonText").text(
                     
                   
                     
                     
                  
                  );
              },
              //请求出错的处理
              error:function(){
                        alert("请求出错");
              }
           });
          }
	</script>
</html>