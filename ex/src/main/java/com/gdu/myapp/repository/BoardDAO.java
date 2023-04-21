package com.gdu.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.myapp.domain.BoardDTO;

@Repository // 스프링컨테이너에 보드다오등록해주는 애, 보드다오 타입의 Bean을 만든다.
// Bean만드는 방법은 3가지이지만, 통일해서 만들어야함
public class BoardDAO {
	
	public List<BoardDTO> list() {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		boardList.add(new BoardDTO(1, "공지"));
		boardList.add(new BoardDTO(2, "협조"));
		return boardList;
	}

	public BoardDTO detail1(int boardNo) {
		return new BoardDTO(boardNo, "제목"); // ctrolle - service - 
	}

	public BoardDTO detail2(int boardNo) {
		return new BoardDTO(boardNo, "스프링");
	}
	public BoardDTO detail3(int boardNo) {
		return new BoardDTO(boardNo, "이게뭐야");
	}

	
	
	
	
	
	
	
	
	

}