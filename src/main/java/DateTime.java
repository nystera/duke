import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    private Date DateAndTime;

    public static String wrongTimeFormatError = "Wrong formatting of date and time!\n" +
            "Please use any of the following format instead:\n" +
            "DD/MM/YYYY HHMM\n" +
            "DD/MM/YYYY hh.mma";

    public static String wrongTimeFormatError2 = "Wrong formatting of date and time!\n" +
            "Please use the following format instead:\n" +
            "DD/MM/YYYY hhmm-hhmm\n" +
            "DD/MM/YYYY h.mma-h.mma";

    private SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/yyyy HHmm");
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MMM/yyyy h.mma");
    private SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy h.mma");
    private SimpleDateFormat dateFormat5 = new SimpleDateFormat("dd/MM/yyyy");

    private SimpleDateFormat[] dateFormats = {dateFormat1, dateFormat2, dateFormat3, dateFormat4};


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

    public DateTime(String userInput, int other) throws DukeException {
            try {
                DateAndTime = dateFormat5.parse(userInput);
            } catch (ParseException e) {
        }
        if(DateAndTime == null){
            throw new DukeException(wrongTimeFormatError2);
        }
    }


    public String getDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h.mma");
        String dateStr = dateFormat.format(DateAndTime);
        return dateStr;
    }

    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = dateFormat.format(DateAndTime);
        return dateStr;
    }

}
