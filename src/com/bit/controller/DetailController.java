package com.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.dao.BoardDAO;
import com.bit.vo.BoardVO;
import com.bit.vo.FileVO;

public class DetailController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * 1. 조회수 증가
		 * 2. 게시글 정보 조회
		 * 3. 첨부파일 정보 조회
		 * 
		*/		
		int no = Integer.parseInt(request.getParameter("no"));
		String type = request.getParameter("type");
		BoardDAO dao = new BoardDAO();
		// 조회수 증가
		if(type !=null && type.equals("list")) {
			dao.updateViewCnt(no);
		}
		// 게시글 정보 조회
		BoardVO board = dao.selectByNo(no);
		
		//첨부파일 정보 조회
		List<FileVO> fileList = dao.selectFileByBoardNo(no);
		
		request.setAttribute("board", board);
		request.setAttribute("fileList", fileList);
		
		return "/WEB-INF/views/board/detail.jsp";
	}

}





