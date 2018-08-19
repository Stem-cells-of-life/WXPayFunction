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
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
  <style>
    body{
      background-color: rgb(46,139,87);
    }
    table{
    background: #fff;
    }
    </style>  
  </head>  
  <body>  
 
    <div class="table-responsive">
    <table class="table table-striped table-bordered table-hover table-condensed">
       <tr>
	       <th><input type="checkbox" name="checkbox" id="checkAll" />确认续费项目</th>
	       <th>协议号</th>
	       <th>协议类型</th>
	       <th>费用到期时间</th>
	       <th>上次冻存费</th>
	       <th>是否作废</th>
	       <th>缴费(元)</th>
       </tr>
       <c:forEach items="${feeList}" var="list" >
        <tr class="css_tr">   
           <td class="css_tr_th" id="checkbox"><input type="checkbox" name="checkbox" /> </td>
           <td class="css_td" id="bs_agreement_0001" name="bs_agreement_0001">${list.bs_agreement_0001}</td>
           <td class="css_td">${list.xy_type}</td>
           <td class="css_td">${list.bs_fee_0002}</td>
           <td class="css_td">${list.dcf}</td>
           <td class="css_td">${list.isisvalid}</td>
           <td class="css_td" id="select">
           <select id="priceselect">
                      <option value="600">600(原价:一年)</option>
                      <option value="2910">2910(折扣93%:五年)</option>
                      <option value="5580">5580(折扣93%:十年)</option>
           </select>
           </td>
        </tr>
     </c:forEach>
     <tr>
     <td>合计：</td>
     <td colspan="5"><span id="money">0元</span></td>
     </tr>
    </table>
    </div>

   
     
     
 
<button type="button" class="btn-submit btn btn-success" id="btnSubmit">确认付款</button>

<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

  //获取选中的 checkbox的值
	function getCheckedValues() {
         var arr = [];
         $("input[type='checkbox']:checked").not("#checkAll").each(function (index, item) {//
         
             arr.push({bs_agreement_0001:$(this).parent().siblings().eq(0).html(),
                       xy_type:$(this).parent().siblings().eq(1).html(), 
                       bs_fee_0002:$(this).parent().siblings().eq(2).html(), 
                       dcf:$(this).parent().siblings().eq(3).html(), 
                       isisvalid:$(this).parent().siblings().eq(4).html(), 
                       //priceselect:$(this).parent().siblings().eq(5).html()
                       priceselect:$(this).parent().siblings().eq(5).find("option:selected").val()
             })
            
         });
         console.log(arr)
         return arr;
     }
     
     $("#checkAll").on("click",function(){                //完成全选全不选
	     if($(this).prop("checked")==true){
	   	     $("input[type='checkbox']").not("#checkAll").each(function(){
		     	$(this).prop("checked",true)
		     })
		     getTotalFee( getCheckedValues()); 
		     
	     }else{
	 	     $("input[type='checkbox']").not("#checkAll").each(function(){
		     	$(this).prop("checked",false)
		     })
		     getTotalFee( getCheckedValues()); 
	     }
     })
     
     
     function getTotalFee(arr){                      //计算选中框的金额值
     	var sum=0;
     	for(var i=0;i<arr.length;i++){
     		sum+=Number(arr[i].priceselect);
     	}
     	console.log(sum);
     	$("#money").html(sum+"元");
     	return sum;     	
     }    
     
     $("input[type='checkbox']").not("#checkAll").click(function(){    //当全部选中后，确定全选
     var checkedBox=$("input[type='checkbox']").not("#checkAll");
     
     
     var len=checkedBox.length
     var arr=0;
     
     checkedBox.each(function(){
     if($(this).prop("checked")==true){
     	arr++
     }
     if(arr==len){
     $("#checkAll").prop("checked",true)
     }else{
       $("#checkAll").prop("checked",false)
     }

     
     })
     
          getTotalFee( getCheckedValues());         //绑定显示金额
     });
     
     
     $("#btnSubmit").click(function(){              //点击确认付款提交数据
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