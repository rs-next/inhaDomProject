<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>

<html>
<head>
	<title>회원가입 페이지</title>
</head>
<body>
<form action="/join" method="post">
	<sec:csrfInput/>
	<input type="text" name="domID" placeholder="아이디" />
	<input type="password" name="domPW" placeholder="비밀번호" />	
	<input type="email" name="email" placeholder="이메일" />
	<input type="text" name="phone" placeholder="전화번호(-없이)" />
	<button>회원가입</button>
</form>
</body>
</html>