<%@page import="java.util.Map.Entry"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.TicketProduct"%>
<%@page import="model.TicketSeat"%>
<%@page import="java.util.Map"%>
<%@page import="model.Movie"%>
<%@page import="model.Ticket"%>
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
	<h1>CHỈNH SỬA THÔNG TIN VÉ</h1>
	
	<%
	Ticket foundTicket = (Ticket) request.getAttribute("foundTicket");
	Movie foundMovie = (Movie) request.getAttribute("foundMovie");
	Theater foundTheater = (Theater) request.getAttribute("foundTheater");
	Map<TicketSeat, String> ticketSeatMap = (Map<TicketSeat, String>) request.getAttribute("ticketSeatMap");
	Map<TicketProduct, String> ticketProductMap = (Map<TicketProduct, String>) request.getAttribute("ticketProductMap");
	%>
		
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Mã số vé:&emsp;<input id="id" type="text" value="<%= foundTicket.getTicketId() %>" readonly="readonly"/>
		<br>
		Mã số người dùng:&emsp;<input id="user-id" type="text" value="<%= foundTicket.getUserId() %>" readonly="readonly"/>
		<br>
		Tên phim:&emsp;<input type="text" value="<%= foundMovie.getTitle() %>" readonly="readonly"/>
		<br>
		Tên rạp:&emsp;<input type="text" value="<%= foundTheater.getName() %>" readonly="readonly"/>
		<br>
		Tổng giá tiền (VNĐ):&emsp;<input id="total-amount" type="text" value="<%= foundTicket.getTotalAmount() %>" readonly="readonly"/>
		<br>
		Ngày tạo:&emsp;<input id="created-at" type="date" value="<%= new SimpleDateFormat("dd/MM/yyyy").format(foundTicket.getCreatedAt()) %>" readonly="readonly"/>
		<br>
		<button type="button" onclick="deleteTheater()">Xoá</button>
	</form>
	
	<br>
	
	<ul>
		<li>Chỗ ngồi<br>
			<%
			for(Entry<TicketSeat, String> entry : ticketSeatMap.entrySet()) {
			%>
			<span><%= entry.getValue() %> - Đơn giá: <%= entry.getKey().getPrice() %> VNĐ - Giảm giá: <%= entry.getKey().getDiscountPercentage() %>% - Tổng giá: <%= entry.getKey().getTotalPrice() %></span>
			<%
			}
			%>
		</li>
		<li>Snack + drink<br>
			<%
			for(Entry<TicketProduct, String> entry : ticketProductMap.entrySet()) {
			%>
			<span><%= entry.getValue() %> - Đơn giá: <%= entry.getKey().getPrice() %> VNĐ - Giảm giá: <%= entry.getKey().getDiscountPercentage() %>% - Số lượng: <%= entry.getKey().getQuantity() %> - Tổng giá: <%= entry.getKey().getTotalPrice() %> VNĐ</span>
			<%
			}
			%>
		</li>
	</ul>
	
	<script>
		function deleteMovie() {
			window.location.href = "./deleteTicket?id=" + <%= foundTicket.getTicketId() %>
		}
	</script>

</body>
</html>