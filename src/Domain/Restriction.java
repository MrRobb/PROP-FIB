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


