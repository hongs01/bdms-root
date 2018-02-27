<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海轨道交通实时监控</title>

<jsp:include page="../damscommonheader.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${APP_PATH}js/layout/complex.css" />
<script type="text/javascript" src="${APP_PATH}js/layout/jquery.layout.js"></script>
<script type="text/javascript" src="${APP_PATH}js/layout/complex.js"></script>
<script type="text/javascript" src="${APP_PATH}js/heatMap/heatmap.js"></script>
<script type="text/javascript" src="${APP_PATH}js/snapsvg/snap.svg-min.js"></script>
<script type="text/javascript" src="${APP_PATH}js/svg-manipulate/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${APP_PATH}js/svg-manipulate/snap.svg.zpd.js"></script>
<%-- <script type="text/javascript" src="${APP_PATH}js/svg-manipulate/jquery.svg.pan.zoom.js"></script>
<script type="text/javascript" src="${APP_PATH}js/svg-manipulate/svg.min.js"></script>
<script type="text/javascript" src="${APP_PATH}js/svg-manipulate/svg.draggable.js"></script> --%>

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
		overflow: hidden;
	} 
	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: #1b1b1b; 
	} 
	.ui-layout-toggler { /* all 'toggler-buttons' */ 
		background:url("../../../style/images/leftarrow.png");
	}
	.ui-widget-content{
		background:#03233a;
		border:0px;
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

	var layoutSettings_Outer = {
		
		north: {
			spacing_open:			10			// cosmetic spacing
		,	togglerLength_open:		10			// HIDE the toggler button
		,	togglerLength_closed:	10			// "100%" OR -1 = full width of pane
		,	resizable: 				false
		,	slidable:				true
		//	override default effect
		,	fxName:					"none"
		}
		/* east: {
		 	resizable: 				false
	 	,	resizable: 				true
		,	spacing_open:			12
		,	spacing_closed:			12
		,	togglerLength_open:		60    
		,	togglerLength_closed:   60//pane关闭时，边框按钮的长度
		
		} */
	};	
	
	$(function() {
		
		/* $("#menu").bind("mouseout",function(e){
			alert(123);
			$("#menu")[0].style.visibility='hidden';
			alert(456);
		}); */
		
		
		//layout
		myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("north",0);
		//myLayout.sizePane("east",400);
		
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
<!-- <div class="ui-layout-north" style="background-color:#0171c7;height:auto;">
	<div id="metrotop">
		<div id="metrotitle">
			<div id="titleName"></div>
		</div>
		<div id="clockdiv">
			<div id="clock" class="light">
				<div class="display">
					<div class="date"></div>
					<div class="digits"></div>
					
					<div id="showdiv" style="margin-top:30px"><div id="updatetime" style="padding-left:5px">14:00:00</div></div>
					
				</div>
			</div>
		</div>
	</div>
</div> -->

<div class="ui-layout-center" style="background-color:white;">
<!-- <div id="inner" style="width: 100%;background: #fff;text-align: center;">  
    <button class="svgZoomBtn" zoom="1" style="width: 20%" data-inline="true">放大</button>  
    <button class="svgZoomBtn" zoom="-1" style="width: 20%" data-inline="true">缩小</button>  
</div> -->
	<!-- start map -->
<div style="width:100%;text-align: center;margin:0 auto; float:left;background:black">
 <svg id="fheatmain" width="100%" height="800px"></svg>
</div>


<div border=1 id="menu" onmouseout="out()" style="background:grey;color:red;position:absolute;top:0px;left:0px;width:80px;height:100px;z-index:2;visibility:hidden;">
	<a name="link" id="link1">进站详情</a><br/>
	<a name="link" id="link2">出站详情</a><br/>
	<a name="link" id="link3">进出和</a><br/>
	<a name="link" id="link4">进出差</a>
</div>

	<script type="text/javascript">
	
	var s = Snap("#fheatmain");
	
	var tux = Snap.load("${APP_PATH}style/9.76test3.svg", function ( loadedFragment ) {	    
	    
	    var panel_1=loadedFragment.select("#panel_1");
	    
	    /* var dzwl_=loadedFragment.select("#00001");
	    var wt=loadedFragment.select("#00002");
	    var yy=loadedFragment.select("#00003");
	    gc.click(function () {
	    	//alert(this.attr("id"));
 			ff(); 
  	 	}); 
	    yy.click(function (event) {
	    	//alert(event.button);
	    	//alert(this.attr("id"));
	    	menu.style.visibility="visible";
  	 	}); 
	    wt.click(function (event) {
	    	//alert(event.button);
	    	//alert(this.attr("id"));
	    	menu.style.visibility="hidden";
  	 	});   */
	    
	    for(i=0;i<3;i++){
    	 (function(x){ 
	   		 loadedFragment.select("#sta_01"+(17+i)).mousemove(function (e) {
	   			var menu=$("#menu")[0];
	   			var coords_x=e.clientX;
	   			var coords_y=e.clientY;
	   			menu.style.left=(coords_x+window.document.body.scrollLeft-10)+"px";
	   			menu.style.top=(coords_y+window.document.body.scrollTop-10)+"px";
	   			
	   			f(x);
	   			
	   			menu.style.visibility="visible";
	   			
	    	 });
	   		/* loadedFragment.select("#sta_01"+(17+i)).mouseout(function (e) {
	   			var menu=$("#menu")[0];
	   			menu.style.visibility="hidden";
	   			 //f(x); 
	    	 }); */
	    	
    		 loadedFragment.select("#dzwl_0000"+(1+i)).click(function () {
	   			 ff(x); 
	    	 }); 
	    	 
	   		
    	 }(i)); 
	    }
	     
		s.append( loadedFragment ); 
		s.zpd();
    });
	
	var f = function (m){ 
		
		$("#link1").unbind("click");
		$("#link1").click(function(){window.open("${APP_PATH}dams/metro/in/page/01"+(17+m))});
		
		$("#link2").unbind("click");
		$("#link2").click(function(){window.open("${APP_PATH}dams/metro/out/page/01"+(17+m))});
		
		$("#link3").unbind("click");
		$("#link3").click(function(){window.open("${APP_PATH}dams/metro/inoutsum/page/01"+(17+m))});
		
		$("#link4").unbind("click");
		$("#link4").click(function(){window.open("${APP_PATH}dams/metro/inoutsub/page/01"+(17+m))});
		
		/* $("#link2").click(function(){window.open("${APP_PATH}dams/metro/out/page/01"+(17+m))});
		$("#link3").click(function(){window.open("${APP_PATH}dams/metro/inoutsum/page/01"+(17+m))});
		$("#link4").click(function(){window.open("${APP_PATH}dams/metro/inoutsub/page/01"+(17+m))}); */
	}
	var ff = function (m){ 
		window.open("${APP_PATH}dams/DZWL/DZWLPage/page/0000"+(1+m));
	} 
	
	function test(id){
		window.open(""+id);
	}
	
	function out()
	{
	if(window.event.toElement.id!="menu"&&window.event.toElement.name!="link")
		/* $("#link1")[0].setAttribute("onclick","alert(1)");
		$("#link2")[0].setAttribute("onclick","alert(2)");
		$("#link3")[0].setAttribute("onclick","alert(3)");
		$("#link4")[0].setAttribute("onclick","alert(4)"); */
		
		
	  menu.style.visibility="hidden";
	}
    
	</script>

<!-- end map --> 	
</div>	 
</body>
</html>