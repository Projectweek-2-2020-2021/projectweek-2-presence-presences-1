package ucll.project.ui.controller;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.service.ApplicatieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentLessen extends RequestHandler {

    public StudentLessen(String command, ApplicatieService applicatieService) {
        super(command, applicatieService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Lesson> lessonLijst = getApplicatieService().getLessons();
        request.setAttribute("lessonLijst", lessonLijst);
        return "studentLessen.jsp";
    }
}
