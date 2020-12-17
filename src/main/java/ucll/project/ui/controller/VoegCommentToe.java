package ucll.project.ui.controller;

import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class VoegCommentToe extends RequestHandler {
    public VoegCommentToe(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        Student student = getApplicationService().getStudent(request.getParameter("student"));
        request.setAttribute("student", student);
        request.setAttribute("les", request.getParameter("les"));
        request.setAttribute("datum", request.getParameter("datum"));
        return "voegCommentToe.jsp";
    }
}
