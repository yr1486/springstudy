package com.gdu.notice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.notice.domain.NoticeDTO;

@Repository
public class NoticeDAO { // dao는 DB와 연결해주는 역할. 담기만 해.

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// GDJ61 계정에 접속할 수 있는 Connection 객체를 반환하시오. 예외 처리(try - catch) 없이 진행하시오.
	private Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GDJ61", "1111");
	}
	
	// DB 처리 시 사용한 Connection, PreparedStatement, Result 객체를 close 처리하시오.
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 모든 공지사항을 NOTICE_NO의 내림차순으로 정렬하여 반환하시오.
	
	/*
	public List<NoticeDTO> getNoticeList() {
		 List<NoticeDTO> list = new ArrayList<NoticeDTO>(); // 어레이리스트인 이유 : 셀렉트문을 돌리면 여러행이 나오기떄문에. 얘를 배열로 가지고 와야하니까. 어레이인거야.
		 
		 try {	  
		  con = getConnection();
		  sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT FROM NOTICE ORDER BY NOTICE_NO DESC";
		  ps = con.prepareStatement(sql);
		  rs = ps.executeQuery();
		  while(rs.next()) {
	      NoticeDTO notice =  new NoticeDTO(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4));
	      list.add(notice);
	      
		  }
		  }catch (Exception e) {
			e.printStackTrace();
		  }finally {
			  close();
		  }
		 return list;
	
	}
	*/
	
	public List<NoticeDTO> getNoticeList() { // 리스트는. 많은 정보를 담고 있잖아. 사진도 있고, 테이블도 있고 이런 애들을. 배열에 저장해야하니까. 어레이 리스트를 썼어.
		List<NoticeDTO> list = new ArrayList<NoticeDTO>(); // 여기 맨위에 선언한다는 건. 얘의 값을. 반환하겠다는 거지.트라이캐치안은 한번쓰고 버릴거니까. 얜 밖에 있으니까. 또 쓸 수 있자나.
		
		try {
			con = getConnection();
			// 연결메소드 호출
			sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT FROM NOTICE";
			// 쿼리문 담기
			ps = con.prepareStatement(sql);
			// 준비된 쿼리문 연결해줘.
			rs = ps.executeQuery();
			// 실행해줘.
			while(rs.next()) {
				// 만약 실행된 커리문에 다음이 있다면,
				NoticeDTO notice = new NoticeDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				list.add(notice);
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
		
	
	}
	
	// 전달 받은 공지번호(notice_no)를 가진 공지사항을 반환하시오. 공지번호가 일치하는 공지사항이 없다면 null을 반환하시오.
	//상세보기
	public NoticeDTO getNotice(int notice_no) { // <-매개변수(int notice_no)는 쓰려고 선언해준거야. 그러니까 메소드 안에서 . 무조건 사용해야해.
		
		NoticeDTO notice = null;
		
		try {
			
			
			con = getConnection();
			sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT FROM NOTICE WHERE NOTICE_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, notice_no);
			rs = ps.executeQuery(); // 셀렉트쿼리문은 executeQuery 짝꿍인거야. 만약 쿼리가 insert면 executeUpdate
			
			if(rs.next()) {
				
				notice = new NoticeDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return notice;
	}
	
	// 전달된 NoticeDTO 객체의 값을 이용해서 NOTICE 테이블에 데이터를 삽입하시오. 삽입 결과를 반환하시오.
	public int addNotice(NoticeDTO notice) {
		int result = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, notice.getGubun());
			ps.setString(2, notice.getTitle());
			ps.setString(3, notice.getContent());
			
			result = ps.executeUpdate();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	
		return result;
		
	}
	
	// 전달된 NoticeDTO 객체의 값을 이용해서 NOTICE 테이블에 데이터를 수정하시오. 수정 결과를 반환하시오.
	public int modifyNotice(NoticeDTO notice) {
		int result = 0;
		try {
			con = getConnection();
			sql = "UPDATE NOTICE SET GUBUN = ?, TITLE = ?, CONTENT = ? WHERE NOTICE_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, notice.getGubun());
			ps.setString(2, notice.getTitle());
			ps.setString(3, notice.getContent());
			ps.setInt(4, notice.getNotice_no());
			result = ps.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	
		
		
		
		return result;
	}

	// 전달 받은 공지번호(notice_no)를 가진 공지사항을 삭제하시오. 삭제 결과를 반환하시오.
	public int removeNotice(int notice_no) { // pk
		int result = 0;
		
		try {
			
			con = getConnection();
			sql ="delete from notice where notice_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, notice_no);
			
			result = ps.executeUpdate();
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	
		return result;
	}
	
}
