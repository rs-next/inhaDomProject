<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공통 배너</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body, h1, h2, .btn-primary {
            font-family: 'Noto Sans KR', sans-serif;
        }
        .banner {
            background-color: #666;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            color: white;
        }
        .banner .nav-tabs {
            display: flex;
            align-items: center;
        }
        .banner .nav-tabs .nav-link {
            color: white;
            margin-right: 20px;
        }
        .banner .nav-tabs .nav-link:hover {
            color: #adb5bd;
        }
        .dropdown .user-icon {
            font-size: 24px;
            cursor: pointer;
        }
        .content {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="banner">
        <ul class="nav nav-tabs border-0">
            <li class="nav-item">
                <a class="nav-link" href="/auditMember">심사 탭</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/showRoomStatus">방 현황 조회</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/auditPageAdmin">기숙사 신청</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/logout' />">로그아웃</a>
            </li>
        </ul>
        <div class="dropdown">
            <sec:authorize access="isAuthenticated()">
                <a class="user-icon dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img src="https://img.icons8.com/ios-glyphs/30/ffffff/user--v1.png" alt="User Icon">
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="<c:url value='/logout' />">로그아웃</a>
                </div>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a class="nav-link" href="<c:url value='/login' />">로그인</a>
            </sec:authorize>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
