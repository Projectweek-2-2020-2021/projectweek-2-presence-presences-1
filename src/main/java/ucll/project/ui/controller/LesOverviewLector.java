package ucll.project.ui.controller;


import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LesOverviewLector extends RequestHandler {

    public LesOverviewLector(String command, ApplicationService countryService) {
        super(command, countryService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
