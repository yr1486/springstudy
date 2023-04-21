package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app07.domain.BoardDTO;

public interface BoardService {
	// 서비스는 다오한테 값을주고. 값을 받아온다.
	// 서비스는 다오한테 받아서 그대로 컨트롤러한테 준다.
	// jsp가 컨트롤러로 넘겨주는거. 방법은 3가지. @객체, http, 리퀘스트파람, 그럼 컨트롤러는 서비스로 멀주냐. 여기서는 리퀘스트를 주는거지
	public List<BoardDTO> getBoardList();
		//서비스이름: 다오랑 맞추거나, 새로만들거나 하면됨. 늘 새로만들엇고 담주부터 맞출거임
	public BoardDTO getBoardByNo(HttpServletRequest request);
	public int addBoard(HttpServletRequest request); // 리케스트로 받아온다고 생각하기. => 리케스트를 보드로 바꾸는 작업을 할 수 있어. 디티오로 만든 다음에, 다오측으로 전달. 서비스는 로직을 짜는곳
	public int modifyBoard(HttpServletRequest request); // 리케스트로 받고. 꺼내서. 보드로 만들고.(디티오) 다오로 전달 // 리퀘스트는 받고.꺼내서 만들고 전달. 알기
	public int removeBoard(HttpServletRequest request);
	public void testTx();
}
