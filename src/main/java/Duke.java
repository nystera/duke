import java.util.Scanner; // Imports the Scanner Class

public class Duke {
    private Ui ui;

    // This line is to help make the input look neater
    public static final String line = "____________________________________________________________\n";
    public static TaskList list = new TaskList();

    public Duke(){
        ui = new Ui();
    }

    public void run() throws InvalidInputException {
        ui.showWelcome();
        //Initializes the data in duke.txt
        Storage file = new Storage();
        file.openFile();
        file.readData();
        boolean isExit = false;
        while(!isExit) {
            String input = ui.readInput();
            String[] splitInput1 = input.split(" ");
            String inputType1 = splitInput1[0];
            try{
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
                    case "delete":
                        list.deleteTask(splitInput1);
                        break;
                    case "bye":
                        list.endDuke(splitInput1);
                        isExit = true;
                        break;
                    case "find":
                        list.findTask(splitInput1);
                        break;
                    default:
                        throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
                }
            } catch(InvalidInputException m){
                m.getErrorMsg();
            }
        }
        file.updateFile(list);
        file.closeFile();
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }

}
