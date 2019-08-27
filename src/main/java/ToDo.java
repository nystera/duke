public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    public ToDo(String description, boolean isDone){
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
