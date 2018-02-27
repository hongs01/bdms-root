<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>area管理</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
//area表列数据
	var columnsarea = [ [ {
		field : 'areaId',
		title : '区域Id',
		width : 100
	}, {
		field : 'areaName',
		title : '区域名',
		width : 100,
	},{
		field : 'area',
		title : '区域',
		width : 100,
	},{
		field : 'totalMark',
		title : '总得分',
		width : 100,
		formatter : function(value, row, index) {
			return value;
		}
	},{
		field : 'alarmLevel',
		title : '告警级别',
		width : 100,
	},{
		field : 'alarmCount',
		title : '告警数量',
		width : 100,
	}, ] ];
	
	//初始化areadataGrid
	function initareadataGrid() {
		$('#areaDataGrid').datagrid({
			title : 'area数据表管理',
			idField : 'id',
			toolbar : '#toolbar',
			rownumbers : true,
			columns : columnsarea,
			pagination : true,
			singleSelect : true,
			//url : '${APP_PATH}dams/weight/weightLevel/allAreas'
			url : '${APP_PATH}dams/score/area/data'
		});
	}
	
	//弹出对话框
	  function alertDialog(title, url, operate) {
		var content;

		if (operate == 'add') {
			content = '<iframe scrolling="auto" frameborder="0"  src="' + url
					+ '" style="width:100%;height:100%;"></iframe>';
		}else if (operate == 'edit') {
			var row = $('#areaDataGrid').datagrid('getSelected');
			//alert(row.id);
			var editurl = url + "/" + row.id;
			content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ editurl + '" style="width:100%;height:100%;"></iframe>';
		}else if (operate == 'next') {
			var row = $('#areaDataGrid').datagrid('getSelected');
			//alert(row.id);
			var nexturl = url + "/" + row.id;
			content = '<iframe scrolling="auto" frameborder="0"  src="'
					+nexturl + '" style="width:100%;height:100%;"></iframe>';
		}  

		$('#operate').dialog({
			title : title,
			content : content
		});

		$('#operate').dialog('open');

	}  
	
	//增加区域
	  function addArea() {
		alertDialog('新增区域', '${APP_PATH}dams/score/area/area_add', 'add');
	}  
	
	//编辑区域
	   function editArea() {
		var row = $('#areaDataGrid').datagrid('getSelected');
		if (row != null) {
			alertDialog('编辑区域', '${APP_PATH}dams/score/area/edit', 'edit');
			$('#areaDataGrid').datagrid('clearSelections');
		} else {
			$.messager.alert('提示', '请选中一行', 'info');
		}
	}  
	
	//删除区域
	  function delArea() {
		var row = $('#areaDataGrid').datagrid('getSelected');
		if (row) {
			var url = '${APP_PATH}dams/score/area/delete' +"/"+ row.id;
			$.messager.confirm('Confirm', '您真的确定要删除该区域吗?', function(r) {
				if(r){
				$.get(url, function(data) {
					if (data) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							$('#areaDataGrid').datagrid('reload');
							$('#areaDataGrid').datagrid('clearSelections');
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
	  function nextLev() {
			var row = $('#areaDataGrid').datagrid('getSelected');
			if (row != null) {
				alertDialog('一级显示', '${APP_PATH}dams/score/area/nextlev', 'next');
				$('#areaDataGrid').datagrid('clearSelections');
			} else {
				$.messager.alert('提示', '请选中一行', 'info');
			}
		}
		
	$(function() {
		initareadataGrid();
	 	})
	 	
	 	//查找列表
	function doSearch() {
		if ($('#areaName').textbox('isValid')) {
			$('#areaDataGrid').datagrid('load', {
				areaName : $('#areaName').val(),
			});
		} else {
			$.messager.alert('提示', ' 请输入区域名进行查询', 'info');
		}
	}
	 
	//刷新
	function reloadDataGird() {
		$('#areaName').textbox('clear');
		doSearch();
	}
</script>
<body>

	<!-- 查询 -->
	<!-- toolbar -->
	<div id="toolbar">
		<div id="sdssearchbox" class="easyui-panel" title="请输入查询内容"
			collapsible="true">
			<ul>
				<li><label>区域名：</label> <input class="easyui-textbox"
					id="areaName" name="areaName" type="text"></li>
				<li><a id="sdssearchboxbtn" href="#" onclick="doSearch()"
					class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找</a>
				</li>
				<li>
				<a id="sdssearchboxreloadbtn" href="#" onclick="reloadDataGird()"
					class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
				</li>
			</ul>
		</div>

		<div>
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addArea()"> 新增 </a>  
		     <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editArea()"> 编辑 </a> 
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="delArea()"> 删除 </a> 
			 <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="nextLev()"> 下一级 </a>   
		</div>
	</div>
	<!-- datagrid -->
	<table id="areaDataGrid" class="easyui-datagrid" style="width: 100%">
	</table>
	<!-- 增加or编辑页面 -->
	<div id="operate" class="easyui-dialog"
		style="width: 800px; height: 480px; padding: 10px 20px" closed="true">
	</div>
</body>
</html>