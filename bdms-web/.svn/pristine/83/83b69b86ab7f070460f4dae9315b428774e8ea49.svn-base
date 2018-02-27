<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海轨道交通实时监控</title>
<jsp:include page="../header/metroHeader.jsp"></jsp:include>

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
	 
	</style>
	
 
<script type="text/javascript">
	$(function() {
		//layout
		myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("east",360);
		
		var theme="${THEME}";	
		var style="";
		if("smoothness" == theme)
		{
			style="style='color:black'";
		}
		else
		{
			style="style='color:white'";
		}
		
		//button and select
		$("#quanshi").button();
		//$("#shishi").button();
		$("#lishi").button();
	    $("#selectmenu").selectmenu({width: 90});
		$("#selectmenu2").selectmenu({width: 150});
		
		var sid="${sid}";
		var lineIdParam=sid.substr(0,2);
		
		$.getJSON("${APP_PATH}dams/metro/in/lines", { "resultType": "json" }, function(data, textStatus)
				{
				   $("#selectmenu").empty();//先清空tbody  
					var strHTML = "<option value='-'>请选择</option>";
					var lineNoCo=new Array('#888','#e91b39','#8ac53f','#fad107','#502e8d','#9056a3','#d61870','#f37121','#009eda','#79c8ed','#bca8d1','#7e2131','#007c65','#e895c0','#e895c0','#e895c0','#8dd1ba');
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
									$("#lineStatInfo").html("<span style='color:"+lineNoCo[parseInt(lineIdParam)]+"'>"+lineIdParam+"</span><span "+style+"> 号线 </span><span style='color:"+lineNoCo[parseInt(lineIdParam)]+"'>"+sName+"</span><span "+style+"> 进出站统计</span>");
								});
					}
					
				}); 
			
			$("#selectmenu").on("selectmenuchange",function( event, ui){
				var lineNoCo=new Array('#888','#e91b39','#8ac53f','#fad107','#502e8d','#9056a3','#d61870','#f37121','#009eda','#79c8ed','#bca8d1','#7e2131','#007c65','#e895c0','#e895c0','#e895c0','#8dd1ba');
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
						$("#lineStatInfo").html("<span "+style+">换乘线 进出站统计</span>");
						$('#lineStatInfo').css("display","none");
						$('#lineStatInfo').fadeIn(200);
						
						changeStationImgData("0098");
					}else{
						//changeTitleBg("line");
						//changeLine(str);
						$('#lineStatInfo').css("color",lineNoCo[parseInt(str)]);	
						
						$("#lineStatInfo").html("<span style='height:50px;color;"+lineNoCo[parseInt(str)]+"'>"+str+"</span><span "+style+"> 号线 进出站统计 </span>");
						$('#lineStatInfo').css("display","none");
						$('#lineStatInfo').fadeIn(200);
						
						changeStationImgData(linenum+"00");
					}	
				}else{
					//clearallinfo();
					//changeTitleBg("top");
					$("#lineStatInfo").html("<span "+style+">全市 进出站统计</span>");
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
				var lineNoCo=new Array('#888','#e91b39','#8ac53f','#fad107','#502e8d','#9056a3','#d61870','#f37121','#009eda','#79c8ed','#bca8d1','#7e2131','#007c65','#e895c0','#e895c0','#e895c0','#8dd1ba');
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
								$("#lineStatInfo").html("<span style='color:"+lineNoCo[parseInt(str)]+"'>"+str+"</span><span "+style+"> 号线 </span><span style='color:"+lineNoCo[parseInt(str)]+"'>"+data.name+"</span><span "+style+"> 进出站统计</span>");
							}else{
								$("#lineStatInfo").html("</span><span "+style+">换乘线  </span><span style='color:"+lineNoCo[parseInt(str)]+"'>"+data.name+"</span><span "+style+"> 进出站统计</span>");
							}
							//changeStationLogo(data);
							changeStationImgData(stat);
						}else{
							if(linenum!="00"){
								$("#lineStatInfo").html("<span style='color:"+lineNoCo[parseInt(str)]+"'>"+str+"</span><span "+style+"> 号线 </span><span "+style+"> 进出站统计</span>");
							}else{
								$("#lineStatInfo").html("</span><span "+style+">换乘线  </span><span "+style+"> 进出站统计</span>");
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
				$("#lineStatInfo").html("<span "+style+">全市 进出站统计</span>");
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
					//alert(len.substring(0));
					//alert($("#main").css("width">1100));
					if(parseInt(len)>1100){
						$( ".ui-layout-toggler" ).css("background-image","url('"+"${APP_PATH}style/images/leftarrow.png"+"')");
					}else{
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
		    dates.datepicker({
		        //showButtonPanel:true,
			    //default:$.datepicker.regional['zh-CN'],
		    	//option:$.datepicker.regional['zh-CN'],
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
		          }  
		        }
		    });
		    dates.val(currentDate.Format("yyyy-MM-dd"));
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
	 if(pSize==undefined){
		 pSize=1;
	 }
	 //alert(pSize);
	 var stat=$("#selectmenu").val();
	 var stat2=$("#selectmenu2").val();
	 var codeStr="";
	 var typeStr="";
	 if(stat2!="-"){
	 	codeStr=stat2;
	 	typeStr="stationSumRT";
	 }else if(stat!="-"){
		 if(stat="00"){
			 codeStr="0098";
			 typeStr="transLineSumRT";
		 }else{
			 codeStr=stat; 
			 typeStr="lineSumRT"; 
		 }
	 }else{
		 codeStr="0099";
		 typeStr="allSumRT";
	 }
	 //alert(codeStr+"--"+typeStr);
	 
	 $.getJSON("${APP_PATH}dams/metro/in/criterion", { code:codeStr,type:typeStr,"resultType": "json" }, function(data, textStatus)
		{	
		 if(pSize==0){
			 pSize=1;
		 }
			level=data.level.split(",");
			//alert(num+":"+level[0]+"--"+level[1]+"--"+level[2]);
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
			$((id+"num")).html("<span class='just-font' style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:left;font-weight:bold;width:280px;font-family: '微软雅黑';' Title='"+num+",历史值为："+numh+"'>"+formatNum(num)+"（"+formatNum(numh)+"）</span>");
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

<div class="ui-layout-center west-body-background">
	<div style="width:100%; height:100px;">
		<div id="lineStatInfo"><span class="font-family-color">全市 进出站统计</span></div>
	</div>
	<div id="main" style="height:400px;width:100%; float:left;"></div>
	<div style="margin-left:60px">
	<div style="float:left;margin-top:4px"><img width="20px" height="20px" src="${APP_PATH}style/images/timeupdate.png"></img></div>
	<div class="update-div">数据更新时间：<span id="updatetime"></span></div>
	</div>				 
</div>
 
<div class="ui-layout-east east-div"> 
	<!-- start right -->
	<div id="right" style="float:left;width:380px;padding-top:10px">
	
		<div style="padding: 5px;width: 100%;">
			<div style="padding: 5px;width:100%;float:left">
			<!-- <div id="shishi" style="font-family: '微软雅黑';width: 80px;" >实时数据</div> -->
			<!-- <div id="lishi" style="font-family: '微软雅黑';width: 100px;margin-left:8px" >历史日期</div> -->
			<span class="ui-button-text date-span" style="width: 100px">日期：</span>
            <input type="text" id="start" value="" readonly="true" class="in-date-checkbox"/>
			
			</div>
		</div>
	
		<div style="padding: 5px;width: 100%;">
			<div style="padding: 5px;width:65px;float:left">
				<a id="quanshi" style="font-family: '微软雅黑';width: 60px;" >全市</a>
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
			<!-- 进出站统计 -->
			<div style="width: 100%; padding-top: 25px;float:left" >
			<div style="width:100%;BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 20px"></div>
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">进出站统计</div>
			</div>
			<div class="renPlusNum" >
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
      		<!--进出站总统计 -->
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:200px; float:left;font-weight:bold;padding-left:10px;font-size:18px">进出站总统计</div>
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
      		<!--早高峰进出站总统计  --> 
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:310px; float:left;font-weight:bold;padding-left:10px;font-size:18px">早高峰进出站总统计（07:00-09:00）</div>
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
      		<!--晚高峰进出站总统计  -->
      		<div style="width: 100%; padding-top: 20px;float:left" >
				<div style="float:left;margin-top:1px;margin-left:10px"><img src="${APP_PATH}style/jqueryUIthems/${THEME}/images/round.png"></img></div>
				<div class="just-font" style="width:310px; float:left;font-weight:bold;padding-left:10px;font-size:18px">晚高峰进出站总统计（17:00-19:00）</div>
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
var url = "${APP_PATH}dams/metro/inoutsum/dayInOutSumdata";
var yc = "${APP_PATH}dams/metro/inoutsum/dayInOutSumPredicteData";
var station = "0099";

var main = jQuery("#main");

var yAxisBands = "";

var hcharts = null;

var hBefore = 0;
var hAfter  = 0;

var needReload = false;

$(function () {
	  //画线
	var sid="${sid}";
	  if(sid==""){
		  changeStationImgData(station); 
	  }else{
		  changeStationImgData(sid);
	  }
	  loadImgData();
		
	});	

//计时器   动态加载并 切换 图标的 数据  的时间间隔
function loadImgData(){
	  setInterval('getImgData();', 10000); 
	  setInterval('changePredicteImgData();', 60000); 
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
function changeStationImgData(sid){
	 
	 station = sid;
	
	//获取datepicker选择的时间
     dtimeStr = $("#start").val();
	 if(dtimeStr!=""){
		 dtime=getTimeByDateStr(dtimeStr)+"";
	 }else{
		 dtime="";
	 }
	 
    jQuery.post(yc,{dateTime:dtime,sid:station},function(data){
    	
     // console.log(data);
        
      hBefore = data.pBefore;
      hAfter  = data.pAfter;
      
      yAxisBands = eval(data.yAxis);
  	  showimg([],[]);
	  hcharts.series[0].setData(data.data);
	  jQuery.post(url,{dateTime:dtime,sid:station},function(data){
			 hcharts.series[1].setData(data.data);
			 updateLabel.text(data.pDate);
			 refreshPNums(data.pNow,data.pCount,data.pBefore,data.pAfter,data.hNow,data.hCount,hBefore,hAfter,data.pointSize);
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


//使用  highcharts 画图
function showimg(ind,out){
	
	if("${THEME}"=="black/dot-luv"){
		hcharts = new Highcharts.Chart({ 
	          chart: { 
	              renderTo: 'main', //图表放置的容器，DIV 
	              defaultSeriesType: 'spline', //图表类型line(折线图), 
	              // zoomType: 'x',  //x轴方向可以缩放 
				  //backgroundColor:'${HIGHCHARTBG}'
				  backgroundColor:'#1b1b1b'
	          }, 
	        //去除右下角的logo水印
	        credits: {  
	            enabled:false  
	 		 }, 
	 		legend:{
	 			enabled : true
	 		},
	        title: {
	            text: ''
	        },
	      /*  subtitle: {
	            text: 'Irregular time data in Highcharts JS'
	        },*/
	        tooltip: {  
         formatter: function () {  
             return  Highcharts.dateFormat('%H:%M', this.x) + '-' + Highcharts.dateFormat('%H:%M', this.x + 300000) + '<br/>' +  
             '<b>' + this.series.name  + '</b> : ' + this.y + " 人 ";  
         }  
     },  
	        
	        xAxis: {
	            type: 'datetime',
	            lineWidth: 2,
	            //gridLineWidth: 1,
	           // labels:{y:26},
	             tickInterval: 3600000,
	             labels:{
		             style: {
		                 color: '#fffffb'
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
		              text: '',
		              style: {
			                 color: '#fffffb'
			             }
		          },
		          labels : { 
		        	  style: {
			                 color: '#fffffb'
			             },
		                formatter : function() {//设置纵坐标值的样式  
		        
		                	
		                	return formatNum(this.value);
		                 
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
	        	{name:"历史",data:ind, visible:false},
	        	{name:"进出站统计",data:out}
	        ]
	    });
	}else{
		hcharts = new Highcharts.Chart({ 
	          chart: { 
	              renderTo: 'main', //图表放置的容器，DIV 
	              defaultSeriesType: 'spline', //图表类型line(折线图), 
	              // zoomType: 'x',  //x轴方向可以缩放 
				  //backgroundColor:'${HIGHCHARTBG}'
				  backgroundColor:'white'
	          }, 
	        //去除右下角的logo水印
	        credits: {  
	            enabled:false  
	 		 }, 
	 		legend:{
	 			enabled : true
	 		},
	        title: {
	            text: ''
	        },
	      /*  subtitle: {
	            text: 'Irregular time data in Highcharts JS'
	        },*/
	        tooltip: {  
         formatter: function () {  
             return  Highcharts.dateFormat('%H:%M', this.x) + '-' + Highcharts.dateFormat('%H:%M', this.x + 300000) + '<br/>' +  
             '<b>' + this.series.name  + '</b> : ' + this.y + " 人 ";  
         }  
     },  
	        
	        xAxis: {
	            type: 'datetime',
	            lineWidth: 2,
	            lineColor:'black',
	            //gridLineWidth: 1,
	           // labels:{y:26},
	             tickInterval: 3600000,
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
	        	lineColor:'black',
		          title: {
		              text: '',
		              style: {
			                 color: 'black'
			             }
		          },
		          labels : { 
		        	  style: {
			                 color: 'black'
			             },
		                formatter : function() {//设置纵坐标值的样式  
		        
		                	
		                	return formatNum(this.value);
		                 
		                }  
		               },   
		          lineWidth: 2,
		          min: 0,
		          minorGridLineWidth: 0,
		          gridLineDashStyle: 'LongDash',
		          gridLineColor:'black',
		          gridLineWidth: 1,
		          alternateGridColor: null,
		          plotBands: yAxisBands
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
	        	{name:"历史",data:ind,color:'rgb(135,179,203)', visible:false},
	        	{name:"进出站统计",data:out,color:'rgb(0,84,248)'}
	        ]
	    });
	}
	 
 } 



</script>

</body>
</html>