package com.gdu.app09.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeListService {

	public void getEmployeeListUsingPagination(HttpServletRequest request, Model model); // 담을때쓰는도구. 모델. 담을게 많을때.


}
