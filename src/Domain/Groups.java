package Domain;

import java.util.HashMap;
import java.util.Map;

public class Groups {

	public static Integer invalidID = -1;
	private static Groups instance = null;

	private HashMap<Integer, Group> groups;

	private Groups() {
		groups = new HashMap<Integer, Group>(0);
	}

	static Groups getInstance() {
		if (instance == null) {
			instance = new Groups();
		}

		return instance;
	}

	boolean exists(Integer id) {
		return groups.containsKey(id);
	}

	boolean exists(Group group) {
		return groups.containsValue(group);
	}

	Integer addGroup(Group group) {
		if (group == null) {
			return invalidID;
		}
		else {
			Integer id = groups.size();
			groups.put(id, group);
			return id;
		}
	}

	Group get(Integer id) {
		return groups.get(id);
	}

	Integer getID(Group group) {
		for (Map.Entry<Integer, Group> value : groups.entrySet()) {
			if (value.getValue().equals(group)) {
				return value.getKey();
			}
		}
		return Groups.invalidID;
	}

	Integer getNewID() {
		return groups.size();
	}

	Integer size() {
		return groups.size();
	}

	boolean clear() {
		Groups.getInstance().groups.clear();
		return true;
	}
}
