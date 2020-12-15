package ucll.project.domain.model;

public class Lector {
    private String voornaam, achternaam, lectorennummer;

    public Lector() {}

    public Lector(String voornaam, String achternaam, String lectorennummer){
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setLectorennummer(lectorennummer);
    }

    public String getVoornaam() {
        return this.voornaam;
    }

    public String getAchternaam(){
        return this.achternaam;
    }

    public String getLectorennummer(){
        return this.lectorennummer;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.trim().isEmpty()) throw new DomainException("Geef een geldige voornaam.");
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.trim().isEmpty()) throw new DomainException("Geef een geldige achternaam.");
        this.achternaam = achternaam;
    }

    public void setLectorennummer(String lectorennummer) {
        if (lectorennummer == null || lectorennummer.trim().isEmpty()) throw new DomainException("Geef een geldig lectorennummer.");
        if (lectorennummer.toLowerCase().charAt(0) != 'u' || lectorennummer.length() != 8) throw new DomainException("Uw lectorennummer moet van het formaat 'u1234567' zijn.");
        this.lectorennummer = lectorennummer;
    }
}
