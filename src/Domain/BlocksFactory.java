package Domain;

public class BlocksFactory {

	public static boolean produce() {
		Blocks.getInstance().add("No class between time frame", Block::noClassOnDayBetweenSHEH);
		Blocks.getInstance().add("No overlapping classroom and time", Block::noTwoClassesAtSameHourSameClassroom);
		return true;
	}
}
