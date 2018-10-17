package Domain;

/**
 * Made by: Roberto
 */

public class DateTime {

	/**
	 * Data Structures
	 */

	enum WeekDay {
		Monday,
		Tuesday,
		Wednesday,
		Thursday,
		Friday
	}

	/**
	 * Attributes
	 */

	private WeekDay weekday;
	private Integer hour;

	/**
	 * Constructor / Destructor
	 */

	DateTime(WeekDay weekDay, Integer hour) {
		setWeekday(weekDay);
		setHour(hour);
	}

	/**
	 * Getters / Setters
	 */

	public WeekDay getWeekday() {
		return weekday;
	}

	public boolean setWeekday(WeekDay weekday) {
		this.weekday = weekday;
		return true;
	}

	public Integer getHour() {
		return hour;
	}

	public boolean setHour(Integer hour) {
		this.hour = hour;
		return true;
	}
}
