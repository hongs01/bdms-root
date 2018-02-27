<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>echartsLine示意图</title>

</head>


<style type="text/css">
#backImg{
 /**background: url(${APP_PATH}style/images/grey_wash_wall.png) */
 background:black ;
}
</style>
<body id="backImg">
<!-- start top -->
	<jsp:include page="onTimeHeader2.jsp"></jsp:include>
<!-- end top -->

<!-- start map -->
<div id="fheatmain" style="height:500px;width:1000px; float: left;padding-left:40px">
	<iframe width="100%" height="100%" src="${APP_PATH}dams/elecfence/heatMap" frameborder="0"></iframe>
</div>
<!-- end map -->

		<!-- Tabs -->
<div id="tabs" style="width: 400; height:80px;float: left; margin-left: 20px; height: 490px; color: #FFF">
	
	<ul style="border:0;background:none;padding:0;">
		<li><a href="#tabs-1" style="font-size:13px;padding:0;"><img width="65" height="45" src="${APP_PATH}style/images/in2.png"/></a></li>
		<li><a href="#tabs-2" style="font-size:13px;padding:0;"><img width="65" height="45" src="${APP_PATH}style/images/out.png" /></a></li>
		<li><a href="#tabs-3" style="font-size:13px;padding:0;"><img width="65" height="45" src="${APP_PATH}style/images/inout1.png" /></a></li>
		<li><a href="#tabs-4" style="font-size:13px;padding:0;"><img width="65" height="45" src="${APP_PATH}style/images/inout2.png" /></a></li>
	</ul>
		 
		 
		<div id="tabs-1" style="padding:0;">
		<div class="container">
			<div class="sub-con cur-sub-con" align="center">
				<table
					style="font-size: 17px; color: white;font-family:Microsoft Yahei;">
					<tr height=60>
					</tr>
					<tr>
						<td width="136" id="inname1">虹桥火车站</td>
						<td width="60">
							<div id="inimg1"></div>
							<div id="indivPb1" style="width: 50px; height: 5px;"></div>
						</td>
						<td width="60" id="innum1">1246</td>
					</tr>
					<tr>
						<td id="inname2">人民广场</td>
						<td>
							<div id="inimg2"></div>
							<div id="indivPb2" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum2">1021</td>
					</tr>
					<tr>
						<td id="inname3">上海火车站</td>
						<td>
							<div id="inimg3"></div>
							<div id="indivPb3" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum3">900</td>
					</tr>
					<tr>
						<td id="inname4">衡山路</td>
						<td>
							<div id="inimg4"></div>
							<div id="indivPb4" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum4">775</td>
					</tr>
					<tr>
						<td id="inname5">肇嘉浜路</td>
						<td>
							<div id="inimg5"></div>
							<div id="indivPb5" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum5">612</td>
					</tr>
					<tr>
						<td id="inname6">常熟路</td>
						<td>
							<div id="inimg6"></div>
							<div id="indivPb6" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum6">342</td>
					</tr>
					<tr>
						<td id="inname7">宜山路</td>
						<td>
							<div id="inimg7"></div>
							<div id="indivPb7" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum7">331</td>
					</tr>
					<tr>
						<td id="inname8">宜山路</td>
						<td>
							<div id="inimg8"></div>
							<div id="indivPb8" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum8">331</td>
					</tr>
					<tr>
						<td id="inname9">宜山路</td>
						<td>
							<div id="inimg9"></div>
							<div id="indivPb9" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum9">331</td>
					</tr>
					<tr>
						<td id="inname10">宜山路</td>
						<td>
						<div id="inimg10"></div>
							<div id="indivPb10" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum10">331</td>
					</tr>
				</table>
				</div>
				</div>
		</div>
		<div id="tabs-2" style="padding:0;">
		<div class="container">
			<div class="sub-con cur-sub-con" align="center">
				<table 
					style="font-size: 17px; color: white;font-family:Microsoft Yahei;">
					<tr height=60>
					</tr>
					<tr>
						<td width="136" id="outname1">虹桥火车站</td>
						<td width="60">
							<div id="outimg1"></div>
							<div id="outdivPb1" style="width: 50px; height: 5px;"></div>
						</td>
						<td width="60" id="outnum1">1375</td>
					</tr>
					<tr>
						<td id="outname2">上海火车站</td>
						<td>
							<div id="outimg2"></div>
							<div id="outdivPb2" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum2">1556</td>
					</tr>
					<tr>
						<td id="outname3">人民广场</td>
						<td><div id="outimg3"></div><div id="outdivPb3" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum3">899</td>
					</tr>
					<tr>
						<td id="outname4">衡山路</td>
						<td><div id="outimg4"></div><div id="outdivPb4" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum4">812</td>
					</tr>
					<tr>
						<td id="outname5">常熟路</td>
						<td><div id="outimg5"></div><div id="outdivPb5" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum5">562</td>
					</tr>
					<tr>
						<td id="outname6">肇嘉浜路</td>
						<td><div id="outimg6"></div><div id="outdivPb6" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum6">214</td>
					</tr>
					<tr>
						<td id="outname7">徐家汇</td>
						<td><div id="outimg7"></div><div id="outdivPb7" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum7">211</td>
					</tr>
					<tr>
						<td id="outname8">徐家汇</td>
						<td><div id="outimg8"></div><div id="outdivPb8" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum8">211</td>
					</tr>
					<tr>
						<td id="outname9">徐家汇</td>
						<td><div id="outimg9"></div><div id="outdivPb9" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum9">211</td>
					</tr>
					<tr>
						<td id="outname10">徐家汇</td>
						<td><div id="outimg10"></div><div id="outdivPb10" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum10">211</td>
					</tr>
				</table>
				</div>
				</div>
		
		</div>
		<div id="tabs-3" style="padding:0;">
		<div class="container">
			<div class="sub-con cur-sub-con" align="center">
				<table
					style="font-size: 17px; color: white;font-family:Microsoft Yahei;">
					<tr height=60>
					</tr>
					<tr>
						<td width="136" id="subname1">肇家浜</td>
						<td width="60"><div id="subimg1"></div><div id="subdivPb1" style="width: 50px; height: 5px;"></div></td>
						<td width="60" id="subnum1">398</td>
					</tr>
					<tr>
						<td id="subname2">曹杨路</td>
						<td><div id="subimg2"></div><div id="subdivPb2" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum2">249</td>
					</tr>
					<tr>
						<td id="subname3">镇坪路</td>
						<td><div id="subimg3"></div><div id="subdivPb3" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum3">212</td>
					</tr>
					<tr>
						<td id="subname4">南京东路</td>
						<td><div id="subimg4"></div><div id="subdivPb4" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum4">155</td>
					</tr>
					<tr>
						<td id="subname5">人民广场</td>
						<td><div id="subimg5"></div><div id="subdivPb5" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum5">120</td>
					</tr>
					<tr>
						<td id="subname6">宜山路</td>
						<td><div id="subimg6"></div><div id="subdivPb6" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum6">89</td>
					</tr>
					<tr>
						<td id="subname7">陕西南路</td>
						<td><div id="subimg7"></div><div id="subdivPb7" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum7">78</td>
					</tr>
					<tr>
						<td id="subname8">陕西南路</td>
						<td><div id="subimg8"></div><div id="subdivPb8" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum8">78</td>
					</tr>
					<tr>
						<td id="subname9">陕西南路</td>
						<td><div id="subimg9"></div><div id="subdivPb9" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum9">78</td>
					</tr>
					<tr>
						<td id="subname10">陕西南路</td>
						<td><div id="subimg10"></div><div id="subdivPb10" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum10">78</td>
					</tr>
				</table>
				</div>
				</div>
		
		</div>
		<div id="tabs-4" style="padding:0;">
		<div class="container">
			<div class="sub-con cur-sub-con" align="center">
				<table 
					style="font-size: 17px; color: white;font-family:Microsoft Yahei;">
					<tr height=60>
					</tr>
					<tr>
						<td width="136" id="sumname1">虹桥火车站</td>
						<td width="60"><div id="sumimg1"></div><div id="sumdivPb1" style="width: 50px; height: 5px;"></div></td>
						<td width="60" id="sumnum1">2621</td>
					</tr>
					<tr>
						<td id="sumname2">上海火车站</td>
						<td><div id="sumimg2"></div><div id="sumdivPb2" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum2">2456</td>
					</tr>
					<tr>
						<td id="sumname3">人民广场</td>
						<td><div id="sumimg3"></div><div id="sumdivPb3" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum3">1920</td>
					</tr>
					<tr>
						<td id="sumname4">衡山路</td>
						<td><div id="sumimg4"></div><div id="sumdivPb4" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum4">1587</td>
					</tr>
					<tr>
						<td id="sumname5">常熟路</td>
						<td><div id="sumimg5"></div><div id="sumdivPb5" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum5">904</td>
					</tr>
					<tr>
						<td id="sumname6">肇嘉浜路</td>
						<td><div id="sumimg6"></div><div id="sumdivPb6" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum6">826</td>
					</tr>
					<tr>
						<td id="sumname7">宜山路</td>
						<td><div id="sumimg7"></div><div id="sumdivPb7" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum7">454</td>
					</tr>
					<tr>
						<td id="sumname8">宜山路</td>
						<td><div id="sumimg8"></div><div id="sumdivPb8" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum8">454</td>
					</tr>
					<tr>
						<td id="sumname9">宜山路</td>
						<td><div id="sumimg9"></div><div id="sumdivPb9" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum9">454</td>
					</tr>
					<tr>
						<td id="sumname10">宜山路</td>
						<td><div id="sumimg10"></div><div id="sumdivPb10" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum10">454</td>
					</tr>
				</table>
				</div>
				</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			
			(function update_time() {

				refreshTopAndIn();
				refreshOut();
				refreshSub();
				refreshSum();
				
				// 每5分钟运行一次
				setTimeout(update_time, 1000*60*5);

			})();
			
			
		
			//$("#titleName").css("background-image","url('"+"../../style/images/titleAll_top2.png"+"')");		
		$("#tabs").tabs();
	});
		
	function refreshTopAndIn(){
		$.getJSON("../../dams/metro/od/fheatDataIn", { "resultType": "json" }, function(data, textStatus)
				{
					//alert(data.top.totoal);
					$("#totalCount").html("<span style='font-size:16px;color:red'>"+data.top.totoal+"</span>");
					$("#lineStation").html("<span style='font-size:20px;color:red'>"+data.top.name+"<br>"+data.top.ename+"</span>");
					$("#inCount").html("<span style='font-size:16px;color:red'>"+data.top.count+"</span>");
					$("#updatetime").html(data.top.updateTime);
					
					for(i=0;i<10;i++){
						$(("#inname"+(i+1))).html(data.right[i].name);
						countNum=data.right[i].count;
						$(("#innum"+(i+1))).html(countNum);
						level=data.right[i].level.split(",");
						
						if(countNum<parseInt(level[0])){
							$(("#inimg"+(i+1))).html("<img src='${APP_PATH}style/images/GreenPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[1])){
							$(("#inimg"+(i+1))).html("<img src='${APP_PATH}style/images/YellowPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[2])){
							$(("#inimg"+(i+1))).html("<img src='${APP_PATH}style/images/OrangePerson.png' style='height: 16px;' />");
						}else{
							$(("#inimg"+(i+1))).html("<img src='${APP_PATH}style/images/RedPerson.png' style='height: 16px;' />");
						}
						percent=Math.ceil(countNum/2000*100);
						$(("#indivPb"+(i+1))).progressbar({value: percent});
					}
				});
	}	
	
	function refreshOut(){
		$.getJSON("../../dams/metro/od/fheatDataOut", { "resultType": "json" }, function(data, textStatus)
				{
					for(i=0;i<10;i++){
						$(("#outname"+(i+1))).html(data.right[i].name);
						countNum=data.right[i].count;
						$(("#outnum"+(i+1))).html(countNum);
						
						level=data.right[i].level.split(",");
						if(countNum<parseInt(level[0])){
							$(("#outimg"+(i+1))).html("<img src='${APP_PATH}style/images/GreenPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[1])){
							$(("#outimg"+(i+1))).html("<img src='${APP_PATH}style/images/YellowPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[2])){
							$(("#outimg"+(i+1))).html("<img src='${APP_PATH}style/images/OrangePerson.png' style='height: 16px;' />");
						}else{
							$(("#outimg"+(i+1))).html("<img src='${APP_PATH}style/images/RedPerson.png' style='height: 16px;' />");
						}
						
						percent=Math.ceil(countNum/2000*100);
						$(("#outdivPb"+(i+1))).progressbar({value: percent});
					}
				});
	}
	
	function refreshSub(){
		$.getJSON("../../dams/metro/od/fheatDataSub", { "resultType": "json" }, function(data, textStatus)
				{
					for(i=0;i<10;i++){
						$(("#subname"+(i+1))).html(data.right[i].name);
						countNum=data.right[i].count;
						$(("#subnum"+(i+1))).html(countNum);
						
						level=data.right[i].level.split(",");
						if(countNum<parseInt(level[0])){
							$(("#subimg"+(i+1))).html("<img src='${APP_PATH}style/images/GreenPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[1])){
							$(("#subimg"+(i+1))).html("<img src='${APP_PATH}style/images/YellowPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[2])){
							$(("#subimg"+(i+1))).html("<img src='${APP_PATH}style/images/OrangePerson.png' style='height: 16px;' />");
						}else{
							$(("#subimg"+(i+1))).html("<img src='${APP_PATH}style/images/RedPerson.png' style='height: 16px;' />");
						}
						
						percent=Math.ceil(countNum/2000*100);
						$(("#subdivPb"+(i+1))).progressbar({value: percent});
					}
				});
	}
	
	function refreshSum(){
		$.getJSON("../../dams/metro/od/fheatDataSum", { "resultType": "json" }, function(data, textStatus)
				{
					for(i=0;i<10;i++){
						$(("#sumname"+(i+1))).html(data.right[i].name);
						countNum=data.right[i].count;
						$(("#sumnum"+(i+1))).html(countNum);
						
						level=data.right[i].level.split(",");
						if(countNum<parseInt(level[0])){
							$(("#sumimg"+(i+1))).html("<img src='${APP_PATH}style/images/GreenPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[1])){
							$(("#sumimg"+(i+1))).html("<img src='${APP_PATH}style/images/YellowPerson.png' style='height: 16px;' />");
						}else if(countNum<parseInt(level[2])){
							$(("#sumimg"+(i+1))).html("<img src='${APP_PATH}style/images/OrangePerson.png' style='height: 16px;' />");
						}else{
							$(("#sumimg"+(i+1))).html("<img src='${APP_PATH}style/images/RedPerson.png' style='height: 16px;' />");
						}
						
						percent=Math.ceil(countNum/2000*100);
						$(("#sumdivPb"+(i+1))).progressbar({value: percent});
					}
				});
	}
	
	
	
	</script> 

</body>

</html>