package com.gdu.app06.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; // Connection, PreparedStatement, ResultSet을 사용하는스프링 클래스
	private String sql;
	

	
	// 1. 목록
	
	public List<BoardDTO> selectBoardList() {
		sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD ORDER BY BOARD_NO DESC";
		List<BoardDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));  // 셀렉트돌릴때 쓰는 메소드 .쿼리
	
		return list; 
	}
	
	// 2. 상세
	public BoardDTO selectBoardByNo(final int BOARD_NO) { // 매개변수의 변조를 막기 위해서 final 처리한다(예전에 매개변수 해킹 시도가 있었다)
		sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?";
		BoardDTO board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), BOARD_NO); // BOARD_NO 에 알아서 ?가 들어간다.
		return board;
		
	}
	
	// 3. 삽입
	// 삽입 수정 삭제 패턴이 같은거 이해하기
	
	public int insertBoard(final BoardDTO BOARD) {
		sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'),TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARD.getTitle());
				ps.setString(2, BOARD.getContent());
				ps.setString(3, BOARD.getWriter());
				// PreparedStatement는 ?에 1,2,3번 넣어주세요 할수 있게 해주는 애
			}
		});
		return result;
	}
	
	// 4. 수정
	
	public int updateBoard(final BoardDTO BOARD) {
		sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_AT = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARD.getTitle());
				ps.setString(2, BOARD.getContent());
				ps.setInt(3, BOARD.getBoard_no());
				// PreparedStatement는 ?에 1,2,3번 넣어주세요 할수 있게 해주는 애
			}
		});
		return result;
	}
	
	// 5. 삭제
	
	public int deleteBoard(final int BOARD_NO) {
		sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, BOARD_NO);
			}
		});
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

