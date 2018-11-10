package Domain;

public class BlocksFactory {

	public static boolean produce() {

		Blocks.getInstance().add("No class between time frame", Block::noClassOnDayBetweenSHEH);
		Blocks.getInstance().add("No overlapping classroom and time", Block::noTwoClassesAtSameHourSameClassroom);
		Blocks.getInstance().add("Every class must be between two specified hours", Block::everyClassBetweenStartAndEndHour);
		Blocks.getInstance().add("Every group of the type specified must go before every other type of the subject", Block::groupOfATypeMustBeSetBefore);

		return true;
	}
}
