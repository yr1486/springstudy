package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {

	// 컨트롤러가 사용하는 필드는 서비스야.
	@Autowired
	private StaffService staffService;
	
	// 어레이리스트를 반환하기로 했으니까.
	// 보통 Map, responseEntity, List를 사용하는 선택지가 있음
	// 단 Map을 사용하려면 @ResponseBody 
	// 단 responseEntity 는 @ResponseBody 필요없음
	/*
	@ResponseBody // 맵을 사용할경우 꼭 있어야함. // responseEntity로 가면 없어도됨.
	@GetMapping("/list.json")
	public List<StaffDTO> {
	
	}
	*/
	
	@ResponseBody 
	@GetMapping(value="/list.json", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StaffDTO> list1() {
		return staffService.getStaffList1();
	} // 프로듀스 속성값을 통해 알려주고 있고.
	
	// 만들고자하는 서비스 이름. 짓고. 서비스로 가자.

	
//	@GetMapping("/list.json")
	public ResponseEntity<List<StaffDTO>> list2() {
		return staffService.getStaffList2();
	} // 얘는 프로듀스가 없지만. 서비스 본문에 나와 있고.
	
	// 이런 선택의 코드 차이점이 있음.
	// 둘중에 하나의 방법으로 진행하기.

	//@ResponseBody
	//@PostMapping(value="/add.do", produces="text/plain; charset=UTF-8")
	public String add1(HttpServletRequest request) {
		return staffService.addStaff1(request);
		
	}
	
	
	
	@PostMapping(value="/add.do", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> add2(StaffDTO staffDTO) {
		return staffService.addStaff2(staffDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}