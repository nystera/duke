public class InvalidInputException extends Exception{
    private String errorMsg;
    private String inputContent;
    private String inputType;
    private boolean hasDescription = false;
    private boolean hasTimeSet = false;

    public InvalidInputException(String s){
        this.errorMsg = s;
    }

    public InvalidInputException(String s, String commandType, boolean hasDescription, boolean hasTimeSet){
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
        }
        else if(hasDescription && !hasTimeSet){
            System.out.println(Duke.line + "OOPS!!! Please set a valid format of time of a " + inputType);
        }
        else if(!hasDescription && hasTimeSet){
            System.out.println(Duke.line + "OOPS!!! Please insert the description of the " + inputType);
        }
        else if(hasDescription && hasTimeSet){
            System.out.println(Duke.line + "OOPS!!! Description of " + inputType + " is in the wrong format");
        }
    }
}
