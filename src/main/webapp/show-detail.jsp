<%@page import="model.Product"%>
<%@page import="model.Seat"%>
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
	<h1>CHI TIẾT XUẤT CHIẾU </h1>
	
	<%
	Show foundShow = (Show) request.getAttribute("foundShow");
	Movie foundMovie = (Movie) request.getAttribute("foundMovie");
	Theater foundTheater = (Theater) request.getAttribute("foundTheater");
	List<Seat> seatList = (List<Seat>) request.getAttribute("seatList");
	List<Product> productList = (List<Product>) request.getAttribute("productList");
	%>

	<ul>
		<li>Hình ảnh: <%=foundMovie.getImage() %></li>
		<li>Tên phim: <%=foundMovie.getTitle() %></li>
		<li>Thời lượng: <%=foundMovie.getDuration() %> phút</li>
		<li>Tên rạp: <%=foundTheater.getName() %></li>
		<li>Địa điểm: <%=foundTheater.getLocation() %></li>
		<li>Thời gian: <%=foundShow.getStartAt() %></li>
	</ul>
	
	<br>
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		<ul>
			<li>Chỗ ngồi<br>
				<%
				for(Seat seat : seatList) {
				%>
				<input type="checkbox" class="seats-choosen" value="<%=seat.getSeatId() %>"><label><%=seat.getSeatNumber() %></label><br>
				<%
				}
				%>
			</li>
			<li>Snack + drink<br>
				<%
				for(Product product : productList) {
				%>
				<input type="checkbox" class="products-choosen" value="<%=product.getProductId() %>"><label><%=product.getName() %> - <%=product.getImage() %> - <%=product.getPrice() %>vnđ (-<%=product.getDiscountPercentage() %>%)</label>
				(Số lượng: <input type="number" class="quantity-input" data-for="<%=product.getProductId() %>" min="0">)<br>
				<%
				}
				%>
			</li>
		</ul>
		<br>
		<button type="button" onclick="submitFormCreateTicket()">Đặt vé</button>
	</form>
	
</body>
</html>