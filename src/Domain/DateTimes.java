package Domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class DateTimes {

	public static Integer invalidID = -1;
	private static DateTimes instance = null;

	private LinkedHashMap<Integer, DateTime> datetimes;

	private DateTimes() {
		datetimes = new LinkedHashMap<>(0);
	}

	public static DateTimes getInstance() {
		if (instance == null) {
			instance = new DateTimes();
		}

		return instance;
	}

	public boolean exists(Integer id) {
		return datetimes.containsKey(id);
	}

	public boolean exists(DateTime datetime) {
		return datetimes.containsValue(datetime);
	}

	public Integer addDateTime(DateTime datetime) {
		if (datetime == null) {
			return invalidID;
		}
		else {
			Integer id = datetimes.size();
			datetimes.put(id, datetime);
			return id;
		}
	}

	public DateTime get(Integer id) {
		return datetimes.get(id);
	}

	public Integer getID(DateTime datetime) {
		for (Map.Entry<Integer, DateTime> value : datetimes.entrySet()) {
			if (value.getValue().equals(datetime)) {
				return value.getKey();
			}
		}
		return DateTimes.invalidID;
	}

	public LinkedHashMap<Integer, DateTime> get() {
		return datetimes;
	}

	public Integer size() {
		return datetimes.size();
	}

	public boolean clear() {
		DateTimes.getInstance().datetimes.clear();
		return true;
	}

	public DateTime first() {
		return datetimes.get(0);
	}

	public DateTime firstPossible() {
		return new DateTime(DateTime.WeekDay.Monday, 0);
	}

	public DateTime next(DateTime datetime) {

		DateTime.WeekDay weekday = datetime.getWeekday();
		Integer hour = datetime.getStartHour();
		DateTime nextValue = new DateTime(weekday, hour);

		if (nextValue.equals(lastPossible())) {
			return null;
		}
		else {
			if (hour == 23) {
				nextValue.setWeekday(DateTime.WeekDay.values()[weekday.ordinal() + 1]);
			}
			nextValue.setStartHour((++hour) % 24);
		}

		return nextValue;
	}

	public DateTime last() {
		Integer lastIndex = datetimes.size() -1;
		return datetimes.get(lastIndex);
	}

	public DateTime lastPossible() {
		return new DateTime(DateTime.WeekDay.Friday, 23);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
