package com.gdu.app00.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {
	//               /는 컨택스트패스, 메소드 얘는 생략해도됨
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index"; // ViewResoler에 의해서 해석된다.(servlet-context.xml을 참고하자)
						// /WEP-INF/views/index.jsp
	}

	@GetMapping("/poster1.do")
	public String poster1()	{
		return "poster1";
	}
	
	@GetMapping("/poster2.do")
	public String poster2()	{
		return "poster2";
	}
	
	@GetMapping("/poster3.do")
	public String poster3()	{
		return "poster3";
	}
	
	@GetMapping("/poster4.do")
	public String poster4()	{
		return "poster4";
	}


}
