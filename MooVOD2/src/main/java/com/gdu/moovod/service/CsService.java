package com.gdu.moovod.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface CsService {
	
  // 페이지네이션
  public void loadcsList(HttpServletRequest request, Model model);
  
	public int addCs(MultipartHttpServletRequest multipartRequest);
	public void getCsByNo(int csNo, Model model);
	public ResponseEntity<byte[]> display(int attachNo);
	public ResponseEntity<Resource> download(int attachNo, String userAgent);
	public ResponseEntity<Resource> downloadAll(int csNo);
	public int removeCs(int csNo);
	public int modifyCs(MultipartHttpServletRequest multipartRequest);
	public int removeAttach(int attachNo);

}
