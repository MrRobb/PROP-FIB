package Domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class BlocksFactory {

	public static boolean produce() {

		ArrayList<Pair<String,java.lang.Class>> arrB1 = new ArrayList<>();
		arrB1.add(new Pair<>("WeekDay (ex: Monday)", DateTime.WeekDay.class));
		arrB1.add(new Pair<>("Start hour (ex: 8)", Integer.class));
		arrB1.add(new Pair<>("End hour (ex: 16)", Integer.class));
		Block b1 = new Block(Functions::noClassOnDayBetweenSHEH, arrB1);
		Blocks.getInstance().add("No class between [Start hour] and [End hour] on [WeekDay]", b1);

		ArrayList<Pair<String,java.lang.Class>> arrB2 = new ArrayList<>();
		Block b2 = new Block(Functions::noTwoClassesAtSameHourSameClassroom, arrB2);
		Blocks.getInstance().add("No overlapping at the same hour and classroom", b2);

		ArrayList<Pair<String,java.lang.Class>> arrB3 = new ArrayList<>();
		arrB3.add(new Pair<>("Start hour (ex: 13)",Integer.class));
		arrB3.add(new Pair<>("End hour (ex: 16)", Integer.class));
		Block b3 = new Block(Functions::everyClassBetweenStartAndEndHour, arrB3);
		Blocks.getInstance().add("Every class must be between [Start hour] and [End hour]", b3);

		ArrayList<Pair<String,java.lang.Class>> arrB4 = new ArrayList<>();
		arrB4.add(new Pair<>("Subject (ex: " + Subjects.getInstance().getAllKeys().get(0) + ")", String.class));
		arrB4.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")", String.class));
		Block b4 = new Block(Functions::groupOfATypeMustBeSetBefore, arrB4);
		Blocks.getInstance().add("Every group of [Subject] with [Type] must be set before the other groups of the same subject with other types", b4);

		ArrayList<Pair<String,java.lang.Class>> arrB5 = new ArrayList<>();
		arrB5.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")",String.class));
		Block b5 = new Block(Functions::allGroupMustHaveType, arrB5);
		Blocks.getInstance().add("All the groups must have a [Type]", b5);

		ArrayList<Pair<String,java.lang.Class>> arrB6 = new ArrayList<>();
		arrB6.add(new Pair<>("Extra (ex: " + Classrooms.getInstance().getExtras().first() + ")",String.class));
		arrB6.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")",String.class));
		Block b6 = new Block(Functions::allClassroomMustHaveExtra, arrB6);
		Blocks.getInstance().add("Every group of [type] must be assigned to a class with [Extra]", b6);


		ArrayList<Pair<String,java.lang.Class>> arrB7 = new ArrayList<>();
		Block b7 = new Block(Functions::noGroupSubGroupOverlapped, arrB7);
		Blocks.getInstance().add("No overlapping between group and its subgroups", b7);

		ArrayList<Pair<String,java.lang.Class>> arrB8 = new ArrayList<>();
		Block b8 = new Block(Functions::noTwoSameSemesterGroupsOverlappedWithSameName, arrB8);
		Blocks.getInstance().add("No overlapping between groups of the same level and the same name", b8);

		ArrayList<Pair<String,java.lang.Class>> arrB9 = new ArrayList<>();
		arrB9.add(new Pair<>("Subject (ex: " + Subjects.getInstance().getAllKeys().get(0) + ")", String.class));
		arrB9.add(new Pair<>("Hour (ex: 8)",Integer.class));
		Block b9 = new Block(Functions::subjectMustBeSetAlwaysAfterHour, arrB9);
		Blocks.getInstance().add("[Subject] must be set after [Hour]", b9);

		ArrayList<Pair<String,java.lang.Class>> arrB10 = new ArrayList<>();
		Block b10 = new Block(Functions::groupFitsInClassroom, arrB10);
		Blocks.getInstance().add("All groups capacity must fit in classroom", b10);

		ArrayList<Pair<String,java.lang.Class>> arrB11 = new ArrayList<>();
		arrB11.add(new Pair<>("Max number of classrooms ",Integer.class));
		Block b11 = new Block(Functions::atMostNClassroomsCanBeUsed, arrB11);
		Blocks.getInstance().add("At most n classrooms can be used", b11);

		return true;
	}

}
