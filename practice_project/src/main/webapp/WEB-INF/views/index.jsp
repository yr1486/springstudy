<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>


<script>

	$(function(){
		// C:\GDJ61\images 디렉터리에 저장된 flow1~5.jpg 화면에 표시하기
		// 경로(path)와 파일명(filename)을 전달하면 해당 이미지를 화면에 출력하는 연습
		
		// Java에서 이미지를 byte 배열로 저장해서 Jsp로 보내면 이미지가 나타난다.
		
		
		for(let n = 1; n <= 4; n++) {
			let filename = 'poster' + n + '.jpg';
			let str = '<span>';
			str += '<a href="${contextPath}/poster' + n +'.do">'
			str += '<img src="${contextPath}/resources/images/' + filename + '" width="100px">';
			$('#result').append(str); // 반복문에 표시하려면 추가해야한다. 덮어쓰기하면 안된다는 뜻
		}
	
	})

</script>
</head>
<body>
	
	<!-- 컨택스트패스가 웹앱폴더야 -->
	<span id="result"></span>


</body>
</html>