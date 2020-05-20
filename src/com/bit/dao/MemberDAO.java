package com.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bit.util.ConnectionFactory;
import com.bit.util.JDBCClose;
import com.bit.vo.MemberVO;

// Database Access Object
public class MemberDAO {
	private MemberVO member = new MemberVO();

	// 전체 회원 조회
	public List<MemberVO> selectAllMember() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MemberVO> list = new ArrayList<>();

		try {
			con = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT id, name, password, tel1, tel2, tel3,  ");
			sql.append("  		type, reg_date ");
			sql.append("	FROM member ");

			pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setTel1(rs.getString("tel1"));
				member.setTel2(rs.getString("tel2"));
				member.setTel3(rs.getString("tel3"));
				member.setType(rs.getString("type"));
				member.setReg_date(rs.getString("reg_date"));
				list.add(member);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, pstmt);
		}
		return list;
	}

	// 유저 상세 보기(id로 조회)
	public MemberVO selectByID(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT *  FROM member ");
			sql.append(" WHERE id = ?  ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			member.setId(id); // 전달된 아이디
			member.setName(rs.getString("name"));
			member.setPassword(rs.getString("password"));
			member.setEmail_id(rs.getString("email_id"));
			member.setEmail_domain(rs.getString("email_domain"));
			member.setTel1(rs.getString("tel1"));
			member.setTel2(rs.getString("tel2"));
			member.setTel3(rs.getString("tel3"));
			member.setPost(rs.getString("post"));
			member.setBasic_addr(rs.getString("basic_addr"));
			member.setDetail_addr(rs.getString("detail_addr"));
			member.setType(rs.getString("type"));
			member.setReg_date(rs.getString("reg_date"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, pstmt);
		}

		return member;
	}

	// 마이페이지 수정 업데이트
	public int memberUpdate(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			con = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			// update member set name="이름이름이름" where id="ididid";

			sql.append("  UPDATE member  ");
			sql.append("  set id=?, name=?, password=?, email_id=?, email_domain=?,  ");
			sql.append("  tel1=?, tel2=?, tel3=?, post=?, basic_addr=?, detail_addr=? ");
			sql.append("  where id = ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail_id());
			pstmt.setString(5, member.getEmail_domain());
			pstmt.setString(6, member.getTel1());
			pstmt.setString(7, member.getTel2());
			pstmt.setString(8, member.getTel3());
			pstmt.setString(9, member.getPost());
			pstmt.setString(10, member.getBasic_addr());
			pstmt.setString(11, member.getDetail_addr());
			pstmt.setString(12, member.getId());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, pstmt);
		}

		return result;
	}

}
