/**
 * This class is to catch exceptions that are exclusive only to Duke.
 * <p>
 *     It throws and catches exceptions when the User inputs the wrong format command.
 *     The error message will then be shown by Duke to ensure that the User inputs the correct
 *     command.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class DukeException extends Exception{
    private String errorMsg;
    private String inputContent;
    private String inputType;
    private boolean hasDescription = false;
    private boolean hasTimeSet = false;

    /**
     * This constructor simply returns the error message if the error is too specific.
     * @param s is the String that Duke will output in the terminal.
     */
    public DukeException(String s){
        this.errorMsg = s;
    }

    /**
     * This overloaded constructor is to properly determine the type of error that the User made.
     * @param s is the String that Duke will output in the terminal.
     * @param commandType is the type of subclass of Command.
     * @param hasDescription checks if it is an Add___ Command or others.
     * @param hasTimeSet checks if the input contains Time.
     */
    public DukeException(String s, String commandType, boolean hasDescription, boolean hasTimeSet){
        this.inputContent = s;
        this.inputType = commandType;
        this.hasDescription = hasDescription;
        this.hasTimeSet = hasTimeSet;
    }

    /**
     * This function is to print out the error message when User inputs the wrong format
     * of command.
     * <p>
     * It will customize the error message based off what the User has done wrong
     * </p>
     * @author Nathan Yeo
     * @version 1.0
     * @since v1.0
     */
    public void getErrorMsg(){
        if(inputType == null){ // Garbage inputs
            System.out.println(Duke.line + errorMsg);
            System.out.print(Duke.line);
        }
        else if(!hasDescription && !hasTimeSet){
            System.out.println(Duke.line + "OOPS!!! There is nothing to add in the " + inputType);
            System.out.print(Duke.line);
        }
        else if(hasDescription && !hasTimeSet){
            System.out.println(Duke.line + "OOPS!!! Please set a valid format of the " + inputType);
            if(inputType.equals("deadline")){
                System.out.println("Example: \"deadline description /by DD/MM/YYYY HHMM\"");
            }
            else {
                System.out.println("Example: \"event description /at DD/MM/YYYY HHMM\"");
            }
            System.out.print(Duke.line);
        }
        else if(!hasDescription && hasTimeSet){
            System.out.println(Duke.line + "OOPS!!! Please insert the description of the " + inputType);
            System.out.print(Duke.line);
        }
        else if(hasDescription && hasTimeSet){
            System.out.println(Duke.line + "OOPS!!! Description of the " + inputType + " is in the wrong format");
            System.out.print(Duke.line);
        }
    }
}
