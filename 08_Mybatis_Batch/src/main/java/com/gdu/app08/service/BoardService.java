package com.gdu.app08.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app08.domain.BoardDTO;


public interface BoardService {
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardByNo(HttpServletRequest request);
	public void addBoard(HttpServletRequest request, HttpServletResponse response);
	public void modifyBoard(HttpServletRequest request, HttpServletResponse response);
	public void removeBoard(HttpServletRequest request, HttpServletResponse response);
	public void removeBoardList(HttpServletRequest request, HttpServletResponse response);
	public void getBoardCount();
}
	// int > void 로 바꿈
	// 컨트롤러는 더이상 삽입수정삭제의 결과를 모른다.
	// 그래서 서비스단에서 직접 이동시켜줘야한다. ==> 직접응답할수 있게 response를 넘겨줘야함.

