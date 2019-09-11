/**
 * This is the main abstract Command class that will have multiple children for easier
 * command parsing.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public abstract class Command {
    /**
     * This boolean variable helps determine if command is to exit Duke.
     */
    public boolean isExit;
    /**
     * This String is to describe the activity of the task.
     */
    public String description;
    private boolean isOneWordCommand;
    /**
     * This int is to help obtain the element array in ArrayList<Task>
     */
    public int number;

    /**
     * This constructor will be used for all subclasses of addCommand.
     * @param description
     */
    Command (String description) {
        this.description = description;
    }

    /**
     * This overloaded constructor is to determine if command has only one word. ("bye", "list").
     * @param isOneWordCommand
     */
    Command(boolean isOneWordCommand) { this.isOneWordCommand = true;}

    /**
     * This overloaded constructor is to properly obtain the element in 0 index.
     * @param number
     */
    Command(int number) { this.number = number - 1;}

    /**
     * This empty execute are for subclasses of Command to Override.
     */
    public void execute(){ }
}
