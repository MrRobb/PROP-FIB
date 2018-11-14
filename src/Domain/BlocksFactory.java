package Domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class BlocksFactory {

	public static boolean produce() {

		ArrayList<Pair<String,java.lang.Class>> arrB1 = new ArrayList<Pair<String,java.lang.Class>>();
		arrB1.add(Pair<"Start hour", Integer.class>);
		arrB1.add(Integer.class);;
		Block b1 = new Block(Functions::noClassOnDayBetweenSHEH, arrB1);
		Blocks.getInstance().add("no class between start hour and end hour", b1);

		ArrayList<java.lang.Class> arrB2 = new ArrayList<java.lang.Class>();
		Block b2 = new Block(Functions::noTwoClassesAtSameHourSameClassroom, arrB2);
		Blocks.getInstance().add("no overlapping hour and classroom", b2);

		ArrayList<java.lang.Class> arrB3 = new ArrayList<java.lang.Class>();
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
		Blocks.getInstance().add("no overlapping between group and its subgroup", b6);

		return true;
	}

}
