/**
 * This class is to distinguish itself from other classes of Command, in order to
 * properly call the "execute" function when parsed.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class ListCommand extends Command {
     
    ListCommand(boolean isOneWordCommand) {
        super(isOneWordCommand);
        super.isExit = false;
    }
    /**
     * This is to properly show the text in the Terminal when user list out the Tasks in Duke.
     * @return the String to show in the Terminal.
     */
    @Override
    public void execute() {
        Duke.list.listTask();
    }
}
