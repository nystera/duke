public class Event extends Task {

    protected String duration;
    protected DateTime date;

    public Event(String description, DateTime date, String duration){
        super(description);
        this.date = date;
        this.duration = duration;

    }

    public Event(String description, String date, String duration, boolean isDone) throws DukeException {
        super(description);
        super.isDone = isDone;
        this.duration = duration;
        this.date = new DateTime(date,0);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + date.getDate() + " " + duration + ")";
    }
}
