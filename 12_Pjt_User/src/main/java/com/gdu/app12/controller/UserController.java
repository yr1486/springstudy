package com.gdu.app12.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app12.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
  
  // 컨트롤러의 필드 선언
  @Autowired
  private UserService userService;
  
  @GetMapping("/agree.form")
  public String agreeForm() {
    return "user/agree";
  }
  
  @GetMapping("/join.form")
  public String joinForm(@RequestParam(value="location", required=false) String location  // 파라미터 location이 전달되지 않으면 빈 문자열("")이 String location에 저장된다.
                       , @RequestParam(value="event", required=false) String event        // 파라미터 event가 전달되지 않으면 빈 문자열("")이 String event에 저장된다.
                       , Model model) {
    model.addAttribute("location", location);
    model.addAttribute("event", event);
    return "user/join";
  }
  
  // 에이작쓸거라서 프로듀스필요
  @ResponseBody
  @GetMapping(value="/verifyId.do", produces="application/json")
  public Map<String, Object> verifyId(@RequestParam("id") String id) {
    return userService.verifyId(id);
  }
  
  @ResponseBody
  @GetMapping(value="/verifyEmail.do", produces="application/json")
  public Map<String, Object> verifyEmail(@RequestParam("email") String email) {
    return userService.verifyEmail(email);
  }
  
  @ResponseBody
  @GetMapping(value="/sendAuthCode.do", produces="application/json")
  public Map<String, Object> sendAuthCode(@RequestParam("email") String email) {
    return userService.sendAuthCode(email);
  }
  
  @PostMapping("/join.do")
  public void join(HttpServletRequest request, HttpServletResponse response) {
    userService.join(request, response);
  }
  
  @GetMapping("/login.form")
  public String loginForm(@RequestHeader("referer") String url, Model model) { // 로그인 폼으로 이동할때,
    // 요청 헤더 referer :  로그인 화면으로 이동하기 직전의 주소를. 저장하는 헤더값
    model.addAttribute("url", url);
    
    return "user/login";
  }
  
  @PostMapping("/login.do")
  public void login(HttpServletRequest request, HttpServletResponse response) {
    userService.login(request, response);
  }
  
  @GetMapping("/logout.do")
  //
  public String requiredLogin_logout(HttpServletRequest request, HttpServletResponse response) {
    // 로그인이 되어 있는지 확인
    userService.logout(request, response);
    return "redirect:/";
  }
  
  @GetMapping("/leave.do")
  public void requiredLogin_leave(HttpServletRequest request, HttpServletResponse response) {
    // 로그인이 되어 있는지 확인
    userService.leave(request, response);
  }
  
  // userController 에 있는 모든 메소드는 joinPointCut
  
  
  
  @GetMapping("/wakeup.form")
  public String wakeup() {
    return "user/wakeup";
  }
  
  @GetMapping("/restore.do")
  public void restore(HttpSession session) {
    // 복원할 회원의 아이디를 sysout으로 출력해보시오.
    System.out.println(session.getAttribute("sleepUserId"));
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 
 
 
 
 
 
 
 
 
}
