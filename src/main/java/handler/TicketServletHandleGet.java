package handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dao.ProductDAO;
import dao.SeatDAO;
import dao.TheaterDAO;
import dao.TicketDAO;
import dao.TicketProductDAO;
import dao.TicketSeatDAO;
import model.Movie;
import model.Theater;
import model.Ticket;
import model.TicketProduct;
import model.TicketSeat;
import model.User;

public class TicketServletHandleGet {

	private TicketDAO ticketDAO;
	private TicketSeatDAO ticketSeatDAO;
	private SeatDAO seatDAO; 
	private ProductDAO productDAO;
	private TicketProductDAO ticketProductDAO;
	private MovieDAO movieDAO;
	private TheaterDAO theaterDAO;
	
	public TicketServletHandleGet() {
		this.ticketDAO = new TicketDAO();
		this.ticketSeatDAO = new TicketSeatDAO();
		this.seatDAO = new SeatDAO();
		this.ticketProductDAO = new TicketProductDAO();
		this.productDAO = new ProductDAO();
		this.movieDAO = new MovieDAO();
		this.theaterDAO = new TheaterDAO();
	}
	
	public void handleGetTickets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Ticket> ticketList = this.ticketDAO.getTickets();
		request.setAttribute("ticketList", ticketList);
		
		Map<Long, String> ticketIdMovieNameMap = ticketList.stream()
				.collect(Collectors.toMap((ticket) -> ticket.getTicketId(), (ticket) -> this.movieDAO.getMovieById(ticket.getMovieId()).getTitle()));
		request.setAttribute("ticketIdMovieNameMap", ticketIdMovieNameMap);
		
		Map<Long, String> ticketIdTheaterNameMap = ticketList.stream()
				.collect(Collectors.toMap((ticket) -> ticket.getTicketId(), (ticket) -> this.theaterDAO.getTheaterById(ticket.getTheaterId()).getName()));
		request.setAttribute("ticketIdTheaterNameMap", ticketIdTheaterNameMap);

		request.getRequestDispatcher("./table-ticket.jsp").forward(request, response);
	}
	
	public void handleGetTicketDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		Ticket foundTicket = this.ticketDAO.getTicketById(id);
		request.setAttribute("foundTicket", foundTicket);
		
		Movie foundMovie = this.movieDAO.getMovieById(foundTicket.getMovieId());
		request.setAttribute("foundMovie", foundMovie);
		
		Theater foundTheater = this.theaterDAO.getTheaterById(foundTicket.getTheaterId());
		request.setAttribute("foundTheater", foundTheater);

		Map<TicketSeat, String> ticketSeatMap = this.ticketSeatDAO.getAllTicketSeatsByTicketId(id).stream()
				.collect(Collectors.toMap((ticketSeat) -> ticketSeat, (ticketSeat) -> this.seatDAO.getSeatById(ticketSeat.getSeatId()).getSeatNumber()));
		request.setAttribute("ticketSeatMap", ticketSeatMap);
		
		Map<TicketProduct, String> ticketProductMap = this.ticketProductDAO.getAllTicketProductsByTicketId(id).stream()
				.collect(Collectors.toMap((ticketProduct) -> ticketProduct, (ticketProduct) -> this.productDAO.getProductById(ticketProduct.getProductId()).getName()));
		request.setAttribute("ticketProductMap", ticketProductMap);
		
		request.getRequestDispatcher("./modify-ticket.jsp").forward(request, response);
	}
	
	public void handleCreateTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getTickets");
	}
	
	public void handleUpdateTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getTickets");
	}
	
	public void handleDeleteTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		if(!this.ticketDAO.deleteTicket(id, null)) {
			System.out.println("Delete ticket failed");
			response.sendRedirect("./getTickets");
			return;
		}
		
		System.out.println("Delete user successful");
		response.sendRedirect("./getTickets");
	}
	
	public void handleMyTickets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		
		List<Ticket> ticketList = this.ticketDAO.getTicketsByUserId(loginUser.getUserId());
		request.setAttribute("ticketList", ticketList);
		
		Map<Long, String> ticketIdMovieNameMap = ticketList.stream()
				.collect(Collectors.toMap((ticket) -> ticket.getTicketId(), (ticket) -> this.movieDAO.getMovieById(ticket.getMovieId()).getTitle()));
		request.setAttribute("ticketIdMovieNameMap", ticketIdMovieNameMap);
		
		Map<Long, String> ticketIdTheaterNameMap = ticketList.stream()
				.collect(Collectors.toMap((ticket) -> ticket.getTicketId(), (ticket) -> this.theaterDAO.getTheaterById(ticket.getTheaterId()).getName()));
		request.setAttribute("ticketIdTheaterNameMap", ticketIdTheaterNameMap);

		request.getRequestDispatcher("./my-tickets.jsp").forward(request, response);
	}
	
	public void handleMyTicketDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		Ticket foundTicket = this.ticketDAO.getTicketById(id);
		request.setAttribute("foundTicket", foundTicket);
		
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(foundTicket.getUserId() != loginUser.getUserId()) {
			response.sendRedirect("./myTickets");
			return;
		}

		Movie foundMovie = this.movieDAO.getMovieById(foundTicket.getMovieId());
		request.setAttribute("foundMovie", foundMovie);
		
		Theater foundTheater = this.theaterDAO.getTheaterById(foundTicket.getTheaterId());
		request.setAttribute("foundTheater", foundTheater);
		
		Map<TicketSeat, String> ticketSeatMap = this.ticketSeatDAO.getAllTicketSeatsByTicketId(id).stream()
				.collect(Collectors.toMap((ticketSeat) -> ticketSeat, (ticketSeat) -> this.seatDAO.getSeatById(ticketSeat.getSeatId()).getSeatNumber()));
		request.setAttribute("ticketSeatMap", ticketSeatMap);
		
		Map<TicketProduct, String> ticketProductMap = this.ticketProductDAO.getAllTicketProductsByTicketId(id).stream()
				.collect(Collectors.toMap((ticketProduct) -> ticketProduct, (ticketProduct) -> this.productDAO.getProductById(ticketProduct.getProductId()).getName()));
		request.setAttribute("ticketProductMap", ticketProductMap);
		
		request.getRequestDispatcher("./my-ticket-detail.jsp").forward(request, response);
	}
	
}
