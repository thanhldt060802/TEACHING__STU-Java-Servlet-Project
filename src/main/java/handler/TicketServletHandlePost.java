package handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.SeatDAO;
import dao.ShowDAO;
import dao.TicketDAO;
import model.Product;
import model.Seat;
import model.Show;
import model.Ticket;
import model.TicketProduct;
import model.TicketSeat;
import model.User;

public class TicketServletHandlePost {
	
	private TicketDAO ticketDAO;
	private ShowDAO showDAO;
	private SeatDAO seatDAO;
	private ProductDAO productDAO;
	
	public TicketServletHandlePost() {
		this.ticketDAO = new TicketDAO();
		this.showDAO = new ShowDAO();
		this.seatDAO = new SeatDAO();
		this.productDAO = new ProductDAO();
	}
	
	public void handleCreateTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long movieId = Long.parseLong(request.getParameter("movieIdInput"));
		Long theaterId = Long.parseLong(request.getParameter("theaterIdInput"));
		Long showId = Long.parseLong(request.getParameter("showIdInput"));
		// Lấy danh sách seat
		String[] seatIds = request.getParameterValues("seatIdsInput");
		// Lấy danh sách product + quantity
		String[] productIds = request.getParameterValues("productIdsInput");
		Map<String, Integer> productQuantities = new HashMap<>();
		if (productIds != null) {
		    for (String productId : productIds) {
		        String quantityStr = request.getParameter("quantity" + productId + "Input");
		        int quantity = Integer.parseInt(quantityStr);
		        productQuantities.put(productId, quantity);
		    }
		}
		
		
				
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		Ticket newTicket = new Ticket();
		newTicket.setTicketId(System.nanoTime());
		newTicket.setUserId(loginUser.getUserId());
		newTicket.setMovieId(movieId);
		newTicket.setTheaterId(theaterId);
		newTicket.setTotalAmount(0L);
		newTicket.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		double totalAmount = 0;
		
		Show foundShow = this.showDAO.getShowById(showId);
		
		Map<TicketSeat, Seat> ticketSeatMap = new HashMap<TicketSeat, Seat>();
		for(String seatId : seatIds) {
			Seat foundSeat = this.seatDAO.getSeatById(Long.parseLong(seatId));
			foundSeat.setAvailable(false);
			
			TicketSeat newTicketSeat = new TicketSeat();
			newTicketSeat.setTicketId(newTicket.getTicketId());
			newTicketSeat.setSeatId(foundSeat.getSeatId());
			newTicketSeat.setPrice(foundShow.getPrice());
			newTicketSeat.setDiscountPercentage(foundShow.getDiscountPercentage());
			double totalPrice = newTicketSeat.getPrice() * (1 - newTicketSeat.getDiscountPercentage()/100.0);
			newTicketSeat.setTotalPrice((long)totalPrice);
			
			ticketSeatMap.put(newTicketSeat, foundSeat);
			totalAmount += totalPrice;
		}
		
		Map<TicketProduct, Product> ticketProductMap = new HashMap<TicketProduct, Product>();
		for(String productId : productIds) {
			Product foundProduct = this.productDAO.getProductById(Long.parseLong(productId));
			foundProduct.setStock(foundProduct.getStock() - productQuantities.get(productId));
			
			TicketProduct newTicketProduct = new TicketProduct();
			newTicketProduct.setTicketId(newTicket.getTicketId());
			newTicketProduct.setProductId(foundProduct.getProductId());
			newTicketProduct.setPrice(foundProduct.getPrice());
			newTicketProduct.setDiscountPercentage(foundProduct.getDiscountPercentage());
			newTicketProduct.setQuantity(productQuantities.get(productId));
			double totalPrice = newTicketProduct.getPrice() * (1 - newTicketProduct.getDiscountPercentage()/100.0) * newTicketProduct.getQuantity();
			newTicketProduct.setTotalPrice((long)totalPrice);
			
			ticketProductMap.put(newTicketProduct, foundProduct);
			totalAmount += totalPrice;
		}
		
		newTicket.setTotalAmount((long)totalAmount);
		
		if(!this.ticketDAO.createTicket(newTicket, ticketSeatMap, ticketProductMap, null)) {
			System.out.println("Create ticket failed");
			response.sendRedirect("./getShowDetail?id=" + showId);
			return;
		}
		
		System.out.println("Create ticket successful");
		response.sendRedirect("./myTickets");
	}

}
