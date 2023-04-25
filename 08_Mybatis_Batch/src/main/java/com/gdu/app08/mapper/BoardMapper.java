package com.gdu.app08.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app08.domain.BoardDTO;



/*
 	@Mapper
 	
 	1. mapper의 쿼리문을 직접 호출할 수 있는 인터페이스이다.
 	2. 쿼리문의 id와 메소드이름을 동일하게 처리한다.
 	3. DBconfig.java(SqlSessionTemplate Bean이 정의된 파일)에 @MapperScan을 추가해야 한다.
 	맵퍼는 맵퍼스캔과 같이 써야한다.
 	
 */







@Mapper // 7장과 다른점 Repository > mapper로. 
public interface BoardMapper { // 7장과 다른점 : class > interface로 ,  필드들을 보관할 필요가 없으니 지워. 인터페이스화 하는거지.
	
	
	public List<BoardDTO> selectBoardList();
	public BoardDTO selectBoardByNo(int boardNo);
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(int boardNo);
	public int deleteBoardList(List<String> boardNoList);
	public int selectBoardCount();
}
























