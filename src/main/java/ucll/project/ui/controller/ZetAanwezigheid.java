package ucll.project.ui.controller;

import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZetAanwezigheid extends RequestHandler{
    public ZetAanwezigheid(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String aanwezigheid = request.getParameter("aanwezigheid");
        String les = request.getParameter("les");
        int lesId = getApplicationService().getVakId(les);
        getApplicationService().zetAanwezigheid(aanwezigheid, 1, lesId);
        return "Controller?command=StudentLessen";
    }
}
