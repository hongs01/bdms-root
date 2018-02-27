<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海轨道交通实时监控</title>
<jsp:include page="../header/metroHeader.jsp"></jsp:include>

<style type="text/css">
	/**
	 *	Basic Layout Theme
	 * 
	 *	This theme uses the default layout class-names for all classes
	 *	Add any 'custom class-names', from options: paneClass, resizerClass, togglerClass
	 */
	.ui-layout-pane { /* all 'panes' */ 
		background: #FFF; 
		border: 0px solid #BBB; 
		overflow: hidden;
	} 
	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: #1b1b1b; 
	} 
	.ui-layout-toggler { /* all 'toggler-buttons' */ 
		background:url("../../../style/images/leftarrow.png");
	}
	
	.ui-helper-reset{
		line-height:3;
	}
	
	.ui-widget-header{
		background:#03233a;
	}
	
	.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active{
		background:#03233a;
	}
	.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default{
		background:#03233a;
	}
	
	
	</style>

  <script type="text/javascript">
	
	 $(function() {
		//layout
		myLayout = $("body").layout(layoutSettings_Outer);
		//myLayout.sizePane("north",-50);
		myLayout.sizePane("east",400);
		 
		$( ".ui-layout-toggler" ).on("click",function(event,ui){
			
			//changeStationImgData(station);
			len=$("#fheatmain").css("width");
			len=len.replace("px","");
				if(parseInt(len)>1100){
					$( ".ui-layout-toggler" ).css("background-image","url('"+"../../../style/images/leftarrow.png"+"')");
					
					//$("#fheatmain").css("width","1400");
					//myChart.resize();
					//alert("resized");
					
				}else{
					$( ".ui-layout-toggler" ).css("background-image","url('"+"../../../style/images/rightarrow.png"+"')");
				
					//$("#fheatmain").css("width","1000");
					//myChart.resize();
					//alert("resized");
				}
		});
	}); 
	  
</script>  

<script type="text/javascript">

/* 在界面上显示鼠标所在的位置 */
function show_coords(event){
	 var x = event.clientX;
	 var y = event.clientY;
	 var say = document.all("coords");
	 say.innerHTML = "X:"+x+" Y:"+y;
	 say.style.position = "absolute";
	 say.style.left = x + 30;
	 say.style.top = y;
}

 function formatNum(str){ 
	 str=str+"";
		var newStr = ""; var count = 0;   
		if(str.indexOf(".")==-1){ 
			for(var i=str.length-1;i>=0;i--){  
				if(count % 3 == 0 && count != 0){    
					newStr = str.charAt(i) + "," + newStr;  
				}else{    
					newStr = str.charAt(i) + newStr;  
				}  
				count++;    
				}    
			str = newStr;     
			return str; 
		} else{ 
			for(var i = str.indexOf(".")-1;i>=0;i--){  
				if(count % 3 == 0 && count != 0){   
					newStr = str.charAt(i) + "," + newStr;  
				}else{    
					newStr = str.charAt(i) + newStr;   
					}  
				count++;    
				}    
			str = newStr + (str).substr((str + "00").indexOf("."),3);    
			return str;  
			} 
		}  
</script>

</head>


<body>
    <!-- start header -->
     
    <!-- end   header-->
<div class="ui-layout-center west-body-background">
	<!-- start map -->
<div id="fheatmain" style="height:800px;width:100%;text-align: center;margin:0 auto; float:left;">	</div>

<script type="text/javascript">
var theme= "${THEME}";
var white=false;
if("smothness" == theme)
{
	white=true;	
}
	var myChart = echarts.init(document.getElementById('fheatmain'));
	//自定义扩展图表类型：mapType = body
	echarts.util.mapData.params.params.OD = {
		    getGeoJson: function (callback) {
		        $.ajax({
		            url:'${APP_PATH}style/9.76.svg',
		            dataType: 'xml',
		            success: function(xml) {
		            	 
		                callback(xml)
		                
		            }
		        });
		    }
		} 

$(function(){
$.ajax({
	 url:'${APP_PATH}dams/metro/heatMetro/data',
	 dataType:'json',
	 success:function(data)
	 { 
		 createOption(data,0);		 
	 }
 }); 
 
setInterval('animate();', 60000*5); 
 
}); 

function createOption(hd,flag){
	var heatData = []; 
	//alert(hd);
	var timestr=hd[0][3]+"";
	$("#day").html(timestr.substr(0,2)+":"+timestr.substr(2,2)+":"+timestr.substr(4,2));
	if(flag==0){
	    for(var k=0;k<hd.length;k++){
	    	var renders=Math.floor(hd[k][2]/hd[0][2]*10+1);
	    	
			for (var i = 0; i < renders; ++i) {
			    heatData.push([
			        hd[k][0],
			        hd[k][1],
			        Math.random()*5
			    ]);
			}  
	    }
	}else{
		var renders=Math.floor(hd[flag-1][2]/hd[0][2]*20+1);
		for (var i = 0; i < renders; ++i) {
		    heatData.push([
		        hd[flag-1][0],
		        hd[flag-1][1],
		        Math.random()*5
		    ]);
		}
	}
	option = {
	    backgroundColor: '#1b1b1b',
	    /* title : {
	        x:'center',
	        textStyle: {
	            color: 'blue'
	        }
	    }, */
	    tooltip : {
	        trigger: 'item',
	        formatter: '{b}'
	    },
	    color: ['white', 'rgba(100, 149, 237, 1)', 'green'],
	    series : [
	        {
	            name: 'OD轨道交通热力',
	            type: 'map',
	            mapType: 'OD',
	            radius: '1',
	            roam: true,
	            mapLocation:{
	            	position: 'absolute',
		            display: 'none',
		            padding: '5px', 
		            left: '200px',
		            top: '0px',
		            width:'150%',
		            height:'150%'
	            },
	            gradientColors: [
	                            {
	                offset: 0.1,
	                color: 'green'
	            }, 
	            {
	                offset: 0.2,
	                color: 'yellow'
	            }, {
	                offset: 0,
	                color: 'orange'
	            }, {
	                offset: 0,
	                color: 'red'
	            }],
	            hoverable: true,
	            data:[],
	            heatmap: {
	                minAlpha: 0.24,
	                data: heatData
	            },
	            valueScale:2,
	            blurSize:2,
	            /* itemStyle: {
	                normal: {
	                    borderColor:'white',
	                    borderWidth:0.1,
	                    areaStyle: {
	                        color: '#1b1b1b'
	                    }
	                }
	            } */
	            itemStyle : {
                    normal: {
                        borderWidth:2,     //线宽
                        lineStyle: {
                            type: 'solid',
                            shadowBlur: 10
                        }
                    }
                },
	        }
	    ]
	};
	myChart.setOption(option);
}

</script>

<!-- end map -->
<div style="margin-left:60px;z-index:-1">
	
	<div class="update-div"><img width="20px" height="20px" src="${APP_PATH}style/images/timeupdate.png"></img></div>数据更新时间：<span id="day"></span></div>
    </div>			 
</div>
 
<div class="ui-layout-east east-div" style="filter:alpha(opacity=0.5;)"> 

	<div id="tabs" style="width:400px;height:100%;" class="only-for-hotline">
	  	<ul style="width:396px" >
		    <li style="width:91px" class="only-for-hotline"><a href="#tabs-1" class="only-for-hotline"><img width="65" height="45" src="${APP_PATH}style/images/in2.png"/></a></li>
		    <li style="width:91px" class="only-for-hotline"><a href="#tabs-2" class="only-for-hotline"><img width="65" height="45" src="${APP_PATH}style/images/out.png" /></a></li>
		    <li style="width:91px" class="only-for-hotline"><a href="#tabs-3" class="only-for-hotline"><img width="65" height="45" src="${APP_PATH}style/images/inout2.png" /></a></li>
		    <li style="width:91px" class="only-for-hotline"><a href="#tabs-4" class="only-for-hotline"><img width="65" height="45" src="${APP_PATH}style/images/inout1.png" /></a></li>
		  </ul>
	  <div id="tabs-1" style="padding:0;">
		<div class="container">
			<div class="sub-con cur-sub-con only-for-hotline" align="center">
				<table class="just-font only-for-hotline"
					style="font-size: 17px;font-family:Microsoft Yahei;" >
					<tr height="50px">
						<!-- <td onclick="animate();">显示全部 </td> -->
					</tr>
					<tr height="50px">
						<td width="136" id="inname1">虹桥火车站</td>
						<td width="60">
							<div id="inimg1"></div>
							<div id="indivPb1" style="width: 50px; height: 5px;"></div>
						</td>
						<td width="60" id="innum1">1246</td>
					</tr>
					<tr height="50px">
						<td id="inname2">人民广场</td>
						<td>
							<div id="inimg2"></div>
							<div id="indivPb2" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum2">1021</td>
					</tr>
					<tr height="50px">
						<td id="inname3">上海火车站</td>
						<td>
							<div id="inimg3"></div>
							<div id="indivPb3" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum3">900</td>
					</tr>
					<tr height="50px">
						<td id="inname4">衡山路</td>
						<td>
							<div id="inimg4"></div>
							<div id="indivPb4" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum4">775</td>
					</tr>
					<tr height="50px">
						<td id="inname5">肇嘉浜路</td>
						<td>
							<div id="inimg5"></div>
							<div id="indivPb5" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum5">612</td>
					</tr>
					<tr height="50px">
						<td id="inname6">常熟路</td>
						<td>
							<div id="inimg6"></div>
							<div id="indivPb6" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum6">342</td>
					</tr>
					<tr height="50px">
						<td id="inname7">宜山路</td>
						<td>
							<div id="inimg7"></div>
							<div id="indivPb7" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum7">331</td>
					</tr>
					<tr height="50px">
						<td id="inname8">宜山路</td>
						<td>
							<div id="inimg8"></div>
							<div id="indivPb8" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum8">331</td>
					</tr>
					<tr height="50px">
						<td id="inname9">宜山路</td>
						<td>
							<div id="inimg9"></div>
							<div id="indivPb9" style="width: 50px; height: 5px;"></div>
						</td>
						<td id="innum9">331</td>
					</tr>
					<tr height="50px">
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
	  	<div id="tabs-2" style="padding:0;" class="only-for-hotline">
		<div class="container">
			<div class="sub-con cur-sub-con only-for-hotline" align="center">
				<table class="just-font only-for-hotline" 	style="font-size: 17px;font-family:Microsoft Yahei;">
					<tr height="50px">
					</tr>
					<tr height="50px">
						<td width="136" id="outname1">虹桥火车站</td>
						<td width="60">
							<div id="outimg1"></div>
							<div id="outdivPb1" style="width: 50px; height: 5px;"></div>
						</td>
						<td width="60" id="outnum1">1375</td>
					</tr>
					<tr height="50px">
						<td id="outname2">上海火车站</td>
						<td>
							<div id="outimg2"></div>
							<div id="outdivPb2" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum2">1556</td>
					</tr>
					<tr height="50px">
						<td id="outname3">人民广场</td>
						<td><div id="outimg3"></div><div id="outdivPb3" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum3">899</td>
					</tr>
					<tr height="50px">
						<td id="outname4">衡山路</td>
						<td><div id="outimg4"></div><div id="outdivPb4" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum4">812</td>
					</tr>
					<tr height="50px">
						<td id="outname5">常熟路</td>
						<td><div id="outimg5"></div><div id="outdivPb5" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum5">562</td>
					</tr>
					<tr height="50px">
						<td id="outname6">肇嘉浜路</td>
						<td><div id="outimg6"></div><div id="outdivPb6" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum6">214</td>
					</tr>
					<tr height="50px">
						<td id="outname7">徐家汇</td>
						<td><div id="outimg7"></div><div id="outdivPb7" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum7">211</td>
					</tr>
					<tr height="50px">
						<td id="outname8">徐家汇</td>
						<td><div id="outimg8"></div><div id="outdivPb8" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum8">211</td>
					</tr>
					<tr height="50px">
						<td id="outname9">徐家汇</td>
						<td><div id="outimg9"></div><div id="outdivPb9" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum9">211</td>
					</tr>
					<tr height="50px">
						<td id="outname10">徐家汇</td>
						<td><div id="outimg10"></div><div id="outdivPb10" style="width: 50px; height: 5px;"></div></td>
						<td id="outnum10">211</td>
					</tr>
				</table>
				</div>
				</div>
		
		</div>
		<div id="tabs-3" style="padding:0;" class="only-for-hotline">
		<div class="container">
			<div class="sub-con cur-sub-con only-for-hotline" align="center">
				<table class="just-font only-for-hotline"
					style="font-size: 17px;font-family:Microsoft Yahei;">
					<tr height="50px">
					</tr>
					<tr height="50px">
						<td width="136" id="sumname1">虹桥火车站</td>
						<td width="60"><div id="sumimg1"></div><div id="sumdivPb1" style="width: 50px; height: 5px;"></div></td>
						<td width="60" id="sumnum1">2621</td>
					</tr>
					<tr height="50px">
						<td id="sumname2">上海火车站</td>
						<td><div id="sumimg2"></div><div id="sumdivPb2" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum2">2456</td>
					</tr>
					<tr height="50px">
						<td id="sumname3">人民广场</td>
						<td><div id="sumimg3"></div><div id="sumdivPb3" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum3">1920</td>
					</tr>
					<tr height="50px">
						<td id="sumname4">衡山路</td>
						<td><div id="sumimg4"></div><div id="sumdivPb4" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum4">1587</td>
					</tr>
					<tr height="50px">
						<td id="sumname5">常熟路</td>
						<td><div id="sumimg5"></div><div id="sumdivPb5" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum5">904</td>
					</tr>
					<tr height="50px">
						<td id="sumname6">肇嘉浜路</td>
						<td><div id="sumimg6"></div><div id="sumdivPb6" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum6">826</td>
					</tr>
					<tr height="50px">
						<td id="sumname7">宜山路</td>
						<td><div id="sumimg7"></div><div id="sumdivPb7" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum7">454</td>
					</tr>
					<tr height="50px">
						<td id="sumname8">宜山路</td>
						<td><div id="sumimg8"></div><div id="sumdivPb8" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum8">454</td>
					</tr>
					<tr height="50px">
						<td id="sumname9">宜山路</td>
						<td><div id="sumimg9"></div><div id="sumdivPb9" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum9">454</td>
					</tr>
					<tr height="50px">
						<td id="sumname10">宜山路</td>
						<td><div id="sumimg10"></div><div id="sumdivPb10" style="width: 50px; height: 5px;"></div></td>
						<td id="sumnum10">454</td>
					</tr>
				</table>
				</div>
				</div>
		
		
		</div>
		<div id="tabs-4" style="padding:0;" class="only-for-hotline">
		<div class="container">
			<div class="sub-con cur-sub-con only-for-hotline" align="center">
				<table class="just-font only-for-hotline"
					style="font-size: 17px;font-family:Microsoft Yahei;">
					<tr height="50px">
					</tr>
					<tr height="50px">
						<td width="136" id="subname1">肇家浜</td>
						<td width="60"><div id="subimg1"></div><div id="subdivPb1" style="width: 50px; height: 5px;"></div></td>
						<td width="60" id="subnum1">398</td>
					</tr>
					<tr height="50px">
						<td id="subname2">曹杨路</td>
						<td><div id="subimg2"></div><div id="subdivPb2" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum2">249</td>
					</tr>
					<tr height="50px">
						<td id="subname3">镇坪路</td>
						<td><div id="subimg3"></div><div id="subdivPb3" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum3">212</td>
					</tr>
					<tr height="50px">
						<td id="subname4">南京东路</td>
						<td><div id="subimg4"></div><div id="subdivPb4" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum4">155</td>
					</tr>
					<tr height="50px">
						<td id="subname5">人民广场</td>
						<td><div id="subimg5"></div><div id="subdivPb5" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum5">120</td>
					</tr>
					<tr height="50px">
						<td id="subname6">宜山路</td>
						<td><div id="subimg6"></div><div id="subdivPb6" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum6">89</td>
					</tr>
					<tr height="50px">
						<td id="subname7">陕西南路</td>
						<td><div id="subimg7"></div><div id="subdivPb7" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum7">78</td>
					</tr>
					<tr height="50px">
						<td id="subname8">陕西南路</td>
						<td><div id="subimg8"></div><div id="subdivPb8" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum8">78</td>
					</tr>
					<tr height="50px">
						<td id="subname9">陕西南路</td>
						<td><div id="subimg9"></div><div id="subdivPb9" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum9">78</td>
					</tr>
					<tr height="50px">
						<td id="subname10">陕西南路</td>
						<td><div id="subimg10"></div><div id="subdivPb10" style="width: 50px; height: 5px;"></div></td>
						<td id="subnum10">78</td>
					</tr>
				</table>
				</div>
				</div>
		</div>
	</div>
	</div>
	
	
	
	
	
	
	<script type="text/javascript">
		$(function() {
			
			refreshTopAndIn();
			refreshOut();
			refreshSub();
			refreshSum();
					
		
			//$("#titleName").css("background-image","url('"+"../../style/images/titleAll_top2.png"+"')");		
		//$("#tabs").tabs();
			    $( "#tabs" ).tabs({
			      event: "mouseover"
			    });
	});
		
	function refreshTopAndIn(){
		$.getJSON("${APP_PATH}dams/metro/heatMetro/fheatDataIn", { "resultType": "json" }, function(data, textStatus)
				{
					//alert(data.top.totoal);
					$("#totalCount").html("<span style='font-size:16px;color:lightblue'>"+data.top.totoal+"</span>");
					$("#lineStation").html("<span style='font-size:20px;color:lightblue'>"+data.top.name+"<br>"+data.top.ename+"</span>");
					$("#inCount").html("<span style='font-size:16px;color:lightblue'>"+data.top.count+"</span>");
					$("#updatetime").html(data.top.updateTime);
					
					for(i=0;i<10;i++){
						//$(("#inname"+(i+1))).html("<span onclick='animate("+(i+1)+")' style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+data.right[i].name+"</span>");
						$(("#inname"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+data.right[i].name+"("+data.right[i].line+")"+"</span>");
						
						countNum=data.right[i].count;
						$(("#innum"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+formatNum(countNum)+"</span>");
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
		$.getJSON("${APP_PATH}dams/metro/heatMetro/fheatDataOut", { "resultType": "json" }, function(data, textStatus)
				{
					for(i=0;i<10;i++){
						$(("#outname"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+data.right[i].name+"("+data.right[i].line+")"+"</span>");
						countNum=data.right[i].count;
						$(("#outnum"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+formatNum(countNum)+"</span>");
						
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
		$.getJSON("${APP_PATH}dams/metro/heatMetro/fheatDataSub", { "resultType": "json" }, function(data, textStatus)
				{
					for(i=0;i<10;i++){
						$(("#subname"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+data.right[i].name+"("+data.right[i].line+")"+"</span>");
						countNum=data.right[i].count;
						$(("#subnum"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+formatNum(countNum)+"</span>");
						
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
		$.getJSON("${APP_PATH}dams/metro/heatMetro/fheatDataSum", { "resultType": "json" }, function(data, textStatus)
				{
					for(i=0;i<10;i++){
						$(("#sumname"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+data.right[i].name+"("+data.right[i].line+")"+"</span>");
						countNum=data.right[i].count;
						$(("#sumnum"+(i+1))).html("<span style='font-size:16px;font-weight:bold;font-family:微软雅黑;'>"+formatNum(countNum)+"</span>");
						
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
	
	function animate(id){
		//alert(id);
		//alert(myChart.getOption().backgroundColor);
		//alert(myChart.getOption().series[0].heatmap.data);
		//myChart.restore();
		myChart.clear();
		$.ajax({
			 url:'${APP_PATH}dams/metro/heatMetro/data',
			 dataType:'json',
			 success:function(data)
			 {
				if(id==undefined){
					id=0;
				}
				createOption(data,id);		
				
			 }
		 });
	}
	
	function formatNum(str){ 
		 str=str+"";
			var newStr = ""; var count = 0;   
			if(str.indexOf(".")==-1){ 
				for(var i=str.length-1;i>=0;i--){  
					if(count % 3 == 0 && count != 0){    
						newStr = str.charAt(i) + "," + newStr;  
					}else{    
						newStr = str.charAt(i) + newStr;  
					}  
					count++;    
					}    
				str = newStr;  
				if(str.indexOf("-,")!=-1){
					str=str.replace("-,","-");
				}
				return str; 
			} else{ 
				for(var i = str.indexOf(".")-1;i>=0;i--){  
					if(count % 3 == 0 && count != 0){   
						newStr = str.charAt(i) + "," + newStr;  
					}else{    
						newStr = str.charAt(i) + newStr;   
						}  
					count++;    
					}    
				str = newStr + (str).substr((str + "00").indexOf("."),3);  
				if(str.indexOf("-,")!=-1){
					str=str.replace("-,","-");
				}
				return str;  
				} 
			}
	
	</script> 
</div>




</body>
</html>