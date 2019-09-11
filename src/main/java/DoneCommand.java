/**
 * This class is to distinguish itself from other classes of Command, in order to
 * properly call the "execute" function when parsed.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class DoneCommand extends Command {

    DoneCommand(int number) {
        super(number);
        super.isExit = false;
    }

    /**
     * This function executes the function of "completeTask" in TaskList.
     */
    @Override
    public void execute() {
        Duke.list.completeTask(number);
    }
}
