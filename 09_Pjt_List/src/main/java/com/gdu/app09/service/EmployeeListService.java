package com.gdu.app09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeListService {

	public void getEmployeeListUsingPagination(HttpServletRequest request, Model model); // 담을때쓰는도구. 모델. 담을게 많을때.
	public Map<String, Object> getEmployeeListUsingScroll(HttpServletRequest request); // 담을때쓰는도구. 모델. 담을게 많을때.


}
