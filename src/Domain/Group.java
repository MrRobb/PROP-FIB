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
	private Integer parentGroupID;
	private Integer duration;
	private Integer capacity;
	private String subjectID;

	/**
	 * Constructor / Destructor
	 */

	Group(Integer id, String name, Integer duration, Integer capacity, String subjectID, Integer level, ArrayList<String> types, Integer parentGroup, String classroomID) {
		this.name = name;
		this.types = types;
		this.level = level;
		this.parentGroupID = parentGroupID;
		this.duration = duration;
		this.capacity = capacity;
		this.subjectID = subjectID;
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

	public Integer getParentGroupID() {
		return parentGroupID;
	}

	public boolean setParentGroup(Integer parentGroupID) {
		this.parentGroupID = parentGroupID;
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

    public String getSubjectID() { return subjectID; }
    public Boolean setSubjectID(String id){
	    subjectID = id;
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