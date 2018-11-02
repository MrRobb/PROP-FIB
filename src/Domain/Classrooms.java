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

	public static Classrooms getInstance() {
		if (instance == null) {
			instance = new Classrooms();
		}

		return instance;
	}

	public boolean exists(Integer id) {
		return classrooms.containsKey(id);
	}

	public boolean exists(Classroom classroom) {
		return classrooms.containsValue(classroom);
	}

	public Integer addClassroom(Classroom classroom) {
		if (classroom == null) {
			return invalidID;
		}
		else {
			Integer id = classrooms.size();
			classrooms.put(id, classroom);
			return id;
		}
	}

	public Classroom get(Integer id) {
		return classrooms.get(id);
	}

	public Integer getID(Classroom classroom) {
		for (Map.Entry<Integer, Classroom> value : classrooms.entrySet()) {
			if (value.getValue().equals(classroom)) {
				return value.getKey();
			}
		}
		return Classrooms.invalidID;
	}

	public Integer size() {
		return classrooms.size();
	}

	public boolean clear() {
		Classrooms.getInstance().classrooms.clear();
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
