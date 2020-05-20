package com.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.dao.BoardDAO;
import com.bit.vo.BoardVO;

public class ListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//Model
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.selectAllBoard(); //DB에서 모든 게시글 정보를 가져와,
		
		request.setAttribute("list", list); // request 영역에 등록 
		//forward 방식이기에 request 영역 공유 
		
		
		return "/WEB-INF/views/board/list.jsp";
	}

}
