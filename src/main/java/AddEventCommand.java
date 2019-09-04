public class AddEventCommand extends Command{

    private final String at;

    public AddEventCommand(String description, String time) {
        super(description);
        this.at = time;
        super.isExit = false;
    }

    @Override
    public void execute() {
        Duke.list.addEvent(description, at);
    }

}