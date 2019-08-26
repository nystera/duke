import java.util.ArrayList;

public class TaskList{
    private static ArrayList<Task> list;
    private static int curPtr;

    public TaskList(){
        list = new ArrayList<Task>();
    }

    public int getSize(){
        return list.size();
    }

    // Function repeats the statement of the user.
    void printAddedTask(String input){
        System.out.println(Duke.line + "added: " + input + "\n" + Duke.line);
    }

    // Function shows when you have completed a task.
    void completeTask(String[] splitInput, int size){
        try{
            DukeException.ValidateMarkAsDone(splitInput, size);
            list.get(Integer.parseInt(splitInput[1]) - 1).markAsDone();
            System.out.println(Duke.line + "Nice! I've marked this task as done:\n" + "  [\u2713] " + list.get(Integer.parseInt(splitInput[1]) - 1).description + "\n" + Duke.line);
        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
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

    void addToDo(String input, String[] splitInput){
        try{
            DukeException.validateInput(splitInput, "todo");
            String description = input.substring(5);
            ToDo newToDo = new ToDo(description);
            list.add(newToDo);
            System.out.println(Duke.line + "Got it. I've added this task:" );
            System.out.println("  " + newToDo.toString());
            System.out.println(showCurrTasks());
            System.out.println(Duke.line);
        } catch(InvalidInputException m) {
            m.getErrorMsg();
        }

    }

    void addDeadline(String input, String[] splitInput){
        try{
            DukeException.validateInput(splitInput, "deadline");
            String description = input.substring(9, input.indexOf("/by") - 1);
            String by = input.substring(input.indexOf("/by") + 4);
            Deadline newDeadline = new Deadline(description, by);
            list.add(newDeadline);
            System.out.println(Duke.line + "Got it. I've added this task:" );
            System.out.println("  " + newDeadline.toString());
            System.out.println(showCurrTasks());
            System.out.println(Duke.line);
        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
    }

    void addEvent(String input, String[] splitInput) {
        try{
            DukeException.validateInput(splitInput, "event");
            String description = input.substring(6, input.indexOf("/at") - 1);
            String at = input.substring(input.indexOf("/at") + 4);
            Event newEvent = new Event(description, at);
            list.add(newEvent);
            System.out.println(Duke.line + "Got it. I've added this task:");
            System.out.println("  " + newEvent.toString());
            System.out.println(showCurrTasks());
            System.out.println(Duke.line);
        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
    }

    void endDuke(String[] splitInput){
        try{
            DukeException.validateInput(splitInput, "bye");
            System.out.println(Duke.line + "Bye. Hope to see you again soon!\n" + Duke.line);
        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
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

}
