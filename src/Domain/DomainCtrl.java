package Domain;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DomainCtrl {

	private static DomainCtrl instance = null;
    private Scanner commandLine = new Scanner(System.in);

	public static void main(String[] args) {
	    DomainCtrl.getInstance().menu();
	}

	private DomainCtrl() {}

    public static DomainCtrl getInstance() {
        if (instance == null) {
            instance = new DomainCtrl();
        }
        return instance;
    }

    public void menu() {

        // Load info from JSON
        produceFactory();

        while (true) {

            System.out.println("Select the type of user (Number):");
            System.out.println("0. Exit");
            System.out.println("1. User");
            System.out.println("2. Administrator");

            int option = getInputAsInt(0, 2);
            System.out.println();

            switch (option) {

                case 0:
                    return;

                case 1:
                    System.out.println("Logged as: User\n");
                    user();
                    System.out.println("Logged out\n");
                    break;

                case 2:
                    System.out.println("Logged as: Administrator\n");
                    admin();
                    System.out.println("Logged out\n");
                    break;

                default:
                    optionError();
                    break;
            }
        }
    }

    public void optionError() {
        System.out.println("Please enter a valid option (number)\n");
    }

    private int getInputAsInt(int min, int max) {

        while (true) {
            try {
                int num = commandLine.nextInt();

                while (min > num || num > max) {
                    System.out.println("Please, enter a valid option (number between " + min + " and " + max + ")");
                    num = commandLine.nextInt();
                }

                return num;

            } catch (Exception e) {
                commandLine.next();
                System.out.println("It should be a number between " + min + " and " + max);
            }
        }
    }

    private String getInput() {
        return commandLine.next();
    }

    public void admin() {

	    while (true) {
            System.out.println("Choose what you want to do:");
            System.out.println("0. Exit");
            System.out.println("1. Generate schedules");
            System.out.println("2. Modify restrictions to apply");
            System.out.println("3. Show saved schedules");
            System.out.println("4. Delete all saved schedules");

            int option = getInputAsInt(0, 4);
            System.out.println();

            switch (option) {

                case 0:
                    return;

                case 1:
                    generateSchedule();
                    break;

                case 2:
                    selectRestrictions();
                    break;

                case 3:
                    showSavedSchedules();
                    break;

                case 4:
                    clearSavedSchedules();
                    break;

                default:
                    optionError();
                    break;
            }
        }
    }

	public void user() {

	    while (true) {
            System.out.println("Choose what you want to do:");
            System.out.println("0. Exit");
            System.out.println("1. Show schedules");

            int option = getInputAsInt(0, 1);
            System.out.println();

            switch (option) {

                case 0:
                    return;

                case 1:
                    showSavedSchedules();
                    break;

                default:
                    optionError();
                    break;
            }
        }
    }

	public void generateSchedule() {

        System.out.println("Starting to generate...");

	    // Generate
		TreeSet<Schedule> schedules = Generator.getInstance().generate();

        System.out.println("Finished generating");
        System.out.println();

        if (schedules.isEmpty()) {
            System.out.println("Unable to generate any schedule with the actual restrictions\n");
            return;
        }

        int i = 1;
        for (Schedule s : schedules) {

            // Show schedule
            System.out.println("Schedule " + i);
            System.out.println(s.toString());

            // Ask for saving
            System.out.println("Do you want to save this schedule?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            // Get yes / no
            int option = getInputAsInt(1, 2);
            System.out.println();

            if (option == 1) {
                if (saveSchedule(s)) {
                    System.out.println("Schedule saved successfully");
                }
                else {
                    System.out.println("Failed while saving!");
                }
            }
            
        }

	}

	public void showSavedSchedules() {

	    int nSchedules = Schedules.getInstance().size();
	    if (nSchedules == 1) {
            System.out.println("There is " + nSchedules + " schedule saved");
        }
        else {
            System.out.println("There are " + nSchedules + " schedules saved");
        }
        System.out.println();

	    for (int i = 0; i < nSchedules; i++) {
	        System.out.println("Showing schedule nº " + i);
            System.out.println(Schedules.getInstance().get(i).toString());
        }
    }

	public boolean saveSchedule(Schedule s) {
	    return Schedules.getInstance().addSchedule(s);
    }

	public boolean clearSavedSchedules() {

        // Ask for saving
        System.out.println("Are you sure you want to delete ALL saved schedules?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        // Get yes / no
        int option = getInputAsInt(1, 2);
        System.out.println();

        if (option == 1) {
            return Schedules.getInstance().clear();
        }

        return false;
    }

    public void selectRestrictions() {

        while (true) {

            // Show already applieds
            System.out.println("Already applied restrictions:");
            Set<String> applieds = Restrictions.getInstance().getAppliedRestrictionNames();

            if (applieds.isEmpty()) {
                System.out.println("You are not applying any restriction");
            }

            for (String s : applieds) {
                System.out.println(s);
            }
            System.out.println();

            // Ask for action

            System.out.println("What do you want to do?");
            System.out.println("0. Exit");
            System.out.println("1. Add restriction");
            System.out.println("2. Remove restriction");

            int option = getInputAsInt(0, 2);
            System.out.println();

            switch (option) {
                case 0:
                    return;

                case 1:
                    addApplied();
                    break;

                case 2:
                    eraseApplied();
                    break;

                default:
                    optionError();
                    break;
            }
        }
    }

    public void eraseApplied() {

        // Show already applieds
        System.out.println("Already applied restrictions:");
        Set<String> applieds = Restrictions.getInstance().getAppliedRestrictionNames();

        if (applieds.isEmpty()) {
            System.out.println("You are not applying any restriction");
            System.out.println("Therefore you can not delete any");
            return;
        }

        System.out.println("0. Exit");

        int i = 1;
        HashMap<Integer, String> map = new HashMap<>(applieds.size());
        for (String s: applieds) {
            System.out.println(i + ". " + s);
            map.put(i, s);
            i++;
        }
        System.out.println();

        // Ask for action
        System.out.println("Which one do you want to delete?");

        int restrictionID = getInputAsInt(0, applieds.size());
        if (Restrictions.getInstance().deleteApplyed(map.get(restrictionID))) {
            System.out.println("Eliminated successfully!");
        }
        else {
            System.out.println("Error while deleting");
        }

        System.out.println("This is the resulting applied restrictions:");
        Set<String> applieds_res = Restrictions.getInstance().getAppliedRestrictionNames();

        i = 0;
        for (String s: applieds_res) {
            System.out.println(i + ". " + s);
        }
        System.out.println();
    }

    public void addApplied() {

        // Show applied
        System.out.println("Already applied restrictions:");
        Set<String> applied = Restrictions.getInstance().getAppliedRestrictionNames();

        // No applied restrictions
        if (applied.isEmpty()) {
            System.out.println("You are not applying any restriction");
        }

        // Iterate applied
        for (String s: applied) {
            System.out.println(s);
        }
        System.out.println();

        // Show available
        System.out.println("Available restrictions:");
        Set<String> availables = Restrictions.getInstance().getAvailableRestrictionsNames();

        // No restrictions
        if (availables.isEmpty()) {
            System.out.println("There are no available restrictions");
            System.out.println("Therefore you can not add any");
            System.out.println();
            return;
        }

        System.out.println("0. Exit");

        // Iterate available
        int i = 1;
        HashMap<Integer, String> map = new HashMap<>();
        for (String s : availables) {
            System.out.println(i + ". " + s);
            map.put(i, s);
            i++;
        }
        System.out.println();

        // Ask for action
        System.out.println("Which one do you want to add?");

        // Select restriction
        int restrID = getInputAsInt(0, availables.size());
        System.out.println();

        if (restrID == 0) {
            return;
        }

        Restriction available = Restrictions.getInstance().getAvailableRestriction(map.get(restrID));

        if (available != null) {

            // Available -> Applied
            Restriction newRestriction = available.clone();

            // Ask parameters if any
            System.out.println("This restriction has " + newRestriction.getNumberOfTotalParams() + " parameters");

            // For each block
            for (int blockIndex = 0; blockIndex < newRestriction.getNumberOfBlocks(); blockIndex++) {

                ArrayList<Object> params = new ArrayList<>();

                // Ask for every parameter
                for (int paramIndex = 0; paramIndex < newRestriction.getSizeArgsBlock(blockIndex); paramIndex++) {

                    // Get type of the param
                    String paramTemplate = newRestriction.askParameter(blockIndex, paramIndex);
                    System.out.println("Enter the parameter" + " " + paramTemplate);

                    // Check that param in String can be casted as correct Type
                    String argGiven = getInput();

                    while (!newRestriction.checkParam(blockIndex, paramIndex, argGiven)) {
                        System.out.println("Enter a valid parameter (" + paramTemplate + ")");
                        argGiven = getInput();
                    }

                    // Add to params array (to update later)
                    params.add(argGiven);
                }

                newRestriction.setParameter(blockIndex, params.toArray());
            }
            if (Restrictions.getInstance().addApplied(newRestriction)) {
                System.out.println("New restriction applied\n");
            }
            else {
                System.out.println("Error: Unable to add restriction (already existing restriction)\n");
            }
        }
    }

    public boolean produceFactory() {

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
