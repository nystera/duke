import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    private final String expected = "[D][" + "\u2718" + "] test description (by: 01/01/2019 11.59PM)";

    @Test
    public void DeadlineTest() throws DukeException {
        DateTime dateTime = new DateTime("01/01/2019 2359");
        Deadline deadline = new Deadline("test description", dateTime);
        String outputString = deadline.toString();
        assertEquals(expected, outputString);
    }
}
