public class Duke {
    private Ui ui;

    // This line is to help make the input look neater
    public static final String line = "____________________________________________________________\n";
    public static TaskList list = new TaskList();

    public Duke(){
        ui = new Ui();
    }

    public void run() throws DukeException {
        ui.showWelcome();
        //Initializes the data in duke.txt
        Storage file = new Storage();
        file.openFile();
        file.readData();
        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.readInput();
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

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            e.getErrorMsg();
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
    }

}
