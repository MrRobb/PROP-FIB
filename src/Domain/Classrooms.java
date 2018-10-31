package Domain;

import java.util.HashMap;
import java.util.Map;

public class Classrooms {

	public static Integer invalidID = -1;
	private static Classrooms instance = null;

	private HashMap<Integer, Classroom> classrooms;

	private Classrooms() {
		classrooms = new HashMap<Integer, Classroom>(0);
	}

	static Classrooms getInstance() {
		if (instance == null) {
			instance = new Classrooms();
		}

		return instance;
	}

	boolean exists(Integer id) {
		return classrooms.containsKey(id);
	}

	boolean exists(Classroom classroom) {
		return classrooms.containsValue(classroom);
	}

	Integer addClassroom(Classroom classroom) {
		if (classroom == null) {
			return invalidID;
		}
		else {
			Integer id = classrooms.size();
			classrooms.put(id, classroom);
			return id;
		}
	}

	Classroom get(Integer id) {
		return classrooms.get(id);
	}

	Integer getID(Classroom classroom) {
		for (Map.Entry<Integer, Classroom> value : classrooms.entrySet()) {
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
