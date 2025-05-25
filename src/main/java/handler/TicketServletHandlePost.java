package handler;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TicketServletHandlePost {
	
	public TicketServletHandlePost() {
	}
	
	public void handleCreateTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy danh sách seat
		String[] seats = request.getParameterValues("seatIdsInput");

		// Lấy danh sách product
		String[] products = request.getParameterValues("productIdsInput");
		Map<String, Integer> productQuantities = new HashMap<>();

		if (products != null) {
		    for (String productId : products) {
		        String quantityStr = request.getParameter("quantity" + productId + "Input");
		        if (quantityStr != null) {
		            try {
		                int quantity = Integer.parseInt(quantityStr);
		                if (quantity > 0) {
		                    productQuantities.put(productId, quantity);
		                }
		            } catch (NumberFormatException e) {
		                // Xử lý lỗi nếu cần
		            }
		        }
		    }
		}
		
		System.out.println(Arrays.toString(seats));
		System.out.println(Arrays.toString(products));
		System.out.println(productQuantities);
	}

}
