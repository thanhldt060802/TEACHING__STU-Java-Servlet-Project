<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	LỐI TẮT CHUNG:&emsp;&emsp;<a href="">TRANG CHỦ</a>&emsp;/&emsp;<a href="">SẢN PHẨM</a>
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
	<a href="">THÔNG TIN TÀI KHOẢN</a>&emsp;/&emsp;<a href="">GIỎ HÀNG</a>&emsp;/&emsp;<a href="">HOÁ ĐƠN</a>&emsp;/&emsp;<a href="./logout">ĐĂNG XUẤT</a>
	<%
	if (loginUser.getRoleName().equals("ADMIN")) {
	%>
	<div>
		DỮ LIỆU:&emsp;&emsp;<a href="./getAllUsers">NGƯỜI DÙNG</a>&emsp;/&emsp;<a href="">DANH MỤC SẢN PHẨM</a>&emsp;/&emsp;<a href="">SẢN PHẨM</a>&emsp;/&emsp;<a href="">GIỎ HÀNG</a>&emsp;/&emsp;<a href="">HOÁ ĐƠN</a>
	</div>
	<%
	}
	%>
	<%
	}
	%>
</div>