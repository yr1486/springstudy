package com.gdu.app03.service;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class FourthServiceImpl implements IFourthService {

	@Override
	public ResponseEntity<byte[]> display(String path, String filename) {

		
		try {
			
			// path와 filename을 이용해서 File 객체 만들기
			File file = new File(path, filename);
			
			//File 객체를 byte 배열로 복사하기
			byte[] b = FileCopyUtils.copyToByteArray(file);
			
			// byte 배열이 된 이미지 파일을 반환하기
			// 그림도 데이터야, 바이트 데이터로 만들어가지고? 보내는 거임
			return new ResponseEntity<byte[]>(b, HttpStatus.OK); // 진짜 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; // 가짜 반환
	}

}
