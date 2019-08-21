import java.util.Scanner; // Imports the Scanner Class

public class Duke {
    // This line is to help make the input look neater
    private static String line = "____________________________________________________________\n";

    //This repeats the statement of the user
    private static void copyWord(String input){
        System.out.println(line + input + "\n" + line);
    }

    public static void main(String[] args) {
        // These are Strings that will help with the aesthetic of the terminal
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println(line + "Hello I'm Duke\n" + "What can I do for you?\n" + line);
        Scanner userInputs = new Scanner(System.in);
        String input;
        while(!(input = userInputs.nextLine()).equals("bye")){
            copyWord(input);
        }
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}

