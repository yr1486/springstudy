package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

// 1. 제일먼저 서비스컨테이너에 보관한다. => 등록
@Service
public class BoardServiceImpl implements BoardService {

	// 서비스는 다오를 먼저 잡아준다. 등록된거 가져다쓰려고. 오토와이어드
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoardList() { // ()안은 컨트롤러에서 받아온거. => () 받아온게 없으니 컨트롤러한테 리턴으로 주기만하면되

		return boardDAO.selectBoardList(); // 이걸부르면 어레이리스트가오는거 
	}

	// jsp 번호와함께 보냄 > c > s > dao 
	@Override
	public BoardDTO getBoardByNo(HttpServletRequest request) {
		// 파라미터 boardNo가 없으면(null, "") 0을 사용한다.
		
		// optional로 쓴다면 null 처리만 가능
		
		String strBoardNo = request.getParameter("boardNo"); //파라미터는 스트링이야. 잊지말기. // 리퀘스트에 있는 파라미터를 꺼내서 스트링에 넣어두고
		
		int boardNo = 0;
		if(strBoardNo != null && strBoardNo.isEmpty() == false) // 비어있지 않다. => 값을 받고 비어있지 않으면,
			boardNo = Integer.parseInt(strBoardNo); // 인테져해서 변수에 넣어둔다
		
		return boardDAO.selectBoardByNo(boardNo);
		// 다오의 호출은 항상 리턴에 있따.
	}

	@Override
	public int addBoard(HttpServletRequest request) {
		
		try {

			// 파라미터 title, content, writer 를 받아온다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");

			// BoardDAO로 전달할 BoardDTO 를 만들어야해
			BoardDTO board = new BoardDTO();
			board.setTitle(title);
			board.setContent(content);
			board.setWriter(writer);

			return boardDAO.insertBoard(board);
			
		} catch (Exception e) {
			return 0; // 실패는 0
		}
	}

	@Override
	public int modifyBoard(HttpServletRequest request) {
		try {
			
			
			
		// writer는 못온다. 작성자 수정 불가.
		// 파라미터 3개 받아옴
		// add와 다른거. boardNo를 받아옴 ==> 어떤게시글 수정할건지. 모르겠으면 board.xml봐바.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		board.setBoardNo(boardNo);

		return boardDAO.updateBoard(board);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int removeBoard(HttpServletRequest request) {
		// 리무브를 겟과 포스트로 구현하는 방법이 있음.
		// 단, 겟으로 진행하면 누구나 주소창을 이용해서 삭제 할 수 있음. (겟은 주소로 하는거니까)
		
		// post는 주소창으로 조작이 안되니까. 포스트방식으로 하자.
		// 삭제요청 부분만.
		
		// 그래서 번호 받아서 삭제하는건데 안올경우도 생각해야하니까
		
		// 파라미터 baordNo를 받아온다.
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		return boardDAO.deleteBoard(boardNo);
	}

	// 트랜잭션 확인 == > 언제 붙이냐면 삽입수정삭제를 2개 이상 쓸떄!!! 하나쓸때는 부를 필요가 없다.
	@Transactional
	@Override
	public void testTx() {
		boardDAO.insertBoard(new BoardDTO(0, "타이틀", "콘텐트", "작성자", null, null));
		boardDAO.insertBoard(new BoardDTO()); // 얘는 실패임
	}
	
	
	
	
	// == > try/catch를 뒤집어 씌우는거는 오류가 생길거 대비해서.
	// 트라이캐치안하고 제목 빈칸해놓고 작성하기하면 500 오류가 뜨는데
	// 트라이캐치 해주면 게시글 등록이 실패했습니다가 뜸!
	
	
	// 게시글삭제가 실패했습니다는 어떻게하면 나오는지?
	
	
	
	
	
	
}
