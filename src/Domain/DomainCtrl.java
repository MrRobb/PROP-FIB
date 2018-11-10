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
	    // eliminar horarios de la capa de d    atos
        return true;
    }

    public static void modifyRestrictions() {
	    Scanner user_input = new Scanner(System.in);
        System.out.println("1. Add new ones" + "\n" + "2. Erase existing ones" + "3. Choose which to apply");
        int restr_action = user_input.nextInt();
        switch (restr_action) {
            case (1) :  addNewAvailableRestrictions();
                break;
            case (2) :  eraseAvailableRestrictions();
                break;
            case (3) : whichToApply();
                break;
                default: optionError();
        }
    }

    public static void addNewAvailableRestrictions(){
        
    }

    public static void eraseAvailableRestrictions() {
        Scanner user_input = new Scanner(System.in);
        // Chose of the available ones
        System.out.println("Enter the number of the restrictions you want to erase, enter -1 to stage");
        Set<String> all_restrictions = Restrictions.getInstance().getAvailableRestriccionsNames();
        int i = 1;
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (String s: all_restrictions) {
            System.out.println(i + ". " + s);
            map.put(i, s);
        }
        int restrToErase = user_input.nextInt();
        while (restrToErase != -1) {
            Boolean b = Restrictions.getInstance().deleteAvailable(map.get(restrToErase));
            System.out.println(map.get(restrToErase) + "deleted from available restrictions");
            restrToErase = user_input.nextInt();
        }
        System.out.println("Change staged!");
    }

    public static void whichToApply() {
	    Scanner user_input = new Scanner(System.in);
	    // Show applied ones
	    Set<String> applyeds = Restrictions.getInstance().getAppliedRestrictionNames();
	    System.out.println("Already applied restrictions:");
	    for (String s: applyeds) System.out.print(s + " || ");
	    Set<String> all_restrictions = Restrictions.getInstance().getAvailableRestriccionsNames();
	    // Chose of the available ones
	    System.out.println("Enter the number of the restrictions you want to apply from the available ones, enter -1 to stage");
	    Restrictions.getInstance().clearApplied();
	    int i = 1;
	    HashMap<Integer, String> map = new HashMap<Integer, String>();
	    for (String s: all_restrictions) {
	        System.out.println(i + ". " + s);
	        map.put(i, s);
        }
	    int restrToApply = user_input.nextInt();
	    while (restrToApply != -1) {
	        Restriction restriction = Restrictions.getInstance().getAvailableRestriction(map.get(restrToApply));
            Restrictions.getInstance().addApplied(map.get(restrToApply), new  ArrayList<Object[]>());
            restrToApply = user_input.nextInt();
        }
        System.out.println("Change staged!");
    }

    public static void optionError() {
	    System.out.println("Please enter a valid option (number)!");
    }

}
