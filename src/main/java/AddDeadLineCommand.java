/**
 * This class is to distinguish itself from other classes of Command, in order to
 * properly call the "execute" function when parsed.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class AddDeadLineCommand extends Command{

    /**
     * This String is to be parsed into Datetime format.
     */
    private final String by;

    /**
     * This constructor helps parse the command.
     * @param description
     * @param time
     */
    public AddDeadLineCommand(String description, String time) {
        super(description);
        this.by = time;
        super.isExit = false;
    }

    /**
     * This function executes the function of "addDeadLine" in TaskList.
     */
    @Override
    public void execute() {
            Duke.list.addDeadline(description, by);
        }
}
