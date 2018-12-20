package Domain;


// Java program for write JSON to a file

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

class DegreesFactory {

	public static boolean produce(String degname, Integer ncredits, JSONArray gtypes, JSONArray grps) {

		Degree.getInstance().clear();

		// Treat degree
		ArrayList<String> types = new ArrayList<>();
		Iterator itrgr = gtypes.iterator();
		while (itrgr.hasNext()){
			types.add((String) itrgr.next());
		}

		Degree.getInstance().setName(degname);
		Degree.getInstance().setCredits(ncredits);
		Degree.getInstance().setTypeOfGroups(types);

		// Treat groups
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
		return true;
	}
}
