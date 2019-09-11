/**
 * This is the main class of Duke. We will be running the main function in this class.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class Duke {
    private Ui ui;

    /**
     * The line that helps the terminal look neater.
     */
    public static final String line = "____________________________________________________________\n";

    /**
     * The Class of TaskList that contains an ArrayList<Task> and functions to manipulate data.
     */
    public static TaskList list = new TaskList();

    /**
     * This initializes the UI needed for Duke.
     */
    public Duke(){
        ui = new Ui();
    }

    /**
     * This function reads the text file that is stored in the user's folder.
     * <p>
     * Checks if there is already a file, else fucntion will automatically create
     * a new "Duke.txt" that is used to store data inputs by user.
     * </p>
     * @throws DukeException if there is an error reading an invalid command.
     */
    public void run() throws DukeException {
        ui.showWelcome();
        //Initializes the data in duke.txt
        Storage file = new Storage();
        file.openFile();
        file.readData();
        boolean isExit = false;
        while(!isExit) {
            String input = ui.readInput();
            try {
                Command c = Parser.parse(input);
                c.execute();
                isExit = c.isExit;
            } catch (DukeException e) {
                e.getErrorMsg();
            }
        }
        file.updateFile(list);
        file.closeFile();
    }

    /**
     * This is the main function that runs every time the User starts Duke.
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            e.getErrorMsg();
        }
    }

}
