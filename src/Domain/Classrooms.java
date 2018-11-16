package Domain;

import java.util.*;

public class Classrooms {

	public static String invalidID = "";
	private static Classrooms instance = null;

	private TreeMap<String, Classroom> classrooms;

	private Classrooms() {
		classrooms = new TreeMap<>();
	}

	public static Classrooms getInstance() {
		if (instance == null) {
			instance = new Classrooms();
		}

		return instance;
	}

	boolean exists(String id) {
		return classrooms.containsKey(id);
	}

	public boolean exists(Classroom classroom) {
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

	public ArrayList<String> getAllKeys(){
		ArrayList<String>classroomsKeys = new ArrayList<>();
		for (Map.Entry<String, Classroom> value : classrooms.entrySet()) {
			classroomsKeys.add(value.getKey());
		}
		return  classroomsKeys;
	}

	TreeMap<String, Classroom> get() {
		return classrooms;
	}

	public TreeSet<String> getExtras() {

		TreeSet<String> extras = new TreeSet<>();

		for (Classroom classroom : classrooms.values()) {
			extras.addAll(classroom.getExtras());
		}

		return extras;
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
