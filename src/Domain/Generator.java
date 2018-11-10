package Domain;

import javafx.util.Pair;
import java.util.*;

public class Generator {

	private static Generator instance = null;

	/**
	 * Constructor / Destructor
	 */
	private Generator() {}

	public static Generator getInstance() {
		if (instance == null) {
			instance = new Generator();
		}

		return instance;
	}

	/**
	 * Consultor
	 * @return The Schedules Generated with the Groups & Classrooms & DateTimes & Restrictions
	 */
	public TreeSet<Schedule> generate() {

		LinkedHashMap<Integer, Group> groups = new LinkedHashMap<>(Groups.getInstance().get());
		LinkedHashMap<Integer, DateTime> dates = new LinkedHashMap<>(DateTimes.getInstance().get());
		LinkedHashMap<String, Classroom> classrooms = new LinkedHashMap<>(Classrooms.getInstance().get());

		TreeSet<Schedule> schedules = new TreeSet<>();
		Schedule schedule = new Schedule(dates.keySet(), classrooms.keySet());

		if (generateSchedules(schedules, schedule, groups, groups.entrySet().iterator())) {
			return schedules;
		}
		else {
			return new TreeSet<>();
		}
	}

	/*
	 * Helpers
	 */
	private boolean generateSchedules(TreeSet<Schedule> schedules, Schedule schedule, LinkedHashMap<Integer, Group> groups, Iterator<Map.Entry<Integer, Group>> it) {

		if (!it.hasNext()) {
			schedules.add(new Schedule(schedule));

			if (schedules.size() > Schedules.getMaxSize()) {
				schedules.pollLast();
			}

			return schedule.getScore() != null && schedule.getScore() >= 0;
		}

		Map.Entry<Integer, Group> entry = it.next();
		Integer id = entry.getKey();
		Group group = entry.getValue();
		boolean anySolution = false;

		for (ScheduleKey slot : schedule.getAvailableSlots()) {

			Class c = new Class();
			c.setGroup(group);
			c.setDateTimeID(slot.getKey());
			c.setClassroomID(slot.getValue());

			schedule.addClass(c);

			if (Restrictions.getInstance().Check(schedule)) {
				anySolution = anySolution | generateSchedules(schedules, schedule, groups, it);
			}

			schedule.removeClass(c);
		}

		return anySolution;
	}
}
