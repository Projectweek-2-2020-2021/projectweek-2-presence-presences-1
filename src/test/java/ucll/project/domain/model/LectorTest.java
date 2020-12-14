package ucll.project.domain.model;
import org.junit.*;

public class LectorTest {

    //Deze testen kijken of de lector kan worden aangemaakt. In de constructor wordt gebruik gemaakt van Setters.
    //deze worden dus in principe al getest in de constructor zelf.
    @Test(expected = DomainException.class)
    public void Lector_Met_Een_Lege_String_Als_Voornaam_Gooit_Een_Exception() {
        Lector l = new Lector("", "Achternaam", "u1234567");
    }

    @Test (expected = DomainException.class)
    public void Lector_Met_Een_Lege_String_Als_Achternaam_Gooit_Een_Exception() {
        Lector l = new Lector("Voornaam", "", "u1234567");
    }

    @Test (expected = DomainException.class)
    public void Lector_Met_null_Als_Voornaam_Gooit_Een_Exception() {
        Lector l = new Lector(null, "Achternaam", "u1234567");
    }

    @Test (expected = DomainException.class)
    public void Lector_Met_null_Als_Achternaam_Gooit_Een_Exception() {
        Lector l = new Lector("Voornaam", null, "u1234567");
    }

    @Test (expected = DomainException.class)
    public void Lector_Met_Een_Lege_String_Als_Lectorennummer_Gooit_Een_Exception(){
        Lector l = new Lector("Voornaam", "Achternaam", "");
    }

    @Test (expected = DomainException.class)
    public void Lector_Met_null_Als_Lectorennummer_Gooit_Een_Exception(){
        Lector l = new Lector("Voornaam", "Achternaam", null);
    }

    @Test (expected = DomainException.class)
    public void Lector_Met_Een_Lectorennummer_Startende_Met_Een_Andere_Letter_Dan_U_Gooit_Een_Exception(){
        Lector l = new Lector("Voornaam", "Achternaam", "r1234567");
    }

    @Test (expected = DomainException.class)
    public void Lector_Met_Een_Lectorennummer_Dat_Meer_Dan_8_Karakters_Bevat_Gooit_Een_Exception(){
        Lector l = new Lector("Voornaam", "Achternaam", "u12345678");
    }

    @Test
    public void Lector_Met_Correcte_Waardes_Wordt_Aangemaakt(){
        Lector l = new Lector("Voornaam", "Achternaam", "u1234567");
        Assert.assertEquals("Voornaam", l.getVoornaam());
        Assert.assertEquals("Achternaam", l.getAchternaam());
        Assert.assertEquals("u1234567", l.getLectorennummer());
    }
}