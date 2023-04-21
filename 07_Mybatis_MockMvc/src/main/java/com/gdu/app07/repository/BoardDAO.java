package com.gdu.app07.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.BoardDTO;

@Repository
public class BoardDAO {
	
	// DAO에 Bean준비
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	// 얘는 DBConfig에서 가져온거야
	// 다오는 서비스한테 반환값을 줘야하는게 역할임, 맵퍼에서!!! 받아온거 서비스한테 돌려주는 역할 // 다오는 디비만 갔다오는애
	
	
	// 서비스한테 넘겨주자'
	
	private final String NS = "mybatis.mapper.board.";
	
	public List<BoardDTO> selectBoardList() {
		// 매개변수가 비어었는 이유는 board.xml에 파라미터타입값을 보면됨.=> 없으니까.
		// 받은게 없으니까 주는것도 없는거. ()
		return sqlSessionTemplate.selectList(NS + "selectBoardList"); // 목록으로 셀렉트리스트를 받아오는 애, ()안에는 맵퍼이름. board.xml에 <mapper namespace="mybatis.mapper.board"> 여기 밑에 있는 select문을 부르는거야
										//      맵퍼 board.xml밑에.있는 셀렉트아이디 가져오는거.
	}
	
	public BoardDTO selectBoardByNo(int boardNo) {//()안은 서비스로 받아오는자리. // 가서, 파라미터가인트타입이고. 조건절에 보드넘버가있으니. 얘를 받아오는거지
	// 메소드의 이름은 쿼리문의 아이디로 하기로햇어
		return sqlSessionTemplate.selectOne(NS + "selectBoardByNo", boardNo); // 첫번쨰가쿼리문, 두번쨰가 서비스가보드넘버준거
		// ()로 받아와서                     "얘한테 "      ,이값주라는거.
	}
	
	public int insertBoard(BoardDTO board) {
		//쿼리가 뭐 주는지 보고 그대로 반환타입
		
		return sqlSessionTemplate.insert(NS + "insertBoard", board);
		
	}
	
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update(NS + "updateBoard", board); // ,boardNo라고 적어서 틀렸음. => 보드를 넘겨준거니까. 넘버가 아니고 객체를 넘겨줘야지.
		
	}
	
	public int deleteBoard(int boardNo) {
		return sqlSessionTemplate.delete(NS + "deleteBoard", boardNo);
	}
	





}

























