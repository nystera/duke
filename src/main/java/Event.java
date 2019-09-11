/**
 * This is the main class for all Event tasks.
 * <p>
 * It is to help facilitate the parsing of all Event tasks by being a subclass of Task.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class Event extends Task {
    /**
     * This string variable is to store the duration of the Event.
     */
    protected String duration;
    /**
     * This DateTime variable is to store the date of the event in the proper format.
     */
    protected DateTime date;

    /**
     * This constructor is create a proper Event for parsing.
     * @param description
     * @param date
     * @param duration
     */
    public Event(String description, DateTime date, String duration){
        super(description);
        this.date = date;
        this.duration = duration;

    }
    /**
     * This overloaded constructor is to help read the data from Duke.txt.
     * @param description that describes the task.
     * @param date is the date of the Event.
     * @param duration is the time of the Event.
     * @param isDone is to check if the User has already completed the task.
     * @throws DukeException if the Date and Time is in the wrong format in the text file.
     */
    public Event(String description, String date, String duration, boolean isDone) throws DukeException {
        super(description);
        super.isDone = isDone;
        this.duration = duration;
        this.date = new DateTime(date,0);
    }
    /**
     * This is to properly show the proper text in the Terminal when user lists down the tasks added.
     * @return the String to show in the Terminal.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + date.getDate() + " " + duration + ")";
    }
}
