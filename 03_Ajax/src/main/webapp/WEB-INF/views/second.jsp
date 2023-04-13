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
	function fnBmi1(){
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/second/bmi1',
			data: $('#frm').serialize(),
			
			// 응답
			dataType: 'json',
			success: function(resData){  		// resData : {"bmi": 22, "obesity": "정상"}
				$('#bmi').text(resData['bmi']); // 태그안에들어가는 택스트로 작업해야함 // 프로퍼티 포함?하는방법 두개. 대괄호랑 .으로
				$('#obesity').text(resData['obesity']); //  == resData.obesity 
			},
			
			error: function(jqXHR) { // 숫자로 안넘어왔을떄 등
				$('#bmi').text('');
				$('#obesity').text('');
				alert(jqXHR.responseText);
				
			}
			
		
		})
		
		
	}
	
	function fnBmi2(){
		
		$.ajax({
			//요청
			type: 'get',
			url: '${contextPath}/second/bmi2',
			data: $('#frm').serialize(),
			
			// 응답
			dataType: 'json',
			success: function(resData){  		// resData : {"bmi": 22, "obesity": "정상"}
				$('#bmi').text(resData['bmi']); // 태그안에들어가는 택스트로 작업해야함 // 프로퍼티 포함?하는방법 두개. 대괄호랑 .으로
				$('#obesity').text(resData['obesity']); //  == resData.obesity 
			},
			
			error: function(jqXHR) { // 숫자로 안넘어왔을떄 등
				$('#bmi').text('');
				$('#obesity').text('');
				alert(jqXHR.responseText);
				
			}
		
		})
		
		
		
	}
</script>
</head>
<body>

	<div>
		<form id="frm">
			<div>
				<label for="weight">몸무게</label> <input id="weight" name="weight" placeholder="kg">
			</div>
			<div>
				<label for="height">키</label> <input id="height" name="height" placeholder="cm">
			</div>
			<div>
				<input type="button" value="BMI계산1" onclick="fnBmi1()"> 
				<input type="button" value="BMI계산2" onclick="fnBmi2()">
			</div>
		</form>
	</div>

	<hr>

	<div>
		<h3>비만도</h3>
		<ul>
			<li>18.5미만 : 저체중</li>
			<li>24.9미만 : 정상</li>
			<li>29.9미만 : 과체중</li>
			<li>30 초과 : 비만</li>
		</ul>
		<div>
			체질량지수(BMI) : <span id="bmi"></span>
		</div>
		<div>
			비만도 : <span id="obesity"></span>
		</div>
	</div>
	
	
</body>
</html>