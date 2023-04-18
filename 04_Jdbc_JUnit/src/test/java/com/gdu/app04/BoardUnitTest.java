package com.gdu.app04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app04.domain.BoardDTO;
import com.gdu.app04.repository.BoardDAO;

// JUnit4
@RunWith(SpringJUnit4ClassRunner.class) // junit쓰겠다. (.class는 타입을 명시한다는 뜻)


/*
	 	@ContextConfiguration
	 	1. 어떤 방식으로 Spring Container에 Bean을 등록했는지 알려주는 애너테이션이다. 역할이다.
	 	2. Bean 등록 방식 (3가지)
	 		1) root-context.xml에 <bean> 태그를 추가한 경우
	 			@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
	 		
	 		2) com.gdu.app04.config.AppConfig.java에 @Configuration + @Bean을 작성한 경우
	 			@ContextConfiguration(classes={AppConfig.class})
	 		
	 		3) @Component(@Controller, @Service, @Repository)를 작성한 경우
	 			@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
	 		
	 		지금은 3)으로 하고 있음
 */

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

/*
 	테스트 순서 결정하기
 	1. 순서 : 삽입 - 상세 - 수정 - 상세 - 목록 - 삭제
 	2. 테스트 메소드명을 기준으로 순서를 결정하는 애너테이션인 @FixMethodOrder를 사용한다.
 	
 	
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드이름의 오름차순 알파벳순으로 테스트를 수행한다.
public class BoardUnitTest {

	// 지금까지 왜했냐. 단위테스트 때문에
	// DAO 메소드 단위로 Unit 테스트를 진행하기 위해서 BoardDAO 타입의 Bean이 필요하다.
	@Autowired
	private BoardDAO boardDAO; // @ContextConfiguration 를 해야 @Autowired 가 된다.
	
	// BoardUnitTest 클래스를 실행할 때 org.slf4j.Logger를 동작 시킨다.
	// 평소에는 안찍히고 BoardUnitTest 실행시키면 찍힌다.
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardUnitTest.class); // 얜 일단 인터페이스야
	//                                                다 복붙하고() 괄호 안에만 바꾸는거.
	
	// 테스트 메소드
	@Test
	public void a1삽입테스트() {
		BoardDTO board = new BoardDTO(0, "제목", "내용", "작성자", null, null);
		assertEquals(1, boardDAO.insertBoard(board)); //boardDAO.insetBoard(board) 결과가 1이면 테스트 성공!
		
	}
	
	@Test
	public void a2상세테스트() {
		int board_no = 1; // 조회할 게시글의 번호
		BoardDTO board = boardDAO.selectBoardByNo(board_no);
		// System.out.println("a2 - " + board); // LOG 기능으로 대체할 예정
		LOGGER.info(board.toString()); // LOG 기능으로 대체 완료 // 문자열로바꿔서 전달해줘야함 
		
		assertNotNull(board); // board가 null이 아니면 테스트 성공!
	}
	
	
	@Test
	public void a3수정테스트() {
		BoardDTO board = new BoardDTO(1, "[수정]제목", "[수정]내용", null, null, null);
		// 원리는 삽입이랑 똑같다.
		assertEquals(1, boardDAO.updateBoard(board)); //boardDAO.updateBoard(board) 결과가 1이면 테스트 성공!
		
	}
	
	@Test
	public void a4상세테스트() {
		int board_no = 1; // 조회할 게시글의 번호
		BoardDTO board = boardDAO.selectBoardByNo(board_no);
		// System.out.println("a4 - " + board); // LOG 기능으로 대체할 예정
		LOGGER.info(board.toString()); // LOG 기능으로 대체 완료 // 문자열로바꿔서 전달해줘야함 
		
		assertNotNull(board); // board가 null이 아니면 테스트 성공!
	}
	
	@Test
	public void a5목록테스트() {
		List<BoardDTO> list = boardDAO.selectBoardList();
		// System.out.println("a5 - " + list);  // LOG 기능으로 대체할 예정
		LOGGER.info(list.toString()); // LOG 기능으로 대체 완료 // 문자열로바꿔서 전달해줘야함 
		assertEquals(1, list.size());  // 목록 개수가 1개이면 테스트 성공!
	}
	
	@Test
	public void a6삭제테스트() {
		int board_no = 1;  // 삭제할 게시글 번호
		assertEquals(1, boardDAO.deleteBoard(board_no));  // boardDAO.deleteBoard(board) 결과가 1이면 테스트 성공!
	}
	
	
	
}















