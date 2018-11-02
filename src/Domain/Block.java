package Domain;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class Block {

    /**
     * Checks that there is no class on week day Day between startHour and endHour.
     * @param input in[0] The schedule we want to check.
                     in[1] The day we want to restrict classes
                     args[0] The start hour of the interval with no classes
                     args[1] The end hour of the interval with no classes
     * @return true if no classes on this day between [startHour, endHour], false otherwise.
     */

	public static Out noClassOnDayBetweenSHEH(In input) {

		// Casting args
		Schedule s = (Schedule) input.getIn(0);
		String day = (String) input.getArgs(0);
		Integer startHour = (Integer) input.getArgs(1);
		Integer endHour = (Integer) input.getArgs(2);

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

    public static Out noTwoClassesAtSameHourSameClassroom(In input){
        Schedule s = (Schedule) input.getIn(0);
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
     * Checks that there is no class on week day Day between startHour and endHour.
     * @param input in[0] The schedule we want to check.
                     in[1] The day we want to restrict classes
                     args[0] The start hour of the interval with no classes
                     args[1] The end hour of the interval with no classes
     * @return true if no classes on this day between [startHour, endHour], false otherwise.
     */

    public Out everyClassBetweenStartAndEndHour(In input){
        Schedule s = (Schedule) input.getIn(0);
        Integer startHour = (Integer) input.getArgs(0);
        Integer endHour = (Integer) input.getArgs(1);
        ArrayList<Class> classes = s.getClasses();
        for(Class c : classes){
            Integer sh = c.getDateTime().getStartHour();
            Integer eh = c.getDateTime().getEndHour();
            if(sh < startHour || eh >= endHour) return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }

    public static Out count(In input) {
        return new Out(input.getIn().length);
    }

    /**
     * Checks the number of hours of class in these days.
     * @param input in[0] the schedule.
     *              args[] the days we want to count hours in String.
     * @return total hours of in args[] days.
     */

    public static Out countHoursDays(In input) {
        Schedule s = (Schedule) input.getIn(0);
        int count = 0;
        for (Class c: s.getClasses()) {
            DateTime dT = DateTimes.getInstance().get(c.getDateTimeID());
            String dtWeekDay = dT.getWeekday().toString();
            for (Object o: input.getArgs()) {
                String weekDay = (String) o;
                if (weekDay.equals(dtWeekDay)) count += dT.getDuration();
            }
        }
        return new Out(count);
    }

    /**
     * Get a specific subject
     * @param input in[0] the schedule.
     *              args[0] the subject we want to get.
     * @return return the subject if found, otherwise null
     */

    public static Out getSubject(In input) {
        Schedule s = (Schedule) input.getIn(0);
        String subName = (String) input.getArgs(0);
        for (Class c: s.getClasses()) {
            Group g = Groups.getInstance().get(c.getGroupID());
            Subject sub = Subjects.getInstance().get(g.getSubjectID());
            if (sub.getName().equals(subName)) return new Out(sub);
        }
        return null;
    }

    /**
     * Get a specific subject
     * @param input in[0] the schedule.
     *              args[0] the subject we want to get.
     * @return return the subject if found, otherwise null
     */

    public static Out getClassroom(In input) {
        Schedule s = (Schedule) input.getIn(0);
        String subName = (String) input.getArgs(0);
        for (Class c: s.getClasses()) {
            Group g = Groups.getInstance().get(c.getGroupID());
            Subject sub = Subjects.getInstance().get(g.getSubjectID());
            if (sub.getName().equals(subName)) return new Out(sub);
        }
        return null;
    }

    public static Out hasClassroom (In input) {
        Schedule s = (Schedule) input.getIn(0);
        String clName = (String) input.getArgs(0);
        for (Class c: s.getClasses()) {
            Group g = Groups.getInstance().get(c.getGroupID());
            if (g.getClassroomID().equals(clName)) return new Out(Boolean.TRUE);
        }
        return new Out(Boolean.FALSE);
    }

    public static Out hasSubject (In input) {
        Schedule s = (Schedule) input.getIn(0);
        String subName = (String) input.getArgs(0);
        for (Class c: s.getClasses()) {
            Group g = Groups.getInstance().get(c.getGroupID());
            if (g.getSubjectID(.equals(subName)) return new Out(Boolean.TRUE);
        }
        return new Out(Boolean.FALSE);
    }

    public static Out filterClassroom (In input) {
        Object[] objs = input.getIn();
        String clName = (String) input.getArgs(0);
        for (Object obj: objs) {
            Class c = (Class) obj;
            Classroom cR = Classrooms.getInstance().get(c.getClassroomID());
            if (classroom.getName().equals(clName)) return new Out(Boolean.TRUE);
        }
        return new Out(Boolean.FALSE);
    }


}
