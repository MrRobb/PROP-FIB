package Domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.function.Function;

public class Functions {
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

        LinkedHashSet<Class> classes = s.getClasses();

        for (Class c : classes) {
            if (c.isOK()) {

                DateTime cDT = c.getDateTime();
                if (cDT.getWeekday().toString().equals(day)) {

                    Integer sh = cDT.getStartHour();
                    Integer eh = cDT.getEndHour(c.getGroup().getDuration());

                    if ((sh >= startHour && sh <= endHour) ||
                            (eh >= startHour && eh <= endHour) ||
                            (sh <= startHour && eh >= endHour))
                    {
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
        LinkedHashSet<Class> classes = s.getClasses();
        for(Class c : classes){
            DateTime cDT = c.getDateTime();
            Classroom cClassroom = c.getClassroom();
            Group cGroup = c.getGroup();
            // if class c has a classroom and date assigned
            if(cDT != null && cClassroom != null){
                String wday = cDT.getWeekday().toString();
                Integer shour = cDT.getStartHour();
                Integer duration = cGroup.getDuration();
                String classroom = cClassroom.getName();
                // inspect every other class cj
                for(Class cj : classes){
                    DateTime cjDT = cj.getDateTime();
                    Classroom cjClassroom = cj.getClassroom();
                    Group cjGroup = cj.getGroup();
                    // possible conflict if both classes have the same classroom and week day
                    if(c != cj && cjDT != null && cjClassroom != null){
                        if(wday == cjDT.getWeekday().toString() && classroom == cjClassroom.getName()){
                            int jShour = cjDT.getStartHour();
                            int jDuration = cjGroup.getDuration();
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

    public static Out everyClassBetweenStartAndEndHour(In input){
        Schedule s = (Schedule) input.getIn(0);
        Integer startHour = (Integer) input.getArgs(0);
        Integer endHour = (Integer) input.getArgs(1);
        LinkedHashSet<Class> classes = s.getClasses();
        for(Class c : classes){
            Integer sh = c.getDateTime().getStartHour();
            Integer eh = c.getDateTime().getEndHour(c.getGroup().getDuration());
            if(sh < startHour || eh >= endHour) return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that a laboratoryPC group must be assigned to a classroom with PCs
     * @param input in[0] The schedule we want to check.     *
     * @return true if all groups of type "laboratoryPC are assigned to a classroom with computers.
     */
    public static Out groupRequiresClassroomWithExtras(In input) {
        Schedule s = (Schedule) input.getIn(0);
        String extra = (String) input.getArgs(0);
        String type = (String) input.getArgs(0);
        LinkedHashSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Group g = c.getGroup();
            if (g.hasType(type)) {
                Classroom cl = c.getClassroom();
                if (!cl.hasExtra(extra)) return new Out(Boolean.FALSE);
            }
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that two groups of the same level with the same name cannot be overlapped.
     * @param input in[0] The schedule we want to check.
     * @return true if no group is overlapped with a group of the same level and name.
     */
    /*public static Out ifSubjHasMoreThanNGroupsThenAfternoon (In input) {
        Schedule s = (Schedule) input.getIn(0);
        Integer n = (Integer) input.getArgs(0);
        Integer minHour = (Integer) input.getArgs(1);
        LinkedHashSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            ArrayList<Class> groups = new ArrayList<>();
            if(c.getGroup().getLevel() == 0){
                subgroups.add(c);
                subgroups.add(parent);

            }

        }
        return new Out(Boolean.TRUE);
    }
*/


    /**
     * Checks that two groups of the same level with the same name cannot be overlapped.
     * @param input in[0] The schedule we want to check.
     * @return true if no group is overlapped with a group of the same level and name.
     */
    public static Out noTwoSameSemesterGroupsOverlappedWithSameName(In input) {
        Schedule s = (Schedule) input.getIn(0);
        LinkedHashSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Group g = c.getGroup();
            Integer semester = g.getSubject().getSemester();
            String name = g.getName();
            for(Class cc : classes){
                Group g2 = cc.getGroup();
                if(semester.equals(g2.getSubject().getSemester()) && name.equals(g2.getName())){
                    if(areOverlapped(c,cc)) return new Out(Boolean.FALSE);
                }
            }
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

    public static Out groupOfATypeMustBeSetBefore(In input){
        Schedule s = (Schedule) input.getIn(0);
        String subj = (String) input.getArgs(0);
        String type1 = (String) input.getArgs(1);
        LinkedHashSet<Class> classes = s.getClasses();
        for(Class c : classes){
            Group g = c.getGroup();
            DateTime subgroupDT = c.getDateTime();
            String subject = Subjects.getInstance().get(g.getSubjectID()).getName();
            // it is a candidate to check -> it's a subgroup of subject of type1
            if(subject.equals(subj) && g.hasType(type1) && g.getLevel() > 0){
                for(Class c1 : classes){
                    Group g1 = c1.getGroup();
                    if(g1.equals(g.getParentGroupID())){
                        DateTime gparentDT = c1.getDateTime();
                        if(subgroupDT.compareTo(gparentDT) == -1)  return new Out(Boolean.FALSE);
                    }
                }
            }
        }
        return new Out(Boolean.TRUE);
    }


    /**
     * Checks that a laboratoryPC group must be assigned to a classroom with PCs
     * @param input in[0] The schedule we want to check.     *
     * @return true if all groups of type "laboratoryPC are assigned to a classroom with computers.
     */
    public static Out laboratoryPCgroupHasPCs(In input) {
        Schedule s = (Schedule) input.getIn(0);
        LinkedHashSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Group g = c.getGroup();
            if (g.hasType("laboratoryPC")) {
                Classroom cl = c.getClassroom();
                if (!cl.hasExtra("computers")) return new Out(Boolean.FALSE);
            }
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that a group and a subgroup of the same subject cannot be overlapped
     * @param input in[0] The schedule we want to check.
     * @return true if no group is overlapped with its own subgroup of a subject.
     */
    public static Out noGroupSubGroupOverlapped(In input) {
        Schedule s = (Schedule) input.getIn(0);
        LinkedHashSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Group g = c.getGroup();
            Group pg = Groups.getInstance().get(g.getParentGroupID());
            for(Class cc : classes){
                if(cc.getGroup().equals(pg)){
                    if(areOverlapped(c,cc)) return new Out(Boolean.FALSE);
                }
            }
        }
        return new Out(Boolean.TRUE);
    }

    public static Boolean areOverlapped(Class c1, Class c2) {
        Integer sh1 = c1.getDateTime().getStartHour();
        Integer eh1 = c1.getDateTime().getEndHour(c1.getGroup().getDuration());
        Integer sh2 = c2.getDateTime().getStartHour();
        Integer eh2 = c2.getDateTime().getEndHour(c2.getGroup().getDuration());
        if(c1.getDateTime().getWeekday().equals(c2.getDateTime().getWeekday()) && (eh1 <= sh2 || eh2 <= sh1)) return false;
        else return true;
    }

}
