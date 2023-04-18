package com.gdu.app04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app04.domain.BoardDTO;
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
		model.addAttribute("boardList", boardService.getBoardList());
										// 데이터베이스에 있는 목록의 결과임
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	// write의 <form method가 post임> postMapping 써야해
	@PostMapping("/add.do")
	public String add(BoardDTO board) {
		boardService.addBoard(board); // addBoard()메소드의 호출 결과인 int 값(0 or 1)은 사용하지 않았다 // 보드서비스에 에드보드를불러준후 board값을 넣어준다.
		return "redirect:/board/list.do"; // 삽입이니까, 리다이렉트 // 삽입하고 나면 목록보기로 간다.
		// 리다이렉트 이후의 경로는 항상 맵핑으로 작성, jsp하지말기.
		
	}
	
	// 파라미터받는 3가지방법중 파람이용
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no
						, Model model) { // 상세보기 , 목록보기는 Model이 필요함. // 받아서 없으면 0쓸게
		model.addAttribute("b", boardService.getBoardByNo(board_no));
		return "board/detail";
	}
	
	
	@GetMapping("/remove.do")
	public String remove(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no) {
		boardService.removeBoard(board_no);
		return "redirect:/board/list.do"; // removeBoard()메소드의 호출 결과인 int 값(0 or 1)은 사용하지 않았다
	}
	
	@PostMapping("/modify.do")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);
		return "redirect:/board/detail.do?board_no=" + board.getBoard_no(); // 성공실패 0,1 사용하지 않았음
		// detail은 board_no를 가지고 가야해서
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
