package com.gdu.app13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodDTO {

  private int memberNo; // 좋아요에는 상세보기가 없어.
  private int blogNo;
  private Date markedAt;


}
