package Domain;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SubjectsFactory {

    public static boolean produce() {

        Subjects.getInstance().clear();

        try {
            Object obj = new JSONParser().parse(new FileReader("json/degreeEasy.json"));
            JSONObject jo =  (JSONObject) obj;

            // Treat subjects
            ArrayList<Subject> subjectsDegree = new ArrayList<>();
            JSONArray subjs = (JSONArray) jo.get("subjects");
            Iterator itrs = subjs.iterator();
            while (itrs.hasNext()){
                JSONObject subj = (JSONObject) itrs.next();
                String namesubj = (String) subj.get("name");
                Double crts = (Double) subj.get("credits");
                Integer sem = (Integer) subj.get("semester");
                Boolean mand = (Boolean) subj.get("mandatory");
                String spec = (String) subj.get("speciality");
                Subject s = new Subject(namesubj, sem, crts, mand, spec);
                Subjects.getInstance().addSubject(s);
                subjectsDegree.add(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
    /*public static void main(String[] args) {
        Subject s1 = new Subject("PROP", 5, 6.0,true, null);
        System.out.println("name is: " + s1.getName());
        System.out.println("semester is: " + s1.getSemester());
        System.out.println("credits: " + s1.getCredits());
        System.out.println("Is mandatory?: " + s1.getMandatory());
        System.out.println("Speciality: " + s1.getSpeciality());



        Subject s2 = new Subject("A");
        s2.setCredits(6.5);
        s2.setMandatory(true);
        s2.setSemester(5);
        s2.setSpeciality("Computing");

        System.out.println("Name: " + s2.getName());
        System.out.println("Credits: " + s2.getCredits());
        System.out.println("Semester: " + s2.getSemester());
        System.out.println("Mandatory? " + s2.getMandatory());
        System.out.println("Speciality: " + s2.getSpeciality());


        s2.setName("Algorithm");
        System.out.println("Name: " + s2.getName());
    }*/

