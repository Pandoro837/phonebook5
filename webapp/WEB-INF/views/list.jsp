<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 리스트</h1>
	<p>입력한 정보 내역입니다.</p>

	<c:forEach items="${requestScope.personList }" var="personInfo" >
		<table border="1">
			<tr>
				<td>이름</td>
				<td>${personInfo.name }</td>
			</tr>
			<tr>
				<td>핸드폰</td>
				<td>${personInfo.hp }</td>
			</tr>
			<tr>
				<td>회사</td>
				<td>${personInfo.company }</td>
			</tr>
			<tr>
				<td>
					<form action="${pageContext.request.contextPath}/pb/updateForm" method="get">
						<input type="hidden" name="personId" value="${personInfo.personId}"> <!-- 필드의 변수명과 같아야 함 -->
						<!--해당 정보만 수정할 수 있도록 personId를 hidden 타입으로 전달-->
						<!-- personId가 전송되지 않는 에러 발생, 확인용 출력 -> 해결  -->
						<button type="submit">수정</button>
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath}/pb/delete" method="get">
						<input type="hidden" name="personId" value="${personInfo.personId}">
						<!--해당 정보만 삭제할 수 있도록 personId를 hidden 타입으로 전달-->
						<button type="submit">삭제</button>
					</form>
				</td>
			</tr>
			
	</table>
	<br>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/pb/writeForm">[추가번호 등록]</a>
</body>
</html>