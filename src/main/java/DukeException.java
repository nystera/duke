import javax.lang.model.type.NullType;

public class DukeException extends Exception {


    static void validateCommand(String command) throws InvalidInputException {
        switch(command.toLowerCase()){
            case "bye":
            case "list":
            case "todo":
            case "event":
            case "deadline":
                break;
            default:
                throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }
    }

    static void validateInput(String[] splitInput, String inputType) throws InvalidInputException {
        if(splitInput.length == 1){
            throw new InvalidInputException("description", inputType, false, false);
        }
        else{
            boolean flag = false;
            for(int i = 1; i < splitInput.length; ++i){
                if((splitInput[i].equalsIgnoreCase("/by") && inputType.equalsIgnoreCase("deadline"))
                || (splitInput[i].equalsIgnoreCase("/at") && inputType.equalsIgnoreCase("event"))){
                    flag = true;
                    if(i == 1){
                        throw new InvalidInputException("task", inputType, false, true);
                    }
                    else if(i == splitInput.length - 1){
                        throw new InvalidInputException("time", inputType, true, false);
                    }
                }
                else if(splitInput[i].contains("/") && inputType.equalsIgnoreCase("todo")){
                    throw new InvalidInputException("description", inputType, true, true);
                }
            }
            if(!flag && !(inputType.equalsIgnoreCase("todo"))) {
                throw new InvalidInputException("time", inputType, true, false);
            }
        }
    }

}
