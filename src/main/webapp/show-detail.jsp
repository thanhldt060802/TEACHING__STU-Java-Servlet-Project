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
		<li>Giá tiền: <%=foundShow.getPrice() %> VNĐ</li>
		<li>Giảm giá: <%=foundShow.getDiscountPercentage() %>%</li>
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
				<div class="product-item">
					<input type="checkbox" class="products-choosen" value="<%=product.getProductId() %>"><label><%=product.getName() %> - <%=product.getImage() %> - <%=product.getPrice() %>vnđ (-<%=product.getDiscountPercentage() %>%)</label>
					(Số lượng: <input type="number" class="quantity-input" data-for="<%=product.getProductId() %>" min="0" value="0">)<br>
				</div>
				<%
				}
				%>
			</li>
		</ul>
		<br>
		<button type="button" onclick="submitFormCreateTicket()">Đặt vé</button>
	</form>
	
	<script>
		function submitFormCreateTicket() {
			const form = document.getElementById("simple-form");
			
		    const params = new URLSearchParams();
		    document.querySelectorAll(".seats-choosen:checked").forEach((checkbox) => {
				params.append("seatIdsInput", checkbox.value);
			});
		    document.querySelectorAll(".products-choosen:checked").forEach((checkbox) => {
				const productId = checkbox.value;

				// Truy xuất input ngay gần checkbox (cùng cha)
				const parent = checkbox.closest(".product-item");
				const quantityInput = parent?.querySelector(".quantity-input");
				if (!quantityInput) {
					return;
				}

				const quantity = parseInt(quantityInput.value);
				if (!isNaN(quantity) && quantity > 0) {
					params.append("productIdsInput", productId);
					params.append("quantity" + productId + "Input", quantity);
				}
			});
	
		    fetch("./createTicket", {
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
			location.reload();
		}
	</script>
	
</body>
</html>