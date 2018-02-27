<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二级得分展示</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
var columns = [ [ {
	field : 'typeId',
	title : '类型Id',
	width : 100
}, {
	field : 'stationId',
	title : '站点Id',
	width : 100
},{
	field : 'name',
	title : '名称',
	width : 100
},{
	field : 'weight',
	title : '权重',
	width : 100,
},{
	field : 'peopleNum',
	title : '进站人数',
	width : 100
},{
	field : 'alarmLevel',
	title : '告警级别',
	width : 100
} ,{
	field : 'secondMark',
	title : '二级总得分',
	width : 100,
	formatter : function(value, row, index) {
		return value;
	}
},] ];
var secondlev=${secondlev};
function initdataGrid() {
	$('#secondtDataGrid').datagrid({
		title : '二级列表：',
		idField : 'id',
		rownumbers : true,
		columns : columns,
		pagination : true,
		url : "${APP_PATH}dams/weight/weightLevel/seconds/" +secondlev
		//url : "${APP_PATH}dams/score/area/seconddata/" +secondlev
		
	});
}

//弹出对话框
function alertDialog(title, url, operate) {
	var content;

	if (operate == 'add') {
		content = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
	}else if (operate == 'edit') {
		var row = $('#secondtDataGrid').datagrid('getSelected');
		var editurl = url + "/" + row.id;
		content = '<iframe scrolling="auto" frameborder="0"  src="'
				+ editurl + '" style="width:100%;height:100%;"></iframe>';
	}   

	$('#secondoperate').dialog({
		title : title,
		content : content
	});

	$('#secondoperate').dialog('open');

}  

//增加二级
function add() {
	alertDialog('新增二级', '${APP_PATH}dams/score/area/second_add', 'add');
}  

//编辑二级
 function edit() {
	var row = $('#secondtDataGrid').datagrid('getSelected');
	if (row != null) {
		alertDialog('编辑二级', '${APP_PATH}dams/score/area/second_edit', 'edit');
		$('#secondtDataGrid').datagrid('clearSelections');
	} else {
		$.messager.alert('提示', '请选中一行', 'info');
	}
}  

//删除二级
function del() {
	var row = $('#secondtDataGrid').datagrid('getSelected');
	if (row) {
		var url = '${APP_PATH}dams/score/area/deletesecond' +"/"+ row.id;
		$.messager.confirm('Confirm', '您真的确定要删除本条记录吗?', function(r) {
			if(r){
			$.get(url, function(data) {
				if (data) {
					$.messager.alert('提示', '删除成功', 'info', function() {
						$('#secondtDataGrid').datagrid('reload');
						$('#secondtDataGrid').datagrid('clearSelections');

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

$(function() {
	initdataGrid();
})

//查询
function doSearch() {
	if ($('#name').textbox('isValid')) {
			$('#secondtDataGrid').datagrid('load', {
				name : $('#name').val(),
			});
		} else {
			$.messager.alert('提示', ' 请输入名称进行查询', 'info');
		}
	}
	
//刷新
function reloadDataGird() {
	$('#name').textbox('clear');
	doSearch();
}
</script>
<body>
<div id="toolbar">
		<!-- <div id="sdssearchbox" class="easyui-panel" title="请输入查询内容"
			collapsible="true">
			<ul>
				<li><label>名称：</label> <input class="easyui-textbox"
					id="name" name="name" type="text"></li>
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
		</div>
	</div>
	<!-- datagrid -->
	<table id="secondtDataGrid" class="easyui-datagrid" style="width: 100%">
	</table>
	<!-- 增加or编辑页面 -->
	<div id="secondoperate" class="easyui-dialog"
		style="width: 600px; height: 280px; padding: 10px 20px" closed="true">
	</div>
</body>
</html>