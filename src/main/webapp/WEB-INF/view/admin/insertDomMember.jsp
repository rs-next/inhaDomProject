<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/commonBanner.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page import="main.entity.Applicant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>생활관 등록 명단</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
    }

    table {
        border-collapse: collapse;
        width: 500px;
        margin: 20px auto;
        background-color: #fff;
        border: 1px solid #dee2e6;
    }

    th, td {
        padding: 10px;
        text-align: center;
        border: 1px solid #dee2e6;
    }

    th {
        background-color: #6c757d;
        color: #fff;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #e9ecef;
    }
</style>
</head>
<body>

    <table>
        <thead>
            <tr>
                <th>학번</th>
                <th>방번호</th>
            </tr>
        </thead>
        
        <tbody>
            <c:forEach var="entry" items="${domMember.entrySet()}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>