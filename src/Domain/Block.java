package Domain;

import java.util.ArrayList;

public class Block {

	/**
	 * Checks that there is no class on week day Day between startHour and endHour.
	 * @param input [0] The schedule we want to check.
	 *              [1] The day we want to restrict classes
	 *              [2] The start hour of the interval with no classes
	 *              [3] The end hour of the interval with no classes
	 * @return true if no classes on this day between [startHour, endHour], false otherwise.
	 */
	public Out noClassOnDayBetweenSHEH(In input) {

		// Casting args
		Schedule s = (Schedule) input.get(0);
		String day = (String) input.get(1);
		Integer startHour = (Integer) input.get(2);
		Integer endHour = (Integer) input.get(3);

		ArrayList<Class> classes = s.getClasses();
		for (Class c : classes) {
			DateTime cDT = c.getDateTime();
			if (cDT != null) {
				if (cDT.getWeekday().toString().equals(day)) {
					Integer sh = cDT.getStartHour();
					Integer d = cDT.getDuration();
					if ((sh >= startHour && sh < endHour) ||
							(sh + d >= startHour && sh + d < endHour) ||
							(sh < startHour && sh + d > endHour)) {
						return new Out(Boolean.FALSE);
					}
				}
			}
		}

		return new Out(Boolean.TRUE);
	}

}
