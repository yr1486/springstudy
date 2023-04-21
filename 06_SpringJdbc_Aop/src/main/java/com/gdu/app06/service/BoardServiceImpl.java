package com.gdu.app06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	// 목록
	@Override
	public List<BoardDTO> getBoardList() {
		
		return  boardDAO.selectBoardList() ;
	}

	// 상세
	@Override
	public BoardDTO getBoardByNo(int board_no) {
		
		return boardDAO.selectBoardByNo(board_no); 
	}

	// 삽입
	@Override
	public int addBoard(BoardDTO board) {
		
		return boardDAO.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		
		return boardDAO.updateBoard(board);
	}
	
	@Override
	public int removeBoard(int board_no) {
		
		return boardDAO.deleteBoard(board_no);
		
	}
	
	// AOP를 활용한 트랜잭션 처리 테스트
	@Override
	public void testTx() {

		// 2개 이상의 삽입, 수정, 삭제가 하나의 서비스에서 진행되는 경우에 트랜젝션 처리가 필요하다.
		// 1개는 트랜잭션이 필요없다는 거임
		
		// 성공하는 작업
		boardDAO.insertBoard(new BoardDTO(0, "트랜잭션제목", "트랜잭션내용", "트랜잭션작성자", null, null));
	
		// 실패하는 작업
		boardDAO.insertBoard(new BoardDTO()); // TITLE칼럼은 NOT NULL이기 떄문에 Exception이 발생한다.
	
		// 트랜잭션 처리가 된다면 모든 insert가 실패해야 한다.
		// 위에 성공하는 작업이 성공했지만, 하나라도 실패했기때문에 결과는 실패로.(=> 성공도 안들어간거임)
		// sqldeveloper에 테이블에서 보면됨. 열에 안들어가 있어야햇어.
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
