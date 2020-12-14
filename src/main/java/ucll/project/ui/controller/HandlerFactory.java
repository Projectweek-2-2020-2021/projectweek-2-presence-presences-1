package ucll.project.ui.controller;

import ucll.project.domain.service.CountryService;

public class HandlerFactory {

    public HandlerFactory(){

    }

    public RequestHandler getHandler(String command, CountryService countryService) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ucll.project.ui.controller." + command);
            Object handlerObject = handlerClass.getConstructor(String.class, CountryService.class).newInstance(command, countryService);
            handler = (RequestHandler) handlerObject;
        } catch (ClassNotFoundException e){
            throw new ControllerException(e.getMessage());
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
        return handler;
    }
}
