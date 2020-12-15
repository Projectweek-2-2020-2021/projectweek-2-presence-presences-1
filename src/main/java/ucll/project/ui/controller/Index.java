package ucll.project.ui.controller;

import ucll.project.domain.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends RequestHandler {

    public Index(String command, LessonService lessonService) {
        super(command, lessonService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }
}
