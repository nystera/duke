import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is to store all tasks given by the User.
 * <p>
 * It contains functions to help facilitate the User as such finding, adding, deleting
 * tasks. It also holds the ArrayList of tasks for storage.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class TaskList{
    /**
     * This ArrayList variable helps store the tasks given by the User.
     */
    public ArrayList<Task> list;

    public TaskList(){
        list = new ArrayList<Task>();
    }

    /**
     * This function is to obtain the size of the list.
     * @return the number of tasks in the ArrayList.
     */
    public int getSize(){
        return list.size();
    }

    /**
     * This function is to check for the number of uncompleted tasks.
     * @return the int of the number of uncompleted Tasks.
     */
    public int getUncompletedTasks(){
        int uncompleted = 0;
        for(Task task : list){
            if(!task.isDone){
                uncompleted++;
            }
        }
        return uncompleted;
    }

    /**
     * This function is to check off the task in the list by letting the User know that
     * the specific task has been completed by them.
     * @param number the integer (1 index) of the element that the User wants to complete.
     */
    void completeTask(int number){
        list.get(number).markAsDone();
        System.out.println(Duke.line + "Nice! I've marked this task as done:\n" +
                list.get(number).toString().substring(3));
        System.out.print("You now have " + getUncompletedTasks() + " remaining tasks.\n" + Duke.line);
    }

    /**
     * This function is the list out all the tasks the User has inputted in the ArrayList so far. It also
     * lets the user know if the list is empty.
     */
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
    /**
     * This function is for adding of the Task of ToDo in the ArrayList when the User
     * is running Duke. It also prompts the User by letting them know that
     * they have added it once it stores the data in the ArrayList.
     * @param description is the description of the task.
     */
    void addToDo(String description){
        ToDo newToDo = new ToDo(description);
        list.add(newToDo);
        System.out.println(Duke.line + "Got it. I've added this task:" );
        System.out.println("  " + newToDo.toString());
        System.out.println(showCurrTasks());
        System.out.println(Duke.line);
    }
    /**
     * This overloaded function is to add the ToDo in the ArrayList when User loads up Duke.
     * @param description is the description of the task.
     * @param isDone is the completion checklist of the task.
     */
    void addToDo(String description, boolean isDone){
        ToDo newToDo = new ToDo(description, isDone);
        list.add(newToDo);
    }

    /**
     * This function is for adding of the Task of Deadline in the ArrayList when the User
     * is running Duke.
     * <p>
     * It also prompts the User by letting them know that
     * they have added it once it stores the data in the ArrayList.
     * It throws an exception and lets the User know if they have inputted the wrong format
     * that Duke allows.
     * </p>
     * @param description is the description of the task.
     * @param time is the time of the task in the proper format.
     */
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
    /**
     * This overloaded function is to add the Deadline in the ArrayList when User loads up Duke.
     * @param description is the description of the task.
     * @param by is the DateTime of the task.
     * @param isDone is the completion checklist of the task.
     * @throws DukeException if the time is given in the wrong format
     */
    void addDeadline(String description, String by, boolean isDone) throws DukeException {
        Deadline newDeadline = new Deadline(description, by, isDone);
        list.add(newDeadline);
    }
    /**
     * This function is for adding of the Task of Event in the ArrayList when the User
     * is running Duke.
     * <p>
     * It also prompts the User by letting them know that
     * they have added it once it stores the data in the ArrayList.
     * It throws an exception and lets the User know if they have inputted the wrong format
     * that Duke allows.
     * </p>
     * @param description is the description of the task.
     * @param time is the time of the task in the proper format.
     */
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
    /**
     * This overloaded function is to add the Deadline in the ArrayList when User loads up Duke.
     * @param description is the description of the task.
     * @param date is the DateTime of the task.
     * @param duration is the String of the length of the event.
     * @param isDone is the completion checklist of the task.
     * @throws DukeException if the time is given in the wrong format
     */
    void addEvent(String description, String date, String duration, boolean isDone) throws DukeException {
        Event newEvent = new Event(description, date, duration, isDone);
        list.add(newEvent);
    }

    /**
     * This function allows the User to delete the task in the ArrayList in 1 index
     * @param number is the int of the task number in 0th index.
     */
    void deleteTask(int number) {
            System.out.print(Duke.line);
            String oldTask = list.get(number).toString();
            list.remove(number);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + oldTask);
            System.out.println("Now you have " + list.size() + " tasks in the list");
            System.out.print(Duke.line);
    }

    /**
     * This function allows the User to find the keyword in the ArrayList.
     * <p>
     * It lists out all tasks containing the specific keyword. Note that it will
     * only match the exact phrasing, such that finding "test" will not show tasks that
     * has the word "tests", "testing" etc.
     * </p>
     * @param keyword is the String that the User is finding.
     */
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

    /**
     * This private function is to show the number of current tasks in the list.
     * @return the String that Duke wants the User to know the number of Tasks in the list currently.
     */
    private String showCurrTasks(){
        return "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * This private function is to assist in only showing the exact phrasing in the Tasks given.
     * @param task is the String of the description of the Task.
     * @param keyword is the String that the User is finding.
     * @return the boolean if the task contains the exact keyword.
     */
    private static boolean isContain(String task, String keyword){
        String pattern = "\\b" + keyword + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(task);
        return m.find();
    }

}
