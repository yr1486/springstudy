package com.gdu.app06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	// ParameterCheckAOP에 의해서 파라미터를 체크할 메소드의 이름은 모두 ParamCheck로 끝난다.
	
	/// 잘 이해가 안가는 부분 : 모델
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
	public String addParamCheck(BoardDTO board) {
		boardService.addBoard(board); // addBoard()메소드의 호출 결과인 int 값(0 or 1)은 사용하지 않았다 // 보드서비스에 에드보드를불러준후 board값을 넣어준다.
		return "redirect:/board/list.do"; // 삽입이니까, 리다이렉트 // 삽입하고 나면 목록보기로 간다.
		// 리다이렉트 이후의 경로는 항상 맵핑으로 작성, jsp하지말기.
		
	}
	
	// 파라미터받는 3가지방법중 파람이용
	@GetMapping("/detail.do")
	public String detailParamCheck(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no
						, Model model) { // 상세보기 , 목록보기는 Model이 필요함. // 받아서 없으면 0쓸게
		model.addAttribute("b", boardService.getBoardByNo(board_no));
		return "board/detail";
	}
	
	@GetMapping("/remove.do")
	public String removeParamCheck(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no) {
		boardService.removeBoard(board_no);
		return "redirect:/board/list.do";
		
	}
	
	@PostMapping("/modify.do")
	public String modifyParamCheck(BoardDTO board) {
		boardService.modifyBoard(board);
		return "redirect:/board/detail.do?board_no=" + board.getBoard_no(); // 성공실패 0,1 사용하지 않았음
		// detail은 board_no를 가지고 가야해서
	}
	
	
	// 트랜잭션 처리 확인을 위한 testTx() 메소드 호출하기
	@GetMapping("/tx.do") // 라는 주소가 입력이 되면, ==> http://localhost:9090/app06/board/tx.do
	public String tx() {
		boardService.testTx();
		return "redirect:/board/list.do";
	}



}
