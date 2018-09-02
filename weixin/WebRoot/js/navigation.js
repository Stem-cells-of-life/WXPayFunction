/**
 * 页面加载时控制地图显示窗口高度
 */
$(document).ready(function(){
	var height = document.documentElement.clientHeight;
	$('.m_map').css("height",height+"px");
});
/**
 * 窗口尺寸改变时地图随之改变
 */
window.onresize = function(){
	var height = document.documentElement.clientHeight;
	$('.m_map').css("height",height+"px");
}
/**
 * 显示弹窗及操作按钮
 */
function openNavigation(){
	//显示遮罩
	$("#btnGroup_getNavigation").removeClass("hidden").addClass("show");
	//动态显示按钮组
	$("#btnGroup_getNavigation .btnGroup").removeClass("hidden").addClass("show");
	
}
	
$(document).ready(function(){
	//关闭导航的弹出按钮组
	$("#btnGroup_getNavigation .button.gray").click(function() {
		//动态降低按钮组高度
		$("#btnGroup_getNavigation .btnGroup").removeClass("show").addClass("down");
		//由于动画需0.5s，延迟执行后续动作
		setTimeout(function(){
			//隐藏按钮组
			$("#btnGroup_getNavigation .btnGroup").removeClass("down").addClass("hidden");
			//去除遮罩
			$("#btnGroup_getNavigation").removeClass("show").addClass("hidden");
		},500);
	});
})
		
/**
 * 调取相应地图APP
 */		
var OpenApp = function(app) {
	var lon = $('#lon').val(),lat = $('#lat').val(),custAdr = $('#custAdr').val(),issinfoCustPhone=$('#issinfoCustPhone').val();
	$("#btnGroup_getNavigation").removeClass("show").addClass("hidden");
	var ua = navigator.userAgent.toLowerCase();
	if(/micromessenger/.test(ua)){
    	alert('微信不支持启动语音导航,请点击屏幕右上角按钮,从浏览器打开。');
        return;
    }
	var IsIos = navigator.userAgent.match('iPhone');
	var IsAndroid = navigator.userAgent.match('Android');
	IsMobile = (IsIos || IsAndroid);
	if(lon!=''&&lat!=''){
		getAPP();
	}else{
		var myGeo = new BMap.Geocoder();
		function getAddr(result){
            myGeo.getPoint(custAdr, function(point){ //第一步getPoint是地址解析。
	            if (point) {
	                myGeo.getLocation(point, function(rs){ //第二步getLocation是反地址解析。
		 	            lon = point.lng;
		 	            lat = point.lat;
		 	            alert("经度："+lon+"纬度："+lat);
		 	           getAPP();
		 	        });
	            }else{
	            	alert("地址无效，无法定位。");
	            }
            }, result.name); //定位获得城市
		}
        var myCity = new BMap.LocalCity();//根据所在城市进行定位
        myCity.get(getAddr);
	}
	
	function getAPP(){
		if (IsMobile == 'iPhone') {
			if (app == 'bd') {
				setTimeout(function() {
					window.location = "itms-apps://itunes.apple.com/app/id452186370?mt=8";
				}, 25);
				window.location = "baidumap://map/direction?origin=我的位置&destination="+custAdr+"&mode=driving&src=XUN4G";
			} else {
				 setTimeout(function() {
					window.location = "itms-apps://itunes.apple.com/app/id461703208?mt=8";
				 }, 25);
				window.location = "iosamap://navi?sourceApplication=XUN4G&poiid=1&poiname=&lat="+lat+"&lon="+lon+"&dname=&dev=0&style=0";
			}

		}
		if (IsMobile == 'Android') {
			if (app == 'bd') {
				window.location = "intent://map/direction?origin=我的位置&destination="+lat+","+lon+"&mode=driving&src=XUN4G#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
			} else {
				window.location = "intent://navi?sourceApplication=XUN4G&lat="+lat+"&lon="+lon+"&dname=&dev=1&style=0#Intent;scheme=androidamap;package=com.autonavi.minimap;end";
			}
		}
	}
};


/**
 * 设置公用参数、地图加载开始
 */
var map,marker,cityName,or_point,label,custName,custAdr,longitude,latitude;
var myGeo = new BMap.Geocoder();
var icon = new BMap.Icon("img/dw1.gif", new BMap.Size(100,100));
function localSearch(){
    map = new BMap.Map("myMap"); // 创建地图实例
    map.enableContinuousZoom();   // 开启连续缩放效果    
    map.setMapStyle({style:'bluish'});//百度地图主题
    var localSearch = new BMap.LocalSearch(map);
    
	custName = $('#custName').val(),custAdr = $('#custAdr').val();
	longitude = $('#lon').val(),latitude = $('#lat').val();
	issinfoCustPhone=$('#issinfoCustPhone').val();
	
	//or_point =new BMap.Point(104.020468,30.723131);//设定初始定位值（成都），防止加载异常。
	or_point =new BMap.Point(114.549459,30.484431);//武汉的位置
	map.centerAndZoom(or_point, 16);
	
	if(longitude!=''&&latitude!=''){
		createInfoWindow(longitude,latitude);
	}else{
		if(custAdr!=''){
			function getAddr(result){
                myGeo.getPoint(custAdr, function(point){ //第一步getPoint是地址解析。
		            if (point) {
		                map.centerAndZoom(point, 16);
		                myGeo.getLocation(point, function(rs){ //第二步getLocation是反地址解析。
			 	            longitude = point.lng;
			 	            latitude = point.lat;
			 	            createInfoWindow(longitude,latitude);
			 	        });
		            }else{
		            	alert("地址无效，无法定位。");
		            }
                }, result.name); //定位获得城市
			}
	        var myCity = new BMap.LocalCity();//根据所在城市进行定位
	        myCity.get(getAddr);
		}else{//经纬度和地址都为空时根据所在城市进行定位
			function myFun(result){
				cityName = result.name;
				myGeo.getPoint(cityName, function(point){ 
					marker = new BMap.Marker(or_point,{icon:icon});  
					label = new BMap.Label("当前地理位置："+cityName,{offset:new BMap.Size(-30,-25)});
					label.setStyle({color : "red",fontSize : "14px",height : "25px",lineHeight : "25px",
			   			 fontFamily:"微软雅黑"});
					marker.setLabel(label);
					map.addOverlay(marker); 
				});
			}
			var myCity = new BMap.LocalCity();//根据所在城市进行定位
			myCity.get(myFun);
		}
	}
}

/**
 * 创建并展现InfoWindow
 */
function createInfoWindow(longitude,latitude){
	var content ="<div id='map'><div id='map_popdiv'><div class='title'>"+custName+""
	+"</div>"
	+"<div class='address'>"+custAdr+"</div><a href='tel:"+issinfoCustPhone+"' class='button left' >拨打电话</a>"
	+"<a href='javascript:openNavigation()' id='getNavigation'  class='button right'>地图导航</a><div style='clear: both;'></div></div></div>";
	var p=new BMap.Point(longitude,latitude); 
	map.centerAndZoom(p, 16);
	marker = new BMap.Marker(p,{icon:icon});
	label = new BMap.Label("当前位置是："+custAdr,{offset:new BMap.Size(-30,-25)});
	label.setStyle({color : "red",fontSize : "12px",height : "20px",lineHeight : "20px",
			 fontFamily:"微软雅黑"});
	map.addOverlay(marker); 
	var infoWindow = new BMap.InfoWindow(content);
	map.openInfoWindow(infoWindow,p); //开启信息窗口
	(function(){
		var index = 0; 
		marker.addEventListener("click",function(){
			marker.setLabel(label);
			map.openInfoWindow(infoWindow,p);
	    });
		
	    infoWindow.addEventListener("close",function(){
	    	index++;
	    	if(index>1){
	    		marker.setLabel(label);
	    	}
		    marker.getLabel().show();
	    })
		label.addEventListener("click",function(){
			map.openInfoWindow(infoWindow,p);
			marker.getLabel().hide();
	    })
	})()
	
}


