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



	function fn1() {
		$('#result').empty(); // 결과창 초기화 한번
		
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/first/ajax1',
			data: 'name=' + $('#name').val() + '&age=' + $('#age').val(),
			
			// 응답
			dataType: 'json',
			success: function(resData){ // resData = {"name": "민경태", "age": 46}
																//사용자가입력한값을 그대로 받을거임
				// ul태그 만들어서 뿌리자
				let str = '<ul>';
				str += '<li>' + resData.name + '</li>';
				str += '<li>' + resData.age + '</li>';
				str += '</ul>';
				$('#result').append(str);
				// 어떻게 보낼지
			},
			error: function(jqXHR){
				$('#result').append(jqXHR.responseText);
			}
		})
	}
	
	function fn2(){
		$('#result').empty();
		
		$.ajax({
			type: 'get',
			url: '${contextPath}/first/ajax2',
			data: $('#frm').serialize(), // 시리얼라이즈써서 파라미터 보내겠다 할꺼면 네임을 꼭 넣어줘야 해
			
			dataType: 'json',
			success: function(resData){ // resData = {"name": "민경태", "age": 46}
				//사용자가입력한값을 그대로 받을거임
				// ul태그 만들어서 뿌리자
				let str = '<ul>';
				str += '<li>' + resData.name + '</li>';
				str += '<li>' + resData.age + '</li>';
				str += '</ul>';
				$('#result').append(str);

			}
	
		})
	}
	
	function fn3(){
		$('#result').empty();
		
		$.ajax({
			type: 'get',
			url: '${contextPath}/first/ajax3',
			data: $('#frm').serialize(),
			
			dataType: 'json',
			success: function(resData){
				let str = '<ul>';
				str += '<li>' + resData.name + '</li>';
				str += '<li>' + resData.age + '</li>';
				str += '</ul>';
				$('#result').append(str);
			}
			
		})
	}

</script>

</head>
<body>


	<div>
		<form id="frm">
			<div>
				<label for="name">이름</label>
				<input id="name" name="name">
			</div>
			<div>
				<label for="age">나이</label>
				<input id="age" name="age">
			</div>
			<div>
				<input type="button" value="전송1" onclick="fn1()">
				<input type="button" value="전송2" onclick="fn2()">
				<input type="button" value="전송3" onclick="fn3()">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div id="result"></div>
	

</body>
</html>