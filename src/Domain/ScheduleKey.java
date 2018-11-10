package Domain;

import javafx.util.Pair;

public class ScheduleKey extends Pair<Integer, String> implements Comparable<ScheduleKey> {

	/**
	 * Creates a new pair
	 *
	 * @param key   The key for this pair
	 * @param value The value to use for this pair
	 */
	public ScheduleKey(Integer key, String value) {
		super(key, value);
	}

	@Override
	public int compareTo(ScheduleKey other) {
		if (!this.getKey().equals(other.getKey())) {
			return this.getKey().compareTo(other.getKey());
		}

		return this.getValue().compareTo(other.getValue());
	}
}
