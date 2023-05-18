package com.gdu.app12.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PreventLoginInterceptor implements HandlerInterceptor {
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
   
    HttpSession session = request.getSession();
    // 로그인 여부 확인
    if(session != null && session.getAttribute("loginId") != null) {
      
      // 응답
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('해당 기능은 사용할 수 없습니다.')");
        out.println("history.back();");
        out.println("</script>");
        out.flush();
        out.close();
        
        return false; // aop가 인터셉터로 바뀌는 부분 => 컨트롤러의 요청이 처리되지 않는다.
    
    }
    
    return true; // 컨트롤러의 요청이 처리된다.
  }
  
  
}
