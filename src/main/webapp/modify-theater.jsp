<%@page import="model.Theater"%>
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
	<h1>CHỈNH SỬA THÔNG TIN RẠP</h1>
	
	<%
	Theater foundTheater = (Theater)request.getAttribute("foundTheater");
	%>
		
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Id:&emsp;<input id="id" type="text" value="<%= foundTheater.getTheaterId() %>" readonly="readonly"/>
		<br>
		Tên rạp:&emsp;<input id="name" type="text" value="<%= foundTheater.getName() %>"/>
		<br>
		Địa điểm:&emsp;<input id="location" type="text" value="<%= foundTheater.getLocation() %>"/>
		<br>
		<button type="button" onclick="submitFormUpdateTheater()">Cập nhật</button>
		<button type="button" onclick="deleteTheater('<%= foundTheater.getTheaterId() %>')">Xoá</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormUpdateMovie() {
			const form = document.getElementById("simple-form");
			
			const params = new URLSearchParams();
		    params.append("idInput", document.getElementById("id").value);
		    params.append("nameInput", document.getElementById("name").value);
		    params.append("locationInput", document.getElementById("location").value);
	
		    fetch("./updateTheater", {
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
		
		function deleteMovie(id) {
			window.location.href = "./deleteTheater?id=" + id
		}
		
		function resetForm() {
			location.reload();
		}
	</script>

</body>
</html>