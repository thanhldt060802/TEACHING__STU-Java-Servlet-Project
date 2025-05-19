<%@page import="model.Product"%>
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
	<h1>CHỈNH SỬA THÔNG TIN SẢN PHẨM</h1>
	
	<%
	Product foundProduct = (Product)request.getAttribute("foundProduct");
	%>
		
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Id:&emsp;<input id="id" type="text" value="<%= foundProduct.getProductId() %>" readonly="readonly"/>
		<br>
		Tên sản phẩm:&emsp;<input id="name" type="text" value="<%= foundProduct.getName() %>"/>
		<br>
		Hình ảnh:&emsp;<input id="image" type="text" value="<%= foundProduct.getImage() %>"/>
		<br>
		Giá tiền (vnđ):&emsp;<input id="price" type="number" value="<%= foundProduct.getPrice() %>"/>
		<br>
		Giảm giá (%):&emsp;<input id="discount-percentage" type="number" value="<%= foundProduct.getDiscountPercentage() %>"/>
		<br>
		Số lượng:&emsp;<input id="stock" type="number" value="<%= foundProduct.getStock() %>"/>
		<br>
		<button type="button" onclick="submitFormUpdateProduct()">Cập nhật</button>
		<button type="button" onclick="deleteProduct('<%= foundProduct.getProductId() %>')">Xoá</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormUpdateProduct() {
			const form = document.getElementById("simple-form");
			
			const params = new URLSearchParams();
		    params.append("idInput", document.getElementById("id").value);
		    params.append("nameInput", document.getElementById("name").value);
		    params.append("imageInput", document.getElementById("image").value);
		    params.append("priceInput", document.getElementById("price").value);
		    params.append("discountPercentageInput", document.getElementById("discount-percentage").value);
		    params.append("stockInput]", document.getElementById("stock").value);
	
		    fetch("./updateProduct", {
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
		
		function deleteMovie(id) {
			window.location.href = "./deleteProduct?id=" + id
		}
		
		function resetForm() {
			location.reload();
		}
	</script>

</body>
</html>