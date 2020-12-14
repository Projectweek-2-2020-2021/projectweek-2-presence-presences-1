package ucll.project.ui.controller;


import ucll.project.domain.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class OverviewTeacherStudents extends RequestHandler{


    public OverviewTeacherStudents(String command, LessonService lessonService) {
        super(command, lessonService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //request.setAttribute("students", studentservice.getAll());
        return "leerkrachtoverzichtstudenten.jsp";
    }
}