package com.gdu.app03.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gdu.app03.domain.Contact;
import com.gdu.app03.service.IThirdService;

@Controller
public class ThirdController {

	// 컨트롤러가 불러주는건 서비스
	// 컨트롤러 => 얘가 주소를 받을 수 있어서 쓰는거임 => jsp 펑션에 url: '${contextPath}/third/ajax1',
	
	
	
	
	
	// 빈의 대한 의미 알기.
	// 애너테이션의 붙은 의미들.
	// 컨테이너가 부모고. 이 안에 컨트롤러가 들어있어.
	// 내가 흐름을 읽어야 할거 : jsp에서 컨트롤러까지 어떻게 돌아가는지, ppt를 먼저 정독한다.
	// 

	// field
	private IThirdService thirdService; // 현재 널값인데.

	// 복습 빈 불러들이기
	// @Autowired
	// field, constructor
	// setter method

	// setter 형식의 method 만들자 (얘로하면 오토와이어드 생락 불가. )
	// setter method 형식의 자동 주입의 경우 @Autowired를 생략할 수 없다.

	// setter method
	@Autowired // 얘는 타입기반이야.타입으로찾아.
	public void method(IThirdService thirdService) { // Spring Container에서 IThirdService타입의 Bean을 찾아서 매개변수에 주입한다.
		this.thirdService = thirdService;
		

	}

	// @ResponseBody 얘를 작성할필요없어 // ResponseEntity<Contact> 얘가 내장기능으로 가지고 있음
	@PostMapping(value = "/third/ajax1", produces = "application/json") // 여기 오타 주의 똑같은코드!!! 자동완성으로 미디어타입이있어. 그거써도돼
	public ResponseEntity<Contact> ajax1(@RequestBody Contact contact) { // 요청 본문(request body)에 포함된 제이슨 데이터를 Contact
													//매개변수가 객체인거야					// contact에 저장해 주세요
												// 오늘스터디 : 객체로 받아 가져온다는게. 지금 도메인에서 객체들을 만들어줬어. 걔네들
		
		return thirdService.execute1(contact);
	}

	@PostMapping(value = "/third/ajax2", produces = "application/json") // = text/html;
	public ResponseEntity<Map<String, String>> ajax2(@RequestBody Map<String, String> map) { // 요청 본문(request body)에 포함된
																								// JSON 데이터를 Map에 저장해
																								// 주세요.
		return thirdService.execute2(map);
	}

}











