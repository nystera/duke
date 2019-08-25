import java.util.ArrayList;

public class TaskList{
    private static ArrayList<Task> list;
    private static int curPtr;

    public TaskList(){
        list = new ArrayList<Task>();
    }

    // Function repeats the statement of the user.
    void printAddedTask(String input){
        System.out.println(Duke.line + "added: " + input + "\n" + Duke.line);
    }

    static boolean isToList(String command){
        return command.equals("list");
    }

    static boolean isToCheckDone(String command) {
        if(command.contains("done ")){
            // Checks if this input is to tick the Task in the list
            String[] splitInput = command.split(" ");
            //Checks if the second String is a number
            if (isNumeric(splitInput[1])) {
                curPtr = Integer.parseInt(splitInput[1]) - 1;
                return true;
            }
        }
        return false;
    }

    void addTask(Task description){
        list.add(description);
    }

    // Function shows when you have completed a task.
    void doneTask(){
        list.get(curPtr).markAsDone();
        System.out.println(Duke.line + "Nice! I've marked this task as done:\n" + "  [\u2713] " + list.get(curPtr).description + "\n" + Duke.line);
    }

    void listTask(){
        short i = 1;
        System.out.println(Duke.line + "Here are the tasks in your list:");
        for(Task task : list) {
            System.out.print(i + ".");
            System.out.println(task.toString());
            i++;
        }
        System.out.println(Duke.line);
    }

    void addDeadline(String input){
        int strLen = input.length();
        String description = input.substring(9, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4, input.length());
        Deadline newDeadline = new Deadline(description, by);
        list.add(newDeadline);
        System.out.println(Duke.line + "Got it. I've added this task:" );
        System.out.println("  " + newDeadline.toString());
        System.out.println(showCurrTasks());
        System.out.println(Duke.line);
    }

    private String showCurrTasks(){
        return "Now you have " + list.size() + " tasks in the list.";
    }

    // Function checks if the string is numeric which means user wants to tick their task as done.
    private static boolean isNumeric(String strNum){
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe){
            return false;
        }
        return true;
    }

}
