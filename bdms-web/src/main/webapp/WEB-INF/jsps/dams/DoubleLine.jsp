<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>双折线图显示</title>
<jsp:include page="damscommonheader.jsp"></jsp:include>
</head>
<body>
<div id="main" style="height:400px"></div>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('main'));
var option = {
	    title : {
	        text: '双数值轴折线',
	        subtext: '纯属虚构'
	    },
	    tooltip : {
	        trigger: 'axis',    //触发类型
	        axisPointer:{
	            show: true,
	            type : 'cross'
	             
	        },
	        formatter : function (params) {
	            return params.seriesName + ' : [ '
	                   + params.value[0] + ', ' 
	                   + params.value[1] + ' ]';
	        }
	    },
	    legend: {
	        data:['折线1','折线2']
	    },
	   
	    calculable : true,
	    xAxis : [
	        {
	            type: 'value',
	            name:'num'
	        }
	    ],
	    yAxis : [
	        {
	            type: 'value',
	            axisLine: {
	                lineStyle: {
	                    //color: '#FFFF00'
	                }
	            },
	           name:'num'
	        }
	    ],
	    series : [
	        {
	            name:'折线1',
	            type:'line',
	            data:[
	                [9.5, 18],[9.5,8], [10, 8], [10, 5], [10, 2]
	            ],
	        },
	        {
	            name:'折线2',
	            type:'line',
	            data:[
	                [6, 5], [10, 5], [12, 5], [12, 7],[15,7] 
	            ]
	        }
	    ]
	};
	                    
	                    
myChart.setOption(option);
</script>
</body>
</html>