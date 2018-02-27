<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WIFI数据监控</title>
<jsp:include page="../header/metroHeader.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${APP_PATH}js/layout/complex.css" />
<script type="text/javascript" src="${APP_PATH}js/layout/jquery.layout.js"></script>
<script type="text/javascript" src="${APP_PATH}js/layout/complex.js"></script>

<!-- 已写到Header中 -->
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

function refreshPNums(num1,num2,num3,num4,num12,num22,num32,num42,pSize){
	refreshPNum("#totalren",num2,num22,1);
}

 function refreshPNum(id,num,numh,pSize){
	 var codeStr="";
	 var typeStr="";
	 codeStr="";
	 typeStr="allInsideRT";
	 //alert(codeStr+"--"+typeStr+"--"+pSize);
	 $.getJSON("${APP_PATH}dams/metro/in/criterion", { code:codeStr,type:typeStr,"resultType": "json" }, function(data, textStatus)
		{	
			level=data.level.split(",");
			//alert(level[0]+","+level[1]+","+level[2]);
			if(num==0){
				$(id+"num").css("background-image","url('"+"${APP_PATH}style/images/grren.png"+"')");
			}else if(num<parseInt(level[0])*pSize){
				$(id+"num").css("background-image","url('"+"../../../style/images/luren.png"+"')");
			}else if(num<parseInt(level[1])*pSize){
				$(id+"num").css("background-image","url('"+"../../../style/images/yeren.png"+"')");
			}else if(num<parseInt(level[2])*pSize){
				$(id+"num").css("background-image","url('"+"../../../style/images/orren.png"+"')");
			}else{
				$(id+"num").css("background-image","url('"+"../../../style/images/reren.png"+"')");
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
			//$((id+"num")).html("<span style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:left;font-weight:bold;width:280px;color:white;font-family: '微软雅黑';'>"+formatNum(num)+"（"+formatNum(numh)+"）</span>");
			$((id+"num")).html("<span class='just-font' style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:center;font-weight:bold;width:180px;font-family: '微软雅黑';'>"+formatNum(num)+"</span>");
		});
 }

 function changeTitleBg(index){
	 if(index=="top"){
		 $("#metrotitle").css("background-image","url('"+"../../../style/images/titleAll_top.png"+"')");
	 }else if(index=="line"){
		 $("#metrotitle").css("background-image","url('"+"../../../style/images/titleAll_line.png"+"')");
	 }else if(index=="all"){
		 $("#metrotitle").css("background-image","url('"+"../../../style/images/titleAll.png"+"')");
	 }else if(index=="line_hc"){
		 $("#metrotitle").css("background-image","url('"+"../../../style/images/titleAll_line_hc.png"+"')");
	 }else if(index=="all_hc"){
		 $("#metrotitle").css("background-image","url('"+"../../../style/images/titleAll_hc.png"+"')");
	 }
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
 
 function formatRen(str){
	 var lastpoint=str[str.length-1];
	// alert(lastpoint);
	var data=(lastpoint[1]);
	 
	 return data;
	}
 
 function formatDate(str){
	 var lasttime=str[str.length-1];
	 var data=(lasttime[0]);
	// alert(getFormatDateByLong(1442215200000, "yyyy-MM-dd hh:mm:ss"))
	 var time= getSmpFormatDateByLong(data,true);
	 return formatHFS(time);
 } 
function formatHFS(str){
	var time=str.substring(11,str.length);
	return time;
}
 
//扩展Date的format方法   
 Date.prototype.format = function (format) {  
     var o = {  
         "M+": this.getMonth() + 1,  
         "d+": this.getDate(),  
         "h+": this.getHours(),  
         "m+": this.getMinutes(),  
         "s+": this.getSeconds(),  
         "q+": Math.floor((this.getMonth() + 3) / 3),  
         "S": this.getMilliseconds()  
     }  
     if (/(y+)/.test(format)) {  
         format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
     }  
     for (var k in o) {  
         if (new RegExp("(" + k + ")").test(format)) {  
             format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
         }  
     }  
     return format;  
 }  
 /**   
 *转换日期对象为日期字符串   
 * @param date 日期对象   
 * @param isFull 是否为完整的日期数据,   
 *               为true时, 格式如"2000-03-05 01:05:04"   
 *               为false时, 格式如 "2000-03-05"   
 * @return 符合要求的日期字符串   
 */    
 function getSmpFormatDate(date, isFull) {  
     var pattern = "";  
     if (isFull == true || isFull == undefined) {  
         pattern = "yyyy-MM-dd hh:mm:ss";  
     } else {  
         pattern = "yyyy-MM-dd";  
     }  
     return getFormatDate(date, pattern);  
 }  
 /**   
 *转换当前日期对象为日期字符串   
 * @param date 日期对象   
 * @param isFull 是否为完整的日期数据,   
 *               为true时, 格式如"2000-03-05 01:05:04"   
 *               为false时, 格式如 "2000-03-05"   
 * @return 符合要求的日期字符串   
 */    

 function getSmpFormatNowDate(isFull) {  
     return getSmpFormatDate(new Date(), isFull);  
 }  
 /**   
 *转换long值为日期字符串   
 * @param l long值   
 * @param isFull 是否为完整的日期数据,   
 *               为true时, 格式如"2000-03-05 01:05:04"   
 *               为false时, 格式如 "2000-03-05"   
 * @return 符合要求的日期字符串   
 */    

 function getSmpFormatDateByLong(l, isFull) {  
     return getSmpFormatDate(new Date(l), isFull);  
 }  
 /**   
 *转换long值为日期字符串   
 * @param l long值   
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
 * @return 符合要求的日期字符串   
 */    

 function getFormatDateByLong(l, pattern) {  
     return getFormatDate(new Date(l), pattern);  
 }  
 /**   
 *转换日期对象为日期字符串   
 * @param l long值   
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
 * @return 符合要求的日期字符串   
 */    
 function getFormatDate(date, pattern) {  
     if (date == undefined) {  
         date = new Date();  
     }  
     if (pattern == undefined) {  
         pattern = "yyyy-MM-dd hh:mm:ss";  
     }  
     return date.format(pattern);  
 }  
	
</script>

</head>


<body>
    <!-- start header -->
     
    <!-- end   header-->

<div class="ui-layout-center west-body-background" >
	<div style="width:100%; height:100px;">
		<div id="lineStatInfo1">
			<span class="font-family-color">豫园站WIFI数据</span>
		</div>
		<div id="lineStatInfo2" class="just-font">
			<!-- <div class="yeren2" id="totalren"></div> -->
			<span id="totalrennum" style="margin-left:30px;font-size:18px;"></span>
		</div>
	</div>
	<div id="main" style="height:400px;width:100%; float:left;"></div>
	<div style="margin-left:60px">
	<div style="float:left;margin-top:4px"><img width="20px" height="20px" src="../../../style/images/timeupdate.png"></img></div>
	<div class="update-div">数据更新时间：<span id="updatetime"></span></div>
	</div>				 
</div>

<script type="text/javascript">

Highcharts.setOptions({  
	 global: { useUTC: false },
  colors: ["#2a5caa","#fffffb", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
   		"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"]  
}); 

var updateLabel = $("#updatetime");

//左侧图表
var myDate= new Date(2015,5,26);
var start_time =  myDate.getTime();

//var dtime = new Date().getTime();
var dtime = start_time;
var url = "${APP_PATH}dams/wifi/wifiPage/wifiData";
var yc = "${APP_PATH}dams/metro/inoutsub/dayInOutSubPredicteData";
var station = "B109D035";

var main = jQuery("#main");

var yAxisBands = "";

var hcharts = null;
var hBefore = 0;
var hAfter  = 0;

var needReload = false;


$(function () {
	  //画线
	  changeStationImgData(station);
	  loadImgData();
		
	});	

//计时器   动态加载并 切换 图标的 数据  的时间间隔
function loadImgData(){
	  setInterval('getImgData();', 10000); 
	  setInterval('changePredicteImgData();', 60000*60*24); 
}


/**
*   切换站点图标     
*  @param sid   站点id
*/
function changeStationImgData(sid){
	 
	 station = sid;
	
	 //获取当前时间
    //dtime = new Date().getTime();
    	
     // console.log(data);
	  //hcharts.series[0].setData(data.data);
	  //alert(station);
	  jQuery.post(url+"/"+station,function(data){
		  //alert(data);
		  hBefore = data.pBefore;
	      hAfter  = data.pAfter;
		  //alert(data.yAxis);
	      yAxisBands = eval(data.yAxis);
	  	  showimg([],[]);
		  
			 hcharts.series[1].setData(data.data);
			 updateLabel.text(data.pDate);
			$("#totalrennum").html("人数: "+formatRen(data.data));
			$("#updatetime").html(formatDate(data.data));
			var s = formatDate(data.data);
			//alert(s+"");
			 //refreshPNums(data.data[1]);
		 });
	 
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
	 
	  //获取当前时间
	  //dtime = new Date().getTime();
	 jQuery.post(url+"/"+station,function(data){
			//  alert(data);
			  hBefore = data.pBefore;
		      hAfter  = data.pAfter;
			  //alert(data.yAxis);
		      yAxisBands = eval(data.yAxis);
		  	//  showimg([],[]);
			  
				 hcharts.series[1].setData(data.data);
				 updateLabel.text(data.pDate);
				$("#totalrennum").html("人数: "+formatRen(data.data));
				$("#updatetime").html(formatDate(data.data));
				 //refreshPNums(data.data[1]);
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
	                return  Highcharts.dateFormat('%H:%M', this.x) + '<br/>' +  
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
		              text: '人数',
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
	        	{name:"人数",data:out}
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
		              text: '人数',
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
	        	{name:"人数",data:out,color:'rgb(0,84,248)'}
	        ]
	    });
	}
 }

</script>

</body>
</html>