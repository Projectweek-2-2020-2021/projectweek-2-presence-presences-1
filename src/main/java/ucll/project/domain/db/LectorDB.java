package ucll.project.domain.db;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.util.List;

public interface LectorDB {
    Lector getLector(String unummer);

    List<Lector> getAllLectors();

    List<Lector> getLectorPerVak(int lectorid);

    public int getLectorId(String lectornaam);
}
