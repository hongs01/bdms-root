<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>大人流监控</title>
<style type="text/css">
.shadow {
	width: 700px;
	height: 350px;
	margin-top:120px;
	margin-left:auto;
	margin-right:auto;
	border: 1px solid #ddd;
	background-color: rgba(250,250,250,0.6);
	font-size: 12px;
	padding: 10px;
	-moz-box-shadow: 0 2px 2px #eee;
	-webkit-box-shadow: 0 2px 2px #eee;
	box-shadow: 0 2px 2px #eee;
	*filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135,
		Color="#3w33333");
		-moz-border-radius: 5px 5px 5px 5px;    
		-webkit-border-radius: 5px 5px 5px 5px;    
		-o-border-radius:5px 5px 5px 5px;    
		border-radius: 5px 5px 5px 5px;
		
		 
}
.shadow ul li
		{ 
			float:left;
			list-style:none;
			padding:20px;
			text-align:center;
			font-size:16px;
			color:#000;
			font-family:'黑体';
			font-weight: bold;
			
		}
		
.shadow ul li img
{
	border: 1px solid #ddd;
		-moz-border-radius: 5px 5px 5px 5px;    
		-webkit-border-radius: 5px 5px 5px 5px;    
		-o-border-radius:5px 5px 5px 5px;    
		border-radius: 5px 5px 5px 5px;
		background-color:#fff;
		padding:5px;
}
</style>
</head>
<body>
	<div class="shadow">
		<ul >
			<li>
				<a href="${APP_PATH}dams/metro/insideMetro/page" target="_blank"><img src="${APP_PATH}style/images/znqs.png"></a> </br>
					站内人数
			</li>
			
			<li>
				<a href="${APP_PATH}dams/metro/heatMetro/page" target="_blank"><img src="${APP_PATH}style/images/relitu.png"></a>
				</br>
					热力图
			</li>
			<li>
				<a href="${APP_PATH}dams/metro/od/showmap" target="_blank"><img src="${APP_PATH}style/images/rmxl2.png"></a>
				</br>
					热门线路
			</li>
			<li>
				<a href="${APP_PATH}dams/metro/inoutTotal/page" target="_blank"><img src="${APP_PATH}style/images/jinchuzhanhecha.png" width="100px" height="100px"></a>
				</br>
					进出总览
			</li>
			<li>
				<a href="${APP_PATH}dams/DZWL/DZWLPage/page" target="_blank" ><img src="${APP_PATH}style/images/mainPlace.png"></a>
				</br>
					重点区域
				
			</li>
			<li>
				<a href="${APP_PATH}dams/wifi/wifiPage/page" target="_blank"><img src="${APP_PATH}style/images/wifqushi.png"></a>
				</br>
					豫园客流
			</li>
			<li>
				<a href="http://10.15.35.200:8000/sjjk_gis/pages/multiVideos.jsp" target="_blank" ><img src="${APP_PATH}style/images/spjk.png"></a>
				</br>
					预警视频
			</li>
			<li>
				<a href="${APP_PATH}dams/video/videoPage/page" target="_blank" ><img src="${APP_PATH}style/images/klqs2.png"></a>
				</br>
					视频监控
			</li>
			
		</ul>
	</div>
</body>
</html>