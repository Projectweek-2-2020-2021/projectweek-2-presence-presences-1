package ucll.project.domain.model;

public class Lesson implements Comparable<Lesson>{

	private String name;

	public Lesson() {
	}

	public Lesson(String name) {
		setName(name);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new DomainException("Name may not be empty!");
		}
		this.name = name;
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		boolean equal = false;
		if (object instanceof Lesson) {
			Lesson other = (Lesson) object;
			equal = this.getName().equals(other.getName());
		}
		return equal;
	}

	@Override
	public String toString() {
		return "Deze les noemt: " + name;
	}

	@Override
	public int compareTo(Lesson o) {
		return this.name.compareTo(o.getName());
	}
}
