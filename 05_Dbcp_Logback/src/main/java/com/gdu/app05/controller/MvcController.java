package com.gdu.app05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
	
	// 단순이동 작업일때
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	
	
	
	
	

}
