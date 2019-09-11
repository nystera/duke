/**
 * This class is to distinguish itself from other classes of Command, in order to
 * properly call the "execute" function when parsed.
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class ExitCommand extends Command {

    /**
     * This constructor is the ensure that isExit is true to allow the User to exit Duke.
     * @param isOneWordCommand
     */
    ExitCommand(boolean isOneWordCommand) {
        super(isOneWordCommand);
        isExit = true;
    }
    /**
     * This is to properly show the proper text in the Terminal when user exits Duke.
     * @return the String to show in the Terminal.
     */
    @Override
    public void execute() {
        System.out.println(Duke.line + "Bye. Hope to see you again soon!\n" + Duke.line);
    }
}
