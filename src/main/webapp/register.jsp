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
	<h1>ĐĂNG KÝ</h1>
	<form action="">
		Họ và tên:&emsp;<input type="text"/>
		<br>
		Email:&emsp;<input type="text"/>
		<br>
		Tên đăng nhập:&emsp;<input type="text"/>
		<br>
		Mật khẩu:&emsp;<input type="password"/>
		<br>
		Nhập lại mật khẩu:&emsp;<input type="password"/>
		<br>
		Địa chỉ:&emsp;<input type="text"/>
		<br>
		<button type="submit">Đăng ký</button>
		<br>
		<a href="/login">Đăng nhập</a> / <a href="">Quên mật khẩu</a>
	</form>

</body>
</html>