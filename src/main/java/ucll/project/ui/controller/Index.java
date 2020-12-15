package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicatieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends RequestHandler {

    public Index(String command, ApplicatieService applicatieService) {
        super(command, applicatieService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }
}
