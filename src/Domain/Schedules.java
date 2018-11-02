package Domain;

import java.util.*;

public class Schedules {

	private static Schedules instance = null;

	private Set<Schedule> schedules;

	private Schedules() {
		schedules = new HashSet<>(0);
	}

	public static Schedules getInstance() {
		if (instance == null) {
			instance = new Schedules();
		}

		return instance;
	}

	public boolean exists(Schedule schedule) {
		return schedules.contains(schedule);
	}

	public boolean addSchedule(Schedule schedule) {
		if (schedule == null) {
			return false;
		}
		else {
			return schedules.add(schedule);
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

	public boolean clear() {
		schedules.clear();
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
