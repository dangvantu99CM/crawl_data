
package crawldata;

import crawldata.ActionButon.Button;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import net.miginfocom.swing.MigLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class CrawlData{

    JFrame mainFrame = new JFrame();
    JPanel panel = new JPanel();

    JButton btnImport;
    JButton btnExport;
    
    JButton btnAdd; 
    JButton btnFilter; 
    
    JTextField txtFilter = new JTextField(100);
    JTextField txtFilter1 = new JTextField(100);
    JTextField txtFilter2 = new JTextField(100);
    
    JLabel lblDsc = new JLabel("Result:");
    JTextArea txaDsc = new JTextArea(10, 10);
    
    JFileChooser chooser;
    String choosertitle;
    
    File selectFolder=null;
    ArrayList<File> listFile = new ArrayList<>();
    ArrayList<String> listResult;
    ArrayList<JTextField> listJTextField = new ArrayList<>();

    public CrawlData() {
            panel.setLayout(new MigLayout("debug, fillx", "[][grow][]"));
            
            JPanel p1 = new JPanel(new MigLayout("", "[][grow]", "[][][]"));
            Button importButton
            p1.add(btnImport, "cell 0 0,growx");
            p1.add(btnExport, "cell 1 0,alignx trailing");
            p1.add(btnAdd, "cell 2 0,alignx trailing");
            p1.add(btnFilter, "cell 3 0,alignx trailing"); 

            panel.add(p1,"span 0 1,skip, wrap");
            
            panel.add(txtFilter,"wrap, skip,growx");
            panel.add(txtFilter1,"wrap, skip,growx");
            panel.add(txtFilter2,"wrap, skip,growx");
            
            panel.add(lblDsc, "top");
            panel.add(new JScrollPane(txaDsc), "push, grow");

            mainFrame.add(panel);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setSize(800,600);
            mainFrame.setLocationRelativeTo(null);
            
            mainFrame.setVisible(true);
    }
    
    public File actionListener(){
            btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    listResult=new ArrayList<>();
                    chooser = new JFileChooser(); 
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setDialogTitle(choosertitle);
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) { 
                        System.out.println("getCurrentDirectory(): " 
                           +  chooser.getCurrentDirectory());
                        System.out.println("getSelectedFile() : " 
                           +  chooser.getSelectedFile());
                        selectFolder=chooser.getSelectedFile();
                        if(selectFolder != null){
                            panel.add(new Label(selectFolder.getName()));
                        }
                        try {
                            listFilesForFolder(chooser.getSelectedFile());
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("No Selection ");
                    }
                }
            });
            
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    listJTextField.add(txtFilter);
                    panel.add((new AddFiled()).initAddField());
                }
            });
            
            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(selectFolder == null){
                        JOptionPane.showMessageDialog(null, "Hãy import file bạn muốn lấy dữ liệu.");
                    }
                    else{
                        try {
                            if(txtFilter.getText().equals("")){
                                JOptionPane.showMessageDialog(null, "Hãy nhập trường bạn cần tìm.");
                            }else{
                                for(File file:listFile){
                                    ReadFile.readFile(file,listResult,txtFilter.getText());
                                }
                                printResult(listResult);
                                WriteToFile.writeToFile("output.txt", listResult);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            return selectFolder;                        
    }
    
    public void listFilesForFolder(final File folder) throws FileNotFoundException, IOException {
        for (final File file : folder.listFiles()) {
            if (file.isDirectory()) {
                listFilesForFolder(file);
            } else {
                String fileName=file.getName();                
                String pattern = "(^.*[.html]$)";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(fileName);
                if (m.find()) {
                    listFile.add(file);
                }
            }
        }
    }
    
    public void printResult(ArrayList<String>listResult){
        String result="";
        if(listResult!=null && listResult.size() > 0){
            for(String item:listResult){
                result+=item+"\n";
            }
        }
        txaDsc.setText(result);
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        CrawlData cd = new CrawlData();
        cd.actionListener();
    }
}
