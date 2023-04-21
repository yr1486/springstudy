<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<style>
	/* 제목,작성자,작성일자 어디를 선택하든 클릭할 수 있게. */
	tbody tr:hover {
		background-color: beige;
		cursor: pointer;
	}
	
</style>
<script>
	function fnDetail(n) {
		location.href = '${contextPath}/board/detail.do?boardNo=' + n;
	}
	
	$(function(){
		let addResult = '${addResult}'; // 	let addResult = '1'; 삽입성공
										// 	let addResult = '0'; 삽입실패
										// let addResult = ''; 삽입과 상관 없음.
		
		if(addResult != '')	 {
			if(addResult == '1'){
				alert('게시글이 등록되었습니다.');
			}
			else{
				alert('게시글 등록이 실패했습니다.');
			}
		}
	})
	/* 코드가 전달이 안될때를 생각해야해 1,0이 아니면 => let addResult = ; 형태가 된다. 그럼 빨간줄이 생기겠지.
	그래서 문자열에다가 담아줄거야 왜냐. ;대신 빈문자열은 가능하니까.
	
	let addResult = '${addResult}'; 이렇게.
	
	*/
	$(function(){
		let removeResult = '${removeResult}'; // 	let addResult = '1'; 삽입성공
										// 	let addResult = '0'; 삽입실패
										// let addResult = ''; 삽입과 상관 없음.
		
		if(removeResult != '')	 {
			if(removeResult == '1'){
				alert('게시글이 삭제 되었습니다.');
			}
			else{
				alert('게시글 삭제가 실패했습니다.');
			}
		}
	})
	
	
	
	
	
	
	
	
</script>
</head>
<body>

	<br>
	<!-- boardController에서 처리되는거야 -->
	<div>
		<a href="${contextPath}/board/write.do">새글작성하기</a>
		
	</div>
	
	<br>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>제목</td>
					<td>작성일</td>
					<td>작성일자</td>
				</tr>
			</thead>
			
			<tbody>
				<c:if test="${empty boardList}">
					<tr>
						<td colspan="3">첫 게시글의 주인공이 되어 보세요!</td>
					</tr>
					
				</c:if>
				<c:if test="${not empty boardList}">
					<c:forEach items="${boardList}" var="b">
					
						<tr onclick="fnDetail(${b.boardNo})">
							<td>${b.title}</td>
							<td>${b.writer}</td>
							<td>${b.createdAt}</td>
						</tr>
						
					</c:forEach>
				</c:if>
			</tbody>
			
		</table>
		
		<!-- b는 boardDTO임, 자바문법도 가능하지만,
		b.getTitle , b.title로써도 el이 알아서 b.getTitle로 써준다
		b.title로 써라. -->
	</div>


</body>
</html>