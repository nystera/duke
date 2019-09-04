import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList{

    public ArrayList<Task> list;

    public TaskList(){
        list = new ArrayList<Task>();
    }

    public int getSize(){
        return list.size();
    }

    public int getUncompletedTasks(){
        int uncompleted = 0;
        for(Task task : list){
            if(!task.isDone){
                uncompleted++;
            }
        }
        return uncompleted;
    }

    void completeTask(int number){
        list.get(number).markAsDone();
        System.out.println(Duke.line + "Nice! I've marked this task as done:\n" +
                list.get(number).toString().substring(3));
        System.out.print("You now have " + getUncompletedTasks() + " remaining tasks.\n" + Duke.line);
    }

    void listTask(){
        short i = 1;
        System.out.print(Duke.line);
        if(list.size() != 0){
            System.out.println("Here are the tasks in your list:");
            for(Task task : list) {
                System.out.print(i + ".");
                System.out.println(task.toString());
                i++;
            }
        }
        else{
            System.out.println("You have no current tasks! Good job!");
        }
        System.out.println(Duke.line);
    }

    void addToDo(String description, boolean isDone){
        ToDo newToDo = new ToDo(description, isDone);
        list.add(newToDo);
    }

    void addToDo(String description){
        ToDo newToDo = new ToDo(description);
        list.add(newToDo);
        System.out.println(Duke.line + "Got it. I've added this task:" );
        System.out.println("  " + newToDo.toString());
        System.out.println(showCurrTasks());
        System.out.println(Duke.line);
    }

    void addDeadline(String description, String time) {
        try{
            String[] splitDateTime = time.split(" ");
            //Temporary solution to check if there exists a time
            splitDateTime[1] = "0";
            DateTime newTime = new DateTime(time);
            Deadline newDeadline = new Deadline(description, newTime);
            list.add(newDeadline);
            System.out.println(Duke.line + "Got it. I've added this task:" );
            System.out.println("  " + newDeadline.toString());
            System.out.println(showCurrTasks());
            System.out.println(Duke.line);
        } catch(ArrayIndexOutOfBoundsException k){
            DukeException n = new DukeException("Please input the time of the deadline!");
            n.getErrorMsg();
        } catch (DukeException p) {
            p.getErrorMsg();
        }
    }

    void addDeadline(String description, String by, boolean isDone) throws DukeException {
        Deadline newDeadline = new Deadline(description, by, isDone);
        list.add(newDeadline);
    }

    void addEvent(String description, String time) {
        try{
            String[] splitDateTime = time.split(" ");
            DateTime newDate = new DateTime(splitDateTime[0], 0);
            try{
                String duration = splitDateTime[1];
                Event newEvent = new Event(description, newDate, duration);
                list.add(newEvent);
                System.out.println(Duke.line + "Got it. I've added this task:");
                System.out.println("  " + newEvent.toString());
                System.out.println(showCurrTasks());
                System.out.println(Duke.line);
            } catch(ArrayIndexOutOfBoundsException k){
                DukeException n = new DukeException("Please input the time duration of the event!");
                n.getErrorMsg();
            }
        } catch(DukeException m){
            m.getErrorMsg();
        }
    }

    void addEvent(String description, String date, String duration, boolean isDone) throws DukeException {
        Event newEvent = new Event(description, date, duration, isDone);
        list.add(newEvent);
    }

    void deleteTask(int number) {
            System.out.print(Duke.line);
            String oldTask = list.get(number).toString();
            list.remove(number);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + oldTask);
            System.out.println("Now you have " + list.size() + " tasks in the list");
            System.out.print(Duke.line);
    }

    void findTask(String keyword){
            System.out.println(Duke.line + "Here are the matching tasks in your list:");
            int counter = 1;
            for(Task task : list){
                if(isContain(task.toString(), keyword)){
                    System.out.println("  " + counter + "." + task.toString());
                    counter++;
                }
            }
            if(counter == 1){
                System.out.println("  There are currently no tasks that contains " + "\"" + keyword + "\".");
            }
            System.out.println(Duke.line);
    }

    private String showCurrTasks(){
        return "Now you have " + list.size() + " tasks in the list.";
    }

    // Function checks if the string is numeric which means user wants to tick their task as done.
    public static boolean isNumeric(String strNum){
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe){
            return false;
        }
        return true;
    }

    // Function checks if the tasks contains exactly the word
    private static boolean isContain(String task, String keyword){
        String pattern = "\\b" + keyword + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(task);
        return m.find();
    }

}
