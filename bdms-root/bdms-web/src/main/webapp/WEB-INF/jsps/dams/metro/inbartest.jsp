<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海轨道交通实时监控</title>
<jsp:include page="../header/metroHeader.jsp"></jsp:include>

<script type="text/javascript" src="${APP_PATH}js/highcharts/highcharts-3d.js"></script>
 

<script type="text/javascript">
$(function(){
    var myDate = new Date();
    myDate.getHours();      /* 获取当前小时数(0-23) */
    myDate.getMinutes();    /* 获取当前分钟数(0-59) */
    myDate.getSeconds();    /* 获取当前秒数(0-59) */
    myDate.toLocaleDateString();    /* 获取当前日期 */
    var mytime=myDate.toLocaleTimeString();   /*  获取当前时间 */
    myDate.toLocaleString( );       /* 获取日期与时间 */

});
</script>

<style type="text/css">

	/**
	 *	Basic Layout Theme
	 * 
	 *	This theme uses the default layout class-names for all classes
	 *	Add any 'custom class-names', from options: paneClass, resizerClass, togglerClass
	 */
	.ui-layout-pane { /* all 'panes' */ 
		border: 0px solid #BBB; 
	} 
	.ui-layout-resizer { /* all 'resizer-bars' */ 
		background: grey; 
	} 
	</style>
 
<script type="text/javascript">

	
	$(function() {
		
		//alert(getGFtimesAM("08:35:00"));
		
		//layout
		layoutSettings_Outer.east.initClosed=true;
		layoutSettings_Outer.east.size=400;
		myLayout.sizePane("east",400);
		//myLayout = $("body").layout(layoutSettings_Outer);
		
		
		//button and select
		$("#quanshi").button();
		//$("#shishi").button();
		$("#lishi").button();
		
		$("#allday").button();
		$("#lasthour").button();
		
		
	    $("#selectmenu").selectmenu({width: 100});
		$("#selectmenu2").selectmenu({width: 150});
		
		var sid="${sid}";
		var lineIdParam=sid.substr(0,2);
		$.getJSON("${APP_PATH}dams/metro/in/lines", { "resultType": "json" }, function(data, textStatus)
				{
				   $("#selectmenu").empty();//先清空tbody  
					var strHTML = "<option value='-'>请选择</option>"; 
					
					var lineNoCo=new Array('#FFFFFF','#e91b39','#8ac53f','#fad107','#502e8d','#9056a3','#d61870','#f37121','#009eda','#79c8ed','#bca8d1','#7e2131','#007c65','#e895c0','#e895c0','#e895c0','#8dd1ba');
					$.each(data,function(InfoIndex,Info){//遍历json里的数据  
						if(Info==lineIdParam){
							strHTML += "<option value="+Info+" selected='selected'>"; 
						}else{
							strHTML += "<option value="+Info+">"; 
						}
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
					//$("#selectmenu").selectmenu( "menuWidget" ).addClass( "smoverflow" );
					$("#selectmenu").selectmenu("refresh");
					
					$("#selectmenu2").empty();//先清空tbody  
					if(sid==""||sid.length!=4){
						strHTML = "<option value='-'>请选择</option>";  
						$("#selectmenu2").html(strHTML);//显示到tbody中
						$("#selectmenu2").selectmenu("refresh");
					}else{	
						$.getJSON("${APP_PATH}dams/metro/in/stationsbyline/"+lineIdParam, { "resultType": "json" }, function(data, textStatus)
								{
								   $("#selectmenu2").empty();//先清空tbody  
									    strHTML2 = "<option value='-'>请选择</option>";  
									$.each(data,function(InfoIndex,Info){//遍历json里的数据  
										if(InfoIndex==sid){
											strHTML2 += "<option value="+InfoIndex+" selected='selected'>";  
											sName=Info;
										}else{
											strHTML2 += "<option value="+InfoIndex+">";  
										}
										strHTML2 += Info;
										strHTML2 += "</option>";  
									});  
									$("#selectmenu2").html(strHTML2);//显示到tbody中
									
									if(data!=''){
										$("#selectmenu2").selectmenu( "menuWidget").addClass("smoverflow");
									}else{
										$("#selectmenu2").selectmenu( "menuWidget").deleteClass("smoverflow");
									}
									$("#selectmenu2").selectmenu("refresh");
									$("#lineStatInfo").html("<span style='color:"+lineNoCo[parseInt(lineIdParam)]+"'>"+lineIdParam+"</span><span style='color:black'> 号线 </span><span style='color:"+lineNoCo[parseInt(lineIdParam)]+"'>"+sName+"</span><span style='color:black'> 进站人数</span>");
								});
					}
					
					/* linenum=$("#selectmenu").val();
					if(linenum.indexOf("0")==0){
						str=linenum.substr(1,1);
					}else{
						str=linenum;
					} */
			}); 
		
		
			
			$("#selectmenu").on("selectmenuchange",function( event, ui){
				var lineNoCo=new Array('#FFFFFF','#e91b39','#8ac53f','#fad107','#502e8d','#9056a3','#d61870','#f37121','#009eda','#79c8ed','#bca8d1','#7e2131','#007c65','#e895c0','#e895c0','#e895c0','#8dd1ba');
				var linenum=$("#selectmenu").val();
				if(linenum!="-"){
					if(linenum.indexOf("0")==0){
						str=linenum.substr(1,1);
					}else{
						str=linenum;
					}
					if(linenum == "00"){
						//clearallinfo();
						//changeTitleBg("line_hc");
						$("#lineStatInfo").html("<span style='color:black'>换乘线 进站人数  </span>");
						$('#lineStatInfo').css("display","none");
						$('#lineStatInfo').fadeIn(200);
						
						changeStationImgData("0098");
					}else{
						//changeTitleBg("line");
						//changeLine(str);
						$('#lineStatInfo').css("color",lineNoCo[parseInt(str)]);	
						
						
						
						$("#lineStatInfo").html("<span style='height:50px;color;"+lineNoCo[parseInt(str)]+"'>"+str+"</span><span style='color:black'> 号线 进站人数 </span>");
						$('#lineStatInfo').css("display","none");
						$('#lineStatInfo').fadeIn(200);
						
						changeStationImgData(linenum+"00");
					}	
				}else{
					//clearallinfo();
					//changeTitleBg("top");
					$("#lineStatInfo").html("<span style='color:black'>全市 进站人数</span>");
					$('#lineStatInfo').css("display","none");
					$('#lineStatInfo').fadeIn(200);
					
					changeStationImgData("0099");
				}
				$.getJSON("${APP_PATH}dams/metro/in/stationsbyline/"+linenum, { "resultType": "json" }, function(data, textStatus)
						{
						   $("#selectmenu2").empty();//先清空tbody  
							    strHTML2 = "<option value='-'>请选择</option>";  
							$.each(data,function(InfoIndex,Info){//遍历json里的数据  
								strHTML2 += "<option value="+InfoIndex+">";  
								strHTML2 += Info;
								strHTML2 += "</option>";  
							});  
							$("#selectmenu2").html(strHTML2);//显示到tbody中
							
							if(data!=''){
								$("#selectmenu2").selectmenu( "menuWidget").addClass("smoverflow");
							}else{
								$("#selectmenu2").selectmenu( "menuWidget").deleteClass("smoverflow");
							}
							$("#selectmenu2").selectmenu("refresh");
						});
				//$("#lineStatInfo").html(linenum);
			});
			
			$("#selectmenu2").on("selectmenuchange",function( event, ui){
				var lineNoCo=new Array('#FFFFFF','#e91b39','#8ac53f','#fad107','#502e8d','#9056a3','#d61870','#f37121','#009eda','#79c8ed','#bca8d1','#7e2131','#007c65','#e895c0','#e895c0','#e895c0','#8dd1ba');
				linenum=$("#selectmenu").val();
				if(linenum.indexOf("0")==0){
					str=linenum.substr(1,1);
				}else{
					str=linenum;
				}
				stat=$("#selectmenu2").val();
				$.getJSON("${APP_PATH}dams/metro/in/station/"+stat, { "resultType": "json" }, function(data, textStatus)
					{
						if(stat!="-"){
							if(linenum!="00"){
								$("#lineStatInfo").html("<span style='color:"+lineNoCo[parseInt(str)]+"'>"+str+"</span><span style='color:black'> 号线 </span><span style='color:"+lineNoCo[parseInt(str)]+"'>"+data.name+"</span><span style='color:black'> 进站人数</span>");
							}else{
								$("#lineStatInfo").html("</span><span style='color:white'>换乘线  </span><span style='color:"+lineNoCo[parseInt(str)]+"'>"+data.name+"</span><span style='color:black'> 进站人数</span>");
							}
							//changeStationLogo(data);
							changeStationImgData(stat);
						}else{
							if(linenum!="00"){
								$("#lineStatInfo").html("<span style='color:"+lineNoCo[parseInt(str)]+"'>"+str+"</span><span style='color:black'> 号线 </span><span style='color:black'> 进站人数</span>");
							}else{
								$("#lineStatInfo").html("</span><span style='color:white'>换乘线  </span><span style='color:black'> 进站人数</span>");
							}
							changeStationImgData(linenum+"00");
						}
					});
			});
			
			$( "#quanshi" ).on("click",function(event,ui){
				
				
				
				//$("#main").css("width","1400");
				//refreshPNums(111001,1400000,440040,111011,1,2,3,4);
				//alert($("#selectmenu").val())
				$.getJSON("${APP_PATH}dams/metro/in/lines", { "resultType": "json" }, function(data, textStatus)
						{
						   $("#selectmenu").empty();//先清空tbody  
							var strHTML = "<option value='-'>请选择</option>";  
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
							//$("#selectmenu").selectmenu( "menuWidget" ).addClass( "smoverflow" );
							$("#selectmenu").selectmenu("refresh");
						}); 
				
				strHTML3 = "<option value='-'>请选择</option>";
				$("#selectmenu2").empty();
				$("#selectmenu2").html(strHTML3);
				$("#selectmenu2").selectmenu("refresh");
				//clearallinfo();
				//changeTitleBg("top");
				changeStationImgData("0099"); 
				$("#lineStatInfo").html("<span style='color:black'>全市 进站人数</span>");
			});
			
			
			
			$( "#allday" ).on("click",function(event,ui){
				//alert("allday");
				linenum=$("#selectmenu").val();
				if(linenum!="-"){
					stat=$("#selectmenu2").val();
					if(stat!="-"){
						changeStationImgData(stat,"allday");
					}else{
						changeStationImgData(linenum+"00","allday");
					}
				}else{
					changeStationImgData("0099","allday");
				}
			});
			
			
			
			
			$( "#lasthour" ).on("click",function(event,ui){
				//alert("lasthour");
				linenum=$("#selectmenu").val();
				if(linenum!="-"){
					stat=$("#selectmenu2").val();
					if(stat!="-"){
						changeStationImgData(stat,"lasthour");
					}else{
						changeStationImgData(linenum+"00","lasthour");
					}
				}else{
					changeStationImgData("0099","lasthour");
				}
			});
			
			
			
			
			
			
			
			
			/* $( "#shishi" ).on("click",function(event,ui){
				$("#start").val("");
				var stat=$("#selectmenu").val();
	      		var stat2=$("#selectmenu2").val();
	      		  if(stat2!="-"){
	      			  changeStationImgData(stat2);
	      		  }else if(stat!="-"){
	      			  changeStationImgData(stat+"00");
	      		  }else{
	      			  changeStationImgData("0099");
	      		  }
			}); */
			
			$( ".ui-layout-toggler" ).on("click",function(event,ui){
				
				changeStationImgData(station);
				len=$("#main").css("width");
				len=len.replace("px","");
				//alert(len);
					//alert(len.substring(0));
					//alert($("#main").css("width">1100));
					if(parseInt(len)>1100){
						//myLayout.sizePane("east",400);
						//myLayout = $("body").layout(layoutSettings_Outer);
						$( ".ui-layout-toggler" ).css("background-image","url('"+"${APP_PATH}style/images/leftarrow.png"+"')");
					}else{
						//layoutSettings_Outer.east.initClosed=true;
						//
						$( ".ui-layout-toggler" ).css("background-image","url('"+"${APP_PATH}style/images/rightarrow.png"+"')");
					}
			});
			
			
			//datepicker
			// 获取调用控件的对象
			
			
		    var dates = $("#start");
		    var datepicker_CurrentInput;
		    var currentDate=new Date();
		    
		    //设置目标时间，因为例子中的开始时间和结束时间是有时间限制的
		    //var targetDate;
		    
		    //$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
			//dates.datepicker($.datepicker.regional['zh-CN']);
			//dates.datepicker('option', $.datepicker.regional['zh-CN']);
		    
		    dates.datepicker({
		    	//default:$.datepicker.regional['zh-CN'],
		    	//option:$.datepicker.regional['zh-CN'],
		        //showButtonPanel:true,
		        defaultDate:currentDate,
		        dateFormat:"yy-mm-dd",
		        changeYear: true,
		        changeMonth: true,
		        //closeText: '清除',
		        //beforeShow: function (input, inst) { datepicker_CurrentInput = input; },
		        //numberOfMonths: 3,
		        //当选择时间的时候触发此事件
		        onSelect: function(selectedDate){  
		          if(this.id == "start"){
		        	  var stat=$("#selectmenu").val();
	        		  var stat2=$("#selectmenu2").val();
	        		  if(stat2!="-"){
	        			  changeStationImgData(stat2);
	        		  }else if(stat!="-"){
	        			  changeStationImgData(stat+"00");
	        		  }else{
	        			  changeStationImgData("0099");
	        		  }
		        	  
		            // 如果是选择了开始时间
		            //option = "minDate";
		            //getTimeByDateStr 这个方法的代码下面会贴出来的，就是处理时间的代码
		            //targetDate = new Date();
		          }
		          //设置时间框中时间，比如根据选择的开始时间，限制结束时间的不可选项,dates.not(this)是js选择器使用，
		          //datepicker("option", option, targetDate),这个就是日期控件封装的api了
		          //dates.not(this).datepicker("option", option, targetDate);  
		        }
		    });
		    dates.val(currentDate.Format("yyyy-MM-dd"));
			
		    /* $(".ui-datepicker-close").live("click", function ()  
		    	     {  
		    	         dates.val("");
		    	     }); */
			
	});
	
</script>

<script type="text/javascript">

/* 在界面上显示鼠标所在的位置 */
function show_coords(event){
	 var x = event.clientX;
	 var y = event.clientY;
	 var say = document.all("coords");
	 say.innerHTML = "X:"+x+" Y:"+y;
	 say.style.position = "absolute";
	 say.style.left = x + 30;
	 say.style.top = y;
}

function refreshPNums(num1,num2,num3,num4,num12,num22,num32,num42,pSize){
	refreshPNum("#ren",num1,num12,1);
	refreshPNum("#totalren",num2,num22,pSize);
	refreshPNum("#totalrenAM",num3,num32,getGFtimesAM($("#updatetime").text()));
	refreshPNum("#totalrenPM",num4,num42,getGFtimesPM($("#updatetime").text()));
}

 function refreshPNum(id,num,numh,pSize){
	 
	 //$(id).css("background-image","");
	 //$((id+"pb")).css("display","none");
	 //$((id+"pbh")).css("display","none");
	 //$((id+"num")).html("");
	 //$((id+"numh")).html("");
	 //$((id+"wait")).css("background-image","url('"+"${APP_PATH}style/images/waiting.gif"+"')"); 
	 //$((id+"wait")).css("background-repeat","no-repeat"); 
	 
	 var stat=$("#selectmenu").val();
	 var stat2=$("#selectmenu2").val();
	 var codeStr="";
	 var typeStr="";
	 if(stat2!="-"){
	 	codeStr=stat2;
	 	typeStr="stationEnterRT";
	 }else if(stat!="-"){
		 if(stat="00"){
			 codeStr="0098";
			 typeStr="transLineEnterRT";
		 }else{
			 codeStr=stat; 
			 typeStr="lineEnterRT"; 
		 }
	 }else{
		 codeStr="0099";
		 typeStr="allEnterRT";
	 }
	 //alert(codeStr+"--"+typeStr);
	 
	 $.getJSON("${APP_PATH}dams/metro/in/criterion", { code:codeStr,type:typeStr,"resultType": "json" }, function(data, textStatus)
		{	
		 //alert(id+"---"+pSize);
			 if(pSize==0){
				 pSize=1;
			 }
			level=data.level.split(",");
			//alert(id+"---"+pSize+": "+num+":"+level[0]+"--"+level[1]+"--"+level[2]);
			//$((id+"wait")).css("background-image","");
			//$((id+"pb")).css("display","block");
			//$((id+"pbh")).css("display","block");
			if(num==0){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/grren.png"+"')");
			}else if(num<parseInt(level[0])*pSize){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/luren.png"+"')");
			}else if(num<parseInt(level[1])*pSize){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/yeren.png"+"')");
			}else if(num<parseInt(level[2])*pSize){
				$(id).css("background-image","url('"+"${APP_PATH}style/images/orren.png"+"')");
			}else{
				$(id).css("background-image","url('"+"${APP_PATH}style/images/reren.png"+"')");
			}
			if(num<0){
				percent=0;
			}else{
				percent=Math.ceil(num/parseInt(level[2])*100);
			}
			if(numh<0){
				percenth=0;
			}else{
				percenth=Math.ceil(numh/parseInt(level[2])*100);
			}
			
			/* $((id+"pb")).progressbar({value: percent});
			$((id+"pb")).find( ".ui-progressbar-value" ).css({
		          "background": '#55BF3B'
	        });
			$((id+"pbh")).progressbar({value: percenth});
			$((id+"pbh")).find( ".ui-progressbar-value" ).css({
		          "background": '#FF0033'
	        }); */
			$((id+"num")).html("<span style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:left;font-weight:bold;width:280px;color:black;font-family: '微软雅黑';'>"+formatNum(num)+"（"+formatNum(numh)+"）</span>");
			//$((id+"numh")).html("<span style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:left;padding-top:2px' Title="+numh+">("+numh+")</span>");
		});
 }
 function changeTitleBg(index){
	 if(index=="top"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_top.png"+"')");
	 }else if(index=="line"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_line.png"+"')");
	 }else if(index=="all"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll.png"+"')");
	 }else if(index=="line_hc"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_line_hc.png"+"')");
	 }else if(index=="all_hc"){
		 $("#metrotitle").css("background-image","url('"+"${APP_PATH}style/images/titleAll_hc.png"+"')");
	 }
 }
 
 
 function formatNum(str){ 
	 str=str+"";
		var newStr = ""; var count = 0;   
		if(str.indexOf(".")==-1){ 
			for(var i=str.length-1;i>=0;i--){  
				if(count % 3 == 0 && count != 0){    
					newStr = str.charAt(i) + "," + newStr;  
				}else{    
					newStr = str.charAt(i) + newStr;  
				}  
				count++;    
				}    
			str = newStr;  
			if(str.indexOf("-,")!=-1){
				str=str.replace("-,","-");
			}
			return str; 
		} else{ 
			for(var i = str.indexOf(".")-1;i>=0;i--){  
				if(count % 3 == 0 && count != 0){   
					newStr = str.charAt(i) + "," + newStr;  
				}else{    
					newStr = str.charAt(i) + newStr;   
					}  
				count++;    
				}    
			str = newStr + (str).substr((str + "00").indexOf("."),3);  
			if(str.indexOf("-,")!=-1){
				str=str.replace("-,","-");
			}
			return str;  
			} 
		}  
 
 function getGFtimesAM(str){
		var times=str.split(":");
		var hourNum=parseInt(times[0]);
		var minNum=parseInt(times[1]);
		//alert(parseInt(times[0])>17);
		if(hourNum==0 && minNum==0){
 			return 24;
 		}
		if(hourNum<7){
			count=0;
		}else{
			if(hourNum==7){
	 			count=0;
	 		}else if(hourNum==8){
	 			count=12;
	 		}else{
	 			count=24;
	 		}
	 		if(count!=24){
	 			//alert(Math.floor(parseInt(times[1])/5));
	 			count+=Math.floor(minNum/5);
	 		}
		} 		
		return count;
	}
	
	function getGFtimesPM(str){
		var times=str.split(":");
		var hourNum=parseInt(times[0]);
		var minNum=parseInt(times[1]);
		//alert(parseInt(times[0])>17);
		if(hourNum==0 && minNum==0){
 			return 24;
 		}
		if(hourNum<17){
			count=0;
		}else{
			if(hourNum==17){
	 			count=0;
	 		}else if(hourNum==18){
	 			count=12;
	 		}else{
	 			count=24;
	 		}
	 		if(count!=24){
	 			//alert(Math.floor(parseInt(times[1])/5));
	 			count+=Math.floor(minNum/5);
	 		}
		} 		
		return count;
	}
  	
</script>

</head>


<body>
    <!-- start header -->
     
    <!-- end   header-->
<div class="ui-layout-center" style="overflow:hidden">
	<div style="width:100%; height:100px;">
		<div id="lineStatInfo" style="color:black;"><span style='color:black；font-family: '微软雅黑';'>全市 进站人数</span></div>
	</div>
	<div style="float:right">
	
	<div style="padding: 5px;width:85px;float:left">
				<a id="allday" style="font-family: '微软雅黑';width: 80px;" >全天</a>
			</div>
	<div style="padding: 5px;width:160px;float:left">
				<a id="lasthour" style="font-family: '微软雅黑';width: 150px;" >最近一小时</a>
			</div>
	</div>
	
	<div id="main" style="height:400px;width:100%; float:left;"></div>
	
	<table>
		<tr><td>Alpha Angle</td><td><input id="R0" type="range" min="0" max="45" value="0"/> <span id="R0-value" class="value"></span></td></tr>
		<tr><td>Beta Angle</td><td><input id="R1" type="range" min="0" max="45" value="0"/> <span id="R1-value" class="value"></span></td></tr>
	</table>
	
	
	<div style="margin-left:60px">
	<div style="float:left;margin-top:4px"><img width="20px" height="20px" src="${APP_PATH}style/images/timeupdate.png"></img></div>
	<div style="width:420px; float:left;color:black;font-weight:bold;padding-left:5px;font-family: '微软雅黑';font-size:18px">数据更新时间：<span id="updatetime" style="color:black">14:00:00</span></div>
	</div>				 
</div>
 
<div class="ui-layout-east" style="background-color:#white;overflow:hidden"> 
	<!-- start right -->
	<div id="right" style="float:left;width:380px;padding-top:10px">
	
		<div style="padding: 5px;width: 100%;">
			<div style="padding: 5px;width:100%;float:left">
			<!-- <div id="shishi" style="font-family: '微软雅黑';width: 80px;" >实时数据</div> -->
			<!-- <div id="lishi" style="font-family: '微软雅黑';width: 100px;margin-left:8px" >历史日期</div> -->
			<span class="ui-button-text" style="font-family: '微软雅黑';width: 100px;margin-left:0px;font-weight:bold;font-size:13px;color:black;">日期：</span>
            <input type="text" id="start" value="" readonly="true" style="width:80px;background:#333;border:0;color:white;font-weight:bold;padding: 0.4em 1em 0.4em 1em;margin-left:3px;border-bottom-right-radius: 4px;border-bottom-left-radius: 4px;border-top-left-radius: 4px;border-top-right-radius: 4px;"/>
			
			</div>
		</div>
	
		<div style="padding: 5px;width: 100%;">
			<div style="padding: 5px;width:85px;float:left">
				<a id="quanshi" style="font-family: '微软雅黑';width: 80px;" >全市</a>
			</div>
			<div style="padding: 5px;float: left;">
					<select id="selectmenu">
						<option value="-">请选择</option>
					</select>
			</div>
			<div style="padding: 5px;float: left;">
				 <select id="selectmenu2">
					<option value="-">请选择</option>
				</select>
			</div>
		</div>
		
		<div style="width: 380px ;margin-top: 30px;font-family: '微软雅黑';">
			<!-- 进站人数 -->
			<div style="width: 100%; padding-top: 25px;float:left" >
			<div style="width:100%;BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
				<div style="float:left;margin-top:1px"><img src="${APP_PATH}style/images/round.png"></img></div>
				<div style="width:200px; float:left;color:black;font-weight:bold;padding-left:10px;font-size:18px">进站人数</div>
			</div>
			<div class="renPlusNum">
				<div style="float:left;width:100%;padding-top:10px" id="renwait">
      				<div class="ren" id="ren" style="float:left;margin-left:5px"></div>
      				<div  style="width:100%;height: 22px;font-size:14px">
      					<span id="rennum"></span>
      					<span id="rennumh"></span>
      				</div>
      			</div>
			</div>
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
			
			<!-- <div style="width: 100%; padding-top: 20px;float:left">
      			<div class="title" id="titlein" style=""></div>
      			<div style="float:left;width:70%;padding-top:10px" id="renwait">
      				<div class="ren" id="ren" style="float:left;margin-left:5px"></div>
      				<div  style="width:100%;height: 22px;font-size:14px">
      					<span id="rennum"></span>
      					<span id="rennumh"></span>
      				</div>
      			</div>
      		</div> -->
      		<!--进站总人数 -->
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px"><img src="${APP_PATH}style/images/round.png"></img></div>
				<div style="width:200px; float:left;color:black;font-weight:bold;padding-left:10px;font-size:18px">进站总人数</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenwait">
      				<div class="yeren" id="totalren" style="float:left;margin-left:5px"></div>
      				<div  style="width:100%;height: 22px;font-size:14px">
      					<span id="totalrennum"></span>
      					<span id="totalrennumh"></span>
      				</div>
      			</div>
			</div>
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
      		<!-- <div style="width: 100%; padding-top: 15px;float:left" >
      			<div class="title" id="titleintotal"></div>
      			<div style="float:left;width:65%;padding-top:10px" id="totalrenwait">
      				<div class="yeren" id="totalren" style="float:left;margin-left:5px"></div>
      				<div  style="width:100%;height: 22px;font-size:14px">
      					<span id="totalrennum"></span>
      					<span id="totalrennumh"></span>
      				</div>
      				<div id="totalrenpb" style="width: 100%; height: 5px;"></div>
      				<div id="totalrenpbh" style="width: 100%; height: 5px;"></div>
      			</div>
      		</div> -->
      		<!--早高峰进站总人数  --> 
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px"><img src="${APP_PATH}style/images/round.png"></img></div>
				<div style="width:340px; float:left;color:black;font-weight:bold;padding-left:10px;font-size:18px">早高峰进站总人数（07:00-09:00）</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenAMwait">
      				<div class="orren" id="totalrenAM" style="float:left;margin-left:5px"></div>
      				<div  style="width:100%;height: 22px;font-size:14px">
      					<span id="totalrenAMnum"></span>
      					<span id="totalrenAMnumh"></span>
      				</div>
      			</div>
			</div>  
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>   		
      		<!-- <div style="width: 100%; padding-top: 15px;float:left" >
      			<div class="title" id="mtitleintotal"></div>
      			<div style="float:left;width:65%;padding-top:10px" id="totalrenAMwait">
      				<div class="orren" id="totalrenAM" style="float:left;margin-left:5px"></div>
      				<div  style="width:100%;height: 22px;font-size:14px">
      					<span id="totalrenAMnum"></span>
      					<span id="totalrenAMnumh"></span>
      				</div>
      				<div id="totalrenAMpb" style="width: 100%; height: 5px;"></div>
      				<div id="totalrenAMpbh" style="width: 100%; height: 5px;"></div>
      			</div>
      		</div> 
      		<div class="titlets" id="timespan69" style="float:left"></div>-->
      		<!--晚高峰进站总人数  -->
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px"><img src="${APP_PATH}style/images/round.png"></img></div>
				<div style="width:340px; float:left;color:black;font-weight:bold;padding-left:10px;font-size:18px">晚高峰进站总人数（17:00-19:00）</div>
			</div>
			<div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenPMwait">
      				<div class="reren" id="totalrenPM" style="float:left;margin-left:5px"></div>
      				<div style="width:100%;height: 22px;font-size:14px">
      					<span id="totalrenPMnum"></span>
      					<span id="totalrenPMnumh"></span>
      				</div>
      			</div>
			</div> 
			<div style="width:100%;BORDER-BOTTOM: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
      		
      		
      		<!-- <div style="width: 100%; padding-top: 0px;float:left" >
      			<div class="title" id="ntitleintotal"></div>
      			<div style="float:left;width:65%;padding-top:10px" id="totalrenPMwait">
      				<div class="reren" id="totalrenPM" style="float:left;margin-left:5px"></div>
      				<div style="width:100%;height: 22px;font-size:14px">
      					<span id="totalrenPMnum"></span>
      					<span id="totalrenPMnumh"></span>
      				</div>
      				<div id="totalrenPMpb" style="width: 100%; height: 5px;"></div>
      				<div id="totalrenPMpbh" style="width: 100%; height: 5px;"></div>
      			</div>
      		</div>
      		<div class="titlets" id="timespan1720"></div> -->	
		</div>
		<!-- <p id="coords"></p>  -->	
	</div>
	<!-- end right -->
</div>


<script type="text/javascript">

Highcharts.setOptions({  
	 global: { useUTC: false },
   colors: ["#2a5caa","#fffffb", "#DF5353", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
    		"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"]  
}); 

var updateLabel = $("#updatetime");


//左侧图表
var myDate= new Date(2015,5,26);
var start_time =  myDate.getTime();

//var dtime = new Date().getTime();
var dtime = start_time;

var url = "${APP_PATH}dams/metro/out/dayexitdata";
var urlout = "${APP_PATH}dams/metro/in/dayenterdata";
var yc  = "${APP_PATH}dams/metro/in/dayenterpredictedata";
var station = "0099";

var main = jQuery("#main");

var pointIndex = 0; 

var hcharts = null;


var yAxisBands = "";

var hBefore = 0;
var hAfter  = 0;

var needReload = false;
function showValues() {
    $('#R0-value').html(hcharts.options.chart.options3d.alpha);
    $('#R1-value').html(hcharts.options.chart.options3d.beta);
}

$(function () {
	  //画线
	  var sid="${sid}";
	  if(sid==""){
		  changeStationImgData(station); 
	  }else{
		  changeStationImgData(sid);
	  }
	  
	// Activate the sliders
	    $('#R0').on('change', function(){
	        hcharts.options.chart.options3d.alpha = this.value;
	        showValues();
	        hcharts.redraw(false);
	    });
	    $('#R1').on('change', function(){
	    	hcharts.options.chart.options3d.beta = this.value;
	        showValues();
	        hcharts.redraw(false); 
	    });

	    
	   
	    
	    
	 // Add mouse events for rotation
	    /* $(hcharts.container).bind('mousedown.hc touchstart.hc', function (e) {
	        e = hcharts.pointer.normalize(e);

	        var posX = e.pageX,
	            posY = e.pageY,
	            alpha = hcharts.options.chart.options3d.alpha,
	            beta = hcharts.options.chart.options3d.beta,
	            newAlpha,
	            newBeta,
	            sensitivity = 5; // lower is more sensitive

	        $(document).bind({
	            'mousemove.hc touchdrag.hc': function (e) {
	                // Run beta
	                newBeta = beta + (posX - e.pageX) / sensitivity;
	                newBeta = Math.min(100, Math.max(-100, newBeta));
	                hcharts.options.chart.options3d.beta = newBeta;

	                // Run alpha
	                newAlpha = alpha + (e.pageY - posY) / sensitivity;
	                newAlpha = Math.min(100, Math.max(-100, newAlpha));
	                hcharts.options.chart.options3d.alpha = newAlpha;

	                hcharts.redraw(false);
	            },                            
	            'mouseup touchend': function () { 
	                $(document).unbind('.hc');
	            }
	        });
	    }); */
	    
    	
	    
	    
	  
	  //showimg([],[]);
	  //loadImgData();
		
	});	

//计时器   动态加载并 切换 图标的 数据  的时间间隔
function loadImgData(){
	
	  setInterval('getImgData();', 10000); 
	  setInterval('changePredicteImgData();', 60000*60*24); 
}

/**
 * 根据日期字符串(2015-12-01)取得其时间
 */
function getTimeByDateStr(dateStr){
	ymd=dateStr.split("-");
    year = ymd[0];
    month = ymd[1];
    day = ymd[2];
    //alert(year+""+month+""+day);
    return year+""+month+""+day;
}


/**
*   切换站点图标     
*  @param sid   站点id
*/
function changeStationImgData(sid,type){
	 
	 station = sid;
	 
	 //console.log(hcharts.yAxis[0].setTitle({text:"xxx"}));
	
	//获取datepicker选择的时间
     dtimeStr = $("#start").val();
	 if(dtimeStr!=""){
		 dtime=getTimeByDateStr(dtimeStr)+"";
	 }else{
		 dtime="";
	 }
	 
	 
    jQuery.post(yc,{dateTime:dtime,sid:station},function(data){
    	
     //console.log(data);
     hBefore = data.pBefore;
     hAfter = data.pAfter;
     
    
      yAxisBands = eval(data.yAxis);
      //alert(yAxisBands);
      showimg([],[]);
      //alert(data.data);
      //data: [{name: '1',y: 0}, {name: '2',y: 5}, {name: '2',y: 9}]
	 
      
	  //hcharts.xAxis[0].setCategories(categories);
      //alert(hcharts.series.length);
      /* if (hcharts.series.length > 0) {
          for (var i = 0; i < 2; i++) {
        	  hcharts.series[i].remove();
          }
      } */
      
	  datasliced=data.data;
	  if(data.data.length>12){
		  datasliced=data.data.slice(datasliced.length-12, datasliced.length);
	  } 
      
      //hcharts.addSeries({ name: "hisin", data: datasliced, stack: 'hisin', visible:false});
      
	  jQuery.post(url,{dateTime:dtime,sid:station},function(data){
		  //hcharts.series[1].setData({data:[5,4,11]});
		  
		
		  datasliced=data.data;
		  if(data.data.length>12){
			  datasliced=data.data.slice(datasliced.length-12, datasliced.length);
		  } 
		  if(type=="allday"){
			  hcharts.addSeries({ name: "rtin", data: data.data, stack: 'rtinout' });
		  }else{
			  hcharts.addSeries({ name: "rtin", data: datasliced, stack: 'rtinout' }); 
		  }
		  
		  updateLabel.text(data.pDate);
		 //console.log(data);
		  refreshPNums(data.pNow,data.pCount,data.pBefore,data.pAfter,data.hNow,data.hCount,hBefore,hAfter,data.pointSize);
	  	  
	  	  jQuery.post(urlout,{dateTime:dtime,sid:station},function(data){
	  		  
	  		  
		  datasliced=data.data;
		  if(data.data.length>12){
			  datasliced=data.data.slice(datasliced.length-12, datasliced.length);
		  }
	  		
		  if(type=="allday"){
			  hcharts.addSeries({ name: "rtout", data: data.data, stack: 'rtinout' });
		  }else{
			  hcharts.addSeries({ name: "rtout", data: datasliced, stack: 'rtinout' });
		  }
		  
	  	  
		  });
	  	  
	  	 //alert("${THEME}");
		 if("${THEME}"=="smothness"){
			 hcharts.options.chart.backgroundColor="#eeeeee";
		 }else{
		 }
	  	  
	  	  
	  	});
	 });

	 
}

function changePredicteImgData(){
	
	 var tmp =  new Date();
	 
	 if( tmp.getHours() == 0 &&  tmp.getMinutes() < 2 ){
		 
		 needReload = true;
		 
	 }else{
		 
		 needReload = false;
	 }
	
	 if(needReload ){
		 changeStationImgData(station);
	 }
}

//动态加载并 切换 图标的 数据 
function getImgData(){
	 
	//获取datepicker选择的时间
    dtimeStr = $("#start").val();
	 if(dtimeStr!=""){
		 dtime=getTimeByDateStr(dtimeStr)+"";
	 }else{
		 dtime="";
	 }
	  jQuery.post(url,{dateTime:dtime,sid:station},function(data){
		  hcharts.series[1].setData(data.data) ;
		  updateLabel.text(data.pDate);
		  refreshPNums(data.pNow,data.pCount,data.pBefore,data.pAfter,data.hNow,data.hCount,hBefore,hAfter,data.pointSize);
	  });
	  
	}


// 使用  highcharts 画图
function showimg(ind,out){
	
	Highcharts.setOptions({
        colors: [ '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
    });
	
	categories = [];
    name = '';
    data = [];
    
   hcharts = new Highcharts.Chart({
        
        chart: {
        	renderTo: 'main',
            type: 'column',
            options3d: {
                enabled: true,
                alpha: 0,
                beta: 0,
                viewDistance: 25,
                depth: 40
                /* frame: {
                    bottom: { size: 1, color: 'rgba(0,0,0,0.02)' },
                    back: { size: 1, color: 'rgba(0,0,0,0.04)' },
                    side: { size: 1, color: 'rgba(0,0,0,0.06)' }
                } */
                
            },
            marginTop: 80,
            marginRight: 40
        },

      //去除右下角的logo水印
        credits: {  
            enabled:false  
 		 }, 
        title: {
            text: ''
        },

        xAxis: {
            type: 'datetime',
            lineWidth: 2,
            //gridLineWidth: 1,
           // labels:{y:26},
             tickInterval: 600000,
             labels:{
	             style: {
	                 color: 'black'
	             },
		             rotation:0
             },

           dateTimeLabelFormats:
           {
            	day :'%e日',
            	hour: '%H'
           }
        },
        yAxis: {
	          title: {
	              text: '进站人数',
	              style: {
		                 color: 'black'
		             }
	          },
	          labels : { 
	        	  style: {
		                 color: 'black'
		             },
	                formatter : function() {//设置纵坐标值的样式  
	        
	                	
	                	return this.value;
	                 
	                }  
	               },   
	          lineWidth: 2,
	          min: 0,
	          minorGridLineWidth: 0,
	          gridLineDashStyle: 'LongDash',
	          gridLineColor:'rgba(229,229,229,0.1)',
	          gridLineWidth: 1,
	          alternateGridColor: null,
	          plotBands: yAxisBands
	      },

        tooltip: {
        	
        	formatter: function () {  
                return  Highcharts.dateFormat('%H:%M', this.x) + '-' + Highcharts.dateFormat('%H:%M', this.x + 300000) + '<br/>' +  
                '<b>' + this.series.name  + '</b> : ' + this.y + " 人 ";  
            } 
        	
            /* headerFormat: '<b>{point.key}</b><br>',
            pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y}' */
        },

        plotOptions: {
            column: {
                stacking: 'normal',
                depth: 40
            }
        },
        
        series:[]

        /* series: [{
            name: 'Joe',
            data: [3, 4, 4, 2, 5],
            stack: 'male',
            visible:false
        }, {
            name: 'Jane',
            data: [2, 5, 6, 2, 1],
            stack: 'female'
        }] */
    });
	   
   showValues();
   
 } 
</script>

</body>
</html>