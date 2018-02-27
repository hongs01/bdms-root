<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户列表</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
	var columns = [ [ {
		field : 'accountName',
		title : '用户名',
		width : 100
	}, {
		field : 'roleNames',
		title : '角色',
		width : 500,
		formatter : function(value, row, index) {
			return value;
		}
	}, {
		field : 'lastUpdateTime',
		title : '最后更新时间',
		width : 150,
		formatter : function(value, row, index) {
			var timestamp = new Date(value);
			return timestamp.toLocaleString();
		}
	}, {
		field : 'active',
		title : '是否激活',
		width : 100,
		formatter : function(value, row, index) {
			return value == true ? '激活' : '未激活';
		}
	},] ];

	//初始化dataGrid
	function initdataGrid() {
		$('#accountDataGrid').datagrid({
			title : '账户管理',
			idField : 'id',
			toolbar : '#toolbar',
			rownumbers : true,
			columns : columns,
			pageSize:10,
			pageList:[10,20,30],
			pagination : true,
			singleSelect : true,
			url : '${APP_PATH}account/list/data'
		});
	}

	//弹出对话框
	function alertDialog(title, url, addoredit) {
		var content;

		if (addoredit == 'add') {
			content = '<iframe scrolling="auto" frameborder="0"  src="' + url
					+ '" style="width:100%;height:100%;"></iframe>';
		} else if (addoredit == 'change') {
			var row = $('#accountDataGrid').datagrid('getSelected');
			var changeurl = url + "/" + row.id;
			content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ changeurl + '" style="width:100%;height:100%;"></iframe>';
		} else {
			var row = $('#accountDataGrid').datagrid('getSelected');
			var editurl = url + "/" + row.id;
			content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ editurl + '" style="width:100%;height:100%;"></iframe>';
		}

		$('#editoradd').dialog({
			title : title,
			content : content
		});

		$('#editoradd').dialog('open');

	}

	//增加账户
	function addAccount() {
		alertDialog('新增账户', '${APP_PATH}auth/account/add', 'add');
	}

	//编辑账户
	function editAccount() {
		var row = $('#accountDataGrid').datagrid('getSelected');
		if (row != null) {
			alertDialog('编辑账户', '${APP_PATH}auth/account/edit', 'edit');
			$('#accountDataGrid').datagrid('clearSelections');
		} else {
			$.messager.alert('提示', '请选中一行', 'info');
		}
	}

	$(function() {
		initdataGrid();
	})

	//角色修改
	function changeRole() {
		var row = $('#accountDataGrid').datagrid('getSelected');
		if (row != null) {
			alertDialog('角色修改', '${APP_PATH}auth/account/change', 'change');
			$('#accountDataGrid').datagrid('clearSelections');
		} else {
			$.messager.alert('提示', '请选中一行', 'info');
		}
	}

	//删除账户
	function delAccount() {
		var row = $('#accountDataGrid').datagrid('getSelected');
		if (row) {
			var url = '${APP_PATH}/auth/account/delete/' + row.id;
			$.messager.confirm('Confirm', '您真的确定要删除该账户吗?', function(r) {
				$.get(url, function(data) {
					if (data) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							$('#accountDataGrid').datagrid('reload');
							$('#accountDataGrid').datagrid('clearSelections');

						});
					}
				}, 'json');
			});
		} else {
			$.messager.alert('提示', '请选中一行', 'info');
		}
	}

	//验证时间
	$.extend($.fn.validatebox.defaults.rules, {
		dateValid : {
			validator : function(value, param) { //参数value为当前文本框的值，也就是endDate
				startTime = $(param[0]).datetimebox('getValue');//获取起始时间的值
				var start = $.fn.datebox.defaults.parser(startTime);
				var end = $.fn.datebox.defaults.parser(value);
				varify = end > start;
				return varify;
			},
			message : '结束时间要大于开始时间!'
		}
	});

	//查找列表

	function doSearch() {
		if ($('#endDate').datebox('isValid')) {
			$('#accountDataGrid').datagrid('load', {
				accountName : $('#accountName').val(),
				startDate : $('#startDate').datebox('getValue'),
				endDate : $('#endDate').datebox('getValue')
			});
		} else {
			$.messager.alert('提示', '查询时间不正确', 'info');
		}
	}

	//刷新
	function reloadDataGird() {
		$('#accountName').textbox('clear');
		$('#startDate').datebox('setValue','');
		$('#endDate').datebox('setValue','');
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
				<li><label>用户名：</label> <input class="easyui-textbox"
					id="accountName" name="accountName" type="text"></li>
				<li><label>更新日期：</label> <input id="startDate" name="startDate"
					class="easyui-datebox" type="text">至 <input id="endDate"
					name="endDate" class="easyui-datebox "
					validType="dateValid['#startDate']" type="text"></li>
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
				iconCls="icon-add" plain="true" onclick="addAccount()"> 新增 </a>  <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editAccount()"> 编辑 </a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="delAccount()"> 删除 </a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="changeRole()"
				style="width: 100px">角色修改</a>
		</div>
	</div>
	<!-- datagrid -->
	<table id="accountDataGrid" class="easyui-datagrid" style="width: 100%">
	</table>
	<!--linkbutton -->
	<!--  <div id="roleLinkButton">
	 <a href="javascript:void(0)" class="easyui-linkbutton"  
	iconCls="icon-edit" plain="true" onclick="changeRole()" style="width:100px">角色修改</a>
	</div> -->

	<!-- 增加or编辑页面 -->
	<div id="editoradd" class="easyui-dialog"
		style="width: 600px; height: 280px; padding: 10px 20px" closed="true">
	</div>
</body>
</html>
