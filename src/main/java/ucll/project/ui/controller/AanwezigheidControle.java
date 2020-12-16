package ucll.project.ui.controller;

import ucll.project.domain.model.Rol;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AanwezigheidControle extends RequestHandler {
    public AanwezigheidControle(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Rol[] roles = new Rol[]{Rol.STUDENT};
        Utility.checkRoles(request, roles);
        String lesNaam = request.getParameter("naam");
        String datum = request.getParameter("datum");
        request.setAttribute("naam", lesNaam);
        request.setAttribute("datum", datum);
        return "bevestigAanwezigheid.jsp";
    }
}
