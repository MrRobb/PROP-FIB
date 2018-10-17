/**
 * Made by: Roberto
 */

package Domain;

public class Class {

	/**
	 * Attributes
	 */
	DateTime dateTime;
	Group group;
	Classroom classroom;

	/**
	 * Constructor / Destructor
	 */

	Class() {

	}

	Class(DateTime dateTime, Group group, Classroom classroom) {
		this.dateTime = dateTime;
		this.group = group;
		this.classroom = classroom;
	}
}
