import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    private final String expected = "[T][" + "\u2718" + "] test description";

    @Test
    public void addToDoTest() {
        ToDo todo = new ToDo("test description");
        String outputString = todo.toString();
        assertEquals(expected, outputString);
    }

}
