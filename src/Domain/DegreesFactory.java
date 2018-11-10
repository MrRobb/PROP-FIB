package Domain;


// Java program for write JSON to a file

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DegreesFactory {

	public static boolean produce() {

		Degrees.getInstance().clear();

		try {
			Object obj = new JSONParser().parse(new FileReader("json/degreeEasy.json"));
			JSONObject jo =  (JSONObject) obj;

			// Treat degree
			String degname = (String) jo.get("name");
			Integer ncredits = (Integer) jo.get("credits");
			JSONArray gtypes = (JSONArray) jo.get("types");
			ArrayList<String> types = new ArrayList<>();
			Iterator itrgr = gtypes.iterator();
			while (itrgr.hasNext()){
				types.add((String) itrgr.next());
			}

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

			Degree degree = new Degree(degname,ncredits,types,subjectsDegree);
			Degrees.getInstance().addDegree(degree);

			// treat classrooms
			JSONArray cls =(JSONArray) jo.get("classrooms");
			Iterator itrc = cls.iterator();
			while(itrc.hasNext()){
				JSONObject croom = (JSONObject) itrc.next();
				String cname = (String) croom.get("name");
				Integer capacity = (Integer) croom.get("capacity");
				JSONArray ex = (JSONArray) croom.get("extras");
				ArrayList<String> extras = new ArrayList<>();
				Iterator itrce = ex.iterator();
				while(itrce.hasNext()){
					extras.add((String) itrce.next());
				}
				Classroom c = new Classroom(cname,capacity,extras);
				Classrooms.getInstance().addClassroom(c);
			}

			JSONArray grps = (JSONArray) jo.get("groups");
			Iterator itr2 = grps.iterator();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*String name = "FIB";
		Integer credits = 240;
		ArrayList<String> typeOfGroups = new ArrayList<>(0);
		ArrayList<Subject> subjects = new ArrayList<>(0);
		typeOfGroups.add("Morning");
		Subject prop = new Subject("PROP");
		subjects.add(prop);

		Degree fib = new Degree(name, credits, typeOfGroups, subjects);

		boolean ok = true;
		if (ok) ok = fib.addTypeOfGroups("Afternoon");
		if (ok) ok = fib.addSubject(new Subject("EDA"));
		if (ok) ok = fib.setName("FIB-2018");
		if (ok) ok = fib.setCredits(240);
		if (ok) ok = fib.setSubjects(subjects);
		if (ok) ok = fib.setTypeOfGroups(typeOfGroups);

		*/
		return  ok;



	}
}
