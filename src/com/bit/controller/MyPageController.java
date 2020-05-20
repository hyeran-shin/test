package com.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.dao.MemberDAO;
import com.bit.vo.MemberVO;


public class MyPageController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		MemberDAO dao = new MemberDAO();
		MemberVO member = dao.selectByID(id);
		
		session.setAttribute("member", member);
		
		return "/WEB-INF/views/mypage/myPage.jsp";
	}

}
