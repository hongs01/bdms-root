<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增账户</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
</head>
<body>
	<script type="text/javascript">
		//初始化form表
		function initForm() {
			$('#addAccountForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (!isValid) {
						$.messager.alert('提示', '表单信息不正确'); // 如果表单是无效的则隐藏进度条
					}
					return isValid;
				},
				success : function(data) {
					if (data == 'true') {
						$.messager.alert('提示', '账户增加成功', 'info', function() {
							parent.$('#editoradd').dialog('close'); //关闭父页面
							parent.$('#accountDataGrid').datagrid('reload');
						});
					} else {
						$.messager.alert('提示', '提交失败', 'info');
					}
				}
			});
		}

		function addAccount() {
			$('#addAccountForm').submit();
		}

		$(function() {
			initForm();
		});
		
		
		$.extend($.fn.validatebox.defaults.rules, {
	        /*必须和某个字段相等*/
	        equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
	       });
	</script>
	<form:form id="addAccountForm" name="addAccountForm"
		modelAttribute="account" action="${APP_PATH}auth/account/doadd">
		<ul class="formul">
			<li><label>用户名：</label><input id="accountName"
				class="easyui-validatebox" name="accountName" type="text"
				data-options="required:true" missingMessage="用户名不能为空" validtype="remote['${APP_PATH}auth/account/isexist','accountName']"  invalidMessage="该帐号已经存在" /></li>
			<li><label>密 码：</label><input id="password" name="password"
				class="easyui-validatebox" type="password"
				data-options="required:true" missingMessage="密码不能为空" /></br></li>
				<li><label>再次密 码：</label><input name="repassword" id="repassword" 
				class="easyui-validatebox" type="password"
				data-options="required:true" missingMessage="密码不能为空" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" /></br></li>
			<li><a id="btn" href="#" class="easyui-linkbutton"
				onclick="addAccount()">提交</a></li>
		</ul>
	</form:form>
</body>
</html>