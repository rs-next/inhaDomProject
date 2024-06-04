<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ include file="/WEB-INF/view/commonBanner.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }
</style>
</head>
<body>



<table>
    <thead>
        <tr>
            <th>학번</th>
            <th>방번호</th>
            <th>이름</th>
            <th>성별</th>

        </tr>
    </thead>
    <tbody>
        <c:forEach var="domMember" items="${domMembers}">
            <tr>
                <td><c:out value="${domMember.memNum}"/></td>
                <td><c:out value="${domMember.roomNum}"/></td>
                <td><c:out value="${domMember.memName}"/></td>
                <td><c:out value="${domMember.memGender}"/></td>                
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>