<%@page import="model.Theater"%>
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
	<h1>DANH SÁCH RẠP</h1>
	
	<%
	List<Theater> theaterList = (List<Theater>) request.getAttribute("theaterList");
	%>

	<table border="1">
		<tr>
			<th>Mã số</th>
			<th>Tên rạp</th>
			<th>Chức năng</th>
		</tr>
		<%
		for(Theater theater : theaterList) {
		%>
		<tr>
			<td><%= theater.getTheaterId() %></td>
			<td><%= theater.getName() %></td>
			<td><a href="./getTheaterDetail?id=<%= theater.getTheaterId() %>">Chỉnh sửa</a></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<br>
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Tên rạp:&emsp;<input id="name" type="text"/>
		<br>
		Địa điểm:&emsp;<input id="location" type="text"/>
		<br>
		<button type="button" onclick="submitFormCreateTheater()">Tạo mới</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormCreateTheater() {
			const form = document.getElementById("simple-form");
			
		    const params = new URLSearchParams();
		    params.append("nameInput", document.getElementById("name").value);
		    params.append("locationInput", document.getElementById("location").value);
	
		    fetch("./createTheater", {
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