<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="./partial/header.jsp"></jsp:include>
	<h1>LẤY LẠI MẬT KHẨU</h1>
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Tên đăng nhập:&emsp;<input id="username" type="text"/>
		<br>
		Email:&emsp;<input id="email" type="text"/>
		<br>
		<button type="button" onclick="submitFormRetrievePassword()">Xác nhận</button>
		<br>
		<a href="./login">Đăng nhập</a>
	</form>
	
	<script>
		function submitFormRetrievePassword() {
		    // Implementation ...
		}
	</script>

</body>
</html>