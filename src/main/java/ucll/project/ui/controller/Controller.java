package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private HandlerFactory handlerFactory;
    private ApplicationService service;


    @Override
    public void init() throws ServletException {
        super.init();
        handlerFactory = new HandlerFactory();
        service = new ApplicationService();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // checks connection with database and reconnects when necessary
            // with singleton-pattern: application is connected to database when initiated (cfr util.AppContextListener)
            // when the connection with the database has been idle for some time, the database itself disconnects the application.
            // therefore the application must reconnect to the database
            if (service.getConnection().isClosed()) {
                System.out.println("connection has been closed");
                service.reConnect();
            }

            String command = request.getParameter("command");
            if (command == null || command.trim().isEmpty()) {
                command = "Index";
            }
            RequestHandler handler = handlerFactory.getHandler(command, service);
            String destination = handler.handleRequest(request, response);
            handler.forwardRequest(destination, request, response);
        } catch (NotAuthorizedException e) {
            request.setAttribute("notAuthorizedError", "U bent niet geautoriseerd om de gewenste pagina te bezoeken.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("stacktrace", e.getStackTrace());
            request.getRequestDispatcher("index.jsp").forward(request, response);
//            throw new ControllerException(e.getMessage());
        }
    }
}

