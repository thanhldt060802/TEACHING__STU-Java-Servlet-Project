<%@page import="java.util.Map"%>
<%@page import="model.Ticket"%>
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
	<h1>DANH SÁCH VÉ</h1>
	
	<%
	List<Ticket> ticketList = (List<Ticket>) request.getAttribute("ticketList");
	Map<Long, String> ticketIdMovieNameMap = (Map<Long, String>) request.getAttribute("ticketIdMovieNameMap");
	Map<Long, String> ticketIdTheaterNameMap = (Map<Long, String>) request.getAttribute("ticketIdTheaterNameMap");
	%>

	<table border="1">
		<tr>
			<th>Mã số</th>
			<th>Mã số người dùng</th>
			<th>Tên phim</th>
			<th>Tên rạp</th>
			<th>Tổng giá tiền</th>
			<th>Ngày tạo</th>
			<th>Chức năng</th>
		</tr>
		<%
		for(Ticket ticket : ticketList) {
		%>
		<tr>
			<td><%= ticket.getTicketId() %></td>
			<td><%= ticket.getUserId() %></td>
			<td><%= ticketIdMovieNameMap.get(ticket.getTicketId()) %></td>
			<td><%= ticketIdTheaterNameMap.get(ticket.getTicketId()) %></td>
			<td><%= ticket.getTotalAmount() %> VNĐ</td>
			<td><%= new SimpleDateFormat("dd/MM/yyyy").format(ticket.getCreatedAt()) %></td>
			<td><a href="./getTicketDetail?id=<%= ticket.getTicketId() %>">Chỉnh sửa</a></td>
		</tr>
		<%
		}
		%>
	</table>
	
</body>
</html>