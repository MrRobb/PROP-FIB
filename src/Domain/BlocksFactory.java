package Domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class BlocksFactory {

	public static boolean produce() {

		ArrayList<Pair<String,java.lang.Class>> arrB1 = new ArrayList<>();
		arrB1.add(new Pair<>("Start hour (ex: 8)", Integer.class));
		arrB1.add(new Pair<>("End hour (ex: 16)", Integer.class));
		arrB1.add(new Pair<>("WeekDay (ex: Monday)", DateTime.WeekDay.class));
		Block b1 = new Block(Functions::noClassOnDayBetweenSHEH, arrB1);
		Blocks.getInstance().add("No class between [StartHour] and [EndHour] on [WeekDay]", b1);

		ArrayList<Pair<String,java.lang.Class>> arrB2 = new ArrayList<>();
		Block b2 = new Block(Functions::noTwoClassesAtSameHourSameClassroom, arrB2);
		Blocks.getInstance().add("No overlapping at the same hour and classroom", b2);

		ArrayList<Pair<String,java.lang.Class>> arrB3 = new ArrayList<>();
		arrB3.add(new Pair<>("Start hour (ex: 13)",Integer.class));
		arrB3.add(new Pair<>("End hour (ex: 16)", Integer.class));
		Block b3 = new Block(Functions::everyClassBetweenStartAndEndHour, arrB3);
		Blocks.getInstance().add("Every class must be between [StartHour] and [EndHour]", b3);

		ArrayList<Pair<String,java.lang.Class>> arrB4 = new ArrayList<>();
		arrB4.add(new Pair<>("Subject (ex: " + Subjects.getInstance().getAllKeys().get(0) + ")", String.class));
		arrB4.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")", String.class));
		Block b4 = new Block(Functions::groupOfATypeMustBeSetBefore, arrB4);
		Blocks.getInstance().add("Every group of [Subject] with [Type] must be set before the other groups of the same subject with other types", b4);


		ArrayList<Pair<String,java.lang.Class>> arrB6 = new ArrayList<>();
		arrB6.add(new Pair<>("Extra (ex: " + Classrooms.getInstance().getExtras().first() + ")",String.class));
		arrB6.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")",String.class));
		Block b6 = new Block(Functions::allGroupMustHaveClassromWithExtra, arrB6);
		Blocks.getInstance().add("To a class with [Extra] must be assigned a group of [Type]", b6);


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
		Blocks.getInstance().add("All groups must fit in a classroom", b10);

		ArrayList<Pair<String,java.lang.Class>> arrB11 = new ArrayList<>();
		arrB11.add(new Pair<>("Max number of classrooms (ex: 15)",Integer.class));
		Block b11 = new Block(Functions::atMostNClassroomsCanBeUsed, arrB11);
		Blocks.getInstance().add("At most [Int] classrooms can be used", b11);

		ArrayList<Pair<String,java.lang.Class>> arrB12 = new ArrayList<>();
		arrB12.add(new Pair<>("Max number of groups of the given subject and given type", Integer.class));
		arrB12.add(new Pair<>("Subject (ex: " + Subjects.getInstance().getAllKeys().get(0) + ")", String.class));
		arrB12.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")", String.class));
		Block b12 = new Block(Functions::atMostNGroupsOfSubjectOfTypeA, arrB12);
		Blocks.getInstance().add("At most [Int] groups of [Subject] of [Type] ", b12);

		ArrayList<Pair<String,java.lang.Class>> arrB13 = new ArrayList<>();
		arrB13.add(new Pair<>("Classroom (ex: " + Classrooms.getInstance().getAllKeys().get(0) + ")", String.class));
		Block b13 = new Block(Functions::notUsedClassroom, arrB13);
		Blocks.getInstance().add("[Classroom] can't be used", b13);

		ArrayList<Pair<String,java.lang.Class>> arrB14 = new ArrayList<>();
		arrB14.add(new Pair<>("Subject (ex: " + Subjects.getInstance().getAllKeys().get(0) + ")", String.class));
		Block b14 = new Block(Functions::notAsignedSubject, arrB14);
		Blocks.getInstance().add("[Subject] can't be used", b14);

		ArrayList<Pair<String,java.lang.Class>> arrB15 = new ArrayList<>();
		arrB15.add(new Pair<>("Subject (ex: " + Subjects.getInstance().getAllKeys().get(0) + ")", String.class));
		arrB15.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")", String.class));
		arrB15.add(new Pair<>("Classroom (ex: " + Classrooms.getInstance().getAllKeys().get(0) + ")", String.class));
		Block b15 = new Block(Functions::subjectCantBeAssignedToClassroom, arrB15);
		Blocks.getInstance().add("[Subject] of [Type] can't be in [Classroom]", b15);

		ArrayList<Pair<String,java.lang.Class>> arrB16 = new ArrayList<>();
		arrB16.add(new Pair<>("Max number of total hour (ex: 15)",Integer.class));
		Block b16 = new Block(Functions::noOverpassNHourTotal, arrB16);
		Blocks.getInstance().add("At most [Int] different hours in the schedule", b16);

		ArrayList<Pair<String,java.lang.Class>> arrB17 = new ArrayList<>();
		arrB17.add(new Pair<>("Max number of hour in a day(ex: 15)",Integer.class));
		Block b17 = new Block(Functions::noOverpassNHourAllDays, arrB17);
		Blocks.getInstance().add("At most [Int] hours in a day", b17);

		ArrayList<Pair<String,java.lang.Class>> arrB18 = new ArrayList<>();
		arrB18.add(new Pair<>("Subject (ex: " + Subjects.getInstance().getAllKeys().get(0) + ")", String.class));
		arrB18.add(new Pair<>("Type of group (ex: " + Degree.getInstance().getTypeOfGroups().get(0) + ")", String.class));
		arrB18.add(new Pair<>("Extra (ex: " + Classrooms.getInstance().getExtras().first() + ")",String.class));
		Block b18 = new Block(Functions::noGroupOfSubjectOfTypeWithExtra, arrB15);
		Blocks.getInstance().add("[Subject] of [Type] can't be in classroom with [Extra]", b18);

		ArrayList<Pair<String,java.lang.Class>> arrB19 = new ArrayList<>();
		arrB19.add(new Pair<>("Start hour (ex: 13)",Integer.class));
		arrB19.add(new Pair<>("End hour (ex: 16)", Integer.class));
		arrB19.add(new Pair<>("Min capacity (ex: 40)",Integer.class));
		Block b19 = new Block(Functions::noLessCapacityOfGroupsBetweenSHEH, arrB19);
		Blocks.getInstance().add("No class between [StartHour] and [EndHour] with less than [Int]", b19);

		ArrayList<Pair<String,java.lang.Class>> arrB20 = new ArrayList<>();
		arrB20.add(new Pair<>("The max free places in a classroom (ex: 5)",Integer.class));
		Block b20 = new Block(Functions::noMoreFreeCapacityBetweenGroupAndClassroom, arrB20);
		Blocks.getInstance().add("Difference between classroom capacity and group capacity no higher than [Int]", b20);



		return true;
	}

}
