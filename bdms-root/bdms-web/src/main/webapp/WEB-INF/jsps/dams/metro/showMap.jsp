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
		border: 0px solid #BBB; 
	} 
	</style>  
		

<script type="text/javascript">
  
	$(function() {
		//layout
		myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("east",400);
		
		//时间控件
		 var dates = $("#time");
		    var datepicker_CurrentInput;
		    var d=new Date();
			d.setTime(d.getTime()-24*60*60*1000);
			var currentDate=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		    //设置目标时间，因为例子中的开始时间和结束时间是有时间限制的
		    dates.datepicker({
			    default:$.datepicker.regional['zh-CN'],
		    	option:$.datepicker.regional['zh-CN'],
		       // showButtonPanel:true,
		       // defaultDate:currentDate,
		        dateFormat:"yy-mm-dd",
		        changeYear: true,
		        changeMonth: true,
		        //closeText: '清除',
		        //beforeShow: function (input, inst) { datepicker_CurrentInput = input; },
		        //numberOfMonths: 3,
		        //当选择时间的时候触发此事件
		        onSelect: function(selectedDate){  
		        	  if(this.id == "time"){
		        		  myChart.clear();
		        		  $("#tb1").empty();
		        		var offset = 0;
		  				var dateTime = changeDateFormat($("#time").val());
		  					$.ajax({
		  						url:'${APP_PATH}dams/metro/od/odShortestPath/'+changeDateFormat($("#time").val()),
		  						
		  						 dataType:'json',
		  						 success:function(data)
		  						
		  						 {
		  							 if(data!="},],],]"){
		  							 var gc = eval("("+data[0]+")")
		  							 var md = eval("("+data[1]+")")
		  							 var mp = eval("("+data[2]+")") 
		  							 var mpd= eval("("+data[3]+")")   
		  							 createOption(gc,md,mp,mpd)
		  							 }else{
		  								 alert("请求数据为空");
		  							 }
		  						 }
		  						 
		  					 });
		  					
		  					showHotStations(dateTime,offset*10,false);
		        	  }	        	
		        }
		    });
		    dates.val(currentDate);
		
			$( ".ui-layout-toggler" ).on("click",function(event,ui){
				
				//changeStationImgData(station);
				len=$("#main").css("width");
				len=len.replace("px","");
					//alert(len.substring(0));
					//alert($("#main").css("width">1100));
					if(parseInt(len)>1000){
						$( ".ui-layout-toggler" ).css("background-image","url('"+"../../../style/images/leftarrow.png"+"')");
					}else{
						$( ".ui-layout-toggler" ).css("background-image","url('"+"../../../style/images/rightarrow.png"+"')");
					}
			});
		 
			 $("#getMore").click(function(){
				var offset = $(this).attr("name");
				var dateTime = changeDateFormat($("#time").val());
				//alert(dateTime);
				if(offset <= 99 ){
					
					showHotStations(dateTime,offset*10,false);
					$(this).attr("name",Math.abs(offset)+1);
				}else{
					alert("没有更多了");
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

function changeDateFormat(str){
	 var date=str.substring(0,4)+str.substring(5,7)+str.substring(8,10);
	 return date;
}


function refreshPNums(num1,num2,num3,num4,num12,num22,num32,num42){
	refreshPNum("#ren",num1,num12);
	refreshPNum("#totalren",num2,num22);
	refreshPNum("#totalrenAM",num3,num32);
	refreshPNum("#totalrenPM",num4,num42);
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
 

<div class="ui-layout-center west-body-background">
	  
<!-- 		<div id="lineStatInfo" style="color:white;"><span style="color:white；font-family:  '微软雅黑';">全市热门线路</span></div>
 -->	 
	<div id="main" style="height:800px;width:100%;text-align: center;margin:0 auto; float:left;">
	</div>
	<div style="margin-left:60px;z-index:-1">
	
	<div class="just-font" style="width:100%; float:left;padding-left:5px;font-family: '微软雅黑';position:fixed;bottom:0;opacity:0.75;margin-bottom:5px;font-size:16px"><div style="float:left;margin-top:3px"><img width="20px" height="20px" src="../../../style/images/timeupdate.png"></img></div>数据更新时间：<span id="day"></span></div>
    </div>
 </div>
	<div class="ui-layout-east east-div" id="tb" style="display:none;paddingtop:20px;filter:alpha(opacity=90)"> 
	
	<!-- start right -->
	
	
	<div >
	<input type="text" id="time" value="" name="1" readonly="true" class="in-date-checkbox"/>
 	<input type="button" value="更多" style="background-color:#ff0;border:0;"name="1" id="getMore">
 	</div>
 	
	  <div id="tb1">
		<div id="right" class="just-font" style="float:left;width:380px;padding-top:20px;overflow:hidden;display:none;filter:alpha(opacity=90)">
				<div id="img" style="width:31px;height:45px;float:left;margin-left:10px;"><img src="${APP_PATH}style/images/jt.png" /></div>
				<div id="os" style="width:120px;height:45px;float:left;">
				    <div  id="o" style="width:120px;height:20px;float:left;font-family:'微软雅黑';font-size:18px; margin-top: -5px;"></div>
				    <div  id="d" style="width:120px;height:20px;float:left;font-family:'微软雅黑';font-size:18px; margin-top: 10px;"></div>
                </div>
				<div id="nAb" style="width:120px;height:40px;float:left">
				    <div id="n" style="width:160px;height:20px;float:left;font-family:'微软雅黑';font-size:18px;margin-bottom:5px;text-align:center"></div>
				    <div id="b" style="width:160px;height:10px;float:left;border:0;"></div>
				    <div style="width:120px;height:10px;"></div>
				</div>
				<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
				<!-- <div id="p" style="width:400px;height:20px;">....................................</div> -->
		</div>
	</div>
		<input id="top1Data" type="hidden"  value="">
	
	<!-- end right -->
</div>


 <div id="ec" style="height:1000px;width:100%;background-color:#fff"> 
</div>  
<script type="text/javascript">

var myChart = echarts.init(document.getElementById('main'));

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
		// url:'${APP_PATH}dams/metro/od/oddatao',
		url:'${APP_PATH}dams/metro/od/odShortestPath/'+changeDateFormat($("#time").val()),
		
		 dataType:'json',
		 success:function(data)
		 {
			 if(data!="},],],]"){
			 var gc = eval("("+data[0]+")")
			 var md = eval("("+data[1]+")")
			 var mp = eval("("+data[2]+")") 
			 var mpd= eval("("+data[3]+")")   
			 createOption(gc,md,mp,mpd)
			 }else{
				 alert("请求数据为空");
			 }
		 }
	 });
	
});


function createOption(g,n,ml,mld){
	var option = {
		    backgroundColor:'#1b1b1b',
		    tooltip : {
		        trigger: 'item',
		        formatter: '{b}'
		    },
		    color: ['white', 'rgba(100, 149, 237, 1)', 'green'],
		    series : [
		        {
		            name: '热门线路',
		            type: 'map',
		            mapType: 'OD',
		            roam:true,
		            mapLocation:{
		             position: 'absolute',
		             display: 'none',
		             padding: '5px', 
		             left: '200px',
		             top: '0px',
		             width:'200%',
		             height:'250%'
		            },
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data: [],
		            geoCoord: g, 
		         
		            markPoint : {
		                symbolSize : 3,
		                data : n
		            },
		            
		            markLine : {
		              //  smooth:true,
		                effect : {
		                    show: true,
		                    scaleSize: 1,
		                    period: 20,        //流动速度
		                    color: '#fff',
		                    shadowBlur: 2
		                },
		                symbol: ['none'],
		                itemStyle : {
		                    normal: {
		                        borderWidth:2,     //线宽
		                        lineStyle: {
		                            type: 'solid',
		                            shadowBlur: 10
		                        }
		                    }
		                },
		    		data :ml
		    	}
		        },
		        {
		            type: 'map',
		            mapType: 'OD',
		            data: [],
		            markPoint: {
		                symbol: 'emptyCircle',
		                effect: {
		                    show: true,
		                    color: 'rgba(218, 70, 214, 1)'
		                },
		                data :mld
		            }
		        }			     
		    ]
		};
	 myChart.setOption(option);
}
</script>


<script type="text/javascript">
	 //   var getmore=$("#getMore");
		var tb1 = $("#tb1");
		var model = $("#right");
		var tab = null;
		$(function () {
			//alert(changeDateFormat($("#time").val()));
			showHotStations(changeDateFormat($("#time").val()),0);
		});
	      function showHotStations(dateTime,offs){
	    	  $.ajax({
					url:'${APP_PATH}dams/metro/od/oneDayODData'+'/'+dateTime+'/'+offs,
					 dataType:'json',
					// data:{dateTime:dateTime,offset:offs},
					 success:function(data){
				 if(data!=""){
					   if(offs==0){
						 var S=data[0];
						 $('#top1Data').val(S.count);
					 }  	
					   for(i=0;i<data.length;i++){
		 						 obj = data[i];
		 						 tab = model.clone().css("display","block");
		 						   
		 						 tab.find("#o").text(obj.ost);
		 						 tab.find("#d").text(obj.dst);
		 						 tab.find("#n").text(formatNum(obj.count));
		 						 var a=$("#top1Data").attr("value");
		 						$(tab.find("#b")).progressbar({value: (obj.count)/(a)*100});
		 						$(tab.find("#b")).find(".ui-progressbar-value" ).css({
		 					          "background": '#55BF3B',"border":'0'
		 				        });
		 				      
		 						tb1.append(tab);
		 						  
							}
					   }else{
						   alert("请求数据为空");
					   }
					 }
				});
				
	      }
	   	  
	    </script>

</body>
</html>