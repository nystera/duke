public class DoneCommand extends Command {

    DoneCommand(int number) {
        super(number);
    }

    @Override
    public void execute() {
        Duke.list.completeTask(number);
    }
}
