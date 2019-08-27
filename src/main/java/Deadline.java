import java.util.Date;

public class Deadline extends Task{

    protected String by;
    protected Date DateAndTime;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone){
        super(description);
        super.isDone = isDone;
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
