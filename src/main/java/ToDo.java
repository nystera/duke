/**
 * This is the main class for all ToDo tasks.
 * <p>
 * It is to help facilitate the parsing of all ToDo tasks by being a subclass of Task.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    /**
     * This overloaded constructor is to help read the data from Duke.txt.
     * @param description that describes the task.
     * @param isDone is to check if the User has already completed the task.
     */
    public ToDo(String description, boolean isDone){
        super(description);
        super.isDone = isDone;
    }
    /**
     * This is to properly show the proper text in the Terminal when user lists down the tasks added.
     * @return the String to show in the Terminal.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
