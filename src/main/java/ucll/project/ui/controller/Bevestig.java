package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bevestig extends RequestHandler{
    public Bevestig(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String bevestiging = request.getParameter("bevestiging");
        int studentId = getApplicationService().getStudentId(request.getParameter("student"));
        int lesId = getApplicationService().getVakId(request.getParameter("les"));
        getApplicationService().zetBevestiging(bevestiging, studentId, lesId);

        return "Controller?LectorOverviewStudents&vaknaam=" + request.getParameter("les");
    }
}
