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

	public static boolean produce() {

		Classrooms.getInstance().clear();
		try {
			Object obj = new JSONParser().parse(new FileReader("json/degreeReal.json"));
			JSONArray ja = (JSONArray) obj;
			JSONObject jo = (JSONObject) ja.get(0);

			// treat classrooms
			JSONArray cls = (JSONArray) jo.get("classrooms");
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

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;

		/*Classroom c1 = new Classroom("A5001", 60);
		Classroom c2 = new Classroom("A5002", 60);
		Classroom c3 = new Classroom("A5E01", 60);
		Classroom c4 = new Classroom("A5E02", 60);

		c1.addExtra("Projector");
		c2.addExtra("Projector");
		c3.addExtra("Projector");
		c4.addExtra("Projector");

		c3.addExtra("Fans");
		c4.addExtra("Fans");

		Classrooms.getInstance().addClassroom(c1);
		Classrooms.getInstance().addClassroom(c2);
		Classrooms.getInstance().addClassroom(c3);
		Classrooms.getInstance().addClassroom(c4);
	    */

	}
}
