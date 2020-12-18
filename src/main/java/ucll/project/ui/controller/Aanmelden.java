package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Aanmelden extends RequestHandler {
    List<String> errors = new ArrayList<>();

    public Aanmelden(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        getApplicationService().reConnect();
        String gebruikersnaam = request.getParameter("gebruikersnaam");
        String wachtwoord = Utility.wachtwoordEncryptie(request.getParameter("wachtwoord"));

        if (gebruikersnaam.startsWith("u")) {
            lectorAanmelding(gebruikersnaam, wachtwoord, request);
        } else {
            studentAanmelding(gebruikersnaam, wachtwoord, request);
        }
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
        }
        return "Controller?command=Index";
    }

    private void studentAanmelding(String gebruikersnaam, String wachtwoord, HttpServletRequest request) {
        Student student;
        try {
            student = getApplicationService().getStudent(gebruikersnaam);
            if (student != null && student.isCorrectWachtwoord(wachtwoord)) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", student);
                session.setAttribute("rol", "student");
            } else errors.add("Ongeldige gebruikersnaam of wachtwoord");
        } catch (Exception e) {
            errors.add("Student bestaat niet!");
        }
    }

    private void lectorAanmelding(String gebruikersnaam, String wachtwoord, HttpServletRequest request) {
        Lector lector;
        try {
            lector = getApplicationService().getLector(gebruikersnaam);
            if (lector != null && lector.isCorrectWachtwoord(wachtwoord)) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", lector);
                session.setAttribute("rol", "lector");
            } else errors.add("Ongeldige gebruikersnaam of wachtwoord");
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
