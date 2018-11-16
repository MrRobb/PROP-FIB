package test;

import Domain.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class DateTimeTest {

    enum WeekDay {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday
    }

    @org.junit.Test
    public void getWeekday() {
        DateTime dateTime = new DateTime(DateTime.WeekDay.Friday, 8);
        String wd = dateTime.getWeekday().toString();
        assertEquals("Friday",wd);
    }

    @org.junit.Test
    public void setWeekday() {
        DateTime dateTime = new DateTime(DateTime.WeekDay.Friday, 8);
        dateTime.setWeekday(DateTime.WeekDay.Monday);
        String wd = dateTime.getWeekday().toString();
        assertEquals("Monday",wd);
    }

    @org.junit.Test
    public void getStartHour() {
        DateTime dateTime = new DateTime(DateTime.WeekDay.Friday, 8);
        Integer sh = dateTime.getStartHour();
        assertEquals(8,sh,0);
    }

    @org.junit.Test
    public void setStartHour() {
        DateTime dateTime = new DateTime(DateTime.WeekDay.Friday, 8);
        dateTime.setStartHour(10);
        Integer sh = dateTime.getStartHour();
        assertEquals(10,sh,0);
    }

    @org.junit.Test
    public void getEndHour() {
        Integer duration = 2;
        DateTime dateTime = new DateTime(DateTime.WeekDay.Friday, 23);
        Integer eh = (dateTime.getStartHour() + duration) % 24;
        assertEquals(1,eh,0);
    }

    @org.junit.Test
    public void getAbsEndHour() {
        Integer duration = 2;
        DateTime dateTime = new DateTime(DateTime.WeekDay.Friday, 23);
        Integer eh = dateTime.getStartHour() + duration;
        assertEquals(25,eh,0);
    }

    @org.junit.Test
    public void equals() {
        DateTime dateTime1 = new DateTime(DateTime.WeekDay.Friday, 23);
        DateTime dateTime2 = new DateTime(DateTime.WeekDay.Friday, 23);
        assertEquals(dateTime1,dateTime2);
    }

    @org.junit.Test
    public void compareTo() {
        DateTime dateTime1 = new DateTime(DateTime.WeekDay.Monday, 15);
        DateTime dateTime2 = new DateTime(DateTime.WeekDay.Friday, 8);
        Boolean b = dateTime1.compareTo(dateTime2) < 0;
        assertEquals(b,true);
    }
}