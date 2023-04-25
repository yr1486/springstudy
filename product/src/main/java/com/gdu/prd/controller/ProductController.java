package com.gdu.prd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.prd.domain.ProductDTO;
import com.gdu.prd.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/list.do")
	public String list(Model model) { // 오늘배운거 적용하면 : ()안에 페이지파라미터가 있어야해.  pagination하려면. 오늘 수업까지는 없음.
		productService.loadProductList(model);
		return "product/list";	
	}
	// 여기 매개변수 모델과 서비스임플의 매개변수 모델은 같은거야!! 주소값이 담긴거라 완벽하게 같은 값이라고 생각하면됨.
	// 모델값을 서비스로 넘기고 사용하는거니까 알아두기. 참조값참조값.

	// 모델을 쓰는이유. 모델에 값을 저장하는 이유는 포워드하려고. 전달하려고.
	// 포워드는 return에 따로 표기안해도됨. 그럼 포워드. 리다이렉트는 따로 표기해줘야함. return redirect~~~


	// 이번 보충은 리퀘스트 사용안하고 진행
	
	@PostMapping("/add.do") // 프러덕트디티오에는 객체로가져와서그렇지 2개의 값이 있는거, 이걸 두개로 나눈다면 
	public String add(ProductDTO productDTO, RedirectAttributes redirectAttributes) { // 리다이렉트할때 정보를 전달할 목적으로 쓴다.
		/* 1 */ int addResult = productService.addProduct(productDTO); // 반환타입은 인트, 매개변수는 프러덕디티오!! 이거가지고 서비스인터페이스로가.
		/* 3 */ redirectAttributes.addFlashAttribute("addResult", addResult); // addAttribute 쓰지않기. 두번보내야하는데 이걸 할 수 있는게 플래시이고, 애드에트리뷰트  포워드할떄만 쓸 수 있다. 
		/* 3 */ return "redirect:/product/list.do";
	}













}
