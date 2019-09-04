public class Parser {

    private static String[] splitCommand;
    private static String description, by, at;

    public static Command parse(String command) throws DukeException {
        splitCommand = command.split(" ");
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

    private static void validateDeadline(String command) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("description", splitCommand[0], false, false);
        } else if (!command.contains(" /by ")) {
            throw new DukeException("task", splitCommand[0], true, false);
        } else if (splitCommand[1].equals("/by")) {
            throw new DukeException("task", splitCommand[0], false, true);
        }
    }
    private static void validateEvent(String command) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("description", splitCommand[0], false, false);
        } else if (!command.contains(" /at ")) {
            throw new DukeException("task", splitCommand[0], true, false);
        } else if (splitCommand[1].equals("/at")) {
            throw new DukeException("task", splitCommand[0], false, true);
        }
    }
    private static void validateToDo(String command) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("description", splitCommand[0], false, false);
        } else if (command.contains("/by") || command.contains("/at")) {
            throw new DukeException("time", splitCommand[0], true, true);
        }
    }
    private static void validateOneWordCommand(String commandType) throws DukeException {
        String doWhat = commandType.equals("bye") ? "exit Duke?\n" : "list out your tasks?\n";
        if (splitCommand.length != 1) {
            throw new DukeException("Sorry, were to trying to " + doWhat + "Please only type out \"" +
                    commandType + "\"");
        }
    }
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
