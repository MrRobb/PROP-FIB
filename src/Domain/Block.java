package Domain;

import java.util.ArrayList;

public class Block {

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
