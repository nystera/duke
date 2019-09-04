public class DoneCommand extends Command {

    DoneCommand(int number) {
        super(number);
        super.isExit = false;
    }

    @Override
    public void execute() {
        Duke.list.completeTask(number);
    }
}
