<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>metro header</title>
<jsp:include page="../damscommonheader.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${APP_PATH}js/layout/complex.css" />
<script type="text/javascript" src="${APP_PATH}js/layout/jquery.layout.js"></script>
<script type="text/javascript" src="${APP_PATH}js/layout/complex.js"></script>

<script type="text/javascript" src="${APP_PATH}js/jquery-ui-i18n.js"></script>

<style type="text/css">
.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: rgb(3, 35, 58); 
	} 
</style>

<script type="text/javascript">

$(function(){
	//整体布局
	myLayout = $("body").layout(layoutSettings_Outer);
	//myLayout.sizePane("north",200);
	myLayout.sizePane("east",300);
});

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
		,	togglerTip_open:	"关闭"
		,	togglerTip_closed:	"打开"
		,	resizerTip:	"可调整大小"
		}
	};	
	
$(function() {
	//alert(getGFtimes("08:35:00"));
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
		//alert(now);
	    if (now=="010000"){
			 window.location.reload();
		}    
		var date = moment().format("YYYY年MM月DD日");
		var week = weekday[moment().format('d')];
		$("#day").html(date);
		$(".date").html("<span style='font-weight:bold'>"+date + ' ' + week+"</span>");
		// 每秒钟运行一次
		setTimeout(update_time, 1000);
	})();
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

//对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) 
{ //author: meizz 
var o = { 
"M+" : this.getMonth()+1,                 //月份 
"d+" : this.getDate(),                    //日 
"h+" : this.getHours(),                   //小时 
"m+" : this.getMinutes(),                 //分 
"s+" : this.getSeconds(),                 //秒 
"q+" : Math.floor((this.getMonth()+3)/3), //季度 
"S"  : this.getMilliseconds()             //毫秒 
}; 
if(/(y+)/.test(fmt)) 
fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
for(var k in o) 
if(new RegExp("("+ k +")").test(fmt)) 
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
return fmt; 
}
	

</script>
</head>
<body>
    <!-- start header 
<div class="ui-layout-north" style="background-color:#0171c7;height:auto;overflow:hidden">
	<%-- <div id="metrotop">
	   <!--start title  -->
		<div id="metrotitle">
			<div id='${topimg}'>
			</div>    
		</div>
		<!-- end title  -->
		
		<!-- start clock -->
		<div id="clockdiv">
			<div id="clock" class="light">
				<div class="display">
					<div class="date"></div>
					<div class="digits"></div>
				</div>
			</div>
		</div> --%>
		<!-- end clock
	</div>-->

    <!-- end   header-->
</body>
</html>