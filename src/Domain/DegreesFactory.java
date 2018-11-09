package Domain;

import java.util.ArrayList;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class DegreesFactory {

	public static boolean produce() {

		Degrees.getInstance().clear();
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


		return  ok;
		*/


	}
}
