package ucll.project.ui.controller;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LessonOverview extends RequestHandler {

    public LessonOverview(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Lesson> lessonLijst = getCountryService().getLessons();
        request.setAttribute("lessonLijst", lessonLijst);
        return "lessonOverview.jsp";
    }
}
