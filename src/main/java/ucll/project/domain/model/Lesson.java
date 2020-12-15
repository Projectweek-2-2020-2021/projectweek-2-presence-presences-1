package ucll.project.domain.model;

import java.util.ArrayList;

public class Lesson implements Comparable<Lesson> {

	ArrayList<String> richtingen = new ArrayList<>();
	private String naam, studierichting;
	private int studiepunten;

	public Lesson() {
	}

	public Lesson(String naam, int studiepunten, String studierichting) {
		setNaam(naam);
		setStudiepunten(studiepunten);

		richtingen.add("TI");
		richtingen.add("OM");
		richtingen.add("BM");
		setStudierichting(studierichting);
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
