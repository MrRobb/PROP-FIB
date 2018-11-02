package Domain;

import java.util.HashMap;
import java.util.Map;

public class Classrooms {

	public static String invalidID = "";
	private static Classrooms instance = null;

	private HashMap<String, Classroom> classrooms;

	private Classrooms() {
		classrooms = new HashMap<String, Classroom>(0);
	}

	static Classrooms getInstance() {
		if (instance == null) {
			instance = new Classrooms();
		}

		return instance;
	}

	boolean exists(String id) {
		return classrooms.containsKey(id);
	}

	boolean exists(Classroom classroom) {
		return classrooms.containsValue(classroom);
	}

	String addClassroom(Classroom classroom) {
		if (classroom == null) {
			return invalidID;
		}
		else {
			String id = classroom.getName();
			classrooms.put(id, classroom);
			return id;
		}
	}

	Classroom get(String id) {
		return classrooms.get(id);
	}

	String getID(Classroom classroom) {
		for (Map.Entry<String, Classroom> value : classrooms.entrySet()) {
			if (value.getValue().equals(classroom)) {
				return value.getKey();
			}
		}
		return Classrooms.invalidID;
	}

	Integer size() {
		return classrooms.size();
	}

	boolean clear() {
		Classrooms.getInstance().classrooms.clear();
		return true;
	}
}
