package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class GewettigdAfwezig extends RequestHandler {
    public GewettigdAfwezig(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        int studentId = getApplicationService().getStudentId(request.getParameter("student"));
        int lesId = getApplicationService().getVakId(request.getParameter("les"));
        getApplicationService().zetGewettigdeAfwezigheid(studentId, lesId);

        return "Controller?LectorOverzichtStudenten&vaknaam=" + request.getParameter("les");
    }
}
