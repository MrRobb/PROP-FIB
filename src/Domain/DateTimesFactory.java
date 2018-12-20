package Domain;

class DateTimesFactory {

	public static boolean produce() {

		for (DateTime d = DateTimes.getInstance().firstPossible(); d != null; d = DateTimes.getInstance().next(d)) {
			DateTimes.getInstance().addDateTime(d);
		}

		return true;
	}

}
