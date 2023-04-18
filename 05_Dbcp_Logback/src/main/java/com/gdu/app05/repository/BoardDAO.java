package com.gdu.app05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.gdu.app05.domain.BoardDTO;

// dbcp에서는!!!! JUnit 단위 테스트가 안된다. @Test  


// 오류 주의 : root-context.xml에 있으면 이거 먼저 읽어서 안됨!! 주의하기.

@Repository // DAO가 사용하는 @Component 이다.
			// Spring Container에 Bean이 등록될 때,
			// Singleton으로 등록되기 때문에 별도의 Singleton Pattern 코드를 작성할 필요가 없다.



/// 다오에서 @레파지토리 해준거라서. 

/*
서비스임플에서 
@Autowired 
private BoardDAO boardDAO;

하나로 뉴 안하고 쭉 쓸 수 있는거
메소드 만들때도 뉴뉴뉴뉴 안하고.
뉴뉴뉴뉴안할라고 싱글턴 쓴거
 * 
 *
 */


public class BoardDAO {
	
	// dbcp 방식(jdbc + DataSource)
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private DataSource dataSource;

	// BoardDAO 생성자(webapp/Meta-INF/context.xml에 작성한 <Resource> 태그 읽기)
	public BoardDAO() {
		// JNDI 방식 : <Resource> 태그의 name 속성으로 Resource를 읽어 들이는 방식
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61"); // context.xml에서
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// private 메소드 (BoardDAO 클래스 내부에서만 사용)
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close(); // 사용한 Connection을 dataSource에게 반납한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// DAO 메소드 (BoardServiceImpl 클래스에서 사용하는 메소드)
	// 컨트롤러는 서비스가져다쓰고 서비스는 DAO를 가져다 씀!!!
	// DAO 메소드명
	// 방법1. BoardServiceImpl의 메소드와 이름을 맞춤
	// 방법2. DB 친화적으로 새 이름을 부여 (이론상 권장)
	
	
	
	// 1. 목록
	public List<BoardDTO> selectBoardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			con = dataSource.getConnection(); // dataSource가 관리하는 Connection 8개 중 하나를 대여한다.
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) { 
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(board); // array리스트에 담기
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 자원반납
		}
		return list;
	} 
	
	
	
	// 2. 상세
	
	public BoardDTO selectBoardByNo(int board_no) {
		BoardDTO board = null;
		try {
			con = dataSource.getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery(); // 실행하는메소드
			
			if(rs.next()) { // 있으면
				board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 자원반납
		}
		return board;
		
	}
	
	// 삽입 수정 삭제 패턴이 같은거 이해하기.
	// 3. 삽입
	
	public int insertBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'),TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
			// 물음표3개니까
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 자원반납
		}
		return result;
	}
	
	
	// 4. 수정
	
	public int updateBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_AT = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 자원반납
		}
		return result;
	}
	
	
	// 5. 삭제
	
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = dataSource.getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
			sql ="DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(); // 자원반납
		}
		return result;
	}



}























