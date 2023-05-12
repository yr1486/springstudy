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

   $(function(){
      fnList();
   })
   
   	/* 주소에 /staff가 이 안에서는 ${contextPath} 인거야. 중요 
	요청이 있으면 파라미터를 만들라는 얘기고, 없으면 아니야, 파라미터를 보내는게  data: '~~'임. 
	근데 우리는 지금 응답만 있어서 data를 쓸필요 없어. 그래서 아래에는 없음. 있을떄만 써. 
	*/
   function fnList(){
      $.ajax({
         type: 'get',
         url: '${contextPath}/list.json',
         dataType: 'json',
         success: function(resData){  /* resData = [{}, {}, {}] -> 배열의 형태 , 반복문(each) 필요 //  변수명은언제나바뀜. 리스펀스데이터.  */
         $('#staffList').empty();   
         $.each(resData, function(i,staff){ /* 초기화코드가 항상 이치문시작하기전에 있어야함.  */
               let str ='<tr>';
               str += '<td>' + staff.sno;   
               str += '<td>' + staff.name;   
               str += '<td>' + staff.dept;   
               str += '<td>' + staff.salary;   
               $('#staffList').append(str);
            })
         }
      })
   }
   
   function fnAdd(){
      $.ajax({
         type: 'post',
         url: '${contextPath}/add.do',
         data: $('#frm_add').serialize(),
         dataType: 'text',
         success: function(resData){ // resData: 사원 등록이 성공헀습니다.
            alert(resData);
            fnList();
            $('#sno').val('');
            $('#name').val('');
            $('#salary').val('');
         },
         error: function(jqXHR){ // jqXHR.responseText : 사원 등록이 실패했습니다.
            alert(jqXHR.responseText);
         }
      })
   }
   
   
   function fnSearch(){
	   var search = $('#frm_search').val();
	      $.ajax({
	         type: 'get',
	         url: '${contextPath}/search.json',
	         data: $('#frm_search').serialize(),
	         dataType: 'json',
	         success: function(resData){ 
	             $('#staffList').empty();
	             $.each(resData, function(i, staff) {
	                let str = '<tr>';
	                str += '<td>' + staff.sno;
	                str += '<td>' + staff.name;
	                str += '<td>' + staff.dept;
	                str += '<td>' + staff.salary;
	                $('#staffList').append(str);
	             });
	         error: function(jqXHR){ 
	            alert(jqXHR.responseText);
	         }
	      })
	   }
   
</script>
</head>
<body>

   <div>
      <h3>사원등록</h3>
      <form id="frm_add">
         <input type="text" name="sno" id="sno" placeholder="사원번호">
         <input type="text" name="name" id="name" placeholder="사원명">
         <input type="text" name="dept" id="dept" placeholder="부서명">
         <input type="button" value="등록" onclick="fnAdd()">
      </form>
   </div>
   
   <hr>
   
   <div>
      <h3>사원조회</h3>
      <form id="frm_search">
         <input type="text" name="query" id="query" placeholder="사원번호입력">
         <input type="button" value="조회" onclick="fnSearch()">
         <input type="button" value="전체" onclick="fnList()">
      </form>
   </div>
   
   <hr>
   
   <div>
      <h3>사원목록</h3>
      <table border="1">
         <thead>
            <tr>
               <td>사원번호</td>
               <td>사원명</td>
               <td>부서명</td>
               <td>연봉</td>
            </tr>
         </thead>
         <tbody id="staffList"></tbody>
      </table>
   </div>
   
</body>
</head>
<body>
   
</body>
</html>