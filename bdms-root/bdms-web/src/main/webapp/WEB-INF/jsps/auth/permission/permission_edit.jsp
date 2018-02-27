<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑权限</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>

</head>
<body>
<body>
	 
	<form:form id="editPermissionForm" name="editPermissionForm"
		modelAttribute="permission" action="${APP_PATH}auth/permission/doedit">
	<ul class="formul">
	        <li><label></label><input  id="id"  class="easyui-validatebox"
				name="id"  type="hidden" data-options="required:true" value="${permission.id}"  missingMessage="编号不能为空" /></li>
		    <li><label>所属模块：</label><input  id="module"  class="easyui-validatebox"
				name="module"  type="text" data-options="required:true" value="${permission.module}"  missingMessage="所属模块不能为空" /></li>
			<li><label>操作项目名称：</label><input id="title" name="title" class="easyui-validatebox"
				 type="text" data-options="required:true" value="${permission.title}" missingMessage="操作项目名称不能为空" /></li>
			<li><label>操作项：</label><input  id="operation"  class="easyui-validatebox"
				name="operation"  type="text" data-options="required:true" value="${permission.operation}" missingMessage="操作项不能为空" /></li>
			<li><label>路径资源：</label><input  id="resource"  class="easyui-validatebox"
				name="resource"  type="text" data-options="required:true" value="${permission.resource}" missingMessage="路径资源不能为空" /></li>
			<li><label>备注:</label><input  id="remark"  class="easyui-validatebox"
				name="remark"  type="text" data-options="required:true" value="${permission.remark}" missingMessage="备注不能为空" /></li>
		 
		<li><a id="btn" href="#" onclick="editPermission()"   class="easyui-linkbutton" >提交</a></li>
		</ul>
	</form:form>
</body>
<script type="text/javascript">
/* 定义为一个表单 */

//初始化form表
	function initForm() {
		$('#editPermissionForm').form({ 
		onSubmit: function(){    
			var isValid = $(this).form('validate');   
			if (!isValid){
				$.messager.alert('提示','表单信息不正确');    	// 如果表单是无效的则隐藏进度条
			}
			return(isValid);
		},
		   success:function(data){    
		        if(data)                   
		        	{
		        		$.messager.alert('提示','权限编辑成功','info',function(){
		        			parent.$('#addoredit').dialog('close');     //关闭父页面
			        		parent.$('#permissionDataGrid').datagrid('reload');  //刷新父页面
		        		});
		        	}
		    }
		});
}
 
 
	function editPermission() {
		$('#editPermissionForm').submit();
	}
 
 $(function(){
		initForm();	
	});
</script>
</html>