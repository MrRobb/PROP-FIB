package Domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Restriction {

    ArrayList<Function<Object[], Object[]>> restriccion;
    HashMap<String, Function<Object[], Object[]>> blocks;
    // ArrayList<Object[]>

    Restriction() {

        //blocks.put("No class on day...", this::noClassOnDayBetweenSHEH);

    }

    boolean AddBlock(String blockName) {

        // Get block
        Function<Object[], Object[]> func = blocks.get(blockName);

        // Add block
        restriccion.add(func);

        return true;
    }

    Boolean apply(Object[] o) {

        Object[] ret = o;

        try {
            for (Function<Object[], Object[]> block : restriccion) {
                ret = block.apply(ret);
            }

        }
        catch (Exception e) {

        }

        return (Boolean) ret[0];
    }

    /**
     * Checks that there is no class on week day Day between startHour and endHour.
     * @param args [0] The schedule we want to check.
     * @param args [1] The day we want to restrict classes
     * @param args [2] The start hour of the interval with no classes
     * @param args [3] The end hour of the interval with no classes
     * @return true if no classes on this day between [startHour, endHour], false otherwise.
     */

    public Object[] noClassOnDayBetweenSHEH(Object[] args) {

        // Casting args
        Schedule s = (Schedule) args[0];
        String day = (String) args[1];
        Integer startHour = (Integer) args[2];
        Integer endHour = (Integer) args[3];

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
                            (sh < startHour && sh+d > endHour)) {
                        return new Object[] {Boolean.FALSE};

                    }
                }
            }
        }

        return new Object[] {Boolean.TRUE};
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
                                    (jShour< shour && jShour+jDuration > shour+duration)) return false;
                        }
                    }
                }
            }
        }
        return true;
    }



}


