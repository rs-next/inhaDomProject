<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page import="main.entity.Applicant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>

				<th>학번</th>
				<th>방번호</th>

				<!-- 필요한 열들을 추가 -->
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="entry" items="${domMember.entrySet()}">
				<tr>
					<!-- completApplicant 리스트를 순회하며 각 항목을 출력 -->


					<td>${entry.key}</td>
					<td>${entry.value}</td>

					<!-- 필요한 열들을 추가 -->
				</tr>
				
				
				
			</c:forEach>
		</tbody>

	</table>
	
</body>
</html>