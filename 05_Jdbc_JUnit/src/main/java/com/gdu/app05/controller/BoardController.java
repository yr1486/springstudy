package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app05.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired 
	private BoardService boardService; 
	
	@GetMapping("/list.do")
	public String list(Model model) { // Model의 역할: jsp로 전달(forward)할 데이터(속성, attribute)를 저장한다. jsp로 보낼 데이터를 모델에 실어준다. // 기본적으로 model은 맵 기반으로 되어있음. 
		return "board/list";
	}

}
