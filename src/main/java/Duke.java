import java.util.Scanner; // Imports the Scanner Class
import java.util.ArrayList;

public class Duke {
    // This line is to help make the input look neater
    private static String line = "____________________________________________________________\n";

    // Function repeats the statement of the user.
    private static void showStoredWord(String input){
        System.out.println(line + "added: " + input + "\n" + line);
    }
    // Function shows the list created so far.
    private static void showList(ArrayList<String> list){
        System.out.print(line);
        for(short x = 1; x <= list.size(); x++){
            System.out.print(x);
            System.out.println(". " + list.get(x - 1));
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //This is to store the tasks given
        ArrayList<String> list = new ArrayList<String>();

        System.out.println(line + "Hello I'm Duke\n" + "What can I do for you?\n" + line);
        Scanner userInputs = new Scanner(System.in);
        String input;
        while(!(input = userInputs.nextLine()).equals("bye")){
            if(input.equals("list")){
                showList(list);
            }
            else{
                list.add(input);
                showStoredWord(input);
            }
        }
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
