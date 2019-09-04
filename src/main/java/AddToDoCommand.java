public class AddToDoCommand extends Command {

    AddToDoCommand(String description) {
        super(description);
        super.isExit = false;
    }

    @Override
    public void execute() {
        Duke.list.addToDo(description);
    }
}
