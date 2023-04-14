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
	/* 펑션의 요청 부분이 새로워 지니까 주의해서 보기 */
	function fn1(){
		$.ajax({
			// 요청
			type: 'post', // 서버로 보낼 데이터를 요청 본문(request body - 오늘처음배움)에 저장해서 보낸다. 겟으로하면안감
			
			url: '${contextPath}/third/ajax1', // 컨트롤러로갈떄 주소복사해서 가자
			data: JSON.stringify({ // 문자열 형식의 제이슨데이터를 서버로 보낸다. 파라미터 이름이 없음에 주의!!! 다짜고짜 데이터만 보내는 형태야. 원래같으면 앞에 파라미터이름을 붙여야하는데. 그게 없다는거임 즉, 서버에서 파라미터로 받을 수 없다!!!!
				'name' : $('#name').val(), // 사용자가 입력한 이름을 받아오고.
				'tel' : $('#tel').val()
			
			}), 
			// JSON.stringify : 객체 -> JSON(문자열) 변환하기 // 별도라이브러리가 필요하지 않는애. 얘가하는거 : 제이슨데이타만들기
			// 예시 : data: '{"name": "kim", "tel": "010"}' 이렇게 값만 있어. 파라미터 네임이 안붙고.
			contentType: 'application/json', // 서버로 보내는 data의 타입을 서버에 알려준다. // 애플리케이션 빼먹지 말기
			
			
			// 응답
			dataType: 'json',
			success: function(resData){ // resData = {"name":"민경태", "tel": "010"}
				let str = '<ul>';
				str += '<li>' + resData.name;
				str += '<li>' + resData.tel;
				$('#result').html(str); // first.jsp에서는 지우고(기존에 있던거 지우고 지우는코드맨위에썼음), append 해줬었음. 어펜드는 원래 있던거에 추가하는거라 초기화라고 보면됨 무튼 2개의 코드로 갈라졌던걸 하나의 html이라는 형태는 다르고 뜻으 같은 코드를 써준거야.
			},
			error: function(jqXHR){
				if(jqXHR.status == 400) { // 빈문자열일때 400 번 내놓는거임  = BAD_REQUEST
					alert('이름과 전화번호는 필수입니다.');
				}
				
			}
				
		})
	}
	
	
	function fn2() {
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/third/ajax2',
			data: JSON.stringify({
				'name' : $('#name').val(),
				'tel' : $('#tel').val()
			}),
			contentType: 'application/json',
			
			// 응답
			dataType: 'json',
			success: function(resData){ // resData = {"name":"민경태", "tel": "010"}
				let str = '<ul>';
				str += '<li>' + resData.name;
				str += '<li>' + resData.tel;
				$('#result').html(str); // first.jsp에서는 지우고(기존에 있던거 지우고 지우는코드맨위에썼음), append 해줬었음. 어펜드는 원래 있던거에 추가하는거라 초기화라고 보면됨 무튼 2개의 코드로 갈라졌던걸 하나의 html이라는 형태는 다르고 뜻으 같은 코드를 써준거야.
			},
			error: function(jqXHR){
				if(jqXHR.status == 400) { // 빈문자열일때 400 번 내놓는거임  = BAD_REQUEST
					alert('이름과 전화번호 입력을 확인하세요.');
				}
				
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
				<label for="tel">전화번호</label>
				<input id="tel" name="tel">
			</div>
			<div>
				<input type="button" value="전송1" onclick="fn1()">
				<input type="button" value="전송2" onclick="fn2()">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div id="result"></div>
	
	
</body>
</html>