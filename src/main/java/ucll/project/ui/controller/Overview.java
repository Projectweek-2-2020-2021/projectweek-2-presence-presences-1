package ucll.project.ui.controller;

import ucll.project.domain.model.Country;
import ucll.project.domain.service.CountryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {

    public Overview(String command, CountryService countryService) {
        super(command, countryService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Country mostPopular = getCountryService().getMostPopularCountry();
        request.setAttribute("popular", mostPopular);
        List<Country> countries  = getCountryService().getCountries();
        request.setAttribute("countries", countries);
        return "countryOverview.jsp";
    }
}
