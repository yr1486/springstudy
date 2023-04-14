package com.gdu.app03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app03.domain.BmiVO;
import com.gdu.app03.service.ISecondService;

@Controller
public class SecondController {

	private ISecondService secondService; // 빈의 아이디와 같게 하길 권장함.
	
	@Autowired // @autowired 생성자 위에 해주는건데 생성자에서는 생략할수 있음
	public SecondController(ISecondService secondService) { // 여기로!!!!컨테이너에있는 빈이 세컨드서비스를 찾아서 여기로 와서 아래에 this.~필드로 주입이 된다. 그래서 위에 private~까지 전달이 되는거임!!!!!!!!!!!!!! 거쳐서 오는 경로를 이해하기
		super();
		this.secondService = secondService;
	}
	
	// @ResponseEntity 안해도되 => ResponseEntity가 해줌
	@GetMapping(value="/second/bmi1", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BmiVO> bmi1(HttpServletRequest request) {
		return secondService.execute1(request);
		
	}
	
	// @ResponseEntity 안해도되 => ResponseEntity가 해줌
	@GetMapping("/second/bmi2") // produces가 없음에 주의합니다. (얘를 서비스. 반환객체 responseEntity에 Content-Type을 작성해서 보냅니다.)
	public ResponseEntity<Map<String, Object>> bmi2(BmiVO bmiVO){
		// <반환타입적어주는거임>
		return secondService.execute2(bmiVO); // 반환값을 그대로 전달
	}
	
	
	/* 다른 방법으로 처리하려고 주석처리함
	 
	// 지금 이 3줄이나 되는 생성자 말고 더 간단하게 쓰는 방법
	// ===> @컨트롤러 위에 @AllArgsConstructor 하나만 놓으면 됨!!!!!!! 왜냐 얘가 모든필드가지고 생성자 만들어 주는 애 를 삽입하면 똑같은 거니까. 단, 롬복을 써야 쓸수 있다는 거 알고 있기.
	// 정리 :@AllArgsConstructor 로 위에 생성자를 대체해도 좋다!!!! 왜냐 autowired는 생략해도 되니께
	
	@ResponseBody // 이거 해줘야만 리턴에 잇는값이 jsp로 해석되지 않고!!! 값으로 해석 된다. = 응답데이터로 처리가 된다.
	@GetMapping(value="/second/bmi1", produces=MediaType.APPLICATION_JSON_VALUE) // 얘는 MediaType.APPLICATION_JSON_VALUE ======= "application/json"이랑 똑같음, 오타때문에 생긴거라 보면됨
	public BmiVO bmi1(HttpServletRequest request, HttpServletResponse response) {
	
		return secondService.execute1(request, response); 
	}
	
	// 객체로 권장
	@ResponseBody
	@GetMapping(value="/second/bmi2", produces=MediaType.APPLICATION_JSON_VALUE)
	// 서비스가 맵인지 객체인지 보고 결정하는거
	public Map<String, Object> bmi2(BmiVO bmiVO) {
		return secondService.execute2(bmiVO);
	}
	
	*/
	
	
}
