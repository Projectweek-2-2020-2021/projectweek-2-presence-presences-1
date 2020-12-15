package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicatieService;

public class HandlerFactory {

    public HandlerFactory(){

    }

    public RequestHandler getHandler(String command, ApplicatieService applicatieService) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ucll.project.ui.controller." + command);
            Object handlerObject = handlerClass.getConstructor(String.class, ApplicatieService.class).newInstance(command, applicatieService);
            handler = (RequestHandler) handlerObject;
        } catch (ClassNotFoundException e) {
            throw new ControllerException(e.getMessage());
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
        return handler;
    }
}
