<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限列表</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
	var columns = [ [ {
		field : 'module',
		title : '所属模块',
		width : 100
	}, {
		field : 'title',
		title : '操作项目名称',
		width : 100
	} ,{
		field : 'operation',
		title : '操作项',
		width : 100
	},{
		field : 'resource',
		title : '路径资源',
		width : 100
	},{
		field : 'remark',
		title : '备注',
		width : 100
	}] ];
	
	//初始化dataGrid
	function initdataGrid() {
		$('#permissionDataGrid').datagrid({
			title : '权限管理',
			idField : 'id',
			toolbar : '#toolbar',
			rownumbers : true,
			columns : columns,
			pagination : true,
			url : '${APP_PATH}permission/list/data'
		});
	}
	
		//弹出对话框
		function alertDialog(title, url, addoredit) {
			var content;

			if (addoredit == 'add') {
				content = '<iframe scrolling="auto" frameborder="0"  src="' + url
						+ '" style="width:100%;height:100%;"></iframe>';
			} else {
				var row = $('#permissionDataGrid').datagrid('getSelected');
				var editurl = url + "/" + row.id;
				content = '<iframe scrolling="auto" frameborder="0"  src="'
						+ editurl + '" style="width:100%;height:100%;"></iframe>';
			}

			$('#addoredit').dialog({
				title : title,
				content : content
			});

			$('#addoredit').dialog('open');

		}
		
		
		//增加权限
		function addPermission() {
			alertDialog('新增权限', '${APP_PATH}auth/permission/add', 'add');
		}

		//编辑权限
		function editPermission() {
			var row = $('#permissionDataGrid').datagrid('getSelected');
			if (row != null) {
				alertDialog('编辑权限', '${APP_PATH}auth/permission/edit', 'edit');
				$('#permissionDataGrid').datagrid('clearSelections');
			} else {
				$.messager.alert('提示', '请选中一行', 'info');
			}
		}

		$(function() {
			initdataGrid();
		})
		
		//删除权限
	function delPermission() {
		var row = $('#permissionDataGrid').datagrid('getSelected');
		if (row) {
			var url = '${APP_PATH}/auth/permission/delete/' + row.id;
			$.messager.confirm('Confirm', '您真的确定要删除该账户吗?', function(r) {
				$.get(url, function(data) {
					if (data) {
						$.messager.alert('提示', '删除成功', 'info',function(){							
							$('#permissionDataGrid').datagrid('reload');
							$('#permissionDataGrid').datagrid('clearSelections');
							
						});
					}
				}, 'json');
			});
		} else {
			$.messager.alert('提示', '请选中一行', 'info');
		}
	}
	</script>	
<body>
<!-- toolbar -->
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addPermission()"> 新增 </a>
	    <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editPermission()"> 编辑 </a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="delPermission()"> 删除 </a>
	</div>
	<!-- datagrid -->
	<table id="permissionDataGrid" class="easyui-datagrid" style="width: 100%">
	</table>
	<!-- 增加or编辑页面 -->
	<div id="addoredit" class="easyui-dialog"
		style="width: 600px; height: 280px; padding: 10px 20px" closed="true">
	</div>	
</body>
</html>