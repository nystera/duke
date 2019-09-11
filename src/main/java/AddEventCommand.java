/**
 * This class is to distinguish itself from other classes of Command, in order to
 * properly call the "execute" function when parsed.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class AddEventCommand extends Command{

    /**
     * This string is to be parsed in DateTime format.
     */
    private final String at;

    /**
     * This constructor helps parse the command.
     * @param description
     * @param time
     */
    public AddEventCommand(String description, String time) {
        super(description);
        this.at = time;
        super.isExit = false;
    }
    /**
     * This function executes the function of "addDeadLine" in TaskList.
     */
    @Override
    public void execute() {
        Duke.list.addEvent(description, at);
    }

}