package com.gdu.app07.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app07.service.BoardService;


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
	
	// addBoard() 서비스가 반환한 0또는 1을 가지고 /board/list.do(redirect)한다
	// 실제로 위에있는 list.do로 간다는거야
	// addBoard() 서비스가 반환한 0 또는 1은 list.jsp로 확인하는거
	@PostMapping("/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("addResult", boardService.addBoard(request)); //리퀘스트전달.
														// 얘가이제 0 또는 1인거지		
														// list.jsp에가서 스크립트 펑션 보기 addResult
		return "redirect:/board/list.do";
	}
	
	
	// addBoard() 서비스가 반환한 0또는 1을 가지고 /board/detail.do 이동(redirect)한다
	// addBoard() 서비스가 반환한 0 또는 1은 /WEB-INF/views/bord/detail.jsp에서 확인한다.
	
	@PostMapping("/modify.do")
	public String modify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("modifyResult", boardService.modifyBoard(request));
//		return "redirect:/board/detail.do";
		// 상세보기 요청을 하고 싶으면. 보드넘버값을 전달해줘야함. 몇번게시글이 보고싶은지.
		return "redirect:/board/detail.do?boardNo=" + request.getParameter("boardNo");
										//?boardNo=이거 안하면 안되는건가? 그냥 do + request.getParameter("boardNo") 하면 안되는건지.
	}
	
	
	// removeBoard() 서비스가 반환한 0 또는 1을 가지고 /board/list.do으로 이동(redirect)한다.
	// removeBoard() 서비스가 반환한 0 또는 1은 /WEB-INF/views/board/list.jsp에서 확인한다.
	@PostMapping("/remove.do")
	public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", boardService.removeBoard(request));
//		return "redirect:/board/detail.do";
		// 상세보기 요청을 하고 싶으면. 보드넘버값을 전달해줘야함. 몇번게시글이 보고싶은지.
		return "redirect:/board/list.do";
	}
	
	
	@GetMapping("/tx.do")
	public void tx() {
		boardService.testTx();
	}
	
	

}
