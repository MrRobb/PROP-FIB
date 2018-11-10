package Domain;
import java.util.*;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

public class DomainCtrl {

	private static DomainCtrl instance = null;

	public static void main(String[] args) {
		Scanner user_input = new Scanner(System.in);
        System.out.println("Introduce the type of user(num):" + "\n" + "1. Normal user" + "\n" + "2. Administrator");
        int user_type = user_input.nextInt();
        if (user_type == 1) {
            normal_user();
        }
        else if (user_type == 2) {
            admin();
        }
        else System.out.println("Enter a valid number!");
	}

	public static DomainCtrl getInstance() {
		if (instance == null) {
			instance = new DomainCtrl();
		}
		return instance;
	}

    public static void admin() {
	    Scanner user_input = new Scanner(System.in);
        System.out.println("Now you are administrator");
        System.out.println("Choose what you want to do:" + "\n" + "1. Generate schedules" + "\n" + "2. Erase existing schedules" + "\n" + "3. Modify restrictions");
        int action = user_input.nextInt();
        switch (action) {
            case (1) :  generateSchedule();
                break;
            case (2) :  eraseSchedule();
                break;
            case (3) :  modifyRestrictions();
                break;

                default :  optionError();
        }
    }

	public static void normal_user() {
        System.out.println("Now you are normal user");
	    // consultar los horarios en capa de datos
    }


	public static void generateSchedule() {
		SubjectsFactory.produce();
		DegreesFactory.produce();
		ClassroomsFactory.produce();
		DateTimesFactory.produce();
		TreeSet<Schedule> schedules = Generator.getInstance().generate();
		Scanner user_input = new Scanner(System.in);
        if (schedules.isEmpty()) System.out.println("Enable to generate any schedule with the actual restrictions");
        else {
            int i = 1;
            for (Schedule s : schedules) {
                showSchedule(s, i);
                System.out.println("Do you want to save this schedule?" + "\n" + "1. Yes" + "\n" + "2. No");
                int saveOption = user_input.nextInt();
                if ( saveOption == 1) {
                    if (saveSchedule(s)) System.out.println("Schedule saved successfully!");
                    else System.out.println("Failed on saving!");
                }
                else if (saveOption == 2) {
                    System.out.println("Schedule skipped!");
                }
                else optionError();
                i++;
            }
        }
	}

	public static boolean showSchedule(Schedule s, int i) {
        System.out.println("Schedule " + i);
        String output = s.toString();
        System.out.println(output);
        return true;
    }

	public static boolean saveSchedule(Schedule s) {
	    // guardar horario a la capa de datos
        return true;
    }

	public static boolean eraseSchedule() {
	    // eliminar horarios de la capa de datos
        return true;
    }

    public static void modifyRestrictions() {
	    Scanner user_input = new Scanner(System.in);
        System.out.println("1. Add new ones" + "\n" + "2. Erase existing ones");
        int restr_action = user_input.nextInt();
        if (restr_action == 1) {

        }
        else if (restr_action == 2) {

        }
        else optionError();
    }

    public static void optionError() {
	    System.out.println("Please enter a valid option (number)!");
    }

}
