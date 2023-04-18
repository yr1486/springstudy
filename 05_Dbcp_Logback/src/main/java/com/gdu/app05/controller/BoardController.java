package com.gdu.app05.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app05.domain.BoardDTO;
import com.gdu.app05.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	// BoardController 클래스를 실행 할 때 org.
	private static final Logger Logger = LoggerFactory.getLogger(BoardController.class);
	
	
	@Autowired
	private BoardService  boardService;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		List<BoardDTO> list = boardService.getboardList();
		Logger.debug(list.toString()); // 목록 확인
		model.addAttribute("boardList", boardService.getboardList());
		return "baordList";
	}
	
	
	@GetMapping("/write.do")
	public String write() {
		return "/board/write";
	}
	
	@GetMapping("/add.do")
	public String add(BoardDTO board) {
		Logger.debug(board.toString()); // 파라미터 확인
		Logger.debug(boardService.addBoard(board) + ""); // 결과 확인
		return "redirect:board/list.do";
	
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no
								, Model model) {
		Logger.debug(board_no + ""); // 파라미터 확인
		BoardDTO b
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
