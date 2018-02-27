<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一级得分展示</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
var columns = [ [ {
	field : 'areaId',
	title : '区域Id',
	width : 100
}, {
	field : 'typeName',
	title : '类型名称',
	width : 100
},{
	field : 'typeCode',
	title : '类型编码',
	width : 100
},{
	field : 'weight',
	title : '权重',
	width : 100,
},{
	field : 'firstMark',
	title : '一级总得分',
	width : 100,
	formatter : function(value, row, index) {
		return value;
	}
},/* {
	field : 'stationName',
	title : '站点名',
	width : 100
}, */{
	field : 'alarmCount',
	title : '告警数量',
	width : 100
},{
	field : 'alarmLevel',
	title : '告警级别',
	width : 100
},] ];

var firstlev=${firstlev};
function initdataGrid() {
	$('#firstDataGrid').datagrid({
		title : '区域 的一级列表：',
		idField : 'id',
		rownumbers : true,
		columns : columns,
		pagination : true,
		url : "${APP_PATH}dams/weight/weightLevel/firsts/" + firstlev
		//url : "${APP_PATH}dams/score/area/firstdata/" +firstlev
	});
}
//弹出对话框
function alertDialog(title, url, operate) {
	var content;

	if (operate == 'add') {
		content = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
	}else if (operate == 'edit') {
		var row = $('#firstDataGrid').datagrid('getSelected');
		var editurl = url + "/" + row.id;
		content = '<iframe scrolling="auto" frameborder="0"  src="'
				+ editurl + '" style="width:100%;height:100%;"></iframe>';
	}else if (operate == 'next') {
		var row = $('#firstDataGrid').datagrid('getSelected');
		//alert(row.id);
		var nexturl = url + "/" + row.id;
		content = '<iframe scrolling="auto" frameborder="0"  src="'
				+ nexturl + '" style="width:100%;height:100%;"></iframe>';
	}  

	$('#firstoperate').dialog({
		title : title,
		content : content
	});

	$('#firstoperate').dialog('open');

}  

//增加一级
function add() {
	alertDialog('新增一级', '${APP_PATH}dams/score/area/first_add', 'add');
}  

//编辑一级
 function edit() {
	var row = $('#firstDataGrid').datagrid('getSelected');
	if (row != null) {
		alertDialog('编辑一级', '${APP_PATH}dams/score/area/first_edit', 'edit');
		$('#firstDataGrid').datagrid('clearSelections');
	} else {
		$.messager.alert('提示', '请选中一行', 'info');
	}
}  

//删除一级
function del() {
	var row = $('#firstDataGrid').datagrid('getSelected');
	if (row) {
		var url = '${APP_PATH}dams/score/area/deletefirst' +"/"+ row.id;
		$.messager.confirm('Confirm', '您真的确定要删除本条记录吗?', function(r) {
			if(r){
			$.get(url, function(data) {
				if (data) {
					$.messager.alert('提示', '删除成功', 'info', function() {
						$('#firstDataGrid').datagrid('reload');
						$('#firstDataGrid').datagrid('clearSelections');
					});
				}
			}, 'json');
			}else{
				$.messager.alert('提示','放弃删除','info');
			}
		});
	} else {
		$.messager.alert('提示', '请选中一行', 'info');
	}
}  

// 显示下一级 
function next() {
		var row = $('#firstDataGrid').datagrid('getSelected');
		if (row != null) {
			alertDialog('二级显示', '${APP_PATH}dams/score/area/first_nextlev', 'next');
			$('#firstDataGrid').datagrid('clearSelections');
		} else {
			$.messager.alert('提示', '请选中一行', 'info');
		}
	}


$(function() {
	initdataGrid();
})

//查询
function doSearch() {
	if ($('#typeName').textbox('isValid')) {
			$('#firstDataGrid').datagrid('load', {
				typeName : $('#typeName').val(),
			});
		} else {
			$.messager.alert('提示', ' 请输入类型名称进行查询', 'info');
		}
	}
	 
//刷新
function reloadDataGird() {
	$('#typeName').textbox('clear');
	doSearch();
}
</script>
<body>
	<div id="toolbar">
		<!-- <div id="sdssearchbox" class="easyui-panel" title="请输入查询内容"
			collapsible="true">
			<ul>
				<li><label>类型名称：</label> <input class="easyui-textbox"
					id="typeName" name="typeName" type="text"></li>
				<li><a id="sdssearchboxbtn" href="#" onclick="doSearch()"
					class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找</a>
				</li>
				<li>
				<a id="sdssearchboxreloadbtn" href="#" onclick="reloadDataGird()"
					class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
				</li>
			</ul>
		</div> -->

		<div>
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="add()"> 新增 </a>  
		     <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="edit()"> 编辑 </a> 
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="del()"> 删除 </a> 
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="next()"> 下一级 </a>   
		</div>
	</div>
	<!-- datagrid -->
	<table id="firstDataGrid" class="easyui-datagrid" style="width: 100%">
	</table>
	<!-- 增加or编辑页面 -->
	<div id="firstoperate" class="easyui-dialog"
		style="width: 600px; height: 280px; padding: 10px 20px" closed="true">
	</div>
</body>
</html>