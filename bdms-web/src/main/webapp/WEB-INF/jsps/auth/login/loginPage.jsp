<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面登录</title>
<jsp:include page="../../admin/admincommonheader.jsp"></jsp:include>
</head>
<body>
<script type="text/javascript">
function Login(){
	$('#loginForm').submit()
}
function celLogin(){
	 
}
</script>
<form:form id="loginForm" name="loginForm"
		modelAttribute="login" action="${APP_PATH}/login/AccountAuthentication">
		<ul class="formul">
			<li><label>请输入帐户名：</label><input id="accountName"
				class="easyui-validatebox" name="accountName" type="text"
				data-options="required:true" missingMessage="账户名不能为空"/></li>
			<li><label>请输入密码：</label><input id="password" name="password"
				class="easyui-validatebox" type="password"
				data-options="required:true" missingMessage="密码不能为空" /></br></li>
				<li><label>确认密 码：</label><input name="repassword" id="repassword" 
				class="easyui-validatebox" type="password"
				data-options="required:true" missingMessage="密码不能为空" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" /></br></li>
			<li><a id="btnLog" href="#" class="easyui-linkbutton"
				onclick="Login()">登录</a> <a id="btnCel" href="#" class="easyui-linkbutton"
				onclick="celLogin()">取消</a> </li>
				 
		</ul>
	</form:form>
	
	
	<h2>${emsg} }</h2>
	<form method="post">
	username:<input type="text" name="username"/><br/>
	password:<input type="password" name="password"><br/>
	<input type="submit"/>
	</form>
</body>
</html>