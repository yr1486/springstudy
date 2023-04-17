package com.gdu.app04.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.app04.domain.BoardDTO;

@Repository // DAO가 사용하는 @Component 이다.
			// Spring Container에 Bean이 등록될 때,
			// Singleton으로 등록되기 때문에 별도의 Singleton Pattern 코드를 작성할 필요가 없다.
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
			if(rs != null) rs.
			close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}catch (Exception e) {
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
		list.add(new BoardDTO(1, "제목", "내용", "작성자", "작성일", "수정일"));
		
		try {
			con = getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
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
			con = getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
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
	
	public int insetBoard(BoardDTO board) {
		int result = 0;
		try {
			con = getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
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
			con = getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
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
			con = getConnection(); // 시작은 커넥션 모든 DAO 메소드에서 변치 않음
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























