<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海轨道交通实时监控</title>
<jsp:include page="../header/metroHeader.jsp"></jsp:include>

<script type="text/javascript">
	$(function() {
		var myDate = new Date();
		myDate.getHours(); /* 获取当前小时数(0-23) */
		myDate.getMinutes(); /* 获取当前分钟数(0-59) */
		myDate.getSeconds(); /* 获取当前秒数(0-59) */
		myDate.toLocaleDateString(); /* 获取当前日期 */
		var mytime = myDate.toLocaleTimeString(); /*  获取当前时间 */
		myDate.toLocaleString(); /* 获取日期与时间 */

	});
</script>

<style type="text/css">
/**
	 *	Basic Layout Theme
	 * 
	 *	This theme uses the default layout class-names for all classes
	 *	Add any 'custom class-names', from options: paneClass, resizerClass, togglerClass
	 */
</style>

<script type="text/javascript">
	$(function() {
		//layout
		/* myLayout = $("body").layout(layoutSettings_Outer);
		myLayout.sizePane("north",200);
		myLayout.sizePane("east",200); */

		myLayout.sizePane("north", 0);

		//datepicker
		// 获取调用控件的对象
		var dates = $("#start");
		var totalrennumdatepicker_CurrentInput;
		var currentDate = new Date();

		dates.datepicker({
			//showButtonPanel:true,
			//default:$.datepicker.regional['zh-CN'],
			//option:$.datepicker.regional['zh-CN'],
			defaultDate : currentDate,
			dateFormat : "yy-mm-dd",
			changeYear : true,
			changeMonth : true,
			//closeText: '清除',
			//beforeShow: function (input, inst) { datepicker_CurrentInput = input; },
			//numberOfMonths: 3,
			//当选择时间的时候触发此事件
			onSelect : function(selectedDate) {
				if (this.id == "start") {
					changeStationImgData("0099");
				}
			}
		});
		dates.val(currentDate.Format("yyyy-MM-dd"));

		//$("#shishi").button();
		/* $("#shishi" ).on("click",function(event,ui){
			$("#start").val("");
			changeStationImgData("0099");
		}); */
	});
</script>

<script type="text/javascript">
	/* 在界面上显示鼠标所在的位置 */
	function show_coords(event) {
		var x = event.clientX;
		var y = event.clientY;
		var say = document.all("coords");
		say.innerHTML = "X:" + x + " Y:" + y;
		say.style.position = "absolute";
		say.style.left = x + 30;
		say.style.top = y;
	}

	function refreshPNums(num1, num2, num3, num4, num12, num22, num32, num42,
			pSize) {
		//alert(num1+"---"+num2+"---"+num3+"---"+num4+"---"+num12+"---"+num22+"---"+num32+"---"+num42+"---"+pSize);
		refreshPNum("#totalren", num2, num22, 1);
	}

	function refreshPNum(id, num, numh, pSize) {
		var codeStr = "";
		var typeStr = "";
		codeStr = "0099";
		typeStr = "allInsideRT";
		//alert(codeStr+"--"+typeStr+"--"+pSize);
		$
				.getJSON(
						"${APP_PATH}dams/metro/in/criterion",
						{
							code : codeStr,
							type : typeStr,
							"resultType" : "json"
						},
						function(data, textStatus) {
							level = data.level.split(",");
							if (num < 0) {
								num = 0;
							}
							//alert(level[0]+","+level[1]+","+level[2]);
							if (num == 0) {
								$(id + "num")
										.css(
												"background-image",
												"url('"
														+ "${APP_PATH}style/images/grren.png"
														+ "')");
							} else if (num < parseInt(level[0]) * pSize) {
								$(id + "num")
										.css(
												"background-image",
												"url('"
														+ "${APP_PATH}style/images/luren.png"
														+ "')");
							} else if (num < parseInt(level[1]) * pSize) {
								$(id + "num")
										.css(
												"background-image",
												"url('"
														+ "${APP_PATH}style/images/yeren.png"
														+ "')");

							} else if (num < parseInt(level[2]) * pSize) {
								$(id + "num")
										.css(
												"background-image",
												"url('"
														+ "${APP_PATH}style/images/orren.png"
														+ "')");
							} else {
								$(id + "num")
										.css(
												"background-image",
												"url('"
														+ "${APP_PATH}style/images/reren.png"
														+ "')");
							}
							if (num < 0) {
								percent = 0;
							} else {
								percent = Math.ceil(num / parseInt(level[2])
										* 100);
							}
							if (numh < 0) {
								percenth = 0;
							} else {
								percenth = Math.ceil(numh / parseInt(level[2])
										* 100);
							}
							$((id + "num"))
									.html(
											"<span class='just-font' style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:center;font-weight:bold;width:180px;font-family: '微软雅黑';'>"
													+ formatNum(num)
													+ "</span>");
						});
	}

	function changeTitleBg(index) {
		if (index == "top") {
			$("#metrotitle")
					.css(
							"background-image",
							"url('"
									+ "${APP_PATH}style/images/titleAll_top.png"
									+ "')");
		} else if (index == "line") {
			$("#metrotitle").css(
					"background-image",
					"url('" + "${APP_PATH}style/images/titleAll_line.png"
							+ "')");
		} else if (index == "all") {
			$("#metrotitle").css("background-image",
					"url('" + "${APP_PATH}style/images/titleAll.png" + "')");
		} else if (index == "line_hc") {
			$("#metrotitle").css(
					"background-image",
					"url('" + "${APP_PATH}style/images/titleAll_line_hc.png"
							+ "')");
		} else if (index == "all_hc") {
			$("#metrotitle").css("background-image",
					"url('" + "${APP_PATH}style/images/titleAll_hc.png" + "')");
		}
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

	/**
	 * 根据日期字符串(2015-12-01)取得其时间
	 */
	function getTimeByDateStr(dateStr) {
		ymd = dateStr.split("-");
		year = ymd[0];
		month = ymd[1];
		day = ymd[2];
		//alert(year+""+month+""+day);
		return year + "" + month + "" + day;
	}
</script>

</head>


<body>

	<div class="ui-layout-center west-body-background">
		<div style="width: 100%; height: 100px;">
			<div id="lineStatInfo1">
				<span class="font-family-color">全市 地铁站内总人数</span>
				<!-- <span id="totalrennum"></span>
			<span class="yeren" id="totalren"></span> -->
			</div>
			<div id="lineStatInfo2" class="just-font">
				<!-- <div class="yeren2" id="totalren"></div> -->
				<span id="totalrennumSum"
					style="background-repeat: no-repeat; background-size: 45px 24px; margin-left: 50px; font-size: 15px;"></span>
					<span style="background-repeat: no-repeat; background-size: 45px 24px; margin-left: 50px; font-size: 15px;"><span class='just-font' style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:right;font-weight:bold;font-family: '微软雅黑';'>当前站内总人数：</span></span>
				<span id="totalrennum"
					style="background-repeat: no-repeat; background-size: 45px 24px; "></span>
			</div>
			<!-- <div class="renPlusNum" >
				<div style="float:left;width:100%;padding-top:10px" id="totalrenwait">
      				<div class="yeren" id="totalren" style="float:left;margin-left:5px"></div>
      				<div  style="width:100%;height: 22px;font-size:14px">
      					<span id="totalrennum"></span>
      				</div>
      			</div>
			</div> -->
			<div id="lineStatInfo3" class="just-font">
				<div style="padding: 5px; width: 100%; float: left">
					<!-- <div id="shishi" style="font-family: '微软雅黑';width: 80px;margin-top:3px" >实时数据</div> -->
					<span class="ui-button-text date-span">日期：</span> <input
						type="text" id="start" value="" readonly="true"
						class="in-date-checkbox" />
				</div>
			</div>
		</div>
		<div id="main" style="height: 400px; width: 100%; float: left;"></div>
		<div style="margin-left: 60px">
			<div style="float: left; margin-top: 4px">
				<img width="20px" height="20px"
					src="${APP_PATH}style/images/timeupdate.png"></img>
			</div>
			<div 
				style="width: 420px; float: left; padding-left: 5px; font-weight:bold;font-family: '微软雅黑'; font-size: 16px" class="just-font">
				数据更新时间：<span id="updatetime"></span>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		Highcharts.setOptions({
			global : {
				useUTC : false
			},
			colors : [ "#2a5caa", "#fffffb", "#DF5353", "#7798BF", "#aaeeee",
					"#ff0066", "#eeaaee", "#55BF3B", "#DF5353", "#7798BF",
					"#aaeeee" ]
		});

		var updateLabel = $("#updatetime");

		//左侧图表
		var myDate = new Date(2015, 5, 26);
		var start_time = myDate.getTime();

		//var dtime = new Date().getTime();
		var dtime = start_time;
		var urlIn = "${APP_PATH}dams/metro/in/dayenterdata";
		var url = "${APP_PATH}dams/metro/insideMetro/dayInOutSubdataStatic";
		var yc = "${APP_PATH}dams/metro/insideMetro/dayInOutSubPredicteDataStatic";
		var station = "0099";

		var main = jQuery("#main");

		var yAxisBands = "";

		var hcharts = null;
		var hBefore = 0;
		var hAfter = 0;

		var needReload = false;

		$(function() {
			//画线
			changeStationImgData(station);
			loadImgData();

		});

		//计时器   动态加载并 切换 图标的 数据  的时间间隔
		function loadImgData() {
			setInterval('getImgData();', 10000);
			setInterval('changePredicteImgData();', 60000 * 60 * 24);
		}

		/**
		 *   切换站点图标     
		 *  @param sid   站点id
		 */
		function changeStationImgData(sid) {

			station = sid;

			//获取datepicker选择的时间
			dtimeStr = $("#start").val();
			if (dtimeStr != "") {
				dtime = getTimeByDateStr(dtimeStr) + "";
			} else {
				dtime = "";
			}

			jQuery.post(url, {
				dateTime : dtime,
				sid : station
			}, function(data) {

				yAxisBands = eval(data.yAxis);
				showimg([], []);

				hcharts.series[1].setData(data.data);
				updateLabel.text(data.pDate);
				refreshPNums(data.pCountTotal, data.pCount, data.pCountAvg,
						data.pCountMax, data.hNow, data.hCount, hBefore,
						hAfter, data.pointSize);

				jQuery.post(yc, {
					dateTime : dtime,
					sid : station
				}, function(data) {

					//alert(data.yAxis);
					//yAxisBands = eval(data.yAxis);
					//showimg([],[]);
					hBefore = data.pBefore;
					hAfter = data.pAfter;
					hcharts.series[0].setData(data.data);
				});
			});

			jQuery
					.post(
							urlIn,
							{
								dateTime : dtime,
								sid : station
							},
							function(data) {
								//console.log(data);
								$("#totalrennumSum")
										.html(
												"<span class='just-font' style='overflow:hidden;white-space:nowrap;display:-moz-inline-box;display:inline-block;font-size:18px;text-align:right;font-weight:bold;font-family: '微软雅黑';'>累计进站人数："
														+ formatNum(data.pCount)
														+ "</span>");
							});

		}

		function changePredicteImgData() {

			var tmp = new Date();

			if (tmp.getHours() == 0 && tmp.getMinutes() < 2) {

				needReload = true;

			} else {

				needReload = false;
			}

			if (needReload) {
				changeStationImgData(station);
			}
		}

		//动态加载并 切换 图标的 数据 
		function getImgData() {

			//获取当前时间
			//dtime = new Date().getTime();
			jQuery.post(url, {
				dateTime : dtime,
				sid : station
			}, function(data) {
				hcharts.series[1].setData(data.data);
				updateLabel.text(data.pDate);
				refreshPNums(data.pCountTotal, data.pCount, data.pCountAvg,
						data.pCountMax, data.hNow, data.hCount, hBefore,
						hAfter, data.pointSize);
			});

		}

		//使用  highcharts 画图
		function showimg(ind, out) {

			if ("${THEME}" == "black/dot-luv") {
				hcharts = new Highcharts.Chart({
					chart : {
						renderTo : 'main', //图表放置的容器，DIV 
						defaultSeriesType : 'spline', //图表类型line(折线图), 
						// zoomType: 'x',  //x轴方向可以缩放 
						//backgroundColor:'${HIGHCHARTBG}'
						backgroundColor : '#1b1b1b'
					},
					//去除右下角的logo水印
					credits : {
						enabled : false
					},
					legend : {
						enabled : true
					},
					title : {
						text : ''
					},
					/*  subtitle: {
					      text: 'Irregular time data in Highcharts JS'
					  },*/
					tooltip : {
						formatter : function() {
							return Highcharts.dateFormat('%H:%M', this.x)
									+ '-'
									+ Highcharts.dateFormat('%H:%M',
											this.x + 300000) + '<br/>' + '<b>'
									+ this.series.name + '</b> : ' + this.y
									+ " 人 ";
						}
					},

					xAxis : {
						type : 'datetime',
						lineWidth : 2,
						//gridLineWidth: 1,
						// labels:{y:26},
						tickInterval : 3600000,
						labels : {
							style : {
								color : '#fffffb'
							},
		  		             rotation:0
						},

						dateTimeLabelFormats : {
							day : '%e日',
							hour : '%H'
						}
					},
					yAxis : {
						title : {
							text : '',
							style : {
								color : '#fffffb'
							}
						},
						labels : {
							style : {
								color : '#fffffb'
							},
							formatter : function() {//设置纵坐标值的样式  

								return formatNum(this.value);

							}
						},
						lineWidth : 2,
						min : 0,
						minorGridLineWidth : 0,
						gridLineDashStyle : 'LongDash',
						gridLineColor : 'rgba(229,229,229,0.1)',
						gridLineWidth : 1,
						alternateGridColor : null,
						plotBands : yAxisBands
					},

					plotOptions : {
						line : {
							dataLabels : {
								enabled : true
							//在数据点上显示对应的数据值 
							},
							enableMouseTracking : false
						//取消鼠标滑向触发提示框 
						},
						spline : {
							//lineWidth: 4,
							states : {
								hover : {
									lineWidth : 4
								}
							},
							marker : {
								enabled : false
							},
							pointInterval : 3600000, // one hour
							pointStart : start_time
						}
					},
					series : [ {
						name : "历史",
						data : ind,
						visible : false
					}, {
						name : "人数",
						data : out
					} ]
				});
			} else {
				hcharts = new Highcharts.Chart({
					chart : {
						renderTo : 'main', //图表放置的容器，DIV 
						defaultSeriesType : 'spline', //图表类型line(折线图), 
						// zoomType: 'x',  //x轴方向可以缩放 
						//backgroundColor:'${HIGHCHARTBG}'
						backgroundColor : 'white'
					},
					//去除右下角的logo水印
					credits : {
						enabled : false
					},
					legend : {
						enabled : true
					},
					title : {
						text : ''
					},
					/*  subtitle: {
					      text: 'Irregular time data in Highcharts JS'
					  },*/
					tooltip : {
						formatter : function() {
							return Highcharts.dateFormat('%H:%M', this.x)
									+ '-'
									+ Highcharts.dateFormat('%H:%M',
											this.x + 300000) + '<br/>' + '<b>'
									+ this.series.name + '</b> : ' + this.y
									+ " 人 ";
						}
					},

					xAxis : {
						type : 'datetime',
						lineWidth : 2,
						lineColor : 'black',
						//gridLineWidth: 1,
						// labels:{y:26},
						tickInterval : 3600000,
						labels : {
							style : {
								color : 'black'
							},
		  		             rotation:0
						},

						dateTimeLabelFormats : {
							day : '%e日',
							hour : '%H'
						}
					},
					yAxis : {
						lineColor : 'black',
						title : {
							text : '',
							style : {
								color : 'black'
							}
						},
						labels : {
							style : {
								color : 'black'
							},
							formatter : function() {//设置纵坐标值的样式  

								return formatNum(this.value);

							}
						},
						lineWidth : 2,
						min : 0,
						minorGridLineWidth : 0,
						gridLineDashStyle : 'LongDash',
						gridLineColor : 'black',
						gridLineWidth : 1,
						alternateGridColor : null,
						plotBands : yAxisBands
					},

					plotOptions : {
						line : {
							dataLabels : {
								enabled : true
							//在数据点上显示对应的数据值 
							},
							enableMouseTracking : false
						//取消鼠标滑向触发提示框 
						},
						spline : {
							lineWidth: 4,
							states : {
								hover : {
									lineWidth : 6
								}
							},
							marker : {
								enabled : false
							},
							pointInterval : 3600000, // one hour
							pointStart : start_time
						}
					},
					series : [ {
						name : "历史",
						data : ind,
						color : 'rgb(135,179,203)',
						visible : false
					}, {
						name : "人数",
						data : out,
						color : 'rgb(0,84,248)'
					} ]
				});
			}

		}

		function getGFtimesAM(str) {
			var times = str.split(":");
			var hourNum = parseInt(times[0]);
			var minNum = parseInt(times[1]);
			//alert(parseInt(times[0])>17);
			if(hourNum==0 && minNum==0){
	 			return 24;
	 		}
			if (hourNum < 7) {
				count = 0;
			} else {
				if (hourNum == 7) {
					count = 0;
				} else if (hourNum == 8) {
					count = 12;
				} else {
					count = 24;
				}
				if (count != 24) {
					//alert(Math.floor(parseInt(times[1])/5));
					count += Math.floor(minNum / 5);
				}
			}
			return count;
		}

		function getGFtimesPM(str) {
			var times = str.split(":");
			var hourNum = parseInt(times[0]);
			var minNum = parseInt(times[1]);
			//alert(parseInt(times[0])>17);
			if(hourNum==0 && minNum==0){
	 			return 24;
	 		}
			if (hourNum < 17) {
				count = 0;
			} else {
				if (hourNum == 17) {
					count = 0;
				} else if (hourNum == 18) {
					count = 12;
				} else {
					count = 24;
				}
				if (count != 24) {
					//alert(Math.floor(parseInt(times[1])/5));
					count += Math.floor(minNum / 5);
				}
			}
			return count;
		}
	</script>

</body>
</html>