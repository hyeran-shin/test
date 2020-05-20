package com.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit.util.ConnectionPool;
import com.bit.vo.BoardVO;
import com.bit.vo.FileVO;

/*
 * jblog Database DDL Script
 * 
 * [Table] - 게시글, 파일
  	CREATE TABLE board(
  		no			 INT 			 AUTO_INCREMENT,
  		title		 VARCHAR(200) 	 NOT NULL,
  		writer		 VARCHAR(40) 	 NOT NULL,
  		content		 VARCHAR(4000),
  		reg_date	 TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  		view_cnt	 INT DEFAULT 0,
  		PRIMARY KEY  (no) 
    );
    
    CREATE TABLE board_file(
    	no				 INT	 AUTO_INCREMENT,
    	board_no		 INT,
    	file_ori_name	 VARCHAR(200),
    	file_save_name	 VARCHAR(200),
    	file_size 		 VARCHAR(512),
    	PRIMARY KEY(no)
    );
    
 * 
*/

public class BoardDAO2 {
	private BoardVO board = new BoardVO();
	
	
	/*
	 * 모든 게시글 조회
	 */

	public List<BoardVO> selectAllBoard() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<BoardVO> list = new ArrayList<>();

		try {
			// connectionFactory 말고 다른 거 사용
			con = ConnectionPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT no, title, writer, reg_date, view_cnt ");
			sql.append("   FROM board ");
			sql.append("  ORDER BY no DESC  ");
			pstmt = con.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();

				// 데이터베이스 조회 결과 가져오기
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String reg_date = rs.getString("reg_date");
				int view_cnt = rs.getInt("view_cnt");

				// 리스트에 담아 전달하기 위한 BoardVO
				board.setNo(no);
				board.setTitle(title);
				board.setWriter(writer);
				board.setReg_date(reg_date);
				board.setView_cnt(view_cnt);

				// 리스트에 저장
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			ConnectionPool.close(con);
		}

		return list;
	}

	/*
	 * 게시글 등록 시 사용할 번호 가져오기 (Sequence)
	 */
	public int selectNo() {
		Connection con = null;
		PreparedStatement pstmt = null;
		int no = 0;

		try {
			con = ConnectionPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT max(no) + 1   ");
			sql.append("  FROM board  ");

			pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			no = rs.getInt(1);

		} catch (Exception e) {

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionPool.close(con);
		}

		return no;
	}

	/*
	 * 게시글 저장
	 */
	public void insert(BoardVO board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO board(no, title, writer, content)  ");
			sql.append(" VALUES (?,?,?,?)  ");

			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, board.getNo());
			pstmt.setString(index++, board.getTitle());
			pstmt.setString(index++, board.getWriter());
			pstmt.setString(index, board.getContent());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(con);
		}
	}

	/*
	 * 첨부파일 저장
	 */
	public void insertFile(FileVO file) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO board_file (no, board_no, ");
			sql.append("	file_ori_name, file_save_name, file_size) ");
			sql.append(" VALUES(?,?,?,?,?)  ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, file.getNo());
			pstmt.setInt(2, file.getBoradNo());
			pstmt.setString(3, file.getFileOriName());
			pstmt.setString(4, file.getFileSaveName());
			pstmt.setInt(5, file.getFileSize());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionPool.close(con);
		}
	}

	/*
	 * 조회수 증가
	 */
	public void updateViewCnt(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE board ");
			sql.append(" SET view_cnt = view_cnt+1");
			sql.append(" WHERE no = ?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionPool.close(con);
		}
	}

	/*
	 * 특정 게시글 정보 조회(게시글 번호로 하나의 게시글 조회)
	 */
	public BoardVO selectByNo(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT no, title, writer, content, ");
			sql.append("  		view_cnt, reg_date ");
			sql.append(" 	FROM board ");
			sql.append("  WHERE no = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			board.setNo(no);
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setContent(rs.getString("content"));
			board.setView_cnt(rs.getInt("view_cnt"));
			board.setReg_date(rs.getString("reg_date"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionPool.close(con);
		}
		return board;
	}
	
	/*
	 * 첨부파일 정보 조회
	 * 		-> 두 테이블간 연결의 역할을 하는 boardNo 존재
	 * 			board -> no
	 * 			board_file -> boardNo	
	*/
	public List<FileVO> selectFileByBoardNo(int boardNo){
		List<FileVO> fileList = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT no, file_ori_name,file_save_name, file_size ");
			sql.append(" 	FROM board_file ");
			sql.append("  WHERE board_no = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				FileVO file = new FileVO();
				file.setNo(rs.getInt("no"));
				file.setFileOriName(rs.getString("file_ori_name"));
				file.setFileSaveName(rs.getString("file_save_name"));
				file.setFileSize(rs.getShort("file_size"));
				fileList.add(file);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ConnectionPool.close(con);
		}
		
		
		return fileList;
	}
}




