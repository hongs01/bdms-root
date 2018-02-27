<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 新增角色</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
</head>
<body>
<script type="text/javascript">
	
	
	//初始化form表
	function initForm() {
			$('#addRoleForm').form({
				onSubmit: function(){    
					var isValid = $(this).form('validate');   
					if (!isValid){
						$.messager.alert('提示','表单信息不正确');    	// 如果表单是无效的则隐藏进度条
					}
					return isValid;
			    },
			    success:function(data){    
			        if(data)
			        	{
			        		$.messager.alert('提示','角色增加成功','info',function(){
			        			parent.$('#addoredit').dialog('close');     //关闭父页面
				        		parent.$('#roleDataGrid').datagrid('reload');
			        		});
			        	}
			    }    
			});
		}

		function addRole() {
			$('#addRoleForm').submit();  
		}
		

		$(function(){
			initForm();	
		});
	</script>
	<form:form id="addRoleForm" name="addRoleForm"
		modelAttribute="role" action="${APP_PATH}auth/role/doadd">
		<ul class="formul">
			<!-- <li><label>角色Id：</label><input  id="id"  class="easyui-validatebox"
				name="id"  type="text" data-options="required:true" missingMessage="角色Id不能为空" /></li> -->
			<li><label>角色名称：</label><input id="roleName" name="roleName" class="easyui-validatebox"
				 type="text" data-options="required:true" missingMessage="角色名称不能为空" /></br></li>
			<li><a id="btn" href="#" class="easyui-linkbutton" onclick="addRole()">提交</a>
			</li>
		</ul>
	</form:form>
</body>
</html>