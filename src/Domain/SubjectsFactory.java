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

    public static boolean produce(JSONArray subjs) {
        Subjects.getInstance().clear();
            // Treat subjects
            ArrayList<Subject> subjectsDegree = new ArrayList<>();
            Iterator itrs = subjs.iterator();
            while (itrs.hasNext()){
                JSONObject subj = (JSONObject) itrs.next();
                String namesubj = (String) subj.get("name");
                Double crts = (Double) subj.get("credits");
                Integer sem = (int)(long) subj.get("semester");
                Boolean mand = (Boolean) subj.get("mandatory");
                String spec = (String) subj.get("speciality");
                Subject s = new Subject(namesubj, sem, crts, mand, spec);
                Subjects.getInstance().addSubject(s);
                subjectsDegree.add(s);
            }
        return true;
    }
}

