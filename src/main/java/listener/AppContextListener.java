package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import infrastructure.MySQLDB;

@WebListener
public class AppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Đóng kết nối đến các ứng dụng thứ 3 khác");
		
		MySQLDB.deregisterJDBC();
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Mở kết nối đến các ứng dụng thứ 3 khác");
	}

}
