package Domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

class Schedules {

	private static Schedules instance = null;
	private static Integer maxSize = 1;

	private Set<Schedule> schedules;

	private Schedules() {
		schedules = new HashSet<>(0);
	}

	public static Schedules getInstance() {
		if (instance == null) {
			instance = new Schedules();
			DomainCtrl.getInstance().updateNumberOfSchedules();
		}
		return instance;
	}

	public static int getMaxScore() {
		return Restrictions.getInstance().getMaxScore();
	}

	public boolean exists(Schedule schedule) {
		return schedules.contains(schedule);
	}

	public boolean addSchedule(Schedule schedule) {
		if (schedule == null) {
			return false;
		}
		else {
			boolean ok = schedules.add(schedule);
			if (ok) {
				DomainCtrl.getInstance().updateNumberOfSchedules();
			}
			return ok;
		}
	}

	public boolean removeSchedule(Schedule schedule) {
		if (schedule == null) {
			return false;
		}
		else {
			boolean ok = schedules.remove(schedule);
			if (ok) {
				DomainCtrl.getInstance().updateNumberOfSchedules();
			}
			return ok;
		}
	}

	public Schedule get(Integer index) {
		if (0 <= index && index < schedules.size()) {
			return (Schedule) schedules.toArray()[index];
		}
		else {
			return null;
		}
	}

	public Integer getID(Schedule subj) {
		int index = -1;
		for (Schedule schedule : schedules) {
			index++;
			if (schedule == subj) {
				return index;
			}
		}
		return index;
	}

	public Integer size() {
		return schedules.size();
	}

	public static boolean setMaxSize(Integer max) {
		if (max >= 0) {
			maxSize = max;
			return true;
		}
		return false;
	}

	public static Integer getMaxSize() {
		return maxSize;
	}

	public boolean clear() {
		schedules.clear();
		DomainCtrl.getInstance().updateNumberOfSchedules();
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

    public JSONArray toJSONArray() {
		JSONArray schedules = new JSONArray();

		for (int i = 0; i < Schedules.getInstance().size(); i++) {
			Schedule schedule_i = Schedules.getInstance().get(i);
			schedules.add(schedule_i.toJSONObject());
		}

		return schedules;
	}

	public JSONObject toJSONObject(String key) {
		JSONArray schedules_array = toJSONArray();
		JSONObject schedules_obj = new JSONObject();

		schedules_obj.put(key, schedules_array);

		return schedules_obj;
	}
}
