package com.gdu.app03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app03.service.IFifthService;

@Controller
public class FifthController {

	
	// field
	@Autowired
	private IFifthService fifthService;
	// FifthController 클래스에서 사용하는 인터페이스로, 이 인터페이스를 구현한 구현체를 실제로 사용합니다.
	
	@GetMapping(value="/papago.do", produces ="application/json")
	public ResponseEntity<String> papago(HttpServletRequest request) {
		return fifthService.papago(request);
		
		//반환이 그냥 String이여도 됨. 그러면 위에 @responsebody가 와야함.
		
		
	}
	// 사실은 스트링이아니라 제이슨 데이터다.

}
