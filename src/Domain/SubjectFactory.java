package Domain;

public class SubjectFactory {

    public static void main(String[] args) {
        Subject s1 = new Subject("PROP", 5, 6.0,true, null);
        System.out.println("name is: " + s1.getName());
        System.out.println("semester is: " + s1.getSemester());
        System.out.println("credits: " + s1.getCredits());
        System.out.println("Is mandatory?: " + s1.getMandatory());
        System.out.println("Speciality: " + s1.getSpeciality());
        System.out.println("Weekly Hours: " + s1.getWeeklyHours("theory"));
        s1.addWeeklyHours("theory", 4);
        System.out.println("Weekly Hours: " + s1.getWeeklyHours("theory"));


        Subject s2 = new Subject("A");
        s2.setCredits(6.5);
        s2.setMandatory(true);
        s2.setSemester(5);
        s2.setSpeciality("Computing");

        System.out.println("Name: " + s2.getName());
        System.out.println("Credits: " + s2.getCredits());
        System.out.println("Semester: " + s2.getSemester());
        System.out.println("Mandatory? " + s2.getMandatory());
        System.out.println("Weekly hours of problems: " + s2.getWeeklyHours("problems"));
        System.out.println("Speciality: " + s2.getSpeciality());

        s2.addWeeklyHours("problems", 2);
        System.out.println("Weekly hours of problems: " + s2.getWeeklyHours("problems"));
        s2.setName("Algorithm");
        System.out.println("Name: " + s2.getName());
    }
}
