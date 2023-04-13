package com.gdu.app03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.BmiVO;

public class SecondServiceImpl implements ISecondService {

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

}
