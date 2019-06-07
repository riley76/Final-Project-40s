
package gameTools;

/*
/** required package class namespace */

/** required imports */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * FileHandler.java - various methods for saving and opening files
 *
 * @author Mr. Wachs
 * @since 14-May-2019 
 */
public class FileHandler <T>
{
    
    private String filename;
    
    
    /**
     * Default constructor for the class, sets class properties
     * 
     * @param filename the filename to associate the class with 
     */
    public FileHandler(String filename) {
        this.filename = filename;
    }

    /**
     * Reads the data from the file and returns all the lines as a string array
     * 
     * @return a string array of all the lines from the file 
     */
    public LinkedList<String> read() {
        try {                                                   // error trap
            File           file   = convertToFile(filename);    // create file
            FileReader     reader = new FileReader(file);       // create reader
            BufferedReader buffer = new BufferedReader(reader); // create buffer
            String         line   = buffer.readLine();          // read a line
            if (line == null) return null;                      // file empty            
            LinkedList<String> lines = new LinkedList<>();      // create list
            while (line != null) {                              // traverse
                lines.add(line);                                // add to list
                line = buffer.readLine();                       // read line
            }
            buffer.close();                                     // close file
            return lines;                                       // return list
        }
        catch (IOException e) {                                 // no file
            System.out.println("I/O error");
        }
        catch (NullPointerException e) {                        // read a null
            System.out.println("Read Null error");
        }
        return null;                                            // error caught
    }

    /**
     * Writes the passed data to the file
     * 
     * @param data string list to write to the file
     */
    public void write(LinkedList<String> data) {
        try {                                               // error trap
            File        file    = convertToFile(filename);  // create file
            FileWriter  writer  = new FileWriter(file);     // create writer
            PrintWriter printer = new PrintWriter(writer);  // create printer
            for (int i = 0; i < data.size(); i++) {         // traverse list
                printer.println(data.get(i));               // write list item
            }
            printer.close();                                // close file
        }
        catch (IOException e) {                             // no file
            System.out.println("I/O error");
        }
        catch (NullPointerException e) {                    // read a null
            System.out.println("Write Null error");
        }
    }
    
    /**
     * Converts the file name string into a file object relative to the 
     * application package
     * 
     * @return a file object converted from the file name string 
     */
    public File convertToFile(String filename) {
        try {                                               // error trap
            URL  url  = getClass().getResource(filename);   // convert to URL
            URI  uri  = url.toURI();                        // convert to URI
            File file = new File(uri);                      // create file
            if (!file.exists()) file.createNewFile();       // create new file
            return file;                                    // return new file
        }
        catch (URISyntaxException ex) {                     // URI error caught
            System.out.println("URI error");
        } 
        catch (IOException ex) {                            // file path error
            System.out.println("I/O error");
        }
        return null;                                        // error caught
    }
    
    /**
     * Saves the generic object to the passed file
     * 
     * @param data the generic object to save
     * @param file the file object to save to
     * @return the operation was successful (true) or not (false)
     */
    public boolean saveObject(T data, File file) {
        try {
            return saveObject(data, file.getAbsolutePath());
        }
        catch(NullPointerException e) {
            return false;
        }
    }
    
    /**
     * Saves the generic object to the passed filename
     * 
     * @param data the generic object to save
     * @param filename the filename to save it to
     * @return the operation was successful (true) or not (false)
     */
    public boolean saveObject(T data, String filename) {
        try {
            FileOutputStream   stream = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(stream);
            output.writeObject(data);
            output.close();
            return true;
        }
        catch(NullPointerException e) {
            return false;
        }
        catch (IOException error) {
            return false;
        }
    }
    
    /**
     * Opens the passed filename and reads the generic object from it
     * 
     * @param filename the filename to open
     * @return the generic data type in the file
     */
    public T openObject(String filename) {
        try {
            FileInputStream   stream = new FileInputStream(filename);
            ObjectInputStream input  = new ObjectInputStream(stream);
            T object = (T)input.readObject();
            input.close();
            return object;            
        }
        catch (ClassCastException e) {
            return null;
        }
        catch (ClassNotFoundException e) {
            return null;
        }
        catch(NullPointerException e) {
            return null;
        }
        catch (IOException error) {
            return null;
        }
    }
    
    /**
     * Opens the passed file object and reads the generic object from it
     * 
     * @param file the file object to open
     * @return the generic data type in the file
     */
    public T openObject(File file) {
        try {
            return openObject(file.getAbsolutePath());
        }
        catch(NullPointerException e) {
            return null;
        }
    } 
    
}