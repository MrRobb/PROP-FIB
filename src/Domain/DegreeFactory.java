package Domain;

import java.util.ArrayList;

public class DegreeFactory {

	public static boolean produce() {

		String name = "FIB";
		Integer credits = 240;
		ArrayList<String> typeOfGroups = new ArrayList<>(0);
		typeOfGroups.add("Theory");
		typeOfGroups.add("Lab");
		typeOfGroups.add("Problems");

		boolean ok = Degree.getInstance().setName(name);
		if (ok) ok = Degree.getInstance().setCredits(credits);
		if (ok) ok = Degree.getInstance().setTypeOfGroups(typeOfGroups);

		return ok;
	}
}
