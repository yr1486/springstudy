package com.gdu.app11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {

	
	@Bean
	public MultipartResolver multipartResolver() {
		// 인터페이스로 빈을 만들면, 매칭시키기의 기본 
		// 빈타입은 멀티파트리졸버로 설정해야한다. (
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 100); 			// 전체 첨부 파일의 크기 100MB
		multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10);		// 첨부 파일 하나의 크기 10MB
		return multipartResolver;
	
	}
	
	
	
	
	
	
	
}
