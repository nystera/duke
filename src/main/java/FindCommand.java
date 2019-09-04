public class FindCommand extends Command {

    FindCommand(String description) {
        super(description);
    }

    @Override
    public void execute() {
        Duke.list.findTask(description);
    }
}
