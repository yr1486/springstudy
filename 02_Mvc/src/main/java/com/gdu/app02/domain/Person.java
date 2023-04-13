package com.gdu.app02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// 사용한 롬복으로 어떤 메소드가 만들어 졌는지 보려면
// 메뉴 > 쇼뷰 > 아웃라인 열면됨

public class Person {

	private String name;
	private int age;
	
}
