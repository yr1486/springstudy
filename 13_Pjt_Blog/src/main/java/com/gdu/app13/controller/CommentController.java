package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app13.service.CommentService;

@RequestMapping("/comment")
@Controller
public class CommentController {

  @Autowired
  private CommentService commentService;
  
  @PostMapping(value="/addComment.do", produces="application/json")
  @ResponseBody
  public Map<String, Object> addComment(HttpServletRequest request) {
    return commentService.addComment(request);
  }
  
  
  
  
  
  
  
  
  
  
  
}