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
</script>
<body>
	<div style="color: #ccc; width: 400px; margin: 10px auto 0 auto; font-size: 12px;" >
		全文检索为你找到查找关键字<span style="color: red; font-weight: bold;">${keyword}</span>,总共查找到
	</div>
</body>
</html>