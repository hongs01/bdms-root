<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<jsp:include page="damscommonheader.jsp"></jsp:include>
<title>mac桌面显示</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
		html, body{ margin:0; padding:0; background: url(${APP_PATH}style/images/grey_wash_wall.png)}
		.row label
		{ 
			color: #999;
			padding: 0;
			line-height: 30px;
			font-size: 13px;
			font-weight: normal;	
		}
	</style>
 <script type="text/javascript">
      $(document).ready(function() {
        $('#wrapper1').dockmenu({
          buttons: [{
            'title': 'Settings',
            'href': '#settings',
            'imgURL': '${APP_PATH}style/images/Settings.png',
            'onClick': function(){
                              alert('You clicked on the Settings icon');
                            }
          },{
            'title': 'App Store',
            'href': '#AppStore',
            'imgURL': '${APP_PATH}style/images/AppStore.png',
        
          },{
            'title': 'Camera',
            'href': '#camera',
            'imgURL': '${APP_PATH}style/images/Camera.png',
        
          },{
            'title': 'Games',
            'href': '#Games',
            'imgURL': '${APP_PATH}style/images/Games.png',
        
          },{
            'title': 'Mail',
            'href': '#Mail',
            'imgURL': '${APP_PATH}style/images/Mail.png',
        
          },{
            'title': 'Music',
            'href': '#Music',
            'imgURL': '${APP_PATH}style/images/Music.png',
			'onClick': function(){
                              alert('You clicked on the Music icon');
                            }
          },{
            'title': 'Safari',
            'href': '#Safari',
            'imgURL': '${APP_PATH}style/images/Safari.png',
        
          },{
            'title': 'Photos',
            'href': '#Photos',
            'imgURL': '${APP_PATH}style/images/Photos.png',
        
          }]
      
        });
		
      });
	  
	  function recreate()
	  {
		var showBoard = $('#showBoard').val();
		
		$('#wrapper1').empty();
  		$('#wrapper1').toggle();
  		//$('#wrapper1').show();
		$('#wrapper1').dockmenu({
			showBoard:  $('#showBoard').is(':checked'),
          buttons: [{
            'title': 'Settings',
            'href': '#settings',
            'imgURL': '${APP_PATH}style/images/Settings.png'
          },{
            'title': 'App Store',
            'href': '#AppStore',
            'imgURL': '${APP_PATH}style/images/AppStore.png',
        
          },{
            'title': 'Camera',
            'href': '#camera',
            'imgURL': '${APP_PATH}style/images/Camera.png',
        
          },{
            'title': 'Games',
            'href': '#Games',
            'imgURL': '${APP_PATH}style/images/Games.png',
        
          },{
            'title': 'Mail',
            'href': '#Mail',
            'imgURL': '${APP_PATH}style/images/Mail.png',
        
          },{
            'title': 'Music',
            'href': '#Music',
            'imgURL': '${APP_PATH}style/images/Music.png',
			'onClick': function(){
                              alert('You clicked on the Music icon');
                            }
          },{
            'title': 'Safari',
            'href': '#Safari',
            'imgURL': '${APP_PATH}style/images/Safari.png',
        
          },{
            'title': 'Photos',
            'href': '#Photos',
            'imgURL': '${APP_PATH}style/images/Photos.png',
        
          }]
      
        });
	  }
    </script>
</head>

<body>
		<div class="row"> <label>显示桌面:</label> <input type="checkbox" checked="checked" id="showBoard"/></div>
		
		<button onClick="recreate(); return true;">显示/隐藏</button>
	
	    <div id="wrapper1"></div><!-- Dockmenu build wrapper1 -->
</body>
</html>