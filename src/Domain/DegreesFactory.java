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

		Degree.getInstance().clear();

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

			Degree.getInstance().setName(degname);



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
		return true;
	}
}
