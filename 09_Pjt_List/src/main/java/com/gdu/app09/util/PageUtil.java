package com.gdu.app09.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component // 이 아래것들을 객체로 만들어주는애
@Getter
public class PageUtil {

	private int page;			 // 현재 페이지(파라미터로 받아온다.페이지를 알아야 모든 계산이 가능함.)
	private int totalRecord;	 // 전체 레코드(DB에서 구해온다) 						1~26
	private int recordPerPage;   // 한 페이지에 표시할 레코드 개수(파라미터로 받아온다) 1~5
	private int begin;			 // 한 페이지에 표시할 레코드의 시작 번호(계산한다)
	private int end;			 // 한 페이지에 표시할 레코드의 종료 번호(계산한다)
	
	// 비긴과 엔드의 기준이 ROWNUM 이다.
	
	
	private int pagePerBlock = 5; //  한 블록에 표시할 페이지 수(임의로 정한다, 받아오는 정보가 아닌 임의로 지정함.)(아래 페이지가 몇개인지 표시할 페이지 수), 
	private int totalPage;		  // 전체 페이지 개수(계산한다)
	private int beginPage;		  // 한 블록에 표시할 페이지의 시작 번호(계산한다)
	private int endPage;	      // 한 블록에 표시할 페이지의 종료 번호(계산한다)
	
	
	
	
	public void setPageUtil(int page, int totalRecord, int recordPerPage) {
		
				// page, totalRecord, recordPerPage 저장
				this.page = page;
				this.totalRecord = totalRecord;
				this.recordPerPage = recordPerPage;
			
				// begin, end 계산
				/*
				 	totalRecord = 26, recordPerPage=5인 상황
				 	page 	begin 	end 
				 	1		1		5
				 	2		6		10
				 	3		11		15
				 	4		16		20
				 	5		21		25
				 	6		26		26				
				 */

				begin = (page - 1) * recordPerPage + 1;
				end = begin + recordPerPage - 1;
				if(end > totalRecord) {
					end = totalRecord;
				}
				
				// totalPage 계산
				totalPage = totalRecord / recordPerPage;
				// 5      =     26      / 5 가 되버리니까. +1을 해줘야한다. 나눠서 나머지가 있는애들은.(=> 26~29까지 애들)
				if(totalRecord % recordPerPage != 0) {
					totalPage++;
				}
		
		// beginPage, endPage 계산
		/*
		 	totalPage=6, pagePerBlock=4인 상황
		 	block							bigin		endPage
		 	1(페이지가 1~4인 상황)			1			4
		 	2(페이지가 5~6인 상황)			5			6
		 */
				beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
				endPage = beginPage + pagePerBlock - 1;
				if(endPage > totalPage) {
					endPage = totalPage;
				}
		
	}
	
	
	/*
	  1 2 3 4 5>
	 <6 7 8 9 10>
	 <11
	 */
	 public String getPagination(String path) {
	
		 // path에 ? 가 포함되어 있으면, 이미 파라미터가 포함된 경로이므로, &를 붙여서  page 파라미터를 추가한다.
			if(path.contains("?")) {
				path += "&";  // path = "/app09/employees/pagination.do?order=ASC&"
			} else {
				path += "?";  // path = "/app09/employees/pagination.do?"
			}
		 
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class=\"pagination\">");
		
	 // 이전 블록 : 1블록은 이전 블록이 없고, 나머지 블록은 이전 블록이 있다.
			if(beginPage == 1) {
				sb.append("<span class=\"hidden\">◀</span>");
			} else {
				sb.append("<a class=\"link\" href=\"" + path + "page=" + (beginPage - 1) + "\">◀</a>");
			}
		
			// 페이지번호 : 현재 페이지는 링크가 없다.
			for(int p = beginPage; p <= endPage; p++) {
				if(p == page) {
					sb.append("<span class=\"strong\">" + p + "</span>");
				} else {
					sb.append("<a class=\"link\" href=\"" + path + "page=" + p + "\">" + p + "</a>");
				}
			}
			
			// 다음 블록 : 마지막 블록은 다음 블록이 없고, 나머지 블록은 다음 블록이 있다.
			if(endPage == totalPage) {
				sb.append("<span class=\"hidden\">▶</span>");
			} else {
				sb.append("<a class=\"link\" href=\"" + path + "page=" + (endPage + 1) + "\">▶</a>");
			}
			
			sb.append("</div>");
			
			return sb.toString();
			
		}
		
	}
