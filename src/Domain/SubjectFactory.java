package Domain;


public class SubjectFactory {

    public static void main(String[] args) {
        Subject s1 = new Subject("PROP", 5, 6,true, null);
        String n = s1.getName();
        System.out.println("name is: " + n);
        Integer sem = s1.getSemester();
        System.out.println("semester is: " + sem);
        Integer c = s1.getCredits();
        System.out.println("credits: " + c);
        Boolean mand = s1.getMandatory();
        System.out.println("Is mandatory?: " + mand);
        String spec = s1.getSpeciality();
        System.out.println("Speciality: " + spec);
    }
}
