package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Rol;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentLessen extends RequestHandler {

    public StudentLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        LinkedHashMap<Date, List<Lesson>> lessenPerDag = new LinkedHashMap<>();
        List<Lesson> lessenLijst = new ArrayList<>();
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("loggedIn");
        Rol[] roles = new Rol[]{Rol.STUDENT};
        Utility.checkRoles(request, roles);
        int id = getApplicationService().getStudentId(student.getRnummer());



        List<Date> datums = getApplicationService().getAllDatums();
        Collections.sort(datums);

        for (Date d : datums){
             lessenLijst = getApplicationService().getLessenVoorStudent(id, d);
             for (Lesson l : lessenLijst){
                 int lesid = getApplicationService().getVakId(l.getNaam());
                 String status = getApplicationService().getstatus(id, lesid, d);
                 l.setStatus(status);
             }
             lessenPerDag.put(d,lessenLijst);
        }

        request.setAttribute("lessenPerDag", lessenPerDag);


        List<List<Lector>> lectorlijst = new ArrayList<>();
        for (Lesson les: lessenLijst
             ) {
            List<Lector> l = getApplicationService().getLectorPerVak(getApplicationService().getVakId(les.getNaam()));
            lectorlijst.add(l);
        }

        request.setAttribute("lectorenlijst", lectorlijst);


        return "studentLessen.jsp";
    }
}
