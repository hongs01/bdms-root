<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>进站显示图</title>
<jsp:include page="damscommonheader.jsp"></jsp:include>
</head>
<body>
<div id="main" style="height:500px;width:900px;">

</div>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('main'));

var option = {
	    title : {
	        text: '未来一周气温变化',
	        subtext: '纯属虚构'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['最高气温','最低气温']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} °C'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'最高气温',
	            type:'line',
	            data:[11, 11, 15, 13, 12, 13, 10],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'最低气温',
	            type:'line',
	            data:[1, -2, 2, 5, 3, 2, 0],
	            markPoint : {
	                data : [
	                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};
	
	
myChart.setOption(option); 
	                    
</script>
</body>
</html>