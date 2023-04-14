package com.gdu.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index"; // ViewResoler에 의해서 해석된다.(servlet-context.xml을 참고하자)
						// /WEP-INF/views/index.jsp
	}
	// index.jsp는 넘어가는게 없어. 처음 화면이니까 @GetMapping이 안되는거야
	
	

	
	
	@GetMapping("/first.do")
	public String first()	{
		return "first";
		
		// 얘가  first 스 
	}
	
	@GetMapping("/second.do")
	public String second()	{
		return "second";
	}
	
	@GetMapping("/third.do")
	public String third()	{
		return "third";
	}
	
	@GetMapping("/fourth.do")
	public String fourth()	{
		return "fourth";
	}
	
	@GetMapping("/fifth.do")
	public String fifth()	{
		return "fifth";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
