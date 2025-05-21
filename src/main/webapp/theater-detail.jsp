<%@page import="java.util.Map.Entry"%>
<%@page import="model.Show"%>
<%@page import="model.Theater"%>
<%@page import="java.util.Map"%>
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
	<h1>CHI TIẾT RẠP</h1>
	
	<%
	Theater foundTheater = (Theater) request.getAttribute("foundTheater");
	Map<Movie, List<Show>> movieMap = (Map<Movie, List<Show>>) request.getAttribute("movieMap");
	%>

	<ul>
		<li>Tên rạp: <%=foundTheater.getName() %></li>
		<li>Địa điểm: <%=foundTheater.getLocation() %></li>
	</ul>
	
	<br>
	
	<ul>
		<%
		for(Entry<Movie, List<Show>> entry : movieMap.entrySet()) {
		%>
		<li><%= entry.getKey().getTitle() %>
			<ul>
				<%
				for(Show show : entry.getValue()) {
				%>
				<li><%= show.getStartAt()%> (<a href="./getShowDetail?id=<%= show.getShowId()%>">Xem chi tiết</a>)</li>
				<%
				}
				%>
			</ul>
		</li>
		<%
		}
		%>
	</ul>
	
</body>
</html>