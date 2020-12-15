package ucll.project.ui.controller;


import ucll.project.domain.model.Rol;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class LectorOverzichtStudenten extends RequestHandler {


    public LectorOverzichtStudenten(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Rol[] roles = new Rol[]{Rol.LECTOR};
        Utility.checkRoles(request, roles);
        String vak = request.getParameter("vaknaam");
        int id = getApplicationService().getVakId(vak);
        List<Student> students;
        students = getApplicationService().getStudentPerVak(id);
        request.setAttribute("studentspervak", students);

        return "lectorOverzichtStudenten.jsp";
    }
}