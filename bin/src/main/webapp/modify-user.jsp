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
		
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
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
		Vai trò:&emsp;<select id="role-name">
		    <option value="ADMIN" <%=foundUser.getRoleName().equals("ADMIN") ? "selected" : "" %>>ADMIN</option>
		    <option value="CUSTOMER" <%=foundUser.getRoleName().equals("CUSTOMER") ? "selected" : "" %>>CUSTOMER</option>
		</select>
		<br>
		<button type="button" onclick="submitFormUpdateUser()">Cập nhật</button>
		<button type="button" onclick="deleteUser('<%= foundUser.getId()%>')">Xoá</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormUpdateUser() {
			const form = document.getElementById("simple-form");
			
			const params = new URLSearchParams();
		    params.append("idInput", document.getElementById("id").value);
		    params.append("fullNameInput", document.getElementById("full-name").value);
		    params.append("emailInput", document.getElementById("email").value);
		    params.append("addressInput", document.getElementById("address").value);
		    params.append("roleNameInput", document.getElementById("role-name").value);
	
		    fetch("./updateUser", {
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
		
		function deleteUser(id) {
			window.location.href = "./deleteUser?id=" + id
		}
		
		function resetForm() {
			location.reload();
		}
	</script>

</body>
</html>