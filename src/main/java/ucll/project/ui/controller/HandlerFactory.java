package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

public class HandlerFactory {

    public HandlerFactory(){

    }

    public RequestHandler getHandler(String command, ApplicationService applicationService) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ucll.project.ui.controller." + command);
            Object handlerObject = handlerClass.getConstructor(String.class, ApplicationService.class).newInstance(command, applicationService);
            handler = (RequestHandler) handlerObject;
        } catch (ClassNotFoundException e) {
            throw new ControllerException(e.getMessage());
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
        return handler;
    }
}
