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
			Object obj = new JSONParser().parse(new FileReader("json/degreeReal.json"));
			JSONArray ja = (JSONArray) obj;
			JSONObject jo = (JSONObject) ja.get(0);

			// Treat degree
			String degname = (String) jo.get("name");
			Integer ncredits = (int)(long) jo.get("credits");
			JSONArray gtypes = (JSONArray) jo.get("groupTypes");
			ArrayList<String> types = new ArrayList<>();
			Iterator itrgr = gtypes.iterator();
			while (itrgr.hasNext()){
				types.add((String) itrgr.next());
			}

			Degree.getInstance().setName(degname);
			Degree.getInstance().setCredits(ncredits);
			Degree.getInstance().setTypeOfGroups(types);

			// Treat groups
			JSONArray grps = (JSONArray) jo.get("groups");
			Iterator itrg = grps.iterator();
			while(itrg.hasNext()){
				JSONObject gr = (JSONObject) itrg.next();
				String name = (String) gr.get("name");
				Integer level = (int)(long) gr.get("level");
				String nameParent = (String) gr.get("nameParent");
				Integer duration = (int)(long) gr.get("duration");
				Integer capacity = (int)(long) gr.get("capacity");
				String subj = (String) gr.get("subject");
				JSONArray groupTypes = (JSONArray) gr.get("types");
				ArrayList<String> grtypes = new ArrayList<>();
				Iterator itgrt = groupTypes.iterator();
				while(itgrt.hasNext()){
					grtypes.add((String) itgrt.next());
				}
				String subjID = Subjects.getInstance().getIDfromName(subj);
				Integer parentID = Groups.getInstance().getIDfromNameAndSubject(nameParent,subjID);
				Group g = new Group(name,duration,capacity,subjID,level,grtypes,parentID);
				Groups.getInstance().addGroup(g);
			}


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
