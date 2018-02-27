<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二级增加</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>
</head>
<body>

<script type="text/javascript">
//初始化form表
function initForm() {
	$('#addSecondForm').form({
		onSubmit : function() {
			var isValid = $(this).form('validate');
			if (!isValid) {
				$.messager.alert('提示', '表单信息不正确'); // 如果表单是无效的则隐藏进度条
			}
			return isValid;
		},
		success : function(data) {
			if (data == 'true') {
				$.messager.alert('提示', '二级增加成功', 'info', function() {
					parent.$('#secondoperate').dialog('close'); //关闭父页面
					parent.$('#secondtDataGrid').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '提交失败', 'info');
			}
		}
	});
}

function addSecond() {
	$('#addSecondForm').submit();
}

$(function() {
	initForm();
});
</script>

<form:form id="addSecondForm" name="addSecondForm"
		  modelAttribute="second" action="${APP_PATH}dams/score/area/secondadd"    >
		<ul class="formul">
			<li><label>类型Id：</label><input id="typeId"
				class="easyui-validatebox" name="typeId" type="text"
				data-options="required:true" missingMessage="类型Id不能为空" /></br></li>
			<li><label>站点Id：</label><input id="stationId"
			    class="easyui-validatebox" name="stationId" type="text"
			    data-options="required:true" missingMessage="站点Id不能为空" /></br></li>
		    <li><label>名称：</label><input id="name"
		        class="easyui-validatebox" name="name" type="text"
		        data-options="required:true" missingMessage="名称不能为空" /></br></li>
			<li><label>权重</label><input id="weight" name="weight"
				class="easyui-validatebox" type="text"
				data-options="required:true" missingMessage="权重不能为空" /></br></li>
		    <li><label>进站人数</label><input id="peopleNum" name="peopleNum"
			    class="easyui-validatebox" type="text"
			    data-options="required:true" missingMessage="进站人数不能为空" /></br></li>
	        <li><label>告警级别</label><input id="alarmLevel" name="alarmLevel"
	            class="easyui-validatebox" type="text"
	            data-options="required:true" missingMessage="告警级别不能为空" /></br></li>
			<li><a id="btn" href="#" class="easyui-linkbutton"
				onclick="addSecond()">提交</a></li>
		</ul>
	</form:form>
</body>
</html>