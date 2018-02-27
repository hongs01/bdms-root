//设置ajax访问后台填充折线图
function setChartLine(url,id){
	var Chart=require('echarts').init(document.getElementById(id));
	Chart.showLoading({text:'正在努力的读取数据中...'});
	var label=[];
	var value=[];
	$.ajax({
		url:url,
		dataType:"json",
		success:function(data){
			$.each(data,function(i,p){
				label[i]=p['label'];
				value[i]={'name':p['label'],'value':p['value']};
			});
			Chart.hideLoading();
			optionLine.legend.data=label;
			optionLine.series[0]['data']=value;
			optionLine.series[0]['radius']=[0,100];
			Chart.setOption(optionLine);
		}
	});
}