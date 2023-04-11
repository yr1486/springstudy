package com.gdu.app01.java_into_xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	@Bean
	public Publisher publisher() {
		Publisher publisher = new Publisher();
		publisher.setName("한국출판사");
		publisher.setTel("02-2222-2222");
		return publisher;
	}
	
	@Bean
	public Book book() {
		Book book = new Book();
		book.setTitle("소나기");
		book.setPublisher(publisher());
		return book;
	}

}
