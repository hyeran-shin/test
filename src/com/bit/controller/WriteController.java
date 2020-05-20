package com.bit.controller;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.dao.BoardDAO;
import com.bit.util.MyFileRenamePolicy;
import com.bit.vo.BoardVO;
import com.bit.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;

public class WriteController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * 게시글 등록 (첨부파일)
		 * 		Database에는 파일 경로(파일명)을 저장,
		 * 		다른 물리적 저장소(하드디스크)에 실제 파일을 저장하여,
		 * 		Server는 DB로부터 경로를 받아 실제 저장소에 접근
		 * 
		*/
		
		String saveFolder
				= "C:\\Users\\qweqwe\\Desktop\\web-workspace\\jblog\\WebContent\\upload";
		// 절대 경로 지정(실제 파일을 저장할 위치)
		// "\" 문자 하나는 Escapte Sequence로 인식! (자바 컴파일러)
		//  -> \ 문자열 활용하기 위해 \\로 표현
		
		/*
		 *  MultipartRequest
		 *  	- HTTP 방식에서 파일 및 데이터를 전송하기 위해 사용 (업로드 구현)
		 *  	- 파일에 대한 내용을 알기 위해 사용
		 *  
		 *  [Parameter] 매개변수
		 *  	1. 요청 객체
		 *  	2. 저장 경로
		 *  	3. 파일 크기 규약
		 *  	4. 파일의 인코딩
		 *  	5. 동명의 파일명을 바꿔주는 rename 정책
		 *  		A -> cat.jpg 저장
		 *  		B -> cat.jpg 저장
		 *  		 이름 충돌! (저장 시 이름을 바꾸겠다.)
		 *  	
		*/
		
		
		MultipartRequest multi = new MultipartRequest(
				request, saveFolder, 1024 * 1024 * 3, 
				"utf-8", new MyFileRenamePolicy()
				);
		/**
		 * 게시글 저장
		 */

		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		int boardNo=dao.selectNo();
		
		BoardVO board = new BoardVO();
		board.setNo(boardNo);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		dao.insert(board);
		
		/*		
		 * 첨부파일 저장		
		*/	
		@SuppressWarnings("rawtypes")
		Enumeration files = multi.getFileNames();
		while(files.hasMoreElements()) {
			String fileName = (String) files.nextElement();
			File f = multi.getFile(fileName);
			
			// 다음과 같이 { } 생략 후 변수 선언을 한다면, 컴파일 에러
			//	-> 단, 블록 지정 시 컴파일 에러는 발생하지 않는다.
			//  -> 생략 : 단 한줄만 가능!
			
//			if(f!=null) int num = 10; // 컴파일 에러, 한 줄만 가능한데 지역 변수 선언 코드
//			if(f!=null) {
//				int num =10; // 컴파일 성공, 추가 코드가 작성될 가능성 
//			}
	
			
			if(f!=null) { //파일이 있다면,
				// 실제 파일 이름
				String fileOriName = multi.getOriginalFileName(fileName);
				// 저장 파일 이름
				String fileSaveName = multi.getFilesystemName(fileName);
				int fileSize = (int)f.length(); //원래 long, 지금 int로 사용
				
				FileVO file = new FileVO(); 
				file.setBoradNo(boardNo);
				file.setFileOriName(fileOriName);
				file.setFileSaveName(fileSaveName);
				file.setFileSize(fileSize);
				
				dao.insertFile(file);
			}
		}
		return "/WEB-INF/views/board/write.jsp";
	}

}





