<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>告警级别实时监控</title>

<jsp:include page="../damscommonheader.jsp"></jsp:include>
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
		background: #FFF; 
		border: 0px solid #BBB; 
		padding: 10px; 
		overflow: auto;
	} 
	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: #1b1b1b; 
	} 
	.ui-layout-toggler { /* all 'toggler-buttons' */ 
		background:url("../../../style/images/leftarrow.png");
	}
	</style>
	
	


<script type="text/javascript">

	function getGFtimes(str){
		var times=str.split(":");
		
		if(times[0]=="07" || times[0]=="17"){
			count=0;
		}else if(times[0]=="08" || times[0]=="18"){
			count=12;
		}else{
			count=24;
		}
		if(count!=24){
			//alert(Math.floor(parseInt(times[1])/5));
			count+=Math.floor(parseInt(times[1])/5);
		}
		return count;
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

	var layoutSettings_Outer = {
		
		north: {
			spacing_open:			0			// cosmetic spacing
		,	togglerLength_open:		0			// HIDE the toggler button
		,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
		,	resizable: 				false
		,	slidable:				false
		//	override default effect
		,	fxName:					"none"
		},
		east: {
		 	resizable: 				false
	 	,	resizable: 				true
		,	spacing_open:			12
		,	spacing_closed:			12
		,	togglerLength_open:		60    
		,	togglerLength_closed:   60//pane关闭时，边框按钮的长度
		
		}
	};	
	
	$(function() {
		//select prewarding type
		$("#selecttype").selectmenu({width: 150});
		 showStations("4");
		$("#selecttype").on("selectmenuchange",function( event, ui){
			var typeId=$("#selecttype").val();
			
			showStations(typeId);
		});
		
		//layout
		myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("north",100);
		myLayout.sizePane("east",400);
		
		//clock
		var clock = $('#clock');
		//定义数字数组0-9
		var digit_to_name = [ 'zero', 'one', 'two', 'three', 'four', 'five',
				'six', 'seven', 'eight', 'nine' ];
		//定义星期
		var weekday = [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ];
		var digits = {};
		//定义时分秒位置
		var positions = [ 'h1', 'h2', ':', 'm1', 'm2', ':', 's1', 's2' ];
		//构建数字时钟的时分秒
		var digit_holder = clock.find('.digits');
		$.each(positions, function() {
			if (this == ':') {
				digit_holder.append('<div class="dots">');
			} else {
				var pos = $('<div>');
				for ( var i = 1; i < 8; i++) {
					pos.append('<span class="d' + i + '">');
				}
				digits[this] = pos;
				digit_holder.append(pos);
			}
		});
		
		// 让时钟跑起来
		(function update_time() {
			//调用moment.js来格式化时间
			var now = moment().format("HHmmss");
			//处理ie8兼容性问题
				if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE8.0"){
					digits.h1.html(gettimeHtml(digit_to_name[now[0]]));
					digits.h2.html(gettimeHtml(digit_to_name[now[1]]));
					digits.m1.html(gettimeHtml(digit_to_name[now[2]]));
					digits.m2.html(gettimeHtml(digit_to_name[now[3]]));
					digits.s1.html(gettimeHtml(digit_to_name[now[4]]));
					digits.s2.html(gettimeHtml(digit_to_name[now[5]]));
				}else{
					digits.h1.attr('class', digit_to_name[now[0]]);
					digits.h2.attr('class', digit_to_name[now[1]]);
					digits.m1.attr('class', digit_to_name[now[2]]);
					digits.m2.attr('class', digit_to_name[now[3]]);
					digits.s1.attr('class', digit_to_name[now[4]]);
					digits.s2.attr('class', digit_to_name[now[5]]);
				}
			var date = moment().format("YYYY年MM月DD日");
			var week = weekday[moment().format('d')];
			$(".date").html("<span style='font-weight:bold'>"+date + ' ' + week+"</span>");
			// 每秒钟运行一次
			setTimeout(update_time, 1000);
		})();
		
		//select prewarding type
		 
			$( ".ui-layout-toggler" ).on("click",function(event,ui){
				var cameraId=$("#selectvideo").val();
				changeStationImgData(cameraId);
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
	});
	
	function gettimeHtml(num){
		switch (num) {
		  case "one": return "<div><span class='d5'></span><span class='d7'></span></div>";
		    break;
		  case "two": return "<div><span class='d1'></span><span class='d5'></span><span class='d2'></span><span class='d6'></span><span class='d3'></span></div>";
		    break;
		  case "three": return "<div><span class='d1'></span><span class='d5'></span><span class='d2'></span><span class='d7'></span><span class='d3'></span></div>";
		    break;
		  case "four": return "<div><span class='d4'></span><span class='d2'></span><span class='d5'></span><span class='d7'></span></div>";
		    break;
	      case "five": return "<div><span class='d1'></span><span class='d4'></span><span class='d2'></span><span class='d7'></span><span class='d3'></span></div>";
	    	break;
	    	case "six": return "<div><span class='d1'></span><span class='d4'></span><span class='d2'></span><span class='d6'></span><span class='d3'></span><span class='d7'></span></div>";
	    	break;
	    	case "seven": return "<div><span class='d1'></span><span class='d5'></span><span class='d7'></span></div>";
	    	break;
	    	case "eight": return "<div><span class='d1'></span><span class='d2'></span><span class='d3'></span><span class='d4'></span><span class='d5'></span><span class='d6'></span><span class='d7'></span></div>";
	    	break;
	    	case "nine": return "<div><span class='d1'></span><span class='d4'></span><span class='d2'></span><span class='d5'></span><span class='d7'></span></div>";
	    	break;
	    	case "zero": return "<div><span class='d1'></span><span class='d4'></span><span class='d5'></span><span class='d6'></span><span class='d7'></span><span class='d3'></span></div>";
	    	break;
		  default: return "";
		}
	}
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
		 
			if(num<parseInt(level[0])*pSize){
				$(id).css("background-image","url('"+"../../../style/images/luren.png"+"')");
			}else if(num<parseInt(level[1])*pSize){
				$(id).css("background-image","url('"+"../../../style/images/yeren.png"+"')");
			}else if(num<parseInt(level[2])*pSize){
				$(id).css("background-image","url('"+"../../../style/images/orren.png"+"')");
			}else{
				$(id).css("background-image","url('"+"../../../style/images/reren.png"+"')");
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
			
			$((id+"num")).html("<span style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:left;font-weight:bold;width:280px;color:white;font-family: '微软雅黑';'>"+formatNum(num)+"（"+formatNum(numh)+"）</span>");
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
 
 
 
 	
</script>

</head>


<body>
<div class="ui-layout-north" style="background-color:#0171c7;height:auto;overflow:hidden">
	<div id="metrotop">
		<div id="metrotitle">
			<!-- <div id="titleName1"></div> -->
		</div>
		<div id="clockdiv">
			<div id="clock" class="light">
				<div class="display">
					<div class="date"></div>
					<div class="digits"></div>
					<!-- 
					<div id="showdiv" style="margin-top:30px"><div id="updatetime" style="padding-left:5px">14:00:00</div></div>
					 -->
				</div>
			</div>
		</div>
	</div>
</div>

<div class="ui-layout-center" style="background-color:#1b1b1b;overflow:hidden">
	<div style="width:100%; height:100px;">
		<div id="lineStatInfo" style="color:white;"><span style='color:white；font-family: '微软雅黑';'>预警站点监控</span></div>
	</div>
	<div id="main" style="height:800px;width:100%; float:left;background-color:pink;">
	<div id="type" style="height:30px;width:100%;margin:5px;">
	<div style="height:30px;width:100px;float:left;">告警类型：</div>
	    <div style="float:left;">
	     <select id="selecttype">			
	    <!-- <option value="0">全部</option> -->
	    <option value="4">红色预警</option>
	    <option value="3">橙色预警</option>
	    <option value="2">蓝色预警</option>
	    <option value="1">绿色预警</option>
		</select> 
	</div>
	</div>
	
	<div style="height:30px;width:100%;margin:5px">
	<div style="height:30px;width:100px;float:left;">告警级别：</div>   
	<div style="height:30px;width:100px;color: green;float:left;">1:绿色预警</div> 
	<div style="height:30px;width:100px;color: yellow;float:left;"> 2：黄色预警</div>
    <div style="height:30px;width:100px;color: orange;float:left;"> 3：橙色预警</div> 
    <div style="height:30px;width:100px;color: red;float:left;"> 4：红色预警</div>
	</div> 
	
	<div style="float:left;width:380px;height:30px;margin:5px;">
				<div  style="width:100px;height:15px;float:left;margin-left:10px;">站点</div>
				<div  style="width:100px;height:15px;float:left;margin-left:10px;">人数</div>
				<div  style="width:100px;height:15px;float:left;margin-left:10px;">级别</div>
		</div>
	<div id="tb";>
	<div id="left" style="width:380px;height:30px;margin:5px;">
			<div id="stat" style="width:100px;height:15px;float:left;margin-left:10px;"></div>
			<div id="time" style="width:100px;height:15px;float:left;margin-left:10px;"></div>
			<div id="level" style="width:100px;height:15px;float:left;margin-left:10px;"></div>
	</div>
	</div>
</div>
 
 </div>
 
 <script type="text/javascript">
/*  
 $(function () {
	 // loadImgData();
	

	});	 */
 
 function myrefresh(){
	 window.location.reload();
 }
 setTimeout('myrefresh()',5*60*1000);
 

//计时器   动态加载并 切换 图标的 数据  的时间间隔
/* function loadImgData(){
	  setInterval('showStations();', 5*60*1000); 
} */
var obj;
		var tb = $("#tb");
		var model = $("#left");
		var tab = null;
		//showStations();
	   	  /* function showStations(){
		//	$.ajax({
			//	url:'${APP_PATH}dams/prewaring/prewaringLevel/prewaringdata',
				// dataType:'json',
				 
			//	 success:function(data){
				 //	for(i=0;i<data.length;i++){
	 			//			 obj = data[i];
	 						 tab = model.clone().css("display","block");
	 						   
	 						 tab.find("#stat").text("aaa");
	 						 tab.find("#time").text("bbb");
	 						 tab.find("#level").text("ccc");
	 						 tb.append(tab);
		//				}
				 
			//	 }
		//	});
			
	    }
	   	   */
	   	
	   	   /*  function showStations(lev){
	   		   alert(lev+"111");
	 			$.ajax({
	 				url:'${APP_PATH}dams/prewaring/prewaringLevel/prewaringdata',
	 				 dataType:'json',
	 				 data:{Integer:lev},
	 				 success:function(data){
	 					 console.log(data);
	 					 alert( "data.length:"+data.length+"data:"+data);
	 				 	for(i=0;i<data.length;i++){
	 	 						 obj = data[i];
	 	 						 tab = model.clone().css("display","block");
	 	 						 alert("aaa");
	 	 						   alert(obj.level);
	 	 						 tab.find("#stat").text("obj.station");
	 	 						 tab.find("#time").text("obj.enterTimes");
	 	 						 tab.find("#level").text("obj.level");
	 	 						 tb.append(tab);
	 						}
	 				 
	 				 }
	 			});
	 			
	 	    }    */
	 	 var url = "${APP_PATH}dams/prewaring/prewaringLevel/prewaringdata/";
	 	//var  url:"${APP_PATH}dams/prewaring/prewaringLevel/prewaringdata"
	   	 function showStations(lev){
	 	    	/*  jQuery.post(url,{Integer:lev},function(data){
	 	    		 console.log(data);
	 	    		 console.log(data.length);
	 	    		for(i=0;i<data.length;i++){
 						 obj = data[i];
 						 tab = model.clone().css("display","block");
 						 //alert("aaa");
 						   //alert(obj.level);
 						 tab.find("#stat").text("obj.station");
 						 tab.find("#time").text("obj.enterTimes");
 						 tab.find("#level").text("obj.level");
 						 tb.append(tab);
					}
	 			 }); */
	 			 
	 		var geturl=url+lev;
	 			 
	   		jQuery.get(geturl,function(data){
	   			
	   			//console.log(data);
	    		// console.log(data.length);
	    		for(i=0;i<data.length;i++){
					 obj = data[i];
					 tab = model.clone().css("display","block");
					 //alert("aaa");
					   //alert(data[i].level);
					 tab.find("#stat").text(obj.station);
					 tab.find("#time").text(obj.enterTimes);
					 tab.find("#level").text(obj.level);
					 tb.append(tab);
				}
	   		})
	 			
	 	    }  
	 	   	   
	    </script>
</body>
</html>