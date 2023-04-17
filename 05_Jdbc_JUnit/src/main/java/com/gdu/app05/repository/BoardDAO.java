package com.gdu.app05.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	// jdbc 방식
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// private 메소드 - 1 (BoardDAO 클래스 내부에서만 사용)
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // ojdbc8.jar 메모리 로드
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GDJ61", "1111");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// private 메소드 - 2 (BoardDAO 클래스 내부에서만 사용)
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
