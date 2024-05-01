<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>입사 신청폼</h1>
<form action="/view/ApplicantInsert" method="post">
	이름<input type="text" name="stuName"><br>
	학번<input type="text" name="stuNum"><br>
	생년월일<input type="text" name="stuBirth" placeholder="YYYYMMDD" pattern="[0-9]{8}"><br>
	학과<input type="text" name="stuDept"><br>
	연락처<input type="text" name="stuPhoneNum"><br>
	주소<input type="text" name="stuAddress"><br>
	상세주소<input type="text" name="stuDetailAddress"><br>
	이메일<input type="text" name="stuMail"><br>
	직전성적<input type="text" name="stuGrade"><br>
	신청방타입<input type="text" list="roomType" name="appliType"><br>
	성별<input type="radio" value="M" name="stuGender" id="M" checked/><label for="M">M</label>
	<input type="radio" id="W" value="W" name="stuGender"/><label for="W">W</label>
	

	<datalist id="roomType">
		<option value="2A"/>
		<option value="2B"/>
		<option value="4"/>		
	</datalist>
	<input type="submit" value="작성완료">
	</form>
	<a href="/view/ApplicantInsert">이동</a>
</body>
</html>