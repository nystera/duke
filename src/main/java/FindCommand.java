public class FindCommand extends Command {

    FindCommand(String description) {
        super(description);
        super.isExit = false;
    }

    @Override
    public void execute() {
        Duke.list.findTask(description);
    }
}
