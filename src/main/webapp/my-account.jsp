<%@page import="model.User"%>
<%@page import="java.util.List"%>
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
	<h1>THÔNG TIN TÀI KHOẢN</h1>
	
	<%
	User loginUser = (User)request.getSession().getAttribute("loginUser");
	%>
		
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Id:&emsp;<input id="id" type="text" value="<%= loginUser.getId() %>" readonly="readonly"/>
		<br>
		Họ và tên:&emsp;<input id="full-name" type="text" value="<%= loginUser.getFullName() %>"/>
		<br>
		Email:&emsp;<input id="email" type="text" value="<%= loginUser.getEmail() %>"/>
		<br>
		Tên đăng nhập:&emsp;<input id="username" type="text" value="<%= loginUser.getUsername() %>" readonly="readonly"/>
		<br>
		Mật khẩu mới:&emsp;<input id="new-password" type="password"/>
		<br>
		Nhập lại mật khẩu mới:&emsp;<input id="re-new-password" type="password" disabled="disabled"/>
		<br>
		<button type="button" onclick="submitFormUpdateMyAccount()">Cập nhật</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		const newPassword = document.getElementById('new-password');
	    const reNewPassword = document.getElementById('re-new-password');
	
	    newPassword.addEventListener('input', function() {
	        reNewPassword.disabled = newPassword.value.trim() === '';
	        if (newPassword.value === "") {
	        	reNewPassword.value = ""
	        }
	    });
	
		function submitFormUpdateMyAccount() {
			const form = document.getElementById("simple-form");
			
			const params = new URLSearchParams();
		    params.append("fullNameInput", document.getElementById("full-name").value);
		    params.append("emailInput", document.getElementById("email").value);
		    params.append("newPasswordInput", document.getElementById("new-password").value);
	
		    fetch("./updateMyAccount", {
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
		
		function resetForm() {
			location.reload();
		}
	</script>

</body>
</html>