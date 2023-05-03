package com.gdu.app09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeListService {
	public void getEmployeeListUsingPagination(HttpServletRequest request, Model model);// 담을때쓰는도구. 모델. 담을게 많을때.
	public Map<String, Object> getEmployeeListUsingScroll(HttpServletRequest request);
	public void getEmployeeListUsingSearch(HttpServletRequest request, Model model);
	public Map<String, Object> getAutoComplete(HttpServletRequest request); // 가지고온 데이터를 그대로 반환
}
