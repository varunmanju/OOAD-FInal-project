package abhi.ooad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger extends Subscriber{
    // subType type = subType.LOGGER;
    File outputFile;
    FileWriter writer;
    String fName;

    //constructor to specify type and open file to write to
    //code from https://www.w3schools.com/java/java_files_create.asp for handling files in Java
    Logger(String fileName){
        type = subType.LOGGER;
        fName = fileName;
    
        //create file for output, delete it, and make a new one
        outputFile = new File(fName);
        outputFile.delete();
        outputFile = new File(fName);

        try{
            writer = new FileWriter(outputFile, true);
            writer.write("----------------------" + fName + "----------------------" + "\n");
            writer.close();
        } catch(IOException b){
            System.out.println("error occured while creating writer to" + fName);
            b.printStackTrace();
        }  
    }

    //write to file
    public void update(String clerk, String info, String data){
        // create writer for the file
        try{
            writer = new FileWriter(outputFile, true);
            writer.write(info + " - " + data + "\n");
            writer.close();
        } catch(IOException b){
            System.out.println("error occured while creating writer to" + fName);
            b.printStackTrace();
        }  

     
    }

   
}