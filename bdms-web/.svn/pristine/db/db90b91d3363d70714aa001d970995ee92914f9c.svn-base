<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>

</head>
<body>
	<%-- ${account } --%>
	<form:form id="editAccountForm" name="editAccountForm"
		modelAttribute="account" action="${APP_PATH}auth/account/doedit">
	<ul class="formul">
		<li>
			<input name="id" id="id" class="easyui-validatebox" data-options="required:true" 
		     missingMessage="编号不能为空" type="hidden"  value="${account.id}" /> 
		     
		     <input name="version" type="hidden" value="${account.version}" />
		     </li>
		 	
		<li><label>用户名：</label><input id="accountName"  class="easyui-validatebox" data-options="required:true" name="accountName"
			 missingMessage="用户名不能为空" value="${account.accountName}"   type="text" /></li>
		 
		<li><label>密 码：</label> <input id="password" class="easyui-validatebox" data-options="required:true" name="password"
			 missingMessage="密码不能为空" value="${account.password}" type="password" /></li>
		 
		 <li><label>再次密 码：</label><input name="repassword" id="repassword" 
				class="easyui-validatebox" type="password"
				data-options="required:true" missingMessage="密码不能为空" value="${account.password}" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" /></br></li>
		<li><a id="btn" href="#" onclick="editAccount()"   class="easyui-linkbutton" >提交</a></li>
		</ul>
	</form:form>
</body>
<script type="text/javascript">
/* 定义为一个表单 */

//初始化form表
	function initForm() {
		$('#editAccountForm').form({ 
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
		        		$.messager.alert('提示','账户编辑成功','info',function(){
		        			parent.$('#editoradd').dialog('close');     //关闭父页面
			        		parent.$('#accountDataGrid').datagrid('reload');  //刷新父页面
		        		});
		        	}
		    }
		});
}
 
/* $(function(){
	$('#editAccountForm').form({      
	    success:function(data){    
	        if(data)
	        	{
	        		parent.$('#edit_account_from').dialog('close');   /* 关闭父界面 
	        		parent.$('#dg').datagrid('reload');            /* 刷新父界面 
	        	}
	    }    
	}); 
});
 */
	function editAccount() {
		$('#editAccountForm').submit();
	}
 
 $(function(){
		initForm();	
	});
 
 
 $.extend($.fn.validatebox.defaults.rules, {
     /*必须和某个字段相等*/
     equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
    });
</script>
</html>