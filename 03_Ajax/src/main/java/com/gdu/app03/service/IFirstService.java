package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.Person;

public interface IFirstService {
	
	public Person execute1(HttpServletRequest request, HttpServletResponse response);
							// 파라미터(리퀘스트를받아서 펄슨을 반환하는 아이)
	
	
	public Map<String, Object> execute2(String name, int age); // 매개변수를 한번 바꿔본거임
	
	public Map<String, Object> execute3(Person person);
	
	
// 인터페이스 만들었으니까
	//인터페이스 구현하는 클래스 서비스들을 만들러 가자.
	// 클래스 만들고 add에서 얘 인터페이스 잡아주면됨
}
