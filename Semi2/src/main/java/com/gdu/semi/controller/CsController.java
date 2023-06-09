package com.gdu.semi.controller;

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

import com.gdu.semi.service.CsService;

@RequestMapping("/cs")
@Controller
public class CsController {

	// field
	@Autowired
	private CsService csService;

	 @GetMapping("/notice.do")
	  public String notice() {
	    return "cs/notice";
	  }
	 
	  @GetMapping("/qna.do")
	  public String qna() {
	    return "cs/qna";
	  }
	  
	  @GetMapping("/faq.do")
	  public String faq() {
	    return "cs/faq";
	  }
	
	
	@GetMapping("/list.do")
	public String list(HttpServletRequest request, Model model) {
		csService.getCsList(request, model);
		return "cs/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "cs/write";
	}
	
	@PostMapping("/add.do")
	public String add(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
		int csResult = csService.addCs(multipartRequest);
		redirectAttributes.addFlashAttribute("csResult", csResult);
		
		return "redirect:/cs/list.do";
	
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="csNo", required=false, defaultValue="0") int csNo
					   , Model model) {
		csService.getCsByNo(csNo, model);
		return "cs/detail";
	}
	
	@GetMapping("/display.do")
	public ResponseEntity<byte[]> display(@RequestParam("attachNo") int attachNo) {
		return csService.display(attachNo);
	}
	
	
	// a링크로 요청했으니. 갭맵핑 
	// 다운로드하면 페이지는 안바뀜
	// 페이지 안바뀌는 방식은 : ajax! 그럼 반환타입은 : responseEntity 또는 Map이야
	@GetMapping("/download.do")
	public ResponseEntity<Resource> download(@RequestParam("attachNo") int attachNo, @RequestHeader("User-Agent") String userAgent) {
		//                                     파라미터가 있다는 뜻.  // 파람대신 httpRequest도 가능           
		return csService.download(attachNo, userAgent);
	}
	
	@GetMapping("/downloadAll.do")
	public ResponseEntity<Resource> downloadAll(@RequestParam("csNo") int csNo) {
		//                                     파라미터가 있다는 뜻.  // 파람대신 httpRequest도 가능           
		return csService.downloadAll(csNo);
	}
	
	
	@PostMapping("/removeCs.do")
	public String removeUpload(@RequestParam("csNo") int csNo, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", csService.removeCs(csNo));
		return "redirect:/cs/list.do";
	}
	
	 @PostMapping("/editCs.do")
	  public String editUpload(@RequestParam("csNo") int csNo, Model model) {
	    csService.getCsByNo(csNo, model);
	    return "cs/edit";
	  }
	  
	  @PostMapping("/modify.do")
	  public String modify(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
	    int modifyResult = csService.modifyCs(multipartRequest);
	    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
	    return "redirect:/cs/detail.do?csNo=" + multipartRequest.getParameter("csNo");
	  }
	  
	  @GetMapping("/removeAttach.do")
	  public String removeAttach(@RequestParam("csNo") int csNo, @RequestParam("attachNo") int attachNo) {
	    csService.removeAttach(attachNo);
	    return "redirect:/cs/detail.do?csNo=" + csNo;
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
