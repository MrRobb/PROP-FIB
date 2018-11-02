package Domain;

import java.util.ArrayList;

public class Block {

    /**
     * Checks that there is no class on week day Day between startHour and endHour.
     * @param input in[0] The schedule we want to check.
                     in[1] The day we want to restrict classes
                     in[2] The start hour of the interval with no classes
                     in[3] The end hour of the interval with no classes
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

    /**
     * Checks that there are no classes overlapped at the same classroom.
     * @param input in[0] The schedule we want to check.
     * @return true if no classes overlapped at the same classroom, false otherwise.
     */

    public Out noTwoClassesAtSameHourSameClassroom(In input){
        Schedule s = (Schedule) input.get(0);
        ArrayList<Class> classes = s.getClasses();
        for(int i = 0; i<classes.size(); ++i){
            Class c = classes.get(i);
            DateTime cDT = c.getDateTime();
            Classroom cClassroom = c.getClassroom();
            // if class c has a classroom and date assigned
            if(cDT != null && cClassroom != null){
                String wday = cDT.getWeekday().toString();
                Integer shour = cDT.getStartHour();
                Integer duration = cDT.getDuration();
                String classroom = cClassroom.getName();
                // inspect every other class cj
                for(int j=0; j<classes.size(); ++j){
                    Class cj = classes.get(j);
                    DateTime cjDT = cj.getDateTime();
                    Classroom cjClassroom = cj.getClassroom();
                    // possible conflict if both classes have the same classroom and week day
                    if(i != j && cjDT != null && cjClassroom != null){
                        if(wday == cjDT.getWeekday().toString() && classroom == cjClassroom.getName()){
                            int jShour = cjDT.getStartHour();
                            int jDuration = cjDT.getDuration();
                            //conflict if overlapped
                            if((jShour >= shour && jShour < shour+duration) ||
                                    (jShour+jDuration >= shour && jShour+jDuration < shour+duration) ||
                                    (jShour< shour && jShour+jDuration > shour+duration)) return new Out(Boolean.FALSE);
                        }
                    }
                }
            }
        }
        return new Out(Boolean.TRUE);
    }

    /**
     *
     */

}
