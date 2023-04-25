package com.gdu.app09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

	@GetMapping("/") //컨텍스트패스경로
	public String welcome() {
		return "index";
	}
}
