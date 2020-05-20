<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.bit.dao.MemberDAO"%>
<%@ page import="com.bit.vo.MemberVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	/*
		MVC Model (Pattern)
			Model		- Java Bean(DAO, VO) + Service (Logic)
			View		- JSP (유저에게 보여줄 페이지)
			Controller	- Servlet  
			
		MVC Model 1
				: View, Service들을 JSP내에서 모두 처리하는 형태
			[장점]
				- JSP내에서 모두 처리했기에, 쉽게 작성이 가능
				- 구현이 용이
			[단점]
				- front-end와 back-end의 구현 내용이 한 곳에 모여있다.
					-> 코드 구성이 복잡하다.
				- 유지보수가 쉽지 않다.
				
		MVC Model 2
				: 기존 모델(Model 1)과 달리 View, Service이 분리되는 형태
				  JSP 끼리의 요청을 처리하는 것과 달리,
				    요청을 처리할 Servlet(Controller)을 분리하여 구성
				    
				    역할에 맞게 쪼갠다?
	*/
		

	// DAO를 통해 데이터를 받아와, 출력하기 위해 영역에 등록 
	MemberDAO dao = new MemberDAO();
	List<MemberVO> list = dao.selectAllMember();
	pageContext.setAttribute("list", list);
%>
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
	<div id="content">

		<hr width="80%">
		<h2>회원 목록</h2>
		<hr width="80%">
		<br>

		<table width="80%" border="1">
			<tr>
				<th width="15%">아이디</th>
				<th width="10%">이름</th>
				<th width="15%">비밀번호</th>
				<th width="20%">전화번호</th>
				<th width="5%">등급</th>
				<th width="15%">가입일</th>
			</tr>

			<c:forEach var="member" items="${list}">
				<tr>
					<td align="center">${member.id}</td>
					<td align="center">
						<a href="/jblog/jsp/member/userDetail.jsp?id=${member.id}"> 
							<c:out value="${member.name}" />
						</a>
					</td>
					<td align="center">${member.password}</td>
					<td align="center">${member.tel1}-${member.tel2}-${member.tel3}</td>
					<td align="center">${member.type}</td>
					<td align="center">${member.reg_date}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div id="footer">
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</div>
</body>
</html>







