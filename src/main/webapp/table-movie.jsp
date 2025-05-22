<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Movie"%>
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
	<h1>DANH SÁCH PHIM</h1>
	
	<%
	List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
	%>

	<table border="1">
		<tr>
			<th>Mã số</th>
			<th>Tên phim</th>
			<th>Thể loại</th>
			<th>Thời lượng</th>
			<th>Khởi chiếu</th>
			<th>Chức năng</th>
		</tr>
		<%
		for(Movie movie : movieList) {
		%>
		<tr>
			<td><%= movie.getMovieId() %></td>
			<td><%= movie.getTitle() %></td>
			<td><%= movie.getGenre() %></td>
			<td><%= movie.getDuration() %> phút</td>
			<td><%= new SimpleDateFormat("dd/MM/yyyy").format(movie.getReleaseDateAt()) %></td>
			<td><a href="./getMovieDetail?id=<%= movie.getMovieId() %>">Chỉnh sửa</a></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<br>
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Tên phim:&emsp;<input id="title" type="text"/>
		<br>
		Hình ảnh:&emsp;<input id="image" type="text"/>
		<br>
		Thể loại:&emsp;<input id="genre" type="text"/>
		<br>
		Thời lượng:&emsp;<input id="duration" type="number"/>
		<br>
		Khởi chiếu:&emsp;<input id="release-date-at" type="date"/>
		<br>
		<button type="button" onclick="submitFormCreateMovie()">Tạo mới</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormCreateMovie() {
			const form = document.getElementById("simple-form");
			
		    const params = new URLSearchParams();
		    params.append("titleInput", document.getElementById("title").value);
		    params.append("imageInput", document.getElementById("image").value);
		    params.append("genreInput", document.getElementById("genre").value);
		    params.append("durationInput", document.getElementById("duration").value);
		    params.append("releaseDateAtInput", document.getElementById("release-date-at").value);
	
		    fetch("./createMovie", {
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
		    document.getElementById("title").value = "";
		    document.getElementById("image").value = "";
		    document.getElementById("genre").value = "";
		    document.getElementById("duration").value = "";
		    document.getElementById("release-date-at").value = "";
		}
	</script>
	
</body>
</html>