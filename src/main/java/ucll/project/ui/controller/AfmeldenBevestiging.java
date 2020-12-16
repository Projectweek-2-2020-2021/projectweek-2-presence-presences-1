package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class AfmeldenBevestiging extends RequestHandler {
    public AfmeldenBevestiging(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        String beslissing = request.getParameter("beslissing");
        if (beslissing.equals("ja")) {
            request.getSession().invalidate();
        }
        return "Controller?command=Index";
    }
}
