package com.gdu.app08.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app08.service.BoardService;


@Controller
@RequestMapping("/board")	// ("/board")로 시작하는 맵핑들은 모두 내가 받겠다. 
public class BoardController {
	
	// 컨트롤러가 
	@Autowired
	private BoardService boardService;
	// BoardService 해도되고. BoardServiceImpl해도 됨
	// 근데 모르는오류가있을수있으니 하던대로하자.
	
	/*
	 	데이터(속성) 저장 방법
	 	1. forward 일때 : Model에 attribute로 저장한다.
	 	2. redirect 일때: RedirectAttributes로 flasshAttribute로 저장한다.
	 */
	
	//첫번쨰로 보여질게 리스트.do
	// /board는 위에 애가 가져가서 안적어줘도 되는거
	@GetMapping("/list.do") // == /board/list.do
	// 목록보기라 하는것은 getBoardList() 서비스가 반환한 List<BoardDTO>(=어레이리스트라고함) 를 board/에 list.jsp로 전달한다.(=전달한다는 forward하는거고. 포워드할 데이터는 모델에 저장한다)
	public String list(Model model) { //jsp가보면은 붙은 파라미터가없어서 ()
		model.addAttribute("boardList", boardService.getBoardList()); // boardService.getBoardList() 얘를 부르면 어레이리스트가 오는거라 부르는거임
			//           jsp에 boardList 이름만들어놈
		return "board/list";
		// 뷰 리졸버덕분에 /WEB-INF/views    .jsp를 앞뒤로 붙여주니까. 우리는 가운데만 적는거 
		
	}
	
	// getBoardByno() 서비스가 반환한 BoardDTO를 / detail.jsp로 전달한다.
	@GetMapping("/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("b", boardService.getBoardByNo(request)); // detail.jsp에서 b로 별명준거 확인해보기
		return "board/detail";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	// addBoard() 서비스 내부에 location.href를 이용한 /board/list.do 이동이 있기 떄문에 응답할 View를 반환하지 않는다.
	@PostMapping("/add.do")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		boardService.addBoard(request, response); //리퀘스트전달.
	}
	
	
	// addBoard() 서비스 내부에 location.href를 이용한 /board/list.do 이동이 있기 떄문에 응답할 View를 반환하지 않는다.
	@PostMapping("/modify.do")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		boardService.modifyBoard(request, response); 
	}
	
	
	// removeBoard() 서비스 내부에 location.href를 이용한 /board/list.do 이동이 있기 떄문에 응답할 View를 반환하지 않는다.
	@PostMapping("/remove.do")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		boardService.removeBoard(request, response); //리퀘스트전달.
		//return "redirect:/board/detail.do";
		// 상세보기 요청을 하고 싶으면. 보드넘버값을 전달해줘야함. 몇번게시글이 보고싶은지.
	}
	
	@PostMapping("/removeList.do")
	public void removeList(HttpServletRequest request, HttpServletResponse response) {
			boardService.removeBoard(request, response);
		}
	
	
	
	
	

}
