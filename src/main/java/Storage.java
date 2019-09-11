import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * This class is to allow the data storage of their added TaskList.
 * <p>
 *     It is done by having a scanner and formatter such that it will attempt to
 *     load their file when Duke starts up, and saves the file when they exit Duke.
 * </p>
 * @author Nathan Yeo
 * @version 1.0
 * @since v1.0
 */
public class Storage {
    private static Scanner x;
    private static Formatter y;
    /**
     * These string are to help create and save files in the specific folder of the User,
     * regardless of where they saved Duke in their computer.
     */
    private static final String ROOTDIR = System.getProperty("user.dir");
    private static final String filePath = ROOTDIR + "\\data\\duke.txt";
    private static final String FOLDER = ROOTDIR + "\\data";

    /**
     * This function attempts to create the file if it is the first time that the User
     * starts up Duke.
     */
    public void createFile(){
        try{
            File makeDirectory = new File(FOLDER);
            makeDirectory.mkdir();
            y = new Formatter(filePath);
        } catch(Exception e){
            System.out.println("error");
        }
    }

    /**
     * This function attempts to open the file of Duke.txt and load the data in it. It will throw
     * an exception if file is not found, and attempts to create it.
     */
    public void openFile(){
        try{
            x = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            this.createFile();
            this.openFile();
        }
    }

    /**
     * This function closes the file after the User has exited Duke in case of file loading errors.
     */
    public void closeFile(){
        if(y != null){
            y.close();
        }
        x.close();
    }

    /**
     * This function updates the corresponding data in Duke.txt file after the User has exited the application.
     * As we are dealing with a small sample, Duke will simply recreate a new file to overwrite it.
     * @param list is the list of the Tasks inputted by the User.
     */
    public void updateFile(TaskList list){
        try{
            y = new Formatter(filePath);
            addData(list);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    /**
     * This function is to add data into Duke.txt file by formatting words into the file, depending on the
     * type of Add Command given.
     * @param list is the list of the Tasks inputted by the User.
     */
    public void addData(TaskList list){
        String taskType, status, description, time;
        String taskStr;
        for(Task task : list.list){
            taskStr = task.toString();
            taskType = task.toString().substring(1,2);
            status = task.isDone ? "1" : "0";
            if(taskType.equals("T")){
                description = task.toString().substring(7);
                y.format("%s // %s // %s\n", taskType, status, description);
            }
            else if(taskType.equals("E")){
                String date;
                description = taskStr.substring(7, taskStr.indexOf(" (at:"));
                time = taskStr.substring(taskStr.indexOf("(at:"));
                time = time.replace("(at: ", "");
                time = time.substring(0, time.length() - 1);
                String[] splitDateAndTime = time.split(" ");
                date = splitDateAndTime[0];
                time = splitDateAndTime[1];
                y.format("%s // %s // %s // %s // %s\n", taskType, status, description, date, time);
            }
            else if(taskType.equals("D")){
                description = taskStr.substring(7, taskStr.indexOf(" (by:"));
                time = taskStr.substring(taskStr.indexOf("(by:"));
                time = time.replace("(by: ", "");
                time = time.substring(0, time.length() - 1);
                y.format("%s // %s // %s // %s\n", taskType, status, description, time);
            }
        }
    }

    /**
     * This function is to read the Data in the Duke.txt file and parse it such that Duke is able
     * to understand the line of texts, and stores them into the TaskList.
     * @throws DukeException if there is no file to be read.
     */
    public void readData() throws DukeException {
        String taskType, description, time;
        boolean isDone;
        while(x.hasNextLine()){
            String[] splitLine = x.nextLine().split(" // ");
            taskType = splitLine[0];
            isDone = splitLine[1].equals("1");
            description = splitLine[2];
            if(taskType.equals("T")){
                Duke.list.addToDo(description, isDone);
            }
            else{
                if(taskType.equals("D")){
                    time = splitLine[3];
                    Duke.list.addDeadline(description, time, isDone);
                }
                else{
                    String date = splitLine[3];
                    time = splitLine[4];
                    Duke.list.addEvent(description, date, time, isDone);
                }
            }
        }
    }
}
