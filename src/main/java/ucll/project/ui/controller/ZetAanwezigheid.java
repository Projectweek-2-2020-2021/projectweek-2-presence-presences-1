package ucll.project.ui.controller;

import ucll.project.domain.model.Rol;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ZetAanwezigheid extends RequestHandler{
    public ZetAanwezigheid(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Rol[] roles = new Rol[]{Rol.STUDENT};
        Utility.checkRoles(request, roles);
        String aanwezigheid = request.getParameter("aanwezigheid");
        String les = request.getParameter("les");
        int lesId = getApplicationService().getVakId(les);
        String datum = request.getParameter("datum");
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Student student = (Student) request.getSession().getAttribute("loggedIn");
        int id = getApplicationService().getStudentId(student.getRnummer());
        getApplicationService().zetAanwezigheid(aanwezigheid, id , lesId, Date.valueOf(date));
        return "Controller?command=StudentLessen";
    }
}
