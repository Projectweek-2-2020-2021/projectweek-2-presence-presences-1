package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GewettigdAfwezig extends RequestHandler {
    public GewettigdAfwezig(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        int studentId = getApplicationService().getStudentId(request.getParameter("student"));
        int lesId = getApplicationService().getVakId(request.getParameter("les"));
        String datum = request.getParameter("datum");
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        getApplicationService().zetGewettigdeAfwezigheid(studentId, lesId, Date.valueOf(date));

        return "Controller?command=LectorOverzichtStudenten&vaknaam=" + request.getParameter("les") + "&datum=" + request.getParameter("datum");
    }
}
