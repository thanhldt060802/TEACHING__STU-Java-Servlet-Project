<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	LỐI TẮT CHUNG:&emsp;&emsp;<a href="./home">TRANG CHỦ</a>&emsp;/&emsp;<a href="./movies">PHIM</a>&emsp;/&emsp;<a href="./theaters">RẠP</a>
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
	<a href="./myAccount">THÔNG TIN TÀI KHOẢN</a>&emsp;/&emsp;<a href="./myTicket">VÉ CỦA TÔI</a>&emsp;/&emsp;<a href="./logout">ĐĂNG XUẤT</a>
	<%
	if (loginUser.getRoleName().equals("ADMIN")) {
	%>
	<div>
		DỮ LIỆU:&emsp;&emsp;<a href="./getUsers">NGƯỜI DÙNG</a>&emsp;/&emsp;<a href="./getMovies">PHIM</a>&emsp;/&emsp;<a href="./getTheaters">RẠP</a>&emsp;/&emsp;<a href="./getShows">XUẤT CHIẾU</a>&emsp;/&emsp;<a href="./getProducts">SẢN PHẨM</a>&emsp;/&emsp;<a href="./getTickets">VÉ ĐÃ BÁN</a>
	</div>
	<%
	}
	%>
	<%
	}
	%>
</div>