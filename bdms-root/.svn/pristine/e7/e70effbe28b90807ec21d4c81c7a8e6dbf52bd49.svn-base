<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="admincommonheader.jsp"></jsp:include>
<title>大数据管理平台</title>
</head>
<script type="text/javascript">
	function addtab(title, url) {
		$('#contentTab').tabs('add', {
			title : title,
			content : '<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%; height:100%;"></iframe>',
			closable : true,
			
		});
	}

	
</script>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'菜单'" href="${APP_PATH}menu"  style="width:200px;"></div>
	<div data-options="region:'east',split:true,collapsed:true,title:'内容'" style="width:300px;">内容</div>
	
	<div id="contentTab" data-options="region:'center'" class="easyui-tabs">
	    <div title="主页" data-options="closable : true"  > 
	    	<iframe scrolling='no' frameborder='0'  src='${APP_PATH}' style='width:100%; height:100%;'></iframe>
	    </div>
    </div>
    
</body>
</html>