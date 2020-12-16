package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bevestig extends RequestHandler{
    public Bevestig(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String bevestiging = request.getParameter("bevestiging");
        String datum = request.getParameter("datum");
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int studentId = getApplicationService().getStudentId(request.getParameter("student"));
        int lesId = getApplicationService().getVakId(request.getParameter("les"));
        String les = request.getParameter("les");
        getApplicationService().zetBevestiging(bevestiging, studentId, lesId, Date.valueOf(date));
        String url = "Controller?command=LectorOverzichtStudenten&vaknaam=" + les;

        return url;
    }
}
