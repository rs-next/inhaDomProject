<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/commonBanner.jsp" %>

<%@ page import="main.entity.Applicant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>합격자 명단</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body, h1, h2, .btn-primary {
            font-family: 'Noto Sans KR', sans-serif;
        }
        .container {
            max-width: 500px;
        }
        .table thead th {
            background-color: #343a40;
            color: #fff;
        }
        .table tbody tr:nth-child(odd) {
            background-color: #f8f9fa;
        }
        .table tbody tr:nth-child(even) {
            background-color: #e9ecef;
        }
        .btn-primary {
            background-color: #6c757d;
            border-color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">해당 방 타입 합격자 명단</h1>
       
        <form action="/view/admin/insertDomMember" method="post">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>이름</th>
                        <th>학번</th>
                        <!-- 필요한 열들을 추가 -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${completApplicant}">
                        <tr>
                            <!-- completApplicant 리스트를 순회하며 각 항목을 출력 -->
                            <td>${p.stuName}</td>
                            <td>${p.stuNum}</td>
                            <!-- 필요한 열들을 추가 -->
                        </tr>
                        <input type="hidden" name="stuNum[]" value="${p.stuNum}">
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary btn-dark">생활관 회원으로 등록</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>