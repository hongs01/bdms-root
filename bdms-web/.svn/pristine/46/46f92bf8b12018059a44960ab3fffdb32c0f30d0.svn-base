<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑角色</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
</head>
<body>
<%-- ${role} --%>
	<form:form id="editRoleForm" name="editRoleForm"
		modelAttribute="role" action="${APP_PATH}auth/role/doedit">
	<ul class="formul">
		<li><label> <!-- 角色Id: --></label> <input name="id" id="id" class="easyui-validatebox" data-options="required:true" 
		     missingMessage="角色Id不能为空"  value="${role.id}"  type="hidden" /></li>
		 
		<li><label>角色名称：</label> <input id="roleName"  class="easyui-validatebox" data-options="required:true" name="roleName"
			 missingMessage="角色名称不能为空" value="${role.roleName}" type="text" /></li>
		 
	<!-- 	<li><label></label> <input id="password" class="easyui-validatebox" data-options="required:true" name="password"
			 missingMessage="密码不能为空" type="hidden"   /></li> -->
		 
		<li><a id="btn" href="#" onclick="editRole()"   class="easyui-linkbutton" >提交</a></li>
	</ul>
	</form:form>
</body>
 
<script type="text/javascript">
/* 定义为一个表单 */

//初始化form表
	function initForm() {
		$('#editRoleForm').form({ 
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
		        		$.messager.alert('提示','角色编辑成功','info',function(){
		        			parent.$('#addoredit').dialog('close');     //关闭父页面
			        		parent.$('#roleDataGrid').datagrid('reload');  //刷新父页面
		        		});
		        	}
		    }
		});
}

	function editRole() {
		$('#editRoleForm').submit();
	}
 
 $(function(){
		initForm();	
	});
</script>
 
</html>