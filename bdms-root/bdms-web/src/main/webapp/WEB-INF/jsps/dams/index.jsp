<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="damscommonheader.jsp"></jsp:include>
<title>上海市人流密集场所安全风险监测</title>
<script type="text/javascript">
	function iframecontentSrc(url) {
		$("#iframecontent", parent.document.body).attr("src",url);
	}
</script>
</head>
<body>
	<div id="top">
			<ul>
				<li>
					<a href="#" onclick="iframecontentSrc('${APP_PATH}dams/elecfence/sdayef')">日进出站站</a>
				</li>
				<li>
					<a href="#" onclick="iframecontentSrc('${APP_PATH}dams/elecfence/smonthef')">月日进出站站</a>
				</li>
				<!--  
				<li>
					<a href="#" onclick="iframecontentSrc('${APP_PATH}dams/metro')">轨交分析</a>
				</li>
				<li>
					<a href="#" onclick="iframecontentSrc('${APP_PATH}')">围栏分析</a>
				</li>
				<li>
					<a href="#" onclick="iframecontentSrc('${APP_PATH}')">热点分析</a>
				</li>
				-->
			</ul>
	</div>
	<div id="content">
		<iframe id="iframecontent" width="100%" height="100%" scrolling="no"
			frameborder="0" src="${APP_PATH}dams/main"> 
		</iframe>
	</div>
	<div id="foot">
		上海市公安局
	</div>
</body>
</html>