package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Rol;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class StudentLessen extends RequestHandler {

    public StudentLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Rol[] roles = new Rol[]{Rol.STUDENT};
        Utility.checkRoles(request, roles);
        Student student = (Student) session.getAttribute("loggedIn");
        int id = getApplicationService().getStudentId(student.getRnummer());
        List<Lesson> lessenLijst = getApplicationService().getLessenVoorStudent(id);
        request.setAttribute("lessenLijst", lessenLijst);

        ArrayList<String> datums = new ArrayList<>();
        datums.add("1/1/2020");
        datums.add("2/2/2020");
        datums.add("3/3/2020");
        datums.add("4/4/2020");
        request.setAttribute("datums", datums);

        /*

        List<Lector> lectorlijst = new ArrayList<>();
        for (Lesson les: lessenLijst
             ) {

            List<Lector> l = getApplicationService().getVakPerLector(getApplicationService().getVakId(les.getNaam()));
            for (Lector lector: l
                 ) {
                lectorlijst.add(lector);
            }
        }


        request.setAttribute("lectorenlijst", lectorlijst);
        */
        return "studentLessen.jsp";
    }
}
