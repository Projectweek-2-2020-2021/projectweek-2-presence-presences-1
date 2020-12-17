package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Rol;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class LectorLessen extends RequestHandler {

    public LectorLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        LinkedHashMap<Date, List<Lesson>> lessenPerDag = new LinkedHashMap<>();
        List<Lesson> lessenLijst;
        Rol[] roles = new Rol[]{Rol.LECTOR};
        Utility.checkRoles(request, roles);
        Lector lector = (Lector) request.getSession().getAttribute("loggedIn");
        String nummer = lector.getLectorennummer();
        int id = getApplicationService().getLectorId(nummer);

        List<Date> datums = getApplicationService().getAllDatumsLector();
        Collections.sort(datums);

        for (Date d : datums){
            lessenLijst = getApplicationService().getLessonForLector(id, d);
            lessenPerDag.put(d, lessenLijst);
        }
        request.setAttribute("lessenPerDag", lessenPerDag);

        return "lectorLessen.jsp";
    }
}
