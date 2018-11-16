package Domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class BlocksFactory {

	public static boolean produce() {

		ArrayList<Pair<String, java.lang.Class>> argsTemplate1 = new ArrayList<>();
		argsTemplate1.add(new Pair<>("WeekDay", String.class));
		argsTemplate1.add(new Pair<>("Start hour", Integer.class));
		argsTemplate1.add(new Pair<>("End hour", Integer.class));
		Block blockTemplate1 = new Block(Functions::noClassOnDayBetweenSHEH, argsTemplate1);
		Blocks.getInstance().add("No class between [Start hour] and [End hour] on [WeekDay]", blockTemplate1);

		ArrayList<Pair<String, java.lang.Class>> argsTemplate2 = new ArrayList<>();
		Block blockTemplate2 = new Block(Functions::noTwoClassesAtSameHourSameClassroom, argsTemplate2);
		Blocks.getInstance().add("No overlapping hour and classroom", blockTemplate2);

		/*ArrayList<java.lang.Class> arrB3 = new ArrayList<java.lang.Class>();
		arrB3.add(Integer.class);
		arrB3.add(Integer.class);;
		Block b3 = new Block(Functions::everyClassBetweenStartAndEndHour, arrB3);
		Blocks.getInstance().add("every class must be between start hour and end hour", b3);

		ArrayList<java.lang.Class> arrB4 = new ArrayList<java.lang.Class>();
		arrB4.add(String.class);
		arrB4.add(String.class);
		Block b4 = new Block(Functions::groupOfATypeMustBeSetBefore, arrB4);
		Blocks.getInstance().add("all the group of type A must be set before the others of the same subject", b4);

		ArrayList<java.lang.Class> arrB5 = new ArrayList<java.lang.Class>();
		Block b5 = new Block(Functions::laboratoryPCgroupHasPCs, arrB5);
		Blocks.getInstance().add("all laboratory group has PC", b5);

		ArrayList<java.lang.Class> arrB6 = new ArrayList<java.lang.Class>();
		Block b6 = new Block(Functions::noGroupSubGroupOverlapped, arrB6);
		Blocks.getInstance().add("no overlapping between group and its subgroup", b6);*/

		return true;
	}

}
