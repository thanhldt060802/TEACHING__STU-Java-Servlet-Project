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
	<h1>CHI TIẾT PHIM</h1>
	
	<%
	Movie foundMovie = (Movie) request.getAttribute("foundMovie");
	%>

	<ul>
		<li>Tên phim: <%=foundMovie.getTitle() %></li>
		<li>Hình ảnh: <%=foundMovie.getImage() %></li>
		<li>Thể loại: <%=foundMovie.getGenre() %></li>
		<li>Thời lượng: <%=foundMovie.getDuration() %> phút</li>
		<li>Khởi chiếu: <%=new SimpleDateFormat("dd/MM/yyyy").format(foundMovie.getReleaseDateAt()) %></li>
	</ul>
	
</body>
</html>