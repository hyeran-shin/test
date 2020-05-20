package com.bit.util;

import java.sql.Connection;
import java.sql.DriverManager;

// 사용 시에만 받아오도록 Factory 정의
public class ConnectionFactory {
	
	public Connection getConnection() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jblog?serverTimezone=UTC ";
			String user = "root";
			String pw = "root";
			con = DriverManager.getConnection(url,user,pw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
