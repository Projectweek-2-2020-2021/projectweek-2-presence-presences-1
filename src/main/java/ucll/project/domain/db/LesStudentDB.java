package ucll.project.domain.db;

public interface LesStudentDB {

    void reConnect();

    void zetAanwezigheid(String aanwezigheid, int studentId, int lesId);
}
