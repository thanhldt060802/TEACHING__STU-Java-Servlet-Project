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
		Nhập lại mật khẩu mới:&emsp;<input id="re-new-password" type="password"/>
		<br>
		Địa chỉ:&emsp;<input id="address" type="text" value="<%= loginUser.getAddress() %>"/>
		<br>
		<button type="button" onclick="submitFormUpdateMyAccount()">Cập nhật</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormUpdateMyAccount() {
			// Implementation ...
		}
		
		function resetForm() {
			location.reload();
		}
	</script>

</body>
</html>