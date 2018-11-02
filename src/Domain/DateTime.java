/**
 * Made by: Roberto
 */

package Domain;

public class DateTime implements Comparable {



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
	private Integer duration;

	/**
	 * Constructor / Destructor
	 */

	public DateTime(WeekDay weekDay, Integer startHour, Integer duration) {
		setWeekday(weekDay);
		setStartHour(startHour);
		setDuration(duration);
	}

	/**
	 * Getters / Setters
	 */

	public WeekDay getWeekday() {
		return this.weekday;
	}

	public boolean setWeekday(WeekDay weekday) {
		this.weekday = weekday;
		return true;
	}

	public Integer getStartHour() {
		return this.hour;
	}

	public boolean setStartHour(Integer startHour) {
		this.hour = startHour;
		return true;
	}

	public Integer getEndHour() {
		return (this.hour + this.duration) % 24;
	}

	public boolean setEndHour(Integer endHour) {
		// Error checking
		if (endHour < this.hour) {
			return false;
		}

		this.duration = endHour - this.hour;
		return true;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public boolean setDuration(Integer duration) {
		this.duration = duration;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DateTime)) {
			return false;
		}
		DateTime d = (DateTime)obj;
		return this.weekday == d.weekday && this.hour.equals(d.hour);
	}

	@Override
	public int compareTo(Object o) {
		DateTime d = (DateTime)o;

		if (this.weekday.ordinal() != d.weekday.ordinal()) {
			return this.weekday.ordinal() - d.weekday.ordinal();
		}

		return this.hour - d.hour;
	}
}
