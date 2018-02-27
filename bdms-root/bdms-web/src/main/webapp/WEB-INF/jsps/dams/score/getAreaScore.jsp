<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="refresh" content="300;url=${APP_PATH }dams/score/scorePage/getTopAreaScore">
<title>重点区域客流风险评估值</title>

<style type="text/css">

#topArealeft {
	width: 30%;
	height: 190px;
	float: left;
}

#topAreaCenter {
	float: left;
	width: 15%
}

#topAreaRight {
	float: left;
	width: 30%;
	margin-left: 200px;
}


#topAreaCenterFont {
	color: #fff;
	float: right;
	font-size: 30px;
	font-family: '微软雅黑';
	margin-top: 120px;
}

#TotalAreaNameScore {
	margin-top: 10px;
	width: 180px;
	height: 180px;
}


#firstWeightListul li {
	float: left;
	margin-left: 40px;
	list-style: none;
	margin-top: 30px
}

.fm {
	position: relative;
	top: 82px;
	font-size: 18px;
	font-family: '微软雅黑';
	font-weight: bold;
	width: 100px;
	text-align: center;
}
.fmName
{
	width: 100px;
	text-align: center;
}

#firstimg {
	width: 100px;
	height: 106px;
	
}


.seList ul li
{
	line-height: 25px;
	font-size: 14px;
	list-style: none;
	margin: 0px;
}

.sestationName{
	margin-top: 10px;
	margin-left: 10px;
}

.alarm_1{
	color: rgb(35,172,56);
	font-size: 24px;
	font-weight: bold;
	font-family: '微软雅黑';
}
.alarm_2{
	color: rgb(238,234,56);
	font-size: 24px;
	list-style: none;
	font-weight: bold;
	
}
.alarm_3{
	color: rgb(236,101,23);
	font-weight: bold;
	font-size: 24px;
	font-family: '微软雅黑';
}
.alarm_4{
	color: rgb(232,54,23);
	font-weight: bold;
	font-size: 24px;
	font-family: '微软雅黑';
}

#areaByMark{
	width: 100%;
	text-align: center;
    padding-top: 20px;
	font-size: 24px;
	color: #fff;
	font-family: '微软雅黑';
	height: 64px;
	background-color: #1f2e77;
	line-height: 50px;
}


#areasByMarkul li a{
	text-decoration: none;
	color: #fff;
}
#areasByMarkul li a:HOVER
{
	color: #066b81;
}
.circliful {
    position: relative; 
}

.circle-text, .circle-info, .circle-text-half, .circle-info-half {
    width: 100%;
    position: absolute;
    text-align: center;
    display: inline-block;
}

.circle-info, .circle-info-half {
	color: #eee;
}
.circle-info-half{
	line-height: 180px;
	float: left;
	overflow: hidden;
}

.circliful .fa {
	margin: -10px 3px 0 3px;
	position: relative;
	bottom: 4px;
}
#font_info{
	position: relative;
	top:-90px;
	z-index: 999;
	left: 134px;
	font-family: '微软雅黑';
	
}
.AreaNameScore{
	width: 80px;
	height: 80px;
	float: right;
	margin-right: 20px;
}
.AreaNameScorecircliful{
}
.areaNameByMark{
	line-height: 80px;
	color: #fff;
	float: left;
	margin-left: 20px;
}
.alarmCount{
	position: relative;
	color: #fff;
	z-index: 999;
	line-height: 20px;
	top:-70px;
	left: 60px;
	width: 20px;
	height: 20px;
	text-align: center;
	font-size: 15px;
}

</style>
</head>
<body class="west-body-background">
	<!-- start header -->
	<jsp:include page="../header/scoreHeader.jsp"></jsp:include>

	<style type="text/css">
.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: rgb(193, 229, 237);
	} 
</style>

	<!-- end header -->
	<!-- start center -->
	
	<div class="ui-layout-center bodycolor west-body-background"
		style="border: 0">
		<div class="score1">
			<div style="float:left">
				<div id="topAreaTitle">
				大客流聚集安全风险监测平台 
				<fmt:formatNumber value="${area.totalMark}" pattern="0" />
				</div>
				<div id="areaName">${area.areaName}(${area.area})</div>
			</div>
			<div id="topAreaRight">
			<div id="TotalAreaNameScore" >
				<div id="TotalAreaNameScorecircliful" 
				data-dimension="180" 
				data-text='<fmt:formatNumber value="${area.totalMark}" pattern="0" />' 
				data-width="20"
				data-fontsize="80" 
				data-percent='<fmt:formatNumber value="${area.totalMark}" pattern="0" />' 
				data-fgcolor='<c:choose>
				<c:when test="${area.alarmLevel=='1'}">
					#23ac38
				</c:when>
				<c:when test="${area.alarmLevel==2}">
					#eeea38
				</c:when>
				<c:when test="${area.alarmLevel==3}">
					#ec6517
				</c:when>
				<c:when test="${area.alarmLevel==4}">
					#e83617
				</c:when>
			</c:choose>'
				data-bgcolor="#eee" 
				data-fill="#ddd">
				</div>
				<div id="font_info">
					分
				</div>
			</div>
			
		</div>
		</div>
		<ul id="firstWeightListul">
			<c:forEach items="${area.firstWeightList}" var="fw">
				<li   onmouseout="showseList(${fw.id})" onmouseover="showseList(${fw.id})">
					<div class="${fw.typeName}_${fw.alarmLevel}">
						<div class="fm">
							<fmt:formatNumber value="${fw.firstMark}" pattern="0" />
							分
							<div class="fmName"><a href="#" onclick="jump('${fw.typeName}','${fw.typeCode}')" target="blank">${fw.stationName}</a></div>
						</div>

						<div class="seList" id="${fw.id}">
							<div class="sestationName">
								<font class="alarm_${fw.alarmLevel}">${fw.stationName}</font>
								共${fw.secondWeightList.size()}项，其中有<font class="alarm_${fw.alarmLevel}">${fw.alarmCount}</font>个预警。
							</div>
							<ul>
								<c:forEach items="${fw.secondWeightList}" var="sw">
									<li><spring:message code="${sw.name}"></spring:message>的人数${sw.peopleNum}，风险评分<a href="#"><font class="alarm_${sw.alarmLevel}"><fmt:formatNumber value="${sw.secondMark}" pattern="0" /></font></a>分</li>
								</c:forEach>
							</ul>

						</div>
						
					</div>
					
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- end center -->

	<!-- start east -->
	<div class="ui-layout-east bodycolor east-div" style="border: 0">
		<div id="areaByMark">
			重点区域客流风险评估值
		</div>
		
		<ul id="areasByMarkul">
			<c:forEach items="${areas}" var="a">
				<li>
				<div>
				<div class="areaNameByMark">
					<a class="score2"  href="${APP_PATH}dams/score/scorePage/getAreaScore/${a.id}">${a.areaName }(${a.area})</a>
				</div>
				<div class="AreaNameScore" >
				<div class="AreaNameScorecircliful" 
					data-dimension="80" 
					data-text='<fmt:formatNumber value="${a.totalMark}" pattern="0" />' 
					data-width="10"
					data-fontsize="18" 
					data-percent='<fmt:formatNumber value="${a.totalMark}" pattern="0" />' 
					data-fgcolor='<c:choose>
					<c:when test="${a.alarmLevel=='1'}">
						#23ac38
					</c:when>
					<c:when test="${a.alarmLevel==2}">
						#eeea38
					</c:when>
					<c:when test="${a.alarmLevel==3}">
						#ec6517
					</c:when>
					<c:when test="${a.alarmLevel==4}">
						#e83617
					</c:when>
					</c:choose>'
					data-bgcolor="#eee" 
					data-fill="#ddd">
				</div>
				<c:choose>
					<c:when test="${a.alarmCount>0}">
						<div class="alarmCount alarmCountbg">
							${a.alarmCount}
						</div>
					</c:when>
				</c:choose>
				
			</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- end east -->


</body>
<script type="text/javascript">
	$(function(){
		
	 $('#TotalAreaNameScorecircliful').circliful();
	 $('.AreaNameScorecircliful').circliful();
	 
	});
	
	//显示子项得分
	function showseList(id)
	{
		var idstr='#'+id;
		$(idstr).toggle();
	}
	
	//一级得分画圆圈
	function initCircleScore() {
		$('#TotalAreaNameScore').radialIndicator({
			barColor : {
				0 : '#23ac38',
				60 : '#23ac38', //0-50设置成绿色
				// 51: '#FFFF00', 
				70 : '#eeea38', //50-70设置成黄色
				//71：'#FFA500', 
				90 : '#ec6517', //70-90设置成橙色    
				//91：'#FF0000',
				100 : '#e83617' //90-100设置成红色
			},
			barWidth : 15,
			percentage : false,
			progress : 0.5,
			fontFamily : '微软雅黑',
			fontSize : 40,
			radius : 70,
			initValue:'<fmt:formatNumber value="${area.totalMark}" pattern="0"/>',
			format : function(score) {
				return score + "分";
			}
		});
	};
	
	
	function jump(type,stationId){
	
		var urlDZWL = "${APP_PATH}dams/DZWL/DZWLPage/page/"+stationId;
		
		var urlMetro = "${APP_PATH}dams/metro/inoutTotal/page/"+stationId;
		
		var urlWifiData ="${APP_PATH}dams/wifi/wifiData/page/"+stationId;
		
		if("dzwl" == type){
						
			window.open(urlDZWL); 
			
		}else if("metro" == type){
			
			window.open(urlMetro); 
			
		}else if("wifiData" == type){
			
			window.open(urlWifiData); 
		}	
		
	}
</script>
</html>