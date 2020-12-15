package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicatieService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {
    private ApplicatieService applicatieService;

    public RequestHandler(String command, ApplicatieService applicatieService) {
        setApplicatieService(applicatieService);
    }

    public ApplicatieService getApplicatieService() {
        return applicatieService;
    }

    private void setApplicatieService(ApplicatieService applicatieService) {
        if (applicatieService == null) {
            throw new ControllerException("Lesson service cannot be null.");
        }
        this.applicatieService = applicatieService;
    }

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    void forwardRequest(String destination, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }


}
