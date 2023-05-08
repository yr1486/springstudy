package com.gdu.app11.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtil {

	// String filesystemName 만들기
	public String getFilesystemName(String originName) { // 원래이름의 확장자를 알아야해서. 이름을 받아와야해
	
		// 원래 첨부 파일의 확장자 꺼내기.
		String extName = null;
		
		// 확장자에 마침표(.) 가 포함된 예외 상황 처리
		if(originName.endsWith("tar.gz")) { // 리눅스 확장자 명
			extName = "tar.gz";
		}
		else {
			// String.split(정규식)
			// 정규식에서 마침표(.)는 모든 문자를 의미하므로, 이스케이프 처리 해줘야한다. 아니면 문자 클래스로 처리.
			// 이스케이프 처리하는법 : \.
			// 문자클래스 처리하는법 : [.]
			String[] array = originName.split("\\."); // 자바에서 역슬래시 넣으려면 두개 넣어야해. 아니면 대괄호로 감싸거나.
			extName = array[array.length - 1];
			
		}
		
		// 결과 반환
		// UUID.exeName
		// replace는 지울 수 있는 메소드
		return UUID.randomUUID().toString().replace("-", "") + "." + extName; // 하이푼이 제거된 uuid값에 확장자를 붙여줬음.
			
	}
	
	
}
