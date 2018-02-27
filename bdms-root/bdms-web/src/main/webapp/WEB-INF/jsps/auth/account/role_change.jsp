<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色修改</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
<script type="text/javascript">
var columns = [ [ {
	 field:'id',
	 checkbox:true 
}, {
	field : 'roleName',
	title : '角色名称',
	width : 100
}] ];

function initdataGrid() {
	$('#roleDataGrid').datagrid({
		title : '帐号${account.accountName}的角色列表：',
		idField : 'id',
		rownumbers : true,
		columns : columns,
		pagination : true,
		url : '${APP_PATH}role/list/data'
	});
}

$(function() {
	initdataGrid();
})
</script>
</head>
<body>
	<form:form modelAttribute="account">
			<input name="id" id="id" class="easyui-validatebox" data-options="required:true" 
		     missingMessage="编号不能为空" type="hidden"  value="${account.id}" /> 
		     <input name="version" type="hidden" value="${account.version}" />
	</form:form>
	
	
	<table id="roleDataGrid" class="easyui-datagrid" style="width: 100%">
	</table>
</body>

</html>