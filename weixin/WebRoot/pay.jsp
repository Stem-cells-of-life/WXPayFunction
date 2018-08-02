<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<!-- uc强制竖屏 -->
	<meta name="screen-orientation" content="portrait">
	<!-- QQ强制竖屏 -->
	<meta name="x5-orientation" content="portrait">
	<!-- UC强制全屏 -->
	<meta name="full-screen" content="yes">
	<!-- QQ强制全屏 -->
	<meta name="x5-fullscreen" content="true">
	<!-- UC应用模式 -->
	<meta name="browsermode" content="application">
	<!-- QQ应用模式 -->
	<meta name="x5-page-mode" content="app">
	<!-- windows phone 点击无高光 -->
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="format-detection" content="telephone=no, email=no" />
	<style type="text/css">
		html,body{
			height:100%;
			width: 100%;
		}
		html,body,div,span,p,h1,h2,h3{
			margin: 0;
			padding: 0;
	   }
	   h2,h3,p{
	   	display:inline;
	   }
	  .head{
	  	width:100%;
	  	height: 70%;
	  }
	   .head .con{
	   		padding: 5vh 0 5vh 0;
	   		text-align: center;
	   }
	   .head .byinfo{
	   		margin: 0 10vw;
	   }
	   .head .byinfo>div{
	   		margin:3vh auto;
	   }
	   .head .byinfo>div>p{
	   		font-family: "Microsoft YaHei";
	   		font-size: 20px;
	   		margin-left: 5vw;
	   }
	   .address>h2{
	   		display: block;
	   }
	   .pay{
	   		margin: 0 5vw;
	   		height: 20%;
	   }
	   .totalfee{
	   		text-align: center;
	   }
	   .totalfee>p{
	   		font-family: "Microsoft YaHei";
	   		font-size: 30px;
	   }
	   .totalfee>span{
	   		font-family: "Microsoft YaHei";
	   }
	   .btn{
	   		margin: 6vh 0;
	   		text-align: center;
	   }
	   .sure{
	   		font-size: 20px;
	   		background-color:#00FF7F;
	   		border-radius:20px;
	   }
	   .back{
	   		margin-left: 4vw;
	   		font-size: 20px;
	   		background-color:#FF4500;
	   		border-radius:20px;
	   }
	   .foot{
	   		clear: both;
	   }
	</style>
<title>确认信息</title>
</head>
<body>		
			<div class="head">
				<div class="con"><h1>信息确认</h1></div>
				<div class="byinfo">
					<div><h2>收件人名:</h2><p>${username}</p></div>
					<div><h2>电话号码:</h2><p>${phone }</p></div>
					<div><h2>购买数量:</h2><p>${number }</p></div>
					<div class="address">
						<h2>邮寄地址:</h2>
						<p style="display:block;margin-top:2vh;">${address }</p>
					</div>
				</div>
			</div>
			<div class="pay">
				<div class="totalfee">
					<h3>总价格：</h3>
					<p>${fee }</p>
					<span>元</span>
				</div>
				<div class="btn">
					<button class="sure" type="button" onclick="wxpay()">确认付款</button>
					<button class="back" type="button" onclick="javascript:history.back();">取消付款</button>
				</div>
			</div>	
			<div class="foot">	
				<input type="hidden" name="appId" value="${appId}">
				<input type="hidden" name="nonceStr" value="${nonceStr}">
				<input type="hidden" name="prepayId" value="${prepayId}">
				<input type="hidden" name="paySign" value="${paySign}">
				<input type="hidden" name="timeStamp" value="${timeStamp}">
				
				<!--已下是客户信息  -->
				<input type="hidden" name="ordernumber" value="${ordernumber}">
				<input type="hidden" name="username" value="${username}">
				<input type="hidden" name="phone" value="${phone}">
				<input type="hidden" name="number" value="${number}">
				<input type="hidden" name="address" value="${address}">
				<input type="hidden" name="fee" value="${fee}">
			</div>	
</body>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	 function wxpay(){
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
            	var ordernumber = $("input[name='ordernumber']").val();
            	var username = $("input[name='username']").val();
		        var phone = $("input[name='phone']").val();
		        var number = $("input[name='number']").val();
		        var address = $("input[name='address']").val();
		        var fee = $("input[name='fee']").val();
		        var parm = "ordernumber="+ordernumber+
		        							"&username="+encodeURI(encodeURI(username))+
									        "&phone="+phone+
									        "&number="+number+
									        "&address="+encodeURI(encodeURI(address))+
									        "&fee="+fee+
									        "&a="+Math.floor(Math.random()*10000);
                if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                    //alert("支付成功");
                   location.href="./payafter.do?"+parm+"&state=01";
                }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                   // alert("支付取消");
                 	location.href="./payafter.do?"+parm+"&state=02";
                }else if(res.err_msg =="get_brand_wcpay_request:fail"){
                	//alert("支付失败");
                	location.href="./payafter.do?"+parm+"&state=03";
                }
            }
        );
    }
</script>
</html>