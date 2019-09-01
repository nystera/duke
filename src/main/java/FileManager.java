import java.io.*;
import java.util.*;
import java.lang.*;

public class FileManager {
    private static Scanner x;
    private static Formatter y;
    private FileWriter z;
    private static final String ROOTDIR = System.getProperty("user.dir");
    private static final String filePath = ROOTDIR + "\\data\\duke.txt";
    private static final String FOLDER = ROOTDIR + "\\data";


    public void createFile(){
        try{
            File makeDirectory = new File(FOLDER);
            makeDirectory.mkdir();
            y = new Formatter(filePath);
        } catch(Exception e){
            System.out.println("error");
        }
    }


    public void openFile(){
        try{
            x = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            this.createFile();
            this.openFile();
        }
    }

    public void closeFile(){
        if(y != null){
            y.close();
        }
        x.close();
    }

    // As we are dealing with a smaller sample, we will just recreate a new file everytime it closes.
    public void updateFile(TaskList list){
        try{
            y = new Formatter(filePath);
            addData(list);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public void addData(TaskList list){
        String taskType, status, description, time;
        String taskStr;
        for(Task task : list.list){
            taskStr = task.toString();
            //System.out.println(taskStr);
            taskType = task.toString().substring(1,2);
            //System.out.println(taskType);
            status = task.isDone ? "1" : "0";
            //System.out.println(status);
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

    public void readData() throws InvalidInputException {
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
