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

public class ClassroomsFactory {

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
