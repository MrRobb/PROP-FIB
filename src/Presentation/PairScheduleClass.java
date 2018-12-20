package Presentation;

public class PairScheduleClass {

    private String hour;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    public PairScheduleClass(String classname, String day, String hour) {
        this.hour = hour;
        if (day.equalsIgnoreCase("Monday")) {
            monday = classname;
        }
        else if (day.equalsIgnoreCase("Tuesday")) {
            tuesday = classname;
        }
        else if (day.equalsIgnoreCase("Wednesday")) {
            wednesday = classname;
        }
        else if (day.equalsIgnoreCase("Thursday")) {
            thursday = classname;
        }
        else if (day.equalsIgnoreCase("Friday")) {
            friday = classname;
        }
    }

    public String getMonday() {
        return monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public String getFriday() {
        return friday;
    }

    public String getHour() {
        return hour;
    }
}
