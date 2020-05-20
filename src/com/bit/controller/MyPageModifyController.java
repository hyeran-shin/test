package com.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.dao.MemberDAO;
import com.bit.vo.MemberVO;

public class MyPageModifyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 업데이트
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("pw");
		String email_id= request.getParameter("email_id");
		 
		String email_domain = "";
		if((email_domain = request.getParameter("email_domain1"))== "" ){
			email_domain = request.getParameter("email_domain2");
		}
		
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String post = request.getParameter("post");
		String basic_addr = request.getParameter("basic_addr");
		String detail_addr = request.getParameter("detail_addr");
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setName(name);
		member.setPassword(password);
		member.setEmail_id(email_id);
		member.setEmail_domain(email_domain);
		member.setTel1(tel1);
		member.setTel2(tel2);
		member.setTel3(tel3);
		member.setPost(post);
		member.setBasic_addr(basic_addr);
		member.setDetail_addr(detail_addr);
		
		
		MemberDAO dao = new MemberDAO();
		int updateResult = dao.memberUpdate(member); //-1 실패
		String msg="";
		String url="";
			
		if(updateResult != -1) { // update 성공
			msg="수정 완료 하였습니다.";
			url="/jblog/myPage.do?id="+id;
		}else {
			msg="수정 실패 하였습니다.";
			url="/jblog/myPageModifyForm.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/WEB-INF/views/mypage/myPageModify.jsp";
	}

}
