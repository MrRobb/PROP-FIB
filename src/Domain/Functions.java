package Domain;

import java.util.Date;
import java.util.Iterator;
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

    public static Out noTwoClassesAtSameHourSameClassroom(In input) {

        Schedule s = (Schedule) input.getIn(0);

        LinkedHashSet<Class> classes = s.getClasses();

        for (Class ci : classes) {
            for (Class cj : classes) {
                if (ci != cj && ci.isOK() && cj.isOK()) {
                    if (ci.getDateTimeID().equals(cj.getDateTimeID()) &&
                            ci.getClassroomID().equals(cj.getClassroomID())) {
                        return new Out(Boolean.FALSE);
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
     * Checks that all groups must belong to a certain type
     * @param input in[0] The schedule we want to check.     *
     *              args[0] The type of group
     * @return true if all groups have this type.
     */
    public static Out allGroupMustHaveType(In input) {
        Schedule s = (Schedule) input.getIn(0);
        String type = (String) input.getArgs(0);
        LinkedHashSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Group g = c.getGroup();
            if (!g.hasType(type)) return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that all classrooms must have a certain equipment (extra)
     * @param input in[0] The schedule we want to check.     *
     *              args[0] The extra
     * @return true if all classroom have this extra
     */
    public static Out allClassroomMustHaveExtra(In input) {
        Schedule s = (Schedule) input.getIn(0);
        String extra = (String) input.getArgs(0);
        LinkedHashSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Classroom cl = c.getClassroom();
            if (!cl.hasExtra(extra)) return new Out(Boolean.FALSE);
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


    // auxiliar function not block
    public static Boolean areOverlapped(Class c1, Class c2) {
        Integer sh1 = c1.getDateTime().getStartHour();
        Integer eh1 = c1.getDateTime().getEndHour(c1.getGroup().getDuration());
        Integer sh2 = c2.getDateTime().getStartHour();
        Integer eh2 = c2.getDateTime().getEndHour(c2.getGroup().getDuration());
        if(c1.getDateTime().getWeekday().equals(c2.getDateTime().getWeekday()) && (eh1 <= sh2 || eh2 <= sh1)) return false;
        else return true;
    }

    /**
     * Checks that a subject subj cannot be placed before an hour h
     * @param input in[0] The schedule we want to check.
     *              args[0] The subject we want to check.
     *              args[1] The start hour in which subj can be placed.
     * @return true if both classes are overlapped.
     */
    public static Out subjectMustBeSetAlwaysAfterHour(In input){
        Schedule s = (Schedule) input.getIn(0);
        String subj = (String) input.getArgs(0);
        Integer iniHour = (Integer) input.getArgs(1);
        LinkedHashSet<Class> classes = s.getClasses();
        for(Class c : classes) {
            if(c.getGroup().getSubject().getName().equals(subj) && c.getDateTime().getStartHour() < iniHour)
                return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }

}
