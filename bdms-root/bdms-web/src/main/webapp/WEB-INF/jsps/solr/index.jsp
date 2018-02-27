<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DS-全文检索（JAVA）</title>
<jsp:include page="../admin/admincommonheader.jsp"></jsp:include>
</head>
<script type="text/javascript">
	$(function() {
		$('#Searchbox').textbox({
			buttonText : '全文检索',
			buttonIcon : 'icon-search',
			iconAlign : 'left',
			onClickButton : function() {
				$('#SearchForm').submit();
			}
		});
	});
</script>
<body>
	<form:form id="SearchForm" name="SearchForm" action="${APP_PATH}solr/data">
		<div
			style="margin-left: auto; margin-right: auto; margin-top: 200px; width: 600px">
			<input class="easyui-textbox" id="Searchbox" name="keyword"
				style="width: 600px; line-height: 40px; height: 40px; font-size: 18px">
		</div>
	</form:form>
</body>
</html>