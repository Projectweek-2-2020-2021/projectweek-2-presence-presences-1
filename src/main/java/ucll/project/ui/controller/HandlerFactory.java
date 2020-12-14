package ucll.project.ui.controller;

import ucll.project.domain.service.LessonService;

public class HandlerFactory {

    public HandlerFactory(){

    }

    public RequestHandler getHandler(String command, LessonService lessonService) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ucll.project.ui.controller." + command);
            Object handlerObject = handlerClass.getConstructor(String.class, LessonService.class).newInstance(command, lessonService);
            handler = (RequestHandler) handlerObject;
        } catch (ClassNotFoundException e){
            throw new ControllerException(e.getMessage());
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
        return handler;
    }
}
