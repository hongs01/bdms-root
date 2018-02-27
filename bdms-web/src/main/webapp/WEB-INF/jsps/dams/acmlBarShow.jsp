<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="damscommonheader.jsp"></jsp:include>
<title>堆积柱状图展示</title>
</head>
<body>
<div id="main" style="height:400px"></div>
 <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        var option = {
        	    tooltip : {
        	        trigger: 'axis',
        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	        }
        	    },
        	    legend: {
        	        data:['直接访问', '邮件营销','联盟广告','视频广告','搜索引擎']
        	    },
        	    toolbox: {
        	        show : true,
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    calculable : true,
        	    xAxis : [
        	             
						{
							name:'星期',
						    type : 'category',
						    data : ['周一','周二','周三','周四','周五','周六','周日']
						}
        	       
        	    ],
        	    yAxis : [
        	             {
             	            type : 'value'
             	        }
        	    ],
        	    series : [
        	        {
        	            name:'直接访问',
        	            type:'bar',
        	            stack: '总量',
        	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
        	            data:[320, 302, 301, 334, 390, 330, 320]
        	        },
        	        {
        	            name:'邮件营销',
        	            type:'bar',
        	            stack: '总量',
        	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
        	            data:[120, 132, 101, 134, 90, 230, 210]
        	        },
        	        {
        	            name:'联盟广告',
        	            type:'bar',
        	            stack: '总量',
        	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
        	            data:[220, 182, 191, 234, 290, 330, 310]
        	        },
        	        {
        	            name:'视频广告',
        	            type:'bar',
        	            stack: '总量',
        	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
        	            data:[150, 212, 201, 154, 190, 330, 410]
        	        },
        	        {
        	            name:'搜索引擎',
        	            type:'bar',
        	            stack: '总量',
        	            itemStyle : { normal: {label : {show: true, position: 'insideLeft'}}},
        	            data:[820, 832, 901, 934, 1290, 1330, 1320]
        	        }
        	    ]
        	};
        	                    
        	
      myChart.setOption(option); 
        
</script>
</body>
</html>