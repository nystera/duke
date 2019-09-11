/**
 * This is the main class for all Deadline tasks.
 * <p>
 * It is to help facilitate the parsing of all deadline tasks by being a subclass of Task.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class Deadline extends Task{
    /**
     * This DateTime variable stores the proper time and date format into the text file.
     */
    protected DateTime DateAndTime;

    /**
     * This constructor is create a proper deadline for parsing.
     * @param description that describes the task.
     * @param by is the time and date of the deadline.
     */
    public Deadline(String description, DateTime by){
        super(description);
        this.DateAndTime = by;
    }

    /**
     * This overloaded constructor is to help read the data from Duke.txt.
     * @param description that describes the task.
     * @param by is the time and date of the deadline.
     * @param isDone is to check if the User has already completed the task.
     * @throws DukeException if the Date and Time is in the wrong format in the text file.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description);
        super.isDone = isDone;
        this.DateAndTime = new DateTime(by);
    }

    /**
     * This is to properly show the proper text in the Terminal when user lists down the tasks added.
     * @return the String to show in the Terminal.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + DateAndTime.getDateAndTime()+ ")";
    }
}
