package ucll.project.domain.model;

public enum Rol {
    STUDENT("student"), LECTOR("lector"), STC("stc"), STUDENTADMIN("studentadmin");

    private final String stringValue;

    Rol(String stringValue) {
        this.stringValue = stringValue;
    }


    public String getStringValue() {
        return stringValue;
    }


}
