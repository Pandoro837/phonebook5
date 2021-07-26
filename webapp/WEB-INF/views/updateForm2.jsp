<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 수정</h1>
	<p>수정화면입니다. 아래 항목을 수정하시고, 수정 버튼을 클릭하세요</p>
	<form action="${pageContext.request.contextPath}/pb/update2" method="get">
		<input type="text" name="personId" value="${personInfo.PERSON_ID }"><br> 
		이름 : <input type="text" name="name" value="${personInfo.NAME }"><br> 
		핸드폰 : <input type="text" name="hp" value="${personInfo.HP }"><br> 
		회사 : <input type="text" name="company" value="${personInfo.COMPANY }"><br>
		<button type="submit">수정</button>
	</form>

</body>
</html>