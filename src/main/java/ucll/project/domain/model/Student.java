package ucll.project.domain.model;

public class Student {
    private String rnummer, naam, voornaam, email, adres, telefoonNummer, wachtwoord;
    private boolean aanwezig;

    public Student() {

    }

    public Student(String rnummer, String naam, String voornaam, String email, String adres, String telefoonNummer, boolean aanwezig, String wachtwoord) {
        setRNummer(rnummer);
        setNaam(naam);
        setVoornaam(voornaam);
        setEmail(email);
        setAdres(adres);
        setTelefoonNummer(telefoonNummer);
        setAanwezig(aanwezig);
        setWachtwoord(wachtwoord);
    }

    public void setWachtwoord(String wachtwoord) {
        if (wachtwoord.isEmpty()) throw new DomainException("Wachtwoord mag niet leeg zijn");
        this.wachtwoord = wachtwoord;
    }

    public boolean isCorrectWachtwoord(String wachtwoord) {
        if (wachtwoord.isEmpty()) throw new DomainException("Wachtwoord is fout!");
        return this.wachtwoord.equals(wachtwoord);
    }

    public void setRNummer(String rnummer) {
        if (rnummer.isEmpty()) {
            throw new DomainException("r_nummer verkeerd!");
        }
        this.rnummer = rnummer;
    }

    public String getRnummer() {
        return rnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam.isEmpty()) {
            throw new DomainException("naam verkeerd!");
        }
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam.isEmpty()) {
            throw new DomainException("voornaam verkeerd!");
        }
        this.voornaam = voornaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            throw new DomainException("email verkeerd!");
        }
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        if (adres.isEmpty()) {
            throw new DomainException("adres verkeerd!");
        }
        this.adres = adres;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        if (telefoonNummer.isEmpty()) {
            throw new DomainException("telefoonNummer verkeerd!");
        }
        this.telefoonNummer = telefoonNummer;
    }

    public boolean isAanwezig() {
        return aanwezig;
    }

    public void setAanwezig(boolean aanwezig) {
        this.aanwezig = aanwezig;
    }

    @Override
    public String toString() {
        String t = "r-nummer: " + rnummer;
        t += "\nDeze student noemt: " + naam + " " + voornaam;
        t += "\nEmail: " + email;
        t += "\nAdres: " + adres;
        t += "\nGsm: " + telefoonNummer;
        return t;
    }
}
