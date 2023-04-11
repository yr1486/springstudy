package com.gdu.app01.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	// 임의의 학생을 만들고, MainClass에서 확인하기
	@Bean
	public Student stu() { // <bean id="stu" class="Student">
		
		// 0~100 난수 5개
		List<Integer> scores = new ArrayList<Integer>();
		for(int cnt = 0; cnt < 5; cnt++) { // count. 반복코드를 만들기위한
			scores.add((int)(Math.random() * 101));
			// 난수 5개를. xml로 만들려면 어려움.. 그래서.
		}
		
		// 상 3개
		Set<String> awards = new HashSet<String>();
		awards.add("개근상");
		awards.add("장려상");
		awards.add("참가상");
		
		
		// address, tel
		Map<String, String> contact = new HashMap<String, String>();
		contact.put("address", "seoul");
		contact.put("tel", "02-1111-2222");
		
		
		// Bean 생성 및 반환
		Student student = new Student();
		student.setScores(scores);
		student.setAwards(awards);
		student.setContact(contact);
		return student;
		
	
		
	}
			

}
