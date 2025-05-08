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
	<h1>CHỈNH SỬA NGƯỜI DÙNG</h1>
	
	<%
	User foundUser = (User)request.getAttribute("foundUser");
	%>
		
	<form>
		Id:&emsp;<input id="id" type="text" value="<%= foundUser.getId() %>" readonly="readonly"/>
		<br>
		Họ và tên:&emsp;<input id="full-name" type="text" value="<%= foundUser.getFullName() %>"/>
		<br>
		Email:&emsp;<input id="email" type="text" value="<%= foundUser.getEmail() %>"/>
		<br>
		Tên đăng nhập:&emsp;<input id="username" type="text"  value="<%= foundUser.getUsername() %>" readonly="readonly"/>
		<br>
		Địa chỉ:&emsp;<input id="address" type="text" value="<%= foundUser.getAddress() %>"/>
		<br>
		<button type="button" onclick="submitForm('./updateUser')">Cập nhật</button>
		<button type="button" onclick="submitForm('./deleteUser')">Xoá</button>
	</form>
	
	<script>
		function submitForm(action) {
			
		}
	</script>

</body>
</html>