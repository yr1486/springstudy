package com.gdu.myapp.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.myapp.domain.BoardDTO;

@Controller
public class BoardController {

	@GetMapping({"/", "/index.do"})  // "/"        http://localhost:9090/myapp 요청인 경우에 동작한다. (context path 경로)
	                                 // "index.do" http://localhost:9090/myapp/index.do 요청인 경우에 동작한다.
	/*
	// 이 둘의 차이 없음 ..! / 있고 없고 차이 없음.
	@GetMapping("/board")  // http://localhost:9090/myapp/board 요청인 경우에 동작한다.
	@GetMapping("board")   // http://localhost:9090/myapp/board 요청인 경우에 동작한다.
	*/
	
	// 반환타입 String : 이동할 jsp 이름을 반환한다. 반환된 이름은 servlet-context.xml의 ViewResolver에 의해서 rendering 된다.(prefix + 반환값 + suffix)
	public String index() {
		return "index";
	}
	
	
	
	// <a>, location
	/*
	@GetMapping("/detail.do")
	public void getDetail(HttpServletRequest request) {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		System.out.println(boardNo + ", " + title);
	}
	*/
	// <form>
	/*
	@PostMapping("/detail.do")
	public void postDetail(HttpServletRequest request) {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		System.out.println(boardNo + ", " + title);
	}
	*/
	
	/*
	@GetMapping("/detail.do")
	public void getDetail(@RequestParam(value="boardNo", required=false, defaultValue="0") int boardNo
			            , @RequestParam(value="title", required=false, defaultValue="빈제목") String title) {
		System.out.println(boardNo + ", " + title);
	}
	
	@PostMapping("/detail.do")
	public void postDetail(@RequestParam(value="boardNo", required=false, defaultValue="0") int boardNo
            			 , @RequestParam(value="title", required=false, defaultValue="빈제목") String title) {
		System.out.println(boardNo + ", " + title);
	}
	*/
	
	/*
	@GetMapping("/detail.do")
	public void getDetail(BoardDTO board) {  // board.setBoardNo()와 board.setTitle()이 여기서 사용된다.
		System.out.println(board);  // BoardDTO 객체를 출력하면 BoardDTO 클래스의 toString()이 동작한다. 
	}
	
	@PostMapping("/detail.do")
	public void postDetail(BoardDTO board) {  // board.setBoardNo()와 board.setTitle()이 여기서 사용된다.
		System.out.println(board);  // BoardDTO 객체를 출력하면 BoardDTO 클래스의 toString()이 동작한다.
	}
	*/
	
	
	
	// Model
	// 1. 주 목적 : jsp로 forward할 데이터(attribute)를 저장하는 용도
	// 2. 속성(attribute) 저장소 4개(pageContext, request, session, application) 중에서 request를 이용
	// 3. 컨트롤러에서만 선언할 수 있다.
	
	/*
	@GetMapping("/detail.do")  // 반환타입이 없으면(void이면) 맵핑이 jsp인줄 안다. 웹에서 404뜨면서 detail.jsp를 찾을수 없다고 나올거임
	public String getDetail(HttpServletRequest request, Model model) {
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt1.orElse("0"));
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("title"));
		String title = opt2.orElse("빈제목");
		model.addAttribute("boardNo", boardNo);  // request.setAttribute("boardNo", boardNo);
		model.addAttribute("title", title);      // request.setAttribute("title", title);
		return "detail";
	}
	@PostMapping("/detail.do")
	public String postDetail(HttpServletRequest request, Model model) {
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt1.orElse("0"));
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("title"));
		String title = opt2.orElse("빈제목");
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("title", title);
		return "detail"; // detail.jsp로 전달. 위에 model. 두문장을.
	}
	*/
	
	/*
	@GetMapping("/detail.do")
	public String getDetail(@RequestParam(value="boardNo", required=false, defaultValue="0") int boardNo
		 	              , @RequestParam(value="title", required=false, defaultValue="빈제목") String title
			              , Model model) {
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("title", title);
		return "detail";
	}
	
	@PostMapping("/detail.do")
	public String postDetail(@RequestParam(value="boardNo", required=false, defaultValue="0") int boardNo
            			 , @RequestParam(value="title", required=false, defaultValue="빈제목") String title
            			 , Model model) {
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("title", title);
		return "detail";
	}
	*/
	
	// 그냥 실었을 때
	@GetMapping("/detail.do")
	public String getDetail(BoardDTO board) {  // 파라미터를 객체로 받으면 자동으로 Model에 저장이 된다! 속성명은 "boardDTO"로 저장이 된다!(클래스를 이용해서 속성명을 만든다!)
		return "detail";
	}
	
	// 이름 바꿔서 실었을 때
	@PostMapping("/detail.do")
	public String postDetail(@ModelAttribute("board") BoardDTO board) {  // Model에 저장하는 속성명을 "board"로 하겠다!
		return "detail";
	}
	
}



