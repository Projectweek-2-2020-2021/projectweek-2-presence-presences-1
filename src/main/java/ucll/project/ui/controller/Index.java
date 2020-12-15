package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends RequestHandler {

    public Index(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }
}
