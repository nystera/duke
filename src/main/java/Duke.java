import java.util.Scanner; // Imports the Scanner Class


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
            String[] splitInput1 = input.split(" ");
            String inputType1 = splitInput1[0];
            try{
                DukeException.validateCommand(inputType1);
                switch(inputType1.toLowerCase()){
                    case "deadline":
                        list.addDeadline(input, splitInput1);
                        break;
                    case "todo":
                        list.addToDo(input, splitInput1);
                        break;
                    case "event":
                        list.addEvent(input, splitInput1);
                        break;
                    case "list":
                        list.listTask();
                        break;
                    case "done":
                        list.completeTask(splitInput1, list.getSize());
                        break;
                    case "bye":
                }
/*
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
                        case "odo":
                            list.addToDo(input);
                            break;
                        case "event":
                            list.addEvent(input);
                            break;
                    }
                }*/
            } catch(InvalidInputException m){
                m.getErrorMsg();
            }

        }
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
