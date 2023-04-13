package com.gdu.app02.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller // 얘가 애너테이션의 한 종류임
public class PostController {
	
	@GetMapping("/post/detail.do")
		// 3가지 방법이 있음
		// 1.
		public String detail(HttpServletRequest request) throws Exception { //파라미터가 name,age가 있어
		// 2. public String detail(@RequestParam("name") String name, @RequestParam("age") int age) { }
		// 3. public String detail(Person p) { }
			
			// 받아보자
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			
			System.out.println("/post/detail.do");
			System.out.println("name: " + name + " age: " + age);
			
			
			
			
			// return "redirect:이동경로"; 
			// 리다이렉트는 특정맵핑을 요청해야 한다. 포워드할떄는이동경로가jsp인데, 리다이렉트는jsp가아니야!!!!!
			// add.do를 삽입 - 리다이렉트 - list.do로 와야 삽입한 내용을 볼 수 있음. 목록보기에서. 그래서 삽입수정삭제가 리다이렉트임. 돌아가는 포워드가 아니고
			// 근데 수정은 상세보기로 넘어가기도 했음 수정한걸 다시 봐야하니까.detail.do해도되고 list.do해도되고
			// 그래도 삽입수정삭제는 리다이렉트인게 맞음
			
			return "redirect:/post/list.do?name=" + URLEncoder.encode(name, "UTF-8") + "&age=" + age; // name, age파라미터를 다시 붙이기 전달하고싶으면
					// redirect:/post/list.do까지하면 리다이렉트라서 전달이안돼 그래서 전달하고 싶으면 name하고 age를 파라미터를 붙여준다.
					// 리다이렉트다음에 나오는건 맵핑으로 해석하기!! 맵핑으로 이동하시오.
					// 슬래시로시작하는맵핑 연습중
					// jsp아님 주의.
			
			// 리다이렉트는 두번이니까!!!
			// 인덱스에서 넘어와서 리다이렉트 위에한번 받고. 여기서 한번더받고 두번.
			
	}
	
	
			@GetMapping("/post/list.do")
			public String list(HttpServletRequest request,  // name, age파라미터가 있다.
								Model model) {
				// 리다이렉트니까. 전달이 안되는디 위에 리턴에서 파라마터 붙여줘서 되는거임
			
				String name = request.getParameter("name");
				String age = request.getParameter("age");
				
				model.addAttribute("name", name);
				model.addAttribute("age", age);
				
				// /WEB-INF/views/post/list.jsp로 forward하겠다.
				return "post/list";

			
			
			
			// 두번의 걸쳐서 이동된 결과를 살펴본거임
			// 리다이렉트 뒤 경로는 무조건 맵핑이다.
			// 리다이렉트는 새로운 경로를 만드는거기떄문에 필요하면 파라미터를 가져다 붙여라.
				
		}
	
			// ------------------------------------------------------------------
			
			
		@GetMapping("/post/detail.me")
		public String detailMe(HttpServletRequest request,
								RedirectAttributes redirectAttributes) { // Redirect할때 속성(애트리뷰트)을 전달하는 스프링 인터페이스 (마치 포워드해주는)
			// 					리다이렉트할때, 속성저장해주는애. 원래 리다이렉트는 전달이 안되는데. 얘쓰면 되는거임
			
		
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			
			// Redirect 경로까지 전달되는 속성 : Flash Attribute
			redirectAttributes.addFlashAttribute("name", name); // addAttribute쓰면 나가리. 모델1이랑 같은 개념이래. 포워드에서는 전달이되겠지만. 리다이렉트는 안됨.
			redirectAttributes.addFlashAttribute("age", age);
			
			return "redirect:/post/list.me";
			
		}
		
		
		
		@GetMapping("/post/list.me")
		public String listMe()	 { // Flash Attribute는 Redirect 경로까지 자동으로 전달되므로 별도의 코드가 필요하지 않다.
			return "post/list";
		}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

}
