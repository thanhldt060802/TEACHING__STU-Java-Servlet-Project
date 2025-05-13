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
		Họ và tên:&emsp;<input id="full-name" type="text" required="required"/>
		<br>
		Email:&emsp;<input id="email" type="text" required="required"/>
		<br>
		Tên đăng nhập:&emsp;<input id="username" type="text" required="required"/>
		<br>
		Mật khẩu:&emsp;<input id="password" type="password" required="required"/>
		<br>
		Nhập lại mật khẩu:&emsp;<input id="re-password" type="password" required="required"/>
		<br>
		Địa chỉ:&emsp;<input id="address" type="text" required="required"/>
		<br>
		<button type="button" onclick="submitForm('./register')">Đăng ký</button>
		<br>
		<a href="./login">Đăng nhập</a>
	</form>
	
	<script>
		function submitForm(action) {
		    const form = document.getElementById("simple-form");

		    const params = new URLSearchParams();
		    params.append("action", action);
		    params.append("fullNameInput", document.getElementById("full-name").value);
		    params.append("emailInput", document.getElementById("email").value);
		    params.append("usernameInput", document.getElementById("username").value);
		    params.append("passwordInput", document.getElementById("password").value);
		    params.append("addressInput", document.getElementById("address").value);
	
		    fetch(action, {
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