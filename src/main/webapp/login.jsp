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
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded">
		Tên đăng nhập:&emsp;<input id="username" type="text"/>
		<br>
		Mật khẩu:&emsp;<input id="password" type="password"/>
		<br>
		<button type="button" onclick="submitForm('./login')">Đăng nhập</button>
		<br>
		<a href="./register">Đăng ký</a>&emsp;/&emsp;<a href="./retrievePassword">Quên mật khẩu</a>
	</form>
	
	<script>
		function submitForm(action) {
		    const form = document.getElementById("simple-form");

		    const params = new URLSearchParams();
		    params.append("action", action);
		    params.append("usernameInput", document.getElementById("username").value);
		    params.append("passwordInput", document.getElementById("password").value);
	
		    fetch(action, {
		        method: "POST",
		        headers: {
		            "Content-Type": "application/x-www-form-urlencoded"
		        },
		        body: params.toString()
		    })
		    .catch(error => {
		        console.error("Lỗi:", error);
		        alert("Đã xảy ra lỗi khi gửi dữ liệu.");
		    });
		}
	</script>

</body>
</html>