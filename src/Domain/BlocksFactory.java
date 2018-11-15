package Domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class BlocksFactory {

	public static boolean produce() {

		ArrayList<Pair<String,java.lang.Class>> arrB1 = new ArrayList<Pair<String,java.lang.Class>>();
		arrB1.add(new Pair<>("WeekDay (first letter in capital letter)", String.class));
		arrB1.add(new Pair<>("Start hour", Integer.class));
		arrB1.add(new Pair<>("End hour", Integer.class));
		Block b1 = new Block(Functions::noClassOnDayBetweenSHEH, arrB1);
		Blocks.getInstance().add("No class between start hour SH and end hour EH", b1);

		ArrayList<Pair<String,java.lang.Class>> arrB2 = new ArrayList<Pair<String,java.lang.Class>>();
		Block b2 = new Block(Functions::noTwoClassesAtSameHourSameClassroom, arrB2);
		Blocks.getInstance().add("No overlapping at the same hour and classroom", b2);

		ArrayList<Pair<String,java.lang.Class>> arrB3 = new ArrayList<Pair<String,java.lang.Class>>();
		arrB3.add(new Pair<>("Start hour",Integer.class));
		arrB3.add(new Pair<>("End hour", Integer.class));
		Block b3 = new Block(Functions::everyClassBetweenStartAndEndHour, arrB3);
		Blocks.getInstance().add("Every class must be between start hour SH and end hour EH", b3);

		ArrayList<Pair<String,java.lang.Class>> arrB4 = new ArrayList<Pair<String,java.lang.Class>>();
		arrB4.add(new Pair<>("Subject",String.class));
		arrB4.add(new Pair<>("Type of group",String.class));
		Block b4 = new Block(Functions::groupOfATypeMustBeSetBefore, arrB4);
		Blocks.getInstance().add("All the group of subject S and a type T must be set before the groups of the other types", b4);

		ArrayList<Pair<String,java.lang.Class>> arrB5 = new ArrayList<Pair<String,java.lang.Class>>();
		arrB5.add(new Pair<>("Type of group",String.class));
		Block b5 = new Block(Functions::allGroupMustHaveType, arrB4);
		Blocks.getInstance().add("All the group must have a type T", b5);

		ArrayList<Pair<String,java.lang.Class>> arrB6 = new ArrayList<Pair<String,java.lang.Class>>();
		arrB6.add(new Pair<>("Extra",String.class));
		Block b6 = new Block(Functions::allClassroomMustHaveExtra, arrB4);
		Blocks.getInstance().add("All the classroom must have a certain extra E", b6);

		ArrayList<Pair<String,java.lang.Class>> arrB7 = new ArrayList<Pair<String,java.lang.Class>>();
		Block b7 = new Block(Functions::noGroupSubGroupOverlapped, arrB7);
		Blocks.getInstance().add("No overlapping between group and its subgroup", b7);

		ArrayList<Pair<String,java.lang.Class>> arrB8 = new ArrayList<Pair<String,java.lang.Class>>();
		Block b8 = new Block(Functions::noTwoSameSemesterGroupsOverlappedWithSameName, arrB8);
		Blocks.getInstance().add("No overlapping between groups of the same level and the same name", b8);

		ArrayList<Pair<String,java.lang.Class>> arrB9 = new ArrayList<Pair<String,java.lang.Class>>();
		arrB9.add(new Pair<>("Subject",String.class));
		arrB9.add(new Pair<>("Hour",Integer.class));
		Block b9 = new Block(Functions::subjectMustBeSetAlwaysAfterHour, arrB8);
		Blocks.getInstance().add("Subject S must be set after hour H", b9);

		return true;
	}

}
