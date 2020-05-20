<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	// JavaScript Function (함수)
	//  -> 글 등록 버튼 클릭 시 호출
	function moveToWriteForm(){
		location.href="/jblog/writeForm.do";
		// JavaScript 페이지 이동 방식
	}
	
	function doAction(boardNo){ // 매개변수 : boardNo, 타입은 없고 매개변수. 
		<c:choose>
		<c:when test="${not empty user}">
			location.href="/jblog/detail.do?type=list&no=" + boardNo;
		</c:when>
		<c:otherwise>
			if(confirm('로그인 후 사용 가능합니다. \n로그인 페이지로 이동하시겠습니까?')) //확인 : 참,
				location.href="/jblog/loginForm.do";
		</c:otherwise>
		</c:choose> 
	}
	
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
	</div>
	<div id="content" align="center">
		<hr width="80%">
		<h2>게시판 목록</h2>
		<hr width="80%">
		<br>


		<table width="60%" border="1">
			<tr>
				<th width="8%">번호</th>
				<th width="30%">제목</th>
				<th width="15%">작성자</th>
				<th width="25%">작성일</th>
				<th width="8%">조회수</th>
			</tr>
			<c:forEach var="board" items="${list }">
				<tr>
					<td align="center" >${board.no }</td>
					<td align="center">
						<a href="javascript:doAction('${ board.no }')">
							<b>${board.title}</b>
						</a>
					</td>
					<td align="center" >${board.writer }</td>
					<td align="center" >${board.reg_date }</td>
					<td align="center"> ${board.view_cnt }</td>
				</tr>
			</c:forEach>
		</table>
		<br>

		<c:if test="${ not empty user }">
			<input type="button" value="글 등록" onclick="moveToWriteForm()" />
		</c:if>

	</div>
	<div id="footer">
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</div>

</body>
</html>