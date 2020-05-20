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
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
	</div>
	<div id="content" align="center">
		<hr width="80%">
		<h2>게시물 상세 정보</h2>
		<hr width="80%">

		<table width="80%" border="1">
			<tr>
				<th width="25%">번호</th>
				<td>${param.no }</td>
			</tr>
			<tr>
				<th width="25%">제목</th>
				<td>${board.title }</td>
			</tr>
			<tr>
				<th width="25%">내용</th>
				<td>${board.content }</td>
			</tr>
			<tr>
				<th width="25%">조회수</th>
				<td>${board.view_cnt }</td>
			</tr>
			<tr>
				<th width="25%">작성일</th>
				<td>${board.reg_date }</td>
			</tr>
			<tr>
				<th width="25%">첨부파일</th>
				<td>
					<c:forEach var = "file" items="${fileList }">
						<a target="blank" href="/jblog/upload/${file.fileSaveName }"
						 download="${file.fileOriName }">
						 	${file.fileOriName}
						</a>
						&nbsp; (${file.fileSize } byte)
						<br>
					</c:forEach>
				</td>
			</tr>
		</table>
		</div>		
	<div id="footer">
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</div>
</body>
</html>