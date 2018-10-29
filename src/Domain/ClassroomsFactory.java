package Domain;

class ClassroomsFactory {

	public static boolean produce() {

		Classrooms.getInstance().clear();

		Classroom c1 = new Classroom("A5001", 60);
		Classroom c2 = new Classroom("A5002", 60);
		Classroom c3 = new Classroom("A5E01", 60);
		Classroom c4 = new Classroom("A5E02", 60);

		c1.addExtra("Projector");
		c2.addExtra("Projector");
		c3.addExtra("Projector");
		c4.addExtra("Projector");

		c3.addExtra("Fans");
		c4.addExtra("Fans");

		Classrooms.getInstance().addClassroom(c1);
		Classrooms.getInstance().addClassroom(c2);
		Classrooms.getInstance().addClassroom(c3);
		Classrooms.getInstance().addClassroom(c4);

		return true;
	}
}
