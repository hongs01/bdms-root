<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=xGOZflEFM16Sb933SiVEQre3"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/AjaxMap.js" />"></script>
    <title>热力图功能示例</title>
    <style type="text/css">
		ul,li{list-style: none;margin:0;padding:0;float:left;}
		html{height:100%}
		body{height:100%;margin:0px;padding:0px;font-family:"微软雅黑";}
		#container{height:80%;width:100%;}
		#r-result{width:100%;}
    </style>	
   
</head>
<body>
<!-- <input type="button" value="提交" onclick="updata()"> -->
	<div id="container"></div>
	<div id="r-result">
		<input type="button"  onclick="openHeatmap();" value="显示热力图"/><input type="button"  onclick="closeHeatmap();" value="关闭热力图"/>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){  
	     setTimeout(heatMap, 1000);  
	    }); 
	$(document).ready(function(){  
	     setInterval(updateHeatMap, 10*1000);  
	    }); 
	
    
	</script>
</body>
</html>