package Domain;

import java.util.ArrayList;

/**
 * @author Alberto Gimenez Aragon
 */
class Classroom implements Comparable<Classroom> {

	/**
	 * Attributes
	 */

	private String name;
	private Integer capacity;
	private ArrayList<String> extras;

	/**
	 * Constructor / Destructor
	 */

	public Classroom(String name, Integer capacity) {
		this.name = name;
		this.capacity = capacity;
		extras = new ArrayList<>(0);
	}

	public Classroom(String name, Integer capacity, ArrayList<String> extras) {
		this.name = name;
		this.capacity = capacity;
		this.extras = extras;
	}

	public Classroom(String name) {
		this(name, 0);
	}

	/**
	 * Getters / Setters
	 */

	public String getName() {
		return name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public ArrayList<String> getExtras() {
		return extras;
	}

	public Boolean hasExtra(String e) {
		for (String ex : extras) {
			if (ex.equals(e)) return true;
		}
		return false;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public boolean addExtra(String e) {
		if (this.hasExtra(e)) return false;
		else {
			extras.add(e);
			return true;
		}
	}

	@Override
	public int compareTo(Classroom other) {
		return this.getName().compareTo(other.getName());
	}

	@Override
	public String toString() {
		return getName();
	}
}