<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
  <title>TwoPointsView</title>
  <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
  <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
  <script src="http://webapi.amap.com/maps?v=1.3&key=796e010bf17562ebe0bc459a8b37242b"></script>
  
</head>

<body>

  <!-- 初始化地图 -->	
  <div id="mapContainer"></div>
  
  	<script>
  	var map, marker;
	
	// 设置地图中心点
    var map = new AMap.Map("mapContainer", {
    resizeEnable: true,
    center: [116.397428, 39.90923],
    zoom: 13						
    });
	
	// 坐标点赋值
	var location1 = ${AddrEast1};
	var location2 = ${AddrNorth1};
	var location3 = ${AddrEast2};
	var location4 = ${AddrNorth2};

	// 绘制连接线
    var lineArr = new Array();
	lineArr[0] = new Array();
	lineArr[1] = new Array();
	lineArr[0][0] = location1;
	lineArr[0][1] = location2;
	lineArr[1][0] = location3;
	lineArr[1][1] = location4;
	  
    polyline = new AMap.Polyline({
        path: lineArr,          	//设置线覆盖物路径
        strokeColor: "#3366FF", 	//线颜色
        strokeOpacity: 0.75,    	//线透明度
        strokeWeight: 5,        	//线宽
        strokeStyle: "solid",   	//线样式
    });
    polyline.setMap(map);   

    // 添加点标记
    var markers = [{
        icon: 'http://webapi.amap.com/images/marker_sprite.png',
        position: [location1,location2]
    }, {
        icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png',
        position: [location3,location4]
    }];
	
    // 实例化点标记
    markers.forEach(function(marker) {
        new AMap.Marker({
            map: map,
            icon: marker.icon,
            position: [marker.position[0], marker.position[1]],
        });
    });

    // 添加地图自适应,根据标记点显示到合适的范围
    map.setFitView();
    
  </script>
</body>
<!-- 完美的body ^o^ -->
</html>