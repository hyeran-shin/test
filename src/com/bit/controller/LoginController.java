package com.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.dao.LoginDAO;
import com.bit.vo.LoginVO;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 1. 로그인 시 넘어온 파라미터 정보를 얻겠다.
		String id = request.getParameter("id");
		String password = request.getParameter("pw");

		// 2. LoginVO에 파라미터 설정
		LoginVO login = new LoginVO();
		login.setId(id);
		login.setPassword(password);

		// 3. 데이터베이스의 로그인 과정 수행 (DAO)
		// -> id, password를 전달하여
		// 성공이라면, name과 type까지 받아오겠다.
		// 실패하면, null
		// *데이터베이스의 해당 회원 정보가 있는지 판단하겠다.

		LoginDAO dao = new LoginDAO();
		LoginVO user = dao.login(login);

		// 4. 성공 시 로그인 정보 세션 등록
		String msg = ""; // 전달할 메시지
		String url = ""; // 이동할 경로

		if (user != null) { // 성공이라면,
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // 세션 등록

			// 유저 타입 판단
			switch (user.getType()) {
			case "S":
				msg = user.getName() + " 관리자님 환영합니다.";
				break;
			case "U":
				msg = user.getName() + " 회원님 환영합니다.";
				break;
			}
			url = request.getContextPath(); // "/jblog" (메인 페이지)
		} else { // 실패라면
			msg= "ID 혹은 Password가 잘못 되었습니다.";
			url = "/jblog/loginForm.do";
		}

		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/WEB-INF/views/login/login.jsp";
	}

}



