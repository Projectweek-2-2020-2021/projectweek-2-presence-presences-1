package ucll.project.domain.model;

import java.util.Date;

public class LesStudent implements Comparable<LesStudent>{
    private Date datum;
    private String lesNaam, rnummer, klaslokaal, status, opmerking;

    public LesStudent(String lesNaam, String rnummer, Date datum, String klaslokaal, String opmerking, String status) {
        setLesNaam(lesNaam);
        setrnummer(rnummer);
        setDatum(datum);
        setKlaslokaal(klaslokaal);
        setOpmerking(opmerking);
        setStatus(status);
    }

    public String getrnummer() {
        return rnummer;
    }

    public void setrnummer(String rnummer) {
        this.rnummer = rnummer;
    }

    public Date getDatum() {
        return datum;
    }

    public String getKlaslokaal() {
        return klaslokaal;
    }

    public String getLesNaam() {
        return lesNaam;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public String getStatus() {
        return status;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setKlaslokaal(String klaslokaal) {
        this.klaslokaal = klaslokaal;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public void setLesNaam(String lesNaam) {
        this.lesNaam = lesNaam;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(LesStudent o) {
        return this.datum.compareTo(o.getDatum());
    }
}
