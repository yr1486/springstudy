package com.gdu.app10.domain;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BbsDTO {

	private int bbsNo;
	private String writer;
	private String title;
	private String ip;
	
}
