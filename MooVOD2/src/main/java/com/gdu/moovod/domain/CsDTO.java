package com.gdu.moovod.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsDTO {
	
	private int csNo;
	private String csTitle;
	private String csWriter;
	private String csContent;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private int attachCount; // UPLOAD 테이블에는 없는 칼럼이지만, 목록 보기에서 정보를 저장하기 위해 사용.

}