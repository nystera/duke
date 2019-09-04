public class ListCommand extends Command {


    ListCommand(boolean isOneWordCommand) {
        super(isOneWordCommand);
        super.isExit = false;
    }

    @Override
    public void execute() {
        Duke.list.listTask();
    }
}
