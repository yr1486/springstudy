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
		return null;
	}

	// 파라미터 notice_no가 전달되지 않았다면 notice_no=0을 사용하여 상세 조회 결과를 반환하시오.
	@Override
	public NoticeDTO getNotice(HttpServletRequest request) {
		return null;
	}

	// 파라미터 gubun, title, content 값을 notice 테이블에 삽입하고, 삽입 결과를 반환하시오.
	@Override
	public int addNotice(HttpServletRequest request) {
		return 0;
	}

	// 파라미터 notice_no, gubun, title, content 값을 가진 NoticeDTO 객체를 이용하여 notice 테이블을 수정하고, 수정 결과를 반환하시오.
	@Override
	public int modifyNotice(HttpServletRequest request) {
		return 0;
	}

	// 파라미터 notice_no가 전달되지 않았다면 notice_no=0을 사용하여 notice 테이블에서 삭제하고, 삭제 결과를 반환하시오.
	@Override
	public int removeNotice(HttpServletRequest request) {
		return 0;
	}

}
