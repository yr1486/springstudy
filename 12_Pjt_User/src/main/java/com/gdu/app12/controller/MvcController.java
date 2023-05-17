package com.gdu.app12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

	@GetMapping(value={"/", "/index.do"}) // 회원가입하고, 첫페이지로 이동시켜주는거
	public String welcome() {
		return "index";
	}
	
}
