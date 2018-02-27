<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>echartsLine示意图</title>
<jsp:include page="damscommonheader.jsp"></jsp:include>
<script type="text/javascript">

$(function(){
    var clock = $('#clock');
	//定义数字数组0-9
	var digit_to_name = ['zero','one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine'];
	//定义星期
	var weekday = ['周日','周一','周二','周三','周四','周五','周六'];

    var digits = {};

    //定义时分秒位置
    var positions = [
        'h1', 'h2', ':', 'm1', 'm2', ':', 's1', 's2'
    ];

    //构建数字时钟的时分秒

    var digit_holder = clock.find('.digits');

    $.each(positions, function(){

        if(this == ':'){
            digit_holder.append('<div class="dots">');
        }
        else{

            var pos = $('<div>');

            for(var i=1; i<8; i++){
                pos.append('<span class="d' + i + '">');
            }

            digits[this] = pos;

            digit_holder.append(pos);
        }

    });
	

    // 让时钟跑起来
    (function update_time(){

        //调用moment.js来格式化时间
        var now = moment().format("HHmmss");

        digits.h1.attr('class', digit_to_name[now[0]]);
        digits.h2.attr('class', digit_to_name[now[1]]);
        digits.m1.attr('class', digit_to_name[now[2]]);
        digits.m2.attr('class', digit_to_name[now[3]]);
        digits.s1.attr('class', digit_to_name[now[4]]);
        digits.s2.attr('class', digit_to_name[now[5]]);

		var date = moment().format("YYYY年MM月DD日");
		var week = weekday[moment().format('d')];
		$(".date").html(date + ' ' + week);


        // 每秒钟运行一次
        setTimeout(update_time, 1000);

    })();
});

</script>
</head>

<style typoe="text/css">
#backImg{
 /**background: url(${APP_PATH}style/images/grey_wash_wall.png) */
 background:black ;
}
</style>
<body id="backImg">

<div id="heatmain" style="height:1528px;width:1500px; float: left;">
</div>

<script type="text/javascript">
var myChart = echarts.init(document.getElementById('heatmain'));
var heatData = [];
for (var i = 0; i < 4; ++i) {
    heatData.push([
       	480+Math.random()*2,
        728+Math.random()*2,
        Math.random()
    ]);
}

for (var i = 0; i < 10; ++i) {
    heatData.push([
       	252+Math.random()*5,
        782+Math.random()*10,
        Math.random()
    ]);
}


for (var i = 0; i < 8; ++i) {
    heatData.push([
       	778+Math.random()*5,
        778+Math.random()*10,
        Math.random()
    ]);
}


for (var i = 0; i < 4; ++i) {
    heatData.push([
       	830+Math.random()*5,
        778+Math.random()*10,
        Math.random()
    ]);
}

for (var i = 0; i < 8; ++i) {
    heatData.push([
       	730+Math.random()*5,
        640+Math.random()*10,
        Math.random()
    ]);
}


for (var i = 0; i < 4; ++i) {
    heatData.push([
       	690+Math.random()*5,
        630+Math.random()*10,
        Math.random()
    ]);
}

for (var i = 0; i < 4; ++i) {
    heatData.push([
       	630+Math.random()*5,
        630+Math.random()*10,
        Math.random()
    ]);
}

for (var i = 0; i < 4; ++i) {
    heatData.push([
       	580+Math.random()*5,
        630+Math.random()*10,
        Math.random()
    ]);
}

for (var i = 0; i < 10; ++i) {
    heatData.push([
       	980+Math.random()*5,
        840+Math.random()*10,
        Math.random()
    ]);
}
for (var i = 0; i < 10; ++i) {
    heatData.push([
       	560+Math.random()*5,
        880+Math.random()*10,
        Math.random()
    ]);
}
for (var i = 0; i < 8; ++i) {
    heatData.push([
       	520+Math.random()*5,
        910+Math.random()*10,
        Math.random()
    ]);
}
for (var i = 0; i < 4; ++i) {
    heatData.push([
       	630+Math.random()*5,
        825+Math.random()*10,
        Math.random()
    ]);
}
for (var i = 0; i < 4; ++i) {
    heatData.push([
       	630+Math.random()*5,
        885+Math.random()*10,
        Math.random()
    ]);
}
for (var i = 0; i < 4; ++i) {
    heatData.push([
       	680+Math.random()*5,
        825+Math.random()*10,
        Math.random()
    ]);
}



option = {
    title : {
        text: '上海市地铁交通热力'
    },
    series : [
        {
            type : 'heatmap',
            data : heatData,
            hoverable : false
        }
    ]
};
                    
 
	myChart.setOption(option); 

</script>
</body>
</html>