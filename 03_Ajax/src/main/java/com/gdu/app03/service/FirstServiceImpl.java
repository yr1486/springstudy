package com.gdu.app03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.Person;

public class FirstServiceImpl implements IFirstService {

	@Override
	public Person execute1(HttpServletRequest request, HttpServletResponse response) {
		
		// 예외 발생 시 예외 메시지를 화면으로 전달하기
		// (나이에서 32가 아니라 32.5 이럴 경우) ==> 총3개의  파일을 수정해야함. 컨트롤러와 서비스2개
		
		
		try {
			
			String name = request.getParameter("name");
			name = name.isEmpty() ? "홍길동" : name; // 사용자가 입력한 name이 없으면 빈 문자열이 전달된다.
			// 사용자가 입력한 값이 없으면 홍길동으로 하겠단 뜻

			String strAge = request.getParameter("age");
			strAge = strAge.isEmpty() ? "0" : strAge; // 없으면 빈 문자열이 전달된다.
			
			// return new Person(name, Integer.parseInt(strAge));
			int age = Integer.parseInt(strAge);
			
			// 0~100 범위를 벗어난 경우 예외 발생시키기
			if(age < 0 || age > 100) {
				throw new RuntimeException(age + "살은 잘못된 나이입니다.");
			}
			
			return new Person(name, age); // $.ajax의 success로 넘기는 값

		} catch (Exception e) {

				try {
	
					response.setContentType("text/plain; charset=UTF-8"); // text/plain도 되고 text/html도 된다.
					PrintWriter out = response.getWriter();
					out.println(e.getMessage()); // $.ajax의 error로 넘기는 예외 메시지
					out.flush();
					out.close();
	
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				return null;
		}
	}
	
	// 스프링컨트롤러는 리턴을 jsp로 해석한다.
	
	
	// 컨트롤 스페이스 하면 execute2가 바로뜰거임. 
	@Override
	public Map<String, Object> execute2(String name, int age) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("age", age);
		
		return map;
	}
	
	// jsp서블릿과 달라진점 -> 과도하게 서비스를 많이 만들었음. 하나의 서비스가 하나의 메소드를 가지고 있었음. 클래스 하나당 메소드 하나. 클래스 갯수만큼 메소드가 필요했음.
	// 원래 하나의 클래스의 여러개 만들수 있잖아. 스프링에서는 이제 이 방법으로 이제 갈거야.
	// 하나의 인터페이스에서 마니 구현해서. 어찌고 해서 갈거야
	
	
	@Override
	public Map<String, Object> execute3(Person person) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", person.getName());
		map.put("age", person.getAge());
		
		
		
		return map;
	}
	

}
