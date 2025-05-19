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

	<ul>
		<%
		for(Movie movie : movieList) {
		%>
		<li><%=movie.getTitle() %> - <%=movie.getImage() %> <a href="./getMovieDetail?id=<%=movie.getMovieId() %>">Xem chi tiết</a></li>
		<%
		}
		%>
	</ul>
	
</body>
</html>