package com.gdu.app01.xml05;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class MainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml05/app-context.xml");
		Person person = ctx.getBean("p", Person.class);
		
		// List<String> hobbies
		List<String> hobbies = person.getHobbies();
		for(int i = 0; i < hobbies.size(); i++) {
			System.out.println("취미" + (i + 1) + ": " + hobbies.get(i));
		}
		
		// Set<String> contacts
		Set<String> contacts = person.getContacts();
		for(String contact : contacts) {  // Set 구조는 index가 없기 때문에 advanced for만 가능합니다.
			System.out.println(contact);
		}
		
		// Map<String, String> friends
		Map<String, String> friends = person.getFriends();
		for(Entry<String, String> entry : friends.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}							// 엔트리셋이 키와밸류 모두를 가지고 있으니까. 엔트리.겟키값만가져오겠다/엔트리.겟밸류값만을 가져오겠다. // 둘다 가져 오려면 엔트리셋 하는거임 // 엔트리안에 있는 값을 가져오니까.(키와밸류)
		// 셋이랑 맵은 인덱스구조가 아니라, for문을 돌릴 수 없어서, 향상for문을 사용하고.
		// 
		ctx.close();	
		
		}

}
