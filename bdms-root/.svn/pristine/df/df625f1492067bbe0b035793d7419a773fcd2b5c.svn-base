<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OD热门线路图</title>
<jsp:include page="damscommonheader.jsp"></jsp:include>
</head>
<body>
<div id="main" style="height:600px">
</div>
<script type="text/javascript">

var myChart = echarts.init(document.getElementById('main'));

//自定义扩展图表类型：mapType = body
echarts.util.mapData.params.params.OD = {
   getGeoJson: function (callback) {
       $.ajax({
           url:'${APP_PATH}style/yu_2_svg.svg',
           dataType: 'xml',
           success: function(xml) {
              /*  var str=JSON.stringify(xml);
           	alert('hh'+str); */
           	callback(xml)
           }
       });
   }
} 

/* 
var geoCoord={"商城路":["365.71","409.11"],"肇嘉浜路":["273.85","426.62"]};

var md=[{"name":"肇嘉浜路"},{"name":"商城路"}];
var ml=[
		   [
         {name:'肇嘉浜路'}, 
         {name:'商城路'}      //从一个站点流向另一个站点
     ],
     [
          {name:'九亭'}, 
          {name:'肇嘉浜路'}
      ],
      [
       {name:'松江南站'}, 
       {name:'松江新城'}
     ],
     [
         {name:'松江新城'}, 
         {name:'九亭'}
     ]];

var mpd=[
      {name: '肇嘉浜路'},
      {name: '商城路'},
      {name: '松江南站'},
      {name: '松江新城'},
      {name: '九亭'}
  ]; */

$(function(){
	
	
	
	$.ajax({
		// url:'${APP_PATH}dams/metro/od/oddatao',
		url:'${APP_PATH}dams/metro/od/odshortestpath',
		
		 dataType:'json',
		 success:function(data)
		 {
			 //alert(data);
			 var gc = eval("("+data[0]+")")
			 var md = eval("("+data[1]+")")
			 var mp = eval("("+data[2]+")") 
			 var mpd= eval("("+data[3]+")")   
		     
			 /* var gc = eval("("+data[0]+")")
			 var md = eval("("+data[2]+")")
			 var mp = eval("("+data[1]+")")
			 var mpd= eval("("+data[3]+")") */   
			 createOption(gc,md,mp,mpd)
			 //series.geoCoord = data.geoCoord;
			 //series.markPoint.data=data.name;
			 //series.markLine.data=ml;
			// myChart.setOption(option);
			// console.log(series.geoCoord);
			 //console.log( series.markPoint.data);
			
		 }
	 });
	
	//var hot_tab = $("#hot_tab");
	showHotLine(0);
	
	
	
	
 
});


function createOption(g,n,ml,mld){
	var option = {
		    backgroundColor:'#1b1b1b',
		    title : {
		       // text : 'OD轨道交通热门线路示意图',
		        textStyle: {
		            color: '#000'
		        }
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: '{b}'
		    },
		    color: ['white', 'rgba(100, 149, 237, 1)', 'green'],
		    legend: {
		        data: ['热门线路']
		    },
		    series : [
		        {
		            name: '热门线路',
		            type: 'map',
		            mapType: 'OD',
		            roam:true,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data: [],
		            geoCoord: g, 
		         
		            markPoint : {
		                symbolSize : 3,
		                data : n
		            },
		            
		            markLine : {
		              //  smooth:true,
		                effect : {
		                    show: true,
		                    scaleSize: 1,
		                    period: 20,        //流动速度
		                    color: '#fff',
		                    shadowBlur: 2
		                },
		                symbol: ['none'],
		                itemStyle : {
		                    normal: {
		                        borderWidth:1,     //线宽
		                        lineStyle: {
		                            type: 'solid',
		                            shadowBlur: 10
		                        }
		                    }
		                },
		    		data :ml
		    	}
		        },
		        
		        {
		            type: 'map',
		            mapType: 'OD',
		            data: [],
		            markPoint: {
		                symbol: 'emptyCircle',
		                effect: {
		                    show: true,
		                    color: 'rgba(218, 70, 214, 1)'
		                },
		                data :mld
		            }
		        }			     
		    ]
		};
	 myChart.setOption(option);
	 console.log(option.series[0].geoCoord);
}

</script>
</body>
</html>