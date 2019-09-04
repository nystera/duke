public abstract class Command {
    public boolean isExit;
    public String description;
    private boolean isOneWordCommand;
    public int number;

    Command (String description) {
        this.description = description;
    }

    Command(boolean isOneWordCommand) { this.isOneWordCommand = true;}

    Command(int number) { this.number = number - 1;}

    public void execute(){ }
}
