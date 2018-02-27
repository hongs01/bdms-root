var map;

function test2(){
$.ajax({
url:"/bdms-web/hbase/data",
type:'POST',
dataType:'json',
timeout:2000,
cache:false,
error:function(resultData){
	//alert("xml-gg");
},

success:function(resultData){ 
	//alert(resultData);
	var map = new BMap.Map("container");          // 创建地图实例
    var point = new BMap.Point(121.4475, 31.1583);
    map.centerAndZoom(point, 9);             // 初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom(); // 允许滚轮缩放
    if(!isSupportCanvas()){
    	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
    }
		//详细的参数,可以查看heatmap.js的文档 https://github.com/pa7/heatmap.js/blob/master/README.md
		//参数说明如下:
		/* visible 热力图是否显示,默认为true
	     * opacity 热力的透明度,1-100
	     * radius 势力图的每个点的半径大小   
	     * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
	     *	{
				.2:'rgb(0, 255, 255)',
				.5:'rgb(0, 110, 255)',
				.8:'rgb(100, 0, 255)'
			}
			其中 key 表示插值的位置, 0~1. 
			    value 为颜色值. 
	     */
	//	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
		//map.addOverlay(heatmapOverlay);
	//	heatmapOverlay.setDataSet({data:resultData,max:100});
    window.map=map;
	heatmap(resultData,window.map);
     
}
});
}

function updata(){
	$.ajax({
	url:"/bdms-web/hbase/data2",
	type:'POST',
	dataType:'json ',
	timeout:2000,
	cache:false,
	error:function(resultData){
		//alert("xml--mm");
	},

	success:function(resultData){ 
		//alert("happy");
		openHeatmap(resultData);
	     
	}
	});
	}

function heatmap(resultData,map){
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":80});
	map.addOverlay(heatmapOverlay);
	heatmapOverlay.setDataSet({data:resultData,max:100});
}

	//是否显示热力图
    function openHeatmap(resultData){
    	heatmapOverlay.setDataSet({data:resultData,max:100});
        heatmapOverlay.show();
    }
	function closeHeatmap(){
        heatmapOverlay.hide();
    }
	closeHeatmap();
    function setGradient(){
     	/*格式如下所示:
		{
	  		0:'rgb(102, 255, 0)',
	 	 	.5:'rgb(255, 170, 0)',
		  	1:'rgb(255, 0, 0)'
		}*/
     	var gradient = {};
     	var colors = document.querySelectorAll("input[type='color']");
     	colors = [].slice.call(colors,0);
     	colors.forEach(function(ele){
			gradient[ele.getAttribute("data-key")] = ele.value; 
     	});
        heatmapOverlay.setOptions({"gradient":gradient});
    }
	//判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }
    
    
  /*  function t()
    {
    	alert("hello");
    }

    $(document).ready(function(){  
     setInterval(t, 10000);  
    }); */

    



