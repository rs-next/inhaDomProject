<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/commonBanner.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>
<script>
function downloadExcel() {
    // 기숙사생 데이터를 엑셀 파일로 변환
    var table = document.getElementsByTagName("table")[0];
    var workbook = XLSX.utils.table_to_book(table);    
    XLSX.writeFile(workbook, 'StudentData.xlsx');
}
</script>
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
<button class="btn btn-dark" style="float:right; margin-top:20px; margin-right:50px;" onclick="downloadExcel()">엑셀로 다운로드</button>

</body>
</html>
