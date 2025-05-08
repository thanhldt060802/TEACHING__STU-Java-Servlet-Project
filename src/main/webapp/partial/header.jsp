<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	LỐI TẮT CHUNG:&emsp;&emsp;<a href="./home">TRANG CHỦ</a>&emsp;/&emsp;<a href="./product">SẢN PHẨM</a>
</div>
<%
User loginUser = (User) request.getSession().getAttribute("loginUser");
%>
<div>
	TÀI KHOẢN:&emsp;&emsp;
	<%
	if (loginUser == null) {
	%>
	<a href="./login">ĐĂNG NHẬP</a>
	<%
	} else {
	%>
	<a href="./myAccount">THÔNG TIN TÀI KHOẢN</a>&emsp;/&emsp;<a href="./myCart">GIỎ HÀNG</a>&emsp;/&emsp;<a href="./myInvoice">HOÁ ĐƠN</a>&emsp;/&emsp;<a href="./logout">ĐĂNG XUẤT</a>
	<%
	if (loginUser.getRoleName().equals("ADMIN")) {
	%>
	<div>
		DỮ LIỆU:&emsp;&emsp;<a href="./getUsers">NGƯỜI DÙNG</a>&emsp;/&emsp;<a href="./getCategories">DANH MỤC SẢN PHẨM</a>&emsp;/&emsp;<a href="./getBrands">DANH MỤC THƯƠNG HIỆU</a>&emsp;/&emsp;<a href="./getProducts">SẢN PHẨM</a>&emsp;/&emsp;<a href="./getCarts">GIỎ HÀNG</a>&emsp;/&emsp;<a href="./getInvoices">HOÁ ĐƠN</a>
	</div>
	<%
	}
	%>
	<%
	}
	%>
</div>