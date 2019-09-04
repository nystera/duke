public class DeleteCommand extends Command {
    DeleteCommand(int number) {
        super(number);
    }

    @Override
    public void execute() {
        Duke.list.deleteTask(number);
    }
}
