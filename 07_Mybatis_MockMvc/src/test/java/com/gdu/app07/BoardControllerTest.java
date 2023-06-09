package com.gdu.app07;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// JUnit4
@RunWith(SpringJUnit4ClassRunner.class)

// contextConfigration ==> 3가지 
// 테스트에서 사용할 Bean이 @Component로 생성되었기 때문에 component-scan이 작성된 servlet-context.xml의 경로를 작성한다.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // 배열처럼되어있지만, 하나만 적어주면 됨.


// 테스트 순서를 메소드명의 알파벳순으로 수행
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

// webApplicationContext를 사용하기 위해서 필요한 애너테이션
@WebAppConfiguration // 컨트롤러테스트하는
public class BoardControllerTest {
	
	/*
	 	Mock 테스트
	 	
	 	1. 가상 MVC 테스트
	 	2. controller 를 테스트 할 수 있는 통합 테스트
	 	3. method + mapping 을 이용해서 테스트를 진행한다.
	 */
	
	// Mock 테스트를 수행하는 객체
	// WebApplicationContext에 의해서 생성된다.
	private MockMvc mockMvc;
	
	// @WebApplication이 있어야 자동 주입(@Autowired)이 가능하다.
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	// LOGGER (syso로 찍지않고 로거로 찍는 연습)
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardControllerTest.class);  //slf4g인터페이스꺼 / 클래스꺼 임폴트 주의.
	
	
	// @Before
	// 1. 모든 @Test 수행 이전에 실행된다.
	// 2. MockMvc mockMvc 객체를 @Before에서 build한다.
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build(); // webApplicationContext를 이용해서 mockMvc를 만들겠다.
	}
	
	// 삽입
	@Test
	public void a1삽입테스트() throws Exception { //add.do 삽입테스트하는거임
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/add.do")						// @PostMapping("/board/add.do") // 포스트방식
				.param("title", "테스트제목1")				// 파라미터
				.param("content", "테스트내용1")			// 파라미터
				.param("writer", "테스트작성자"))   // 파라미터
				// 에드는 3가지 파라미터를 받음	
				// 그리고 우리가 확인하고 싶은거. 반환값. 0 or 1
					.andReturn()								// 수정결과
					.getFlashMap()								// FlashAttribute로 저장한 결과 확인
						.toString());								

		
	}
	
	
	// 수정 modi
	@Test
	public void a2수정테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders 
				.post("/board/modify.do")						// @GetMapping("/board/modify.do") // 겟방식
				.param("title", "테스트제목2")				// 파라미터
				.param("content", "테스트내용2")			// 파라미터
				.param("boardNo", "1"))			
					.andReturn()							// 수정결과
					.getFlashMap()					// Model에 저장된 조회결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
						.toString());						
	}
	
	
	// 조회
	@Test
	public void a3상세조회테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders 
				.get("/board/detail.do")						// @GetMapping("/board/detail.do") // 겟방식
					.param("boardNo", "1"))						// 파라미터
						.andReturn()							// 조회결과
						.getModelAndView()						// Model에 저장된 조회결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
						.getModelMap()							// ModelAndView에서 Model을 가져옴
							.toString());						
	}

	// 목록
	@Test
	public void a4목록테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders 
				.get("/board/list.do"))						// @GetMapping("/board/list.do") // 겟방식
					.andReturn()							// 목록조회결과
					.getModelAndView()						// Model에 저장된 조회결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
					.getModelMap()							// ModelAndView에서 Model을 가져옴
						.toString());						
	}
	
	
	
	
	
	// 삭제 modi
	@Test
	public void a5삭제테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders 
				.post("/board/remove.do") 					// @GetMapping("/board/delete.do") // 겟방식
					.param("boardNo", "1"))						// 파라미터
					.andReturn()							// 목록조회결과
					.getFlashMap()							// ModelAndView에서 Model을 가져옴
						.toString());						
	}
	
	
	
	
	
	
	
	
	
	
}
















