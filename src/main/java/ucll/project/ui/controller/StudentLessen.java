package ucll.project.ui.controller;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;
import ucll.project.domain.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class StudentLessen extends RequestHandler {

    public StudentLessen(String command, ApplicationService applicationService) {
        super(command, applicationService);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Utility.checkRoles(request, roles);

        LinkedHashMap<Date, List<Lesson>> lessenPerDag = new LinkedHashMap<>();
        List<Lesson> lessenLijst;

        int id;
        String zoekOpdracht = request.getParameter("zoekOpdracht");
        if (zoekOpdracht != null && !zoekOpdracht.isEmpty()) {
            id = getApplicationService().getStudentId(zoekOpdracht);
        } else {
            HttpSession session = request.getSession();
            Student student = (Student) session.getAttribute("loggedIn");
            id = getApplicationService().getStudentId(student.getRnummer());
        }
        List<Date> datums = getApplicationService().getAllDatumsStudent(id);
        Collections.sort(datums);

        for (Date d : datums) {
            lessenLijst = getApplicationService().getLessenVoorStudent(id, d);
            Collections.sort(lessenLijst);
            for (Lesson l : lessenLijst) {
                int lesid = getApplicationService().getVakId(l.getNaam());
                String status = getApplicationService().getstatus(id, lesid, d);
                l.setStatus(status);
             }
             lessenPerDag.put(d,lessenLijst);
        }
        request.setAttribute("lessenPerDag", lessenPerDag);



        List<List<String>> lokalenlijstperdag = new ArrayList<>();
        for (Date d: datums){
            List<Lesson> lessen = lessenPerDag.get(d);
            List<String> lokalen = new ArrayList<>();
            for (Lesson les: lessen) {
                lokalen.add(getApplicationService().getLokaal(les));
            }
            lokalenlijstperdag.add(lokalen);
        }

        request.setAttribute("lokalenlijstperdag", lokalenlijstperdag);


        List<List<String>> groeplijstperdag = new ArrayList<>();
        for (Date d: datums){
            List<Lesson> lessen = lessenPerDag.get(d);
            List<String> groepen = new ArrayList<>();
            for (Lesson les: lessen) {
                groepen.add(getApplicationService().getGroep(les));
            }
            groeplijstperdag.add(groepen);
        }

        request.setAttribute("groeplijstperdag", groeplijstperdag);


        List<List<List<Lector>>> lectorlijstperdag = new ArrayList<>();
        for (Date d: datums){
            List<Lesson> lessen = lessenPerDag.get(d);
            List<List<Lector>> lectorlijst = new ArrayList<>();
            for (Lesson les: lessen
            ) {
                List<Lector> l = getApplicationService().getLectorPerVak(getApplicationService().getVakId(les.getNaam()));
                lectorlijst.add(l);
            }
            lectorlijstperdag.add(lectorlijst);
        }

        request.setAttribute("lectorlijstperdag", lectorlijstperdag);


        return "studentLessen.jsp";
    }
}
