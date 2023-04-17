package com.gdu.app04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app04.service.BoardService;


@RequestMapping("/board") // 모든 mapping에 /board가 prefix로 추가됩니다.
@Controller
public class BoardController {

	/*
	 원래는 아래처럼 썼지만
	 @requestMapping("/board")를 쓰면 코드 중복을 제거할 수 있음
	 // 프로젝트 대비해서 이렇게 하는거임.
	 
	@GetMapping("/board/list.do")
	public String list() {
		return null;
	}

	@GetMapping("/board/detail.do")
	public String list() {
		return null;
	}
	
	==> 결과
		@GetMapping("/detail.do") // board빼고 이렇게만 쓰면 됨.
	public String list() {
		return null;
	}
	
	
	*/
	
	@Autowired 
	private BoardService boardService; // new 안하고 쓰려고 이방법쓰는거.
	
	@GetMapping("/list.do")
	public String list(Model model) { // Model의 역할: jsp로 전달(forward)할 데이터(속성, attribute)를 저장한다. jsp로 보낼 데이터를 모델에 실어준다. // 기본적으로 model은 맵 기반으로 되어있음. 
		return "board/list";
	}
	
}
