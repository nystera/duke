import java.io.FileNotFoundException;
import java.util.Scanner; // Imports the Scanner Class

public class Duke {
    // This line is to help make the input look neater
    public static final String line = "____________________________________________________________\n";
    public static TaskList list = new TaskList();

    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Initializes the data in duke.txt
        FileManager file = new FileManager();
        file.openFile();
        file.readData();

        System.out.println(line + "Hello I'm Duke\n" + "What can I do for you?\n" + line);
        Scanner userInputs = new Scanner(System.in);
        String input;
        while (userInputs.hasNextLine()) {
            input = userInputs.nextLine();
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
                        list.endDuke(splitInput1);
                        break;
                }
                // Exit condition
                if(splitInput1[0].equalsIgnoreCase("bye")){
                    break;
                }
            } catch(InvalidInputException m){
                m.getErrorMsg();
            }
        }
        file.updateFile();
        file.closeFile();
    }
}
