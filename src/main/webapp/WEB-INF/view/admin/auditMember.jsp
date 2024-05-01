<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>심사 자동화</H1>
	<form action="/view/admin/auditAction" method="post">
		심사 방타입<input type="text" list="roomType" name="roomType">
		<datalist id="roomType">
			<option value="2A" />
			<option value="2B" />
			<option value="4" />
		</datalist>
		심사 성별<input type="radio" value="M" name="stuGender" id="M" checked /><label
			for="M">M</label> <input type="radio" id="W" value="W"
			name="stuGender" /><label for="W">W</label>
			<input type="submit" value="심사">
	</form>
</body>
</html>