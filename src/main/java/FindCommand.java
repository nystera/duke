/**
 * This class is to distinguish itself from other classes of Command, in order to
 * properly call the "execute" function when parsed.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class FindCommand extends Command {
    /**
     * This constructor is to store the proper keyword such that Duke
     * will try to find the specific keyword given in the TaskList.
     * @param description of the keyword.
     */
    FindCommand(String description) {
        super(description);
        super.isExit = false;
    }
    /**
     * This is to properly show the text in the Terminal when user finds a keyword in Duke.
     * @return the String to show in the Terminal.
     */
    @Override
    public void execute() {
        Duke.list.findTask(description);
    }
}
