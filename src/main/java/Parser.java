/**
 * This class is to properly parse the user inputs and returns the Command for the input
 * <p>
 *     It breaks down the input such that Duke will recognize the type of Command given
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class Parser {
    /**
     * This variable is to split the String with the spacing to properly check the Command.
     */
    private static String[] splitCommand;
    /**
     * These string variables are used mainly for "Add___Command" class for Duke to properly
     * store them in the TaskList.
     */
    private static String description, by, at;

    /**
     *
     * @param command String that the User has inputted.
     * @return Command type that is determined to it's specific subclass of Command.
     * @throws DukeException when User inputs the wrong format of Command.
     */
    public static Command parse(String command) throws DukeException {
        splitCommand = command.split(" ",2);
        String commandType = splitCommand[0].toLowerCase();
            switch (commandType) {
                case "deadline":
                    validateDeadline(command);
                    description = command.substring(9, command.indexOf("/by") - 1);
                    by = command.substring(command.indexOf("/by") + 4);
                    return new AddDeadLineCommand(description, by);
                case "event":
                    validateEvent(command);
                    description = command.substring(6, command.indexOf("/at") - 1);
                    at = command.substring(command.indexOf("/at") + 4);
                    return new AddEventCommand(description, at);
                case "todo":
                    validateToDo(command);
                    description = command.substring(5);
                    return new AddToDoCommand(description);
                case "list":
                    validateOneWordCommand(commandType);
                    return new ListCommand(true);
                case "bye":
                    validateOneWordCommand(commandType);
                    return new ExitCommand(true);
                case "done":
                    validateTwoWordsCommand(commandType);
                    return new DoneCommand(Integer.parseInt(splitCommand[1]));
                case "delete":
                    validateTwoWordsCommand(commandType);
                    return new DeleteCommand(Integer.parseInt(splitCommand[1]));
                case "find":
                    validateTwoWordsCommand(commandType);
                    description = splitCommand[1];
                    return new FindCommand(description);
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

    }

    /**
     * This function is to validate the deadline format by checking if the User has inputted the proper
     * way that Duke wants them to.
     * @param command is the String that the User has inputted.
     * @throws DukeException when User does not properly follow the format of Deadline
     */
    private static void validateDeadline(String command) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("description", splitCommand[0], false, false);
        } else if (!command.contains(" /by ")) {
            throw new DukeException("task", splitCommand[0], true, false);
        } else if (splitCommand[1].equals("/by")) {
            throw new DukeException("task", splitCommand[0], false, true);
        }
    }
    /**
     * This function is to validate the Event format by checking if the User has inputted the proper
     * way that Duke wants them to.
     * @param command is the String that the User has inputted.
     * @throws DukeException when User does not properly follow the format of Event
     */
    private static void validateEvent(String command) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("description", splitCommand[0], false, false);
        } else if (!command.contains(" /at ")) {
            throw new DukeException("task", splitCommand[0], true, false);
        } else if (splitCommand[1].equals("/at")) {
            throw new DukeException("task", splitCommand[0], false, true);
        }
    }
    /**
     * This function is to validate the ToDo format by checking if the User has inputted the proper
     * way that Duke wants them to.
     * @param command is the String that the User has inputted.
     * @throws DukeException when User does not properly follow the format of ToDo
     */
    private static void validateToDo(String command) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("description", splitCommand[0], false, false);
        } else if (command.contains("/by") || command.contains("/at")) {
            throw new DukeException("time", splitCommand[0], true, true);
        }
    }
    /**
     * This function is to validate all one word commands by checking if the User has inputted the proper
     * way that Duke wants them to.
     * @param commandType is the String that the User has inputted.
     * @throws DukeException when User does not properly follow the format.
     */
    private static void validateOneWordCommand(String commandType) throws DukeException {
        String doWhat = commandType.equals("bye") ? "exit Duke?\n" : "list out your tasks?\n";
        if (splitCommand.length != 1) {
            throw new DukeException("Sorry, were to trying to " + doWhat + "Please only type out \"" +
                    commandType + "\"");
        }
    }
    /**
     * This function is to validate all two words commands by checking if the User has inputted the proper
     * way that Duke wants them to.
     * @param commandType is the String that the User has inputted.
     * @throws DukeException when User does not properly follow the format or if number is greater than
     * the number of elements in their TaskList.
     */
    private static void validateTwoWordsCommand(String commandType) throws DukeException {
        String word = commandType.equals("find") ? "<keyword>" : "<number>";
        if (splitCommand.length != 2) {
            throw new DukeException("Please only type out " + "\"" + commandType + " " + word + "\"");
        } else {
            try {
                if (commandType.equals("delete") || commandType.equals("done")) {
                    int number = Integer.parseInt(splitCommand[1]);
                    if (number > Duke.list.getSize()) {
                        throw new DukeException("Number currently exceeds the number of tasks!");
                    } else if (number <= 0 ) {
                        throw new DukeException("Number cannot be below 1!");
                    }
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid number!");
            }
        }
    }

}
