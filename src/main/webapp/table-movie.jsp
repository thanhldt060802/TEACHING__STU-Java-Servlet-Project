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
			<td><a href="./getMovieDetail?id=<%= movie.getMovieId() %>">Chỉnh sửa</a>&emsp;<a href="./deleteMovie?id=<%= movie.getMovieId() %>">Xoá</a></td>
		</tr>
		<%
		}
		%>
	</table>
	
</body>
</html>