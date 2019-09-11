import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is to properly parse the Date and Time given when the User is trying to add a time and date.
 * <p>
 * It also has functions to properly ensure that users input the correct format of the date and time.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class DateTime {
    /**
     * This Date variable is to correct store the parsed String into proper date format.
     */
    private Date DateAndTime;
    /**
     * This string is to be returned when User inputs the wrong format for Deadline.
     */
    public static String wrongTimeFormatError = "Wrong formatting of date and time!\n" +
            "Please use any of the following format instead:\n" +
            "DD/MM/YYYY HHMM\n" +
            "DD/MM/YYYY hh.mma";
    /**
     * This string is to be returned when User inputs the wrong format for Event.
     */
    public static String wrongTimeFormatError2 = "Wrong formatting of date and time!\n" +
            "Please use the following format instead:\n" +
            "DD/MM/YYYY hhmm-hhmm\n" +
            "DD/MM/YYYY h.mma-h.mma";

    /**
     * These are the Format for Dates that Duke allows.
     */
    private SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/yyyy HHmm");
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MMM/yyyy h.mma");
    private SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy h.mma");
    private SimpleDateFormat dateFormat5 = new SimpleDateFormat("dd/MM/yyyy");

    private SimpleDateFormat[] dateFormats = {dateFormat1, dateFormat2, dateFormat3, dateFormat4};

    /**
     * This constructor helps initializes DateTime for Deadline.
     * @param userInput
     * @throws DukeException when user inputs the wrong time format.
     */
    public DateTime(String userInput) throws DukeException {
        for (SimpleDateFormat formats : dateFormats) {
            try {
                DateAndTime = formats.parse(userInput);
            } catch (ParseException e) {
            }
        }
        if(DateAndTime == null){
            throw new DukeException(wrongTimeFormatError);
        }
    }
    /**
     * This overloaded constructor helps initializes DateTime for Event.
     * @param userInput
     * @throws DukeException when user inputs the wrong time format.
     */
    public DateTime(String userInput, int other) throws DukeException {
            try {
                DateAndTime = dateFormat5.parse(userInput);
            } catch (ParseException e) {
        }
        if(DateAndTime == null){
            throw new DukeException(wrongTimeFormatError2);
        }
    }

    /**
     * This function is to show the User the time and date for the task added.
     * @author Nathan Yeo
     * @return the String format of Datetime inputted for Deadline.
     */
    public String getDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h.mma");
        String dateStr = dateFormat.format(DateAndTime);
        return dateStr;
    }
    /**
     * This function is to show the User only the date for the task added.
     * @author Nathan Yeo
     * @return the String format of only the Date inputted for Event.
     */
    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = dateFormat.format(DateAndTime);
        return dateStr;
    }

}
