package Domain;

/**
 * @author Roberto Ariosa Hernandez
 */
public class Class {

	/**
	 * Attributes
	 */
	private Integer dateTimeID;
	private Integer groupID;
	private Integer classroomID;

	/**
	 * Constructor
	 */
	public Class() {
		this.dateTimeID = DateTimes.invalidID;
		this.groupID = Groups.invalidID;
		this.classroomID = Classrooms.invalidID;
	}

	/**
	 * Checks that the date, group and classroom exist.
	 * @return Is true if they exist.
	 */
	public boolean isOK() {

		return DateTimes.getInstance().exists(dateTimeID) &&
				Groups.getInstance().exists(groupID) &&
				Classrooms.getInstance().exists(classroomID);
	}

	// DATETIME

	/**
	 * Getter of the ID of DateTime assigned.
	 * @return The ID of the DateTime assigned.
	 */
	public Integer getDateTimeID() {
		return dateTimeID;
	}

	/**
	 * Sets the DateTime given an ID.
	 * @param dateTimeID The ID of the DateTime that you want to set.
	 * @return true if it was set correctly, false if the ID does not exist and it is not invalidID.
	 */
	public boolean setDateTimeID(Integer dateTimeID) {

		if (DateTimes.getInstance().exists(dateTimeID) || dateTimeID.equals(DateTimes.invalidID)) {
			this.dateTimeID = dateTimeID;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Getter of the DateTime assigned.
	 * @return The DateTime assigned, null if no DateTime was set.
	 */
	public DateTime getDateTime() {
		return DateTimes.getInstance().get(dateTimeID);
	}

	/**
	 * Sets the DateTime given.
	 * @param datetime The DateTime that you want to set.
	 * @return true if it was set correctly, false if the DateTime does not exist and it is not null.
	 */
	public boolean setDateTime(DateTime datetime) {
		if (datetime == null) {
			dateTimeID = DateTimes.invalidID;
			return true;
		}
		else if (!DateTimes.getInstance().exists(datetime)) {
			return false;
		}
		else {
			dateTimeID = DateTimes.getInstance().getID(datetime);
			return true;
		}
	}

	// GROUP

	/**
	 * Getter of the ID of Group assigned.
	 * @return The ID of the Group.
	 */
	public Integer getGroupID() {
		return groupID;
	}

	/**
	 * Sets the Group given an ID.
	 * @param groupID The ID of the Group that you want to set.
	 * @return true if it was set correctly, false if the ID does not exist and it is not invalidID.
	 */
	public boolean setGroupID(Integer groupID) {

		if (Groups.getInstance().exists(groupID) || groupID.equals(Groups.invalidID)) {
			this.groupID = groupID;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Getter of the Group assigned.
	 * @return The Group assigned, null if no Group was set.
	 */
	public Group getGroup() {
		return Groups.getInstance().get(groupID);
	}

	/**
	 * Sets the Group given.
	 * @param group The Group that you want to set.
	 * @return true if it was set correctly, false if the Group does not exist and it is not null.
	 */
	public boolean setGroup(Group group) {
		if (group == null) {
			groupID = Groups.invalidID;
			return true;
		}
		else if (!Groups.getInstance().exists(group)) {
			return false;
		}
		else {
			groupID = Groups.getInstance().getID(group);
			return true;
		}
	}

	// CLASSROOM

	/**
	 * Getter of the ID of Classroom assigned.
	 * @return The ID of the Classroom.
	 */
	public Integer getClassroomID() {
		return classroomID;
	}

	/**
	 * Sets the Classroom given an ID.
	 * @param classroomID The ID of the Classroom that you want to set.
	 * @return true if it was set correctly, false if the ID does not exist and it is not invalidID.
	 */
	public boolean setClassroomID(Integer classroomID) {

		if (Classrooms.getInstance().exists(classroomID) || classroomID.equals(Classrooms.invalidID)) {
			this.classroomID = classroomID;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Getter of the Classroom assigned.
	 * @return The Classroom assigned, null if no Classroom was set.
	 */
	public Classroom getClassroom() {
		return Classrooms.getInstance().get(classroomID);
	}

	/**
	 * Sets the Classroom given.
	 * @param classroom The Classroom that you want to set.
	 * @return true if it was set correctly, false if the Classroom does not exist and it is not null.
	 */
	public boolean setClassroom(Classroom classroom) {
		if (classroom == null) {
			classroomID = Classrooms.invalidID;
			return true;
		}
		else if (!Classrooms.getInstance().exists(classroom)) {
			return false;
		}
		else {
			classroomID = Classrooms.getInstance().getID(classroom);
			return true;
		}
	}

}
