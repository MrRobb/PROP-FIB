package Domain;

import java.util.ArrayList;

/**
 * @author Alberto Gimenez Aragon
 */
class Classroom {

	/**
	 * Attributes
	 */

	private String name;
	private Integer capacity;
	private ArrayList<String> extras;

	/**
	 * Constructor / Destructor
	 */

	Classroom(String name, Integer capacity) {
		this.name = name;
		this.capacity = capacity;
		extras = new ArrayList<>(0);
	}

	Classroom(String name) {
		this(name, 0);
	}

	/**
	 * Getters / Setters
	 */

	String getName() {
		return name;
	}

	Integer getCapacity() {
		return capacity;
	}

	ArrayList<String> getExtras() {
		return extras;
	}

	Boolean hasExtra(String e) {
		for (String ex : extras) {
			if (ex.equals(e)) return true;
		}
		return false;
	}

	void setName(String name) {
		this.name = name;
	}

	void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	boolean addExtra(String e) {
		if (this.hasExtra(e)) return false;
		else {
			extras.add(e);
			return true;
		}
	}
}