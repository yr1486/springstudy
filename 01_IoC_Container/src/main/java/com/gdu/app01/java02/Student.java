package com.gdu.app01.java02;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {

	// field
	private List<Integer> scores; 		 //0~100 난수 5개
	// 특징:인덱스 형식으로 순서대로. 데이터를 넣을 수 있다. 0번인덱스에 1234, 1번인덱스에 1234 이렇게. hash쓰냐안쓰냐에따라.
	private Set<String> awards;			 // 상 3개
	// 특징: 메인클래스에서 출력할때마다. 순서가 뒤죽박죽. 주머니로 기억하기. 중복 안된다.
	private Map<String, String> contact; // address, tel
	// 특징: 키,밸류 한쌍 이걸 앤트리라고 불러.entry!!! 프로퍼티와 비슷한 개념. 변수와 값.

	
	// default constructor
	
	// method(getter setter)
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public Set<String> getAwards() {
		return awards;
	}
	public void setAwards(Set<String> awards) {
		this.awards = awards;
	}
	public Map<String, String> getContact() {
		return contact;
	}
	public void setContact(Map<String, String> contact) {
		this.contact = contact;
	}

	
}
