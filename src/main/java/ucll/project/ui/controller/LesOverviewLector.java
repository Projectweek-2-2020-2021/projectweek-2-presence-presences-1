package ucll.project.ui.controller;


import ucll.project.domain.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LesOverviewLector extends RequestHandler {

    public LesOverviewLector(String command, LessonService countryService) {
        super(command, countryService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
