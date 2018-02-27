<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>echartsBar示意图</title>
<jsp:include page="damscommonheader.jsp"></jsp:include>
</head>

<body>
 
<div id="main" style="height:400px"></div>
 <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
   var  option = {
        	    title : {
        	        text: '当月电子围栏趋势图',
        	        subtext: '单个站点'
        	    },
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['当月人流量','上月人流量']
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
        	            axisLabel : {
        	                formatter: '{value} 日',
        	                rotate:60,
        	            },
        	           
        	            data : 
        ['01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value',
        	            max:300,
        	            	axisLabel : {
        	                    formatter: '{value} 千人'
        	                }
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'当月人流量',
        	            type:'bar',
        	            data:
       [2,4,7,23,25,76,135, 162,32,20,6,3,20,25,36,45,25,65,98,74,85,120,130,145,152,168,159,198,159,164,59],
        	        markPoint : {
                        data : [
                            {type : 'max', name: '最大值'}
                        ]
                    }
        	        },
        	        {
        	            name:'上月人流量',
        	            type:'bar',
        	            data:
       [2,5,9,26,28,70,175,182,48,18,6,2,22,36,33,44,28,63,99,77,80,110,120,149,150,166,170,200,180,150,55],
       markPoint : {
           data : [
               {type : 'max', name: '最大值'}
           ]
       }
        	        }
        	    ]
        	};
        	myChart.setOption(option); 
         
        
    </script>                   
</body>
</html>