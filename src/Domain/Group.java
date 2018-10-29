/**
 * Made by: Roberto
 */

package Domain;

import java.util.ArrayList;

public class Group {

	/**
	 * Attributes
	 */
	private String name;
	private ArrayList<String> types;
	private Integer level;
	private Group parentGroup;
	private Integer duration;
	private Integer capacity;

	/**
	 * Constructor / Destructor
	 */

	Group(Integer id, String name, Integer duration) {
		this.name = name;
		this.types = new ArrayList<String>(0);
		this.level = 0;
		this.parentGroup = null;
		this.duration = duration;
		this.capacity = 0;
	}

	/**
	 * Getters / Setters
	 */

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public boolean setTypes(ArrayList<String> types) {
		this.types = types;
		return true;
	}

	public Integer getLevel() {
		return level;
	}

	public boolean setLevel(Integer level) {
		this.level = level;
		return true;
	}

	public Group getParentGroup() {
		return parentGroup;
	}

	public boolean setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
		return true;
	}

	public Integer getDuration() {
		return duration;
	}

	public boolean setDuration(Integer duration) {
		this.duration = duration;
		return true;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public boolean setCapacity(Integer capacity) {
		this.capacity = capacity;
		return true;
	}

	/**
	 * Consultors
	 */

	public boolean hasType(String type) {
		for (String t : this.types) {
			if (t.equals(type)) {
				return true;
			}
		}
		return false;
	}

	public boolean addType(String type) {
		this.types.add(type);
		return true;
	}

	public boolean eraseType(String type) {
		this.types.remove(type);
		return true;
	}
}