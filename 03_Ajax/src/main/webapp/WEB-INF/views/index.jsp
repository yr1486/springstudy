<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>

	<h1>하이하이</h1>

	<div><a href="${contextPath}/first.do">이동1</a></div>
	<div><a href="${contextPath}/second.do">이동2</a></div>
	<div><a href="${contextPath}/third.do">이동3</a></div>
	
	
</body>
</html>