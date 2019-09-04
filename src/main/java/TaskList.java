import javax.sound.midi.SysexMessage;
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

    // Function shows when you have completed a task.
    void completeTask(String[] splitInput, int size){
        try{
            DukeException.ValidateMarkAsDone(splitInput, size);
            list.get(Integer.parseInt(splitInput[1]) - 1).markAsDone();
            System.out.println(Duke.line + "Nice! I've marked this task as done:\n" +
                    list.get(Integer.parseInt(splitInput[1]) - 1).toString().substring(3));
            System.out.print("You now have " + getUncompletedTasks() + " remaining tasks.\n" + Duke.line);
        } catch(InvalidInputException m){
            m.getErrorMsg();
        }
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

    void addToDo(String description){
        ToDo newToDo = new ToDo(description);
        list.add(newToDo);
        System.out.println(Duke.line + "Got it. I've added this task:" );
        System.out.println("  " + newToDo.toString());
        System.out.println(showCurrTasks());
        System.out.println(Duke.line);
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
            InvalidInputException n = new InvalidInputException("Please input the time of the deadline!");
            n.getErrorMsg();
        } catch (InvalidInputException p) {
            p.getErrorMsg();
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

    void deleteTask(String[] splitInput) throws ArrayIndexOutOfBoundsException{
        try{
            if(splitInput.length != 2){
                throw new InvalidInputException("Please only type \"delete <numbering of task>\" !");
            }
            System.out.print(Duke.line);
            int i = Integer.parseInt(splitInput[1]) - 1;
            String oldTask = list.get(i).toString();
            list.remove(i);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + oldTask);
            System.out.println("Now you have " + list.size() + " tasks in the list");
        } catch(NumberFormatException e){
            System.out.println("Please input a valid number! I cannot recognize it :(");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Please input a number that's in range! Your tasks are only up to " + list.size());
        } catch(InvalidInputException e){
            e.getErrorMsg();
        } finally{
            System.out.print(Duke.line);
        }
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


    void findTask(String[] splitInput){
        try{
            if(splitInput.length != 2){
                throw new InvalidInputException("Please only type \"find <word>\"! I cannot find more than ONE keyword");
            }
            System.out.println(Duke.line + "Here are the matching tasks in your list:");
            int counter = 1;
            for(Task task : list){
                if(isContain(task.toString(), splitInput[1])){
                    System.out.println("  " + counter + "." + task.toString());
                    counter++;
                }
            }
            if(counter == 1){
                System.out.println("  There are currently no tasks that contains " + "\"" + splitInput[1] + "\".");
            }
        } catch(InvalidInputException e){
            e.getErrorMsg();
        } finally{
            System.out.println(Duke.line);
        }
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

    // Function checks if the tasks contains exactly the word
    private static boolean isContain(String task, String keyword){
        String pattern = "\\b" + keyword + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(task);
        return m.find();
    }

}
