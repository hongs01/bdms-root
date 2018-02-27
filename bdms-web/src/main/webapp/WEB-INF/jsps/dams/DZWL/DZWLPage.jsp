<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海重点场所实时监控</title>
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
		layoutSettings_Outer.east.initClosed=true;
		myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("east",280);
		
		var sid="${sid}";
		//alert(sid);
		
		//$("#shishi").button();
		$("#select").selectmenu({width: 140});
		$.getJSON("${APP_PATH}dams/DZWL/DZWLPage/allelecfences", { "resultType": "json" }, function(data, textStatus)
				{
			strHTML="";
			$("#select").empty();//先清空tbody  
			var strHTML =""; 
			var areaname="";
			$.each(data,function(InfoIndex,Info){//遍历json里的数据  
			if(sid==Info.code){
				strHTML += "<option value="+Info.code+" selected='selected'>"+Info.oname+"</option>"; 
			}else{
				strHTML += "<option value="+Info.code+">"+Info.oname+"</option>";
			}
			});
			  
			$("#select").html(strHTML);//显示到tbody中
			$("#select").selectmenu("refresh");
			
			
			
				   
		});
		 $("#select").on("selectmenuchange",function( event, ui){
 	$.getJSON("${APP_PATH}dams/DZWL/DZWLPage/allelecfences", { "resultType": "json" }, function(data, textStatus)
 {
 			//alert($("#select").val());		     
			var value=$("#select").val();

			$.each(data,function(InfoIndex,Info){//遍历json里的数据  
			if(value==Info.code){
				//alert(Info.oname);
				$("#lineStatInfo").html(Info.oname+" 人数  ");
			 	changeStationImgData(Info.name);
			}else{
				
			}
			});


			 //$("#lineStatInfo").html(data[value].oname+" 人数  ");
			 
			 //changeStationImgData(data[value].name);
 });	
 	
 }); 
			
		 
			
			$( ".ui-layout-toggler" ).on("click",function(event,ui){
				$.getJSON("${APP_PATH}dams/DZWL/DZWLPage/allelecfences", { "resultType": "json" }, function(data, textStatus)
						 {
						 		     var value=$("#select").val();

			$.each(data,function(InfoIndex,Info){//遍历json里的数据  
			if(value==Info.code){
				//alert(Info.oname);
				$("#lineStatInfo").html(Info.oname+" 人数  ");
			 	changeStationImgData(Info.name);
			}else{
				
			}
			});


									 //$("#lineStatInfo").html(data[value].oname+" 人数  ");
									 
									 //changeStationImgData(data[value].name);
						 });	
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
			
			//datepicker
			// 获取调用控件的对象
		    var dates = $("#start");
		    var datepicker_CurrentInput;
		    var currentDate=new Date();
		    
		    //设置目标时间，因为例子中的开始时间和结束时间是有时间限制的
		    //var targetDate;
		    dates.datepicker({
			    //default:$.datepicker.regional['zh-CN'],
		    	//option:$.datepicker.regional['zh-CN'],
		        //showButtonPanel:true,
		        defaultDate:currentDate,	
		        //showButtonPanel:true,
		        dateFormat:"yy-mm-dd",
		        changeYear: true,
		        changeMonth: true,
		        //closeText: '清除',
		        //beforeShow: function (input, inst) { datepicker_CurrentInput = input; },
		        //numberOfMonths: 3,
		        //当选择时间的时候触发此事件
		        onSelect: function(selectedDate){  
		          if(this.id == "start"){
		        	  $.getJSON("${APP_PATH}dams/DZWL/DZWLPage/allelecfences", { "resultType": "json" }, function(data, textStatus){

			var value=$("#select").val();

			$.each(data,function(InfoIndex,Info){//遍历json里的数据  
			if(value==Info.code){
				//alert(Info.oname);
				$("#lineStatInfo").html(Info.oname+" 人数  ");
				changeStationImgData(Info.name);
			}else{
				
			}
			});


			  		     //var value=$("#select").val()-1;
			 			 //$("#lineStatInfo").html(data[value].oname+" 人数  ");
			 			 //changeStationImgData(data[value].name);
        			  });
		          } 
		        }
		    });
		    dates.val(currentDate.Format("yyyy-MM-dd"));
		    
		   /*  $( "#shishi" ).on("click",function(event,ui){
				$("#start").val("");
				var stat=$("#select").val();
				$.getJSON("${APP_PATH}dams/DZWL/DZWLPage/allelecfences", { "resultType": "json" }, function(data, textStatus){
		 		     var value=$("#select").val()-1;
					 $("#lineStatInfo").html(data[value].oname+" 人数  ");
					 changeStationImgData(data[value].name);
				 });
			}); */
			
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
//根据人数（历史的或实时的来刷新图片）
function refreshPNums(num1){
	refreshPNum("#ren",num1);
}

 function refreshPNum(id,num){
	 
	 var codeStr=$("#select").val();
	 var typeStr="elecfenceUserNum";
	  
	 //获取数据库中给定的预警级别的标准
	 $.getJSON("${APP_PATH}dams/metro/in/criterion", { code:codeStr,type:typeStr,"resultType": "json" }, function(data, textStatus)
		{	
			level=data.level.split(",");
		 	if(num==undefined){
		 		num=0;
		 	}
		 	if(num==0){
		 		$("#ren").css("background-image","url('"+"${APP_PATH}style/images/grren.png"+"')");
			}else if(num<parseInt(level[0])){
				$("#ren").css("background-image","url('"+"${APP_PATH}style/images/luren.png"+"')");
			}else if(num<parseInt(level[1])){
				$("#ren").css("background-image","url('"+"${APP_PATH}style/images/yeren.png"+"')");
			}else if(num<parseInt(level[2])){
				$("#ren").css("background-image","url('"+"${APP_PATH}style/images/orren.png"+"')");
			}else{
				$("#ren").css("background-image","url('"+"${APP_PATH}style/images/reren.png"+"')");
			}
			if(num<0){
				percent=0;
			}else{
				percent=Math.ceil(num/parseInt(level[2])*100);
			}
			 
		});
	 }

 
 function formatNum(str){ 
	 //alert((str!="" && str!=undefined));
	if(str=="0"){
		return "0";
	}
	if(str!="" && str!=undefined){
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
	}else{
		return "";
	}
}  
 
 function formatDate(str){
	 if(str==undefined){
		 data=""; 
	 }else{
	 	data=str.substring(11,17)+":00";
	 }
	 return data;
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
     return year+"-"+month+"-"+day;
 }
 
</script>

</head>


<body class="only-body-background">
 <!-- start header -->
     
    <!-- end   header-->
<div class="ui-layout-center only-body-background" style="overflow:hidden">
	<div id="right" class="wifi-right" style="height:130px">
	    <div id="lineStatInfo" class="just-font">
			<span class="font-family-color">大外滩 人数</span>
		</div>
		
		<div style="height:10px"></div>

	<div id="right1" class="wifi-right" style="height:40px">
			
		<div style="padding-left: 150px; float: left;">
		    <span class="ui-button-text date-span" style="font-size:18px;font-weight:bold;color: #1B1B1B;">日期：</span>
            <input type="text" id="start" value="" readonly="true" class="in-date-checkbox"/>
	    </div>
	    
	    <div style="padding-left: 20px;float: left;line-height: 30px;font-size: 18px;font-family:'微软雅黑';font-weight: bold;">区域：</div>
		<div style="float: left;">
				<select id="select">
					<option value="-">请选择</option>
				</select>
		</div>
		<div style="padding-left: 20px;float: left;line-height: 30px;font-size: 18px;font-family:'微软雅黑';font-weight: bold;margin-top: -1px;">当前人数：</div>
		<div class="ren" id="ren" style="margin-top:2px;float: left;line-height: 30px;font-size: 18px;font-family:'微软雅黑';font-weight: bold;"></div>
		<span id="rennum" style="padding-left: 5px;float: left;line-height: 30px;font-size: 18px;font-family:'微软雅黑';font-weight: bold;margin-top: -1px;"></span>
		<div style="height:20px"></div>
	</div>
		
		
	 
	</div>

	
	<div id="main" style="height:400px;width:100%; float:left;"></div>
	<div style="margin-left:60px">
	<div style="float:left;margin-top:4px"><img width="20px" height="20px" src="${APP_PATH}style/images/timeupdate.png"></img></div>
	<div class="update-time-div font-family-color" style="font-size:18px;font-weight:bold">数据更新时间：<span id="updatetime"></span></div>
	</div>				 
</div>
 
<div class="ui-layout-east east-div"> 
	<!-- start right -->
	<div id="right" style="float:left;width:380px;padding-top:10px;" class="right-bgc">
		<!-- <div style="padding: 5px;width: 100%;">
			<div style="padding: 5px;float: left;">
				<span class="ui-button-text date-span">日期：</span>
	            <input type="text" id="start" value="" readonly="true" class="date-checkbox"/>
			</div>
		</div>
		<div style="padding: 5px;width: 100%;">
			<div style="padding: 5px;float: left;">
				<div class="area-div">区域：</div>
	            <select id="select" style="margin-top:20px">
					<option value="">顾村公园</option>
				</select></div>
		</div> -->
		
		<div style="width: 380px ;margin-top: 20px;font-family: '微软雅黑';">
			<!-- 人数 -->
			<div style="width: 100%; padding-top: 10px;float:left" >
			<div style="width:100%;BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">人数</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="renwait">
      				<!-- <div class="ren" id="ren" style="float:left;margin-left:5px"></div> -->
      				<div  style="width:100%;height: 22px;font-size:18px;font-weight:bold">
      					<!-- <span id="rennum" class="just-font"></span> -->
      					<span id="rennumh" class="just-font"></span>
      				</div>
      			</div>
			</div>
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
			
      		<!--人数7天均值 -->
      		<div style="width: 100%; padding-top: 10px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">人数7天均值</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenwait">
      				<!-- <div class="ren1" id="totalren" style="float:left;margin-left:5px"></div> -->
      				<div class="just-font" style="width:100%;height: 22px;padding-left:10px;font-size:18px;font-weight:bold">
      					<span id="u5t"></span>
      				</div>
      			</div>
			</div>
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
      	 
      		<!--漫入数  --> 
      		<div style="width: 100%; padding-top: 5px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">漫入数</div>
			</div>
			<div class="renPlusNum">
				<div style="float:left;width:100%;padding-top:10px" id="totalrenAMwait">
      				<!-- <div class="orren" id="totalrenAM" style="float:left;margin-left:5px"></div> -->
      				<div class="just-font" style="width:100%;height: 22px;font-size:18px;font-weight:bold;padding-left:10px">
      					<span id="minum"></span>
      				</div>
      			</div>
			</div>  
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>   		
       
      		<!--漫入数7天均值  -->
      		<div style="width: 100%; padding-top: 5px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">漫入数7天均值</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenPMwait">
      				<!-- <div class="reren" id="totalrenPM" style="float:left;margin-left:5px"></div> -->
      				<div class="just-font" style="width:100%;height: 22px;font-size:18px;font-weight:bold;padding-left:10px">
      					<span id="mi5t"></span>
      				</div>
      			</div>
			</div> 
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
			
			
			<!--漫出数  -->
      		<div style="width: 100%; padding-top: 5px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">漫出数</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenPMwait">
      				<!-- <div class="reren" id="totalrenPM" style="float:left;margin-left:5px"></div> -->
      				<div class="just-font" style="width:100%;height: 22px;font-size:18px;font-weight:bold;padding-left:10px">
      					<span id="monum"></span>
      				</div>
      			</div>
			</div> 
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
	
	
	        <!--漫出数7天均值  -->
      		<div style="width: 100%; padding-top: 5px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">漫出数7天均值</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenPMwait">
      				<!-- <div class="reren" id="totalrenPM" style="float:left;margin-left:5px"></div> -->
      				<div class="just-font" style="width:100%;height: 22px;font-size:18px;font-weight:bold;padding-left:10px">
      					<span id="mo5t"></span>
      				</div>
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


//左侧图表
var myDate= new Date(2015,5,26);
var start_time =  myDate.getTime();
//var dtime = new Date().getTime();
var dtime = start_time;

var url = "${APP_PATH}/dams/DZWL/DZWLPage/dzwlyhs";//实时
var yc  = "${APP_PATH}dams/DZWL/DZWLPage/dzwlyhsh";//历史 
var yc1  = "${APP_PATH}dams/metro/in/dayenterpredictedata";

var qyname = "浦西外滩";

var main = jQuery("#main");

var pointIndex = 0; 

var hcharts = null;


var yAxisBands = "";

var hBefore = 0;
var hAfter  = 0;

var needReload = false;


$(function () {
	  //画线
		var sid="${sid}";
		
	  if(sid==""){
		  changeStationImgData(qyname); 
	  }else{
		  $.getJSON("${APP_PATH}dams/DZWL/DZWLPage/allelecfences", { "resultType": "json" }, function(data, textStatus)
		  {
 			$.each(data,function(InfoIndex,Info){//遍历json里的数据  

				//alert(sid+"___"+Info.code);

 				if(Info.code==sid){
 					changeStationImgData(Info.name); 
 					$("#lineStatInfo").html(Info.oname+" 人数  ");
 				}
			});
		  });
	  }
	  loadImgData();
		
});	

//计时器   动态加载并 切换 图标的 数据  的时间间隔
function loadImgData(){
	  setInterval('getImgData();', 10000); 
	  setInterval('changePredicteImgData();', 60000*60); 
 }


/**
*   切换站点图标     
*  @param sid   站点id
*/
function changeStationImgData(qyname){
	//获取datepicker选择的时间
    dtimeStr = $("#start").val();
	 if(dtimeStr!=""){
		 dtime=getTimeByDateStr(dtimeStr)+"";
	 }else{
		 dtime="";
	 }
     jQuery.post(yc,{qym:qyname,dateTime:dtime},function(data){
    	 
      yAxisBands = eval(data.yAxis);
      showimg([],[],[],[]);
      	//预测线
		if(data.data!=undefined){
			hcharts.series[0].setData(data.data.ren);
		}
	  
      	//实时
	  jQuery.post(url,{qym:qyname,dateTime:dtime},function(data){
		  if(undefined != data.data && undefined != data.data.ren)
			 {
			  //实时人数
			  	hcharts.series[1].setData(data.data.ren);
			 }
			 
			 if(undefined != data.data && undefined != data.data.mr)
			 {
				 //实时漫入数
				 //hcharts.series[2].setData(data.data.mr);
			 }
			 if(undefined != data.data && undefined != data.data.mc)
			 {
				 //实时漫出数
				 //hcharts.series[3].setData(data.data.mc);
			 }
			 $("#updatetime").html(formatDate(data.sj));
			 //alert(data.pyhs);
			 $("#rennum").html(formatNum(data.yhs)+" (历史预测："+formatNum(data.pyhs)+" )");
			 /* $("#u5t").html(formatNum(data.yhstb));
			 $("#minum").html(formatNum(data.mrs));
			 $("#mi5t").html(formatNum(data.mrstb));
			 $("#monum").html(formatNum(data.mcs));
			 $("#mo5t").html(formatNum(data.mcstb)); */
			 refreshPNums(data.yhs);
		 });
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
		  
	  $.getJSON("${APP_PATH}dams/DZWL/DZWLPage/allelecfences", { "resultType": "json" }, function(data, textStatus)
	  {
 		    
			var value=$("#select").val();

			$.each(data,function(InfoIndex,Info){//遍历json里的数据  
			if(value==Info.code){
				//alert(Info.oname);
				$("#lineStatInfo").html(Info.oname+" 人数  ");
				area=Info.name;
			}else{
				
			}
			});



			//var value=$("#select").val()-1;
			//$("#lineStatInfo").html(data[value].oname+" 人数  ");
			//area=data[value].name;
			
			//获取datepicker选择的时间
		    dtimeStr = $("#start").val();
			 if(dtimeStr!=""){
				 dtime=getTimeByDateStr(dtimeStr)+"";
			 }else{
				 dtime="";
			 }
			
			jQuery.post(url,{qym:area,dateTime:dtime},function(data)
			{
			  if(undefined !=data.data&& undefined != data.data.ren)
			  {
					hcharts.series[1].setData(data.data.ren) ;
					refreshPNums(data.yhs); 
			  }
			  if(undefined != data.data && undefined != data.data.mr)
			  {
					 //hcharts.series[2].setData(data.data.mr);
			  }
			  if(undefined != data.data && undefined != data.data.mc)
			  {
					 //hcharts.series[3].setData(data.data.mc);
			  }
			  $("#updatetime").html(formatDate(data.sj));
				 $("#rennum").html(formatNum(data.yhs)+" (历史预测："+formatNum(data.pyhs)+" )");
				 /* $("#u5t").html(formatNum(data.yhstb));
				 $("#minum").html(formatNum(data.mrs));
				 $("#mi5t").html(formatNum(data.mrstb));
				 $("#monum").html(formatNum(data.mcs));
				 $("#mo5t").html(formatNum(data.mcstb)); */
			});
	  });
	}  


//使用  highcharts 画图
  function showimg(ind,out,mrind,mcout){
  	
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
  		          minorGridLineWidth: 1,
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
  		        	/* {name:"漫入",data:mrind},
  		        	{name:"漫出",data:mcout} */
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
  		             },
  		             rotation:0
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
  		                	//return this.value/1000+"K";
  		                 
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
  		        	{name:'<b style="font-size:16px">历史</b>',data:ind,color:'rgb(135,179,203)',visible:false},
  		        	{name:'<b style="font-size:16px">实时</b>',data:out,color:'rgb(0,84,248)'}
  		        	/* {name:"漫入",data:mrind,color:'rgb(255,34,23)'},
  		        	{name:"漫出",data:mcout,color:'rgb(227,178,176)'} */
  		        ]
  	    });
  	}
  	 
   } 

</script>

</body>
</html>