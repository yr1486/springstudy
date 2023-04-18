package com.gdu.app04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
	
	// 단순 이동 작업 일때.
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	

}
