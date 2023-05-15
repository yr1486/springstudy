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
			
			error: function(jqXHR) { // 숫자로 안넘어왔을떄 등 // 지금 이 화면에 사용자가누르고 동작하는거를 세컨드서비스임플에서 구현하고있어. 예를들면 트라이캐치를 통해서 여기 펑션에 성공했을떄, 실패했을떄를 트라이캐치에 빗대어서 한번 봐보기.
				$('#bmi').text('');
				$('#obesity').text('');
				
				//아래와 같은 문장 : alert(jqXHR.responseText + '(' + jqXHR.status + ')');
				if(jqXHR.status == 500){ // 500: 세컨서비스임플에서 INTERNAL_SERVER_ERROR 부분이 500인거. 같은 뜻임.
					alert('몸무게와 키 입력을 확인하세요.')
				}
				
			}
			
		
		})
		
		
	}
	
	function fnBmi2(){
		let weight = $('#weight').val();
		if(weight == '' || Number(weight) < 0 || isNaN(weight)) { // || 낫넘이다. 숫자가아니다.
			alert('몸무게를 확인하세요');
			return; //리턴:더이상 코드를 실행하지 마시오. 를 걸어주니까 더이상 백단으로 코드가 넘어가지 않는거임.
			// 지금 if문은. 프론트에서 일어날수있는. 사용자가 몸무게 입력안하고 전송버튼 눌럿을떄. 그럼안되니까 이거 막이주는 코드를 프론트단에서 해준거라고 할 수 있음.
		}
			// 똑같이 if문 코드를 height도 만들어주면됨.
			
		let height = $('#height').val();
		if(height == '' || Number(height) < 0 || isNaN(height)) { // || 낫넘이다. 숫자가아니다.
			alert('키를 확인하세요');
			return;
		}
		
		
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
				// alert(jqXHR.responseText); 
				if(jqXHR.status == 400) { // HttpStatus.BAD_REQUEST
					alert('몸무게와 키는 0일 수 없습니다.')
					
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