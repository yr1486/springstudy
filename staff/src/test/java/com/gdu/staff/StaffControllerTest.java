package com.gdu.staff;

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


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // 배열처럼되어있지만, 하나만 적어주면 됨.


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@WebAppConfiguration
public class StaffControllerTest {
	
	/*
	 	Mock 테스트
	 	
	 	1. 가상 MVC 테스트
	 	2. controller 를 테스트 할 수 있는 통합 테스트
	 	3. method + mapping 을 이용해서 테스트를 진행한다.
	 */
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffControllerTest.class);
	
	
	// @Before
	// 1. 모든 @Test 수행 이전에 실행된다.
	// 2. MockMvc mockMvc 객체를 @Before에서 build한다.
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build(); // webApplicationContext를 이용해서 mockMvc를 만들겠다.
	}

	
	// 조회
	@Test
	public void 사원조회테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders 
				.get("/staff/search.do")						// @GetMapping("/board/detail.do") // 겟방식
					.param("staffNo", "1"))						// 파라미터
						.andReturn()							// 조회결과
						.getModelAndView()						// Model에 저장된 조회결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
						.getModelMap()							// ModelAndView에서 Model을 가져옴
							.toString());						
	}


	
	
	
	
	
	
	
}
















