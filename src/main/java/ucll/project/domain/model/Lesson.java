package ucll.project.domain.model;

import java.util.ArrayList;

public class Lesson implements Comparable<Lesson> {

	ArrayList<String> richtingen = new ArrayList<>();
	private String naam, studierichting, tijd, status;
	private int studiepunten, lesduur;

	public Lesson() {
	}

	public Lesson(String naam, int studiepunten, String studierichting, String tijd, int lesduur) {
		setNaam(naam);
		setStudiepunten(studiepunten);

		richtingen.add("TI");
		richtingen.add("OM");
		richtingen.add("BM");
		setStudierichting(studierichting);
		setTijd(tijd);
		setLesduur(lesduur);

	}

	public String getStudierichting() {
		return studierichting;
	}

	public void setStudierichting(String studierichting) {
		if (studierichting.isEmpty()) throw new DomainException("Studierichting is leeg!");
		if (!richtingen.contains(studierichting)) {
			throw new DomainException("Studierichting bestaat niet!");
		}
		this.studierichting = studierichting;
	}

	public int getStudiepunten() {
		return studiepunten;
	}

	public void setStudiepunten(int studiepunten) {
		if (studiepunten < 0) {
			throw new DomainException("Studiepunten zijn onder de 0!");
		}
		this.studiepunten = studiepunten;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.isEmpty()) {
			throw new DomainException("Name may not be empty!");
		}
		this.naam = naam;
	}

	public void setTijd(String tijd) {
		if (naam == null || naam.isEmpty()) {
			throw new DomainException("Name may not be empty!");
		}
		this.tijd = tijd;
	}

	public String getTijd() {
		return tijd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLesduur() {
		return lesduur;
	}

	public void setLesduur(int lesduur) {
		this.lesduur = lesduur;
	}

	public String getEindTijd(){
		int uur =  Integer.parseInt(this.getTijd().substring(0, 2));
		int min =  Integer.parseInt(this.getTijd().substring(3, 5));
		uur += getLesduur()/60;
		min += getLesduur()%60;
		return uur + ":" + min;
	}

	@Override
	public int hashCode() {
		return getNaam().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		boolean equal = false;
		if (object instanceof Lesson) {
			Lesson other = (Lesson) object;
			equal = this.getNaam().equals(other.getNaam());
		}
		return equal;
	}

	@Override
	public String toString() {
		return "Deze les noemt: " + naam;
	}

	@Override
	public int compareTo(Lesson o) {
		return this.naam.compareTo(o.getNaam());
	}
}
