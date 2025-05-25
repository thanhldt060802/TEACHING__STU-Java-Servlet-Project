<%@page import="model.Movie"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Show"%>
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
	<h1>DANH SÁCH XUẤT CHIẾU</h1>
	
	<%
	List<Show> showList = (List<Show>) request.getAttribute("showList");
	Map<Long, String> showIdMovieNameMap = (Map<Long, String>) request.getAttribute("showIdMovieNameMap");
	Map<Long, String> showIdTheaterNameMap = (Map<Long, String>) request.getAttribute("showIdTheaterNameMap");
	List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
	List<Theater> theaterList = (List<Theater>) request.getAttribute("theaterList");
	%>

	<table border="1">
		<tr>
			<th>Mã số</th>
			<th>Tên phim</th>
			<th>Tên rạp</th>
			<th>Thời gian</th>
			<th>Chức năng</th>
		</tr>
		<%
		for(Show show : showList) {
		%>
		<tr>
			<td><%= show.getShowId() %></td>
			<td><%= showIdMovieNameMap.get(show.getShowId()) %></td>
			<td><%= showIdTheaterNameMap.get(show.getShowId()) %></td>
			<td><%= new SimpleDateFormat("dd/MM/yyyy").format(show.getStartAt()) %></td>
			<td><a href="./getShowDetail?id=<%= show.getShowId() %>">Chỉnh sửa</a></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<br>
	
	<form id="simple-form" enctype="application/x-www-form-urlencoded;charset=UTF-8">
		Tên phim:&emsp;<select id="movie-id">
		    <%
		    for(Movie movie : movieList) {
		    %>
		    <option value="<%= movie.getMovieId() %>"><%= movie.getTitle() %></option>
		    <%
		    }
		    %>
		</select>
		<br>
		Tên rạp:&emsp;<select id="theater-id">
		    <%
		    for(Theater theater : theaterList) {
		    %>
		    <option value="<%= theater.getTheaterId() %>"><%= theater.getName() %></option>
		    <%
		    }
		    %>
		</select>
		<br>
		Thời gian:&emsp;<input id="start-at" type="date"/>
		<br>
		Giá tiền (VNĐ):&emsp;<input id="price" type="number"/>
		<br>
		Giảm giá (%):&emsp;<input id="discount-percentage" type="number"/>
		<br>
		<button type="button" onclick="submitFormCreateShow()">Tạo mới</button>
		<button type="button" onclick="resetForm()">Làm mới</button>
	</form>
	
	<script>
		function submitFormCreateShow() {
			const form = document.getElementById("simple-form");
			
		    const params = new URLSearchParams();
		    params.append("movieIdInput", document.getElementById("movie-id").value);
		    params.append("theaterIdInput", document.getElementById("theater-id").value);
		    params.append("startAtInput", document.getElementById("start-at").value);
		    params.append("priceInput", document.getElementById("price").value);
		    params.append("discountPercentageInput", document.getElementById("discount-percentage").value);
	
		    fetch("./createShow", {
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
		    document.getElementById("movie-id").value = "";
		    document.getElementById("theater-id").value = "";
		    document.getElementById("start-at").value = "";
		    document.getElementById("price").value = "";
		    document.getElementById("discount-percentage").value = "";
		}
	</script>
	
</body>
</html>