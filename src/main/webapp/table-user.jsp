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
	
	<!-- Tải dữ liệu lên bảng -->
	
	<%
	List<User> userList = (List<User>) request.getAttribute("userList");
	%>

	<table border="1">
		<tr>
			<th>Mã số</th>
			<th>Họ và tên</th>
			<th>Email</th>
			<th>Tên người dùng</th>
			<th>Địa chỉ</th>
			<th>Vai trò</th>
			<th>Thời gian tạo</th>
			<th>Thời gian sửa</th>
			<th>Chức năng</th>
		</tr>
		<%
		for(User user : userList) {
		%>
		<tr>
			<td><%= user.getId() %></td>
			<td><%= user.getFullName() %></td>
			<td><%= user.getEmail() %></td>
			<td><%= user.getUsername() %></td>
			<td><%= user.getAddress() %></td>
			<td><%= user.getRoleName() %></td>
			<td><%= user.getCreatedAt() %></td>
			<td><%= user.getUpdatedAt() %></td>
			<td><button onclick="fillForm('<%= user.getId() %>',
									      '<%= user.getFullName() %>',
									      '<%= user.getEmail() %>',
									      '<%= user.getUsername() %>',
									      '<%= user.getAddress() %>')">Xem</button></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<!-- Thực hiện các thao tác Create, Update, Delete -->
	
	<br>
	
	<form id="simple-form">
		Id:&emsp;<input id="id" type="text" name="inputId" readonly="readonly"/>
		<br>
		Họ và tên:&emsp;<input id="full-name" name="inputFullName" type="text"/>
		<br>
		Email:&emsp;<input id="email" name="inputEmail" type="text"/>
		<br>
		Tên đăng nhập:&emsp;<input id="username" name="inputUsername" type="text"/>
		<br>
		Địa chỉ:&emsp;<input id="address" name="inputAddress" type="text"/>
		<br>
		<button type="button" onclick="submitForm('./createUser')">Tạo mới</button>
		<button type="button" onclick="submitForm('./updateUser')">Cập nhật</button>
		<button type="button" onclick="submitForm('./deleteUser')">Xoá</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function fillForm(id, fullName, email, username, address) {
		    document.getElementById("id").value = id;
		    document.getElementById("full-name").value = fullName;
		    document.getElementById("email").value = email;
		    document.getElementById("username").value = username;
		    document.getElementById("address").value = address;
		}
	
		function submitForm(action) {
		    const form = document.getElementById("simple-form");
		    const formData = new FormData(form);
	
		    fetch(action, {
		        method: "POST",
		        body: formData
		    })
		    .then(res => res.text())
		    .then(result => {
		        alert("Phản hồi từ server: " + result);
		    })
		    .catch(error => {
		        console.error("Lỗi:", error);
		        alert("Đã xảy ra lỗi khi gửi dữ liệu.");
		    });
		}
		
		function resetForm() {
		    document.getElementById("id").value = "";
		    document.getElementById("full-name").value = "";
		    document.getElementById("email").value = "";
		    document.getElementById("username").value = "";
		    document.getElementById("address").value = "";
		}
	</script>

</body>
</html>