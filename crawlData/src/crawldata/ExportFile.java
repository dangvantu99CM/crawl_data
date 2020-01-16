/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawldata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Ct_fsp_2
 */
public class ExportFile {
    public static ArrayList<String> file = new ArrayList<String>();
	
    static String country[] = {"UK","England","Wales","Scotland"};
    static String weatherParam []={"Tmax","Tmin","Tmean","Sunshine","Rainfall","Raindays1mm"};
    
    public static void downloader(String fileURL, String saveDir,String nameAppend) throws IOException
    {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                       fileURL.length());
            }
            fileName = fileName.substring(0, fileName.indexOf('.')) +"_"+ nameAppend+ fileName.substring(fileName.lastIndexOf('.'),fileName.length());
            // adding the name of the downloaded file into the array list
           // ParserMainClass.file.add(fileName);
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
            System.out.println("Downloading...");

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println(fileName+"File downloaded\n");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
    public static void main(String args[]) throws IOException{
        String  saveDir="Downloaded_Data";
        String 	fileURL = "";
        String  fileName ="";
        ExportFile slowDown = new ExportFile();
//        DirectoryChooser dirChooser = new DirectoryChooser();
//        File chosenDir = dirChooser.showDialog(primaryStage);
      // sending the links to the downloader to download the .txt files
        for(int i=0;i<country.length;i++)
          {
              for(int j=0;j<weatherParam.length;j++)
              {
                    fileURL = urlGenertor(country[i], weatherParam[j]);
                    fileName = weatherParam[j];
                    slowDown.downloader(fileURL, saveDir,fileName);
              }
        }	
        parser();
    }
    
    // this function generates the URL of the text file
	public static String urlGenertor(String region, String weather_Param)
	{
	   String baseURL="https://www.metoffice.gov.uk/pub/data/weather/uk/climate/datasets/";	
		
	   baseURL += weather_Param+"/date/"+region+".txt";
	   
	   return baseURL;
	}
	
	public static void parser() throws IOException
	{
//		CSV_FileWriter csvWriter = new CSV_FileWriter();
//		TxtFileReader txtFileReader = new TxtFileReader();
//		ArrayList<String> dataObjects= new ArrayList<String>();
//                String fileName = "out.csv";
//                PrintWriter writer = new PrintWriter(new File(fileName));
//                String conumnNames ="Region_Code,Weather_Param,Year,Key,Value";
	     //FileWriter writer = new FileWriter(fileName);
			
	    // writer.append(csvLine);
	  
//		Weather_Format wFormat = new Weather_Format();
		
		/*
		Iterator<String> nameIterator = ParserMainClass.file.iterator();
            while(nameIterator.hasNext())
	     {
	    	 fileName= nameIterator.next();
	    	
	    	 txtFileReader.reader(fileName);
	    	 // Reading the text file present inside the Download_Data folder in this project
	     }
	     
	     */
	     
//   	        for(String count: country)
//   	        {
//   	        	for(String wsString: weatherParam)
//   	   	        {
//   	   	        	fileName = count+"_"+wsString+'.'+"txt";
//   	   	        	
//   	        	    dataObjects.addAll(txtFileReader.reader(fileName,count,wsString));
//   	   	        	
//   	   	        	
//   	   	        }	
//   	        	
//   	        }
//   	        
//   	        writer.write(conumnNames+'\n');;
//     
//   	        for(String csv : dataObjects)
//   	        {
//   	        	csv= csv+'\n';
   	       // 	csvWriter.fileWriter(csv,0);
//   	             writer.write(csv);
//   	        	System.out.println("ParserMainClass: "+csv);
//   	        	
//   	        }
   	      //  csvWriter.fileWriter("", 1);
//	        writer.flush();
//   	        writer.close();
//   	   	System.out.println(dataObjects.size());
//	     
//	     
	}
}
