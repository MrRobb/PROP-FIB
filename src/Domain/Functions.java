package Domain;

import java.util.*;
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
        DateTime.WeekDay day = (DateTime.WeekDay) input.getArgs(0);
        Integer startHour = (Integer) input.getArgs(1);
        Integer endHour = (Integer) input.getArgs(2);

        TreeSet<Class> classes = s.getClasses();

        for (Class c : classes) {
            if (c.isOK()) {

                DateTime cDT = c.getDateTime();
                if (cDT.getWeekday() == day) {

                    Integer sh = cDT.getStartHour();
                    Integer eh = cDT.getEndHour(c.getGroup().getDuration());

                    if ((sh >= startHour && sh < endHour) ||
                            (eh >= startHour && eh < endHour) ||
                            (sh <= startHour && eh > endHour))
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

        TreeSet<Class> classes = s.getClasses();

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

        TreeSet<Class> classes = s.getClasses();

        for (Class c : classes) {

            Integer sh = c.getDateTime().getStartHour();
            Integer eh = c.getDateTime().getAbsEndHour(c.getGroup().getDuration());

            if (sh < startHour || eh > endHour) {
                return new Out(Boolean.FALSE);
            }
        }
        return new Out(Boolean.TRUE);
    }



    /**
     * Checks that all classrooms must have a certain equipment (extra)
     * @param input in[0] The schedule we want to check.     *
     *              args[0] The extra
     * @return true if all classroom have this extra
     */
    public static Out allGroupMustHaveClassromWithExtra(In input) {

        Schedule s = (Schedule) input.getIn(0);
        String extra = (String) input.getArgs(0);
        String type = (String) input.getArgs(1);
        TreeSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Classroom cl = c.getClassroom();
            if (!cl.hasExtra(extra) && c.getGroup().hasType(type)) {
                return new Out(Boolean.FALSE);
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
        TreeSet<Class> classes = s.getClasses();
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

        TreeSet<Class> classes = s.getClasses();

        for (Class c : classes) {
            Group g = c.getGroup();

            Integer semester = g.getSubject().getSemester();
            String name = g.getName();

            for (Class cc : classes) {

                Group g2 = cc.getGroup();

                if (g != g2) {
                    if(semester.equals(g2.getSubject().getSemester()) && name.equals(g2.getName())){
                        if(areOverlapped(c, cc)) {
                            return new Out(Boolean.FALSE);
                        }
                    }
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

    public static Out groupOfATypeMustBeSetBefore(In input) {

        Schedule s = (Schedule) input.getIn(0);
        String givenS = (String) input.getArgs(0);
        String givenT = (String) input.getArgs(1);

        TreeSet<Class> classes = s.getClasses();

        for(Class c1 : classes){

            if (c1.isOK()) {
                Group g1 = c1.getGroup();
                DateTime d1 = c1.getDateTime();
                String s1 = g1.getSubject().getName();

                // it is a candidate to check -> it's a subgroup of subject of givenT
                if (s1.equals(givenS) && g1.hasType(givenT)) {

                    for (Class c2 : classes) {

                        Group g2 = c2.getGroup();
                        DateTime d2 = c2.getDateTime();
                        String s2 = g2.getSubject().getName();

                        if (s2.equals(givenS) && !g2.hasType(givenT)) {

                            if (d2.compareTo(d1) > 0) {
                                return new Out(Boolean.FALSE);
                            }
                        }
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

        TreeSet<Class> classes = s.getClasses();

        for (Class c : classes) {

            Group g = c.getGroup();
            Group pg = Groups.getInstance().get(g.getParentGroupID());

            if (pg != null) {
                for (Class cc : classes) {

                    if(cc.getGroup().equals(pg)) {
                        if(areOverlapped(c, cc)) {
                            return new Out(Boolean.FALSE);
                        }
                    }
                }
            }
        }
        return new Out(Boolean.TRUE);
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

        TreeSet<Class> classes = s.getClasses();

        for(Class c : classes) {

            Group g = c.getGroup();

            if(g.getSubject().getName().equals(subj) && c.getDateTime().getStartHour() < iniHour) {
                return new Out(Boolean.FALSE);
            }
        }

        return new Out(Boolean.TRUE);
    }


    /**
     * Checks that a group must be assigned to a classroom with enough capacity
     * @param input in[0] The schedule we want to check.
     * @return true if no group is overlapped with its own subgroup of a subject.
     */
    public static Out groupFitsInClassroom(In input) {
        Schedule s = (Schedule) input.getIn(0);
        TreeSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Integer groupCap = c.getGroup().getCapacity();
            Integer ccap = c.getClassroom().getCapacity();
            if(ccap < groupCap) return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }


    /**
     * Checks that a group must be assigned to a classroom with enough capacity
     * @param input in[0] The schedule we want to check.
     * @return true if no group is overlapped with its own subgroup of a subject.
     */
    public static Out atMostNClassroomsCanBeUsed(In input) {

        Schedule s = (Schedule) input.getIn(0);
        Integer max = (Integer) input.getArgs(0);

        TreeSet<Class> classes = s.getClasses();
        TreeSet<Classroom> classrooms_used = new TreeSet<>();

        for (Class c : classes) {
            classrooms_used.add(c.getClassroom());
        }
        if (classrooms_used.size() > max) return new Out(Boolean.FALSE);
        return new Out(Boolean.TRUE);
    }


    // auxiliar function not block
    private static Boolean areOverlapped(Class c1, Class c2) {

        Integer sh1 = c1.getDateTime().getStartHour();
        Integer eh1 = c1.getDateTime().getEndHour(c1.getGroup().getDuration());
        Integer sh2 = c2.getDateTime().getStartHour();
        Integer eh2 = c2.getDateTime().getEndHour(c2.getGroup().getDuration());

        if (c1.getDateTime().getWeekday().ordinal() == c2.getDateTime().getWeekday().ordinal()) {
            return eh1 > sh2 && sh1 < eh2;
        }
        else {
            return false;
        }
    }

    /**
     * Checks that at most we have N groups of certain týpe of a subject
     * @param input in[0] The schedule we want to check
     *              args[0] The subject
     *              args[1] The type
     *              args[2] The N
     * @return true if at most we have N subgroup of a specific subject.
     */
    public static Out atMostNGroupsOfSubjectOfTypeA(In input) {

        Schedule s = (Schedule) input.getIn(0);
        String subj = (String) input.getArgs(0);
        String type = (String) input.getArgs(1);
        Integer max = (Integer) input.getArgs(2);

        TreeSet<Class> classes = s.getClasses();
        TreeSet<Class> subgroups = new TreeSet<>();

        for (Class c : classes) {
            String sName = c.getGroup().getSubject().getName();
            if (sName.equals(subj) && c.getGroup().hasType(type)) {
                subgroups.add(c);
            }
            if (subgroups.size() > max) return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that we don't use a given classroom
     * @param input in[0] The schedule we want to check
     *              args[0] The classroom
     * @return true if we don't have any class in that classroom
     */
    public static Out notUsedClassroom(In input) {
        Schedule s = (Schedule) input.getIn(0);
        String prohibitedClassroom = (String) input.getArgs(0);

        TreeSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            String classroom = c.getClassroom().getName();
            if (classroom.equals(prohibitedClassroom)) {
                return new Out(Boolean.FALSE);
            }
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that we don't have any group of the given subject
     * @param input in[0] The schedule we want to check
     *              args[0] The subject
     * @return true if we don't have any class of that subject
     */
    public static Out notAsignedSubject(In input) {
        Schedule s = (Schedule) input.getIn(0);
        String prohibitedSubject = (String) input.getArgs(0);

        TreeSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            String subject = c.getGroup().getSubject().getName();
            if (subject.equals(prohibitedSubject)) {
                return new Out(Boolean.FALSE);
            }
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that the given subject of a certain type is not assigned to the given classroom
     * @param input in[0] The schedule we want to check.
     *              args[0] The subject
     *              args[1] The type
     *              args[2] The classroom
     * @return true if the there aren't any subject of that group assigned to that classroom
     */
    public static Out subjectCantBeAssignedToClassroom(In input) {

        Schedule s = (Schedule) input.getIn(0);
        String subjectName = (String) input.getArgs(0);
        String type = (String) input.getArgs(1);
        String classroom = (String) input.getArgs(2);

        TreeSet<Class> classes = s.getClasses();

        for (Class c : classes) {
            String cl = c.getClassroom().getName();
            String subj = c.getGroup().getSubject().getName();

            if (cl.equals(classroom) && subj.equals(subjectName) && c.getGroup().hasType(type)) {
                return new Out(Boolean.FALSE);
            }
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that the schedule must not overpass the given number of hour
     * @param input in[0] The schedule we want to check.
     *              args[0] The number of hours
     * @return true if the total number of different hour don't overpass the given maximum number of hours
     */
    public static Out noOverpassNHourTotal(In input) {

        Schedule s = (Schedule) input.getIn(0);
        Integer nMax = (Integer) input.getArgs(0);

        TreeSet<Class> classes = s.getClasses();
        TreeSet<DateTime> dt = new TreeSet<>();

        for (Class c : classes) {
            dt.add(c.getDateTime());
            if (nMax < dt.size()) return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);

    }

    /**
     * Checks that the schedule must not have days that overpass the given number of hour
     * @param input in[0] The schedule we want to check.
     *              args[0] The number of hours
     * @return true if the total number of different hour in a day don't overpass the given maximum number of hours
     */
    public static Out noOverpassNHourAllDays(In input) {

        Schedule s = (Schedule) input.getIn(0);
        Integer nMax = (Integer) input.getArgs(0);

        TreeSet<Class> classes = s.getClasses();
        TreeSet<DateTime> monday = new TreeSet<>();
        TreeSet<DateTime> tuesday = new TreeSet<>();
        TreeSet<DateTime> wednesday = new TreeSet<>();
        TreeSet<DateTime> thursday = new TreeSet<>();
        TreeSet<DateTime> friday = new TreeSet<>();


        for (Class c : classes) {
            String day = c.getDateTime().getWeekday().toString();
            if (day.equals("Monday")) {
                monday.add(c.getDateTime());
            }
            else if (day.equals("Tuesday")) {
                monday.add(c.getDateTime());
            }
            else if (day.equals("Wednesday")) {
                monday.add(c.getDateTime());
            }
            else if (day.equals("Thursday")) {
                monday.add(c.getDateTime());
            }
            else if (day.equals("Friday")) {
                monday.add(c.getDateTime());
            }

            if(nMax < monday.size()) return new Out(Boolean.FALSE);
            if(nMax < tuesday.size()) return new Out(Boolean.FALSE);
            if(nMax < wednesday.size()) return new Out(Boolean.FALSE);
            if(nMax < thursday.size()) return new Out(Boolean.FALSE);
            if(nMax < friday.size()) return new Out(Boolean.FALSE);

        }
        return new Out(Boolean.TRUE);

    }

    /**
     * Checks that all classrooms of the given subject of the given type can't must have a certain equipment (extra)
     * @param input in[0] The schedule we want to check.     *
     *              args[0] The subject
     *              args[1] The type
     *              args[2] The extra
     * @return true if no classroom of that subject and that type have that equipment
     */
    public static Out noGroupOfSubjectOfTypeWithExtra(In input) {

        Schedule s = (Schedule) input.getIn(0);
        String subject = (String) input.getArgs(0);
        String type = (String) input.getArgs(1);
        String extra = (String) input.getArgs(2);
        TreeSet<Class> classes = s.getClasses();
        for (Class c : classes) {
            Classroom cl = c.getClassroom();
            if (c.getGroup().getSubject().getName().equals(subject) && c.getGroup().hasType(type)&& cl.hasExtra(extra)) {
                return new Out(Boolean.FALSE);
            }
        }

        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that between start hour and end hour the groups must have at least the given capacity
     * @param input in[0] The schedule we want to check.
     *              args[0] The start hour
     *              args[1] The end hour
     *              args[2] The minimum capacity
     * @return true if no group between start hour and end hour has less than the given capacity
     */
    public static Out noLessCapacityOfGroupsBetweenSHEH(In input) {
        Schedule s = (Schedule) input.getIn(0);
        Integer start_hour = (Integer) input.getArgs(0);
        Integer end_hour = (Integer) input.getArgs(1);
        Integer min_capacity = (Integer) input.getArgs(2);
        TreeSet<Class> classes = s.getClasses();

        for (Class c : classes) {
            Integer sh = c.getDateTime().getStartHour();
            Integer eh = c.getDateTime().getEndHour(c.getGroup().getDuration());
            if (sh >= start_hour && eh <= end_hour && c.getGroup().getCapacity() < min_capacity) new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }

    /**
     * Checks that the difference between capcity of a classroom and the group can't be superior than n
     * @param input in[0] The schedule we want to check.
     *              args[0] The n
     * @return true if no difference of group and classroom's capacity is higher than n
     */
    public static Out noMoreFreeCapacityBetweenGroupAndClassroom(In input) {
        Schedule s = (Schedule) input.getIn(0);
        Integer max_diff = (Integer) input.getArgs(0);
        TreeSet<Class> classes = s.getClasses();

        for (Class c : classes) {
            Integer group_cap = c.getGroup().getCapacity();
            Integer classroom_cap = c.getClassroom().getCapacity();
            if ((classroom_cap - group_cap) > max_diff) return new Out(Boolean.FALSE);
        }
        return new Out(Boolean.TRUE);
    }




}
