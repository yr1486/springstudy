package com.gdu.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app03.domain.Contact;
import com.gdu.app03.service.IThirdService;

@Controller
public class ThirdController {

	// field
	// 컨트롤러가 불러주는건 서비스

	private IThirdService thirdService; // 현재 널값인데. 
	
	// 복습 빈 불러들이기
	// @Autowired
	// field, constructor
	// setter method
	
	// setter 형식의 method 만들자 (얘로하면 오토와이어드 생락 불가. )
	// setter method 형식의 자동 주입의 경우 @Autowired를 생략할 수 없다.
	@Autowired // 얘는 타입기반이야.타입으로찾아.
	public void method(IThirdService thirdService) { // Spring Container에서 IThirdService타입의 Bean을 찾아서 매개변수에 주입한다.
		this.thirdService = thirdService;
		
		
	}
	@ResponseBody
	@PostMapping(value="/third/ajax1", produces="application/json") // 여기 오타 주의 똑같은코드!!! 자동완성으로 미디어타입이있어. 그거써도돼
	public ResponseEntity<Contact> ajax(@RequestBody Contact contact) { // 요청 본문(request body)에 포함된 제이슨 데이터를 Contact contact에 저장해 주세요
		return thirdService.execute1(contact);
	}
}
