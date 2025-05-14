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
	
	<form>
		Họ và tên:&emsp;<input id="full-name" type="text"/>
		<br>
		Email:&emsp;<input id="email" type="text"/>
		<br>
		Tên đăng nhập:&emsp;<input id="username" type="text"/>
		<br>
		Mật khẩu:&emsp;<input id="password" type="password"/>
		<br>
		Nhập lại mật khẩu:&emsp;<input id="re-password" type="password"/>
		<br>
		Địa chỉ:&emsp;<input id="address" type="text"/>
		<br>
		<button type="button" onclick="submitFormRegister()">Đăng ký</button>
		<br>
		<a href="./login">Đăng nhập</a>
	</form>
	
	<script>
		function submitFormRegister() {
		    const form = document.getElementById("simple-form");

		    const params = new URLSearchParams();
		    params.append("fullNameInput", document.getElementById("full-name").value);
		    params.append("emailInput", document.getElementById("email").value);
		    params.append("usernameInput", document.getElementById("username").value);
		    params.append("passwordInput", document.getElementById("password").value);
		    params.append("addressInput", document.getElementById("address").value);
	
		    fetch("./register", {
		        method: "POST",
		        headers: {
		            "Content-Type": "application/x-www-form-urlencoded"
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