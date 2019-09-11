import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    private final String expected = "[E][" + "\u2718" + "] test description (at: 01/01/2019 1-2pm)";

    @Test
    public void EventTest() throws DukeException {
        DateTime dateTime = new DateTime("01/01/2019", 1);
        Event event = new Event("test description", dateTime, "1-2pm");
        String outputString = event.toString();
        assertEquals(expected, outputString);
    }
}
