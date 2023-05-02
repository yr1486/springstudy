package com.gdu.app09.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/employees/change/record.do")  // .do안해도 되지만, .do하면 맵핑인걸 알아라.
	public String changeRecord(HttpSession session
			                 , HttpServletRequest request
			                 , @RequestParam(value="recordPerPage", required=false, defaultValue="10") int recordPerPage) {
		session.setAttribute("recordPerPage", recordPerPage);
		return "redirect:" + request.getHeader("referer");  // 현재 주소(/employees/change/record.do)의 이전 주소(Referer)로 이동하시오.
	}
	
	@GetMapping("/employees/scroll.page")
	public String scrollPage() {
		return "employees/scroll";
	}
	
	@ResponseBody // 여기서 리턴하는 데이터가. 에이작으로 반환하는 데이터가 될 수 있도록 달아주는 애.
	@GetMapping(value="/employees/scroll.do", produces="application/json") //이 맵을 json타입으로 잭슨이 바꿔줄거임.
	public Map<String, Object> scroll(HttpServletRequest request) { //  Map<String, Object>
		return employeeListService.getEmployeeListUsingScroll(request);
	}
	
	@GetMapping("/employees/search.do")
	public String search(HttpServletRequest request, Model model) {
		employeeListService.getEmployeeListUsingSearch(request, model);
		return "employees/search";
	}
	
	@ResponseBody // 여기서 리턴하는 데이터가. 에이작으로 반환하는 데이터가 될 수 있도록 달아주는 애.
	@GetMapping(value="/employees/autoComplete.do", produces="application/json")
	public Map<String, Object> autoComplete(HttpServletRequest request) {
		return employeeListService.getAutoComplete(request);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}