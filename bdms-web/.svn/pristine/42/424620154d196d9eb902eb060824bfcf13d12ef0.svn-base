<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一级编辑</title>
<jsp:include page="../../../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
//初始化form表
function initForm() {
	$('#editFirstForm').form({ 
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
	        		$.messager.alert('提示','一级编辑成功','info',function(){
	        			parent.$('#firstoperate').dialog('close');     //关闭父页面
		        		parent.$('#firstDataGrid').datagrid('reload');  //刷新父页面
	        		});
	        	}
	    }
	});
}

function editFirst() {
	$('#editFirstForm').submit();
}

$(function(){
	initForm();	
});

</script>
<body>
<%-- ${first } --%>
<form:form id="editFirstForm" name="editFirstForm"
		 modelAttribute="first" action="${APP_PATH}dams/score/area/firstedit"   >
	    <ul class="formul">
		 <li><input id="Id" class="easyui-validatebox" name="Id" data-options="required:true" 
			 missingMessage="Id" value="${first.id}"   type="hidden" /></li>
		 
		<li><label>区域Id：</label><input id="areaId" class="easyui-validatebox" name="areaId" data-options="required:true" 
			 missingMessage="区域Id" value="${first.areaId}"   type="text" /></li>
			 
		 <li><label>类型名称：</label><input id="typeName"  class="easyui-validatebox" data-options="required:true" name="typeName"
		     missingMessage="类型名称" value="${first.typeName}"   type="text" /></li>
		
		 <li><label>类型编码：</label><input id="typeCode"  class="easyui-validatebox" data-options="required:true" name="typeCode"
		     missingMessage="类型编码" value="${first.typeCode}"   type="text" /></li>
		  
		<li><label>权重：</label> <input id="weight" class="easyui-validatebox" data-options="required:true" name="weight"
			 missingMessage="权重" value="${first.weight}" type="text" /></li>
		<%-- 		 
	    <li><input id="stationName"  class="easyui-validatebox" data-options="required:false" name="stationName"
	         missingMessage="站点名" value="${first.stationName}"   type="hidden" /></li> --%>
	         
        <li> <input id="firstMark"  class="easyui-validatebox" data-options="required:false" name="firstMark"
             missingMessage="一级总分" value="${first.firstMark}"   type="hidden" /></li>  
		 
		 <li><label>告警数量：</label><input id="alarmCount"  class="easyui-validatebox" data-options="required:true" name="alarmCount"
		    missingMessage="告警数量" value="${first.alarmCount}"   type="text" /></li>
		 
		 <li><label>告警级别：</label><input id="alarmLevel"  class="easyui-validatebox" data-options="required:true" name="alarmLevel"
		    missingMessage="告警级别" value="${first.alarmLevel}"   type="text" /></li>
		
		<li><a id="btn" href="#" onclick="editFirst()"   class="easyui-linkbutton" >提交</a></li>
		</ul>
	</form:form>
</body>
</html>