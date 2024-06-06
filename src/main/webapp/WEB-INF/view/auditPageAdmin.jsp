<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/commonBanner.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입사 신청폼</title>
</head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<body>
	<div class="container mt-5">
		<h1 class="mb-4">입사 신청폼</h1>
		<form action="/logout" method="post">
		 	<sec:csrfInput />
			<input type="submit" value="로그아웃">
		</form>
		<form action="/view/ApplicantInsert" method="post">
			<div class="form-group">
				<label for="stuName">이름</label> <input type="text"
					class="form-control" id="stuName" name="stuName">
			</div>
			<div class="form-group">
				<label for="stuNum">학번</label> <input type="text"
					class="form-control" id="stuNum" name="stuNum">
			</div>
			<div class="form-group">
				<label for="stuBirth">생년월일</label> <input type="text"
					class="form-control" id="stuBirth" name="stuBirth"
					placeholder="YYYYMMDD" pattern="[0-9]{8}">
			</div>
			<div class="form-group">
				<label for="stuDept">학과</label> <input type="text"
					class="form-control" id="stuDept" name="stuDept">
			</div>
			<div class="form-group">
				<label for="stuPhoneNum">연락처</label> <input type="text"
					class="form-control" id="stuPhoneNum" name="stuPhoneNum">
			</div>
			<div class="form-group">
				<label for="stuAddress">주소</label> <input type="text"
					class="form-control" id="stuAddress" name="stuAddress">
			</div>
			<div class="form-group">
				<label for="stuDetailAddress">상세주소</label> <input type="text"
					class="form-control" id="stuDetailAddress" name="stuDetailAddress">
			</div>
			<div class="form-group">
				<label for="stuMail">이메일</label> <input type="email"
					class="form-control" id="stuMail" name="stuMail">
			</div>
			<div class="form-group">
				<label for="stuGrade">직전성적</label> <input type="text"
					class="form-control" id="stuGrade" name="stuGrade">
			</div>
			<div class="form-group">
				<label for="appliType">신청방타입</label> <input type="text"
					class="form-control" list="roomType" id="appliType"
					name="appliType">
				<datalist id="roomType">
					<option value="2A">
					<option value="2B">
					<option value="4">
				</datalist>
			</div>
			<div class="form-group">
				<label>성별</label><br>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="customRadioMale" name="stuGender" value="M"
						class="custom-control-input" checked> <label
						class="custom-control-label" for="customRadioMale">M</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="customRadioFemale" name="stuGender"
						value="W" class="custom-control-input"> <label
						class="custom-control-label" for="customRadioFemale">W</label>
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-dark">작성완료</button>
		</form>		
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>