<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
	var columns = [ [ {
		field : 'id',
		title : '角色id',
		width : 100
	}, {
		field : 'roleName',
		title : '角色名称',
		width : 100
	},{
		field:'permission',
		title:'权限',
		width:500
	}] ];
	
	//初始化dataGrid
	function initdataGrid() {
		$('#roleDataGrid').datagrid({
			title : '角色管理',
			idField : 'id',
			toolbar : '#toolbar',
			rownumbers : true,
			columns : columns,
			pagination : true,
			url : '${APP_PATH}role/list/data'
		});
	}
	
	
	//弹出对话框
	function alertDialog(title, url, addoredit) {
		var content;

		if (addoredit == 'add') {
			content = '<iframe scrolling="auto" frameborder="0"  src="' + url
					+ '" style="width:100%;height:100%;"></iframe>';
		} else {
			var row = $('#roleDataGrid').datagrid('getSelected');
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
	
	
	//增加角色
	function addRole() {
		alertDialog('新增角色', '${APP_PATH}auth/role/add', 'add');
	}
	
	
	//编辑角色
	function editRole() {
		var row = $('#roleDataGrid').datagrid('getSelected');
		if (row != null) {
			alertDialog('编辑角色', '${APP_PATH}auth/role/edit', 'edit');
			$('#roleDataGrid').datagrid('clearSelections');
		} else {
			$.messager.alert('提示', '请选中一行', 'info');
		}
	}
	
	
	$(function() {
		initdataGrid();
	})
	
	
	//删除角色
	function delRole() {
		var row = $('#roleDataGrid').datagrid('getSelected');
		if (row) {
			var url = '${APP_PATH}/auth/role/delete/' + row.id;
			$.messager.confirm('Confirm', '您真的确定要删除该角色吗?', function(r) {
				$.get(url, function(data) {
					if (data) {
						$.messager.alert('提示', '删除成功', 'info',function(){							
							$('#roleDataGrid').datagrid('reload');
							$('#roleDataGrid').datagrid('clearSelections');
							
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
			iconCls="icon-add" plain="true" onclick="addRole()"> 新增 </a>
	    <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editRole()"> 编辑 </a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="delRole()"> 删除 </a>
	</div>
	<!-- datagrid -->
	<table id="roleDataGrid" class="easyui-datagrid" style="width: 100%">
	</table>
	<!-- 增加or编辑页面 -->
	<div id="addoredit" class="easyui-dialog"
		style="width: 600px; height: 280px; padding: 10px 20px" closed="true">
	</div>
</body>
</html>