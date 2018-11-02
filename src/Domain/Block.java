package Domain;

import java.util.ArrayList;
import java.util.function.Function;

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
		Integer endHour = (Integer) input.getArgs(1);

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
     * Checks that every class is between startHour and endHour.
     * @param input in[0] The schedule we want to check.
     *              args[1] The start hour of classes.
     *              args[2] The end hour of classes.
     * @return true if all classes are set between startHour and endHour, false otherwise.
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


    /**
     * Checks if every subgroup of type type1 is set later than the group
     * of the same subject which the subgroup belongs.
     *      .
     * @param input in[0] The schedule we want to check.
     *              args[0] The subject we want to check.
     *              args[2] Type of the group that must go after.
     *
     * @return true if all subgroups of type type1 have the class later
     * than the group of which they belong, false otherwise.
     */

    public Out groupOfATypeMustBeSetBefore(In input){
        Schedule s = (Schedule) input.getIn(0);
        String subj = (String) input.getArgs(0);
        String type1 = (String) input.getArgs(1);
        ArrayList<Class> classes = s.getClasses();
        for(Class c : classes){
            Group g = c.getGroup();
            DateTime subgroupDT = c.getDateTime();
            String subject = Subjects.getInstance().get(g.getSubjectID()).getName();
            // it is a candidate to check -> it's a subgroup of subject of type1
            if(subject.equals(subj) && g.hasType(type1) && g.getLevel() > 0){
                for(Class c1 : classes){
                    Group g1 = c1.getGroup();
                    if(g1.getName() == g.getParentGroup().getName()){
                        DateTime gparentDT = c1.getDateTime();
                        if(subgroupDT.compareTo(gparentDT) == -1)  return new Out(Boolean.FALSE);
                    }

                }
            }
        }
        return new Out(Boolean.TRUE);
    }
}
