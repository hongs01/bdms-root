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
//地铁线
//var lines=['1号线','2号线','3号线'];
//站点
//var stations=['1号线','1a','1b','1c'],
 //            ['2号线','2a','2b','2c'],
//             ['3号线','3a','3b','3c'];
     
//初始化加载
/* window.onload=function(){
	var line=document.getElementById("line");
	var station=document.getElementById("station");
	//添加地铁线路数据
	for(var i=0;i<lines.length;i++){
		line.options.add(new Option(lines[i],lines[i]));
	}
	//添加站台数据
	for(var i=1;i<stations[0].length;i++){
		station.options.add(new Option(station[0][i],stations[0][i]));
	}
}
//线路变化的时候触发
function ch(){
	var line=document.getElementById("line");
	var station=document.getElementById("station");
	for(var i=0;i<lines.length;i++){
		if(station[i][0]=line.value){
			station.options.length=0;
			for(var j=1;j<stations[i].length;j++){
				station.options.add(new Option(stations[i][j],stations[i][j]));
			}
		}
	}
} */




$(function(){
	
	//alert(0);
	
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



$(function(){
	/*  $(".progressBar").each(function () {
	        $(this).trigger("ondblclick");
	    }); */
	    
	    $( "#quanshi" ).button();
	    
	    $( "#selectmenu").selectmenu({width: 100});
		$( "#selectmenu2").selectmenu({width: 100});
		
		$.getJSON("../../dams/elecfence/lines", { "resultType": "json" }, function(data, textStatus)
			{
			   $("#selectmenu").empty();//先清空tbody  
				var strHTML = "<option>请选择</option>";  
				$.each(data,function(InfoIndex,Info){//遍历json里的数据  
					strHTML += "<option value="+Info+">"; 
					if(Info=="00"){
						str="换乘线";
					}else if(Info.indexOf('0')==0){
						str=Info.substr(1,1)+"号线";
					}else{
						str=Info+"号线";
					}
					strHTML += str;
					strHTML += "</option>";  
				});  
				$("#selectmenu").html(strHTML);//显示到tbody中
				$("#selectmenu").selectmenu("refresh");
			}); 
		
		$("#selectmenu").on("selectmenuchange",function( event, ui){
			var linenum=$("#selectmenu").val();
			// alert(linenum);
			if(linenum!="请选择"){
				if(linenum.indexOf("0")==0){
					str=linenum.substr(1,1);
				}else{
					str=linenum;
				}
				$("#lineNum1").html(str);
				$("#lineNum2").html(str);
			}
			
			$.getJSON("../../dams/elecfence/stationsbyline/"+linenum, { "resultType": "json" }, function(data, textStatus)
				{
				   $("#selectmenu2").empty();//先清空tbody  
					var strHTML2 = "<option>请选择</option>";  
					$.each(data,function(InfoIndex,Info){//遍历json里的数据  
						strHTML2 += "<option value="+InfoIndex+">";  
						strHTML2 += Info;
						strHTML2 += "</option>";  
					});  
					$("#selectmenu2").html(strHTML2);//显示到tbody中
					$("#selectmenu2").selectmenu("refresh");
				}); 
		});
		
		$("#selectmenu2").on("selectmenuchange",function( event, ui){
			var stat=$("#selectmenu2").val();
			$.getJSON("../../dams/elecfence/station/"+stat, { "resultType": "json" }, function(data, textStatus)
				{
					if(stat!="请选择"){
						$("#sname").html(data.name);
						$("#sename").html(data.ename);
					}
				}); 
			
			changeStationImgData(stat);
		});
	    
	    
	    
	    $("#divProgressbar").progressbar({value: 30});
	    $("#divProgressbar1").progressbar({value: 40});
	    $("#divProgressbar2").progressbar({value: 30});
	    $("#divProgressbar3").progressbar({value: 50});
	    $("#divProgressbar4").progressbar({value: 60});
	   	$("#divProgressbar5").progressbar({value: 70});
	   	$("#divProgressbar6").progressbar({value: 80});
	    $("#divProgressbar7").progressbar({value: 80});
	    $("#divProgressbar8").progressbar({value: 90});
});

function clearallinfo(){
	str="";
	$("#lineNum1").html(str);
	$("#lineNum2").html(str);
	$("#sname").html(str);
	$("#sename").html(str);
}

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

<style typoe="text/css">
#backImg{
 /**background: url(${APP_PATH}style/images/grey_wash_wall.png) */
 background:#1B1B1B ;
}
.ui-widget {
	font-family: '微软雅黑'; 
	font-size: 13px;
}
</style>
<body id="backImg" scroll="no" style="overflow-x:hidden;" onmousemove="show_coords(event)">
<div id="top" style="height:130px;width:100%; text-align: center;color: #FFF; font-size: 36px;">
	<div style="padding-top: 10px;">
		
		<!-- 
		<div style="position:relative;">
		    
		    <div style="position:absolute;z-indent:2;left:0;top:0;">
		        2
		    </div>
		     -->
		
		    <img alt="" src="${APP_PATH}style/images/title.png" style="width: 800px; height: 120px;">
			<div style="position:absolute;z-indent:2;left:310px;top:58px;"><span id="lineNum1" style="background:green;font-size:40px"></span></div>
			<div style="position:absolute;z-indent:2;left:377px;top:86px;"><span id="lineNum2" style="font-size:5px"></span></div>
			<div style="position:absolute;z-indent:2;left:425px;top:59px;"><span id="sname" style="font-size:20px"></span></div>
			<div style="position:absolute;z-indent:2;left:445px;top:86px;"><span id="sename" style="font-size:10px;"></span></div>
			<div style="position:absolute;z-indent:2;left:720px;top:56px;"><span id="inout" style="font-size:20px;color:red;font:bold">进站</span></div>
			<div style="position:absolute;z-indent:2;left:625px;top:82px;"><span id="ppic" style="font-size:20px;color:red;font:bold">
				<img src="${APP_PATH}style/images/OrangePerson.png" style="width:100px;height:20px" />
 			</span></div>
		
		<div style="float: right;padding-right: 50px;">
				<div id="clock" class="light">
					<div class="display">
						<div class="date"></div>
						<div class="digits"></div>
						<div style="color: #fff;font-size: 14px; margin-top: 10px;">
						   数据更新时间：23:45:00
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
<div id="main" style="height:400px;width:80%; float: left; background-color: #1b1b1b;">
</div>
<div id="right" style="float: left; width: 20%; height:100%;margin-top: 60px; color: #FFF; background-color: #1B1B1B">
		<div style="padding: 5px; width: 100%">
			<a id="quanshi" style="font-family: '微软雅黑'; font-size: 13px; width: 98px;" >全市统计</a>
		</div>
		
		<div style="width: 100%">
			<div style="padding: 5px;float: left;">
					<select id="selectmenu">
						<option>请选择</option>
					</select>
			</div>
			<div style="padding: 5px;float: left;">
				 <select id="selectmenu2">
					<option>请选择</option>
				</select>
			</div>
		</div>
		
		<!-- 
		<div style="width: 100%">
			<div style="padding: 5px; font-family: '微软雅黑'; font-size: 13px; float: left;">
					<select id="xianlu"  style="width: 100px;font-family: '微软雅黑'; font-size: 13px;">   
					   <option style="font-family: '微软雅黑'; font-size: 13px;">1号线</option>   
					   <option style="font-family: '微软雅黑'; font-size: 13px;">2号线</option>   
					   <option style="font-family: '微软雅黑'; font-size: 13px;">15号线</option>   
				   	</select>
			</div>
			<div style="padding: 5px; font-family:'微软雅黑'; font-size: 13px; float: left;">
				 <select id="zhandian" style="width: 130px;">
					<option value="zhonshanpark">中山公园</option>
					<option value="yuyuan">豫园</option>
					<option value="shilong">石龙路</option>
					<option value="hongqiaostation">虹桥火车站</option>
				</select>
			</div>
		</div>
		 -->
		
		<!-- 进站人数 -->
		<div style="width: 100% ;margin-top: 30px;">
			<div style="width: 100% ; padding-top: 30px;">
      			<div class="title" id="titilein">
      			</div>
      			
      			<div style="float: left;">
      				<div class="ren">
      				 
      				</div>
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      				
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar1" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      			</div>
      			<div class="number">
      			  <div style="height: 22px; ">
      			  </div>
      			  <div style="height:20px;">
      			  <label>150(150)</label>
      			  </div>
      			</div>
      		</div>
      		
      		<!--进站总人数  -->
      		<div style="width: 100%; margin-top: 30px; ">
      			<div class="title" id="titileintotal">
      			</div>
      			<div style="float: left;">
      				<div class="yeren">
      				 
      				</div>
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar3" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      				
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar4" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      			</div>
      			<div class="number">
      			  <div style="height: 22px; ">
      			  </div>
      			  <div style="height:20px;">
      			  <label>250(255)</label>
      			  </div>
      			</div>
      		</div>
      		
      		
      		<!--早高峰进站总人数  -->
      		<div style="width: 100%; margin-top: 30px; ">
      			<div class="title" id="mtitileintotal">
      			</div>
      			<div style="float: left;">
      				<div class="orren">
      				 
      				</div>
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar5" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      				
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar6" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      			</div>
      			<div class="number">
      			  <div style="height: 22px; ">
      			  </div>
      			  <div style="height:20px;">
      			  <label>350(360)</label>
      			  </div>
      			</div>
      		</div>
      		
      		<!--晚高峰进站总人数  -->
      		<div style="width: 100%; margin-top: 30px; ">
      			<div class="title" id="ntitileintotal">
      			</div>
      			<div style="float: left;">
      				<div class="reren">
      				 
      				</div>
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar7" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      				
      				<div style="height: 10px; line-height: 30px;">
	      				<div id="divProgressbar8" style="width: 70px; height: 5px;">
	      				</div>
      				</div>
      			</div>
      			<div class="number">
      			  <div style="height: 22px; ">
      			  </div>
      			  <div style="height:20px;">
      			  <label>450(463)</label>
      			  </div>
      			</div>
      		</div>
      		
      		
		</div>
		<p id="coords"></p>
		
</div>
<script type="text/javascript">

Highcharts.setOptions({  
	 global: { useUTC: false },
    colors: ["#FF0033","#55BF3B", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
     		"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"]  
}); 


//左侧图表
 var myDate= new Date(2015,5,26);
 var start_time =  myDate.getTime();
 
 //var dtime = new Date().getTime();
 var dtime = start_time;
 var url = "${APP_PATH}dams/elecfence/dayenterdata";
 var station = "0241";
 
 var main = jQuery("#main");
 
 var hcharts = null;

 
 $(function () {
	  
	  jQuery.post(url,{dateTime:dtime,sid:station},function(data){
			 
		  showimg(data.in,data.in);
		 })
		 
	});	
 
 //计时器   动态加载并 切换 图标的 数据  的时间间隔
 function loadImgData(){
	  
	  setInterval('getImgData();', 10000); 
 }
 
 /**
 *   切换站点图标     
 *  @param sid   站点id
 */
 function changeStationImgData(sid){
	 
	 station = sid;
	// getData();
	
	 //获取当前时间
	  //dtime = new Date().getTime();
	  jQuery.post(url,{dateTime:dtime,sid:station},function(data){
		  
		  hcharts.series[0].setData(data.in) ;
		  hcharts.series[1].setData(data.in) ;
	  })
	 
 }
 
 //动态加载并 切换 图标的 数据 
 function getImgData(){
	 
	  //获取当前时间
	  //dtime = new Date().getTime();
	  jQuery.post(url,{dateTime:dtime,sid:station},function(data){
		 
		  hcharts.series[0].setData(data.in) ;
		 // hcharts.series[1].setData(data.out) ;
	  })
	  
	}
 
 
 // 使用  highcharts 画图
 function showimg(ind,out){
		  
		 hcharts = new Highcharts.Chart({ 
			  
	          chart: { 
	              renderTo: 'main', //图表放置的容器，DIV 
	              defaultSeriesType: 'spline', //图表类型line(折线图), 
	              zoomType: 'x',  //x轴方向可以缩放 
	              events : {
	                  load : loadImgData // 定时器
	                 },
				 backgroundColor:"#1b1b1b"
	            
	          }, 
	       
	        //去除右下角的logo水印
	        credits: {  
	            enabled:false  
	 		 }, 
	 		 
	        title: {
	            text: ''
	        },
	      /*  subtitle: {
	            text: 'Irregular time data in Highcharts JS'
	        },*/
	        tooltip: {  
            formatter: function () {  
                return  Highcharts.dateFormat('%H:%M', this.x) + '<br/>' +  
                '<b>' + this.series.name  + '</b> : ' + this.y + " 人 ";  
            }  
        },  
	        
	        xAxis: {
	            type: 'datetime',
	            lineWidth: 2,
	            dateTimeLabelFormats:
                {
	            	day :'%m月%e日',
	            	hour: '%H:%M'
                }
	        },
	        yAxis: {
		          title: {
		              text: '进站人数'
		          },
		          lineWidth: 2,
		          min: 0,
		          minorGridLineWidth: 0,
		          gridLineWidth: 0,
		          alternateGridColor: null,
		          plotBands: [{ // Light air
		              from: 0,
		              to: 200,
		              color: 'rgba(19, 180, 38,0.1)',
		              label: {
		                  text: '',
		                  style: {
		                      color: '#606060'
		                  }
		              }
		          }, { // Light breeze
		              from: 200,
		              to: 400,
		              color: 'rgba(19, 180, 38, 0.1)',
		              label: {
		                  text: '通畅',
		                  style: {
		                      color: '#606060'
		                  }
		              }
		          }, { // Gentle breeze
		              from: 400,
		              to: 600,
		              color: 'rgba(185, 174,14, 0.1)',
		              label: {
		                  text: '正常',
		                  style: {
		                      color: '#606060'
		                  }
		              }
		          }, { // Moderate breeze
		              from: 600,
		              to: 800,
		              color: 'rgba(186, 82,13, 0.1)',
		              label: {
		                  text: '拥挤',
		                  style: {
		                      color: '#606060'
		                  }
		              }
		          }, { // Fresh breeze
		              from: 800,
		              to: 1000,
		              color: 'rgba(255, 0, 0, 0.1)',
		              label: {
		                  text: '爆满',
		                  style: {
		                      color: '#606060'
		                  }
		              }
		          }, { // Strong breeze
		              from: 1000,
		              to: 1200,
		              color: 'rgba(255, 0, 0, 0.1)',
		              label: {
		                  text: '',
		                  style: {
		                      color: '#606060'
		                  }
		              }
		          }, { // High wind
		              from: 1200,
		              to: 100000,
		              color: 'rgba(255, 0, 0, 0.1)',
		              label: {
		                  text: '',
		                  style: {
		                      color: '#606060'
		                  }
		              }
		          }]
		      },
		      
	        
	        plotOptions: {
	        	line:{ 
	                  dataLabels:{ 
	                      enabled:true  //在数据点上显示对应的数据值 
	                  }, 
	                  enableMouseTracking: false //取消鼠标滑向触发提示框 
	              },
		          spline: {
		              //lineWidth: 4,
		              states: {
		                  hover: {
		                      lineWidth: 4
		                  }
		              },
		              marker: {
		                  enabled: false
		              },
		              pointInterval: 3600000, // one hour
		              pointStart   : start_time
		          }
		      },
	        
	        series:[
	        	{name:"历史",data:ind},
	        	{name:"进站",data:out}
	        ]
	    });
	  
	  } 
 

</script>
</body>
</html>