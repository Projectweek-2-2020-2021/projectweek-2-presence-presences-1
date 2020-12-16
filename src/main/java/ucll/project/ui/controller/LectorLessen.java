package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Rol;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class LectorLessen extends RequestHandler {

    public LectorLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Rol[] roles = new Rol[]{Rol.LECTOR};
        Utility.checkRoles(request, roles);
        Lector lector = (Lector) request.getSession().getAttribute("loggedIn");
        List<Lesson> lessenLijst = getApplicationService().getLessonForLector(lector.getLectorennummer());
        request.setAttribute("lessenLijst", lessenLijst);

        ArrayList<String> datums = new ArrayList<>();
        datums.add("01/01/2020");
        datums.add("02/02/2020");
        datums.add("03/03/2020");
        datums.add("04/04/2020");
        request.setAttribute("datums", datums);
        return "lectorLessen.jsp";
    }
}
