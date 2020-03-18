import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * this is a class that write in a file and return the records
 *
 * @author Hao Chen
 * @version 7/24/2019
 */
public class FileIO
{
    /**
     * save the username and point to the file and return a arrayList of the records
     * @param name the username
     * @param point the point the user gets
     * @return stringList the arraylist the has the records in it.
     */
    public static ArrayList<String> writeInFile(String name, int point){
       File file = new File("records.text");
       try{
           FileWriter output = new FileWriter(file, true);
           output.write(name + " "+ point + " ");
           output.write(System.getProperty("line.separator"));
           output.close();
       }catch(IOException ex){
           System.out.printf("error %s\n", ex);
       }
       
       try{
           Scanner key = new Scanner(file);
           ArrayList<String> stringList = new ArrayList<String>();
           while(key.hasNext()){
               stringList.add(key.next());
           }
           return stringList;
       }catch(IOException ex){
           System.out.printf("error %s\n", ex);
       }
       return null;
    }
}
