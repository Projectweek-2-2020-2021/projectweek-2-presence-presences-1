package ucll.project.ui.controller;


import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

class LectorOverviewStudents extends RequestHandler{


    public LectorOverviewStudents(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String vak = request.getParameter("vaknaam");
        int id = getApplicationService().getVakId(vak);
        List<Student> aanwezig = getApplicationService().getAllAanwezigheid(id);
        List<Student> afwezig = getApplicationService().getAllNietAanwezigheid(id);
        request.setAttribute("les", vak);
        request.setAttribute("aanwezig", aanwezig);
        request.setAttribute("afwezig", afwezig);

        return "lectorOverzichtStudenten.jsp";
    }
}