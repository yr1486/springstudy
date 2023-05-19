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
<script>
	var frm;
	$(function() {
		frm = $('#frm');
	})
	
	function fnRemoveCs(){
		if(confirm('게시글을 삭제하면 모든 첨부 파일이 함께 삭제 됩니다. 그래도 삭제하시겠습니까?') == false){
			return;
		}
		frm.prop('action', '${contextPath}/cs/removeCs.do');
		frm.submit();
	}
</script>
</head>
<body>

	<div>
		<h1>${cs.csNo}번 cs 게시글</h1>
		<ul>
			<li>제목 : ${cs.csTitle}</li>
			<li>내용 : ${cs.csContent}</li>
			<li>작성일자 : ${cs.createdAt}</li>
			<li>수정일자 : ${cs.modifiedAt}</li>
		</ul>
		
		<form id="frm" method="post">
			<input type="hidden" name="csNo" value="${cs.csNo}">
			<input type="button" value="삭제" onclick="fnRemoveCs()">
		</form>
	</div>
	
	<hr>
	
	<div>
		<h4>첨부 목록 및 다운로드</h4>
		<c:if test="${empty attachList}">
			<div>첨부된 파일이 없습니다.</div>
		</c:if>
		<c:if test="${not empty attachList}" var="attach"></c:if>
		<div>
			<c:forEach items="${attachList}" var="attach">
				<div>
					<a href="${contextPath}/cs/download.do?attachNo=${attach.attachNo}">
						<c:if test="${attach.hasThumbnail == 1}">
							<img src="${contextPath}/cs/display.do?attachNo=${attach.attachNo}">
						</c:if>
						<c:if test="${attach.hasThumbnail == 0}">
							<img src="${contextPath}/resources/images/attach1.png" width="50px">
						</c:if>
						${attach.originName}
					</a>
				</div>
			</c:forEach>
			<div>
				<a href="${contextPath}/cs/downloadAll.do?csNo=${cs.csNo}">모두 다운로드</a>
			</div>
		</div>
	</div>
	
</body>
</html>