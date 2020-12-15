package ucll.project.ui.controller;


import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class LectorOverviewStudents extends RequestHandler{


    public LectorOverviewStudents(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //request.setAttribute("students", studentservice.getAll());
        return "lectorOverzichtStudenten.jsp";
    }
}