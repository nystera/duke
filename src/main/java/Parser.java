public class Parser {

    private static String[] splitCommand;
    private static String command;
    private static String description, by;

    public static Command parse(String command) throws InvalidInputException {
        splitCommand = command.split(" ");
        String commandType = splitCommand[0].toLowerCase();

        switch (commandType) {
            case "deadline":
                validateDeadline(command);
                String description = command.substring(9, command.indexOf("/by") - 1);
                String by = command.substring(command.indexOf("/by") + 4);
                String[] splitDateTime = by.split(" ");
                return new AddDeadLineCommand(description, by);
            case "event":
                validateEvent(command);
                description = command.substring(6, command.indexOf("/at") - 1);
                String at = command.substring(command.indexOf("/at") + 4);
                return new AddEventCommand(description, at);
            case "todo":
                validateToDo(command);
                description = command.substring(5);
                return new AddToDoCommand(description);
            case "list":
                validateOneWordCommand(command, commandType);
                return new ListCommand(true);
            case "bye":
                validateOneWordCommand(command, commandType);
                return new ExitCommand(true);
            case "done":
                validateTwoWordsCommand(command, commandType);
                return new DoneCommand(Integer.parseInt(splitCommand[1]));
            case "delete":
                validateTwoWordsCommand(command, commandType);
                return new DeleteCommand(Integer.parseInt(splitCommand[1]));
            case "find":
                validateTwoWordsCommand(command, commandType);
                description = splitCommand[1];
                return new FindCommand(description);
            default:
                throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }

    }

    private static void validateDeadline(String command) throws InvalidInputException {
        if (splitCommand.length == 1) {
            throw new InvalidInputException("description", splitCommand[0], false, false);
        } else if (!command.contains(" /by ")) {
            throw new InvalidInputException("task", splitCommand[0], true, false);
        } else if (splitCommand[1].equals("/by")) {
            throw new InvalidInputException("task", splitCommand[0], false, true);
        }
    }
    private static void validateEvent(String command) throws InvalidInputException {
        if (splitCommand.length == 1) {
            throw new InvalidInputException("description", splitCommand[0], false, false);
        } else if (!command.contains(" /at ")) {
            throw new InvalidInputException("task", splitCommand[0], true, false);
        } else if (splitCommand[1].equals("/at")) {
            throw new InvalidInputException("task", splitCommand[0], false, true);
        }
    }
    private static void validateToDo(String command) throws InvalidInputException {
        if (splitCommand.length == 1) {
            throw new InvalidInputException("description", splitCommand[0], false, false);
        } else if (command.contains("/by") || command.contains("/at")) {
            throw new InvalidInputException("time", splitCommand[0], true, true);
        }
    }
    private static void validateOneWordCommand(String command, String commandType) throws InvalidInputException {
        String doWhat = commandType.equals("bye") ? "exit Duke?\n" : "list out your tasks?\n";
        if (command.length() != 1) {
            throw new InvalidInputException("Sorry, were to trying to " + doWhat + "Please only type out \"" +
                    commandType + "\"");
        }
    }
    private static void validateTwoWordsCommand(String command, String commandType) throws InvalidInputException {
        String word = commandType.equals("find") ? "<keyword>" : "<number>";
        if (command.length() != 2) {
            throw new InvalidInputException("Please only type out " + "\"" + commandType + " word\"");
        } else {
            try {
                if (commandType.equals("delete") || commandType.equals("done")) {
                    int number = Integer.parseInt(splitCommand[1]);
                    if (number > Duke.list.getSize()) {
                        throw new InvalidInputException("Number currently exceeds the number of tasks!");
                    } else if (number <= 0 ) {
                        throw new InvalidInputException("Number cannot be below 1!");
                    }
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Please enter a valid number!");
            }
        }
    }

}
