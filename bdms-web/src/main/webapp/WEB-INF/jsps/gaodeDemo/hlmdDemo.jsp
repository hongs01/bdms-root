<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
      html,body,.map{
        width: 100%;
        height: 100%;
        margin: 0px;
      }
</style>
<title>海量麻点图</title>
</head>
<body onload>
    <div id="mapDiv" class="map" tabindex="0"></div>
    <script type="text/javascript" src = 'http://cache.amap.com/lbs/static/allCity.js'></script> 
    <script type="text/javascript" src = 'http://webapi.amap.com/maps?v=1.3&key=3f41b5a0fc7c6d0d9952ebfa549f2323'></script>
    <script type="text/javascript">
    
   /*  $(function () {
    	alert(111);
    	//showMap()
    	}); */
    
      var map = new AMap.Map('mapDiv', {
        layers: [new AMap.TileLayer({
          textIndex: 2
        })],
        zoom: 4,
        center: [102.342785, 35.312316]
      });
      var mass = new AMap.MassMarks(citys, {
          url: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png',
          anchor: new AMap.Pixel(3, 7),
          size: new AMap.Size(5, 7),
          opacity:0.7,
          cursor:'pointer',
          zIndex: 1
      });
      var marker = new AMap.Marker({
        content:' ',
        map:map
      })
      mass.on('mouseover',function(e){
        marker.setPosition(e.data.lnglat);
        marker.setLabel({content:e.data.name})
      })
      mass.setMap(map);
    </script>

  </body>
</html>