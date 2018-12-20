package Domain;

import java.util.*;

class Generator {

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
			generateSchedules(schedules, schedule, groups.entrySet().iterator());
			return schedules;
		}
		catch (Exception e) {
			System.out.println("Exception in restrictions");
			return new TreeSet<>();
		}
	}

	/*
	 * Helpers
	 */
	private Integer generateSchedules(TreeSet<Schedule> schedules, Schedule schedule, Iterator<Map.Entry<Integer, Group>> it) {

		if (!it.hasNext()) {
			Schedule saveMe = new Schedule(schedule);
			schedules.add(saveMe);
			Schedules.getInstance().addSchedule(saveMe);

			if (schedules.size() > Schedules.getMaxSize()) {
				schedules.pollLast();
				Schedules.getInstance().removeSchedule(saveMe);
			}

			if (schedules.size() == Schedules.getMaxSize() && schedules.last().getScore() == Schedules.getMaxScore()) {
				return null;
			}

			return schedule.getScore();
		}

		Map.Entry<Integer, Group> entry = it.next();
		Group group = entry.getValue();

		System.out.println(schedule.getAvailableSlots(group.getDuration()).size());

		for (ScheduleKey slot : schedule.getAvailableSlots(group.getDuration())) {

			Class c = new Class();
			c.setGroup(group);
			c.setDateTimeID(slot.getKey());
			c.setClassroomID(slot.getValue());

			schedule.addClass(c);

			if (Restrictions.getInstance().Check(schedule)) {

				Integer score = generateSchedules(schedules, schedule, it);

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
