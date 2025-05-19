<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Movie"%>
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
	<h1>CHỈNH SỬA PHIM</h1>
	
	<%
	Movie foundMovie = (Movie)request.getAttribute("foundMovie");
	%>
		
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Id:&emsp;<input id="id" type="text" value="<%= foundMovie.getMovieId() %>" readonly="readonly"/>
		<br>
		Tên phim:&emsp;<input id="title" type="text" value="<%= foundMovie.getTitle() %>"/>
		<br>
		Hình ảnh:&emsp;<input id="image" type="text" value="<%= foundMovie.getImage() %>"/>
		<br>
		Thể loại:&emsp;<input id="genre" type="text"  value="<%= foundMovie.getGenre() %>"/>
		<br>
		Thời lượng:&emsp;<input id="duration" type="number"  value="<%= foundMovie.getDuration() %>"/>
		<br>
		Khởi chiếu:&emsp;<input id="release-date-at" type="date"  value="<%= new SimpleDateFormat("yyyy-MM-dd").format(foundMovie.getReleaseDateAt()) %>"/>
		<br>
		<button type="button" onclick="submitFormUpdateMovie()">Cập nhật</button>
		<button type="button" onclick="deleteMovie('<%= foundMovie.getMovieId() %>')">Xoá</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormUpdateMovie() {
			const form = document.getElementById("simple-form");
			
			const params = new URLSearchParams();
		    params.append("idInput", document.getElementById("id").value);
		    params.append("titleInput", document.getElementById("title").value);
		    params.append("imageInput", document.getElementById("image").value);
		    params.append("genreInput", document.getElementById("genre").value);
		    params.append("durationInput", document.getElementById("duration").value);
		    params.append("releaseDateAtInput", document.getElementById("release-date-at").value);
	
		    fetch("./updateMovie", {
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
			window.location.href = "./deleteMovie?id=" + id
		}
		
		function resetForm() {
			location.reload();
		}
	</script>

</body>
</html>