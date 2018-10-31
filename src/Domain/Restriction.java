package Domain;

import java.util.ArrayList;

public class Restriction {


    /**
     * Checks that there is no class on week day Day between startHour and endHour.
     * @param s The schedule we want to check.
     * @param day The day we want to restrict classes
     * @param startHour The start hour of the interval with no classes
     * @param endHour The end hour of the interval with no classes
     * @return true if no classes on this day between [startHour, endHour], false otherwise.
     */

    public Boolean noClassOnDayBetweenSHEH(Schedule s, String day, Integer startHour, Integer endHour){
        ArrayList<Class> classes = s.getClasses();
        for(int i = 0; i<classes.size(); ++i){
            Class c = classes.get(i);
            DateTime cDT = c.getDateTime();
            if(cDT != null){
                if(cDT.getWeekday().toString().equals(day)){
                    Integer sh = cDT.getStartHour();
                    Integer d = cDT.getDuration();
                    if((sh >= startHour && sh < endHour) ||
                            (sh+d >= startHour && sh+d < endHour) ||
                            (sh < startHour && sh+d > endHour)) return false;
                }
            }
        }
        return true;
    }


    /**
     * Checks that there are no classes overlapped at the same classroom.
     * @param s The schedule we want to check.
     * @return true if no classes overlapped at the same classroom, false otherwise.
     */

    public Boolean noTwoClassesAtSameHourSameClassroom(Schedule s){
        ArrayList<Class> classes = s.getClasses();
        for(int i = 0; i<classes.size(); ++i){
            Class c = classes.get(i);
            DateTime cDT = c.getDateTime();
            Classroom cClassroom = c.getClassroom();
            if(cDT != null && cClassroom != null){
                String wday = cDT.getWeekday().toString();
                Integer shour = cDT.getStartHour();
                Integer duration = cDT.getDuration();
                String classroom = cClassroom.getName();
                for(int j=0; j<classes.size(); ++j){
                    Class cj = classes.get(j);
                    DateTime cjDT = cj.getDateTime();
                    Classroom cjClassroom = cj.getClassroom();
                    if(i != j && cjDT != null && cjClassroom != null){
                        if(wday == cjDT.getWeekday().toString() && classroom == cjClassroom.getName()){
                            int jShour = cjDT.getStartHour();
                            int jDuration = cjDT.getDuration();
                            if((jShour >= shour && jShour < shour+duration) ||
                                    (jShour+jDuration >= shour && jShour+jDuration < shour+duration) ||
                                    (jShour< shour && jShour+jDuration > shour+duration)) return false;
                        }
                    }
                }

            }
        }
        return true;
    }



}


