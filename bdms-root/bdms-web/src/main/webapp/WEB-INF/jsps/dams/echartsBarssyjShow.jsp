<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>四色预警示意图</title>
<jsp:include page="damscommonheader.jsp"></jsp:include>
</head>
<body>
<div id="main" style="height:400px"></div>
<script type="text/javascript">
//基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
        /* option =   {
            title : {
           text: '涉疆人员在沪情况',
           subtext: '总计约1万人',
           x: 'center',
           textStyle : {
               color: '#fff',
               fontSize: 18,
               fontWeight: 'bolder'
           }
       },
       tooltip : {
           trigger: 'axis'
       },
       legend: {
           data:[
               '常住','暂住'
           ],
           orient:'vertical',
           x:'right',
           y:'center',
           textStyle : {
               color: '#fff'
           }
       },
       
       calculable : true,
       //grid: {y: 70, y2:30, x2:20,borderWidth:0},
      
       xAxis : [
           {   
               textStyle : {
               color: '#fff'
               },
               type : 'category',
               axisLine: {show:false},
               axisTick: {show:false},
               axisLabel: {
               textStyle:{color:'#fff'},
               show:true},
            
               splitArea: {show:false},
               splitLine: {show:false},
               data : ['黄浦','卢湾','徐汇','长宁','静安','普陀','闸北','虹口','杨浦','闵行','宝山','嘉定','浦东','金山','松江','青浦','奉贤','崇明']
           },
           {
               type : 'category',
               axisLine: {show:false},
               axisTick: {show:false},
               axisLabel: {show:false},
               splitArea: {show:false},
               splitLine: {show:false},
               data : ['黄浦','卢湾','徐汇','长宁','静安','普陀','闸北','虹口','杨浦','闵行','宝山','嘉定','浦东','金山','松江','青浦','奉贤','崇明']
           }
       ],
       yAxis : [
           {
               type : 'value',
               //axisLine: {show:false},
               //axisTick: {show:false},
                axisLabel: {
               textStyle:{color:'#fff'},
               show:true},
              // splitArea: {show:false},
               splitLine: {show:false}
              // axisLabel:{formatter:'{value} ms'}
           }
       ],
       series : [
           {
               name:'常住',
               type:'bar',
               
               //itemStyle: {normal: {color:'rgba(193,35,43,1)', label:{show:true}}},
               data:[1140,1155,195,175, 10,1180,176,145,186,175,166,155,151,125,149,189,110,119]
           },
           {
               name:'暂住',
               type:'bar',
               xAxisIndex:1,
              // itemStyle: {normal: {color:'rgba(181,195,52,1)', label:{show:true,textStyle:{color:'#27727B'}}}},
               data:[1100,1200,1105,1100,1156,1100,186,150,190,188,177,199,167,180,150,167,1100,125,136]
           }
       ]
       }; */
        
         option = {
        	    tooltip : {
        	        trigger: 'axis',
        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	        }
        	    },
        	    legend: {
        	    data:['红色预警','橙色预警','黄色预警','蓝色预警']
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
        	    yAxis : [
        	        {
        	            type : 'value'
        	        }
        	    ],
        	    xAxis : [
        	        {
        	            type : 'category',
        	            data : ['豫园','植物园','动物园','体育馆','游泳馆','浦东机场','外滩']
        	        }
        	    ],
        	    series : [
              	      {
            	            name:'红色预警',
            	            type:'bar',
            	           stack:'堆积',
            	            itemStyle : { 
            	            	normal: {
            	            		color:'#FF0000',
            	            		label : {show: true, position: 'insideRight'}
              	        }
              	        },
            	            data:[22, 18, 19, 23, 29, 33, 31]
            	        },
            	        {
            	            name:'橙色预警',
            	            type:'bar',
            	           stack:'堆积',
            	            itemStyle : {
            	            	normal: {
            	            		color:'#F3AA36',
            	            		label : {show: true, position: 'insideRight'}
            	        }
            	        },
            	            data:[12, 13, 10, 13, 9, 23, 21]
            	        },
        	           {
        	            name:'黄色预警',
        	            type:'bar',
        	           stack:'堆积',
        	            itemStyle : { 
        	            	normal: {
        	            		color:'#FFFF00',
        	            		label : {show: true, position: 'insideRight'}
            	        }
            	        },
        	            data:[32, 30, 30, 33, 39, 33, 32]
        	          },
        	          {
            	            name:'蓝色预警',
            	            type:'bar',
            	           stack:'堆积',
            	          itemStyle: {
            	            normal: {
            	            	color:"#0000FF",
            	                label : {show: true, position: 'insideRight'}
            	            },
            	           
            	        },
            	            data:[15, 21, 20, 15, 19, 33, 41]
            	        },
        	    ]
        	}; 
        	                    
    
    	 myChart.setOption(option);
</script>
</body>
</html>