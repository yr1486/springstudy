<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>


		<div>
			<h4><a href="${contextPath}/cs/list.do">공지사항</a></h4>
		</div>
		
		리스트 뿌리기

	<hr>
	
		<div>
			<h4><a href="${contextPath}/cs/write.do">1:1 문의 작성하러 가기</a></h4>
		</div>
		

	<hr>
	
		<div>
			<h4><a href="${contextPath}/cs/list.do">FAQ</a></h4>
		</div>

	<hr>

	
		
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>cs게시글번호</td>
					<td>제목</td>
					<td>작성일자</td>
					<td>첨부개수</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${csList}" var="cs">
					<tr>
						<td>${cs.csNo}</td>
						<td><a href="${contextPath}/cs/detail.do?csNo=${cs.csNo}">${cs.csTitle}</a></td>
						<td>${cs.createdAt}</td>
						<td>${cs.attachCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
	
</body>
</html>