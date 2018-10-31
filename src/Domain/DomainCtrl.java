package Domain;

public class DomainCtrl {

	private static DomainCtrl instance = null;

	public static void main(String[] args) {
//		Classroom c1 = new Classroom("A6202", 80);
//		Classroom c2 = new Classroom("A5E02");
//
//		System.out.println("Name: " + c1.getName());
//		System.out.println("Name: " + c1.getCapacity());
//		ArrayList<String> ex = c1.getExtras();
//
//		for (int i = 0; i < ex.size(); ++i) {
//			System.out.print(ex.get(i) + ", ");
//		}
//
//		System.out.println(c2.hasExtra("fans"));
//		c2.addExtra("fans");
//		System.out.println(c2.hasExtra("fans"));
//		System.out.println(c2.addExtra("fans"));
//
//		c2.addExtra("computers");
//		c2.addExtra("projector");
//		ex = c2.getExtras();
//
//		for (int i = 0; i < ex.size(); ++i) {
//			System.out.print(ex.get(i) + ", ");
//		}
//
//		c2.setCapacity(55);
//		System.out.println(c2.getCapacity());

		DomainCtrl.getInstance().generateSchedule();
	}

	public static DomainCtrl getInstance() {
		if (instance == null) {
			instance = new DomainCtrl();
		}
		return instance;
	}

	boolean generateSchedule() {

		DegreesFactory.produce();
		ClassroomsFactory.produce();
		DateTimesFactory.produce();

		return true;
	}

}
