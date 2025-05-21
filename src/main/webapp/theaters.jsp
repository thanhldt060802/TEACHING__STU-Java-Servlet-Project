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

	<ul>
		<%
		for(Theater theater : theaterList) {
		%>
		<li><%=theater.getName() %> (<a href="./getTheaterDetail?id=<%=theater.getTheaterId() %>">Xem chi tiết</a>)</li>
		<%
		}
		%>
	</ul>
	
</body>
</html>