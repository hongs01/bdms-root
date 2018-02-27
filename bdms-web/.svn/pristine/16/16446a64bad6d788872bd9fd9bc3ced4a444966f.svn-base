<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上海市大人流实时监控得分表</title>
<jsp:include page="../damscommonheader.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${APP_PATH}js/layout/complex.css"/>
<script type="text/javascript" src="${APP_PATH}js/layout/jquery.layout.js"></script>
<script type="text/javascript" src="${APP_PATH}js/layout/complex.js"></script>
</head>
<script type="text/javascript">

$(function() {
    $( "#accordion" ).accordion({
      collapsible: true,
      collapsed:false,
      active:0,
      showAll: true,  
    });
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
		}
	};	
	
$(function() {
	showScore();
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
	 /*    if (now=="010000"){
			 window.location.reload();
		}     */
		var date = moment().format("YYYY年MM月DD日");
		var week = weekday[moment().format('d')];
		//$("#day").html(date);
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
</script>
<body>

<div id="accordion">
  <h3>
  <div id="left" style="width:380px;height:40px;margin:5px;font-size: 18px;">
						<div id="typeName" style="width:100px;height:35px;float:left;margin-left:10px;">轨交</div>
						<div id="typeCode" style="width:100px;height:35px;float:left;margin-left:10px;">0725</div>
						<div id="firstMark" style="width:100px;height:35px;float:left;margin-left:10px;">7.83</div>
				 
 </div>
 </h3>
  <div>
    
                <div id="right" style="width:380px;height:40px;margin:5px;font-size: 18px;">
						<div id="Name" style="width:100px;height:35px;float:left;margin-left:10px;">进站人数</div>
						<div id="secondMark" style="width:100px;height:35px;float:left;margin-left:10px;">7.35</div>
				</div>
				 <div id="right" style="width:380px;height:40px;margin:5px;font-size: 18px;">
						<div id="Name" style="width:100px;height:35px;float:left;margin-left:10px;">出站人数</div>
						<div id="secondMark" style="width:100px;height:35px;float:left;margin-left:10px;">9.55</div>
				</div>
				 <div id="right" style="width:380px;height:40px;margin:5px;font-size: 18px;">
						<div id="Name" style="width:100px;height:35px;float:left;margin-left:10px;">进出站和</div>
						<div id="secondMark" style="width:100px;height:35px;float:left;margin-left:10px;">7.35</div>
				</div>
    </p>
  </div>
  <h3> 
  <div id="left2" style="width:380px;height:40px;margin:5px;font-size: 18px;">
						<div id="typeName" style="width:100px;height:35px;float:left;margin-left:10px;">轨交</div>
						<div id="typeCode" style="width:100px;height:35px;float:left;margin-left:10px;">0243</div>
						<div id="firstMark" style="width:100px;height:35px;float:left;margin-left:10px;">17.60</div>
				 
 </div>
 </h3>
  <div>
     
    Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet
    purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor
    velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In
    suscipit faucibus urna.
    
  </div>
  <h3><div id="left3" style="width:380px;height:40px;margin:5px;font-size: 18px;">
						<div id="typeName" style="width:100px;height:35px;float:left;margin-left:10px;">电子围栏</div>
						<div id="typeCode" style="width:100px;height:35px;float:left;margin-left:10px;">00002</div>
						<div id="firstMark" style="width:100px;height:35px;float:left;margin-left:10px;">63.62</div>
				 
				</div>
				</h3>
  <div>
    <p>
    Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
    Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero
    ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis
    lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.
    </p>
    <ul>
      <li>List item one</li>
      <li>List item two</li>
      <li>List item three</li>
    </ul>
  </div>
  <h3>
  <div id="left4" style="width:380px;height:40px;margin:5px;font-size: 18px;">
						<div id="typeName" style="width:100px;height:35px;float:left;margin-left:10px;">电子围栏</div>
						<div id="typeCode" style="width:100px;height:35px;float:left;margin-left:10px;">00001</div>
						<div id="firstMark" style="width:100px;height:35px;float:left;margin-left:10px;">42.41</div>
				 
 </div>
 </h3>
  <div>
    
    Cras dictum. Pellentesque habitant morbi tristique senectus et netus
    et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in
    faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia
    mauris vel est.
    Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per
    inceptos himenaeos.
    
  </div>
</div>
 
</body>


 <script type="text/javascript">
    var obj;
	var tb = $("#tb");
	var model = $("#left");
	var tab = null;
	
	 var url = "${APP_PATH}dams/score/scorePage/areaData";
	   	// function showScore(lev){
	   		 function showScore(){
	 	    	// alert(111);
	 		//var geturl=url+lev;
	 		var geturl=url;
	 			 
	   		jQuery.get(geturl,function(data){
	   		//	alert(data.length);
	    		for(i=0;i<data.length;i++){
					 obj = data[i];
					 tab = model.clone().css("display","block");
 					 tab.find("#areaName").text(obj.areaName);
					 tab.find("#id").text(obj.id);
					 tab.find("#score").text(obj.data);
					 tb.append(tab);
				}
	   		});
	 			
	 	    }  
 </script>
</html>