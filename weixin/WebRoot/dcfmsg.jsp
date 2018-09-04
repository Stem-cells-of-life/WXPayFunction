<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>dcfmsg</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		html,body,div,table,th,tr,td{
			margin: 0;
			padding: 0;
		}
		 table,table tr th, table tr td { 
		 	border:1px solid #0094ff; 
		 	font-size: 50px;
		 }
		
		div{
			text-align: center;
		}
		
		button {
			font-size: 50px;	
		}

		table{
			text-align:center;
			font-size: 50px;
		}
		.tou{
			margin: 10px;
			font-size: 60px;
		}
		.paymsg{
			margin: 10px;
		}
	</style>
  </head>
  <body>
  		<div class="tou">
  			<h1>请确认缴费信息</h1>
  		</div>
  		<div class="paymsg">
			<table align="center">
				<tr>
					<th style="display: none;">id</th>
					<th>协议号</th>
					<th>缴费年限</th>
					<th>到期时间</th>
					<th>缴费(元)</th>
				</tr>
				<c:forEach items="${arrList}" var="list">
					<tr class="css_tr">
						<td class="css_td" style="display: none;">${list.id }</td>
						<td class="css_td" id="bs_agreement_0001" name="bs_agreement_0001">${list.bs_agreement_0001}</td>
						<td class="css_td">${list.payyear}</td>
						<td class="css_td">${list.bs_fee_0002}</td>
						<td class="css_td">${list.priceselect}</td>
					</tr>
				</c:forEach>
				<tr>
					<td>合计：</td>
					<td colspan="5"><span id="money">${total}</span>
					</td>
				</tr>
			</table>
		</div>
		<div class="footer">
			<input type="hidden" name="appId" value="${appId}">
			<input type="hidden" name="nonceStr" value="${nonceStr}">
			<input type="hidden" name="prepayId" value="${prepayId}">
			<input type="hidden" name="paySign" value="${paySign}">
			<input type="hidden" name="timeStamp" value="${timeStamp}">
			<button onclick="pay()">付钱</button>
 		</div>
 		
  </body>
  <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
  	function pay(){
        	if (typeof WeixinJSBridge == "undefined"){
		           	if (document.addEventListener){
		                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		            }else if (document.attachEvent){
		                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		            }
		        }else {
		            onBridgeReady();
		        }
    }
    
	function onBridgeReady(){ 
        var appId = $("input[name='appId']").val();
        var nonceStr = $("input[name='nonceStr']").val();
        var prepayId = $("input[name='prepayId']").val();
        var paySign = $("input[name='paySign']").val();
        var timeStamp = $("input[name='timeStamp']").val();
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":appId,
                    "timeStamp":timeStamp,
                    "nonceStr":nonceStr,
                    "package":prepayId,
                    "signType":"MD5",
                    "paySign":paySign
                },
            	function(res){
	                if(res.err_msg == "get_brand_wcpay_request:ok" ) {
	                    alert("支付成功");
	                   	//location.href="./payafter.do?"+parm+"&state=01";
	                }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
	                   	alert("支付取消");
	                 	location.href="./TestServlet";
	                }else if(res.err_msg =="get_brand_wcpay_request:fail"){
	                	alert("支付失败");
	                	//location.href="./payafter.do?"+parm+"&state=03";
	                }
	            }
        	);
    }
    
    //以上方法不动
    
    
  
  </script>
</html>
