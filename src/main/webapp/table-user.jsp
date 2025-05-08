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
			<td><%= user.getId() %></td>
			<td><%= user.getFullName() %></td>
			<td><%= user.getEmail() %></td>
			<td><%= user.getUsername() %></td>
			<td><%= user.getRoleName() %></td>
			<td><a href="./getUserDetail?id=<%= user.getId() %>">Chỉnh sửa</a></td>
		</tr>
		<%
		}
		%>
	</table>
		
	<br>
	
	<form>
		Họ và tên:&emsp;<input id="full-name" type="text"/>
		<br>
		Email:&emsp;<input id="email" type="text"/>
		<br>
		Tên đăng nhập:&emsp;<input id="username" type="text"/>
		<br>
		Địa chỉ:&emsp;<input id="address" type="text"/>
		<br>
		<button type="button" onclick="submitForm('./createUser')">Tạo mới</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitForm(action) {
			
		}
		
		function resetForm() {
		    document.getElementById("full-name").value = "";
		    document.getElementById("email").value = "";
		    document.getElementById("username").value = "";
		    document.getElementById("address").value = "";
		}
	</script>

</body>
</html>