<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>大数据计算的风险评分</title>

</head>
<body style="background-color: #1b1b1b">
<!-- start header -->
<jsp:include page="../header/metroHeader.jsp"></jsp:include>
<!-- end header -->
<!-- start center -->
<div class="ui-layout-center bodycolor" style="background-color:#1b1b1b;overflow:hidden; border: 0">
	<select id="selectAreaname">
			<option value="00">请选择</option>
	    </select>
	
	<!-- start 区域总得分 -->
		<div style=" height: 150px; color: #fff; margin-top: 10px; width: 100% ">
			<div id="area" style="float: left; width: 20%">
				外滩
			</div>
					<div id="dsc"style="height: 130px;  width: 40%; line-height: 150px; padding:10px; color: white; float: left; font-size: 18px; font-family: 微软雅黑; text-align: center;">
						区域经大数据计算的风险评分为：
					</div>
				<div id="TotalAreaNameScore"
					style="width: 30%; height: 150px;  float: left;"></div>
			</div>
	<!-- end 区域总得分 -->
	
	<!-- start 一级区域得分 -->
	<div id="firstList" >
	</div>
	<!-- end 一级区域得分 -->
	<div class="clear">
	</div>
	<!-- start 二级得分 -->
		<div id="secondList"
			style="margin-top: 150px;   width:100%;color: white;padding-top: 10px;">
			</div>
		<!-- end 二级得分-->
	
</div>
<!-- end center -->

<!-- start east -->
	<div class="ui-layout-east bodycolor" style="background-color:#03233a;overflow:hidden; border: 0">
		<div style="text-align: center; color: #fff">
			<h5>区域经大数据计算的风险评分排行榜</h5>
		</div>
		<ul>
			<li style="list-style: none; color: #fff">
				外滩 ：39分
			</li>
			<li style="list-style: none;color: #fff">
				豫园：30分
			</li>
			<li style="list-style: none;color: #fff">
				静安寺：17分
			</li>
		</ul>
	</div>
<!-- end east -->

<script type="text/javascript">
	$(function(){
		
		//整体布局
		myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("east",400);
		
		
		//初始化下拉框按钮
		$("#selectAreaname").selectmenu({
			width : 150
		});
		
		//下拉列表数据
		$.getJSON("${APP_PATH}dams/weight/weightLevel/area ", {
			"resultType" : "json"
		}, function(data, textStatus) {
			$("#selectAreaname").empty();//先清空tbody  
			var strHTML;
			$.each(data, function(InfoIndex, Info) {//遍历json里的数据  
				strHTML += "<option value="+Info.id+">";
				strHTML += Info.areaName;
				strHTML += "</option>";
			});
			$("#selectAreaname").html(strHTML);//显示到tbody中
			$("#selectAreaname").selectmenu("refresh");
		});
		
		//默认为外滩
		$.getJSON("${APP_PATH}dams/weight/weightLevel/getFirstTotal/" + "1", {
			"resultType" : "json"
		}, function(data, textStatus) {
			showScore(data, '1');
			initCircleScore();
			CircleScore(data[0].totalMark);
		});
		//selectAreaname下拉框change事件
		$("#selectAreaname").on(
				"selectmenuchange",
				function(event, ui) {
					var id = $("#selectAreaname").val();
					$("#firstList").empty(); //先清空firstList
					$("#secondList").empty();//清空secondList
					$("#TotalAreaNameScore").empty();//先清空TotalAreaNameScore
					//var areanaemNoCo=new Array('#FFFFFF','#e91b39','#8ac53f','#fad107','#502e8d','#9056a3','#d61870','#f37121','#009eda','#79c8ed','#bca8d1','#7e2131','#007c65','#e895c0','#e895c0','#e895c0','#8dd1ba');
					if (id != "00") {
						//根据区域名Id获取15:43 2015/10/30所选区域名的区域类型json数据
						$.getJSON(
								"${APP_PATH}dams/weight/weightLevel/getFirstTotal/"
										+ id, {
									"resultType" : "json"
								}, function(data, textStatus) {
									$("#area").html(data[0].areaName);
									console.log(data);
									showScore(data, id);
									initCircleScore();
									CircleScore(data[0].totalMark);
								})
					}
				});
		
	
		
	});
	

	//初始化圆圈
	function initCircleScore() {
		$('#TotalAreaNameScore').radialIndicator({
			barColor : {
				0 : '#00FF00',
				50 : '#00FF00', //0-50设置成绿色
				// 51: '#FFFF00', 
				70 : '#FFFF00', //50-70设置成黄色
				//71：'#FFA500', 
				90 : '#FFA500', //70-90设置成橙色    
				//91：'#FF0000',
				100 : '#FF0000' //90-100设置成红色
			},
			barWidth : 5,
			percentage : false,
			progress : 0.5,
			fontFamily : '微软雅黑',
			fontSize : 28,
			radius : 70,
			format : function(score) {
				return score + "分"
			}
		});
	};

	//radialIndicator插件绘制得分
	function CircleScore(score) {
		//$('#indicatorContainer').radialIndicator();
		var radialObj = $('#TotalAreaNameScore').data('radialIndicator');
		radialObj.animate(score);
	}

	/* onclick="getData('"+obj.typeCode+"')" */

	/* style=\"width:300px;height:50px; background-color:white;\" */

	//显示一级得分
	function showScore(data, id) {
		var obj;
		var dom = "";
		for (i = 0; i < data.length; i++) {
			obj = data[i];
			//alert(obj.typeName);

			if (obj.typeName == "轨交") {

				dom += "<div class='firstListContent firstListContentbggj' id='"
						+ obj.typeCode
						+ "'  onmouseover=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','1','"+obj.name+"')\" onmouseout=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','0')\"> "

						+ "<div class=' span_font'>"
						+ formatNum(obj.firstMark)
						+ "</div><div class='span_name'>"
						+ obj.name
						+ "</div></div>"
			} else if (obj.typeName == "电子围栏") {
				dom += "<div class='firstListContent firstListContentbgdzwl' id='"
						+ obj.typeCode
						+ "'  onmouseover=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','1','"+obj.name+"')\" onmouseout=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','0')\"> "

						+ "<div class=' span_font'>"
						+ formatNum(obj.firstMark)
						+ "</div><div class='span_name'>"
						+ obj.name
						+ "</div></div>"
			} else if (obj.typeName == "视频") {
				dom += "<div class='firstListContent firstListContentbgvideo' id='"
						+ obj.typeCode
						+ "'  onmouseover=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','1')\" onmouseout=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','0')\"> "

						+ "<div class=' span_font'>"
						+ formatNum(obj.firstMark)
						+ "</div><div class='span_name'>"
						+ obj.typeName
						+ "</div></div>"
			} else if (obj.typeName == "WIFI") {
				dom += "<div class='firstListContent firstListContentbgwifi' id='"
						+ obj.typeCode
						+ "'  onmouseover=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','1')\" onmouseout=\"getData('"
						+ obj.typeCode
						+ "','"
						+ id
						+ "','0')\"> "

						+ "<div class=' span_font'>"
						+ formatNum(obj.firstMark)
						+ "</div><div class='span_name'>"
						+ obj.typeName
						+ "</div></div>"
			}
		}
		var firstList = $("#firstList");
		firstList.html(dom);
	}
	
	
	
	
	
	
	//通过类型typeId获取二级列表
	function getData(typeId, id, mouse,name) {
		var url = "${APP_PATH}dams/weight/weightLevel/getSecond/";
		var geturl = url + id + '/' + typeId;
		var secondList ="";
		var bb = $("#secondList");
		bb.show();
		if (mouse == 1) {
			jQuery
					.get(
							geturl,
							function(data) {
								secondList+="<div id='alllist'>"+name+'，总共'+data[0].count+'项，具体得分情况为：'+"</div>";
								
								secondList+="<ul id='slist'>"
								
								for (i = 0; i < data.length; i++) {
									secondList += "<li><span>"+data[i].name+":"+formatNum(data[i].secondMark)+",</span>人数:"+data[i].enterTimes+"</li>";
								}
								secondList+="</ul>";
								
								bb.html(secondList);
							});
		} else if (mouse == 0) {
			bb.html(secondList);
		}
	}
	
	function initCircleScore() {
		$('#TotalAreaNameScore').radialIndicator({
			barColor : {
				0 : '#00FF00',
				50 : '#00FF00', //0-50设置成绿色
				// 51: '#FFFF00', 
				70 : '#FFFF00', //50-70设置成黄色
				//71：'#FFA500', 
				90 : '#FFA500', //70-90设置成橙色    
				//91：'#FF0000',
				100 : '#FF0000' //90-100设置成红色
			},
			barWidth : 5,
			percentage : false,
			progress : 0.5,
			fontFamily : '微软雅黑',
			fontSize : 28,
			radius : 70,
			format : function(score) {
				return score + "分"
			}
		});
	};

	//radialIndicator插件绘制得分
	function CircleScore(score) {
		//$('#indicatorContainer').radialIndicator();
		var radialObj = $('#TotalAreaNameScore').data('radialIndicator');
		radialObj.animate(score);
	}
	
	
	
	function formatNum(str) {
		str = str + "";
		var newStr = "";
		var count = 0;
		if (str.indexOf(".") == -1) {
			for ( var i = str.length - 1; i >= 0; i--) {
				if (count % 3 == 0 && count != 0) {
					newStr = str.charAt(i) + "," + newStr;
				} else {
					newStr = str.charAt(i) + newStr;
				}
				count++;
			}
			str = newStr;
			if (str.indexOf("-,") != -1) {
				str = str.replace("-,", "-");
			}
			return str;
		} else {
			for ( var i = str.indexOf(".") - 1; i >= 0; i--) {
				if (count % 3 == 0 && count != 0) {
					newStr = str.charAt(i) + "," + newStr;
				} else {
					newStr = str.charAt(i) + newStr;
				}
				count++;
			}
			str = newStr + (str).substr((str + "00").indexOf("."), 3);
			if (str.indexOf("-,") != -1) {
				str = str.replace("-,", "-");
			}
			return str;
		}
	}
	
	
</script>
</body>



</html>