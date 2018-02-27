<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一级添加</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>
</head>
<body>
<script type="text/javascript">
//初始化form表
function initForm() {
	$('#addFirstForm').form({
		onSubmit : function() {
			var isValid = $(this).form('validate');
			if (!isValid) {
				$.messager.alert('提示', '表单信息不正确'); // 如果表单是无效的则隐藏进度条
			}
			return isValid;
		},
		success : function(data) {
			if (data == 'true') {
				$.messager.alert('提示', '一级增加成功', 'info', function() {
					parent.$('#firstoperate').dialog('close'); //关闭父页面
					parent.$('#firstDataGrid').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '提交失败', 'info');
			}
		}
	});
}

function addFirst() {
	$('#addFirstForm').submit();
}

$(function() {
	initForm();
});

</script>

<form:form id="addFirstForm" name="addFirstForm"
		  modelAttribute="first" action="${APP_PATH}dams/score/area/firstadd"    >
		<ul class="formul">
			<li><label>区域Id：</label><input id="areaId"
				class="easyui-validatebox" name="areaId" type="text"
				data-options="required:true" missingMessage="区域Id不能为空" /></br></li>
			<li><label>类型名称：</label><input id="typeName"
			    class="easyui-validatebox" name="typeName" type="text"
			    data-options="required:true" missingMessage="类型名称不能为空" /></br></li>
		    <li><label>类型编码：</label><input id="typeCode"
		        class="easyui-validatebox" name="typeCode" type="text"
		        data-options="required:true" missingMessage="类型编码不能为空" /></br></li>
			<li><label>权重</label><input id="weight" name="weight"
				class="easyui-validatebox" type="text"
				data-options="required:true" missingMessage="权重不能为空" /></br></li>
		     <li><input id="firstMark" name="firstMark"
			    class="easyui-validatebox" type="hidden" value=0
			    data-options="required:true" missingMessage="一级总分不能为空" /></li> 
		    <li><label>告警数量</label><input id="alarmCount" name="alarmCount"
		        class="easyui-validatebox" type="text"
		        data-options="required:true" missingMessage="告警数量不能为空" /></br></li>
	        <li><label>告警级别</label><input id="alarmLevel" name="alarmLevel"
	            class="easyui-validatebox" type="text"
	            data-options="required:true" missingMessage="告警级别不能为空" /></br></li>
			<li><a id="btn" href="#" class="easyui-linkbutton"
				onclick="addFirst()">提交</a></li>
		</ul>
	</form:form>
</body>
</html>