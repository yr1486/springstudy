package com.gdu.app01.xml04;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	// field
	private String driver;
	private String url;
	private String user;
	private String password;
	
	// default constructor (기본생성자. 안만들어도됨)
	
	// method(getter, setter, getConnection)

	public Connection getConnection() {
		Connection con = null; // 외부 입출력 가능하게 하려면. 트라이캐치.
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle 접속 완료되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
