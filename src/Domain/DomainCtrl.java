package Domain;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

        // Ask for JSON
        //if (!produceFactory()) {
        //    return;
        //}

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
                System.out.println();
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
            System.out.println("Showing schedule nº " + (i + 1));
            System.out.println(Schedules.getInstance().get(i).toString());
        }
    }

    public boolean saveSchedule(Schedule s) {
        JSONObject json = s.toJSONObject();

        try {
            Object read = new JSONParser().parse(new FileReader("json/savedSchedules.json"));
            JSONArray readArray = (JSONArray) read;
            readArray.add(json);
            System.out.println("File found!");

            try (FileWriter file = new FileWriter("json/savedSchedules.json")) {
                file.write(readArray.toJSONString());
                System.out.println("Schedule added to the JSON!");
                return Schedules.getInstance().addSchedule(s);
            }
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();

            try (FileWriter file = new FileWriter("json/savedSchedules.json")) {
                JSONArray ja = new JSONArray();
                ja.add(json);
                file.write(ja.toJSONString());
                System.out.println("Creating new JSON!");
                return Schedules.getInstance().addSchedule(s);
            }
            catch (IOException e1) {
                e1.printStackTrace();
                return false;
            }
        }
    }


        public boolean clearSavedSchedules() {

        int option = 1;

        if (option == 1) {
            boolean ok = Schedules.getInstance().clear();
            if (ok) {
                System.out.println("Deleted successfully");
            }
            else {
                System.out.println("Error while deleting");
            }
            System.out.println();
            return ok;
        }

        return false;
    }

    public void selectRestrictions() {

        while (true) {

            Set<String> applieds = Restrictions.getInstance().getAppliedRestrictionNames();

            if (applieds.isEmpty()) {
                System.out.println("You have no applied restriction");
                System.out.println();
            }

            else {
                // Show already applieds
                System.out.println("Already applied restrictions:");
                for (String s : applieds) {
                    System.out.println(s);
                }
                System.out.println();
            }

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
        Set<String> applieds = Restrictions.getInstance().getAppliedRestrictionNames();

        if (applieds.isEmpty()) {
            System.out.println("You are not applying any restriction");
            System.out.println("Therefore you can not delete any");
            System.out.println();
            return;
        }


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
        System.out.println();

        if (Restrictions.getInstance().deleteApplied(map.get(restrictionID))) {
            System.out.println("Deleted successfully!");
            System.out.println();
        }
        else {
            System.out.println("Error while deleting");
            System.out.println();
        }
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

    public boolean produceFactory(String filePath) {

        try {
            Object obj = new JSONParser().parse(new FileReader(filePath));
            JSONArray ja = (JSONArray) obj;
            JSONObject jo = (JSONObject) ja.get(0);
            JSONArray cls = (JSONArray) jo.get("classrooms");
            JSONArray subjts = (JSONArray) jo.get("subjects");
            String degname = (String) jo.get("name");
            Integer ncredits = (int) (long) jo.get("credits");
            JSONArray gtypes = (JSONArray) jo.get("groupTypes");
            JSONArray grps = (JSONArray) jo.get("groups");


            SubjectsFactory.produce(subjts);
            /*System.out.println("*************************");
            System.out.println(Subjects.getInstance().size() + " subjects generated:");
            ArrayList<String> subjs = Subjects.getInstance().getAllKeys();
            for (String subID : subjs) {
                System.out.println(subID);
            }
            System.out.println("*************************");*/

            DegreesFactory.produce(degname, ncredits, gtypes, grps);
            /*System.out.println("New degree created: " + Degree.getInstance().getName());
            System.out.println("*************************");
            System.out.println(Groups.getInstance().size() + " groups generated:");*/
            /*TreeMap<Integer, Group> allGroups = Groups.getInstance().get();
            for (Map.Entry<Integer, Group> g : allGroups.entrySet()) {
                System.out.println(g.getValue().getSubject().getName() + " " + g.getValue().getName() + " " + g.getValue().getTypes());
            }
            System.out.println("*************************");*/

            ClassroomsFactory.produce(cls);
            /*System.out.println(Classrooms.getInstance().size() + " classrooms produced:");
            ArrayList<String> rooms = Classrooms.getInstance().getAllKeys();
            for (String classID : rooms) {
                System.out.println(classID);
            }
            System.out.println("*************************");
            System.out.println();*/

            DateTimesFactory.produce();
            BlocksFactory.produce();
            RestrictionsFactory.produce();

            return true;
        }
        catch (IOException | ParseException e) {

            System.out.println("Error while loading JSON, make sure the you have selected the right file");
            System.out.println();

            e.printStackTrace();
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> getSubjects(){
        return Subjects.getInstance().getAllKeys();
    }

    public Integer getNumberOfSubjects(){
        return Subjects.getInstance().size();
    }

    public Integer getNumberOfGroups(){
        return Groups.getInstance().size();
    }

    public Integer getNumberOfClassrooms(){
        return Classrooms.getInstance().size();
    }

    public ArrayList<String> getGroupTypes(){ return Degree.getInstance().getTypeOfGroups(); }

    public TreeSet<String> getAllExtras(){ return Classrooms.getInstance().getExtras(); }

    public ArrayList<ArrayList<String>> getClassroomInfo(){
        ArrayList<ArrayList<String>> cl = new ArrayList<>();
        ArrayList<String> rooms = Classrooms.getInstance().getAllKeys();
        for (String classID : rooms) {
            ArrayList<String> clInfo = new ArrayList<>();
            clInfo.add(classID);
            ArrayList<String> extras = Classrooms.getInstance().get(classID).getExtras();

            String str = String.join(", ", extras);
            clInfo.add(str);
            cl.add(clInfo);
        }
        return cl;
    }

    public ArrayList<ArrayList<String>> getGroupInfo(){
        ArrayList<ArrayList<String>> gr = new ArrayList<>();
        ArrayList<Integer> groups = Groups.getInstance().getAllKeys();
        for (Integer groupID : groups) {
            ArrayList<String> grInfo = new ArrayList<>();
            grInfo.add(Groups.getInstance().get(groupID).getSubject().getName());
            grInfo.add(Groups.getInstance().get(groupID).getName());
            ArrayList<String> types = Groups.getInstance().get(groupID).getTypes();
            String ty = String.join(", ", types);
            grInfo.add(ty);
            gr.add(grInfo);
        }
        return gr;
    }

    public ArrayList<String> getAvailableRestrictions(){
        ArrayList<String> restrictions = new ArrayList<>();
        Set<String> available = Restrictions.getInstance().getAvailableRestrictionsNames();
        for (String s : available) {
            restrictions.add(s);
        }
        return restrictions;
    }

    public ArrayList<String> getAppliedRestrictions(){
        ArrayList<String> restrictions = new ArrayList<>();
        Set<String> applied = Restrictions.getInstance().getAppliedRestrictionNames();
        for(String s : applied){
            restrictions.add(s);
        }
        return  restrictions;
    }

    public Boolean applyRestriction(String id, ArrayList<String> args){
            Restriction av = Restrictions.getInstance().getAvailableRestriction(id);
            // Available -> Applied
            Restriction newRestriction = av.clone();

            // For each block
            for (int blockIndex = 0; blockIndex < newRestriction.getNumberOfBlocks(); blockIndex++) {

                ArrayList<Object> params = new ArrayList<>();
                // Ask for every parameter
                for (int paramIndex = 0; paramIndex < newRestriction.getSizeArgsBlock(blockIndex); paramIndex++) {
                    // Check that param in String can be casted as correct Type
                    String argGiven = args.get(paramIndex);
                    // Add to params array (to update later)
                    params.add(argGiven);
                }

                newRestriction.setParameter(blockIndex, params.toArray());
            }
            if (Restrictions.getInstance().addApplied(newRestriction)) {
                return true;
            }
            else {
                System.out.println("Error: Unable to add restriction (already existing restriction)\n");
                return false;
            }
    }

    public Boolean deleteAppliedRestriction(String id) {
        if (Restrictions.getInstance().deleteApplied(id)) {
            System.out.println();

            Set<String> applied = Restrictions.getInstance().getAppliedRestrictionNames();
            for (String s: applied) {
                System.out.println(s);
            }
            System.out.println();
            return true;
        }
        else {
            System.out.println("Error while deleting");
            System.out.println();
            return false;
        }
    }

    public Boolean importSchedules(String path){
        System.out.println(path);
        try {
            Object obj = new JSONParser().parse(new FileReader(path));
            JSONArray ja = (JSONArray) obj;
            for(Object s : ja){

                JSONObject schedule = (JSONObject) s;
                JSONArray classes = (JSONArray) schedule.get("classes");
                Iterator it = classes.iterator();

                TreeMap<Integer, DateTime> dates = new TreeMap<>(DateTimes.getInstance().get());
                TreeMap<String, Classroom> classrooms = new TreeMap<>(Classrooms.getInstance().get());
                Schedule sch = new Schedule(dates.keySet(), classrooms.keySet());

                while(it.hasNext()){
                    Class classToAdd = new Class();
                    JSONObject c = (JSONObject) it.next();

                    Classroom classroom = Classrooms.getInstance().get( (String) c.get("classroom") );
                    int groupID = Groups.getInstance().getIDfromNameAndSubject((String) c.get("group"), (String) c.get("subject"));
                    Group group = Groups.getInstance().get(groupID);
                    DateTime.WeekDay wd = DateTime.WeekDay.valueOf(c.get("day").toString()) ;
                    Integer h = (int)(long) c.get("startHour");
                    DateTime dt = new DateTime(wd,h);
                    int dateTimeID = DateTimes.getInstance().getID(dt);

                    classToAdd.setDateTimeID(dateTimeID);
                    classToAdd.setGroup(group);
                    classToAdd.setClassroom(classroom);
                    sch.addClass(classToAdd);
                }
                Schedules.getInstance().addSchedule(sch);
            }
            return true;

        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
            return false;
        }

    }

    public Boolean isRestrictionEditable(String res){
        Restriction r = Restrictions.getInstance().getAppliedRestriccion(res);
        return r.getEditable();
    }

    public Boolean updateScore(String res, int score){
        Restrictions.getInstance().getAppliedRestriccion(res).setScore(score);
        return true;
    }

    public void setAppliedRestriction(Boolean b, String res) {
        Restriction r = Restrictions.getInstance().getAppliedRestriccion(res);
        r.setCheckedOnAppliedTable(b);
    }
    public Boolean getAppliedRestriction(String res){ return Restrictions.getInstance().getAppliedRestriccion(res).getCheckedOnAppliedTable(); }
}
