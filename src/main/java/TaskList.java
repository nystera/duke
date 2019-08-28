import java.util.ArrayList;

public class TaskList{

    public static ArrayList<Task> list;
    private static int curPtr;

    public TaskList(){
        list = new ArrayList<Task>();
    }

    public int getSize(){
        return list.size();
    }

    public int getRemainingTasks(){
        int uncompleted = 0;
        for(Task task : list){
            if(!task.isDone){
                uncompleted++;
            }
        }
        return uncompleted;
    }

    // Function shows when you have completed a task.
    void completeTask(String[] splitInput, int size){
        try{
            DukeException.ValidateMarkAsDone(splitInput, size);
            list.get(Integer.parseInt(splitInput[1]) - 1).markAsDone();
            System.out.println(Duke.line + "Nice! I've marked this task as done:\n" +
                    list.get(Integer.parseInt(splitInput[1]) - 1).toString().substring(3));
            System.out.print("You now have " + getRemainingTasks() + " remaining tasks.\n" + Duke.line);
        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
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

    void addToDo(String description, boolean isDone){
        ToDo newToDo = new ToDo(description, isDone);
        list.add(newToDo);
    }

    void addDeadline(String input, String[] splitInput){
        try{
            DukeException.validateInput(splitInput, "deadline");
            String description = input.substring(9, input.indexOf("/by") - 1);
            String by = input.substring(input.indexOf("/by") + 4);
            try{
                String[] splitDateTime = by.split(" ");
                //Temporary solution to check if there exists a time
                splitDateTime[1] = "0";
                DateTime newTime = new DateTime(by);
                Deadline newDeadline = new Deadline(description, newTime);
                list.add(newDeadline);
                System.out.println(Duke.line + "Got it. I've added this task:" );
                System.out.println("  " + newDeadline.toString());
                System.out.println(showCurrTasks());
                System.out.println(Duke.line);
            } catch(ArrayIndexOutOfBoundsException k){
                InvalidInputException n = new InvalidInputException("Please input the time of the deadline!");
                n.getErrorMsg();
            }
        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
    }

    void addDeadline(String description, String by, boolean isDone) throws InvalidInputException {
        Deadline newDeadline = new Deadline(description, by, isDone);
        list.add(newDeadline);
    }

    void addEvent(String input, String[] splitInput) {
        try{
            DukeException.validateInput(splitInput, "event");
            String description = input.substring(6, input.indexOf("/at") - 1);
            String at = input.substring(input.indexOf("/at") + 4);
            String[] splitDateTime = at.split(" ");
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
                InvalidInputException n = new InvalidInputException("Please input the time duration of the event!");
                n.getErrorMsg();
            }

        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
    }

    void addEvent(String description, String date, String duration, boolean isDone) throws InvalidInputException {
        Event newEvent = new Event(description, date, duration, isDone);
        list.add(newEvent);
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
