package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class ZoekStudent extends RequestHandler {
    public ZoekStudent(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        Utility.checkRoles(request, roles);
        String zoekOpdracht = request.getParameter("zoekOpdracht");
        if (!zoekOpdracht.startsWith("r") || zoekOpdracht.length() != 8) {
            request.setAttribute("error", "Gelieve een geldig r-Nummer te geven!");
            return "zoekStudent.jsp";
        }

        return "Controller?command=StudentLessen&zoekOpdracht=" + zoekOpdracht;
    }
}
