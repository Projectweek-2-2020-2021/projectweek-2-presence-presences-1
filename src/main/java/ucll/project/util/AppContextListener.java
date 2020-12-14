package ucll.project.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DbConnectionService.connect();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DbConnectionService.disconnect();
    }
}