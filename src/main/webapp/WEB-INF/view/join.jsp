<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>

<html>
<head>
    <title>회원가입 페이지</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            margin-top: 50px;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-control {
            font-size: 16px;
        }
        .btn-primary {
            font-size: 18px;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">회원가입</h2>
        <form action="/join" method="post">
            <sec:csrfInput/>
            <div class="form-group">
                <label for="domID">아이디</label>
                <input type="text" name="domID" class="form-control" placeholder="아이디" required>
            </div>
            <div class="form-group">
                <label for="domPW">비밀번호</label>
                <input type="password" name="domPW" class="form-control" placeholder="비밀번호" required>
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" name="email" class="form-control" placeholder="이메일" required>
            </div>
            <div class="form-group">
                <label for="phone">전화번호</label>
                <input type="text" name="phone" class="form-control" placeholder="전화번호(-없이)" required>
            </div>
            <button type="submit" class="btn btn-primary btn-dark">회원가입</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
