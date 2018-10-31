package Domain;

import java.util.ArrayList;

public class Restriction {
    
    public Boolean noClassOnDayBetweenSHEH(Schedule s, DateTime.WeekDay day, Integer startHour, Integer endHour){
        ArrayList<Class> classes = s.getClasses();
        for(int i = 0; i<classes.size(); ++i){
            Class c = classes.get(i);
            DateTime cDT = c.getDateTime();
            if(cDT.getWeekday().equals(day)){
                Integer sh = cDT.getStartHour();
                Integer d = cDT.getDuration();
                if((sh >= startHour && sh < endHour) ||
                        (sh+d >= startHour && sh+d < endHour) ||
                        (sh < startHour && sh+d > endHour)) return false;
            }
        }
        return true;
    }
}


