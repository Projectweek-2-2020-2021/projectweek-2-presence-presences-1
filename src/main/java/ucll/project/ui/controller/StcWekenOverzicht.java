package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class StcWekenOverzicht extends RequestHandler{
    public StcWekenOverzicht(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        return "stcWekenOverzicht.jsp";
    }
}
