package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class Zoeken extends RequestHandler {
    public Zoeken(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        Utility.checkRoles(request, roles);
        return "zoekStudent.jsp";
    }
}
