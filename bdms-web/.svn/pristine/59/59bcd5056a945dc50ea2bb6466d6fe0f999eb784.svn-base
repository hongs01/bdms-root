<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WIFI数据监控</title>
<jsp:include page="../header/metroHeader.jsp"></jsp:include>
<%-- <link rel="stylesheet" type="text/css" href="${APP_PATH}js/layout/complex.css" /> --%>

<!-- 已写到Header中 -->
<script type="text/javascript">
  $(function(){
	   $("#selectmenu").selectmenu({width: 130});
	   
	    //datepicker
		// 获取调用控件的对象
	    var dates = $("#start");
	    var datepicker_CurrentInput;
	    var currentDate=new Date();
	    //设置目标时间，因为例子中的开始时间和结束时间是有时间限制的
	    dates.datepicker({
		    //default:$.datepicker.regional['zh-CN'],
	    	//option:$.datepicker.regional['zh-CN'],
	       // showButtonPanel:true,
	        defaultDate:currentDate,
	        dateFormat:"yy-mm-dd",
	        changeYear: true,
	        changeMonth: true,
	        //closeText: '清除',
	        //beforeShow: function (input, inst) { datepicker_CurrentInput = input; },
	        //numberOfMonths: 3,
	        //当选择时间的时候触发此事件
	        onSelect: function(selectedDate){  
	          if(this.id == "start"){
	             var stat=$("#selectmenu").val();
	             // alert(changeDateFormat($("#start").val()))
       			  changeStationImgData(stat+'-'+changeDateFormat($("#start").val()));
       		  } 
	        }
	    });
	    dates.val(currentDate.Format("yyyy-MM-dd"));
	    
	  //下拉列表数据
	  var sid="${sid}";
	
	    $.getJSON("${APP_PATH}dams/wifi/wifiData/allStations ", {"resultType" : "json"}, function(data, textStatus) {
	    	$("#selectmenu").empty();//先清空tbody  
	    	var strHTML;
	    	$.each(data, function(InfoIndex, Info) {//遍历json里的数据  
	    		if(Info.apname==sid){
					strHTML += "<option value="+Info.apname+" selected='selected'>"; 
				}else{
					strHTML += "<option value="+Info.apname+">"; 
				}
	    		//strHTML += "<option value="+Info.apname+">";
	    		strHTML += Info.stationName;
	    		strHTML += "</option>";
	    	});
	    	$("#selectmenu").html(strHTML);//显示到tbody中
	    	if(data!=''){
				$("#selectmenu").selectmenu( "menuWidget").addClass("smoverflow");
			}else{
				$("#selectmenu").selectmenu( "menuWidget").deleteClass("smoverflow");
			}
	    	$("#selectmenu").selectmenu("refresh");
	    	//var apname=$("#selectmenu").val();
	    	//alert(apname+'-'+$("#start1").val());
	    });

	  //选择按钮change事件
	    $("#selectmenu").on("selectmenuchange",function( event, ui){
	    	var apname=$("#selectmenu").val();
	    		 $.getJSON(
						"${APP_PATH}dams/wifi/wifiData/getwifiDataTotal/"
								+ apname, {
							"resultType" : "json"
						}, function(data, textStatus) {
							$("#lineStatInfo1").html(data[0].stationName+" 人数");
						}); 
	    		changeStationImgData(apname+'-'+changeDateFormat($("#start").val()))
	    });
	  
	    
});


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

 
 function changeDateFormat(str){
	 var date=str.substring(0,4)+str.substring(5,7)+str.substring(8,10)+"00";
	 return date;
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
	 //alert(lastpoint);
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
<div id="start1"></div>
<div class="ui-layout-center west-body-background">
	
	<div id="right" class="wifi-right" style="height:115px">
	    <div id="lineStatInfo1" class="just-font">
			<span class="font-family-color"></span>
		</div>
		
		<div style="height:20px"></div>
		
	<div id="right1" class="wifi-right" style="height:40px">
			
		<div style="padding-left: 300px; float: left;">
		    <span class="ui-button-text date-span" style="font-size:18px;font-weight:bold;color: #1B1B1B;">日期：</span>
            <input type="text" id="start" value="" readonly="true" class="in-date-checkbox"/>
	    </div>
	    
	    <div style="padding-left: 20px;float: left;line-height: 30px;font-size: 18px;font-family:'微软雅黑';font-weight: bold;">地点：</div>
		<div style="float: left;">
				<select id="selectmenu">
					<option value="-">请选择</option>
				</select>
		</div>
		<span id="totalrennum" style="font-size:18px;float:left;margin-left: 20px;line-height: 30px;font-family: '微软雅黑';font-weight: bold;"></span>
		<div style="height:20px"></div>
	</div>
		
		
	 
	</div>
	
 
	<div id="main" style="height:400px;width:100%; float:left;"></div>
	<div style="margin-left:60px">
	<div style="float:left;margin-top:4px"><img width="20px" height="20px" src='${APP_PATH}style/images/timeupdate.png'></img></div>
	<div class="update-div" style="font-size:18px">数据更新时间：<span id="updatetime"></span></div>
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
var url = "${APP_PATH}dams/wifi/wifiData/wifiData";
var yc  = "${APP_PATH}dams/metro/in/dayenterpredictedata";

var station ;//= $("#start1").val()+"-20140327082500";

var main = jQuery("#main");

var yAxisBands = "";

var hcharts = null;
var hBefore = 0;
var hAfter  = 0;

var needReload = false;


$(function () {
	//初始化图表
	 var sid="${sid}";
	var sidSub=sid+"-"+changeDateFormat($("#start").val());
	 if(sid==""){
	
	  	     $.getJSON("${APP_PATH}dams/wifi/wifiData/allStations ", {"resultType" : "json"},
	  	    		function(data, textStatus) {
	  	    	$("#lineStatInfo1").html(data[0].stationName+" 人数");
	  	        station=data[0].apname+"-"+changeDateFormat($("#start").val());
	  	      //画线
	  	       changeStationImgData(station);
	  		  loadImgData();
	  	    });
	  		 
	 }else{
		 $.getJSON(
					"${APP_PATH}dams/wifi/wifiData/getwifiDataTotal/"
							+ sid, {
						"resultType" : "json"
					}, function(data, textStatus) {
						$("#lineStatInfo1").html(data[0].stationName+" 人数");
					});
		  changeStationImgData(sidSub);
 		  loadImgData();
	 }
	  	   //changeStationImgData(station);
  		  //loadImgData();
	});	

//计时器   动态加载并 切换 图标的 数据  的时间间隔
function loadImgData(){
	  setInterval('getImgData();', 10000); 
	  setInterval('changePredicteImgData();', 60000*60*24); 
}
/**
 * 根据日期字符串(2015-12-01)取得其时间
 */
function getTimeByDateStr(dateStr){
	ymd=dateStr.split("-");
    year = ymd[0];
    month = ymd[1];
    day = ymd[2];
    //alert(year+""+month+""+day);
    return year+""+month+""+day;
}

/**
*   切换站点图标     
*  @param sid   站点id
*/
function changeStationImgData(sid){
	 
	 station = sid;
	 //获取datepicker时间
	 dtimeStr = $("#start").val();
	 if(dtimeStr!=""){
		 dtime=getTimeByDateStr(dtimeStr)+"";
	 }else{
		 dtime="";
	 }
	 
	  jQuery.post(url+"/"+station,function(data){
		  if(data.data!=null){
		  yAxisBands = eval(data.yAxis);
	  	     showimg([],[]);
			 hcharts.series[1].setData(data.data);
			 updateLabel.text(data.pDate);
			$("#totalrennum").html("当前人数: "+formatRen(data.data));
			$("#updatetime").html(formatDate(data.data));
		  }else{
			  showimg([],[]);
		  }
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
	 jQuery.post(url+"/"+station,function(data){
			//  alert(data);
			if(data.data!=null){
			  hBefore = data.pBefore;
		      hAfter  = data.pAfter;
			  
				 hcharts.series[1].setData(data.data);
				 updateLabel.text(data.pDate);
				$("#totalrennum").html("当前人数: "+formatRen(data.data));
				$("#updatetime").html(formatDate(data.data));
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
	        style: {
                color: '#ffffff'
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
		              text: '',
		              style: {
			                 color: '#fffffb'
			             }
		          },
		          labels : { 
		        	  style: {
			                 color: '#fffffb'
			             },
		                formatter : function() {//设置纵坐标值的样式  
		        
		                	
		                	return formatNum(this.value);
		                 
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
	        	/* series:{
	        	 	color:'#ffff00',
	        	},
	       	        */ 
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
	        	{name:"历史",data:ind, visible:false},
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
		                 color: 'black',
		                 fontWeight: 'bold',
		                 fontSize: '16px'
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
		              text: '',
		              style: {
			                 color: 'black'
			             }
		          },
		          labels : { 
		        	  style: {
			                 color: 'black',
			                 fontWeight: 'bold',
			                 fontSize: '16px'
			             },
		                formatter : function() {//设置纵坐标值的样式  
		        
		                	
		                	return formatNum(this.value);
		                 
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
	        	{name:'<b style="font-size:16px">历史</b>',data:ind,color:'rgb(135,179,203)', visible:false},
	        	{name:'<b style="font-size:16px">人数</b>',data:out,color:'rgb(0,84,248)'}
	        ]
	    });
	}
 }

</script>

</body>
</html>