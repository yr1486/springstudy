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
<script>
	$(function(){
		// recordPerPage의 변경
		$('#recordPerPage').on('change', function(){
			location.href = '${contextPath}/employees/change/record.do?recordPerPage=' + $(this).val();  // session에 recorePerPage 올리기
		})
		
		// 세션에 저장된 recordPerPage 값으로 <select> 태그의 값을 세팅 // pageContext, request, session, application ==> attribute 저장 장소이며, 모두 el로 호출할 수 있다. 중요. 기억하기
		
		let recordPerPage = '${sessionScope.recordPerPage}' == '' ? '10' : '${sessionScope.recordPerPage}';
		$('#recordPerPage').val(recordPerPage);
		
		// 제목을 클릭하면 정렬 방식을 바꿈
		// 현재 정렬상태가(order) 어떤 상태인지. 무엇인지. 얘가 알고 있어야. 넘겨줄 수 있다. 오름인지 , 내림인지
		// 해야 될 . 일을 넘기는거야. //  아래 테이블 테그에 data-order확인하기. data-order 속성값을꺼내는 애가 아래 data('')임 =>data('order')
		// employee_list.xml 에 속성 order와 연결
		$('.title').on('click', function(){
				location.href = '${contextPath}/employees/pagination.do?column=' + $(this).data('column') + '&order=' + $(this).data('order') + "&page=${page}";
			})
	})
	
</script>
<style>
	.title {
		cursor: pointer;
	}
	.title:hover {
		color: gray;
	}
	.title:active {
		color: silver;
	}
	.pagination {
		width: 350px;
		margin: 0 auto;
		text-align: center;
	}
	.pagination span, .pagination a {
		display: inline-block;
		margin: 0 15px;
	}
	.hidden {
		visibility: hidden;
	}
	.strong {
		font-weight: 900;
	}
	.link {
		color: orange;
	}
	table {
		width: 1500px;
	}
	table td:nth-of-type(1) { width: 100px; }
	table td:nth-of-type(2) { width: 150px; }
	table td:nth-of-type(3) { width: 300px; }
</style>
</head>
<body>
<!-- 페이지 목록 만들어 주는 걸 페이지네이션이라고 함. pagination.jsp  -->

	<div>
		<a href="${contextPath}/employees/search.form">사원 조회 화면으로 이동</a>
	</div>
	<div>
		<h1>사원 목록</h1>
		<div>
			<select id="recordPerPage">
				<option value="10">10개</option>
				<option value="20">20개</option>
				<option value="30">30개</option>
			</select>
		</div>
		<hr>
		
		<!-- 태그에 값을 저장시키는 방법 : data-속성 -->
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td><span class="title" data-column="E.EMPLOYEE_ID" data-order="${order}">사원번호</span></td>
					<td><span class="title" data-column="E.FIRST_NAME" data-order="${order}">사원명</span></td>
					<td><span class="title" data-column="E.EMAIL" data-order="${order}">이메일</span></td>
					<td><span class="title" data-column="E.PHONE_NUMBER" data-order="${order}">전화번호</span></td>
					<td><span class="title" data-column="E.HIRE_DATE" data-order="${order}">입사일자</span></td>
					<td><span class="title" data-column="E.JOB_ID" data-order="${order}">직업</span></td>
					<td><span class="title" data-column="E.SALARY" data-order="${order}">연봉</span></td>
					<td><span class="title" data-column="E.COMMISSION_PCT" data-order="${order}">커미션</span></td>
					<td><span class="title" data-column="E.MANAGER_ID" data-order="${order}">매니저</span></td>
					<td><span class="title" data-column="E.DEPARTMENT_ID" data-order="${order}">부서번호</span></td>
					<td><span class="title" data-column="D.DEPARTMENT_NAME" data-order="${order}">부서명</span></td>
				</tr>
			</thead>
			
			
			<!-- 임플로이즈에서 하나씩꺼내서 var=emp라고 부르겠다. 인덱스를 쓰려면. var키워드를 써야함 -->
			<!-- <td>${vs.employeeId}</td> 에서 employeeId 얘는 필드네임임.-->
		<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs">
					<tr>
						<td>${beginNo - vs.index}</td>
						<td>${emp.employeeId}</td>
						<td>${emp.firstName} ${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.phoneNumber}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.jobId}</td>
						<td>${emp.salary}</td>
						<td>${emp.commissionPct}</td>
						<td>${emp.managerId}</td>
						<td>${emp.deptDTO.departmentId}</td>
						<td>${emp.deptDTO.departmentName}</td>
					</tr>
				</c:forEach>
			</tbody>
			
		<tfoot>
				<tr>
					<td colspan="12">
						${pagination}
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>
