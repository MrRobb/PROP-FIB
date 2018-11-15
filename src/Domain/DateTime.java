/**
 * Made by: Roberto
 */

package Domain;

public class DateTime implements Comparable<DateTime> {



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

	public DateTime(WeekDay weekDay, Integer startHour) {
		setWeekday(weekDay);
		setStartHour(startHour);
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

	public Integer getEndHour(int duration) {
		return (this.hour + duration) % 24;
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
	public int compareTo(DateTime other) {

		if (this.weekday.ordinal() != other.weekday.ordinal()) {
			return this.weekday.ordinal() - other.weekday.ordinal();
		}

		return this.hour - other.hour;
	}

	@Override
	public String toString() {
		StringBuilder d = new StringBuilder();

		d.append(getWeekday());
		d.append(" ");
		d.append(getStartHour());
		d.append(":00");

		return d.toString();
	}

	public String toString(int duration) {
		StringBuilder d = new StringBuilder();

		d.append(getWeekday());
		d.append(" ");
		d.append(getStartHour());
		d.append(":00" + " - ");
		d.append(getEndHour(duration));
		d.append(":00");

		return d.toString();
	}
}
