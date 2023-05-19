package com.gdu.moovod.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.moovod.service.CsService;

@RequestMapping("/qna")
@Controller
public class CsController {

	// field
	@Autowired
	private CsService csService;
	
  @GetMapping("/main.do")
  public String main() {
    return "qna/main";
  }

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @GetMapping("/qna.do")
  public String qna(HttpServletRequest request, Model model) {
    csService.loadcsList(request, model);
    return "cs/qna";
  }
  
  @GetMapping("/write.do")
  public String write() {
    return "cs/write";
  }
  
  @PostMapping("/add.do")
  public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    int csResult = csService.addCs(request);
    redirectAttributes.addFlashAttribute("csResult", csResult);
    
    return "redirect:/cs/qna.do";
  
  }
  
  
  //
  
  
	 @GetMapping("/notice.do")
	  public String notice() {
	    return "cs/notice";
	  }
	  
	  @GetMapping("/faq.do")
	  public String faq() {
	    return "cs/faq";
	  }
	
}
