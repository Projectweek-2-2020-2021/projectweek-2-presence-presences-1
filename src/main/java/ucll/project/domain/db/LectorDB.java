package ucll.project.domain.db;

import ucll.project.domain.model.Lector;

import java.util.List;

public interface LectorDB {
    Lector getLector(String unummer);

    List<Lector> getAllLectors();

    List<Lector> getLectorPerVak(int lectorid);

    int getLectorId(String lectornaam);

    void reConnect();
}
