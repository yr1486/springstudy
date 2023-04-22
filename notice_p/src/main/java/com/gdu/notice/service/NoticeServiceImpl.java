package com.gdu.notice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.notice.domain.NoticeDTO;
import com.gdu.notice.repository.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	// 모든 공지사항 목록을 반환하시오.
	@Override
	public List<NoticeDTO> getNoticeList() {
		
		return noticeDAO.getNoticeList();
	}

	// 파라미터 notice_no가 전달되지 않았다면 notice_no=0을 사용하여 상세 조회 결과를 반환하시오.
	@Override
	public NoticeDTO getNotice(HttpServletRequest request) {
		int result = 0;
		
		String str = request.getParameter("notice_no");
		
		if(str != null) {
			result = Integer.parseInt(str);
		}
	
		return noticeDAO.getNotice(result);
	}

	// 파라미터 gubun, title, content 값을 notice 테이블에 삽입하고, 삽입 결과를 반환하시오.
	@Override
	public int addNotice(HttpServletRequest request) {
		
		int gubun = Integer.parseInt(request.getParameter("gubun"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeDTO notice = new NoticeDTO(0, gubun, title, content);
		
		
		return noticeDAO.addNotice(notice); // 다오야 가지고 , 리턴해라.
	}

	
	// 까지 숙제.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 파라미터 notice_no, gubun, title, content 값을 가진 NoticeDTO 객체를 이용하여 notice 테이블을 수정하고, 수정 결과를 반환하시오.
	@Override
	public int modifyNotice(HttpServletRequest request) {
		
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		int gubun = Integer.parseInt(request.getParameter("gubun"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeDTO notice = new NoticeDTO(notice_no, gubun, title, content);
		
		return noticeDAO.modifyNotice(notice);
	}

	// 파라미터 notice_no가 전달되지 않았다면 notice_no=0을 사용하여 notice 테이블에서 삭제하고, 삭제 결과를 반환하시오.
	@Override
	public int removeNotice(HttpServletRequest request) {
		
		int notice_no = 0;
		
		String strNo = request.getParameter("notice_no");
		
		if(strNo != null) {
			
			notice_no = Integer.parseInt(strNo);
			
		}
		
		return noticeDAO.removeNotice(notice_no);
	}

}





