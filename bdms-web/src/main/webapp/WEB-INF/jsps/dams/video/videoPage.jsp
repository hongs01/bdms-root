<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频数据实时监控</title>
<jsp:include page="../header/metroHeader.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${APP_PATH}js/layout/complex.css" />
<script type="text/javascript" src="${APP_PATH}js/layout/jquery.layout.js"></script>
<script type="text/javascript" src="${APP_PATH}js/layout/complex.js"></script>

<style type="text/css">

	/**
	 *	Basic Layout Theme
	 * 
	 *	This theme uses the default layout class-names for all classes
	 *	Add any 'custom class-names', from options: paneClass, resizerClass, togglerClass
	 */
	.ui-layout-pane { /* all 'panes' */ 
		border: 0px solid #BBB; 
	} 
	</style>
	
	


<script type="text/javascript">

function formatDate(str){
	//alert("str is:"+str);
	 var data=str.substring(8,10)+":"+str.substring(10,12)+":"+str.substring(12,14);
	 return data;
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
             
	
	$(function() {
		
		//layout
		myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("east",350);
		 
		
		//select area and video
		//$("#gis").button();
	    $("#selectarea").selectmenu({width: 90});
		$("#selectvideo").selectmenu({width: 150});
		
		
		$( "#gis" ).on("click",function(event,ui){
			var cameraId=$("#selectvideo").val();
			$.getJSON("${APP_PATH}dams/video/videoPage/cameranamesbyvideoId/"+cameraId, { "resultType": "json" }, function(data, textStatus)
					{
						//alert(data.cameraVRCId);
						//这个位置调用gis
						
					});
			
		});
		
		//下拉列表数据
		  var sid="${sid}";
		  //alert(sid);
		
		$.getJSON("${APP_PATH}dams/video/videoPage/videos", { "resultType": "json" }, function(data, textStatus)
				{
			$("#selectarea").empty();//先清空tbody  
			var strHTML =""; 
			strHTML22="";
			var temp="";
			var selectedAreaId="";
			$.each(data,function(InfoIndex,Info){//遍历json里的数据  
				if(Info.cameraId==sid){
					if(Info.areaName!=temp){
					 	temp=Info.areaName;
						strHTML += "<option value="+Info.areaId+" selected='selected'>"+temp+"</option>"; 
						selectedAreaId=Info.areaId;
					}
				}else{
					if(Info.areaName!=temp){
					 	temp=Info.areaName;
						strHTML += "<option value="+Info.areaId+">"+temp+"</option>"; 
					}
				}
			}); 
			//alert(selectedAreaId);
			if(selectedAreaId==""){
				selectedAreaId=data[0].areaId;
			}
			//alert(data[0].areaId);
			$.getJSON("${APP_PATH}dams/video/videoPage/cameranamesbyareaId/"+selectedAreaId, { "resultType": "json" }, function(data, textStatus)
					{
					   $("#selectvideo").empty();//先清空tbody  
					  var selectedlineSInfo="";
					   var selectedcameraId="";
						$.each(data,function(InfoIndex2,Info2){//遍历json里的数据  
							//alert(Info2.cameraId);
							if(InfoIndex2==0){
								selectedlineSInfo="<span>"+Info2.areaName+" </span>"+"<span>"+Info2.cameraName+"</span>";
								selectedcameraId=Info2.cameraId;
							} 
							if(Info2.cameraId==sid){
								strHTML22 += "<option value="+Info2.cameraId+" selected='selected'>"+Info2.cameraName+"</option>"; 
								selectedlineSInfo="<span>"+Info2.areaName+" </span>"+"<span>"+Info2.cameraName+"</span>";
								selectedcameraId=Info2.cameraId;
							}else{
								strHTML22 += "<option value="+Info2.cameraId+">"+Info2.cameraName+"</option>"; 
							}
						}); 
						
						$("#lineStatInfo").html(selectedlineSInfo);
						changeStationImgData(selectedcameraId);
					   
					   		
					   
						$("#selectvideo").html(strHTML22);//显示到tbody中
						$("#selectvideo").selectmenu("refresh");
					});
			$("#selectarea").html(strHTML);//显示到tbody中
			$("#selectarea").selectmenu("refresh");
			
		});
		$("#selectarea").on("selectmenuchange",function( event, ui){
			var areaId=$("#selectarea").val();
			 //alert(areaId);
			 
			//changeStationImgData(stat);
			
			$.getJSON("${APP_PATH}dams/video/videoPage/cameranamesbyareaId/"+areaId, { "resultType": "json" }, function(data, textStatus)
					{
					   $("#selectvideo").empty();//先清空tbody  
					  // alert(data.length); 
					    strHTML2="";
						$.each(data,function(InfoIndex,Info){//遍历json里的数据  
							strHTML2 += "<option value="+Info.cameraId+">"+Info.cameraName+"</option>";  
							if(InfoIndex==0){
								$("#lineStatInfo").html("<span>"+Info.areaName+" </span>"+"<span>"+Info.cameraName+"</span>");
								changeStationImgData(Info.cameraId);
							}
						});  
						 
						$("#selectvideo").html(strHTML2);//显示到tbody中
						
						
						$("#selectvideo").selectmenu("refresh");

						
					});
		});
		$("#selectvideo").on("selectmenuchange",function( event, ui){
			var cameraId=$("#selectvideo").val();
			// alert(cameraId);
			//$("#lineStatInfo").html(Info.cameraName+)
			
			$.getJSON("${APP_PATH}dams/video/videoPage/cameranamesbyvideoId/"+cameraId, { "resultType": "json" }, function(data, textStatus)
					{
				//alert(data.cameraId);
				changeStationImgData(data.cameraId);
				$("#lineStatInfo").html("<span>"+data.areaName+" </span>"+"<span>"+data.cameraName+"</span>");
					});
			
		});
		 
			$( ".ui-layout-toggler" ).on("click",function(event,ui){
				var cameraId=$("#selectvideo").val();
				changeStationImgData(cameraId);
				len=$("#main").css("width");
				len=len.replace("px","");
					//alert(len.substring(0));
					//alert($("#main").css("width">1100));
					if(parseInt(len)>1000){
						$( ".ui-layout-toggler" ).css("background-image","url('"+"${APP_PATH}style/images/leftarrow.png"+"')");
					}else{
						$( ".ui-layout-toggler" ).css("background-image","url('"+"${APP_PATH}style/images/rightarrow.png"+"')");
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

function refreshPNums(num1){
	refreshPNum("#ren",num1);
}

 function refreshPNum(id,num){
	 
	     var codeStr="";
		// alert(codeStr);
		 var typeStr="";
	 
	 $.getJSON("${APP_PATH}dams/metro/in/criterion", { code:codeStr,type:typeStr,"resultType": "json" }, function(data, textStatus)
		{	
			level=data.level.split(",");
		 
			if(num==0){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/grren.png"+"')");
			}else if(num<parseInt(level[0])*pSize){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/luren.png"+"')");
			}else if(num<parseInt(level[1])*pSize){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/yeren.png"+"')");
			}else if(num<parseInt(level[2])*pSize){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/orren.png"+"')");
			}else{
				$(id).css("background-image","url('"+"${APP_PATH}style/images/reren.png"+"')");
			}
			if(num<0){
				percent=0;
			}else{
				percent=Math.ceil(num/parseInt(level[2])*100);
			}
			if(numh<0){
				percenth=0;
			}else{
				percenth=Math.ceil(numh/parseInt(level[2])*100);
			}
			
			$((id+"num")).html("<span class='just-font' style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:left;font-weight:bold;width:280px;font-family: '微软雅黑';'>"+formatNum(num)+"（"+formatNum(numh)+"）</span>");
 		});
 }
 function changeTitleBg(index){
	 if(index=="top"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_top.png"+"')");
	 }else if(index=="line"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_line.png"+"')");
	 }else if(index=="all"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll.png"+"')");
	 }else if(index=="line_hc"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_line_hc.png"+"')");
	 }else if(index=="all_hc"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_hc.png"+"')");
	 }
 }
 
 
</script>

</head>


<body>
    <!-- start header -->
     
    <!-- end   header-->
<div class="ui-layout-center west-body-background" >
	<div style="width:100%; height:100px;">
		<div id="lineStatInfo"><span class="font-family-color"></span></div>
	</div>
	<div id="main" style="height:400px;width:100%; float:left;"></div>
	<div style="margin-left:60px">
	<div style="float:left;margin-top:4px"><img width="20px" height="20px" src="${APP_PATH}style/images/timeupdate.png"></img></div>
	<div class="update-div">数据更新时间：<span id="updatetime"></span></div>
	</div>				 
</div>
 
<div class="ui-layout-east east-div"> 
	<!-- start right -->
	<div id="right" style="float:left;width:380px;padding-top:10px">
		<div style="padding: 5px;width: 100%;">
			<div style="padding: 5px;float: left;">
					<select id="selectarea">
						<option value=""></option>
					</select>
			</div>
			<div style="padding: 5px;float: left;">
				 <select id="selectvideo">
					<option value=""></option>
				</select>
			</div>
			<input type="hidden" id="gisval" value="111"></input>
			<div style="padding: 5px;float: left;">
			   <a id="gis" style="font-family: '微软雅黑'; font-size: 13px; width: 60px;"></a>
		    </div>
		</div>
		
		<div style="width: 380px ;margin-top: 30px;font-family: '微软雅黑';">
			<!-- 人数 -->
			<div style="width: 100%; padding-top: 25px;float:left" >
			<div style="width:100%;BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">人数</div>
			</div>
			<div class="renNum" >
				<div style="float:left;width:100%;padding-top:10px" id="renwait">
      				<div class="ren1" id="ren" style="float:left;margin-left:5px;"></div>
      				<div  class="just-font" style="width:100%;height: 22px;font-size:18px;font-weight:bold">
      					<span id="rennum"></span>
      				</div>
      			</div>
			</div>
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
			 
      		<!--密度等级 -->
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">密度等级</div>
			</div>
			<div class="densityLevel" >
				<div style="float:left;width:100%;padding-top:10px" id="densityLevel">
				    <div class="ren1" id="ren" style="float:left;margin-left:5px"></div>
      				<div class="density just-font" id="dLevel" style="float:left;margin-left:5px;font-size:18px;font-weight:bold;"></div>
      			</div>
			</div>
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
      		 
      		<!--分组数  --> 
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:300px; float:left;font-weight:bold;padding-left:10px;font-size:18px">分组数</div>
			</div>
			<div class="groupNum" >
				<div style="float:left;width:100%;padding-top:10px" id="groupNum">
				    <div class="ren1" id="ren" style="float:left;margin-left:5px;"></div>
      				<div class="group just-font" id="gNum" style="float:left;margin-left:5px;font-size:18px;font-weight:bold;"></div>
      			</div>
			</div>  
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>   		
      		 
      		<!--关注度级别  -->
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:300px; float:left;font-weight:bold;padding-left:10px;font-size:18px">关注度级别</div>
			</div>
			<div class="attentionLevel" >
				<div style="float:left;width:100%;padding-top:10px" id="attentionLevel">
				    <div class="ren1" id="ren" style="float:left;margin-left:5px;"></div>
      				<div class="attention just-font" id="aLevel" style="float:left;margin-left:5px;font-size:18px;font-weight:bold;"></div>
      			</div>
			</div> 
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
		</div>
	</div>
	<!-- end right -->
</div>


<script type="text/javascript">

Highcharts.setOptions({  
	 global: { useUTC: false },
   colors: ["#2a5caa","#fffffb", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
    		"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"]  
}); 

var updateLabel = $("#updatetime");
//var station="015112017166013";

//左侧图表
var myDate= new Date(2015,5,26);
var start_time =  myDate.getTime();

var dtime = new Date().getTime();
var dtime = start_time;

var url = "${APP_PATH}bdms-web/dams/video/videoPage/cameranamesbycameraId";
var yc  = "${APP_PATH}dams/metro/in/dayenterpredictedata";
 

var main = jQuery("#main");

var pointIndex = 0; 

var hcharts = null;


var yAxisBands = "";

var hBefore = 0;
var hAfter  = 0;

var needReload = false;


$(function () {
	  //画线
	  //changeStationImgData(station);
	  loadImgData();
		
	});	

//计时器   动态加载并 切换 图标的 数据  的时间间隔
function loadImgData(){
	
	  setInterval('getImgData();', 10000); 
	  setInterval('changePredicteImgData();', 60000*60*24); 
}

/*
jQuery.post(url,{dateTime:dtime,cid:station},function(data){
	  
	  //alert(data.yAxis);
    yAxisBands = eval(data.yAxis);
	  showimg([],[]);
	  
		 hcharts.series[1].setData(data.data);
		 updateLabel.text(data.pDate);
		 refreshPNums(data.pCountTotal,data.pCount,data.pCountAvg,data.pCountMax,data.hNow,data.hCount,hBefore,hAfter,data.pointSize);
	 });*/

/**
*   切换站点图标     
*  @param sid   站点id
*/
function changeStationImgData(cid){
	 
	 station = cid;
	 
	 //console.log(hcharts.yAxis[0].setTitle({text:"xxx"}));
	
	 //获取当前时间
    //dtime = new Date().getTime();
	 
	  if(dtime == null ){
		  myDate= new Date(2015,5,26);
		  start_time =  myDate.getTime();

		  //var dtime = new Date().getTime();
		 dtime = start_time;
	  }
	// alert(station);
	  $.getJSON("${APP_PATH}dams/video/videoPage/cameranamesbycameraId/"+station, { "resultType": "json" }, function(data, textStatus)
				{
		 // alert(data.groupNum);
		  showimg([],[]);
		  console.log(data);
		  if(data.data==undefined){
			  hcharts.series[1].setData(null);
			     $("#updatetime").html("");
			     $("#rennum").html("");
				 $("#dLevel").html("");
				 $("#gNum").html("");
				 $("#aLevel").html("");
				 //refreshPNums(data.peopleNum);
		  }else{
		  hcharts.series[1].setData(data.data);
		     $("#updatetime").html(formatDate(data.timeStamp));
		     $("#rennum").html(formatNum(data.peopleNum));
			 $("#dLevel").html(formatNum(data.densityLevel));
			 $("#gNum").html(formatNum(data.groupNum));
			 $("#aLevel").html(formatNum(data.warnLevel)); 
			 //refreshPNums(data.peopleNum);
		  }
				});
	  
   /*  jQuery.post(url,{cid:station},function(data){
    	
     console.log(data);
     
     hBefore = data.pBefore;
     hAfter = data.pAfter;
     
      yAxisBands = eval(data.yAxis);
     //console.log(data);
      showimg([],[]);
	  hcharts.series[0].setData(data.data);
	  
	  jQuery.post(url,{cid:station},function(data){
			 hcharts.series[1].setData(data.data);
			// updateLabel.text(data.pDate);
			// console.log(data);
			
		     //$("#updatetime").html(data. );
			 $("#rennum").html(formatNum(data.peopleNum));
			 $("#dLevel").html(formatNum(data.densityLevel));
			 $("#gNum").html(formatNum(data.groupNum));
			 $("#aLevel").html(formatNum(data.warnLevel)); 
			
			  refreshPNums(data.pNow,data.pCount,data.pBefore,data.pAfter,data.hNow,data.hCount,hBefore,hAfter,data.pointSize);
		 });
	 
	 }); */

	 
}

function changePredicteImgData(){
	
	 var tmp =  new Date();
	 
	 if( tmp.getHours() == 0 &&  tmp.getMinutes() < 2 ){
		 
		 needReload = true;
		 
	 }else{
		 
		 needReload = false;
	 }
	
	 if(needReload ){
		 changeStationImgData(station);
	 }
}

//动态加载并 切换 图标的 数据 
function getImgData(){
	 
	  $.getJSON("${APP_PATH}dams/video/videoPage/cameranamesbycameraId/"+station, { "resultType": "json" }, function(data, textStatus)
{			//alert(station);
		  if(data.data==undefined){
			  hcharts.series[1].setData(null);
			     $("#updatetime").html("");
			     $("#rennum").html("");
				 $("#dLevel").html("");
				 $("#gNum").html("");
				 $("#aLevel").html("");
				 //refreshPNums(data.peopleNum);
		  }else{
		 hcharts.series[1].setData(data.data);
		 $("#updatetime").html(formatDate(data.timeStamp));
	     $("#rennum").html(formatNum(data.peopleNum));
		 $("#dLevel").html(formatNum(data.densityLevel));
		 $("#gNum").html(formatNum(data.groupNum));
		 $("#aLevel").html(formatNum(data.warnLevel)); 
		//  refreshPNums(data.peopleNum);
		  }
	  });
	  
	}


//使用  highcharts 画图
function showimg(ind,out){
	
	if("${THEME}"=="black/dot-luv"){
		hcharts = new Highcharts.Chart({ 
	          chart: { 
	              renderTo: 'main', //图表放置的容器，DIV 
	              defaultSeriesType: 'spline', //图表类型line(折线图), 
	              // zoomType: 'x',  //x轴方向可以缩放 
				  //backgroundColor:'${HIGHCHARTBG}'
				  backgroundColor:'#1b1b1b'
	          }, 
	        //去除右下角的logo水印
	        credits: {  
	            enabled:false  
	 		 }, 
	 		legend:{
	 			enabled : true
	 		},
	        title: {
	            text: ''
	        },
	      /*  subtitle: {
	            text: 'Irregular time data in Highcharts JS'
	        },*/
	        tooltip: {  
	            formatter: function () {  
	                return  Highcharts.dateFormat('%H:%M', this.x) +  '<br/>' +  
	                '<b>' + this.series.name  + '</b> : ' + this.y + " 人 ";  
	            }  
	        },   
	        
	        xAxis: {
	            type: 'datetime',
	            lineWidth: 2,
	            //gridLineWidth: 1,
	           // labels:{y:26},
	             tickInterval: 3600000,
	             labels:{
		             style: {
		                 color: '#fffffb'
		             }
	             },

	           dateTimeLabelFormats:
             {
	            	day :'%e日',
	            	hour: '%H'
             }
	        },
	        yAxis: {
		          title: {
		              text: '视频监控人数',
		              style: {
			                 color: '#fffffb'
			             }
		          },
		          labels : { 
		        	  style: {
			                 color: '#fffffb'
			             },
		                formatter : function() {//设置纵坐标值的样式  
		        
		                	
		                	return this.value;
		                 
		                }  
		               },   
		          lineWidth: 2,
		          min: 0,
		          minorGridLineWidth: 0,
		          gridLineDashStyle: 'LongDash',
		          gridLineColor:'rgba(229,229,229,0.1)',
		          gridLineWidth: 1,
		          alternateGridColor: null,
		          plotBands: yAxisBands
		      },
		      
	        
	        plotOptions: {
	        	line:{ 
	                  dataLabels:{ 
	                      enabled:true  //在数据点上显示对应的数据值 
	                  }, 
	                  enableMouseTracking: false //取消鼠标滑向触发提示框 
	              },
		          spline: {
		              //lineWidth: 4,
		              states: {
		                  hover: {
		                      lineWidth: 4
		                  }
		              },
		              marker: {
		                  enabled: false
		              },
		              pointInterval: 3600000, // one hour
		              pointStart   : start_time
		          }
		      },
	        series:[
	        	{name:"历史",data:ind},
	        	{name:"实时",data:out}
	        ]
	    });
	}else{
		hcharts = new Highcharts.Chart({ 
	          chart: { 
	              renderTo: 'main', //图表放置的容器，DIV 
	              defaultSeriesType: 'spline', //图表类型line(折线图), 
	              // zoomType: 'x',  //x轴方向可以缩放 
				  //backgroundColor:'${HIGHCHARTBG}'
				  backgroundColor:'white'
	          }, 
	        //去除右下角的logo水印
	        credits: {  
	            enabled:false  
	 		 }, 
	 		legend:{
	 			enabled : true
	 		},
	        title: {
	            text: ''
	        },
	      /*  subtitle: {
	            text: 'Irregular time data in Highcharts JS'
	        },*/
	        tooltip: {  
         formatter: function () {  
             return  Highcharts.dateFormat('%H:%M', this.x) + '<br/>' +  
             '<b>' + this.series.name  + '</b> : ' + this.y + " 人 ";  
         }  
     },  
	        
	        xAxis: {
	            type: 'datetime',
	            lineWidth: 2,
	            lineColor:'black',
	            //gridLineWidth: 1,
	           // labels:{y:26},
	             tickInterval: 3600000,
	             labels:{
		             style: {
		                 color: 'black'
		             }
	             },

	           dateTimeLabelFormats:
             {
	            	day :'%e日',
	            	hour: '%H'
             }
	        },
	        yAxis: {
	        	lineColor:'black',
		          title: {
		              text: '视频监控人数',
		              style: {
			                 color: 'black'
			             }
		          },
		          labels : { 
		        	  style: {
			                 color: 'black'
			             },
		                formatter : function() {//设置纵坐标值的样式  
		        
		                	
		                	return this.value;
		                 
		                }  
		               },   
		          lineWidth: 2,
		          min: 0,
		          minorGridLineWidth: 0,
		          gridLineDashStyle: 'LongDash',
		          gridLineColor:'black',
		          gridLineWidth: 1,
		          alternateGridColor: null,
		          plotBands: yAxisBands
		      },
		      
	        
	        plotOptions: {
	        	line:{ 
	                  dataLabels:{ 
	                      enabled:true  //在数据点上显示对应的数据值 
	                  }, 
	                  enableMouseTracking: false //取消鼠标滑向触发提示框 
	              },
		          spline: {
		              //lineWidth: 4,
		              states: {
		                  hover: {
		                      lineWidth: 4
		                  }
		              },
		              marker: {
		                  enabled: false
		              },
		              pointInterval: 3600000, // one hour
		              pointStart   : start_time
		          }
		      },
	        series:[	        	
	        	{name:"历史",data:ind,color:'rgb(135,179,203)'},
	        	{name:"实时",data:out,color:'rgb(0,84,248)'}
	        ]
	    });
	}
	 
 }  



</script>

</body>
</html>