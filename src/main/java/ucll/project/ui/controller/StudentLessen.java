package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class StudentLessen extends RequestHandler {

    public StudentLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Lesson> lessenLijst = getApplicationService().getLessons();
        request.setAttribute("lessenLijst", lessenLijst);

        ArrayList<String> datums = new ArrayList<>();
        datums.add("1/1/2020");
        datums.add("2/2/2020");
        datums.add("3/3/2020");
        datums.add("4/4/2020");
        request.setAttribute("datums", datums);

        List<Lector> lectorlijst = new ArrayList<>();

        return "studentLessen.jsp";
    }
}
