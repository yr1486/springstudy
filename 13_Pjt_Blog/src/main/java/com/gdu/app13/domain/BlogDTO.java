package com.gdu.app13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO {

  private int blogNo;
  private String title;
  private String content;
  private int hit;
  private Date createdAt;
  private Date modifiedAt;
  private MemberDTO memberDTO; // memberNo대신 memberDTO를 보내준다. private int memberNo; 사용될 수 있지만, 기타 정보들을 얻기위해 DTO로 씀
  
  
}
