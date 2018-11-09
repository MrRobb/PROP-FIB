package Domain;

import javafx.scene.transform.MatrixType;
import javafx.util.Pair;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;

import java.util.*;

public class Generator {

	/**
	 * Constructor / Destructor
	 */
	private Generator() {}

	/**
	 * Consultor
	 * @return The Schedules Generated with the Groups & Classrooms & DateTimes & Restrictions
	 */
	public TreeSet<Schedule> generate() {

		LinkedHashMap<Integer, Group> groups = new LinkedHashMap<>(Groups.getInstance().get()));
		LinkedHashMap<Integer, DateTime> dates = new LinkedHashMap<>(DateTimes.getInstance().get());
		LinkedHashMap<String, Classroom> classrooms = new LinkedHashMap<>(Classrooms.getInstance().get());

		TreeSet<Schedule> schedules = new TreeSet<>();
		Schedule schedule = new Schedule(dates, classrooms);

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
			schedules.add(schedule);

			if (schedules.size() > Schedules.getMaxSize()) {
				schedules.pollLast();
			}

			return schedule.getScore() != null && schedule.getScore() >= 0;
		}

		Map.Entry<Integer, Group> entry = it.next();
		Integer id = entry.getKey();
		Group group = entry.getValue();

		for (Pair<DateTime, Classroom> slot : schedule.getAvailableSlots()) {

			Class c = new Class();
			c.setGroup(group);
			c.setDateTime(slot.getKey());
			c.setClassroom(slot.getValue());

			schedule.addClass(c);

			if (Restrictions.getInstance().Check(schedule)) {

			}

			schedule.removeClass(c);
		}
	}
}
