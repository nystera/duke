import java.util.Scanner; // Imports the Scanner Class

/**
 * This class facilitates the UI of Duke.
 * <p>
 * It contains Strings to show the User in the terminal and also takes in the User inputs.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class Ui {

    private final String logo = "Duke";
    private static Scanner userInput = new Scanner(System.in);

    /**
     * This function shows the line to make the text neater.
     */
    public final void showLine(){
        System.out.println("____________________________________________________________");
    }

    /**
     * This function is to let the User know that Duke has started.
     */
    public final void showWelcome(){
        showLine();
        System.out.println("Hello! Welcome to ");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * This function is to read the input of the User through the Scanner function.
     * @return the String that the User has typed in the Terminal.
     */
    public String readInput(){
        return userInput.nextLine();
    }
}
