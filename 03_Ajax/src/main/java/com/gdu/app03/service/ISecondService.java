package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.BmiVO;

public interface ISecondService {
	public BmiVO execute1(HttpServletRequest request, HttpServletResponse response); // 리스펀스는 잘못된값이 전달되었을때를 대비해서 만들어놈
	// 반환타입이 객체인 애 젝슨이 json으로 바꿔줌
	public Map<String, Object> execute2(BmiVO bmiVO);
	// 반환타입이 맵인 애
	
	// 젝슨으로 json도만들필요없고, xml만들필요도 없는거.

}
