package ucll.project.ui.controller;

import ucll.project.domain.model.Rol;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class RequestHandler {
    private ApplicationService applicationService;
    protected Rol[] roles = new Rol[]{Rol.STUDENT, Rol.LECTOR}; // Default rollen voor pagina's waar beide rollen in kunnen

    public RequestHandler(String command, ApplicationService applicationService) {
        setApplicationService(applicationService);
    }

    public ApplicationService getApplicationService() {
        return applicationService;
    }

    private void setApplicationService(ApplicationService applicationService) {
        if (applicationService == null) {
            throw new ControllerException("Country service cannot be null.");
        }
        this.applicationService = applicationService;
    }

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException;

    void forwardRequest(String destination, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }
}
