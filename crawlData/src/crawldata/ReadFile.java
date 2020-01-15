/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawldata;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ReadFile {
    public static void readFile(File file,ArrayList<String>listIframe,String filter){
        Document htmlFile = null;
        if(file != null){
            try {
                Scanner readFile =new Scanner(file);
                htmlFile = (Document) Jsoup.parse(file, "ISO-8859-1");
                String pattern = "(^.*(var strViewPDF).*$)";
                Pattern r = Pattern.compile(pattern);
                while(readFile.hasNext()){
                    String line = readFile.nextLine();
                    Matcher m = r.matcher(line);
                    if(m.find()){
                        listIframe.add(line);
                        System.out.println("================"+line);
                    }  
                }
                readFile.close();
                  
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }
}
