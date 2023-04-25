package com.gdu.app09.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app09.service.EmployeeListService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeListService employeeListService;
	
	@GetMapping("/employees/pagination.do")
	public String pagination(HttpServletRequest request, Model model) {
		employeeListService.getEmployeeListUsingPagination(request, model);
		return "employees/pagination";
	}
	
	@GetMapping("/employees/change/record.do") // .do안해도 되지만, .do하면 맵핑인걸 알아라.
	public String changeRecord(HttpSession session
            					, HttpServletRequest request
            					, @RequestParam(value="recordPerPage", required=false, defaultValue="10") int recordPerPage) { // @RequestParam 안쓰고 숨길수 있음
			session.setAttribute("recordPerPage", recordPerPage);
			return "redirect:" + request.getHeader("referer"); // 현재 주소(/employees/change/record.do)의 이전 주소(Referer)로 이동하시오.   //맵핑으로 가고싶을때쓰는거  리다이렉트    // 세션에 올리고나면 다시 목록을 펼치기로 함
	}

	
		
}
