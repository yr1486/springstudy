package com.gdu.app03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app03.domain.Person;
import com.gdu.app03.service.IFirstService;

@Controller
public class FirstController {

	// FirstServiceImpl을 Spring Container에 Bean으로 등록시켜보자. (root-context.xml에 <bean>태그등록) 1. 일단 등록먼저
	
	// Spring Container에 등록된 firstService Bean을 아래 필드 firstService에 주입해보자. (필드에 @Autowired 추가) 2. 가져다가 쓰기
	
	// 주입 방법 3가지가 있어. 1. 필드에 주입 2. 생성자를 이용한 주입 3. 메소드를 이용한 주입
	
	@Autowired
	private IFirstService firstService; // 지금 널값이야.
	
	
	@ResponseBody // 응답바디를 붙여준다. // 메소드의 반환값을 Jsp이름으로 해석하지 않는다. 반환값이 Jsp이름이 아니다. 응답하는 값(데이터)이다. 응답 본문에 실어 주세요. 값을 응답.해주세요.(응답: 요청한 곳으로 보내는 데이터이다)=> 반환값 이부분: return (new FirstServiceImpl()).execute1(request);
	@GetMapping(value="/first/ajax1", produces="application/json") //produces는 응답할 데이터의 MIME TYPE을 적어준다. //기존의 url맵핑값 앞에. 응답데이터의 타입을 적어줘야해.
	// 밸류속성만 적어주는 경우에는 벨류속성을 안적어도되는데, 두번쨰속성이필요한 경우는 적어줘야해.
	 public Person ajax1(HttpServletRequest request, // 네임과 에이지 파라미터가 전달된거. // "Jackson 라이브러리"가 응답데이터(반환값)인 Person 객체를(서비스가만들어준펄슨객체를) 자동으로 JSON데이터로 변환한다. 프로듀시스를 보고. 잭슨라이브러리가 변환해준다는뜻. 문자열로바꿔서햇던걸 안해도되고 젝슨이란애를 프로젝트에 넣어놓으면 됨.
			 				HttpServletResponse response) { // 처음에는 리퀘스트만 했는데, 예외처리를 위해 리스펀스(응답)도 만들어줌. 예외처리는 ajax1번만 함 ajax2안함
					
		return firstService.execute1(request, response);
										// 첫번째 메소드 // 익스큐트1번메소드에 네임과 에이지 다시전달되고. // firstserviceImpl까지 넘어가서. 전달된거임.
	
		// 컨트롤러는 리다이렉트 아니면 포워드인데, 아무것도 안적으면 ? =>
		// ajax는 페이지를 안바꿔. 동일한 페이지에서 요청하면 값만. 받아온다. 받아오는게 값이야.
		
	}
	
	@ResponseBody
	@GetMapping(value="/first/ajax2", produces="application/json") // 얘는 메이븐레파지토리 홈피에서 다운받고 폼에 넣어놓고 해서 쓸수 있는거임 잭슨
	public Map<String, Object> ajax2(@RequestParam("name") String name, @RequestParam("age") int age) {
		return firstService.execute2(name, age);
		// 'jackson라이브러리'가 반환값 Map을 자동으로 JSON데이터로 변환한다!
		// 제이슨을 넘기고 싶으면 객체 또는 맵을 준비하면 잭슨에 의해서 자동으로 제이슨데이터로 변환한다.
	
	
		
	}
	
	// 스프링에서는 객체생성을 스프링이 가져갔는데 쓰긴 쓴다.
	// return (new FirstServiceImpl()).execute2(name, age); => 뉴
	// 이걸 바꾸면
	// return (firstService.execute1(request);
	
	
	@ResponseBody
	@GetMapping(value="/first/ajax3", produces="application/json")
	public Map<String, Object> ajax3(Person person) {
		return firstService.execute3(person);
	}
	
	
	
	
	
	
	
	
}















