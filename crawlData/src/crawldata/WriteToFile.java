/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawldata;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class WriteToFile {
    
    public static void writeToFile(String fileName,ArrayList<String> listLinks) throws IOException{
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        if(listLinks.size() > 0){
            for(String link:listLinks){
                printWriter.print(link);
            }
        }
        printWriter.close();
    }
    
    public static void main(String []args) throws IOException{
        WriteToFile wf = new WriteToFile();
       // wf.writeToFile("aa");
    }
    
}