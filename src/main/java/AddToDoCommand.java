/**
 * This class is to distinguish itself from other classes of Command, in order to
 * properly call the "execute" function when parsed.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class AddToDoCommand extends Command {

    /**
     * This constructor helps parse the command.
     * @param description
     */
    AddToDoCommand(String description) {
        super(description);
        super.isExit = false;
    }
    /**
     * This function executes the function of "addDeadLine" in TaskList.
     */
    @Override
    public void execute() {
        Duke.list.addToDo(description);
    }
}
