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
		<hr width="80%" />
		<h2>로그인</h2>
		<hr width="80%" />
		<form action="/jblog/login.do" method="post" >
			<table width="40%" >
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" size=20 /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pw" /></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="로그인">
			
		</form>
	</div>
	<div id="footer">
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</div>
</body>
</html>