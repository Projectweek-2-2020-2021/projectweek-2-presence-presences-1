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
        request.setAttribute("naam", request.getParameter("naam"));
        request.setAttribute("datum", request.getParameter("datum"));
        return "bevestigAanwezigheid.jsp";
    }
}
