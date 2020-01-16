/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawldata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ReadFile {
    public static void readFile(File file,ArrayList<String>listResult,String filter) throws FileNotFoundException, IOException{
        Document htmlFile = null;
        if(file != null){
            Scanner readFile =new Scanner(file);
            htmlFile = (Document) Jsoup.parse(file, "ISO-8859-1");
            String pattern = "(^.*(var strViewPDF).*$)";
            String pattern1 = "(src=\"(.*?)\".*?)";
            Pattern r = Pattern.compile(pattern);
            Pattern r1 = Pattern.compile(pattern1);
            String[] output = null;
            while(readFile.hasNext()){
                String line = readFile.nextLine();
                Matcher m = r.matcher(line);
                if(m.find()){
                    Matcher findLink = r1.matcher(line);
                    if(findLink.find()){
                        String resultFind = findLink.group(0);
                        String subString=resultFind.substring(resultFind.indexOf("https"),resultFind.length()-1);
                        listResult.add(subString);
                    } 
                }  
            }
            readFile.close();
        } 
    }
}
