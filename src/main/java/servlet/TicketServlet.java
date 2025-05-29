package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.TicketServletHandleGet;
import handler.TicketServletHandlePost;


@WebServlet({ "/getTickets", "/getTicketDetail", "/createTicket", "/deleteTicket", "/myTickets", "myTicketDetail" })
public class TicketServlet extends HttpServlet {
	
	private TicketServletHandleGet handleGet;
	private TicketServletHandlePost handlePost;
	
	private static final String GET_TICKETS_PATTERN = "/getTickets";  // GET
	private static final String GET_TICKET_DETAIL_PATTERN = "/getTicketDetail";  // GET
	private static final String CREATE_TICKET_PATTERN = "/createTicket";  // GET & POST
	private static final String DELETE_TICKET_PATTERN = "/deleteTicket";  // GET
	private static final String MY_TICKETS_PATTERN = "/myTickets";  // GET
	private static final String MY_TICKET_DETAIL_PATTERN = "/myTicketDetail";  // GET
	
    public TicketServlet() {
    	this.handleGet = new TicketServletHandleGet();
    	this.handlePost = new TicketServletHandlePost();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case GET_TICKETS_PATTERN:
			this.handleGet.handleGetTickets(request, response);
			break;

		case GET_TICKET_DETAIL_PATTERN:
			this.handleGet.handleGetTicketDetail(request, response);
			break;
			
		case CREATE_TICKET_PATTERN:
			this.handleGet.handleCreateTicket(request, response);
			break;
			
		case DELETE_TICKET_PATTERN:
			this.handleGet.handleDeleteTicket(request, response);
			break;
			
		case MY_TICKETS_PATTERN:
			this.handleGet.handleMyTickets(request, response);
			break;
			
		case MY_TICKET_DETAIL_PATTERN:
			this.handleGet.handleMyTicketDetail(request, response);
			break;

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {
			
		case CREATE_TICKET_PATTERN:
			this.handlePost.handleCreateTicket(request, response);
			break;
			
		}
	}

}
