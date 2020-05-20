<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
	</div>
	<div id="content" align="center">
		<br><br>
		<h2>게시글 등록을 완료하였습니다.</h2>
		<br>
		<a href="/jblog/list.do">게시판으로 이동</a>
	</div>
	<div id="footer">
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</div>
</body>
</html>