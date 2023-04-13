package com.gdu.app02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
  	@Controller
  	안녕. 난 컨트롤러야.
  	@Component를 포함하고 있어서 자동으로 Spring Container에 Bean으로 등록된다.
  	나는 스프링에 의해서 사용되고 있어
 
 */

@Controller // 애너테이션 해주기.
public class MyController {
	
	// 메소드 : 요청과 응답을 처리하는 단위
	
	/*
	 	메소드 작성 방법
	 	1. 반환타입은 String (응답할 Jsp의 이름을 작성하면 됨)
	 	2. 메소드명은 아무 일도 안한다. 아무 의미 없음
	 	3. 매개변수는 요청과 응답에 따라 다르다. 
	 		기본적으로는 매개변수에 요청이 필요하면 HttpServletRequest, 
	 		응답이 필요하면 HttpServletResponse 등 으로.
	 */
	
	// 아래 메소드가 언제 동작할건지를 정해주자.
	/*
	 	@RequestMapping
	 	1. value : URL Mapping을 작성한다. (동작할 주소를 작성한다 = 이 주소가 오면, 아래 메소드를 실행해달라라는 뜻 )
	 	2. method: Request Method를 작성한다. (GET, POST, PUT, DELETE 등)
	 	
	 */
	
	/*
	 		@RequestMapping(value="/", method=RequestMethod.GET)
	 		URL Mapping이 "/"면 context path 경로를 의미한다. (= http://localhost:9090/app02)
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index"; // ViewResoler에 의해서 해석된다.(servlet-context.xml을 참고하자)
						// /WEP-INF/views/index.jsp
	}

	/*
	 	@RequestMapping 작성 예시 (다 가능)
	 		@RequestMapping(value="/list.do", method=RequestMethod.GET)		정식 버전
	 		@RequestMapping(value="list.do", method=RequestMethod.GET)		value는 슬래시(/)로 시작하지 않아도 된다.
	 		@RequestMapping(value="list.do")								GET방식의 method는 생략할 수 있다.
	 		@RequestMapping("/list.do")										value 속성만 작성하는 경우에는 값만 작성할 수 있다.
	 
	 */
	@RequestMapping("/list.do")
	public String list() {
		return "board/list"; // 실제 처리되는 경로 : /WEB-INF/views/board/list.jsp
		/*
		 	만약에,
		 	return "/board/list"; 로 작성하게 되면
		 	실제 처리되는 경로 : /WEP-INF/views//board/list.jsp
		 	하지만, 실제로는 /WEP-INF/views/board/list.jsp 경로로 알아서 처리된다.
		 */
	
	}
	
	
	
	
	
}












