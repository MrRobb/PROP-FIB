package Domain;

public class BlocksFactory {

	public static boolean produce() {

		Blocks.getInstance().add("No class between time frame", Block::noClassOnDayBetweenSHEH);
		Blocks.getInstance().add("No overlapping classroom and time", Block::noTwoClassesAtSameHourSameClassroom);
		Blocks.getInstance().add("Every class must be between two specified hours", Block::everyClassBetweenStartAndEndHour);
		Blocks.getInstance().add("Every group of the type specified must go before every other type of the subject", Block::groupOfATypeMustBeSetBefore);
		Blocks.getInstance().add("LaboratoryPC group must be assigned to a classroom with computers", Block::groupRequiresClassroomWithExtras);
		Blocks.getInstance().add("No group and subgroup overlapped", Block::noGroupSubGroupOverlapped);
		Blocks.getInstance().add("No two groups of the same level and same name overlapped", Block::noTwoSameSemesterGroupsOverlappedWithSameName);
		Blocks.getInstance().add("A group of a subject must be placed after an hour", Block::groupMustBeSetAlwaysAfterHour);

		return true;
	}

}
