<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page import="main.entity.Applicant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>해당 방 타입 합격자 명단</H1>
	<h1>Applicant List</h1>
	<table border="1">
		<thead>
			<tr>

				<th>이름</th>
				<th>학번</th>

				<!-- 필요한 열들을 추가 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="p" items="${completApplicant }"  >
				<tr>
					<!-- completApplicant 리스트를 순회하며 각 항목을 출력 -->


					<td>${p.stuName }</td>
					<td>${p.stuNum }</td>

					<!-- 필요한 열들을 추가 -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="submit" value="생활관 회원으로 등록">
</body>
</html>