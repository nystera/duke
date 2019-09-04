import java.util.Scanner; // Imports the Scanner Class

public class Ui {

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    public final void showLine(){
        System.out.println("____________________________________________________________");
    }

    public final void showWelcome(){
        showLine();
        System.out.println("Hello! Welcome to ");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        showLine();
    }

    public String readInput(){
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }
}
