public class AddDeadLineCommand extends Command{

    private final String by;

    public AddDeadLineCommand(String description, String time) {
        super(description);
        this.by = time;
        super.isExit = false;
    }

    @Override
    public void execute() {
            Duke.list.addDeadline(description, by);
        }
}
