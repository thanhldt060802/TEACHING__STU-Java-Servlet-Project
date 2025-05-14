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
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Tên đăng nhập:&emsp;<input id="username" type="text"/>
		<br>
		Mật khẩu:&emsp;<input id="password" type="password"/>
		<br>
		<button type="button" onclick="submitFormLogin()">Đăng nhập</button>
		<br>
		<a href="./register">Đăng ký</a>&emsp;/&emsp;<a href="./retrievePassword">Quên mật khẩu</a>
	</form>
	
	<script>
		function submitFormLogin() {
		    const form = document.getElementById("simple-form");

		    const params = new URLSearchParams();
		    params.append("usernameInput", document.getElementById("username").value);
		    params.append("passwordInput", document.getElementById("password").value);
	
		    fetch("./login", {
		        method: "POST",
		        headers: {
		            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
		        },
		        body: params.toString()
		    })
		    .then(response => {
	            if(response.redirected) {
	                window.location.href = response.url;
	            }
        	})
		    .catch(error => {
		        console.error("Lỗi:", error);
		        alert("Đã xảy ra lỗi khi gửi dữ liệu.");
		    });
		}
	</script>

</body>
</html>