<%@page import="model.Product"%>
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
	<h1>DANH SÁCH SẢN PHẨM</h1>
	
	<%
	List<Product> productList = (List<Product>) request.getAttribute("productList");
	%>

	<table border="1">
		<tr>
			<th>Mã số</th>
			<th>Tên sản phẩm</th>
			<th>Giá tiền</th>
			<th>Giảm giá</th>
			<th>Số lượng</th>
			<th>Chức năng</th>
		</tr>
		<%
		for(Product product : productList) {
		%>
		<tr>
			<td><%= product.getProductId() %></td>
			<td><%= product.getName() %></td>
			<td><%= product.getPrice() %>vnđ</td>
			<td><%= product.getDiscountPercentage() %>%</td>
			<td><%= product.getStock() %></td>
			<td><a href="./getProductDetail?id=<%= product.getProductId() %>">Chỉnh sửa</a></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<br>
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Tên sản phẩm:&emsp;<input id="name" type="text"/>
		<br>
		Hình ảnh:&emsp;<input id="image" type="text"/>
		<br>
		Giá tiền:&emsp;<input id="price" type="number"/>
		<br>
		Giảm giá:&emsp;<input id="discount-percentage" type="number"/>
		<br>
		Số lượng:&emsp;<input id="stock" type="number"/>
		<br>
		<button type="button" onclick="submitFormCreateProduct()">Tạo mới</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormCreateProduct() {
			const form = document.getElementById("simple-form");
			
		    const params = new URLSearchParams();
		    params.append("nameInput", document.getElementById("name").value);
		    params.append("imageInput", document.getElementById("image").value);
		    params.append("priceInput", document.getElementById("price").value);
		    params.append("disountPercentageInput", document.getElementById("discount-percentage").value);
		    params.append("stockInput", document.getElementById("stock").value);
	
		    fetch("./createProduct", {
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