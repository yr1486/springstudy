package com.gdu.app03.service;

import org.springframework.http.ResponseEntity;

public interface IFourthService {
	public ResponseEntity<byte[]> display(String path, String filename);
	// 하나씩읽어들인 그림자체. 디지털데이터로 보관하고 있는 자바의 구성요소인 
	// 바이트단위로 읽어야해. 캐렉터가아니라.
}
