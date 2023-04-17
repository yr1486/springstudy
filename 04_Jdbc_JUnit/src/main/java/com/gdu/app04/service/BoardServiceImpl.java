package com.gdu.app04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app04.domain.BoardDTO;
import com.gdu.app04.repository.BoardDAO;


/*
    @Component 설명
    
    1. BoardServiceImpl 클래스 타입의 객체를 만들어서, Spring Container에 저장하시오.
    2. <bean> 태그나 @Configuration + @Bean 애너테이션을 대체하는 방식이다.
    3. Layer별 전용 @Component가 만들어져 있다.
    	1) 컨트롤러   : @Controller
    	2) 서비스     : @Service ===== > 지금이 service니까, @service를 붙여줘야되유 , 컴포넌트대신. Service를 쓰는거임 왜? Service가 컴포넌트를 가지고 있으니까. => 스프링컨테인어에 만들어진다라는뜻이고. 객체로 만들어졌고., 사용할수 있다는 뜻.
    	3) 레파지토리 : @Repository
    단, Component가 @Autowired를 통해서 인식되려면 Component-Scan이 등록되어있어야 한다. 
    @Component =스프링컨테이너에 만들어진 bean이다 라는뜻. 
 	
 	Component-Scan 등록 방법
 	방법1. <contex: component-scan> ==> 얘는 이미 webapp > appServlet > sevlet-context.xml에 이미 만들어져 있음
 	방법2. @ComponentScan ==> 방법1이 이미 되어 있으니, 방법2까지 굳이 할 필요가 없다!!
 	
 */
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;


	@Override
	public List<BoardDTO> getBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO getBoardByNo(int board_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int board_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
