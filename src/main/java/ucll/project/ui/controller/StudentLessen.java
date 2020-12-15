package ucll.project.ui.controller;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentLessen extends RequestHandler {

    public StudentLessen(String command, LessonService lessonService) {
        super(command, lessonService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Lesson> lessonLijst = getLessonService().getLessons();
        request.setAttribute("lessonLijst", lessonLijst);
        return "studentLessen.jsp";
    }
}
