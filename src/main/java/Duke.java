import java.util.Scanner; // Imports the Scanner Class
import java.util.ArrayList;
import java.lang.Object;

public class Duke {
    // This line is to help make the input look neater
    public static final String line = "____________________________________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //This is to store the tasks given
        TaskList list = new TaskList();

        System.out.println(line + "Hello I'm Duke\n" + "What can I do for you?\n" + line);
        Scanner userInputs = new Scanner(System.in);
        String input;
        while (!(input = userInputs.nextLine()).equals("bye")) {
            // Changes it false if it does every other function
            if (TaskList.isToList(input)) {
                list.listTask();
            }
            else if(TaskList.isToCheckDone(input)){
                list.doneTask();
            }
            else{
                String[] splitInput = input.split(" ");
                String inputType = splitInput[0];
                switch(inputType.toLowerCase()){
                    case "deadline":
                        list.addDeadline(input);
                        break;
                    case "todo":
                        list.addToDo(input);
                        break;
                    case "event":
                        list.addEvent(input);
                        break;
                }
            }
        }
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
