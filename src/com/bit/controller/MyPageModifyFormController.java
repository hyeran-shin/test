package com.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyPageModifyFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		MemberVO vo = (MemberVO) request.getAttribute("member");
//		System.out.println("vovovo : " + vo.getId());
		return "/WEB-INF/views/mypage/myPageModifyForm.jsp";
	}

}
