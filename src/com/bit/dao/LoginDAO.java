package com.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bit.util.ConnectionFactory;
import com.bit.util.JDBCClose;
import com.bit.vo.LoginVO;

public class LoginDAO {
	
	public LoginVO login(LoginVO login) {
		Connection con = null;
		PreparedStatement pstmt = null;
		LoginVO user = null;
		
		try {
			con = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT id, password, name, type ");
			sql.append(" FROM member  ");
			sql.append(" WHERE id = ? AND password = ? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new LoginVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setType(rs.getString("type"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(con, pstmt);
		}
		
		return user;
	}
	
}
