package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;

public class BevestigCommentaar extends RequestHandler {
    public BevestigCommentaar(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        if (request.getParameter("bevestigen").equals("bevestigen")) {
            int studentId = getApplicationService().getStudentId(request.getParameter("student"));
            int lesId = getApplicationService().getVakId(request.getParameter("vak"));
            LocalDate datum = LocalDate.parse(request.getParameter("datum"));
            Date date = Date.valueOf(datum);
            String opmerking = request.getParameter("opmerking");
            getApplicationService().setStudentCommentaar(studentId, lesId, date, opmerking);
        }
        return "Controller?command=LectorOverzichtStudenten&vaknaam=" + request.getParameter("vak") + "&datum=" + request.getParameter("datum");
    }
}
