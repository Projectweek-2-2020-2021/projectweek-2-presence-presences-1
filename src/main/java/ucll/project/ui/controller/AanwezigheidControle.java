package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicatieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AanwezigheidControle extends RequestHandler {
    public AanwezigheidControle(String command, ApplicatieService applicatieService) {
        super(command, applicatieService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String lesNaam = request.getParameter("naam");
        return "Controller?command=StudentLessen";
    }
}
