<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		Header
		<!--  JSP include(Action) 동적 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
	</div>
	<div id="content">
		Content
		<!--  JSP include(Action) 동적 -->
		<jsp:include page="/WEB-INF/views/include/content.jsp" />
	</div>
	<div id="footer">
		Footer
		<!--  JSP include(Directive) 정적 -->
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</div>
</body>
</html>