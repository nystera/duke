/**
 * This abstract class Task is to create a skeleton of Tasks to be added into Duke.
 * <p>
 *     Duke does not instantiate this in the code, but will call it's subclasses for
 *     it to read the Command faster.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return the String of a tick or cross depending if the User has completed the Task.
     */
    public String getStatusIcon(){
        return ("[" + (isDone ? "\u2713" : "\u2718") + "]");
    }

    /**
     * This function marks the Task as done by setting the variable of isDone to true.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * This is to properly show the proper text in the Terminal when user lists down the tasks added.
     * It will always be overriden by the subclass of this class.
     * @return the String to show in the Terminal.
     */
    @Override
    public String toString(){
        return getStatusIcon() + " " + description;
    }
}
