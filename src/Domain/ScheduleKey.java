package Domain;

import javafx.util.Pair;

import java.util.Objects;

class ScheduleKey extends Pair<Integer, String> implements Comparable<ScheduleKey> {

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

		if (!Objects.equals(this.getValue(), other.getValue())) {
			return this.getValue().compareTo(other.getValue());
		}

		return this.getKey().compareTo(other.getKey());
	}

	/*
	@Override
	public int compareTo(ScheduleKey other) {

		if (!this.getValue().equals(other.getValue())) {
			return this.getValue().compareTo(other.getValue());
		}

		return this.getKey().compareTo(other.getKey());
	}
	*/
}
