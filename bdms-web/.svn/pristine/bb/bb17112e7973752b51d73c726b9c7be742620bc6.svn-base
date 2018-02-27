<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二级编辑</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>

</head>
<script type="text/javascript">
//初始化form表
function initForm() {
	$('#editSecondForm').form({ 
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
	        		$.messager.alert('提示','二级编辑成功','info',function(){
	        			parent.$('#secondoperate').dialog('close');     //关闭父页面
		        		parent.$('#secondtDataGrid').datagrid('reload');  //刷新父页面
	        		});
	        	}
	    }
	});
}

function editSecond() {
	$('#editSecondForm').submit();
}

$(function(){
	initForm();	
});

</script>
<body>
<%-- ${second } --%>
<form:form id="editSecondForm" name="editSecondForm"
		 modelAttribute="second" action="${APP_PATH}dams/score/area/secondedit"   >
	    <ul class="formul">
		 <li><input id="Id" class="easyui-validatebox" name="Id" data-options="required:true" 
			 missingMessage="Id" value="${second.id}"   type="hidden" /></li>
			 
		<li><label>类型Id：</label><input id="typeId" class="easyui-validatebox" name="typeId" data-options="required:true" 
			 missingMessage="类型Id" value="${second.typeId}"   type="text" /></li>
			 
		 <li><label>站点Id：</label><input id="stationId"  class="easyui-validatebox" data-options="required:true" name="stationId"
		     missingMessage="站点Id" value="${second.stationId}"   type="text" /></li>
		
		 <li><label>名称：</label><input id="name"  class="easyui-validatebox" data-options="required:true" name="name"
		     missingMessage="名称" value="${second.name}"   type="text" /></li>
		  
		<li><label>权重：</label> <input id="weight" class="easyui-validatebox" data-options="required:true" name="weight"
			 missingMessage="权重" value="${second.weight}" type="text" /></li>
		 
		 <li><label>进站人数：</label><input id="peopleNum"  class="easyui-validatebox" data-options="required:true" name="peopleNum"
		    missingMessage="进站人数" value="${second.peopleNum}"   type="text" /></li>
		 
		 <li><label>告警级别：</label><input id="alarmLevel"  class="easyui-validatebox" data-options="required:true" name="alarmLevel"
		    missingMessage="告警级别" value="${second.alarmLevel}"   type="text" /></li>
		
		<li><a id="btn" href="#" onclick="editSecond()"   class="easyui-linkbutton" >提交</a></li>
		</ul>
	</form:form>
</body>
</html>