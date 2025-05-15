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
	<h1>DANH SÁCH NGƯỜI DÙNG</h1>
	
	<%
	List<User> userList = (List<User>) request.getAttribute("userList");
	%>

	<table border="1">
		<tr>
			<th>Mã số</th>
			<th>Họ và tên</th>
			<th>Email</th>
			<th>Tên người dùng</th>
			<th>Vai trò</th>
			<th>Chức năng</th>
		</tr>
		<%
		for(User user : userList) {
		%>
		<tr>
			<td><%= user.getUserId() %></td>
			<td><%= user.getFullName() %></td>
			<td><%= user.getEmail() %></td>
			<td><%= user.getUsername() %></td>
			<td><%= user.getRoleName() %></td>
			<td><a href="./getUserDetail?id=<%= user.getUserId() %>">Chỉnh sửa</a>&emsp;<a href="./deleteUser?id=<%= user.getUserId() %>">Xoá</a></td>
		</tr>
		<%
		}
		%>
	</table>
		
	<br>
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Họ và tên:&emsp;<input id="full-name" type="text"/>
		<br>
		Email:&emsp;<input id="email" type="text"/>
		<br>
		Tên đăng nhập:&emsp;<input id="username" type="text"/>
		<br>
		Mật khẩu:&emsp;<input id="password" type="text"/>
		<br>
		Vai trò:&emsp;<select id="role-name">
		    <option value="ADMIN">ADMIN</option>
		    <option value="CUSTOMER">CUSTOMER</option>
		</select>
		<br>
		<button type="button" onclick="submitFormCreateUser()">Tạo mới</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormCreateUser() {
			const form = document.getElementById("simple-form");
			
		    const params = new URLSearchParams();
		    params.append("fullNameInput", document.getElementById("full-name").value);
		    params.append("emailInput", document.getElementById("email").value);
		    params.append("usernameInput", document.getElementById("username").value);
		    params.append("passwordInput", document.getElementById("password").value);
		    params.append("roleNameInput", document.getElementById("role-name").value);
	
		    fetch("./createUser", {
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
		    document.getElementById("full-name").value = "";
		    document.getElementById("email").value = "";
		    document.getElementById("username").value = "";
		    document.getElementById("password").value = "";
		    document.getElementById("role-name").value = "ADMIN";
		}
	</script>

</body>
</html>