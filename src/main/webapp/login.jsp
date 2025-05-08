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
	<h1>ĐĂNG NHẬP</h1>
	<form action="./login" method="post">
		Tên đăng nhập:&emsp;<input type="text" name="usernameInput"/>
		<br>
		Mật khẩu:&emsp;<input type="password" name="passwordInput"/>
		<br>
		<button type="submit">Đăng nhập</button>
	</form>

</body>
</html>