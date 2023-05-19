package com.gdu.app12.intercept;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.gdu.app12.domain.UserDTO;
import com.gdu.app12.mapper.UserMapper;

@Component
public class AutologinInterceptor implements HandlerInterceptor {
  
  @Autowired
  private UserMapper userMapper;
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession();
    
    if(session != null && session.getAttribute("loginId") == null) {
      
      Cookie cookie = WebUtils.getCookie(request, "autoLoginId");
      if(cookie != null) {
        
        String autoLoginId = cookie.getValue();
        UserDTO loginUserDTO = userMapper.selectAutologin(autoLoginId);
        if(loginUserDTO != null) {
          session.setAttribute("loginId", loginUserDTO.getId());
        }
      }
    }
    
    
    return true; // 인터셉터를 동작 시킨 두 컨트롤러를 계속 동작 시킨다.
  }
  
}

