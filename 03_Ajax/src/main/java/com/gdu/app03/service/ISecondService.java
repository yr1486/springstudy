package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.BmiVO;

public interface ISecondService {
	
	//public BmiVO execute1(HttpServletRequest request, HttpServletResponse response); // 리스펀스는 잘못된값이 전달되었을때를 대비해서 만들어놈
	// 반환타입이 객체인 애 젝슨이 json으로 바꿔줌
	//public Map<String, Object> execute2(BmiVO bmiVO);
	// 반환타입이 맵인 애
	// 젝슨으로 json도만들필요없고, xml만들필요도 없는거.

	// 다른 처리하려고 주석처리함.

	// 스프링에는 에이작 전용 만들어져있음 스프링클래스가 만들어져있음 : ResponseEntity<BmiVO>
	public ResponseEntity<BmiVO> execute1(HttpServletRequest request);
	//					<반환하는데이터>를 반환할건데 몇개더 실어서 보낼거야.
	
	public ResponseEntity<Map<String, Object>> execute2(BmiVO bmiVO);

}
