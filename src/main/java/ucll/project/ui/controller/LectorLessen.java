package ucll.project.ui.controller;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LectorLessen extends RequestHandler {

    public LectorLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Lesson> lessenLijst = getApplicationService().getLessonForLector("u1234567");
        request.setAttribute("lessenLijst", lessenLijst);
        return "lectorLessen.jsp";
    }
}
