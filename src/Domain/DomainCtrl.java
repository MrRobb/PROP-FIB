package Domain;
import java.util.Scanner;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

public class DomainCtrl {

	private static DomainCtrl instance = null;

	public static void main(String[] args) {
		System.out.println("Introduce the type of user:" + "\n" + "1. Normal user" + "\n" + "2. Administrator");
        int typeOfUser = System.in.read();
        //DomainCtrl.getInstance().generateSchedule();
	}

	public static DomainCtrl getInstance() {
		if (instance == null) {
			instance = new DomainCtrl();
		}
		return instance;
	}

	/*public boolean generateSchedule() {

		DegreesFactory.produce();
		ClassroomsFactory.produce();
		DateTimesFactory.produce();

		return true;
	}*/

}
