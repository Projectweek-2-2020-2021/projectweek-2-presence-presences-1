package ucll.project.ui.controller;


import ucll.project.domain.model.Rol;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class LectorOverzichtStudenten extends RequestHandler {


    public LectorOverzichtStudenten(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Rol[] roles = new Rol[]{Rol.LECTOR};
        Utility.checkRoles(request, roles);
        String vak = request.getParameter("vaknaam");
        int id = getApplicationService().getVakId(vak);
        List<Student> aanwezig = getApplicationService().getAllAanwezigheid(id);
        List<Student> afwezig = getApplicationService().getAllNietAanwezigheid(id);
        request.setAttribute("les", vak);
        request.setAttribute("aanwezig", aanwezig);
        request.setAttribute("afwezig", afwezig);

        return "lectorOverzichtStudenten.jsp";
    }
}