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
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0" />
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<style>
body {
	background-color: rgb(46, 139, 87);
}

table {
	background: #fff;
}

.img-circle,.logo{
	border-radius: 50%;
    text-align: center;
}
.banner{
text-align:center;
}
.banner h2{
margin-top:10px;
}
</style>
</head>
<body>
	<div class="img-circle">
		<img class="logo" src="img/logo.png" />
	</div>
	<div class="banner">
					<h2 class="name3">冻存费续费自助服务</h2>
	                <h2 class="name3">尊敬的客户：</h2>
					<h2 class="name3">${username}</h2>
	</div>
	<div class="table-responsive">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<tr>
				<th><input type="checkbox" name="checkbox" id="checkAll" />续费</th>
				<th>协议号</th>
				<th>协议类型</th>
				<th>到期时间</th>
				<!-- <th>冻存费</th>   -->
				<!-- <th>is作废</th>  -->
				<th>缴费(元)</th>
			</tr>
			<c:forEach items="${feeList}" var="list">
				<tr class="css_tr">
					<td class="css_tr_th" id="checkbox"><input type="checkbox"name="checkbox" /></td>
					<td class="css_td" style="display: none;">${list.id }</td>
					<td class="css_td" id="bs_agreement_0001" name="bs_agreement_0001">${list.bs_agreement_0001}</td> 
					<c:if test="${list.xy_type=='01'}">
						<td class="css_td">脐血</td>
					</c:if>
					<c:if test="${list.xy_type=='02'}">
						<td class="css_td">脐带</td>
					</c:if>
					<c:if test="${list.xy_type=='04'}">
						<td class="css_td">胎盘</td>
					</c:if>
					<td class="css_td">${list.bs_fee_0002}</td>
					<!-- <td class="css_td">${list.dcf}</td>  -->
					<!-- <td class="css_td">${list.isisvalid}</td> -->
					<td class="css_td" id="select">
						<select class="priceselect">
							<option value="600">600(原价:一年)</option>
							<option value="2910">2910(折扣93%:五年)</option>
							<option value="5580">5580(折扣93%:十年)</option>
						</select>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>合计：</td>
				<td colspan="5"><span id="money">0元</span>
				</td>
			</tr>
		</table>
	</div>
	
	<p id="basePath" style="display: none;"><%=basePath %></p>
	<form id="jsonform" action="/weixin/dcfcode.do" method="post" enctype="application/x-www-form-urlencoded">
		<input id="data4json" name="arr" style="display: none;"/>
		<input id="total4json" name="total" style="display: none;"/>
		<button type="button" class="btn-submit btn btn-success" id="btnSubmit">确认付款</button>
		<button type="button" class="btn-submit btn btn-success"  id="returnMainPage" >返回首页</button>
	</form>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		//获取选中的 checkbox的值
		function getCheckedValues() {
			var arr = [];
			$("input[type='checkbox']:checked").not("#checkAll").each(
					function(index, item) {
						arr.push({
							id :  $(this).parent().siblings().eq(0).html(),
							bs_agreement_0001 : $(this).parent().siblings().eq(1).html(),
							xy_type : $(this).parent().siblings().eq(2).html(),
							bs_fee_0002 : $(this).parent().siblings().eq(3).html(),
							priceselect : $(this).parent().siblings().eq(4).find("option:selected").val()
						});
					});
			return arr;
		}

		$("#checkAll").on("click", function() { //完成全选全不选
			if ($(this).prop("checked") == true) {
				$("input[type='checkbox']").not("#checkAll").each(function() {
					$(this).prop("checked", true)
				})
				getTotalFee(getCheckedValues());

			} else {
				$("input[type='checkbox']").not("#checkAll").each(function() {
					$(this).prop("checked", false)
				})
				getTotalFee(getCheckedValues());
			}
		})

		function getTotalFee(arr) { //计算选中框的金额值
			var sum = 0;
			for ( var i = 0; i < arr.length; i++) {
				sum += Number(arr[i].priceselect);
			}
			$("#money").html(sum + "元");
			return sum;
		}

		$("input[type='checkbox']").not("#checkAll").click(function() { //当全部选中后，确定全选
			var checkedBox = $("input[type='checkbox']").not("#checkAll");

			var len = checkedBox.length
			var arr = 0;

			checkedBox.each(function() {
				if ($(this).prop("checked") == true) {
					arr++;
				}
				if (arr == len) {
					$("#checkAll").prop("checked", true)
				} else {
					$("#checkAll").prop("checked", false)
				}

			});

			getTotalFee(getCheckedValues()); //绑定显示金额
		});
		
		$(".priceselect").on("change", function() { //点击下拉框付款提交数据
			getTotalFee(getCheckedValues());
		});

		$("#btnSubmit").click(function() { //点击确认付款提交数据
		
			var jsonform = $("#jsonform");
			if(getCheckedValues()==0){
			        alert("您选择的协议为空，请选择有效的协议后，再付款！");
			}
			else{
			    var data4json = JSON.stringify(getCheckedValues());
			    var total = JSON.stringify(getTotalFee(getCheckedValues()));
			    $("#data4json").attr("value",data4json);
			    $("#total4json").attr("value",total);
			    jsonform.submit();
			}	
		});
		
		$("#returnMainPage").click(function() { //返回主页
		   window.location.href="http://203624vk44.iok.la/weixin/shouye.html?a="+Math.floor(Math.random()*10000);	
		});
		//function ajaxSend() {
		//	var basePath = $("#basePath").html();
		//	$.ajax({
		//		url :basePath+"dcfcode.do",
		//		type : "post",
		//		data : {
		//				arr : JSON.stringify(getCheckedValues()),
		//				total : JSON.stringify(getTotalFee(getCheckedValues()))
		//		},
		//		success : function(data) {
		//			alert(data);
		//		},
		//		error : function(err) {
		//			console.log(err)
		//		}
		//	});
		//}
	</script>
</html>