public class DeleteCommand extends Command {
    DeleteCommand(int number) {
        super(number);
        super.isExit = false;
    }

    @Override
    public void execute() {
        Duke.list.deleteTask(number);
    }
}
