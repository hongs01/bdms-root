<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<title>首页</title>
</head>
<body>
<div style="height:100%;width:100%">
		<!-- 轨交 -->
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"><img style="height:80px;width:80px;" src='${APP_PATH}style/images/guidaojiaoton.png'/></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">轨交</div>
		</div>
		<div style="width:20px;height:120px;float:left"></div>

		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/insideMetro/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/znqs.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">站内总人数</div>
		</div> 
 		 
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/heatMetro/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/relitu.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">热力图</div>
		</div>
 		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/od/showmap" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/rmxl2.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">热门线路</div>
		</div>
 		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/in/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/jinzhan.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">进站人数</div>
		</div>
 		  
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/out/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/chuzhan.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">出站人数</div>
		</div>
 		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/inoutNum/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/jinchuzhan.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">进出站人数</div>
		</div>
 		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/inoutsum/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/jinchuzhanhe.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">进出站统计</div>
		</div>
 		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/inoutsub/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/jinchuzhancha.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">站内人数</div>
		</div>  
 		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/metro/inoutTotal/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/jinchuzhanhecha.png'/></a></div>          			
		            <div style="width:100px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">进出站总览</div>
		</div>
		
        <div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden;"></div>
           
           
          <!-- 重点场所  -->
        <div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <img style="height:80px;width:80px;" src='${APP_PATH}style/images/mainPlace.png'/></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">重点区域</div>
		</div>
		<div style="width:20px;height:120px;float:left"></div>
		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/DZWL/DZWLPage/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/klqs.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">客流趋势</div>
		</div>  
 
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href=http://192.168.7.85:8087/examples/heatmap.html?q=heat target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/Gis.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">地图</div>
		</div>
        <div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden;"></div>    
            
           
  		
  		<!-- 视频 -->
  		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <img style="height:80px;width:80px;" src='${APP_PATH}style/images/video.png'/></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">视频</div>
		</div>
		<div style="width:20px;height:120px;float:left"></div>
		
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/video/videoPage/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/klqs2.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">客流趋势</div>
		</div>  
 
		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="http://10.15.35.200:8000/sjjk_gis/pages/multiVideos.jsp" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/spjk.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px; font-weight: bold; ">预警视频</div>
		</div>
        <div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden;"></div>    
            
  			 
  		<!-- WIFI -->
  		<div style="width:80px;height:120px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <img style="height:80px;width:80px;" src='${APP_PATH}style/images/wifi.png'/></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px;font-weight: bold;  ">WIFI</div>
		</div>
		<div style="width:20px;height:120px;float:left"></div>
		
		<div style="width:80px;height:10px;margin-left: 20px;margin-top:10px;float:left;">
		            <div style="width:80px;height: 80px;margin-bottom: 5px"> <a href="${APP_PATH}dams/wifi/wifiPage/page" target="_blank"><img style="height:80px;width:80px;border:0px;" src='${APP_PATH}style/images/wifqushi.png'/></a></div>          			
		            <div style="width:80px;height: 20px;text-align: center;font-family:'微软雅黑';font-size: 16px;font-weight: bold;  ">客流趋势</div>
		</div> 	 
        <div style="width:100%;HEIGHT: 200px"></div>   
</div>
</body>
</html>