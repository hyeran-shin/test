<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bit.util.JDBCClose" %>
<%@ page import="com.bit.util.ConnectionFactory" %>
<!-- 
	jblog Database DDL Script
	
	[Table]
	CREATE TABLE member(
		id 			 	 VARCHAR(20),
		name		 	 VARCHAR(10),
		password	 	 VARCHAR(20),
		email_id 	 	 VARCHAR(20),
		email_domain 	 VARCHAR(20),
		tel1		 	 VARCHAR(3),
		tel2 		 	 VARCHAR(4),
		tel3		 	 VARCHAR(4),
		post 		 	 VARCHAR(5),
		basic_addr	 	 VARCHAR(60),
		detail_addr  	 VARCHAR(60),
		type 		 	 VARCHAR(1) NOT NULL DEFAULT 'U',
		reg_date 	 	 TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);
 -->

<%
	// Parameter
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String password = request.getParameter("pw");
	String email_id= request.getParameter("email_id");
	
	/*
		// 조건에 따라 다른 데이터를 넣기 위해 빈 문자열로 초기화하는 경우
			-> 빈 문자열도 존재하는 데이터
			-> 데이터가 없다면 null 값을 가진다. 
		reqeust.getParameter("name")
			:영역에 name이 존재하지 않으면 null 반환
		*데이터 유무 판단 시 빈 문자열("") 과 null 판단을 동시에 수행
			-> if(data != null && data != ""){ ... }
		*데이터가 나오지 않거나 잘못되어 들어가는 경우, 추적이 쉽지 않다.
			-> 로그를 남기는게 가장 좋지만, 중간 추렭문으로 흐름 파악
	 */
	 
	String email_domain = "";
// 	if((email_domain = request.getParameter("email_domain1"))== null ){
	if((email_domain = request.getParameter("email_domain1"))== "" ){
		email_domain = request.getParameter("email_domain2");
	}
	
	String tel1 = request.getParameter("tel1");
	String tel2 = request.getParameter("tel2");
	String tel3 = request.getParameter("tel3");
	String post = request.getParameter("post");
	String basic_addr = request.getParameter("basic_addr");
	String detail_addr = request.getParameter("detail_addr");
	
	// Database Access
	Connection con = new ConnectionFactory().getConnection();
	StringBuilder sql = new StringBuilder();
	sql.append("  INSERT INTO member( ");
	sql.append("  id, name, password, email_id, email_domain, ");
	sql.append("  tel1, tel2, tel3, post, basic_addr, detail_addr ) ");
	sql.append("  VALUES(?,?,?,?,?,?,?,?,?,?,?)  ");
	
	PreparedStatement pstmt = con.prepareStatement(sql.toString());
	
	pstmt.setString(1,id);
	pstmt.setString(2,name);
	pstmt.setString(3,password);
	pstmt.setString(4,email_id);
	pstmt.setString(5,email_domain);
	pstmt.setString(6,tel1);
	pstmt.setString(7,tel2);
	pstmt.setString(8,tel3);
	pstmt.setString(9,post);
	pstmt.setString(10,basic_addr);
	pstmt.setString(11,detail_addr);
	
	
	pstmt.executeUpdate();
	
	JDBCClose.close(con,pstmt);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<br><br>
		<h2>회원가입에 성공하였습니다.</h2>
		<a href="/jblog/loginForm.do">로그인 하기</a>
	</div>
</body>
</html>