<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title eq null ? 'Welcome' : param.title}</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/init.css?dt=${dt}" /> <!-- css외부파일을 포함하는방법임 , 사용하지 않는 가짜 파라미터를 붙여줌-->
<link rel="stylesheet" href="${contextPath}/resources/css/header.css?dt=${dt}" /> <!-- css외부파일을 포함하는방법임 -->



</head>
<body>

  <div class="header_section">
    <div class="title_menu_wrap">
      <div class="user_menu">
        <span><a href="/user/login.form">로그인</a></span>
        <span><a href="/user/agreeform">회원가입</a></span>
      </div>
      
      <h1 class="title">
        <a href="/">Welcome To My Home</a>
      </h1>
    </div>
    <div class="gnb_wrap">    
      <div class="gnb">
          <ul>
              <li class="dropdown">
                <span>사원목록</span>
                <div class="dropdown_content">
                  <div><a href="/emp/list_scroll.forml">무한스크롤</a></div>
                  <div><a href="/emp/list_paging.form">페이징</a></div>
                  <div><a href="/emp/list_search.form">검색</a></div>
                </div>
              </li>
              <li><a href="/bbs/list.do">계층게시판</a></li>
              <li><a href="/blog/list.do">블로그</a></li>
              <li><a href="/upload/list.do">업로드게시판</a></li>
              <li><a href="/chat.form">실시간채팅</a></li>
          </ul>
      </div>
    </div>
  </div>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

















</body>
</html>