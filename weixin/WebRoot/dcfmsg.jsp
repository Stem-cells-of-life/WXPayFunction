<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
		html,body,div{
			margin: 0;
			padding: 0;
		}
		
		div{
			text-align: center;
		}
		
		button {
			font-size: 50px;	
		}
	</style>
  </head>
  <body>
		<!-- 传过来一个list -->
		<!-- 展示这个list -->
		<div>
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
	                 	//location.href="./payafter.do?"+parm+"&state=02";
	                }else if(res.err_msg =="get_brand_wcpay_request:fail"){
	                	alert("支付失败");
	                	//location.href="./payafter.do?"+parm+"&state=03";
	                }
	            }
        	);
    }
  
  </script>
</html>
