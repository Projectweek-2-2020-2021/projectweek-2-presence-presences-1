package ucll.project.ui.controller;

import ucll.project.domain.service.CountryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {
    private CountryService countryService;

    public RequestHandler(String command, CountryService countryService){
        setCountryService(countryService);
    }

    private void setCountryService(CountryService countryService){
        if (countryService == null){
            throw new ControllerException("Country service cannot be null.");
        }
        this.countryService = countryService;
    }

    public CountryService getCountryService(){ return countryService; }

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    void forwardRequest(String destination, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }


}
