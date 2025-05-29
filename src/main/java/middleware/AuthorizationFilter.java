package middleware;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
@WebFilter({ "/getUsers" })
public class AuthorizationFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		
//		User loginUser = (User) req.getSession().getAttribute("loginUser");
//		
//		String route = req.getServletPath();
//		
//		if(route.equals("/getUsers")) {
//			if(loginUser != null && loginUser.getRoleName().equals("ADMIN")) {
//				chain.doFilter(request, response);
//				return;
//			}
//		}
//		
//		System.out.println("Bạn không có quyền");
//		res.sendRedirect("./home");
		
		chain.doFilter(request, response);
	}

}
