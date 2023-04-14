package com.gdu.app03.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import com.gdu.app03.domain.BmiVO;

public class SecondServiceImpl implements ISecondService {

	
	// 다른 방법 처리하려고 주석처리함.
	/*
	@Override
	public BmiVO execute1(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			// bmi = 몸무게 / 키(m)*키(m), obesity 넘기기
			double weight = Double.parseDouble(request.getParameter("weight")); 
			double height = Double.parseDouble(request.getParameter("height")) / 100; 
			
			double bmi = weight / (height * height);
			String obesity = null;
			
			if(bmi < 18.5) {
				obesity = "저체중";
				
			} else if(bmi < 24.9) {
				obesity = "정상";
				
			} else if(bmi < 29.9) {
				obesity = "과체중";
				
			} else {
				obesity = "비만";
				
			}
			
			return new BmiVO(weight, height, bmi, obesity); /// $.ajax의 error로 넘기는 예외 값  // 넘겨주는거 다가지고 있음 그대로 컨트롤러로 가서 그대로 '잭슨에 의해서' 제이슨데이터로 바뀌는거
		
		} catch(Exception e) { // double.paseDouvle에서 발생하는 익셉션.
			
			try {

				response.setContentType("text/plain; charset=UTF-8"); // text/plain도 되고 text/html도 된다.
				PrintWriter out = response.getWriter();
				out.println("몸무게와 키 입력을 확인하세요"); // $.ajax의 error로 넘기는 예외 메시지
				out.flush();
				out.close();

			} catch (Exception e2) { // 캐치에서 작성한 응답은 애러로 넘어간다.
				e2.printStackTrace();
			}
			return null;

		}
	}

	@Override
	public Map<String, Object> execute2(BmiVO bmiVO) {
		
		double weight = bmiVO.getWeight();
		double height = bmiVO.getHeight() / 100;
		
		
		double bmi = weight / (height * height);
		
		String obesity = null;
		
		
		if(bmi < 18.5) {
			obesity = "저체중";
			
		} else if(bmi < 24.9) {
			obesity = "정상";
			
		} else if(bmi < 29.9) {
			obesity = "과체중";
			
		} else {
			obesity = "비만";
			
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("bmi", bmi);
		map.put("obesity", obesity);
		
		return map;
	}
	
	*/
	
	
	
	
	// 잊지말자. 인터페이스서비스에서 execute1 만들고 여기와서 컨트롤스페이스
	
	/*
	 	ResponseEntity<T> 클래스  ===> <T: 타입>
	 	1. Ajax 응답 데이터를 생성하는 클래스
	 	2. 생성자 중 하나의 사용법
	 		public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status)
	 		1) @Nullable T body 					  : 실제로 응답할 데이터 (널이가능하다. 응답데이터가 널이여도 가능하다. 오류가 발생하지 않는다.)
	 		2)  MultiValueMap<String, String> headers : 응답 헤더 (대표적인응답헤더:content-Type)
	 		3)  HttpStatus status					  : 응답 코드(200, 404, 500 등 , 응답코드도 만들어서 보낼 수 있다.)
	 			404를 달아놓고 보냈다고 하면 => 에러쪽에서 처리가 된다===> 캣치를 해줘야 원래 예외인줄 알자나. 예외처리를만들어줘야.(트라이캐치) 근데 얘는 그냥? 응답에? 실어보내는? 그런거래
	 			
	 */
	@Override
	public ResponseEntity<BmiVO> execute1(HttpServletRequest request) {
	
			try {
				
				double weight = Double.parseDouble(request.getParameter("weight")); 
				double height = Double.parseDouble(request.getParameter("height")) / 100; 
				
				double bmi = weight / (height * height);
				String obesity = null;
				
				if(bmi < 18.5) {
					obesity = "저체중";
					
				} else if(bmi < 24.9) {
					obesity = "정상";
					
				} else if(bmi < 29.9) {
					obesity = "과체중";
					
				} else {
					obesity = "비만";
					
				}
		
				return new ResponseEntity<BmiVO>(new BmiVO(weight, height, bmi, obesity), HttpStatus.OK);
				//                            응답 객체                                  ,응답코드(HttpStatus.OK == 200 임!)
	}catch (Exception e) {
		
		BmiVO bmiVO = null;
		return new ResponseEntity<BmiVO>(bmiVO, HttpStatus.INTERNAL_SERVER_ERROR); // httpstatus가 500이므로 $.ajax의 error에서 처리된다.
					//                   bmiVO를 변수처리하고 불렀으니 널이다는뜻, 응답할데이터가없다.INTERNAL_SERVER_ERROR == 500  !!
	
		//지금 ResponseEntity<BmiVO>(bmiVO, HttpStatus.INTERNAL_SERVER_ERROR); 이문장은 몸무게 키 입력안하고 전송눌렀을때 뜨는 응답메세지를 설정안함.
	}

}
	
	@Override
	public ResponseEntity<Map<String, Object>> execute2(BmiVO bmiVO) {
	
		double weight = bmiVO.getWeight();
		double height = bmiVO.getHeight(); 
		
		if(weight == 0 || height == 0) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST); // 응답 코드가 정상(200)이 아니므로 $.ajax의 error로 전달
		}
		
		double bmi = weight / (height * height / 10000); // bmi = 몸무게(kg) / 키(m)*키(m)
		String obesity = null;
		
		if(bmi < 18.5) {
			obesity = "저체중";
			
		} else if(bmi < 24.9) {
			obesity = "정상";
			
		} else if(bmi < 29.9) {
			obesity = "과체중";
			
		} else {
			obesity = "비만";
		}
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bmi", bmi);
		map.put("obesity", obesity);
		
		// 응답 헤더(Content-Type) -> @GetMapping의 produces 대신 사용
		MultiValueMap<String, String> header = new HttpHeaders();
		// 2. 클래스의 인터페이스                    1. 클래스		
		header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		return new ResponseEntity<Map<String,Object>>(map, header, HttpStatus.OK);
	





	}

	
}
