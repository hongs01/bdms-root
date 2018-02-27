<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑区域</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>
</head>
<body>
<script type="text/javascript">
//初始化form表
function initForm() {
	$('#editAreaForm').form({ 
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
	        		$.messager.alert('提示','区域编辑成功','info',function(){
	        			parent.$('#operate').dialog('close');     //关闭父页面
		        		parent.$('#areaDataGrid').datagrid('reload');  //刷新父页面
	        		});
	        	}
	    }
	});
}

function editArea() {
	$('#editAreaForm').submit();
}

$(function(){
	initForm();	
}); 
</script>
 
	<form:form id="editAreaForm" name="editAreaForm"
		modelAttribute="area" action="${APP_PATH}dams/score/area/doedit">
	    <ul class="formul">
		 <li><input id="Id"  class="easyui-validatebox" data-options="required:true" name="Id"
			 missingMessage="Id" value="${area.id}"   type="hidden" /></li>
			 
		<li><label>区域Id：</label><input id="areaId"  class="easyui-validatebox" data-options="required:true" name="areaId"
			 missingMessage="区域Id" value="${area.areaId}"   type="text" /></li>
		 
		<li><label>区域名：</label> <input id="areaName" class="easyui-validatebox" data-options="required:true" name="areaName"
			 missingMessage="区域名" value="${area.areaName}" type="text" /></li>
			 
		<li><label>区域：</label><input id="area"  class="easyui-validatebox" data-options="required:true" name="area"
		     missingMessage="区域" value="${area.area}"   type="text" /></li>
	     
	     <li><label></label><input id="totalMark"  class="easyui-validatebox" data-options="required:true" name="totalMark"
	     missingMessage="总分" value="${area.totalMark}"   type="hidden" /></li>
		     
	    <li><label>告警级别：</label><input id="alarmLevel"  class="easyui-validatebox" data-options="required:true" name="alarmLevel"
		     missingMessage="告警级别" value="${area.alarmLevel}"   type="text" /></li>
		     
	    <li><label>告警数量：</label><input id="alarmCount"  class="easyui-validatebox" data-options="required:true" name="alarmCount"
		     missingMessage="告警数量" value="${area.alarmCount}"   type="text" /></li>
		<li><a id="btn" href="#" onclick="editArea()"   class="easyui-linkbutton" >提交</a></li>
		</ul>
	</form:form>
</body>
</html>