package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AanwezigheidControle extends RequestHandler {
    public AanwezigheidControle(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String lesNaam = request.getParameter("naam");
        return "Controller?command=StudentLessen";
    }
}
