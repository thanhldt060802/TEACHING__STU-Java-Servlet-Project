package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import infrastructure.MySQLDB;

@WebListener
public class AppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		MySQLDB.closeConnection();
	}

	public void contextInitialized(ServletContextEvent sce) {
		MySQLDB.openConnection();
	}

}
