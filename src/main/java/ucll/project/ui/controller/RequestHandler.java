package ucll.project.ui.controller;

import ucll.project.domain.service.LessonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {
    private LessonService lessonService;

    public RequestHandler(String command, LessonService lessonService){
        setCountryService(lessonService);
    }

    private void setCountryService(LessonService lessonService){
        if (lessonService == null){
            throw new ControllerException("Country service cannot be null.");
        }
        this.lessonService = lessonService;
    }

    public LessonService getCountryService(){ return lessonService; }

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    void forwardRequest(String destination, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }


}
