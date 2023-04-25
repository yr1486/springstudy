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
<script>

	


function fnEdit(){
	$('#edit_screen').show();
	$('#detail_screen').hide();
}

function fnRemove(){
	if(confirm('삭제할까요?')){
		$('#frm_remove').submit();
	}
}
	/* submit => 폼에 있는걸 가지고 액션으로 . 파리미터를 보내주는 거임 */
	
	function fnList(){
		location.href = '${contextPath}/board/list.do';
	}
	
	function fnBack(){
		$('#edit_screen').hide();
		$('#detail_screen').show();
	}
	
	/* https://summernote.org/ 에서 가져옴  */
	$(function(){
		$('#content').summernote({
			width: 640,
			height: 480,
			lang: 'ko-KR',
			toolbar: [
				['style', ['bold', 'italic', 'underline', 'clear']],
				['font', ['strikethrough', 'superscript', 'subscript']],
				['fontname', ['fontname']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture', 'video']],
				['view', ['fullscreen', 'codeview', 'help']]
			]
		})
		$('#edit_screen').hide();  // 최초 편집화면은 숨김
	})

</script>
</head>
<body>

	<div id="detail_screen">
		<h1>${b.boardNo}번 게시글 상세보기</h1>
		<div>제목 : ${b.title}</div>
		<div>작성자 : ${b.writer}</div>
		<div>작성일 : ${b.createdAt}</div>
		<div>수정일 : ${b.modifiedAt}</div>
		<div>${b.content}</div>
	<form id="frm_remove" action="${contextPath}/board/remove.do" method="post"> <!-- 리무브로보내는 파라미터, 보내려면 네임속성이 있어야해 중요 -->
			<input type="hidden" name="boardNo" value="${b.boardNo}"> <!-- boardNo라는 파라미터가 넘어가는거야. -->
	</form>
		<div>
			<input type="button" value="편집" onclick="fnEdit()">
			<input type="button" value="삭제" onclick="fnRemove()">
			<input type="button" value="목록" onclick="fnList()">
		</div>
	</div>

	<div id="edit_screen">
		<div style="cursor: pointer;" onclick="fnBack()"><i class="fa-solid fa-arrow-left"></i> 뒤로 가기</div>
		<h1>편집화면</h1>
		<form method="post" action="${contextPath}/board/modify.do">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" value="${b.title}">
			</div>
			<div>
				<div><label for="content">내용</label></div>
				<textarea id="content" name="content">${b.content}</textarea>  <!-- summernote 편집기로 바뀌는 textarea -->
			</div>
			<div>
				<input type="hidden" name="boardNo" value="${b.boardNo}">
				<button>수정완료</button>
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
	</div>
















</body>
</html>