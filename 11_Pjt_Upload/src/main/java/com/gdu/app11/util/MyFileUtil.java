package com.gdu.app11.util;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtil {
	
	// 경로 구분자
	private String sep = Matcher.quoteReplacement(File.separator);
	
	// String path 만들기
	public String getPath() {
		LocalDate now = LocalDate.now();
		return "/storage" + sep + now.getYear() + sep + String.format("%02d", now.getMonthValue()) + sep + String.format("%02d", now.getDayOfMonth());
		// 실제로 만들어 지는 경로 가 루트/storage/2023/05/08
		// 저장되는 경로는 현재 날짜 기준으로 만들었음.
		
	}

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
		// 이름의 중복이 없어야하고 인코딩 해줘야하고.
		return UUID.randomUUID().toString().replace("-", "") + "." + extName; // 하이푼이 제거된 uuid값에 확장자를 붙여줬음.
			
	}
	
	
	// String tempPath 만들기
	public String getTempPath() {
		return "/storage" + sep + "temp";
	}
	
	// 이름의 중복이 없어야하고 인코딩 해줘야하고.
	
	// String tempfileName 만들기 (zip 파일)
	public String getTempfileName() {
		return  UUID.randomUUID().toString().replace("-", "") + ".zip";
	}
	
	// String yesterdayPath 만들기
		public String getYesterdayPath() {
			LocalDate date = LocalDate.now();
			date.minusDays(1);  // 1일 전
			return "/storage" + sep + date.getYear() + sep + String.format("%02d", date.getMonthValue()) + sep + String.format("%02d", date.getDayOfMonth());
		}
		

}













