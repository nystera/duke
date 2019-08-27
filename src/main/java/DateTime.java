import javax.lang.model.type.NullType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    private static String wrongTimeFormatError = "Wrong formatting of date and time!\n" +
            "Please use any of the following format instead:\n" +
            "DD/MM/YYYY HHMM\n" +
            "DD-MM-YYYY hh.mm am/pm";
    private SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-mm-yyyy HHMM");
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.mm.yyyy HHMM");
    private SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/mm/yyyy HHMM");
    private SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/mm/yyyy h.mma");

    private SimpleDateFormat[] dateFormats = {dateFormat1, dateFormat2, dateFormat3, dateFormat4};

    private String DateTimeStr;
    private Date DateAndTime;

    /*private String tryParse(String userInput){
        for(SimpleDateFormat formats : dateFormats){
            try{

            }
        }
    }*/

    public DateTime(String userInput) throws InvalidInputException {
        for (SimpleDateFormat formats : dateFormats) {
            try {
                DateAndTime = formats.parse(userInput);
            } catch (ParseException e) {
            }
        }
        if(DateAndTime == null){
            throw new InvalidInputException(wrongTimeFormatError);
        }
    }

    public String getDateAndTime(){
        return DateAndTime.toString();
    }

}
