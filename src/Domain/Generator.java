package Domain;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
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

		TreeMap<Integer, Group> groups = new TreeMap<>(Groups.getInstance().get());
		TreeMap<Integer, DateTime> dates = new TreeMap<>(DateTimes.getInstance().get());
		TreeMap<String, Classroom> classrooms = new TreeMap<>(Classrooms.getInstance().get());

		TreeSet<Schedule> schedules = new TreeSet<>();
		Schedule schedule = new Schedule(dates.keySet(), classrooms.keySet());

		try {
			generateSchedules(schedules, schedule, groups, groups.entrySet().iterator());
			return schedules;
		}
		catch (Exception e) {
			return new TreeSet<>();
		}
	}

	/*
	 * Helpers
	 */
	private Integer generateSchedules(TreeSet<Schedule> schedules, Schedule schedule, TreeMap<Integer, Group> groups, Iterator<Map.Entry<Integer, Group>> it) {

		if (!it.hasNext()) {
			schedules.add(new Schedule(schedule));

			if (schedules.size() > Schedules.getMaxSize()) {
				schedules.pollLast();
			}

			if (schedules.size() == Schedules.getMaxSize() && schedules.last().getScore() == Schedules.getMaxScore()) {
				return null;
			}

			return schedule.getScore();
		}

		Map.Entry<Integer, Group> entry = it.next();
		Integer id = entry.getKey();
		Group group = entry.getValue();

		for (ScheduleKey slot : schedule.getAvailableSlots()) {

			Class c = new Class();
			c.setGroup(group);
			c.setDateTimeID(slot.getKey());
			c.setClassroomID(slot.getValue());

			schedule.addClass(c);

			if (Restrictions.getInstance().Check(schedule)) {

				Integer score = generateSchedules(schedules, schedule, groups, it);

				if (score == null) {
					// We have filled all of the schedules necessary
					return schedules.first() != null ? schedules.first().getScore() : Integer.MIN_VALUE;
				}
			}

			schedule.removeClass(c);
		}

		return schedules.size() > 0 ? schedules.first().getScore() : Integer.MIN_VALUE;
	}
}
