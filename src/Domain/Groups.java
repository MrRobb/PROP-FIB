package Domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Groups {

	public static Integer invalidID = -1;
	private static Groups instance = null;

	private TreeMap<Integer, Group> groups;

	private Groups() {
		groups = new TreeMap<>();
	}

	public static Groups getInstance() {
		if (instance == null) {
			instance = new Groups();
		}

		return instance;
	}

	public boolean exists(Integer id) {
		return groups.containsKey(id);
	}

	public boolean exists(Group group) {
		return groups.containsValue(group);
	}

	public Integer addGroup(Group group) {
		if (group == null) {
			return invalidID;
		}
		else {
			Integer id = groups.size();
			groups.put(id, group);
			return id;
		}
	}

	public Group get(Integer id) {
		if (id.equals(invalidID)) {
			return null;
		}
		return groups.get(id);
	}

	public Integer getID(Group group) {
		for (Map.Entry<Integer, Group> value : groups.entrySet()) {
			if (value.getValue().equals(group)) {
				return value.getKey();
			}
		}
		return Groups.invalidID;
	}
	public Integer getIDfromNameAndSubject(String groupName, String subjID) {
		for (Map.Entry<Integer, Group> value : groups.entrySet()) {
			if (value.getValue().getName().equals(groupName) && value.getValue().getSubjectID().equals(subjID)) {
				return value.getKey();
			}
		}
		return Groups.invalidID;
	}

	public ArrayList<Integer> getAllKeys(){
		ArrayList<Integer>groupsKeys = new ArrayList<>();
		for (Map.Entry<Integer, Group> value : groups.entrySet()) {
			groupsKeys.add(value.getKey());
		}
		return  groupsKeys;
	}

	public TreeMap<Integer, Group> get() {
		return groups;
	}

	public Integer getNewID() {
		return groups.size();
	}

	public Integer size() {
		return groups.size();
	}

	public boolean clear() {
		Groups.getInstance().groups.clear();
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
