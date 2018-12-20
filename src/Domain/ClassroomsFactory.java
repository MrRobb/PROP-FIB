package Domain;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class ClassroomsFactory {

	public static boolean produce(JSONArray cls) {
		Classrooms.getInstance().clear();
		// treat classrooms
		Iterator itrc = cls.iterator();
		while(itrc.hasNext()){
			JSONObject croom = (JSONObject) itrc.next();
			String cname = (String) croom.get("name");
			Integer capacity = (int)(long) croom.get("capacity");
			JSONArray ex = (JSONArray) croom.get("extras");
			ArrayList<String> extras = new ArrayList<>();
			Iterator itrce = ex.iterator();
			while(itrce.hasNext()){
				extras.add((String) itrce.next());
			}
			Classroom c = new Classroom(cname,capacity,extras);
			Classrooms.getInstance().addClassroom(c);
		}
		return true;
	}
}
