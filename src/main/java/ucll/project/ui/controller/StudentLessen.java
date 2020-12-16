package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Rol;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class StudentLessen extends RequestHandler {

    public StudentLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Rol[] roles = new Rol[]{Rol.STUDENT};
        Utility.checkRoles(request, roles);
        List<Lesson> lessenLijst = getApplicationService().getLessons();
        request.setAttribute("lessenLijst", lessenLijst);

        ArrayList<String> datums = new ArrayList<>();
        datums.add("1/1/2020");
        datums.add("2/2/2020");
        datums.add("3/3/2020");
        datums.add("4/4/2020");
        request.setAttribute("datums", datums);


        List<List<Lector>> lectorlijst = new ArrayList<>();
        for (Lesson les: lessenLijst
             ) {
            System.out.println(getApplicationService().getVakId(les.getNaam()));
            System.out.println(getApplicationService().getLectorPerVak(getApplicationService().getVakId(les.getNaam())));
            List<Lector> l = getApplicationService().getLectorPerVak(getApplicationService().getVakId(les.getNaam()));
            lectorlijst.add(l);
        }

        request.setAttribute("lectorenlijst", lectorlijst);


        return "studentLessen.jsp";
    }
}
