public class DukeException extends Exception{
    private String errorMsg;
    private String inputContent;
    private String inputType;
    private boolean hasDescription = false;
    private boolean hasTimeSet = false;

    public DukeException(String s){
        this.errorMsg = s;
    }

    public DukeException(String s, String commandType, boolean hasDescription, boolean hasTimeSet){
        this.inputContent = s;
        this.inputType = commandType;
        this.hasDescription = hasDescription;
        this.hasTimeSet = hasTimeSet;
    }

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
