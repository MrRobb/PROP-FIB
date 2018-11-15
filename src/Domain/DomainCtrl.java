package Domain;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.System.exit;

public class DomainCtrl {

	private static DomainCtrl instance = null;

	public static void main(String[] args) {
        produceFactory();
		Scanner user_input = new Scanner(System.in);
		while (true) {
            System.out.println("Introduce the type of user(num):" + "\n" + "1. Normal user" + "\n" + "2. Administrator" + "\n" + "3. To exit");
            int user_type = user_input.nextInt();
            if (user_type == 1) {
                normal_user();
            } else if (user_type == 2) {
                admin();
            } else if (user_type == 3) {
                break;
            } else System.out.println("Enter a valid number!");
        }
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

        System.out.println("Choose what you want to do:" + "\n" +
		        "1. Generate schedules" + "\n" +
		        "2. Erase existing schedules" + "\n" +
		        "3. Modify restrictions");

        int action = user_input.nextInt();
        switch (action) {
            case (1) :  generateSchedule();
                break;
            case (2) :  eraseSchedule();
                break;
            case (3) :  //modifyRestrictions();
                        modifyApplied();
                break;

                default :  optionError();
        }
    }

	public static void normal_user() {
        System.out.println("Now you are normal user");
	    // consultar los horarios en capa de datos
    }


	public static void generateSchedule() {
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

    /*
    // We don't use this function, the case 1 is not implemented, the user can't create a restriction from zero
    public static void modifyRestrictions() {
	    Scanner user_input = new Scanner(System.in);
        System.out.println("1. Add new ones" + "\n" + "2. Erase existing ones" + '\n' + "3. Choose which to apply");
        int restr_action = user_input.nextInt();
        switch (restr_action) {
            case (1) :  addNewAvailableRestrictions();
                break;
            case (2) :  eraseAvailableRestrictions();
                break;
            case (3) : modifyApplied();
                break;
                default: optionError();
        }

    }

    public static void addNewAvailableRestrictions(){
        // Not implemented functionality
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
    }*/

    public static void modifyApplied() {
	    Scanner user_input = new Scanner(System.in);
        // Show already applieds

	    Set<String> applieds = Restrictions.getInstance().getAppliedRestrictionNames();
	    if (applieds.isEmpty()) System.out.println("There are no applied restrictions");
	    else {
            System.out.println("These are the applied restrictions: ");
            for (String s : applieds) System.out.println(s + " || ");
        }

        System.out.println("What do you want to do?" + "\n"
                + "1. Erase already applied restrictions" + "\n"
                + "2. Add/create more from the available ones");

	    int option = user_input.nextInt();
	    switch (option) {
            case (1) :  eraseApplieds();
            break;
            case (2) :  addAppliedFromAvailable();
            break;
            default: optionError();
        }

    }

    public static void eraseApplieds() {
        Scanner user_input = new Scanner(System.in);
        Set<String> applieds = Restrictions.getInstance().getAppliedRestrictionNames();
        if (applieds.isEmpty()) System.out.println("There are no applied restrictions to erase");
        else {
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            int i = 1;
            for (String s : applieds) {
                System.out.println(i + ". " + s);
                map.put(i, s);
                i++;
            }
            System.out.println("Enter the number of the restrictions you want to eliminate, -1 to finish");
            int restrID = user_input.nextInt();
            while (restrID != -1) {
                if (Restrictions.getInstance().deleteApplied(map.get(restrID)))
                    System.out.println("Restriction " + restrID + " eliminated successfully from the applied ones!, enter the next restriction to eliminate or -1 to stage change");
                else {
                    if (Restrictions.getInstance().getAppliedRestrictionNames().isEmpty()) System.out.println("There are no more applied restriction to erase");
                    else System.out.println("Error on elimination, try a valid number");
                }
                restrID = user_input.nextInt();
            }
            System.out.println("This is the resulting applied restrictions:");
            Set<String> applieds_res = Restrictions.getInstance().getAppliedRestrictionNames();
            for (String s : applieds_res) System.out.println(s);
        }
    }

    public static void addAppliedFromAvailable() {
	    Scanner user_input = new Scanner(System.in);
        // Show all availables
        System.out.println("Available restrictions:");
        Set<String> availables = Restrictions.getInstance().getAvailableRestriccionsNames();
        for (String s: availables) System.out.print(s + " || ");
        // Chose of the available ones
        System.out.println("Enter the number of the restrictions you want to apply from the available ones, enter -1 to stage");
        int i = 1;
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (String s: availables) {
            System.out.println(i + ". " + s);
            map.put(i, s);
            ++i;
        }
        int restrID = user_input.nextInt();
        boolean[] already_applied = new boolean[map.size()];
        while (restrID != -1) {
            Restriction available = Restrictions.getInstance().getAvailableRestriction(map.get(restrID));
            if (available == null) System.out.println("Try again, and enter a valid number of restriction!");
            else if (available.getTotalNumOfArgs() == 0 && already_applied[restrID]) System.out.println("You have already applied the restriction number " + restrID + " which has no argument");
            else {
                Restriction new_restr = available.clone();
                for (int blockIndex = 0; blockIndex < new_restr.getNumberOfBlocks(); blockIndex++ ) {
                    ArrayList<Object> params = new ArrayList<>();
                    for (int j = 0; j < new_restr.getSizeArgsBlock(blockIndex); j++) {
                        String param = new_restr.askParameter(blockIndex,j);
                        System.out.println("Enter the parameter" + " " + param);
                        String input_param = user_input.next();
                        while (!new_restr.checkParam(blockIndex, j, input_param)) {
                            System.out.println("Enter a valid parameter!");
                            input_param = user_input.next();
                        }
                        params.add(input_param);
                    }
                    new_restr.setParameter(blockIndex, params.toArray());
                }
                Restrictions.getInstance().addApplied(new_restr);
                already_applied[restrID] = true;
                System.out.println("Restriction " + restrID + " applied, enter the next restriction to apply or -1 to stage change");
            }
            restrID = user_input.nextInt();
        }
        System.out.println("Change staged!");
        Set<String> applieds = Restrictions.getInstance().getAppliedRestrictionNames();
        if (applieds.isEmpty()) System.out.println("No applied restriction");
        else {
            System.out.println("This is the resulting applied restrictions:");
            for (String s : applieds) System.out.println(s);
        }
    }

    public static void optionError() {
	    System.out.println("Please enter a valid option (number)!");
    }

    public static boolean produceFactory() {

        try {
            Object obj = new JSONParser().parse(new FileReader("json/degreeReal.json"));
            JSONArray ja = (JSONArray) obj;
            JSONObject jo = (JSONObject) ja.get(0);
            JSONArray cls = (JSONArray) jo.get("classrooms");
            JSONArray subjts = (JSONArray) jo.get("subjects");
            String degname = (String) jo.get("name");
            Integer ncredits = (int) (long) jo.get("credits");
            JSONArray gtypes = (JSONArray) jo.get("groupTypes");
            JSONArray grps = (JSONArray) jo.get("groups");


            SubjectsFactory.produce(subjts);
            System.out.println("*************************");
            System.out.println(Subjects.getInstance().size() + " subjects generated:");
            ArrayList<String> subjs = Subjects.getInstance().getAllKeys();
            for (String subID : subjs) {
                System.out.println(subID);
            }
            System.out.println("*************************");

            DegreesFactory.produce(degname, ncredits, gtypes, grps);
            System.out.println("New degree created: " + Degree.getInstance().getName());
            System.out.println("*************************");
            System.out.println(Groups.getInstance().size() + " groups generated:");
            LinkedHashMap<Integer, Group> allGroups = Groups.getInstance().get();
            for (Map.Entry<Integer, Group> g : allGroups.entrySet()) {
                System.out.println(g.getValue().getSubject().getName() + " " + g.getValue().getName() + " " + g.getValue().getTypes());
            }
            System.out.println("*************************");

            ClassroomsFactory.produce(cls);
            System.out.println(Classrooms.getInstance().size() + " classrooms produced:");
            ArrayList<String> rooms = Classrooms.getInstance().getAllKeys();
            for (String classID : rooms) {
                System.out.println(classID);
            }
            System.out.println("*************************");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateTimesFactory.produce();
        BlocksFactory.produce();
        RestrictionsFactory.produce();
        return true;
    }

}
