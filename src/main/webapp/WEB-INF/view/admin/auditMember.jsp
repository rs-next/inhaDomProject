<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/commonBanner.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>심사 자동화</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
    body, h1, h2, .btn-primary {
            font-family: 'Noto Sans KR', sans-serif;
        }
        .container {
            max-width: 1000px;
        }
        .form-control, .custom-control-label, .btn-primary {
            background-color: #f8f9fa;
        }
        body {
            background-color: #e9ecef;
        }
        .btn-primary {
            color: #495057;
            border-color: #adb5bd;
        }
    </style>
</head>
<body>
    <div class="container mt-5 p-4 bg-white rounded">
        <h1 class="mb-4">심사 자동화</h1>
        <form action="/admin/auditAction" method="post">
            <div class="form-group">
                <label for="roomType">심사 방타입</label>
                <select class="form-control" id="roomType" name="roomType">
                    <option value="2A">2A</option>
                    <option value="2B">2B</option>
                    <option value="4">4</option>
                </select>
            </div>
            <div class="form-group">
                <label>심사 성별</label><br>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="customRadioMale" name="stuGender" value="M" class="custom-control-input" checked>
                    <label class="custom-control-label" for="customRadioMale">M</label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="customRadioFemale" name="stuGender" value="W" class="custom-control-input">
                    <label class="custom-control-label" for="customRadioFemale">W</label>
                </div>
            </div>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary btn-dark">심사</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>