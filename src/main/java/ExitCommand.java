public class ExitCommand extends Command {


    ExitCommand(boolean isOneWordCommand) {
        super(isOneWordCommand);
        isExit = true;
    }

    @Override
    public void execute() {
        System.out.println(Duke.line + "Bye. Hope to see you again soon!\n" + Duke.line);
    }
}
