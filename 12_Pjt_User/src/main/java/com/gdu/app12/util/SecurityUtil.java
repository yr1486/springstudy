package com.gdu.app12.util;

import java.security.MessageDigest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
  // 만약 사용자가 게시물 내용에 내용이 아닌 코드를 작성해서 해당 게시글을 못보게하거나
  // 조작 코드? 같은걸 쓸 수 있는데. 이를 방지하는 코드
  
  // 크로스 사이트 스크립팅(Cross Site Scripting) 방지하기
  public String preventXSS(String str) {
    // 매개변수로 전달된 스트링을 가공을해서 반환해주는거임.
  
    str = str.replace("<", "&lt;"); 
    str = str.replace(">", "&gt;");
    return str;
    
  }
  
  // 인증코드 반환하기
  public String getRandomString(int count, boolean letters, boolean numbers) {
    return RandomStringUtils.random(count, letters, numbers);
  }

  // SHA-256 암호화하기 (비밀번호 암호화)
  /*
     1. 입력 값을 256비트(32바이트) 암호화 처리하는 해시 알고리즘이다.
     2. 암호화는 가능하지만 복호화는 불가능한 알고리즘이다. (복호화: 암호화된 이전값을 알기가 불가능하다는 뜻)
     3. 암호화된 결과를 저장하기 위한 32바이트 byte 배열이 필요하다.
     4. 1바이트 -> 16진수로 변환해서 암호화된 문자열을 만든다. (1바이트는 16진수 2개 문자로 변환된다.)
     5. 32바이트 -> 16진수로 변환하려면 64글자가 생성된다. (DB칼럼의 크기를 VARCHAR2(64 BYTE)로 설정한다.)
     6. java.security 패키지를 이용한다.
   */

  public String getSha256(String str) {
    MessageDigest messageDigest = null;
    try {
      messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(str.getBytes());
    } catch(Exception e) {
      e.printStackTrace();
    }
    byte[] b = messageDigest.digest(); // 암호화된 32바이트 크기의 byte 배열 b가 여기서 생성된다.
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < b.length; i++) {
      sb.append(String.format("%2X", b[i]));
    }
    return sb.toString();
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
