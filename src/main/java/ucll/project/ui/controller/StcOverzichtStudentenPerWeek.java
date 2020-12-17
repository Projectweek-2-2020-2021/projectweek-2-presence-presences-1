package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.LesStudent;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StcOverzichtStudentenPerWeek extends RequestHandler{
    public StcOverzichtStudentenPerWeek(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Lector lector = (Lector) request.getSession().getAttribute("loggedIn");
        LinkedHashMap<Student, List<LesStudent>> lessenVoorStudenten = new LinkedHashMap<>();
        String van = request.getParameter("van");
        String tot= request.getParameter("tot");
        java.sql.Date vanDate = java.sql.Date.valueOf(van);
        java.sql.Date totDate = java.sql.Date.valueOf(tot);
        List<LesStudent> lesStudents = getApplicationService().getLesStudentVoorStudentenVanStc(vanDate, totDate, lector.getLectorennummer());
        Collections.sort(lesStudents);
        List<Student> students = getApplicationService().getStudentenvoorLector(lector.getLectorennummer());
        Collections.sort(students);
        for (Student student: students) {
            List<LesStudent> l = new ArrayList<>();
            for (LesStudent lesStudent: lesStudents) {
                if (lesStudent.getrnummer().equals(student.getRnummer())) {
                    l.add(lesStudent);
                }
            }
            lessenVoorStudenten.put(student, l);
        }
        request.setAttribute("lessenVoorStudenten", lessenVoorStudenten);
        return "stcOverzichtStudentenPerWeek.jsp";
    }
}
