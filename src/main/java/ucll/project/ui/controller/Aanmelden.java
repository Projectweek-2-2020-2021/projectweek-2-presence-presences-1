package ucll.project.ui.controller;

import jdk.internal.vm.compiler.collections.EconomicMap;
import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Aanmelden extends RequestHandler {
    public Aanmelden(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        List<String> errors = new ArrayList<>();

        String gebruikersnaam = request.getParameter("gebruikersnaam");
        String wachtwoord = request.getParameter("wachtwoord");

        if (gebruikersnaam.startsWith("r")) {
            Student student = new Student();
            try {
                student = getApplicationService().getStudent(gebruikersnaam);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
            try {
                if (errors.isEmpty() && student.isCorrectWachtwoord(wachtwoord)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedIn", student);
                }
                else errors.add("Ongeldige gebruikersnaam of wachtwoord");
            }
            catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
//        else {
//            Lector lector = new Lector();
//            try {
//                lector = getApplicationService().getLector(gebruikersnaam);
//            } catch (Exception e) {
//                errors.add(e.getMessage());
//            }
//            try {
//                if (errors.isEmpty() && student.isCorrectWachtwoord(wachtwoord)) {
//                    HttpSession session = request.getSession();
//                    session.setAttribute("loggedIn", student);
//                }
//                else errors.add("Ongeldige gebruikersnaam of wachtwoord");
//            }
//            catch (Exception e) {
//                errors.add(e.getMessage());
//            }
//        }


        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
        }
        return "Controller?command=Index";
    }
}
