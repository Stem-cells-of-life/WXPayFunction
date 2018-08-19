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
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
  </head>  
  <body>  
  <style>
		  body{
		        background:#2e8b57;
                height:100%;
                text-align:center;
		        
		  }

        #css_table {
        
         background:#EEEEEE;
              display: table;
			   margin: 0 auto;
                   }
       .css_tr {
          
          display: table-row;
         
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
         #checkbox{
             border:0px solid #000;

          }    
          .btn-submit{
          margin-top:10px;
          padding: 6px 30px;
          background: white;
          display: inline-block;
          border-radius: 5px;
          box-shadow: 1px 1px 6px 1px;
          }
    </style>  
<div class="wrapper">
  <div id="css_table_h">
     <div class="css_tr_ta" >
           <div class="css_th" id="checkbox">确认续费项目</div>
           <div class="css_th">协议号</div>
           <div class="css_th">协议类型</div>
           <div class="css_th">费用到期时间</div>
           <div class="css_th">上次冻存费</div>
           <div class="css_th">是否作废</div>
           <div class="css_th">缴费(元)</div>      
     </div>
   <c:forEach items="${feeList}" var="list" >
        <div class="css_tr">
           <div class="css_tr_th" id="checkbox">
                 <input type="checkbox" name="checkbox" /></p>
           </div>
           <div class="css_td" id="bs_agreement_0001" name="bs_agreement_0001">${list.bs_agreement_0001}</div>
           <div class="css_td">${list.xy_type}</div>
           <div class="css_td">${list.bs_fee_0002}</div>
           <div class="css_td">${list.dcf}</div>
           <div class="css_td">${list.isisvalid}</div>
           <div class="css_td" id="select">
           <select id="priceselect">
                      <option value="600">600(原价:一年)</option>
                      <option value="2910">2910(折扣93%:五年)</option>
                      <option value="5580">5580(折扣93%:十年)</option>
           </select>
           </div>
        </div>
     </c:forEach>
     
</div>
<div class="btn-submit" id="btnSubmit" >确定付款</div><br>  <button  >总计</button><br>
 <p id="allPrice" onclick="bind()">总计</p>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function bind() {
				var sum=0;
				var css_tr =$(".css_tr");  
				
				  //产品范围，所有的tr
//				css_tr_th=css_tr.document.getElementByClassName("css_tr_th")[0],      //选项标签
//				css_td = css_tr.document.getElementByClassName("css_tr_th")[0],               //获取页面的t
			   for(var i = 0; i < css_tr.length; i++) {  
			     //console.log(css_tr[i]); 
			     

			                     
				var a = $(css_tr[i]).children(".css_td").eq(3).html();         //获取tr项每一个td【1】的内容
				sum += Number(a);
				console.log("总计：",sum);
			}
			
			console.log("总计：",sum);
			var values=getCheckedValues();
			var sum=getTotalFee(values)
			
			$("#allPrice").html(sum);
			
}
//bind();
  //获取选中的 checkbox的值
	function getCheckedValues() {
         var arr = [];
         $("input[type='checkbox']:checked").each(function (index, item) {//
         
             arr.push({bs_agreement_0001:$(this).parent().siblings().eq(0).html(),
                       xy_type:$(this).parent().siblings().eq(1).html(), 
                       bs_fee_0002:$(this).parent().siblings().eq(2).html(), 
                       dcf:$(this).parent().siblings().eq(3).html(), 
                       isisvalid:$(this).parent().siblings().eq(4).html(), 
                       //priceselect:$(this).parent().siblings().eq(5).html()
                       priceselect:$(this).parent().siblings().eq(5).find("option:selected").val()
             })
            // arr.push($(this).parent().siblings().eq(3).html());
            
         });
         console.log(arr)
         return arr;
     }
     
     
     function getTotalFee(arr){
     	var sum=0;
     	for(var i=0;i<arr.length;i++){
     		sum+=Number(arr[i]);
     	}
     	return sum;     	
     }
     $("#btnSubmit").click(function(){
             ajaxSend();
     });
      function ajaxSend(){
			$.ajax({
				url:"http://localhost:8080/weixin/dcfcode.do",
				type:"post",
				data:{
					arr:JSON.stringify(getCheckedValues())
				},
				success:function(data){
					console.log(data)
				},
				error:function(err){
					console.log(err)
				}
			});
		}		
</script>



</html>