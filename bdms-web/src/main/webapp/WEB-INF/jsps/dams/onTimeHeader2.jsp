<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实时监控</title>
<jsp:include page="damscommonheader.jsp"></jsp:include>

<script type="text/javascript">
	$(function() {

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
			$(".date").html(date + ' ' + week);
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

</script>

</head>

<div id="metrotop">
	<div id="metrotitle">
		<div id="titleName2">
				<div style="width: 800px; height: 100px;margin-left: 115px">
					<!-- <div id="totalCount" style="margin-left:160px;margin-top:33px;font-family:'微软雅黑';font-weight:bold;	font-size:25px;"></div>
					<div id="lineStation" style="float:left;margin-left:362px;margin-top:-40px;font-size:25px;font-family:'微软雅黑';"></div>
					<div id="inCount" style="float:left;margin-left:790px;margin-top:-20px;font-size:25px;font-weight:bold;	font-size:25px;font-family:'微软雅黑';"></div>
				     -->
				</div>
		</div>
	</div>

	<div id="clockdiv">
		<div id="clock" class="light">
			<div class="display">
				<div class="date"></div>
				<div class="digits"></div>
				<div id="showdiv" style="margin-top:30px"><div id="updatetime" style="padding-left:35px">14:00:00</div></div>
			</div>
		</div>
	</div>
</div>
 
</html>