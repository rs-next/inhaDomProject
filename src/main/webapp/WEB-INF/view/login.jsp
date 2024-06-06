<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
</head>
<body>
	<form action="/login" method="post">
	<sec:csrfInput/>
	<input type="text" name="domID" placeholder="아이디" />
	<input type="password" name="domPW" placeholder="비밀번호" />
	<button>로그인</button>
	<a href="/join">회원가입</a>
</form>
</body>
</html>