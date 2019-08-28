import java.util.Date;

public class Deadline extends Task{

    protected DateTime DateAndTime;

    public Deadline(String description, DateTime by){
        super(description);
        this.DateAndTime = by;
    }

    public Deadline(String description, String by, boolean isDone) throws InvalidInputException {
        super(description);
        super.isDone = isDone;
        this.DateAndTime = new DateTime(by);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + DateAndTime.getDateAndTime()+ ")";
    }
}
